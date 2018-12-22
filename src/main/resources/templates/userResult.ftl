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
</form>

</@c.page>