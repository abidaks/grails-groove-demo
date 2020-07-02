<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="Temperature Calculator" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div id="list-tempCalculator" class="content scaffold-list" role="main">
            <h1>Temperature Calculator</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <p>this is the converted temperature page</p>
        </div>
    </body>
</html>