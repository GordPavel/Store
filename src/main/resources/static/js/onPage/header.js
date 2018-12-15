$(document).on("rolesLoaded", function () {
	$(document).ready(function () {
		if (roles.contains('ROLE_USER')) {
			$('.profile-button .anonymous').css('display', 'none');
			$('.profile-button .user').css('display', 'block');
			if (roles.contains('ROLE_ADMIN')) $('.profile-button .admin').css('display', 'block');
			else $('.profile-button .admin').css('display', 'none');
		} else {
			$('.profile-button .user').css('display', 'none');
			$('.profile-button .admin').css('display', 'none');
		}
	})
});