$(document).ready(function () {
	if (roles.contains('ROLE_USER')) {
		$('.anonymous').css('display', 'none');
		$('.user').css('display', 'block');
		if (roles.contains('ROLE_ADMIN')) $('.admin').css('display', 'block');
		else $('.admin').css('display', 'none');
	} else {
		$('.user').css('display', 'none');
		$('.admin').css('display', 'none');
	}
});