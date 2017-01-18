package com.buildServer.rest.agent.analyzer

import com.github.kittinunf.fuel.core.Response
import jetbrains.buildServer.agent.BuildFinishedStatus

/**
 * @author Dmitry Zhuravlev
 *         Date:  19.10.2016
 */
interface ResponseAnalyzer {
    fun analyze(response: Response): ResponseAnalyzerStatus
    fun status(): ResponseAnalyzerStatus
    infix operator fun plus(other: ResponseAnalyzer): Set<ResponseAnalyzer> = setOf(this, other)
}

data class ResponseAnalyzerStatus(val buildFinishedStatus: BuildFinishedStatus, val description: String? = null)

fun Set<ResponseAnalyzer>.worstCase(response: Response) = map { analyzer -> analyzer.analyze(response).buildFinishedStatus }.reduce(BuildFinishedStatus::worse)

fun Set<ResponseAnalyzer>.describeFailedAnalyzers() = filter { analyzer -> analyzer.status().buildFinishedStatus.isFailed }
        .map { analyzer ->
            val statusDescription = analyzer.status().description
            if (statusDescription != null) "$analyzer [$statusDescription]" else "$analyzer"
        }.joinToString(separator = ", \n")
