$(document).ready(function () {
	if (urlParams.has("processing")) $('#validateEmail').css('display', 'block');
	else $('#successRegistration').css('display', 'block');
});