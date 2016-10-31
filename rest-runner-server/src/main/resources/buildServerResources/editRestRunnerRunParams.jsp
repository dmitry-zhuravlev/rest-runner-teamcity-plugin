<%@ page import="static com.buildServer.rest.common.RestRunnerConstants.*" %>
<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="l" tagdir="/WEB-INF/tags/layout" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="forms" tagdir="/WEB-INF/tags/forms" %>
<%@ taglib prefix="bs" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:useBean id="callTypeSelectBean" class="com.buildServer.rest.server.CallTypeSelectBean"
             scope="request"/>

<l:settingsGroup title="REST call parameters">
    <tr>
        <th><label for="ui.rest.runner.endpoint">Endpoint: </label></th>
        <td>
            <props:selectProperty name="<%=REST_RUNNER_CALL_TYPE%>"
                                  enableFilter="true"
                                  className="smallField">
                <props:option value="GET" selected="${callTypeSelectBean.selectedVersion eq 'GET'}">GET</props:option>
                <props:option value="POST" selected="${callTypeSelectBean.selectedVersion eq 'POST'}">POST</props:option>
                <props:option value="PUT" selected="${callTypeSelectBean.selectedVersion eq 'PUT'}">PUT</props:option>
                <props:option value="DELETE" selected="${callTypeSelectBean.selectedVersion eq 'DELETE'}">DELETE</props:option>
            </props:selectProperty>
            <props:textProperty name="<%=REST_RUNNER_ENDPOINT%>" className="longField" maxlength="256"/>
            <span class="smallNote">URL to perform the call.</span>
        </td>
    </tr>
    <tr>
        <th><label for="ui.rest.runner.request.params">Request parameters: </label></th>
        <td>
            <props:textProperty name="<%=REST_RUNNER_REQUEST_PARAMS%>" className="longField" maxlength="256" expandable="true"/>
            <span class="smallNote">Request params specified in form key=value with "," delimiter. E.g. param1=value1, param2=value2.</span>
        </td>
    </tr>
    <tr class="advancedSetting">
        <th><label for="ui.rest.runner.request.username">Username: </label></th>
        <td>
            <props:textProperty name="<%=REST_RUNNER_REQUEST_USERNAME%>" className="longField" maxlength="256"/>
            <span class="smallNote">Username for BASIC authentication on endpoint.</span>
        </td>
    </tr>
    <tr class="advancedSetting">
        <th><label for="ui.rest.runner.request.password">Password: </label></th>
        <td>
            <props:passwordProperty name="<%=REST_RUNNER_REQUEST_PASSWORD%>" className="longField" maxlength="256"/>
            <span class="smallNote">Password for BASIC authentication on endpoint.</span>
        </td>
    </tr>
</l:settingsGroup>
<l:settingsGroup title="Response assertions">
    <tr>
        <th><label for="ui.rest.runner.allowed.http.codes">Allowed HTTP codes: </label></th>
        <td>
            <props:textProperty name="<%=REST_RUNNER_ALLOWED_HTTP_CODES%>" className="longField" maxlength="256" expandable="true"/>
            <span class="smallNote">Allowed HTTP response codes "," delimiter. E.g. 200, 201.</span>
        </td>
    </tr>
    <tr>
        <th><label for="ui.rest.runner.allowed.http.headers">Allowed HTTP headers: </label></th>
        <td>
            <props:textProperty name="<%=REST_RUNNER_ALLOWED_HTTP_HEADERS%>" className="longField" expandable="true"/>
            <span class="smallNote">Allowed HTTP response headers "," delimiter. E.g. Content-Type=text/html;charset=UTF-9, Content-Length=5.<br/>All headers must be present in response for successful step.</span>
        </td>
    </tr>
    <tr>
        <th><label for="ui.rest.runner.groovy.script">Groovy script: </label></th>
        <td>
            <props:textProperty name="<%=REST_RUNNER_GROOVY_SCRIPT%>" className="longField" expandable="true"/>
            <span class="smallNote">Groovy script for analyzing response.<br/>Script input parameters: "response" - response string, "headers" - response HTTP headers.<br/>Script must return a boolean value: 'true' for successful step 'false' otherwise.</span>
        </td>
    </tr>
</l:settingsGroup>