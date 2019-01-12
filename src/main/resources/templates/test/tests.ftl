<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl">
<@c.page>
<h1>Welcome to Tester, ${username}<#if isAdmin><a href="/createtest" class="btn btn-success mt-2 float-right">Create</a></#if></h1>
<table class="table table-bordered table-hover">

    <thead class="thead-dark">
    <tr>
        <th>#</th>
        <th scope="col" class="col-md-7">Name</th>
        <th scope="col" class="col-md-3">Amount of questions</th>
    </tr>
    </thead>

    <tbody>
    <#list tests as test>

    <tr>
        <th scope="row">${test.id}</th>
        <td><a class="nav-link" href="/info?id=${test.id}">${test.name}</a></td>
        <td>${test.amountQuestions}</td>
    </tr>
    </#list>
    </tbody>
</table>
</@c.page>
