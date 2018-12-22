<#import "parts/common.ftl" as c>
<@c.page>
<div class="card">
    <h3 class="card-header">${user.firstName?if_exists} ${user.surName?if_exists}</h3>
    <div class="card-body">
        <h5>E-mail : ${user.email?if_exists}<br><br></h5>
    </div>
</div>
</@c.page>