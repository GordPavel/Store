<%@page pageEncoding="UTF-8" %>
<script src="/tree-view/js/bootstrap-treeview.min.js"></script>
<link rel="stylesheet" href="/tree-view/css/bootstrap-treeview.css">
<%-- todo gordeev.p Разобраться, почему крашит --%>
<%--<link rel="stylesheet" href="/slider/css/normalize.css">--%>
<script src="/slider/js/ion.rangeSlider.min.js"></script>
<link rel="stylesheet" href="/slider/css/ion.rangeSlider.min.css">
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
        <div class="col-md-4" style="background: lightgray; height : 100%">
            <div>
                <button type="button" class="btn btn-outline-primary btn-lg">Поиск</button>
                <div id="priceSlider">
                    <label for="slider"><span>Выбор цены</span></label>
                    <input type="text" id="slider" name="example_name" value=""/>
                </div>
                <div id="categoryPicker">
                    <label>Категории</label>
                    <br>
                    <div id="tree"></div>
                </div>
            </div>
        </div>
    </div>
</div>