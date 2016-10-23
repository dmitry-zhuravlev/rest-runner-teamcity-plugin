package com.buildServer.rest.agent

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

/**
 * @author Dmitry Zhuravlev
 *         Date:  19.10.2016
 */
@RestController
internal open class TestRestService {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    private class Status404 : Exception()

    internal data class User(val name:String ="")

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

}