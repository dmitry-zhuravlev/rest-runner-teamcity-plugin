package com.buildServer.rest.common.http

import jetbrains.buildServer.runner.CommandLineArgumentsUtil
import java.net.InetSocketAddress
import java.net.Proxy

/**
 * @author Dmitry Zhuravlev
 *         Date:  13.10.2016
 */
class ProxyLocator {
    companion object {
        fun findServerProxyConfiguration() = findEnvironmentProxyConfiguration("TEAMCITY_SERVER_OPTS")

        fun findAgentProxyConfiguration() = findEnvironmentProxyConfiguration("TEAMCITY_AGENT_OPTS")

        fun findProxyConfigurationInJVMParams(params: List<String>) = params
                .fold(ProxyParams()) { proxyParams, param ->
                    if (param.startsWith("-Dhttp.proxyHost") || param.startsWith("-Dhttps.proxyHost")) proxyParams.host = param.split("=")[1]
                    if (param.startsWith("-Dhttp.proxyPort") || param.startsWith("-Dhttp.proxyPort")) proxyParams.port = param.split("=")[1].toInt()
                    proxyParams
                }
                .let { proxyParams ->
                    val host = proxyParams.host
                    val port = proxyParams.port
                    if (host != null && port != null) {
                        Proxy(Proxy.Type.HTTP, InetSocketAddress(host, port))
                    } else null
                }

        private fun findEnvironmentProxyConfiguration(envKey: String) = findProxyConfigurationInJVMParams(CommandLineArgumentsUtil.extractArguments(System.getenv(envKey).orEmpty()))


        private data class ProxyParams(var host: String? = null, var port: Int? = null)
    }
}