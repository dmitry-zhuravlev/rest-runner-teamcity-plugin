<%@ taglib prefix="props" tagdir="/WEB-INF/tags/props" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="propertiesBean" scope="request" type="jetbrains.buildServer.controllers.BasePropertiesBean"/>
<div class="parameter">
    REST call endpoint: <strong><props:displayValue name="ui.rest.runner.endpoint"/></strong>
</div>
<div class="parameter">
    Request parameters: <strong><props:displayValue name="ui.rest.runner.request.params" emptyValue="empty"/></strong>
</div>
<c:if test="${not empty propertiesBean.properties['ui.rest.runner.allowed.http.codes']}">
<div class="parameter">
    Allowed HTTP codes: <strong><props:displayValue name="ui.rest.runner.allowed.http.codes"/></strong>
</div>
</c:if>
<c:if test="${not empty propertiesBean.properties['ui.rest.runner.allowed.http.headers']}">
<div class="parameter">
    Allowed HTTP headers: <strong><props:displayValue name="ui.rest.runner.allowed.http.headers"/></strong>
</div>
</c:if>
<c:if test="${not empty propertiesBean.properties['ui.rest.runner.groovy.script']}">
<div class="parameter">
    Groovy script: <strong>specified</strong>
</div>
</c:if>

