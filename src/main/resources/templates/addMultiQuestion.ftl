<#import "parts/common.ftl" as c >
<@c.page>
<br>
<form method="post" action="/addMultiQuestion">
    <div class="card">
        <div class="card-header">
            <h2>Вопрос :</h2>
            <input type="text" class="form-control " name="textQuestion" id="textQuestion" placeholder="Question...." required>
        </div>

        <div class="card-body">
            <div class="form-inline  mb-2">
                <h5 class="col-2">Answer #1  </h5>
                <input class="form-control col-4 ml-2" name="answer1" id="answer1" type="text" placeholder="Default input" required>
                <input class="form-check-input" type="checkbox"  id="check1" name="check1" value="true" >
            </div>
            <div class="form-inline  mb-2">
                <h5 class="col-2">Answer #2  </h5>
                <input class="form-control col-4 ml-2" name="answer2" id="answer2" type="text" placeholder="Default input" required>
                <input class="form-check-input" type="checkbox"  id="check2"  name="check2" value="true">
            </div>
            <div class="form-inline  mb-2">
                <h5 class="col-2">Answer #3  </h5>
                <input class="form-control col-4 ml-2" name="answer3" id="answer3" type="text" placeholder="Default input" required>
                <input class="form-check-input" type="checkbox"  id="check3"  name="check3" value="true">
            </div>
            <div class="form-inline  mb-2">
                <h5 class="col-2">Answer #4  </h5>
                <input class="form-control col-4 ml-2" name="answer4" id="answer4" type="text" placeholder="Default input" required>
                <input class="form-check-input" type="checkbox"  id="check4"  name="check4" value="true">
            </div>
        </div>
        <button class="btn btn-outline-success my-2 my-sm-0 " type="submit">
            Add question!
        </button>
        <input type="text" value="${id}" name="id" id="id" hidden>
    </div>
</form>
</@c.page>