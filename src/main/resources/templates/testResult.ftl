<#import "parts/common.ftl" as c>

<@c.page>
<br><br>
<form method="post">
    <div class="card">
        <h3 class="card-header">№${test.id}. "${test.name}" </h3>
    <#--<button class="btn btn-outline-success my-2 my-sm-0 float-right"
    type="submit" formaction="/addquestion?id=${test.id}">-->
    <#--Add question-->
    <#--</button>-->
        <div class="card-body">
            <h5>${countRightAnswers} of ${amountQuestions} right<br><br></h5>
            <h5> Grade : ${result.grade}<br><br></h5>
        </div>
    <#--<button class="btn btn-outline-success my-2 my-sm-0" type="submit" formaction="/test?id=${test.id}">-->
    <#--Start test!-->
    <#--</button>-->
    </div>

</form>

</@c.page>