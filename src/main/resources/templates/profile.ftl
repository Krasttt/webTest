<#import "parts/common.ftl" as c>
<@c.page>
<div class="card">
    <h3 class="card-header">${user.firstName} ${user.surName?if_exists}</h3>
    <div class="card-body mb-10">
    </div>
</div>
    <#if results?has_content>
<table class="table table-bordered table-hover">

    <thead class="thead-dark">
    <tr>
        <th>#</th>
        <th scope="col" class="col-md-6">Name</th>
        <th scope="col" class="col-md-2">Amount questions</th>
        <th scope="col" class="col-md-2">Grade</th>
    </tr>
    </thead>

    <tbody>
    <#list results as result>

    <tr>
        <th scope="row">${result.id}</th>
        <td><a class="nav-link" href="#">${result.name}</a></td>
        <td>${result.test.amountQuestions}</td>
        <td>${result.grade}</td>
    </tr>
    </#list>
    </tbody>
</table>
    <#else>
    <h5>Результатов тестирования не найдено. Пройдите хотя бы один тест.</h5>
    </#if>

</@c.page>