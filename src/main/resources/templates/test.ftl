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
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="exampleRadios${question.id}" id="Radios1${question.id}"  >
                        <label class="form-check-label" for="Radios1${question.id}">
                            Answer #1
                        </label>
                    </div>
                     <div class="form-check">
                         <input class="form-check-input" type="radio" name="exampleRadios${question.id}" id="Radios2${question.id}" >
                         <label class="form-check-label" for="Radios2${question.id}">
                             Answer #2
                         </label>
                     </div>
                     <div class="form-check">
                         <input class="form-check-input" type="radio" name="exampleRadios${question.id}" id="Radios3${question.id}" >
                         <label class="form-check-label" for="Radios3${question.id}">
                             Answer #3
                         </label>
                     </div>
                     <div class="form-check">
                         <input class="form-check-input" type="radio" name="exampleRadios${question.id}" id="Radios4${question.id}">
                         <label class="form-check-label" for="Radios4${question.id}">
                             Answer #4
                         </label>
                     </div>
                </#if>
                <#if question.type == "MULTI">
                <div class="form-check">
                    <div>
                        <input class="form-check-input" type="checkbox" id="Check1${question.id}">
                        <label class="form-check-label" for="Check1${question.id}">
                            Answer #1
                        </label>
                    </div>
                    <div>
                        <input class="form-check-input" type="checkbox" id="Check2${question.id}">
                        <label class="form-check-label" for="Check2${question.id}">
                            Answer #2
                        </label>
                    </div>
                    <div>
                        <input class="form-check-input" type="checkbox"  id="Check3${question.id}">
                        <label class="form-check-label" for="Check3${question.id}">
                            Answer #3
                        </label>
                    </div>
                    <div>
                        <input class="form-check-input" type="checkbox"  id="Check4${question.id}">
                        <label class="form-check-label" for="Check4${question.id}">
                            Answer #4
                        </label>
                    </div>
                </div>

                </#if>
                <#if question.type == "WORD" >
                    <div class="form-inline">
                        <input type="text" class="form-control" id="textArea${question.id}" placeholder="Example input">
                        <button type="button" class="btn btn-primary mb-2">Send</button>
                    </div>
                </#if>
            </div>
        </div>
    <br>
    </#list>

</@c.page>