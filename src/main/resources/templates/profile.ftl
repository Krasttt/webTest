<#import "parts/common.ftl" as c>
<#include "parts/security.ftl">
<@c.page>
<div class="card mt-3">
    <h3 class="card-header">${user.firstName} ${user.surName?if_exists}</h3>
    <div class="card-body mb-10 row">
        <#if userId == user.id>
            <div class="col-6">
                <form method="post" action="/user/editInfo">
                    <div class="form-group row"><label class="col-4"> First Name : </label>
                        <div>
                    <input class="form-control" type="text" name="firstName" id="firsName"
                           placeholder="First name..."/ >
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                </div>
            </div>
                    <div class="form-group row"><label class="col-4"> Surname : </label>
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
            <div class="col-6">
                <form method="post" action="/user/editPassword">
                    <div class="form-group row"><label class="col-4"> Current password : </label>
                        <div>
                            <input class="form-control" type="password" name="curPassword" id="curPassword"
                                   placeholder="Current password..." required/ >
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        </div>
                    </div>
                    <div class="form-group row"><label class="col-4"> New password : </label>
                        <div>
                            <input class="form-control" type="password" name="newPassword" id="newPassword"
                                   placeholder="New password..."required/ >
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        </div>
                    </div>
                    <div class="form-group row"><label class="col-4"> Repeat password : </label>
                        <div>
                            <input class="form-control" type="password" name="repPassword" id="repPassword"
                                   placeholder="Repeat password..."required/ >
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        </div>
                    </div>
                    <button id="editPassword" class="btn btn-outline-success my-2 my-sm-0" type="submit">
                        Change password
                    </button>
                    <#if error?has_content><h3 class="text-danger">${error}</h3></#if>
                </form>
            </div>
        <#else>
        <div>
            <div><h4>First Name : ${user.firstName}</h4></div>
            <div><h4>Surname : ${user.surName}</h4></div>
            <div><h4>Role : ${user.role}</h4></div>
            <div><a href="/setAdmin/${user.id}" class="btn btn-warning">SetAdmin</a></div>
        </div>
        </#if>

    </div>
</div>
    <#if results?has_content>
<table class="mt-3 table table-bordered table-hover">

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