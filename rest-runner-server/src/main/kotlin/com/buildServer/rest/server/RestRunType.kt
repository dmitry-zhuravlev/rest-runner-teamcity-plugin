package com.buildServer.rest.server

import com.buildServer.rest.common.RestRunnerConstants.REST_RUNNER_ENDPOINT
import com.buildServer.rest.common.RestRunnerConstants.RUNNER_TYPE
import jetbrains.buildServer.serverSide.RunType
import jetbrains.buildServer.serverSide.RunTypeRegistry
import jetbrains.buildServer.web.openapi.PluginDescriptor

/**
 * @author Dmitry Zhuravlev
 *         Date:  07.10.2016
 */
open internal class RestRunType(runTypeRegistry: RunTypeRegistry, val pluginDescriptor: PluginDescriptor) : RunType() {

    init {
        runTypeRegistry.registerRunType(this)
    }

    override fun getType() = RUNNER_TYPE

    override fun getDisplayName() = "REST"

    override fun getDescription() = "REST Runner"

    override fun getEditRunnerParamsJspFilePath() = "${pluginDescriptor.pluginResourcesPath}/editRestRunnerRunParams.jsp"

    override fun getViewRunnerParamsJspFilePath() = "${pluginDescriptor.pluginResourcesPath}/viewRestRunnerRunParams.jsp"

    override fun getRunnerPropertiesProcessor() = null

    override fun getDefaultRunnerProperties() = emptyMap<String, String>()

    override fun describeParameters(parameters: MutableMap<String, String>) = with(StringBuilder()) {
        val restEndpoint = parameters[REST_RUNNER_ENDPOINT]
        if (!restEndpoint.isNullOrEmpty()) {
            append("REST call endpoint: $restEndpoint")
        }
        toString()
    }
}

