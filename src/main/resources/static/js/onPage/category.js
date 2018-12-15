$(document).ready(function () {
	const categoryName = urlParams.get('name');
	$('#categoryName').html(categoryName);
	document.title = categoryName;
	$.ajax({
		url: '/rest/products',
		data: {
			sort: 'views,desc',
			category: categoryName
		},
		success: function (response) {
			const htmlRows = $('#productsRow').render(response.toMatrix(3));
			$('#productChartRows').html(htmlRows);
		}
	})
});