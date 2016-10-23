package com.buildServer.rest.agent

import jetbrains.buildServer.agent.*
import jetbrains.buildServer.agentServer.AgentBuild
import jetbrains.buildServer.artifacts.ArtifactDependencyInfo
import jetbrains.buildServer.parameters.ValueResolver
import jetbrains.buildServer.util.Option
import jetbrains.buildServer.vcs.VcsChangeInfo
import jetbrains.buildServer.vcs.VcsRoot
import jetbrains.buildServer.vcs.VcsRootEntry
import java.io.File

/**
 * @author Dmitry Zhuravlev
 *         Date:  19.10.2016
 */
open internal class TestAgentRunningBuild : AgentRunningBuild {
    override fun getInterruptReason(): BuildInterruptReason? {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getBuildFeaturesOfType(type: String): MutableCollection<AgentBuildFeature> {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getWorkingDirectory(): File {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getBuildNumber(): String {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getResolvedParameters(): ResolvedParameters {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getEffectiveCheckoutMode(): AgentCheckoutMode? {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getArtifactsPaths(): String? {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getSharedBuildParameters(): BuildParametersMap {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getCheckoutDirectory(): File {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getFailBuildOnExitCode(): Boolean {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getSharedParametersResolver(): ValueResolver {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getRunnerParameters(): MutableMap<String, String> {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getBuildFeatures(): MutableCollection<AgentBuildFeature> {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun addSharedEnvironmentVariable(key: String, value: String) {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getUnresolvedParameters(): UnresolvedParameters {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getRunType(): String {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun addSharedSystemProperty(key: String, value: String) {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getBuildParameters(): BuildParametersMap {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun addSharedConfigParameter(key: String, value: String) {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun isInAlwaysExecutingStage(): Boolean {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun isCheckoutOnServer(): Boolean {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getAgentConfiguration(): BuildAgentConfiguration {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getSharedConfigParameters(): MutableMap<String, String> {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun stopBuild(reason: String) {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun <T : Any?> getBuildTypeOptionValue(option: Option<T>): T {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun describe(verbose: Boolean): String {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getDefaultCheckoutDirectory(): File {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun isBuildFailingOnServer(): Boolean {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getMandatoryBuildParameters(): BuildParametersMap {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getBuildId(): Long {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getExecutionTimeoutMinutes(): Long {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getVcsSettingsHashForCheckoutMode(agentCheckoutMode: AgentCheckoutMode?): String {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getBuildTypeExternalId(): String {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getArtifactDependencies(): MutableList<ArtifactDependencyInfo> {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getBuildPreviousVersion(vcsRoot: VcsRoot): String {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun isCleanBuild(): Boolean {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getBuildLogger(): BuildProgressLogger {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun isCustomCheckoutDirectory(): Boolean {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getBuildTypeId(): String {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getBuildTempDirectory(): File {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun isPersonal(): Boolean {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getPersonalVcsChanges(): MutableList<VcsChangeInfo> {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getCheckoutType(): AgentBuild.CheckoutType {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun isPersonalPatchAvailable(): Boolean {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getAccessCode(): String {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getBuildTypeName(): String {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getAgentTempDirectory(): File {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getBuildCurrentVersion(vcsRoot: VcsRoot): String {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun isCheckoutOnAgent(): Boolean {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getProjectName(): String {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getVcsChanges(): MutableList<VcsChangeInfo> {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getVcsRootEntries(): MutableList<VcsRootEntry> {
        throw UnsupportedOperationException("not implemented") 
    }

    override fun getAccessUser(): String {
        throw UnsupportedOperationException("not implemented") 
    }
}