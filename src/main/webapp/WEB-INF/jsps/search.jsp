<%@page pageEncoding="UTF-8" %>
<%-- todo gordeev.p заменить на https://jonmiles.github.io/bootstrap-treeview/ --%>
<script src="/slider/js/ion.rangeSlider.min.js"></script>
<link rel="stylesheet" href="/slider/css/ion.rangeSlider.css">
<%-- todo gordeev.p Разобраться, почему крашит --%>
<%--<link rel="stylesheet" href="/slider/css/normalize.css">--%>
<link rel="stylesheet" href="/slider/css/ion.rangeSlider.skinNice.css">
<script src="/js/onPage/search.js"></script>
<%@include file="templates/productsGrid.jsp" %>
<div class="w3l_banner_nav_right" style="float : initial; width: 100%">
    <div class="row">
        <div class="col-md-8 w3ls_w3l_banner_nav_right_grid w3ls_w3l_banner_nav_right_grid_sub">
            <h3 class="w3l_fruit">Результаты поиска</h3>
            <div id="productChartRows">
                <%-- место для продуктов --%>
            </div>
        </div>
        <div class="col-md-4" style="background: lightgray">
            <div style="width: 90%">
                <div id="priceSlider">
                    <label>
                        <span>Выбор цены</span>
                        <input type="text" id="slider" name="example_name" value=""/>
                    </label>
                </div>
                <div id="categoryPicker">
                    <label>Категории</label>
                    <br>
                    <%--<ul id="tree" class="checktree">--%>
                    <%--&lt;%&ndash; место для дерева категорий &ndash;%&gt;--%>
                    <%--</ul>--%>
                </div>
            </div>
        </div>
    </div>
</div>