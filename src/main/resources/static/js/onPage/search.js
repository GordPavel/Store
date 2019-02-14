function convertToTreeNode(categories) {
	return categories.map(function (category) {
		let node = {
			text: category.name,
			selectable: true,
			state: {
				checked: true,
				expanded: true,
				selected: true
			},
			backColor: "#84C639",
		};
		if (!!category.subCategories && category.subCategories.length !== 0) node.nodes = convertToTreeNode(category.subCategories);
		return node;
	})
}

class Filter {
	constructor(searchString, ids, exceptIds, categories, properties) {
		this.searchString = searchString;
		this.ids = ids;
		this.exceptIds = exceptIds;
		this.categories = categories;
		this.properties = properties;
	}
}

function check(_, node) {
	const $tree = $('#tree');
	if (!!node.nodes) node.nodes.forEach(child => $tree.treeview('checkNode', child.nodeId));
	const parent = $tree.treeview('getParent', node);
	if (!parent.nodes) return;
	const parentHasUnchecked = parent.nodes.some(child => !child.state.checked);
	if (parentHasUnchecked) $tree.treeview('uncheckNode', parent);
	else $tree.treeview('checkNode', parent);

	const filter = JSON.parse(hashParams.get('filter'));
	const selected = $tree.treeview('getSelected').map(node => node.nodeId);
	console.log(JSON.stringify(selected));
	const a = 0;
}

function uncheck(_, node) {
	const $tree = $('#tree');
	const parent = $tree.treeview('getParent', node);
	if (parent !== $tree) $tree.treeview('uncheckNode', parent);

	const filter = JSON.parse(hashParams.get('filter'));
	const selected = $tree.treeview('getSelected').map(node => node.nodeId);
	console.log(JSON.stringify(selected));
	const a = 0;
}

if (!hashParams.has('filter')) {
	const filter = new Filter(),
		stringify = JSON.stringify(filter);
	hashParams.append('filter', stringify);
	window.location.hash = hashParams.toString();
}

$(document).ready(function () {
	$("#slider").ionRangeSlider({
		type: "double",
		min: 0,
		max: 1000,
		from: 200,
		to: 800,
		prefix: "₽",
		skin: 'flat',
		grid: true
	});
	$.ajax("/rest/tree", {
		success: function (response) {
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
		}
	});
});