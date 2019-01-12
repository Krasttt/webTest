<#import "../parts/common.ftl" as c>

<@c.page>
<br><br>
<form method="post">
    <div class="card">
        <h3 class="card-header">№${test.id}. "${test.name}" </h3>
        <div class="card-body">
            <h5>${countRightAnswers} of ${amountQuestions} right<br><br></h5>
            <h5> Grade : ${result.grade}<br><br></h5>
        </div>
    </div>
</form>

</@c.page>