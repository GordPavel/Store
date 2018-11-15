const urlParams = new URLSearchParams(window.location.search);

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