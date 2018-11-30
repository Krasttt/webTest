<#import "parts/common.ftl" as c >
<@c.page>
<br>
         <div class="card">
             <div class="card-header">
                 <h2>Вопрос какого типа вы хотите добавить ?</h2>
             </div>
             <div class="card-body row">
                 <div class="col-4">
                     <a href="/addSingleQuestion" class="btn btn-success ">Single</a>
                     <h5>For example :</h5><br>
                     <div class="form-check">
                         <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
                         <label class="form-check-label" for="defaultCheck1">
                             Default checkbox
                         </label>
                         <input class="form-check-input" type="checkbox" value="" id="defaultCheck2" >
                         <label class="form-check-label" for="defaultCheck2">
                             Disabled checkbox
                         </label>
                     </div>
                 </div>
                 <br>
                 <div class="col-4">
                     <a href="/addMultiQuestion" class="btn btn-success ">Multi</a>
                 </div>
                 <br>
                 <div class="col-4">
                     <a href="/addWordQuestion" class="btn btn-success ">Word</a>
                 </div>
                 <br>
             </div>
         </div>

</@c.page>