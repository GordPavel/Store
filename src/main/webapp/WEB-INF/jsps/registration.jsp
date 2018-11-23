<%@ page pageEncoding="UTF-8" %>
<script src="/js/onPage/registration.js"></script>
<div class="w3l_banner_nav_right" style="float: initial; width: 100%">
    <!-- login -->
    <div class="w3_login">
        <%--<h3>Войти или зарегистрироваться</h3>--%>
        <div class="w3_login_module">
            <div class="module form-module">
                <div id="validateEmail" class="alert alert-info" role="alert" style="display: none">
                    На ваш e-mail отправлено письмо для подтверждения
                </div>
                <div id="successRegistration" class="alert alert-success" role="alert" style="display: none">
                    Регистрация успешно подветрждена!
                    <br>
                    <a href="/login?login">Войти в профиль</a>
                </div>
            </div>
        </div>
    </div>
    <!-- //login -->
</div>