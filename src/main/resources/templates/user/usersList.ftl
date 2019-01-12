<#import "../parts/common.ftl" as c>
<#include "../parts/security.ftl">
<@c.page>
<h1>Welcome to Tester, ${username}</h1>
<table class="table table-bordered table-hover">

    <thead class="thead-dark">
    <tr>
        <th>#</th>
        <th scope="col" class="col-md-7">Name</th>
        <th scope="col" class="col-md-3">Role</th>
    </tr>
    </thead>

    <tbody>
    <#list users as user>

    <tr>
        <th scope="row">${user.id}</th>
        <td><a class="nav-link" href="user/${user.id}">${user.username}</a></td>
        <td>${user.role}</td>
    </tr>
    </#list>
    </tbody>
</table>
</@c.page>
