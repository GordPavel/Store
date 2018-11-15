$(document).ready(function () {
	$.ajax({
		url: '/rest/products',
		data: {
			sort: 'views,desc',
			search: urlParams.get('query')
		},
		success: function (response) {
			const htmlRows = $('#productsRow').render(response.slice(0, 12).toMatrix(3));
			$('#productChartRows').html(htmlRows);
			let prices = response.map(product => product.price);
			$("#slider").ionRangeSlider({
				type: "double",
				grid: true,
				min: prices.min(),
				max: prices.max(),
				from: prices.min(),
				to: prices.max(),
				prefix: "₽"
			});
		}
	});
	$.ajax("/rest/categories", {
		success: function (response) {
			$("#tree").html($("#categoriesTreeTemplate").render(response));
			$("ul.checktree").checktree();
		}
	});
});