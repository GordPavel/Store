<%@ page pageEncoding="UTF-8" %>
<div class="header">
    <div class="container">
        <div class="w3ls_logo_products_left">
            <h1><a href="/" style="color: #84C639">ZooСамара</a></h1>
        </div>
        <div class="w3ls_logo_products_left1">
            <div class="w3l_search">
                <form action="/search" method="post">
                    <input type="text" name="Product" value="Поиск" onfocus="this.value = '';"
                           onblur="if (this.value == '') {this.value = 'Поиск   ';}" required="">
                    <input type="submit" value=" ">
                </form>
            </div>
        </div>
        <div class="w3l_header_right">
            <ul>
                <li class="dropdown profile_details_drop">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                        Профиль
                        <i class="fa fa-user" aria-hidden="true"></i>
                        <span class="caret"></span>
                    </a>
                    <div class="mega-dropdown-menu">
                        <div class="w3ls_vegetables">
                            <ul class="dropdown-menu drp-mnu">
                                <li><a href="/login">Войти</a></li>
                                <li><a href="/login">Загеристрироваться</a></li>
                            </ul>
                        </div>
                    </div>
                </li>
            </ul>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
