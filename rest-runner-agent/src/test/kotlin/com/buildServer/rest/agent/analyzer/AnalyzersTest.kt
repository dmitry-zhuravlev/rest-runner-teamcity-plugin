package com.buildServer.rest.agent.analyzer

import com.github.kittinunf.fuel.core.Response
import jetbrains.buildServer.agent.BuildFinishedStatus
import org.junit.Assert
import org.junit.Test

/**
 * @author Dmitry Zhuravlev
 *         Date:  20.10.2016
 */
open class AnalyzersTest {

    @Test
    fun testGroovyAnalyzer() {
        val groovyScriptBody = """
        def parser = new groovy.json.JsonSlurper()
        def object = parser.parseText("${'$'}response")
        object.name == 'Dmitry'
        """
        val responseData = "{ \"name\": \"Dmitry\" } /* some comment */ "
        val status = GroovyScriptAnalyzer(groovyScriptBody).analyze(Response().apply { data = responseData.toByteArray(Charsets.UTF_8) })
        Assert.assertEquals(BuildFinishedStatus.FINISHED_SUCCESS, status)
    }

    @Test
    fun testHeadersAnalyzer() {
        val allowedContentTypeHeader = "Content-Type" to "application/json"
        val status = HeadersAnalyzer(mapOf(allowedContentTypeHeader)).analyze(Response().apply { httpResponseHeaders = hashMapOf("Content-Type" to listOf("application/json")) })
        Assert.assertEquals(BuildFinishedStatus.FINISHED_SUCCESS, status)
    }

    @Test
    fun testStatusCodeAnalyzer() {
        val allowedStatusCodes = setOf(200, 201)
        val status = StatusCodeAnalyzer(allowedStatusCodes).analyze(Response().apply { httpStatusCode = 200 })
        Assert.assertEquals(BuildFinishedStatus.FINISHED_SUCCESS, status)
    }
}