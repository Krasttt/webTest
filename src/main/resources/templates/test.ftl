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
                <#if question.type == "SINGLE">
                    <#list allAnswers as answer><#if answer.question.id == question.id>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="radioBtn${question.id}" id="radioBtn${question.id}${answer.id}"  >
                        <label class="form-check-label" for="radioBtn${question.id}${answer.id}">
                            ${answer.text}
                        </label>
                    </div>
                    </#if>
                    </#list>
                </#if>
                <#if question.type == "MULTI">
                <div class="form-check">
                        <#list allAnswers as answer>
                            <#if answer.question.id == question.id>
                    <div>
                        <input class="form-check-input" type="checkbox" id="check${question.id}${answer.id}">
                        <label class="form-check-label" for="check${question.id}${answer.id}">
                            ${answer.text}
                        </label>
                    </div>
                            </#if>
                        </#list>
                </div>

                </#if>
                <#if question.type == "WORD" >
                    <#list allAnswers as answer>
                        <#if answer.question.id == question.id>
                    <div class="form-inline">
                        <input type="text" class="form-control" id="textArea${question.id}" placeholder="Example input">
                        <button type="button" class="btn btn-primary ml-2">Send</button>
                    </div>
                        </#if>
                    </#list>
                </#if>
            </div>
        </div>
    <br>
    </#list>

</@c.page>

