<%@ page pageEncoding="UTF-8" %>
<script src="/js/onPage/menu.js"></script>
<script id="categoryTemplate" type="text/x-jsrender">
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
                        {{for subCategories tmpl="#categoryTemplate" /}}
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