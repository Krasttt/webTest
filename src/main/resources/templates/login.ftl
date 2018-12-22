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
        <div class="card mt-5 ">
            <h2 class="card-header text-center ">Welcom to Tester<br>AUTHORIZATION</h2>
            <div class="card-body">

                <div class=" mt-3 ">
                    <form action="/login" method="post">
                        <div class="form-group row"><label class="col-2"> User Name : </label>
                            <div>
                                <input class="form-control" type="text" name="username" placeholder="Username..."/>
                            </div>
                        </div>
                        <div class="form-group row"><label class="col-2"> Password: </label>
                            <div>
                                <input class="form-control" type="password" name="password" placeholder="Password..."/>
                            </div>
                        </div>
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <div><button type="submit" class="btn btn-primary">Sign in</button></div>
                    </form>
                </div>
            </div>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit" formaction="/registration">
                Registration!
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
