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
                 <script>
                     var answers${question.id} = [];
                 </script>
                    <#list allAnswers as answer><#if answer.question.id == question.id>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="radioBtn${question.id}"
                               id="radioBtn${question.id}${answer.id}">
                        <label class="form-check-label" for="radioBtn${question.id}${answer.id}">
                            ${answer.text}
                        </label>
                    </div>
                    <script>
                        var answer = {
                            id:${answer.id},
                            text: "${answer.text}"
                        };
                        answers${question.id}.push(answer)
                    </script>
                    </#if>
                    </#list>
                <button type="button" class="btn btn-primary ml-2" id="btn${question.id}">Confirm</button>
                 <script>
                     $(document).ready(function () {
                         $("#btn${question.id}").click(function () {
                             var answers = [];
                             for (var i = 0; i < answers${question.id}.length; i++) {
                                 if ($("#radioBtn${question.id}" + answers${question.id}[i].id.toString()).is(":checked")) {
                                     answers.push(answers${question.id}[i]);
                                 }
                             }
                             $.ajax({
                                 type: "POST",
                                 contentType: "application/json",
                                 url: "/test/addAnswer/${question.id}/${result.id}",
                                 data: JSON.stringify(answers),
                                 dataType: "json",
                                 complete: function () {
                                     $('input[name=radioBtn${question.id}]').attr("disabled", true);
                                     $("#btn${question.id}").attr("disabled", true);
                                     alert("Success")
                                 }
                             });
                         });
                     });
                 </script>
                </#if>
                <#if question.type == "MULTI">
                <script>
                    var answers${question.id} = [];
                </script>
                <div class="form-check">
                        <#list allAnswers as answer>
                            <#if answer.question.id == question.id>
                    <div>
                        <input class="form-check-input" type="checkbox" id="check${question.id}${answer.id}"
                               name="check${question.id}">
                        <label class="form-check-label" for="check${question.id}${answer.id}">
                            ${answer.text}
                        </label>
                    </div>
                     <script>
                         var answer = {
                             id:${answer.id},
                             text: "${answer.text}"
                         };
                         answers${question.id}.push(answer)
                     </script>
                            </#if>
                        </#list>
                    <button type="button" class="btn btn-primary ml-2" id="btn${question.id}">Confirm</button>
                </div>
                <script>
                    $(document).ready(function () {
                        $("#btn${question.id}").click(function () {
                            var answers = [];
                            for (var i = 0; i < answers${question.id}.length; i++) {
                                if ($("#check${question.id}" + answers${question.id}[i].id.toString()).is(":checked")) {
                                    answers.push(answers${question.id}[i]);
                                }
                            }
                            $.ajax({
                                type: "POST",
                                contentType: "application/json",
                                url: "/test/addAnswer/${question.id}/${result.id}",
                                data: JSON.stringify(answers),
                                dataType: "json",
                                complete: function () {
                                    $('input[name=check${question.id}]').attr("disabled", true);
                                    $("#btn${question.id}").attr("disabled", true);
                                    alert("SUCCESS")
                                }
                            });
                        });
                    });
                </script>
                </#if>
                <#if question.type == "WORD" >
                 <script>
                     var answers${question.id} = [];
                 </script>
                    <#list allAnswers as answer>
                        <#if answer.question.id == question.id>
                    <div class="form-inline">
                        <input type="text" class="form-control" name="textArea${question.id}"
                               id="textArea${question.id}" placeholder="Example input">
                        <button type="button" id="btn${question.id}" class="btn btn-primary ml-2">Send</button>
                    </div>
                        <script>
                            var answer = {
                                id:${answer.id},
                                text: "${answer.text}"
                            };
                            answers${question.id}.push(answer)
                        </script>
                        </#if>
                    </#list>
                <script>
                    $(document).ready(function () {
                        $("#btn${question.id}").click(function () {
                            $.ajax({
                                type: "POST",
                                contentType: "application/json",
                                url: "/test/addAnswer/${question.id}/${result.id}",
                                data: JSON.stringify(answers${question.id}),
                                dataType: "json",
                                complete: function () {
                                    $('input[name=textArea${question.id}]').attr("disabled", true);
                                    $("#btn${question.id}").attr("disabled", true);
                                    alert("SUCCESS")
                                }
                            });
                        });
                    });
                </script>
                </#if>
            </div>
        </div>
    <br>
    </#list>
    <a href="/testResult/${test.id}/${result.id}" class="btn btn-success ">Complite</a>
</@c.page>

