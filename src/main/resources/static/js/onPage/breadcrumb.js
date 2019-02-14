$(document).ready(function () {
	const hrefRegex = /^http:\/\/.+\/(?<place>\w+)?\/?$/;
	const matcher = hrefRegex.exec(window.location.search.split('?')[0]);
	const place = matcher !== null ? (matcher.groups.place || 'index') : 'index';
	let breadcrumb;
	const $breadcrumb = $('#breadcrumb');
	switch (place) {
		case 'login':
			breadcrumb = '<li><span>|</span></li>\n'
				+ '<li>Профиль</li>';
			$breadcrumb.append(breadcrumb);
			break;
		case 'wishList':
			breadcrumb = '<li><span>|</span></li>\n'
				+ '<li>Личный кабинет</li>';
			$breadcrumb.append(breadcrumb);
			break;
		case 'search':
			breadcrumb = '<li><span>|</span></li>\n'
				+ '<li>Поиск</li>';
			$breadcrumb.append(breadcrumb);
			break;
		case 'admin':
			breadcrumb = '<li><span>|</span></li>\n'
				+ '<li>Кабинет администратора</li>';
			$breadcrumb.append(breadcrumb);
			break;
		case 'category':
			$.ajax('/rest/breadcrumb', {
				data: {
					itemId: urlParams.get('id'),
					isProduct: false
				},
				success: function (response) {
					breadcrumb = '<li><span>|</span></li>' + response
						.map(item => `<li><a href="/category?id=${item.id}&name=${item.name}">${item.name}</a></li>`)
						.join('<li><span>|</span></li>');
					$breadcrumb.append(breadcrumb);
					const $last = $('#breadcrumb li:last-child a');
					const innerText = $last.text();
					$last.parent().html(innerText);
				}
			});
			break;
		case 'product':
			$.ajax('/rest/breadcrumb', {
				data: {
					itemId: urlParams.get('id')
				},
				success: function (response) {
					const a = 0;
					$breadcrumb.append(breadcrumb);
					const $last = $('#breadcrumb li:last-child a');
					const innerText = $last.text();
					$last.parent().html(innerText);
				}
			});
			break;
	}
});