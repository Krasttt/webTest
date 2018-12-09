<#import "parts/common.ftl" as c >
<@c.page>
<br>
         <div class="card">
             <div class="card-header">
                 <h2>Вопрос какого типа вы хотите добавить ?</h2>
             </div>
             <div class="card-body row">
                 <div class="col-4">
                     <a href="/addSingleQuestion?id=${id}" class="btn btn-success ">Single</a>
                     <h5>For example :</h5><br>
                     <div class="form-check">
                         <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios1"  >
                         <label class="form-check-label" for="exampleRadios1">
                             Answer #1
                         </label>
                     </div>
                     <div class="form-check">
                         <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios2" >
                         <label class="form-check-label" for="exampleRadios2">
                             Answer #2
                         </label>
                     </div>
                     <div class="form-check">
                         <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios3" >
                         <label class="form-check-label" for="exampleRadios3">
                             Answer #3
                         </label>
                     </div>
                     <div class="form-check">
                         <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios4">
                         <label class="form-check-label" for="exampleRadios4">
                             Answer #4
                         </label>
                     </div>
                 </div>
                 <br>
                 <div class="col-4">
                     <a href="/addMultiQuestion?id=${id}" class="btn btn-success ">Multi</a>
                     <h5>For example :</h5><br>
                     <div class="form-check">
                         <div>
                             <input class="form-check-input" type="checkbox" id="defaultCheck1">
                             <label class="form-check-label" for="defaultCheck1">
                                 Answer #1
                             </label>
                         </div>
                         <div>
                             <input class="form-check-input" type="checkbox" id="defaultCheck2">
                             <label class="form-check-label" for="defaultCheck2">
                                 Answer #2
                             </label>
                         </div>
                         <div>
                             <input class="form-check-input" type="checkbox"  id="defaultCheck3">
                             <label class="form-check-label" for="defaultCheck3">
                                 Answer #3
                             </label>
                         </div>
                         <div>
                             <input class="form-check-input" type="checkbox"  id="defaultCheck4">
                             <label class="form-check-label" for="defaultCheck4">
                                 Answer #4
                             </label>
                         </div>
                     </div>
                 </div>
                 <br>
                 <div class="col-4">
                     <a href="/addWordQuestion?id=${id}" class="btn btn-success ">Word</a>
                     <h5>For example :</h5><br>
                     <div class="form-inline">
                         <input type="text" class="form-control" id="textArea" placeholder="Example input">
                         <button type="button" class="btn btn-primary  ml-2">Send</button>
                     </div>
                 </div>
                 <br>
             </div>
         </div>

</@c.page>