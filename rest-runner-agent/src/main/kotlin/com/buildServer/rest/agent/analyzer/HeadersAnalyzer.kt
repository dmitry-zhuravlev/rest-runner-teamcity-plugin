package com.buildServer.rest.agent.analyzer

import com.github.kittinunf.fuel.core.Response
import jetbrains.buildServer.agent.BuildFinishedStatus

/**
 * @author Dmitry Zhuravlev
 *         Date:  19.10.2016
 */
internal class HeadersAnalyzer(private val allowedHeaders: Map<String, String>) : ResponseAnalyzer {

    private lateinit var status: BuildFinishedStatus

    override fun isFailed() = status.isFailed

    override fun analyze(response: Response) = with(response) {
        if (allowedHeaders.isEmpty() || allowedHeaders.entries.all { header -> containsHeader(header, response) })
            status = BuildFinishedStatus.FINISHED_SUCCESS
        else
            status = BuildFinishedStatus.FINISHED_WITH_PROBLEMS
        status
    }

    private fun containsHeader(header: Map.Entry<String, String>, response: Response): Boolean {
        val (key, value) = header
        return response.httpResponseHeaders[key]?.contains(value) ?: false
    }

    override fun toString() = "HTTP headers analyzer"
}