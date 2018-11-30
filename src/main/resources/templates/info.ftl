<#import "parts/common.ftl" as c>

<@c.page>
<br><br>
<form method="post">
    <div class="card">
        <h3 class="card-header">№${test.id}. "${test.name}"</h3>
        <button class="btn btn-outline-success my-2 my-sm-0 float-right" type="submit" formaction="/addquestion">
            Добавить вопрос
        </button>
        <div class="card-body">
            <h5>Описание теста : ${test.discription?if_exists}<br><br></h5>
            <h5> Тест состоит из <b>${test.amountQuestions}</b> вопросов.<br><br></h5>
            <h5>Время : ${duration} min</h5>
        </div>
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit" formaction="/test?id=${test.id}">Начать
            тест
        </button>
    </div>
</form>
</@c.page>