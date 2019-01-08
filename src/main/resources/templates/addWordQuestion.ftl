<#import "parts/common.ftl" as c >
<@c.page>
<br>
<form method="post" action="/addWordQuestion">
    <div class="card">
        <div class="card-header">
            <h2>Вопрос :</h2>
            <input type="text" minlength="5" maxlength="255" class="form-control ${(lengthError??)?string('is-invalid', '')}"
                   name="textQuestion" id="textQuestion" placeholder="Question...." required>
            <#if lengthError??>
                <div class="invalid-feedback">
                    ${lengthError}
                </div>
            </#if>
        </div>
        <div class="card-body">
            <div class="form-inline  mb-2">
                <h5>Answer </h5>
                <input class="form-control col-4 ml-2" name="answer" id="answer" type="text"
                       placeholder="Default input" <#if answer?has_content> value="${answer}" </#if>
                       required>
            </div>
        </div>
        <button class="btn btn-outline-success my-2 my-sm-0 " type="submit">
            Add question!
        </button>
        <input type="text" value="${id}" name="id" id="id" hidden>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>

</form>
</@c.page>