$(document).ready(function () {

	if (urlParams.has('registration')) {
		$('#loginForm').css('display', 'none');
		$('#registrationForm').css('display', 'block');
	}

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