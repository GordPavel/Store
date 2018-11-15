<%@ page pageEncoding="UTF-8" %>
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
                <div class="form">
                    <h2>Войти в аккаунт</h2>
                    <form action="#" method="post">
                        <input type="text" name="Username" placeholder="E-mail">
                        <input type="password" name="Password" placeholder="Пароль">
                        <input type="submit" value="Войти">
                    </form>
                </div>
                <div class="form">
                    <h2>Зарегистрироваться</h2>
                    <form action="#" method="post">
                        <input type="text" name="Username" placeholder="E-mail" required>
                        <input type="password" name="Password" placeholder="Пароль">
                        <input type="password" name="PasswordAccept" placeholder="Подтверждение пароля">
                        <input type="submit" value="Зарегистрироваться">
                    </form>
                </div>
                <div class="cta">
                    <a class="loginHref" href="/registration/vk?login"><img src="/images/vkontakte32.png"></a>
                    <a class="loginHref" href="/registration/odnoklassniki?login"><img
                            src="/images/odnoklassniki32.png"></a>
                </div>
            </div>
        </div>
        <script>
	        $(document).ready(function () {
		        const $tooltip = $('#tooltip'), $toggle = $('.toggle');
		        $toggle.hover(function () {
			        $tooltip.fadeIn('slow');
		        }, function () {
			        $tooltip.fadeOut('slow');
		        });
		        $toggle.click(function () {
			        const hrefRegex = /(?<base>\/registration\/\w+\?)(?<login>login|registration)/;
			        $('.loginHref').each((index, href) => {
				        href = $(href);
				        const matcher = hrefRegex.exec(href.attr('href'));
				        href.attr('href', matcher.groups.base.concat(matcher.groups.login === 'login' ? 'registration' : 'login'));
			        });

			        // Switches the Icon
			        $(this).children('i').toggleClass('fa-pencil');
			        // Switches the forms
			        $('.form').animate({
				        height: "toggle",
				        'padding-top': 'toggle',
				        'padding-bottom': 'toggle',
				        opacity: "toggle"
			        }, "slow");
		        });
			});
        </script>
    </div>
    <!-- //login -->
</div>