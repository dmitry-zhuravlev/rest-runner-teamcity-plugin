[![Build Status](https://api.travis-ci.org/dmitry-zhuravlev/rest-runner-teamcity-plugin.svg?branch=master)](https://travis-ci.org/dmitry-zhuravlev/rest-runner-teamcity-plugin)
[![Apache 2.0](https://img.shields.io/badge/license-Apache%202.0-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)

TeamCity build runner plugin for executing REST call and analyzing the response.

<b>Features:</b>

- Execute REST call with the given request parameters to the specified endpoint.
- Specify BASIC auth credentials (advanced build runner options) in case if endpoint require BASIC auth.
- Response assertion with `Allowed HTTP codes` list. If response HTTP code not in the `Allowed HTTP codes` list then build will fail. 
If no code specified in the `Allowed HTTP codes` list then response can contains any of the exist HTTP codes. 
- Response assertion with `Allowed HTTP headers` list. All headers should match against the real headers of the response.
- Response handling with Groovy script. The given script will contain input parameters: `response` - response string, `headers` - response HTTP headers. 
Script must return a conditional boolean flag with value `true` - means no build fail needed or `false` for fail the build.

Groovy script example: 
```groovy
        def parser = new groovy.json.JsonSlurper()
        def user = parser.parseText("$response")
        user.name == 'Dmitry' && headers['Content-Type'][0] == 'application/json;charset=UTF-8'
```

<b>For those who connected behind corporate HTTP(S) proxy server:</b>
HTTP(S) Proxy server should be specified in the following places:
  - In server JVM parameters (TEAMCITY_SERVER_OPTS)
  - In agent JVM parameters (TEAMCITY_AGENT_OPTS)

E.g. for HTTP proxy server: 

`-Dhttp.proxyHost=somecompany.com -Dhttp.proxyPort=3128 -Dhttp.nonProxyHosts="localhost|192.168.*"`

E.g. for HTTPS proxy server: 

`-Dhttps.proxyHost=somecompany.com -Dhttps.proxyPort=3128 -Dhttps.nonProxyHosts="localhost|192.168.*"`
  
<b>Note:</b> To start TeamCity agent in debug mode the `agentOptions` should be specified. This options supported only by 
   `com.github.rodm:gradle-teamcity-plugin:0.10-SNAPSHOT`. Download [gradle-teamcity-plugin](https://github.com/rodm/gradle-teamcity-plugin) and build `0.10-SNAPSHOT` instead of using `0.9.1` 
   

