jQuery('#gtreetable').gtreetable({
	cache: 1,
	language: 'ru',
	manyroots: true,
	selectLimit: -1,
	source: function (id) {
		return {
			type: 'GET',
			url: '/admin/categories',
			data: {'id': id},
			dataType: 'json',
			error: function (XMLHttpRequest) {
				alert(XMLHttpRequest.status + ': ' + XMLHttpRequest.responseText);
			}
		}
	}
});
$(document).ready(function () {

});