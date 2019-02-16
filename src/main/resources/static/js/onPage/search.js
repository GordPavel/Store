class Filter {
	constructor(searchString, ids, exceptIds, categories, page, minPrice, maxPrice) {
		this.searchString = searchString;
		this.ids = ids;
		this.exceptIds = exceptIds;
		this.categories = categories;
		this.page = page;
		this.size = 9;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}
}

function updateFilter(filter) {
	hashParams.set('filter', JSON.stringify(filter));
	window.location.hash = hashParams.toString();
}

function check(_, node) {
	const $tree = $('#tree');
	if (!!node.nodes) node.nodes.forEach(child => $tree.treeview('checkNode', child.nodeId));
	const parent = $tree.treeview('getParent', node);
	if (!parent.nodes) return;
	const parentHasUnchecked = parent.nodes.some(child => !child.state.checked);
	if (parentHasUnchecked) $tree.treeview('uncheckNode', parent);
	else $tree.treeview('checkNode', parent);

	let filter = JSON.parse(hashParams.get('filter'));
	filter.categories = $tree.treeview('getEnabled').filter(node => node.state.checked).map(node => node.id);
	updateFilter(filter);
}

function uncheck(_, node) {
	const $tree = $('#tree');
	const parent = $tree.treeview('getParent', node);
	if (parent !== $tree) $tree.treeview('uncheckNode', parent);

	let filter = JSON.parse(hashParams.get('filter'));
	filter.categories = $tree.treeview('getEnabled').filter(node => node.state.checked).map(node => node.id);
	updateFilter(filter);
}

let filter = new Filter(urlParams.get('searchQuery'), [], [], [], 0);
if (hashParams.has('filter')) {
	filter = JSON.parse(hashParams.get('filter'));
	filter.searchString = urlParams.get('searchQuery');
	updateFilter(filter);
}

function convertToTreeNode(categories) {
	return categories.map(function (category) {
		let node = {
			id: category.id,
			text: category.name,
			selectable: true,
			state: {
				checked: filter.categories.contains(category.id),
				expanded: true,
				selected: true
			},
			backColor: "#84C639",
		};
		if (!!category.subCategories && category.subCategories.length !== 0) node.nodes = convertToTreeNode(category.subCategories);
		return node;
	})
}

$(document).ready(function () {

	$('button#searchProducts').click(function () {
		const filter = JSON.parse(hashParams.get('filter'));
		filter.page = 0;
		updateFilter(filter);
		window.location.reload();
	});

	function updateProducts(filter) {
		$.ajax("/rest/products", {
			data: filter,
			dataType: 'json',
			contentType: 'application/json; charset=UTF-8',
			success: function (response) {
				const htmlRows = $('#productsRow').render(response.page.content.toMatrix(3));
				$('#productChartRows').html(htmlRows);
			}
		});
	}

	function loadProducts(filter) {
		$.ajax("/rest/products", {
			data: filter,
			dataType: 'json',
			contentType: 'application/json; charset=UTF-8',
			success: function (response) {
				filter = JSON.parse(hashParams.get('filter'));
				filter.minPrice = filter.minPrice || response.minCost;
				filter.maxPrice = filter.maxPrice || response.maxCost;
				updateFilter(filter);

				const $slider = $("#slider");
				$slider.html('');
				$slider.ionRangeSlider({
					type: "double",
					min: filter.minPricet,
					max: filter.maxPrice,
					from: response.minCost,
					to: response.maxCost,
					prefix: "₽",
					skin: 'flat',
					grid: true,
					onChange: function (data) {
						filter = JSON.parse(hashParams.get('filter'));
						filter.minPrice = data.from;
						filter.maxPrice = data.to;
						updateFilter(filter);
					}
				});

				const $pagination = $('#pagination');
				$pagination.html('');
				$pagination.bootpag({
					total: response.page.totalPages,
					maxVisible: 10,
					page: filter.page + 1,
					leaps: true,
				}).on('page', function (_, page) {
					filter = JSON.parse(hashParams.get('filter'));
					filter.page = page - 1;
					updateFilter(filter);
					updateProducts(filter);
				});
				const htmlRows = $('#productsRow').render(response.page.content.toMatrix(3));
				$('#productChartRows').html(htmlRows);
			}
		});
	}

	$.ajax("/rest/tree", {
		success: function (response) {
			if (!hashParams.has('filter')) {
				$.ajax("/rest/categories", {
					async: false
				}).done(function (data) {
					filter.categories = data.map(cat => cat.id);
				});
			}

			updateFilter(filter);
			const tree = convertToTreeNode(response);
			const $tree = $('#tree');
			$tree.treeview({
				data: tree,
				highlightSelected: false,
				showCheckbox: true,
				expandIcon: 'glyphicon glyphicon-chevron-right',
				collapseIcon: 'glyphicon glyphicon-chevron-down'
			});
			$tree.on('nodeChecked', check);
			$tree.on('nodeUnchecked', uncheck);
			loadProducts(filter);
		}
	});
});