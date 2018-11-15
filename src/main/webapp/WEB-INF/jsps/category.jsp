<%@page pageEncoding="UTF-8" %>
<script>
	Array.prototype.toMatrix = function (width) {
		return this.reduce(function (rows, key, index) {
			return (index % width === 0 ? rows.push([key]) : rows[rows.length - 1].push(key))
				&& rows;
		}, []);
	};

	$(document).ready(function () {
		$.ajax({
			url: '/rest/products',
			data: {
				sort: 'views,desc',
				category: '${category}'
			},
			success: function (response) {
				const htmlRows = $('#productsRow').render(response.toMatrix(3));
				$('#productChartRows').html(htmlRows);
			}
		})
	});
</script>
<%@include file="templates/productsGrid.jsp" %>
<div class="w3l_banner_nav_right" style="float : initial; width: 100%">
    <div class="w3l_banner_nav_right_banner9 w3l_banner_nav_right_banner_pet">
        <h4>Your Pet Favourite Food</h4>
        <p>Sint occaecat cupidatat non proident</p>
        <a href="/product">Shop Now</a>
    </div>
    <div class="w3ls_w3l_banner_nav_right_grid w3ls_w3l_banner_nav_right_grid_sub">
        <h3 class="w3l_fruit">${title}</h3>
        <div id="productChartRows">
            <%-- место для продуктов --%>
        </div>
    </div>
</div>