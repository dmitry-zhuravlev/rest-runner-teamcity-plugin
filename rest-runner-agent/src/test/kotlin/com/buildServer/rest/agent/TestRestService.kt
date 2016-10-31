package com.buildServer.rest.agent

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.bind.annotation.*

/**
 * @author Dmitry Zhuravlev
 *         Date:  19.10.2016
 */
@RestController
internal open class TestRestService {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class Status404 : Exception()

    internal data class User(val name: String = "")

    @RequestMapping(value = "/404", method = arrayOf(RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE))
    open fun status404(): Unit = throw Status404()

    @RequestMapping(value = "/200", method = arrayOf(RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE))
    open fun status200() = ""

    @RequestMapping(value = "/GETWithParam", method = arrayOf(RequestMethod.GET))
    open fun GETWithParam(@RequestParam(value = "param") param: String) = param

    @RequestMapping(value = "/POSTWithParam", method = arrayOf(RequestMethod.POST))
    open fun POSTWithParam(@RequestParam(value = "param") param: String) = param

    @RequestMapping(value = "/user", method = arrayOf(RequestMethod.GET))
    open fun user() = User("Dmitry")

    @RequestMapping(value = "/secured", method = arrayOf(RequestMethod.GET))
    open fun secured() = User("Secured")

}

@EnableWebSecurity
@Configuration
open internal class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(security: HttpSecurity) {
        security.authorizeRequests().antMatchers("/secured/**", RequestMethod.GET.name).fullyAuthenticated().and().httpBasic().and().csrf().disable()
    }
}
