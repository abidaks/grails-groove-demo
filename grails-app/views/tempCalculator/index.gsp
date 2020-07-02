<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="Temperature Calculator" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="list-tempCalculator" class="content scaffold-list" role="main">
            <h1>Temperature Calculator</h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:if test="${flash.errorMsg}">
            <div class="errors" role="status">${flash.errorMsg}</div>
            </g:if>
            <form action="/tempCalculator/convert" method="POST">                      
                        
              <table cellspacing="0" cellpadding="4" frame="box" bordercolor="#dcdcdc" rules="none" style="border-collapse: collapse;">
              <tbody>
              <tr>
                    <td class="frmHeader" background="#dcdcdc" style="border-right: 2px solid white;">Parameter</td>
                    <td class="frmHeader" background="#dcdcdc">Value</td>
                </tr>
              <tr>
                <td class="frmText" style="color: #000000; font-weight: normal;">Type: (Celsius, Fahrenheit)</td>
                <td><input class="frmInput" type="text" size="50" name="property"></td>
              </tr>
            
              <tr>
                <td class="frmText" style="color: #000000; font-weight: normal;">Value:</td>
                <td><input class="frmInput" type="text" size="50" name="val"></td>
              </tr>
            
            <tr>
              <td></td>
              <td align="right"> <input type="submit" value="Convert" class="button"></td>
            </tr>
            </tbody></table>
          

        </form>
        </div>
    </body>
</html>