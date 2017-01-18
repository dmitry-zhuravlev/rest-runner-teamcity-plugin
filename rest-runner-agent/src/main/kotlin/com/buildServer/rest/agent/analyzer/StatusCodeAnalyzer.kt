package com.buildServer.rest.agent.analyzer

import com.github.kittinunf.fuel.core.Response
import jetbrains.buildServer.agent.BuildFinishedStatus

/**
 * @author Dmitry Zhuravlev
 *         Date:  19.10.2016
 */
internal class StatusCodeAnalyzer(private val allowedStatusCodes: Set<Int>) : ResponseAnalyzer {

    private lateinit var status: ResponseAnalyzerStatus

    override fun status() = status

    override fun analyze(response: Response) = with(response) {
        if (allowedStatusCodes.isEmpty() || allowedStatusCodes.contains(httpStatusCode))
            status = ResponseAnalyzerStatus(BuildFinishedStatus.FINISHED_SUCCESS)
        else
            status = ResponseAnalyzerStatus(BuildFinishedStatus.FINISHED_WITH_PROBLEMS)
        status
    }


    override fun toString() = "Response status code analyzer"
}