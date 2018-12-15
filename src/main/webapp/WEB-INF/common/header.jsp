<%@ page pageEncoding="UTF-8" %>
<script src="/js/onPage/header.js"></script>
<nav class="navbar navbar-default" role="navigation" style="margin-bottom: 0">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div class="w3ls_logo_products_left">
                <h1><a href="/" style="color: #84C639">ZooСамара</a></h1>
            </div>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" aria-expanded="false">
                    Профиль
                    <span class="glyphicon glyphicon-user"></span>
                    <span class="caret"></span>
                </a>
                <ul class="dropdown-menu dropdownhover-bottom profile-button" role="menu">
                    <li class="anonymous"><a href="/login?login">Войти</a></li>
                    <li class="anonymous"><a href="/login?registration">Загеристрироваться</a></li>
                    <li class="user"><a href="/wishList">Список желаний</a></li>
                    <li class="user"><a href="/logout">Выйти</a></li>
                    <li class="admin"><a href="/admin">Панель администратора</a></li>
                </ul>
            </li>
        </ul>
        <form action="/search" method="get" class="navbar-form navbar-right searchInput" role="search">
            <div class="form-group">
                <input type="text" name="searchQuery" class="form-control" placeholder="Поиск">
            </div>
            <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
        </form>
    </div><!-- /.container-fluid -->
</nav>