<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/tests">TESTer</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ">
            <li class="nav-item">
                <a class="nav-link" href="/user">Profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/tests">Tests </a>
            </li>
        </ul>
        <form method="get" action="/tests" class="form-inline">
            <input class="form-control mr-sm-2" type="text" name="filter" value="${filter?ifExists}"
                   placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>
        <form action="/logout" method="post">
            <button class="btn btn-outline-success ml-5 my-2 my-sm-0 " type="submit">Sign Out</button>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        </form>
    </div>
</nav>
