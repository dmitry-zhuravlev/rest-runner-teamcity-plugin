package com.buildServer.rest.agent

import com.buildServer.rest.common.RestRunnerConstants
import jetbrains.buildServer.agent.*

/**
 * @author Dmitry Zhuravlev
 *         Date: 08/10/2016
 */
internal open class RestRunnerService : AgentBuildRunner {

    private val info = object : AgentBuildRunnerInfo {

        override fun canRun(agentConfiguration: BuildAgentConfiguration) = true

        override fun getType() = RestRunnerConstants.RUNNER_TYPE
    }

    override fun getRunnerInfo() = info

    override fun createBuildProcess(runningBuild: AgentRunningBuild, context: BuildRunnerContext) = RestCallBuildProcess(context)

}