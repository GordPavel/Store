function toggleAction() {
// Switches the Icon
	$(this).children('i').toggleClass('fa-pencil');
	// Switches the forms
	$('.form').animate({
		height: "toggle",
		'padding-top': 'toggle',
		'padding-bottom': 'toggle',
		opacity: "toggle"
	}, "slow");
}

function changeHrefs() {
	const hrefRegex = /(?<base>\/pages\/.+\.html\?)(?<login>login|registration)/;
	$('.loginHref').each((index, href) => {
		href = $(href);
		const matcher = hrefRegex.exec(href.attr('href'));
		let action = matcher.groups.login === 'login' ? 'registration' : 'login';
		href.attr('href', matcher.groups.base.concat(action));
	});
}

$(document).ready(function () {

	if (urlParams.has('registration')) {
		$('#loginForm').css('display', 'none');
		$('#registrationForm').css('display', 'block');
		changeHrefs();
	}
	const $tooltip = $('#tooltip'), $toggle = $('.toggle');
	$toggle.hover(function () {
		$tooltip.fadeIn('slow');
	}, function () {
		$tooltip.fadeOut('slow');
	});

	$('div.cta > a.loginHref').click(function (event) {
		let buttonHref = event.currentTarget.href;
		let registrationEmail = $('#registrationEmail').val();
		if (buttonHref.endsWith('registration') && (registrationEmail === undefined || registrationEmail === '')) {
			$('#enterEmail').css('display', 'block');
			event.preventDefault();
		} else {
			event.preventDefault();
			window.location.href = `${buttonHref}&email=${registrationEmail}`;
		}
	});

	$toggle.click(function () {
		changeHrefs();
		toggleAction.call(this);
	});
});