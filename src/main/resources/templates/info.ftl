<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">

<@c.page>
<br><br>
<form method="post">
    <div class="card">
        <h3 class="card-header">№${test.id}. "${test.name}" <#if isAdmin><a href="/editTest?id=${test.id}"
                                                               class="btn btn-success float-right">Edit</a></#if></h3>
    <#if isAdmin>
        <button class="btn btn-outline-success my-2 my-sm-0 float-right" type="submit"
                formaction="/addquestion?id=${test.id}">
            Add question
        </button>
    </#if>
        <div class="card-body">
            <h5>Description : ${test.description?if_exists}<br><br></h5>
            <h5> Test contain <b>${test.amountQuestions}</b> questions.<br><br></h5>
            <h5>Time : ${duration} min</h5>
        </div>
        <#if 0<test.amountQuestions >
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit" formaction="/test?id=${test.id}">
            Start test!
        </button>
        </#if>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
</form>

</@c.page>