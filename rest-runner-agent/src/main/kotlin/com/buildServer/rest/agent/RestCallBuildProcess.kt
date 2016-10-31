package com.buildServer.rest.agent

import com.buildServer.rest.agent.analyzer.*
import com.buildServer.rest.common.RestCallType.*
import com.buildServer.rest.common.http.HttpClient
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.interceptors.redirectResponseInterceptor
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import jetbrains.buildServer.BuildProblemData
import jetbrains.buildServer.BuildProblemTypes
import jetbrains.buildServer.RunBuildException
import jetbrains.buildServer.agent.BuildFinishedStatus
import jetbrains.buildServer.agent.BuildProcessAdapter
import jetbrains.buildServer.agent.BuildRunnerContext

/**
 * @author Dmitry Zhuravlev
 *         Date:  18.10.2016
 */
internal class RestCallBuildProcess(private val context: BuildRunnerContext) : BuildProcessAdapter() {

    private var normallyFinished: Boolean = false

    private var isInterrupted: Boolean = false

    private val endpoint = context.getRestEndpoint()

    private val buildLogger = context.build.buildLogger

    private val responseAnalyzers = HeadersAnalyzer(context.getAllowedHttpHeaders()) + StatusCodeAnalyzer(context.getAllowedStatusCodes()) + GroovyScriptAnalyzer(context.getGroovyScriptBody())

    private lateinit var call: Request

    init {
        FuelManager.instance.client = HttpClient()
        FuelManager.instance.removeAllResponseInterceptors()
        FuelManager.instance.addResponseInterceptor(redirectResponseInterceptor())
    }

    override fun start() = prepareCall()

    override fun waitFor() = executeCall()

    override fun interrupt() = interruptCall()

    override fun isInterrupted() = isInterrupted

    override fun isFinished() = isInterrupted || normallyFinished


    private fun interruptCall() {
        call.interrupt {
            buildLogger.message("REST call to $endpoint interrupted")
        }
        isInterrupted = true
    }

    private fun prepareCall() {
        val restCallType = context.getRestCallType() ?: throw RunBuildException("Cannot determine REST call type")
        call = when (restCallType) {
            GET -> endpoint.httpGet(context.getRequestParams())
            POST -> endpoint.httpPost(context.getRequestParams())
            PUT -> endpoint.httpPut(context.getRequestParams())
            DELETE -> endpoint.httpDelete(context.getRequestParams())
        }
    }

    private fun executeCall(): BuildFinishedStatus {
        val (request, response, result) = call.responseString()
        val (responseString, error) = result
        if (error != null) {
            throw RunBuildException("REST call to $endpoint failed ${error.exception.message ?: ""}")
        }
        buildLogger.message("""Executed REST call: $response""")
        val worstCase = responseAnalyzers.worstCase(response)
        if (worstCase.isFailed) {
            val failedReason = "REST runner assertion. The following analyzers failed: ${responseAnalyzers.describeFailedAnalyzers()}"
            buildLogger.error(failedReason)
            buildLogger.logBuildProblem(BuildProblemData.createBuildProblem(context.runType + response.httpStatusCode, BuildProblemTypes.TC_EXIT_CODE_TYPE, failedReason))
        }
        return worstCase
    }
}



