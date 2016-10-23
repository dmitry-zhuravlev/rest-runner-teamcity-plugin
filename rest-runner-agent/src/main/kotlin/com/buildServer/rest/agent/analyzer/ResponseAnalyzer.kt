package com.buildServer.rest.agent.analyzer

import com.github.kittinunf.fuel.core.Response
import jetbrains.buildServer.agent.BuildFinishedStatus

/**
 * @author Dmitry Zhuravlev
 *         Date:  19.10.2016
 */
interface ResponseAnalyzer {
    fun analyze(response: Response): BuildFinishedStatus
    fun isFailed(): Boolean
    infix operator fun plus(other: ResponseAnalyzer): Set<ResponseAnalyzer> = setOf(this, other)
}

fun Set<ResponseAnalyzer>.worstCase(response: Response) = map { analyzer -> analyzer.analyze(response) }.reduce(BuildFinishedStatus::worse)

fun Set<ResponseAnalyzer>.describeFailedAnalyzers() = map { analyzer -> if (analyzer.isFailed()) "$analyzer" else null }.filterNotNull().joinToString()
