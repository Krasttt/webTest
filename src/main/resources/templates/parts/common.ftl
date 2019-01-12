<#macro page>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset=UTF-8"/>
    <title>TEST</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <#--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"-->
          <#--integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">-->
<#--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>-->

    <script type="text/javascript"
            src="/webjars/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css"
          href="/webjars/bootstrap/4.2.1/css/bootstrap.min.css" />




</head>
<body>
    <#include "navbar.ftl">
<div class="container">
<#nested >
</div>
<#--<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"-->
        <#--integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"-->
        <#--crossorigin="anonymous"></script>-->
<#--<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"-->
        <#--integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"-->
        <#--crossorigin="anonymous"></script>-->

<script type="text/javascript"
        src="/webjars/jquery/3.2.1/jquery.min.js"></script>

<script type="text/javascript"
        src="/webjars/bootstrap/4.2.1/js/bootstrap.min.js"></script>
</body>
</html>
</#macro>