<#import "parts/common.ftl" as c>
<@c.page>
<br><br>
<form method="post">
    <div class="card">
        <h3 class="card-header">№${result.test.id}. "${result.name}" </h3>
        <div class="card-body">
            <h5> Date of start :${result.startTest}<br><br></h5>
            <h5> Grade : ${result.grade}<br><br></h5>
        </div>
    </div>

    <table class="mt-3 table table-bordered table-hover">

        <thead class="thead-dark">
        <tr>
            <th>№</th>
            <th scope="col" class="col-md-7">Question text</th>
            <th scope="col" class="col-md-3">Correctly</th>
        </tr>
        </thead>

        <tbody>
    <#list questions as question>

    <tr>
        <th scope="row"></th>
        <td>${question.text}</td>
        <td>right</td>
    </tr>
    </#list>
        </tbody>
    </table>
</form>
</@c.page>