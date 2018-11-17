<%@page pageEncoding="UTF-8" %>
<script src="/tree/js/checktree.js"></script>
<link rel="stylesheet" href="/tree/css/styles.css">
<script src="/slider/js/ion.rangeSlider.min.js"></script>
<link rel="stylesheet" href="/slider/css/ion.rangeSlider.css">
<link rel="stylesheet" href="/slider/css/normalize.css">
<link rel="stylesheet" href="/slider/css/ion.rangeSlider.skinNice.css">
<script src="/js/onPage/search.js"></script>
<script id="categoriesTreeTemplate" type="application/x-jsrender">
    <li>
        <input id="{{>name}}" type="checkbox"/><label for="{{>name}}">{{>name}}</label>
        <ul>
            {{for subCategories tmpl="#categoriesTreeTemplate" /}}
        </ul>
    </li>
</script>
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
                    <ul id="tree" class="checktree">
                        <%-- место для дерева категорий --%>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>