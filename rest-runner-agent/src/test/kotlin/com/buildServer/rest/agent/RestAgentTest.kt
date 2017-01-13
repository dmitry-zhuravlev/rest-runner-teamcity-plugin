package com.buildServer.rest.agent

import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan

/**
 * @author Dmitry Zhuravlev
 *         Date:  19.10.2016
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ComponentScan("com.buildServer.rest.agent")
annotation class RestAgentTest