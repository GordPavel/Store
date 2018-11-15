<%@page pageEncoding="UTF-8" %>
<script id="oneProduct" type="text/x-jsrender">
    <div class="col-md-4 w3ls_w3l_banner_left w3ls_w3l_banner_left_asdfdfd">
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
	                    <h4>{{>price}} ₽
    	                    <%-- todo gordeev Старая цена --%>
	                        <span>{{:oldPrice}} ₽</span>
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