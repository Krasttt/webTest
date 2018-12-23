<#import "parts/common.ftl" as c>
<@c.page>
<div class="card">
    <h3 class="card-header">${user.firstName} ${user.surName?if_exists}</h3>
    <div class="card-body mb-10">
        <form  method="post" action="/user/edit">
        <div class="form-group row"><label class="col-2"> First Name : </label>
            <div>
                <input class="form-control" type="text" name="firstName" id="firsName"
                       placeholder="First name..."/ >
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </div>
        </div>
        <div class="form-group row"><label class="col-2"> Surname : </label>
            <div>
                <input class="form-control" type="text" name="surName" id="surName" placeholder="Surname..."/>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            </div>
        </div>
            <button id="editBtn" class="btn btn-outline-success my-2 my-sm-0" type="submit">
                Edit
            </button>
        </form>
    </div>
</div>
    <#if results?has_content>
<table class="table table-bordered table-hover">

    <thead class="thead-dark">
    <tr>
        <th>Data</th>
        <th scope="col" class="col-md-6">Name</th>
        <th scope="col" class="col-md-2">Amount questions</th>
        <th scope="col" class="col-md-2">Grade</th>
    </tr>
    </thead>

    <tbody>
    <#list results as result>

    <tr>
        <th scope="row">${result.startTest}</th>
        <td><a class="nav-link" href="/testResult/${result.id}">${result.name}</a></td>
        <td>${result.test.amountQuestions}</td>
        <td>${result.grade}</td>
    </tr>
    </#list>
    </tbody>
</table>
    <#else>
    <h5>Results not found.</h5>
    </#if>
</@c.page>