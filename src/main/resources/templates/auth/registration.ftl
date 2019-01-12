<!DOCTYPE HTML>
<html>
<head>
    <meta charset=UTF-8"/>
    <title>TEST</title>

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

</head>
<body>
<div class="container">

    <form method="post">
        <div class="card mt-5">
            <h2 class="card-header text-center ">Welcom to Tester<br>REGISTRATION</h2>
            <div class="card-body ">
                <div class=" mt-3 ">
                    <form action="/registration" method="post">
                        <div class="form-group row"><label class="col-2"> User Name : </label>
                            <div>
                                <input class="form-control ${(usernameError??)?string('is-invalid', '')}
                                           ${(userError??)?string('is-invalid', '')}" type="text"
                                       name="username" placeholder="Username..."
                                <#if user?has_content> value="${user.username?if_exists}" </#if> required/>
                                <#if usernameError??>
                                     <div class="invalid-feedback">
                                        ${usernameError}
                                      </div>
                                </#if>
                                <#if userError??>
                                <div class="invalid-feedback">
                                    ${userError}
                                </div>
                                </#if>
                            </div>
                        </div>
                        <div class="form-group row"><label class="col-2"> Password: </label>
                            <div>
                                <input class="form-control ${(passwordError??)?string('is-invalid', '')}"
                                       type="password" name="password" placeholder="Password..." required/ >
                            <#if passwordError??>
                                <div class="invalid-feedback">
                                    ${passwordError}
                                </div>
                            </#if>
                            </div>
                        </div>
                        <div class="form-group row"><label class="col-2"> Confirm password: </label>
                            <div>
                                <input class="form-control ${(confirmError??)?string('is-invalid', '')}" type="password"
                                       name="confirmPassword" placeholder="Confirm password..." required/>
                                <#if confirmError??>
                                <div class="invalid-feedback">
                                    ${confirmError}
                                </div>
                                </#if>
                            </div>
                        </div>
                        <div class="form-group row"><label class="col-2"> First Name : </label>
                            <div>
                                <input class="form-control" type="text" name="firstName"
                                       placeholder="First name..." <#if user?has_content> value="${user.firstName?if_exists}" </#if>required/>
                            </div>
                        </div>
                        <div class="form-group row"><label class="col-2"> Surname : </label>
                            <div>
                                <input class="form-control" type="text" name="surName" placeholder="Surname..."
                                <#if user?has_content> value="${user.surName?if_exists}" </#if>/>
                            </div>
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    </form>
                </div>
            </div>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">
                Submit
            </button>
        </div>
    </form>
</div>

<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
        integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
        integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
        crossorigin="anonymous"></script>
</body>
</html>