$(document).ready(function () {
	$.ajax("/rest/tree", {
		success: function (response) {
			const html = $('#category').render(response);
			$('#categories').html(html);
			setupDropDownHover();
		}
	});
});