<#import "parts/common.ftl" as c>
<@c.page>
<h1>Welcome to Tester, User</h1>
<fieldset>
    <form method="post">
        <div class=" form-inline ">
            <label class="col-3" for="name">Name : </label>
            <input type="name" name="name" minlength="5" maxlength="255"
                   class="form-control col-7 ${(nameError??)?string('is-invalid', '')}"
                   id="name" placeholder="Name" required>
            <#if nameError??>
                <div class="invalid-feedback col-2">
                    ${nameError}
                </div>
            </#if>
        </div>
        <br>
        <div class=" form-inline ">
            <label class="col-3" for="description">Description : </label>
            <input type="text" minlength="10" maxlength="255"name="description"
                   class="form-control col-7 ${(descriptionError??)?string('is-invalid', '')}" id="description"
                   placeholder="Description" required>
            <#if descriptionError??>
                <div class="col-2 invalid-feedback">
                    ${descriptionError}
                </div>
            </#if>
        </div>
        <br>
        <div class=" form-inline">
            <label class="col-3" for="time">Time to test : </label>
            <input type="number" min="10" max="60" name="duration"
                   class="form-control col-3 ${(durationError??)?string('is-invalid', '')}" id="duration"
                   placeholder="20min" required>
                <#if durationError??>
                <div class="invalid-feedback col-2">
                    ${durationError}
                </div>
                </#if>
        </div>

        <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Create</button>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    </form>
</fieldset>
</@c.page>
