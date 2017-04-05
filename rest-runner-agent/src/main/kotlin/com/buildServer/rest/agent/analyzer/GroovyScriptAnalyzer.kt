package com.buildServer.rest.agent.analyzer

import com.github.kittinunf.fuel.core.Response
import jetbrains.buildServer.RunBuildException
import jetbrains.buildServer.agent.BuildFinishedStatus
import jetbrains.buildServer.agent.BuildProgressLogger
import javax.script.ScriptEngineManager
import javax.script.ScriptException

/**
 * @author Dmitry Zhuravlev
 *         Date:  20.10.2016
 */
internal class GroovyScriptAnalyzer(private val groovyScriptBody: String, private val buildLogger: BuildProgressLogger) : ResponseAnalyzer {

    private lateinit var status: ResponseAnalyzerStatus

    override fun status() = status

    val groovyEngine = ScriptEngineManager(javaClass.classLoader).getEngineByName("groovy") ?: throw RunBuildException("Cannot load Groovy engine")

    override fun analyze(response: Response) = with(response) {
        if (groovyScriptBody.isEmpty() || groovyScriptBody.isBlank()) {
            status = ResponseAnalyzerStatus(BuildFinishedStatus.FINISHED_SUCCESS)
        } else
            try {
                if ((groovyEngine.apply {
                    put("headers", response.httpResponseHeaders)
                    put("response", String(response.data, Charsets.UTF_8))
                    put("buildLogger", buildLogger)
                }.eval(groovyScriptBody) as? Boolean) ?: false)
                    status = ResponseAnalyzerStatus(BuildFinishedStatus.FINISHED_SUCCESS)
                else
                    status = ResponseAnalyzerStatus(BuildFinishedStatus.FINISHED_WITH_PROBLEMS)
            } catch (e: ScriptException) {
                status = ResponseAnalyzerStatus(BuildFinishedStatus.FINISHED_WITH_PROBLEMS, "Groovy script produce exception: ${e.message ?: ""}")
            }
        status
    }

    override fun toString() = "Groovy script analyzer"
}