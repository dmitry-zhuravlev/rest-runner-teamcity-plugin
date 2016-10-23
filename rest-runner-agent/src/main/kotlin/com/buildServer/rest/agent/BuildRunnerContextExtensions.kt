package com.buildServer.rest.agent

import com.buildServer.rest.common.RestCallType
import com.buildServer.rest.common.RestRunnerConstants.REST_RUNNER_ALLOWED_HTTP_CODES
import com.buildServer.rest.common.RestRunnerConstants.REST_RUNNER_ALLOWED_HTTP_HEADERS
import com.buildServer.rest.common.RestRunnerConstants.REST_RUNNER_CALL_TYPE
import com.buildServer.rest.common.RestRunnerConstants.REST_RUNNER_ENDPOINT
import com.buildServer.rest.common.RestRunnerConstants.REST_RUNNER_GROOVY_SCRIPT
import com.buildServer.rest.common.RestRunnerConstants.REST_RUNNER_REQUEST_PARAMS
import jetbrains.buildServer.agent.BuildRunnerContext
import jetbrains.buildServer.util.StringUtil.newLineToSpaceDelimited

/**
 * @author Dmitry Zhuravlev
 *         Date: 08/10/2016
 */

fun BuildRunnerContext.isParameterEnabled(key: String) = runnerParameters.containsKey(key) && runnerParameters[key] == java.lang.Boolean.TRUE.toString()

fun BuildRunnerContext.getGroovyScriptBody() = runnerParameters[REST_RUNNER_GROOVY_SCRIPT].orEmpty()
fun BuildRunnerContext.getRestEndpoint() = runnerParameters[REST_RUNNER_ENDPOINT].orEmpty()
fun BuildRunnerContext.getRestCallType() = RestCallType.values().find { it.name == this@getRestCallType.runnerParameters[REST_RUNNER_CALL_TYPE].orEmpty() }
fun BuildRunnerContext.getAllowedStatusCodes() = runnerParameters[REST_RUNNER_ALLOWED_HTTP_CODES].orEmpty().split(",").map { code ->
    try {
        return@map code.trim().toInt()
    } catch(e: NumberFormatException) {
        //simple ignore not valid status codes
    }
    null
}.filterNotNull().toSet()

fun BuildRunnerContext.getAllowedHttpHeaders() = parseParams(REST_RUNNER_ALLOWED_HTTP_HEADERS).toMap()
fun BuildRunnerContext.getRequestParams() = parseParams(REST_RUNNER_REQUEST_PARAMS).toList()


private fun BuildRunnerContext.parseParams(runnerConstant: String)
        = newLineToSpaceDelimited(runnerParameters[runnerConstant].orEmpty()).split(",")
        .map { headerKeyValue ->
            val splitted = headerKeyValue.split("=", limit = 2)
            if (splitted.size == 2 && splitted[0].isNotEmpty() && splitted[0].isNotBlank() && splitted[1].isNotEmpty() && splitted[1].isNotBlank())
                splitted[0].trim() to splitted[1].trim()
            else null
        }.filterNotNull().toSet()
