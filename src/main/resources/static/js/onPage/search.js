Array.prototype.toMatrix = function (width) {
	return this.reduce(function (rows, key, index) {
		return (index % width === 0 ? rows.push([key]) : rows[rows.length - 1].push(key))
			&& rows;
	}, []);
};
Array.prototype.max = function () {
	return Math.max.apply(Math, this);
};
Array.prototype.min = function () {
	return Math.min.apply(Math, this);
};

$(document).ready(function () {
	$.ajax({
		url: '/rest/products',
		data: {
			sort: 'views,desc',
			search: '${searchQuery}'
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