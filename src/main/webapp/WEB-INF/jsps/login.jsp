<%@ page pageEncoding="UTF-8" %>
<script src="/js/onPage/login.js"></script>
<div class="w3l_banner_nav_right" style="float: initial; width: 100%">
    <!-- login -->
    <div class="w3_login">
        <h3>Войти или зарегистрироваться</h3>
        <div class="w3_login_module">
            <div class="module form-module">
                <div class="toggle"><i class="fa fa-times fa-pencil"></i>
                    <div id="tooltip"
                         style="display: none"
                         class="tooltip">Ещё нет аккаунта?
                    </div>
                </div>
                <div id="loginForm" class="form">
                    <h2>Войти в аккаунт</h2>
                    <form action="/login" method="post">
                        <input type="text" name="username" placeholder="E-mail">
                        <input type="password" name="password" placeholder="Пароль">
                        <input type="submit" value="Войти">
                    </form>
                </div>
                <div id="registrationForm" class="form" style="display: none">
                    <h2>Зарегистрироваться</h2>
                    <form action="/registration" method="post">
                        <input type="text" name="Username" placeholder="E-mail" required>
                        <input type="password" name="Password" placeholder="Пароль">
                        <input type="password" name="PasswordAccept" placeholder="Подтверждение пароля">
                        <input type="submit" value="Зарегистрироваться">
                    </form>
                </div>
                <div class="cta">
                    <a class="loginHref" href="/pages/vkLogin.html"><img src="/images/vkontakte32.png"></a>
                    <a class="loginHref" href="/registration/odnoklassniki?login"><img
                            src="/images/odnoklassniki32.png"></a>
                </div>
            </div>
        </div>
    </div>
    <!-- //login -->
</div>