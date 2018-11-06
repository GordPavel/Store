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
			data: {sort: 'views,desc'},
			success: function (response) {
				const htmlRows = $('#productsRow').render(response.toMatrix(4));
				$('#productChartRows').html(htmlRows);
			}
		})
	});
</script>
<script id="oneProduct" type="text/x-jsrender">
    <div class="col-md-3 w3ls_w3l_banner_left w3ls_w3l_banner_left_asdfdfd">
        <div class="hover14 column">
            <div class="agile_top_brand_left_grid w3l_agile_top_brand_left_grid">
		<%--todo gordeev лейбл --%>
		<div class="agile_top_brand_left_grid_pos">
	    	<img src="/images/offer.png" alt=" " class="img-responsive"/>
		</div>
		<div class="agile_top_brand_left_grid1">
		    <figure>
		        <div class="snipcart-item block">
		            <div class="snipcart-thumb">
		                <%--todo gordeev изображение --%>
		                <a href="/product">
		                 <img src="/images/57.png" alt=" " class="img-responsive"/>
		                </a>
		                <p> {{>name}}</p>
	                    <h4>{{>price}}
    	                    <%-- todo gordeev Старая цена --%>
	                        <span>{{:oldPrice}}</span>
	                    </h4>
	                    <%-- todo gordeev Просмотры --%>
	                    <h4>Просмотров: {{:viewers}}</h4>
	                </div>
	            </div>
	        </figure>
	    </div>
	</div>
        </div>
    </div>

</script>
<script id="productsRow" type="text/x-jsrender">
<div class="w3ls_w3l_banner_nav_right_grid1 w3ls_w3l_banner_nav_right_grid1_veg">
    {{for #data tmpl="#oneProduct"/}}
    <div class="clearfix"></div>
</div>

</script>
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