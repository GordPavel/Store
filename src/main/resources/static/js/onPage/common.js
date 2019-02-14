const urlParams = new URLSearchParams(window.location.search);
const hashParams = new URLSearchParams('?' + window.location.hash.substring(1));
let roles;
$.ajax("/rest/authorities", {
	success: function (response) {
		// noinspection JSUnresolvedVariable
		roles = response.map(a => a.authority);
		$(document).trigger("rolesLoaded");
	}
});
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
Array.prototype.contains = function (item) {
	return this.indexOf(item) !== -1
};