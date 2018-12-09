<#import "parts/common.ftl" as c>

<@c.page>
<br><br>
<form method="post">
    <div class="card">
        <h3 class="card-header">№${test.id}. "${test.name}" <a href="/editTest?id=${test.id}" class="btn btn-success float-right">Edit</a></h3>
        <button class="btn btn-outline-success my-2 my-sm-0 float-right" type="submit" formaction="/addquestion?id=${test.id}">
            Add question
        </button>
        <div class="card-body">
            <h5>Description : ${test.discription?if_exists}<br><br></h5>
            <h5> Test contain <b>${test.amountQuestions}</b> questions.<br><br></h5>
            <h5>Time : ${duration} min</h5>
        </div>
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit" formaction="/test?id=${test.id}">
            Start test!
        </button>
    </div>
</form>

<script>
var obj = {id:${test.id}, name:"${test.name}",discription:"${test.discription}"};
console.log(obj);
var json = JSON.stringify(obj);
console.log(json);
</script>
</@c.page>