$(document).ready(function () {
	$.ajax("/rest/categories", {
		success: function (response) {
			const html = $('#category').render(response);
			$('#categories').html(html);
			setupDropDownHover();
		}
	});
});