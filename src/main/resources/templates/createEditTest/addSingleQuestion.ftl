<#import "../parts/common.ftl" as c >
<@c.page>
<br>
<form method="post" action="/addSingleQuestion">
         <div class="card">
             <div class="card-header">
                 <h2>Question :</h2>
                 <input type="text" minlength="5" maxlength="255" class="form-control ${(lengthError??)?string('is-invalid', '')}"
                        name="textQuestion" id="textQuestion" placeholder="Question...." required>
                 <#if lengthError??>
                <div class="invalid-feedback">
                    ${lengthError}
                </div>
                 </#if>
             </div>
             <div class="card-body">
                 <div class="form-inline  mb-2 ">
                     <h5 class="col-2 text-success">Correct answer  </h5>
                     <input class=" form-control col-4 ml-2" name="corAnswer" id="corAnswer" type="text"
                            placeholder="Default input"<#if corAnswer?has_content> value="${corAnswer}" </#if> required>
                 </div>
                 <div class="form-inline  mb-2">
                     <h5 class="col-2 text-danger">Other answer  </h5>
                     <input class="form-control col-4 ml-2" name="answer2" id="answer2" type="text"
                            placeholder="Default input"<#if answer2?has_content> value="${answer2}" </#if> required>
                 </div>
                 <div class="form-inline  mb-2">
                     <h5 class="col-2 text-danger">Other answer   </h5>
                     <input class="form-control col-4 ml-2" name="answer3" id="answer3" type="text"
                            placeholder="Default input" <#if answer3?has_content> value="${answer3}" </#if>required>
                 </div>
                 <div class="form-inline  mb-2" >
                     <h5 class="col-2 text-danger">Other answer   </h5>
                     <input class="form-control col-4 ml-2" name="answer4" id="answer4" type="text"
                            placeholder="Default input"<#if answer4?has_content> value="${answer4}" </#if> required>
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