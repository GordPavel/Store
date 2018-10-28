<%@ page pageEncoding="UTF-8" %>

<script>
	$(document).ready(function () {
		$.ajax("/rest/categories", {
			success: function (response) {
				$("#menuItemsContainer").html($("#categoryTemplate").render(response));
				$('li.dropdown').hover(function () {
					$(this).find('.dropdown-menu').stop(true, true).css('display', 'block');
				}, function () {
					$(this).find('.dropdown-menu').stop(true, true).css('display', 'none');
				});
			}
		});
	})
</script>
<script id="categoryTemplate" type="text/x-jsrender">
    <%--todo Любой уровень вложенности --%>
    {{if subCategories.length === 0 }}
    <li><a href="/category?name={{>name}}">{{>name}}</a></li>
    {{else}}
    <li class="dropdown mega-dropdown active">
                <a href="/category?name={{>name}}">
                    {{>name}}
                    <span class="caret"></span>
                </a>
                <div class="dropdown-menu mega-dropdown-menu w3ls_vegetables_menu">
                    <div class="w3ls_vegetables">
                        <ul>
                            <li><a href="/category">Vegetables</a></li>
                            <li><a href="/category">Fruits</a></li>
                        </ul>
                    </div>
                </div>
            </li>
    {{/if}}


</script>
<div class="w3l_banner_nav_left" style="width: 100%; float: initial; padding-top: 10px; padding-bottom: 10px">
    <div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
        <ul class="nav navbar-nav nav_1" id="menuItemsContainer">
            <%-- Categories place --%>
        </ul>
    </div>
</div>