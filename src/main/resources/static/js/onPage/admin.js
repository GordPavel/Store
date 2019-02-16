function hasClass(elem, className) {
	return new RegExp("(^|\\s)" + className + "(\\s|$)").test(elem.className)
}

let bf = brutusin["json-forms"].create({
	"$schema": "http://json-schema.org/draft-03/schema#",
	"type": "object",
	"properties": {
		"species": {
			"title": "Species supported",
			"type": "string",
			"enum": [
				"Категория 1",
				"Категория 2",
				"Категория 3"
			],
			"required": true
		}, "prop1": {
			"type": "string",
			"title": "A short string",
			"description": "Enter the name of cat"
		},
		"subspecies": {
			"dependsOn": [
				"species"
			]
		}
	}
});
let bfProduct = brutusin["json-forms"].create({
	"$schema": "http://json-schema.org/draft-03/schema#",
	"type": "object",
	"properties": {
		"Название": {
			"type": "string",
			"required": true
		},
		"Категория": {
			"type": "string",
			"required": true,
			"enum": [
				"Категория 1",
				"Категория 2"
			]
		},
		"Цена": {
			"type": "integer",
			"minimum": 0
		},
		"Свойства": {
			"type": "array",
			"uniqueItems": true,
			"items": {
				"description": "Query item",
				"type": "object",
				"properties": {
					"Название": {
						"type": "string",
						"required": true
					},
					"Значение": {
						"type": "string",
						"required": true
					}
				}
			}
		}
	}
});

$(document).ready(function () {

	function push_all(arr1, arr2) {
		/// Push all object of second array into first.
		for (item of arr2) {
			arr1.push(item);
		}
		return arr1;
	}

	function getCatName(cat) {
		// Get category name, and all nestecd categories.
		let names = [];
		names.push([cat.category.name, cat.name]);
		if (!!cat.subCategories && cat.subCategories.length > 0) {
			for (subCat of cat.subCategories) {
				names = push_all(names, getCatName(subCat));
			}
		}
		return names
	}

	function getCatData(j_data) {
		// Function to get all categories from string data.
		let all_cats = [];
		for (obj of j_data) {
			all_cats = push_all(all_cats, getCatName(obj))
		}
		all_cats.sort();
		all_cats.filter((item, index, self) => {
			self.indexOf(item[0]) === index
		});
		return all_cats;
	}

	const cat_const = ({cat_name}) => `<div class="categoryBIB">${cat_name[0]}</div> <ul class="hr"> <li> <div class="product">${cat_name[1]} <input type="checkbox"/> </div> </li > </ul>`;

	class Filter {
		constructor(searchString, ids, exceptIds, categories, page, minPrice, maxPrice) {
			this.searchString = searchString;
			this.ids = ids;
			this.exceptIds = exceptIds;
			this.categories = categories;
			this.page = page;
			this.size = 900;
			this.minPrice = minPrice;
			this.maxPrice = maxPrice;
		}
	}

	$(function () {
		let mas = [];
		$.ajax("/rest/products", {
			data: new Filter('Продукт', [], [], [], 1, undefined, undefined),
			dataType: 'json',
			contentType: 'application/json; charset=UTF-8',
			success: function (response) {
				mas = getCatData(response.page.content);
				for (item of mas) {
					$(".category").append(cat_const({cat_name: item}))
				}
			}
		});
		// $.ajax({
		// 	method: "GET",
		// 	url: "/rest/products",
		// 	data: new Filter('Продукт',[],[],[],1,undefined,undefined),
		// 	dataType: 'json',
		// 	contentType: 'application/json; charset=UTF-8'
		// }).done((response) => {
		// 	mas = getCatData(response.page.content);
		// 	for (item of mas) {
		// 		$(".category").append(cat_const({cat_name: item}))
		// 	}
		// });
		console.log(mas);
	});

	let $myModal = $('#myModal');

	$('.Expand').click(function (event) {
		event = event || window.event;
		var clickedElem = event.target || event.srcElement;

		if (!hasClass(clickedElem, 'Expand')) {
			return // клик не там
		}

		// Node, на который кликнули
		var node = clickedElem.parentNode;
		if (hasClass(node, 'ExpandLeaf')) {
			return // клик на листе
		}

		var newClass = hasClass(node, 'ExpandOpen') ? 'ExpandClosed' : 'ExpandOpen';
		// заменить текущий класс на newClass
		var re = /(^|\s)(ExpandOpen|ExpandClosed)(\s|$)/;
		node.className = node.className.replace(re, '$1' + newClass + '$3');
	});

	function handleFileSelect() {
		var files; //
		$('input[type=file]').on('change', function () {
			files = this.files;
		});
	}

	function categoryTemplate(data) {
		return '<li class="Node ExpandLeaf IsLast"> <div class="Expand"></div> <input type="checkbox"/> <div class="Content">' + data.prop1 + '</div></li> ';
	}

	function productTemplate(data) {
		return '<li class="Node ExpandLeaf IsLast"> <div class="Expand"></div> <input type="checkbox"/> <div class="Content">' + data.Название + '</div></li> ';
	}

	let $11 = $("#11");

	function closeModal() {
		$('#addNewCat').html('');
		$myModal.hide();
	}

	$('#addButtonCategory').click(function (event) {
		$11.children(':last-child').removeClass('IsLast');
		$11.append(categoryTemplate(bf.getData()));
		closeModal();
	});
	$('#addButtonProduct').click(function (event) {
		$11.children(':last-child').removeClass('IsLast');
		$11.append(productTemplate(bfProduct.getData()));
		closeModal();
	});

	$('.modal-content span.close').click(closeModal);

	$('#apply').click(function () {
		const setMove = document.getElementById("setAction").value;
		if (setMove === "Add category") {
			$('#addButtonProduct').css("display", "none");
			$('#addButtonCategory').css("display", "block");
			$("#fileinput").css("display", "none");
			let categoryFormContainer = document.getElementById('addNewCat');
			bf.render(categoryFormContainer, {});
			$myModal.show();

		}

		if (setMove === "Delete") {
			// todo удалить по id
			var divsForRemove = $('input:checkbox:checked').parent();
			var names = divsForRemove.find('.Content').toArray().map(function (value) {
				return value.innerText
			});
			divsForRemove.remove();
		}
		if (setMove == "Change") {
			let categoryFormContainer = document.getElementById('addNewCat');
			bfProduct.render(categoryFormContainer, data);
			$myModal.show();
		}
		if (setMove === "Add product") {
			$('#addButtonProduct').css("display", "block");
			$('#addButtonCategory').css("display", "none");
			$("#fileinput").css("display", "block");
			let categoryFormContainer = document.getElementById('addNewCat');
			bfProduct.render(categoryFormContainer, {});
			$myModal.show();
			$('#addButtonProduct').click(handleFileSelect());
			let data = bfProduct.getData();
			$.ajax({
				type: 'POST',
				url: "/rest/categories",
				data: data,
				success: function (response) {
				},
				datatype: "json"
			});

		}
	});
});