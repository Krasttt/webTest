<#import "parts/common.ftl" as c>
<@c.page>
    <#list questions as question>
        <div class="card">
            <div class="card-header">
                <h4>${question.text}</h4>
            </div>
            <div class="card-body">
                <h5 class="card-title"></h5>
                <p class="card-text">${question.type}</p>
            </div>
        </div>
    <br>
    </#list>

</@c.page>