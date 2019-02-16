class Filter {
	constructor(searchString, ids, exceptIds, categories, page, minPrice, maxPrice) {
		this.searchString = searchString;
		this.ids = ids;
		this.exceptIds = exceptIds;
		this.categories = categories;
		this.page = page;
		this.size = 8;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}
}

$.ajax("/rest/products", {
	data: new Filter('Продукт', [], [], [], 1, undefined, undefined),
	dataType: 'json',
	contentType: 'application/json; charset=UTF-8',
	success: function (response) {
		const htmlRows = $('#productsRow').render(response.page.content.toMatrix(3));
		$('div#news').html(htmlRows);
	}
});