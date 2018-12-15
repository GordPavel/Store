<script src="/js/onPage/menu.js"></script>
<script id="subCategory" type="text/x-jsrender">
    {{if !subCategories || subCategories.length === 0 }}
        <li><a href="/category?name={{>name}}">{{>name}}</a></li>
    {{else}}
        <li class="dropdown">
            <a href="/category?name={{>name}}" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" aria-expanded="false">{{>name}}<b class="caret"></b></a>
            <ul class="dropdown-menu dropdownhover-right" role="menu">
                {{for subCategories tmpl="#subCategory"/}}
            </ul>
        </li>
    {{/if}}

</script>
<script id="category" type="text/x-jsrender">
    {{if subCategories.length === 0 }}
        <li><a href="/category?name={{>name}}">{{>name}}</a></li>
    {{else}}
        <li class="dropdown">
            <a href="/category?name={{>name}}" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" aria-expanded="false">{{>name}}<b class="caret"></b></a>
            <ul class="dropdown-menu dropdownhover-bottom" role="menu">
                {{for subCategories tmpl="#subCategory"/}}
            </ul>
        </li>
    {{/if}}

</script>
<nav class="navbar navbar-default" style="margin-bottom: 0" role="navigation">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul id="categories" class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown"
                       aria-expanded="false">Dropdown <b class="caret"></b></a>
                    <ul class="dropdown-menu dropdownhover-bottom" role="menu" aria-labelledby="dropdownMenu1">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li class="dropdown">
                            <a href="#" aria-expanded="false">One more dropdown <span class="caret"></span></a>
                            <ul class="dropdown-menu dropdownhover-right">
                                <li><a href="#">Action</a></li>
                                <li><a href="#">Another action</a></li>
                                <li class="dropdown">
                                    <a href="#" aria-expanded="false">One more dropdown <span class="caret"></span></a>
                                    <ul class="dropdown-menu dropdownhover-right">
                                        <li><a href="#">Action</a></li>
                                        <li><a href="#">Another action</a></li>
                                        <li><a href="#">Something else here</a></li>
                                        <li><a href="#">Separated link</a></li>
                                    </ul>
                                </li>
                                <li><a href="#">Separated link</a></li>
                            </ul>
                        </li>
                        <li><a href="#">Something else here</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>