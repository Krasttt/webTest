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
                <form id="form1">
                    <#list allAnswers as answer><#if answer.question.id == question.id>
                    <div class="form-check mb-2">
                        <input class="form-check-input" type="radio" name="radioBtn${question.id}"
                               id="radioBtn${question.id}${answer.id}" required>
                        <input type="text" class="form-control col-4" id="textArea${question.id}${answer.id}"
                               placeholder="${answer.text}">
                    </div>
                            <script>
                                var answer = {
                                    id:${answer.id},
                                    text: "${answer.text}",
                                    correct:${answer.correct?c}};
                                answers${question.id}.push(answer)
                                if (${answer.correct?c}){
                                    $("#radioBtn${question.id}"+ answer.id)[0].checked = true;
                                }
                            </script>
                    </#if>
                    </#list>
                    <button type="button" class="btn btn-primary ml-2" id="btn${question.id}">Edit</button>
                </form>
                <script>
                    $(document).ready(function () {
                        $("#btn${question.id}").click(function () {
                            for (var i = 0; i < answers${question.id}.length; i++) {
                                answers${question.id}[i].text = $("#textArea${question.id}" + answers${question.id}[i].id.toString()).val();
                                $("#textArea${question.id}" + answers${question.id}[i].id.toString()).val('');
                                if ($("#radioBtn${question.id}" + answers${question.id}[i].id.toString()).is(":checked")) {
                                    answers${question.id}[i].correct = true;
                                } else {
                                    answers${question.id}[i].correct = false;
                                }
                            }
                            $.ajax({
                                type:"POST",
                                contentType:"application/json",
                                url:"/editTest/${question.id}",
                                data:JSON.stringify(answers${question.id}),
                                dataType:"json",
                                success: function(data){
                                    for (var i = 0;i<data.length;i++){
                                        $("#textArea"+data[i].question.id +""+ data[i].id)[0].placeholder =  data[i].text;
                                    }
                                    alert("SUCCESS")
                                }
                            });
                        });
                    });
                </script>

                </#if>
                <#if question.type == "MULTI">
                <div class="form-check ">
                    <script>
                        var answers${question.id} = [];
                    </script>
                        <#list allAnswers as answer>
                            <#if answer.question.id == question.id>
                    <div class="mb-2">
                        <input class="form-check-input" type="checkbox" id="check${question.id}${answer.id}">
                        <input type="text" class="form-control col-4" id="textArea${question.id}${answer.id}"
                               placeholder="${answer.text}">
                    </div>
                             <script>
                                 var answer = {
                                     id:${answer.id},
                                     text: "${answer.text}",
                                     correct:${answer.correct?c}};
                                 answers${question.id}.push(answer)
                                 if (${answer.correct?c}){
                                     $("#check"+${question.id} +""+ answer.id)[0].checked = true;
                                 }
                             </script>
                            </#if>
                        </#list>
                    <script>
                        $(document).ready(function () {
                            $("#btn${question.id}").click(function () {
                                for (var i = 0; i < answers${question.id}.length; i++) {
                                    answers${question.id}[i].text = $("#textArea${question.id}" + answers${question.id}[i].id.toString()).val();
                                    $("#textArea${question.id}" + answers${question.id}[i].id.toString()).val('');
                                    if ($("#check${question.id}" + answers${question.id}[i].id.toString()).is(":checked")) {
                                        answers${question.id}[i].correct = true;
                                    } else {
                                        answers${question.id}[i].correct = false;
                                    }
                                }
                                $.ajax({
                                    type:"POST",
                                    contentType:"application/json",
                                    url:"/editTest/${question.id}",
                                    data:JSON.stringify(answers${question.id}),
                                    dataType:"json",
                                    success: function(data){
                                        for (var i = 0;i<data.length;i++){
                                            $("#textArea"+data[i].question.id +""+ data[i].id)[0].placeholder =  data[i].text;
                                        }
                                        alert("SUCCESS")
                                    }
                                });
                            });
                        });
                    </script>
                    <button type="button" class="btn btn-primary ml-2" id="btn${question.id}">Edit</button>
                </div>
                </#if>
                <#if question.type == "WORD" >
                <script>
                    var answers${question.id} = [];
                </script>
                    <#list allAnswers as answer>
                        <#if answer.question.id == question.id>
                    <div class="form-inline">
                        <input type="text" class="form-control" id="textArea${question.id}"
                               placeholder="${answer.text}">
                        <button type="button" class="btn btn-primary ml-2" id="btn${question.id}">Edit</button>
                    </div>
                         <script>
                             var answer = {
                                 id:${answer.id},
                                 text: "${answer.text}",
                                 correct:${answer.correct?c}};
                             answers${question.id}.push(answer)
                         </script>
                        </#if>
                    </#list>
                 <script>
                     $(document).ready(function () {
                         $("#btn${question.id}").click(function () {
                             for (var i = 0; i < answers${question.id}.length; i++) {
                                 answers${question.id}[i].text = $("#textArea${question.id}").val();
                                 $("#textArea${question.id}").val('');
                             }
                             $.ajax({
                                 type:"POST",
                                 contentType:"application/json",
                                 url:"/editTest/${question.id}",
                                 data:JSON.stringify(answers${question.id}),
                                 dataType:"json",
                                 success: function(data){
                                     for (var i = 0;i<data.length;i++){
                                         $("#textArea"+data[i].question.id)[0].placeholder =  data[i].text;
                                     }
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
</@c.page>