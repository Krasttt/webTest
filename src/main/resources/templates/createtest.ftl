<#import "parts/common.ftl" as c>
<@c.page>
<h1>Welcome to Tester, User</h1>
<fieldset>
    <form method="post">
        <div class=" form-inline ">
            <label class="col-4" for="name">Name : </label>
            <input type="name" name="name" class="form-control col-8" id="name" placeholder="Name" required>
        </div>
        <br>
        <div class=" form-inline ">
            <label class="col-4" for="description">Description : </label>
            <input type="text" name="description" class="form-control col-8" id="description" placeholder="Description"
                   required>
        </div>
        <br>
        <div class=" form-inline">
            <label class="col-4" for="time">Time to test : </label>
            <input type="number" min="10" max="60" name="duration" class="form-control col-3" id="duration"
                   placeholder="20min" required>
        </div>
        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Create</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</fieldset>
</@c.page>
