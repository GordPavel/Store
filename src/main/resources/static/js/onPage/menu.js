$(document).ready(function () {
	$.ajax("/rest/categories", {
		success: function (response) {
			$("#menuItemsContainer").html($("#categoryTemplate").render(response));
			$(".dropdown").hover(
				function () {
					$('.dropdown-menu', this).stop(true, true).slideDown("fast");
					$(this).toggleClass('open');
				},
				function () {
					$('.dropdown-menu', this).stop(true, true).slideUp("fast");
					$(this).toggleClass('open');
				}
			);
		}
	});
});