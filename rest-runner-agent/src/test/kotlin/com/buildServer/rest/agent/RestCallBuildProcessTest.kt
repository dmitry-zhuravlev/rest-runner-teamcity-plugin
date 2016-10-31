package com.buildServer.rest.agent

import com.buildServer.rest.common.RestCallType
import com.buildServer.rest.common.RestCallType.*
import com.buildServer.rest.common.RestRunnerConstants.REST_RUNNER_ALLOWED_HTTP_CODES
import com.buildServer.rest.common.RestRunnerConstants.REST_RUNNER_ALLOWED_HTTP_HEADERS
import com.buildServer.rest.common.RestRunnerConstants.REST_RUNNER_CALL_TYPE
import com.buildServer.rest.common.RestRunnerConstants.REST_RUNNER_ENDPOINT
import com.buildServer.rest.common.RestRunnerConstants.REST_RUNNER_GROOVY_SCRIPT
import com.buildServer.rest.common.RestRunnerConstants.REST_RUNNER_REQUEST_PARAMS
import com.buildServer.rest.common.RestRunnerConstants.REST_RUNNER_REQUEST_PASSWORD
import com.buildServer.rest.common.RestRunnerConstants.REST_RUNNER_REQUEST_USERNAME
import jetbrains.buildServer.agent.BuildFinishedStatus
import jetbrains.buildServer.agent.BuildRunnerContext
import jetbrains.buildServer.agent.NullBuildProgressLogger
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner

/**
 * @author Dmitry Zhuravlev
 *         Date:  19.10.2016
 */
@RunWith(SpringRunner::class)
@RestAgentTest
open internal class RestCallBuildProcessTest {

    @Value("\${server.port}")
    lateinit var port: Integer

    @MockBean
    lateinit private var buildRunnerContext: BuildRunnerContext

    @MockBean
    lateinit private var testAgentRunningBuild: TestAgentRunningBuild

    private fun setupBuildRunnerContext(resource: String,
                                        callType: RestCallType,
                                        userName: String? = null,
                                        password: String? = null,
                                        expectedHttpCodes: String = "",
                                        expectedHttpHeaders: String = "",
                                        params: Set<Pair<String, String>> = emptySet(),
                                        responseProcessorGroovyScript: String = "") {
        given(buildRunnerContext.runnerParameters).willReturn(
                mapOf(
                        REST_RUNNER_ENDPOINT to "http://localhost:$port/$resource",
                        REST_RUNNER_CALL_TYPE to callType.name,
                        REST_RUNNER_REQUEST_USERNAME to userName,
                        REST_RUNNER_REQUEST_PASSWORD to password,
                        REST_RUNNER_ALLOWED_HTTP_CODES to expectedHttpCodes,
                        REST_RUNNER_ALLOWED_HTTP_HEADERS to expectedHttpHeaders,
                        REST_RUNNER_REQUEST_PARAMS to params.map { pair -> "${pair.first}=${pair.second}" }.joinToString(),
                        REST_RUNNER_GROOVY_SCRIPT to responseProcessorGroovyScript
                ))
        given(testAgentRunningBuild.buildLogger).willReturn(NullBuildProgressLogger())
        given(buildRunnerContext.build).willReturn(testAgentRunningBuild)
    }

    @Test
    open fun testGet404() {
        setupBuildRunnerContext(
                resource = "404",
                callType = GET,
                expectedHttpCodes = "400, 404",
                expectedHttpHeaders = """Content-Length=306, Content-Type=text/html;charset=UTF-8"""
        )
        val build = RestCallBuildProcess(buildRunnerContext).apply { start() }
        val buildProcessStatus = build.waitFor()
        assertEquals(BuildFinishedStatus.FINISHED_SUCCESS, buildProcessStatus)
    }

    @Test
    open fun testPost404() {
        setupBuildRunnerContext(
                resource = "404",
                callType = POST,
                expectedHttpCodes = "400, 404",
                expectedHttpHeaders = """Content-Length=306, Content-Type=text/html;charset=UTF-8"""
        )
        val build = RestCallBuildProcess(buildRunnerContext).apply { start() }
        val buildProcessStatus = build.waitFor()
        assertEquals(BuildFinishedStatus.FINISHED_SUCCESS, buildProcessStatus)
    }

    @Test
    open fun testPut404() {
        setupBuildRunnerContext(
                resource = "404",
                callType = PUT,
                expectedHttpCodes = "400, 404",
                expectedHttpHeaders = """Content-Length=306, Content-Type=text/html;charset=UTF-8"""
        )
        val build = RestCallBuildProcess(buildRunnerContext).apply { start() }
        val buildProcessStatus = build.waitFor()
        assertEquals(BuildFinishedStatus.FINISHED_SUCCESS, buildProcessStatus)
    }

    @Test
    open fun testDelete404() {
        setupBuildRunnerContext(
                resource = "404",
                callType = DELETE,
                expectedHttpCodes = "400, 404",
                expectedHttpHeaders = """Content-Length=306, Content-Type=text/html;charset=UTF-8"""
        )
        val build = RestCallBuildProcess(buildRunnerContext).apply { start() }
        val buildProcessStatus = build.waitFor()
        assertEquals(BuildFinishedStatus.FINISHED_SUCCESS, buildProcessStatus)
    }


    @Test
    open fun testFailGet404() {
        setupBuildRunnerContext(
                resource = "404",
                callType = GET,
                expectedHttpCodes = "400, 201",
                expectedHttpHeaders = """Content-Length=306, Content-Type=text/html;charset=UTF-8"""
        )
        val build = RestCallBuildProcess(buildRunnerContext).apply { start() }
        val buildProcessStatus = build.waitFor()
        assertEquals(BuildFinishedStatus.FINISHED_FAILED, buildProcessStatus)
    }

    @Test
    open fun testFailPost404() {
        setupBuildRunnerContext(
                resource = "404",
                callType = POST,
                expectedHttpCodes = "400, 201",
                expectedHttpHeaders = """Content-Length=306, Content-Type=text/html;charset=UTF-8"""
        )
        val build = RestCallBuildProcess(buildRunnerContext).apply { start() }
        val buildProcessStatus = build.waitFor()
        assertEquals(BuildFinishedStatus.FINISHED_FAILED, buildProcessStatus)
    }

    @Test
    open fun testFailPut404() {
        setupBuildRunnerContext(
                resource = "404",
                callType = PUT,
                expectedHttpCodes = "400, 201",
                expectedHttpHeaders = """Content-Length=306, Content-Type=text/html;charset=UTF-8"""
        )
        val build = RestCallBuildProcess(buildRunnerContext).apply { start() }
        val buildProcessStatus = build.waitFor()
        assertEquals(BuildFinishedStatus.FINISHED_FAILED, buildProcessStatus)
    }

    @Test
    open fun testFailDelete404() {
        setupBuildRunnerContext(
                resource = "404",
                callType = DELETE,
                expectedHttpCodes = "400, 201",
                expectedHttpHeaders = """Content-Length=306, Content-Type=text/html;charset=UTF-8"""
        )
        val build = RestCallBuildProcess(buildRunnerContext).apply { start() }
        val buildProcessStatus = build.waitFor()
        assertEquals(BuildFinishedStatus.FINISHED_FAILED, buildProcessStatus)
    }

    @Test
    open fun testGet200WithoutExpected() {
        setupBuildRunnerContext(
                resource = "200",
                callType = GET
        )
        val build = RestCallBuildProcess(buildRunnerContext).apply { start() }
        val buildProcessStatus = build.waitFor()
        assertEquals(BuildFinishedStatus.FINISHED_SUCCESS, buildProcessStatus)
    }

    @Test
    open fun testGet200() {
        setupBuildRunnerContext(
                resource = "200",
                callType = GET,
                expectedHttpCodes = "201, 200",
                expectedHttpHeaders = """Content-Type=text/html;charset=UTF-8"""
        )
        val build = RestCallBuildProcess(buildRunnerContext).apply { start() }
        val buildProcessStatus = build.waitFor()
        assertEquals(BuildFinishedStatus.FINISHED_SUCCESS, buildProcessStatus)
    }

    @Test
    open fun testFailGet200() {
        setupBuildRunnerContext(
                resource = "200",
                callType = GET,
                expectedHttpCodes = "201, 200",
                expectedHttpHeaders = """Content-Type=text/html;charset=UTF-9"""
        )
        val build = RestCallBuildProcess(buildRunnerContext).apply { start() }
        val buildProcessStatus = build.waitFor()
        assertEquals(BuildFinishedStatus.FINISHED_FAILED, buildProcessStatus)
    }

    @Test
    open fun testCallGETWithParam() {
        setupBuildRunnerContext(
                resource = "GETWithParam",
                params = setOf(
                        "param" to "value"
                ),
                callType = GET,
                expectedHttpCodes = "200",
                expectedHttpHeaders = """Content-Length=5"""
        )
        val build = RestCallBuildProcess(buildRunnerContext).apply { start() }
        val buildProcessStatus = build.waitFor()
        assertEquals(BuildFinishedStatus.FINISHED_SUCCESS, buildProcessStatus)
    }

    @Test
    open fun testCallPOSTWithParam() {
        setupBuildRunnerContext(
                resource = "POSTWithParam",
                params = setOf(
                        "param" to "value"
                ),
                callType = POST,
                expectedHttpCodes = "200",
                expectedHttpHeaders = """Content-Length=5"""
        )
        val build = RestCallBuildProcess(buildRunnerContext).apply { start() }
        val buildProcessStatus = build.waitFor()
        assertEquals(BuildFinishedStatus.FINISHED_SUCCESS, buildProcessStatus)
    }


    @Test
    open fun testCallGetUser() {
        val groovyScriptBody = """
        def parser = new groovy.json.JsonSlurper()
        def user = parser.parseText("${'$'}response")
        user.name == 'Dmitry' && headers['Content-Type'][0] == 'application/json;charset=UTF-8'
        """
        setupBuildRunnerContext(
                resource = "user",
                callType = GET,
                expectedHttpCodes = "200",
                responseProcessorGroovyScript = groovyScriptBody,
                expectedHttpHeaders = """Content-Type=application/json;charset=UTF-8"""
        )
        val build = RestCallBuildProcess(buildRunnerContext).apply { start() }
        val buildProcessStatus = build.waitFor()
        assertEquals(BuildFinishedStatus.FINISHED_SUCCESS, buildProcessStatus)
    }

    @Test
    open fun testFailCallGetUser() {
        val groovyScriptBody = """
        def parser = new groovy.json.JsonSlurper()
        def user = parser.parseText("${'$'}response")
        user.name == 'John'
        """
        setupBuildRunnerContext(
                resource = "user",
                callType = GET,
                expectedHttpCodes = "200",
                responseProcessorGroovyScript = groovyScriptBody,
                expectedHttpHeaders = """Content-Type=application/json;charset=UTF-8"""
        )
        val build = RestCallBuildProcess(buildRunnerContext).apply { start() }
        val buildProcessStatus = build.waitFor()
        assertEquals(BuildFinishedStatus.FINISHED_FAILED, buildProcessStatus)
    }

    @Test
    open fun testGetSecuredEndpoint() {
        setupBuildRunnerContext(
                resource = "secured",
                callType = GET,
                userName = "user",
                password = "e90468c9-b001-42e2-94e1-a1416ecb95ca",
                expectedHttpCodes = "200",
                expectedHttpHeaders = """Content-Type=application/json;charset=UTF-8"""
        )
        val build = RestCallBuildProcess(buildRunnerContext).apply { start() }
        val buildProcessStatus = build.waitFor()
        assertEquals(BuildFinishedStatus.FINISHED_SUCCESS, buildProcessStatus)
    }

    @Test
    open fun testGetSecuredWithoutAuthEndpoint() {
        setupBuildRunnerContext(
                resource = "secured",
                callType = GET,
                expectedHttpCodes = "401",
                expectedHttpHeaders = """Content-Type=text/html;charset=UTF-8"""
        )
        val build = RestCallBuildProcess(buildRunnerContext).apply { start() }
        val buildProcessStatus = build.waitFor()
        assertEquals(BuildFinishedStatus.FINISHED_SUCCESS, buildProcessStatus)
    }
}