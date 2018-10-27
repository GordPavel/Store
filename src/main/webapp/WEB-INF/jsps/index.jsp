<%@page pageEncoding="UTF-8" %>
<div>
    <!-- banner -->
    <div class="banner">
        <div class="w3l_banner_nav_left" style="display: none">
            <nav class="navbar nav_bottom">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header nav_2">
                    <button type="button" class="navbar-toggle collapsed navbar-toggle1" data-toggle="collapse"
                            data-target="#bs-megadropdown-tabs">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div>
                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
                    <ul class="nav navbar-nav nav_1">
                        <li><a href="/category">Branded Foods</a></li>
                        <li><a href="/category">Households</a></li>
                        <li class="dropdown mega-dropdown active">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Veggies & Fruits<span
                                    class="caret"></span></a>
                            <div class="dropdown-menu mega-dropdown-menu w3ls_vegetables_menu">
                                <div class="w3ls_vegetables">
                                    <ul>
                                        <li><a href="/category">Vegetables</a></li>
                                        <li><a href="/category">Fruits</a></li>
                                    </ul>
                                </div>
                            </div>
                        </li>
                        <li><a href="/category">Kitchen</a></li>
                        <li><a href="/category">Short Codes</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Beverages<span
                                    class="caret"></span></a>
                            <div class="dropdown-menu mega-dropdown-menu w3ls_vegetables_menu">
                                <div class="w3ls_vegetables">
                                    <ul>
                                        <li><a href="/category">Soft Drinks</a></li>
                                        <li><a href="/category">Juices</a></li>
                                    </ul>
                                </div>
                            </div>
                        </li>
                        <li><a href="/category">Pet Food</a></li>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Frozen Foods<span
                                    class="caret"></span></a>
                            <div class="dropdown-menu mega-dropdown-menu w3ls_vegetables_menu">
                                <div class="w3ls_vegetables">
                                    <ul>
                                        <li><a href="/category">Frozen Snacks</a></li>
                                        <li><a href="/category">Frozen Nonveg</a></li>
                                    </ul>
                                </div>
                            </div>
                        </li>
                        <li><a href="/category">Bread & Bakery</a></li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </nav>
        </div>
        <div class="w3l_banner_nav_right" style="width: 100%">
            <section class="slider">
                <div class="flexslider">
                    <ul class="slides">
                        <li>
                            <div class="w3l_banner_nav_right_banner">
                                <h3>Make your <span>food</span> with Spicy.</h3>
                                <div class="more">
                                    <a href="/category"
                                       class="button--saqui button--round-l button--text-thick"
                                       data-text="Shop now">Shop now</a>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="w3l_banner_nav_right_banner1">
                                <h3>Make your <span>food</span> with Spicy.</h3>
                                <div class="more">
                                    <a href="/category"
                                       class="button--saqui button--round-l button--text-thick"
                                       data-text="Shop now">Shop now</a>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="w3l_banner_nav_right_banner2">
                                <h3>upto <i>50%</i> off.</h3>
                                <div class="more">
                                    <a href="/category"
                                       class="button--saqui button--round-l button--text-thick"
                                       data-text="Shop now">Shop now</a>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </section>
            <!-- flexSlider -->
            <link rel="stylesheet" href="/css/flexslider.css" type="text/css" media="screen" property=""/>
            <script defer src="js/jquery.flexslider.js"></script>
            <script type="text/javascript">
				$(window).load(function () {
					$('.flexslider').flexslider({
						animation: "slide",
						start: function (slider) {
							$('body').removeClass('loading');
						}
					});
				});
            </script>
            <!-- //flexSlider -->
        </div>
        <div class="clearfix"></div>
    </div>
    <!-- top-brands -->
    <div class="top-brands">
        <div class="">
            <h3>Новинки</h3>
            <div class="row agile_top_brands_grids">
                <div class="col-md-3 top_brand_left">
                    <div class="hover14 column">
                        <div class="agile_top_brand_left_grid">
                            <div class="tag"><img src="/images/tag.png" alt=" " class="img-responsive"/></div>
                            <div class="agile_top_brand_left_grid1">
                                <figure>
                                    <div class="snipcart-item block">
                                        <div class="snipcart-thumb">
                                            <a href="/prodict"><img title=" " alt=" "
                                                                    src="/images/1.png"/></a>
                                            <p>fortune sunflower oil</p>
                                            <h4>$7.99 <span>$10.00</span></h4>
                                        </div>
                                        <div class="snipcart-details top_brand_home_details">
                                            <form action="checkout.html" method="post">
                                                <fieldset>
                                                    <input type="hidden" name="cmd" value="_cart"/>
                                                    <input type="hidden" name="add" value="1"/>
                                                    <input type="hidden" name="business" value=" "/>
                                                    <input type="hidden" name="item_name"
                                                           value="Fortune Sunflower Oil"/>
                                                    <input type="hidden" name="amount" value="7.99"/>
                                                    <input type="hidden" name="discount_amount" value="1.00"/>
                                                    <input type="hidden" name="currency_code" value="USD"/>
                                                    <input type="hidden" name="return" value=" "/>
                                                    <input type="hidden" name="cancel_return" value=" "/>
                                                    <input type="submit" name="submit" value="Add to cart"
                                                           class="button"/>
                                                </fieldset>

                                            </form>

                                        </div>
                                    </div>
                                </figure>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 top_brand_left">
                    <div class="hover14 column">
                        <div class="agile_top_brand_left_grid">
                            <div class="agile_top_brand_left_grid1">
                                <figure>
                                    <div class="snipcart-item block">
                                        <div class="snipcart-thumb">
                                            <a href="/product"><img title=" " alt=" "
                                                                    src="images/3.png"/></a>
                                            <p>basmati rise (5 Kg)</p>
                                            <h4>$11.99 <span>$15.00</span></h4>
                                        </div>
                                        <div class="snipcart-details top_brand_home_details">
                                            <form action="#" method="post">
                                                <fieldset>
                                                    <input type="hidden" name="cmd" value="_cart"/>
                                                    <input type="hidden" name="add" value="1"/>
                                                    <input type="hidden" name="business" value=" "/>
                                                    <input type="hidden" name="item_name" value="basmati rise"/>
                                                    <input type="hidden" name="amount" value="11.99"/>
                                                    <input type="hidden" name="discount_amount" value="1.00"/>
                                                    <input type="hidden" name="currency_code" value="USD"/>
                                                    <input type="hidden" name="return" value=" "/>
                                                    <input type="hidden" name="cancel_return" value=" "/>
                                                    <input type="submit" name="submit" value="Add to cart"
                                                           class="button"/>
                                                </fieldset>
                                            </form>
                                        </div>
                                    </div>
                                </figure>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 top_brand_left">
                    <div class="hover14 column">
                        <div class="agile_top_brand_left_grid">
                            <div class="agile_top_brand_left_grid1">
                                <figure>
                                    <div class="snipcart-item block">
                                        <div class="snipcart-thumb">
                                            <a href="/product"><img title=" " alt=" "
                                                                    src="images/3.png"/></a>
                                            <p>basmati rise (5 Kg)</p>
                                            <h4>$11.99 <span>$15.00</span></h4>
                                        </div>
                                        <div class="snipcart-details top_brand_home_details">
                                            <form action="#" method="post">
                                                <fieldset>
                                                    <input type="hidden" name="cmd" value="_cart"/>
                                                    <input type="hidden" name="add" value="1"/>
                                                    <input type="hidden" name="business" value=" "/>
                                                    <input type="hidden" name="item_name" value="basmati rise"/>
                                                    <input type="hidden" name="amount" value="11.99"/>
                                                    <input type="hidden" name="discount_amount" value="1.00"/>
                                                    <input type="hidden" name="currency_code" value="USD"/>
                                                    <input type="hidden" name="return" value=" "/>
                                                    <input type="hidden" name="cancel_return" value=" "/>
                                                    <input type="submit" name="submit" value="Add to cart"
                                                           class="button"/>
                                                </fieldset>
                                            </form>
                                        </div>
                                    </div>
                                </figure>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 top_brand_left">
                    <div class="hover14 column">
                        <div class="agile_top_brand_left_grid">
                            <div class="agile_top_brand_left_grid1">
                                <figure>
                                    <div class="snipcart-item block">
                                        <div class="snipcart-thumb">
                                            <a href="/product"><img title=" " alt=" "
                                                                    src="images/3.png"/></a>
                                            <p>basmati rise (5 Kg)</p>
                                            <h4>$11.99 <span>$15.00</span></h4>
                                        </div>
                                        <div class="snipcart-details top_brand_home_details">
                                            <form action="#" method="post">
                                                <fieldset>
                                                    <input type="hidden" name="cmd" value="_cart"/>
                                                    <input type="hidden" name="add" value="1"/>
                                                    <input type="hidden" name="business" value=" "/>
                                                    <input type="hidden" name="item_name" value="basmati rise"/>
                                                    <input type="hidden" name="amount" value="11.99"/>
                                                    <input type="hidden" name="discount_amount" value="1.00"/>
                                                    <input type="hidden" name="currency_code" value="USD"/>
                                                    <input type="hidden" name="return" value=" "/>
                                                    <input type="hidden" name="cancel_return" value=" "/>
                                                    <input type="submit" name="submit" value="Add to cart"
                                                           class="button"/>
                                                </fieldset>
                                            </form>
                                        </div>
                                    </div>
                                </figure>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
            <div class="row agile_top_brands_grids">
                <div class="col-md-3 top_brand_left">
                    <div class="hover14 column">
                        <div class="agile_top_brand_left_grid">
                            <div class="tag"><img src="images/tag.png" alt=" " class="img-responsive"/></div>
                            <div class="agile_top_brand_left_grid1">
                                <figure>
                                    <div class="snipcart-item block">
                                        <div class="snipcart-thumb">
                                            <a href="/product"><img title=" " alt=" "
                                                                    src="images/1.png"/></a>
                                            <p>fortune sunflower oil</p>
                                            <h4>$7.99 <span>$10.00</span></h4>
                                        </div>
                                        <div class="snipcart-details top_brand_home_details">
                                            <form action="checkout.html" method="post">
                                                <fieldset>
                                                    <input type="hidden" name="cmd" value="_cart"/>
                                                    <input type="hidden" name="add" value="1"/>
                                                    <input type="hidden" name="business" value=" "/>
                                                    <input type="hidden" name="item_name"
                                                           value="Fortune Sunflower Oil"/>
                                                    <input type="hidden" name="amount" value="7.99"/>
                                                    <input type="hidden" name="discount_amount" value="1.00"/>
                                                    <input type="hidden" name="currency_code" value="USD"/>
                                                    <input type="hidden" name="return" value=" "/>
                                                    <input type="hidden" name="cancel_return" value=" "/>
                                                    <input type="submit" name="submit" value="Add to cart"
                                                           class="button"/>
                                                </fieldset>

                                            </form>

                                        </div>
                                    </div>
                                </figure>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 top_brand_left">
                    <div class="hover14 column">
                        <div class="agile_top_brand_left_grid">
                            <div class="agile_top_brand_left_grid1">
                                <figure>
                                    <div class="snipcart-item block">
                                        <div class="snipcart-thumb">
                                            <a href="/product"><img title=" " alt=" "
                                                                    src="images/3.png"/></a>
                                            <p>basmati rise (5 Kg)</p>
                                            <h4>$11.99 <span>$15.00</span></h4>
                                        </div>
                                        <div class="snipcart-details top_brand_home_details">
                                            <form action="#" method="post">
                                                <fieldset>
                                                    <input type="hidden" name="cmd" value="_cart"/>
                                                    <input type="hidden" name="add" value="1"/>
                                                    <input type="hidden" name="business" value=" "/>
                                                    <input type="hidden" name="item_name" value="basmati rise"/>
                                                    <input type="hidden" name="amount" value="11.99"/>
                                                    <input type="hidden" name="discount_amount" value="1.00"/>
                                                    <input type="hidden" name="currency_code" value="USD"/>
                                                    <input type="hidden" name="return" value=" "/>
                                                    <input type="hidden" name="cancel_return" value=" "/>
                                                    <input type="submit" name="submit" value="Add to cart"
                                                           class="button"/>
                                                </fieldset>
                                            </form>
                                        </div>
                                    </div>
                                </figure>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 top_brand_left">
                    <div class="hover14 column">
                        <div class="agile_top_brand_left_grid">
                            <div class="agile_top_brand_left_grid1">
                                <figure>
                                    <div class="snipcart-item block">
                                        <div class="snipcart-thumb">
                                            <a href="/product"><img title=" " alt=" "
                                                                    src="images/3.png"/></a>
                                            <p>basmati rise (5 Kg)</p>
                                            <h4>$11.99 <span>$15.00</span></h4>
                                        </div>
                                        <div class="snipcart-details top_brand_home_details">
                                            <form action="#" method="post">
                                                <fieldset>
                                                    <input type="hidden" name="cmd" value="_cart"/>
                                                    <input type="hidden" name="add" value="1"/>
                                                    <input type="hidden" name="business" value=" "/>
                                                    <input type="hidden" name="item_name" value="basmati rise"/>
                                                    <input type="hidden" name="amount" value="11.99"/>
                                                    <input type="hidden" name="discount_amount" value="1.00"/>
                                                    <input type="hidden" name="currency_code" value="USD"/>
                                                    <input type="hidden" name="return" value=" "/>
                                                    <input type="hidden" name="cancel_return" value=" "/>
                                                    <input type="submit" name="submit" value="Add to cart"
                                                           class="button"/>
                                                </fieldset>
                                            </form>
                                        </div>
                                    </div>
                                </figure>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3 top_brand_left">
                    <div class="hover14 column">
                        <div class="agile_top_brand_left_grid">
                            <div class="agile_top_brand_left_grid1">
                                <figure>
                                    <div class="snipcart-item block">
                                        <div class="snipcart-thumb">
                                            <a href="/product"><img title=" " alt=" "
                                                                    src="images/3.png"/></a>
                                            <p>basmati rise (5 Kg)</p>
                                            <h4>$11.99 <span>$15.00</span></h4>
                                        </div>
                                        <div class="snipcart-details top_brand_home_details">
                                            <form action="#" method="post">
                                                <fieldset>
                                                    <input type="hidden" name="cmd" value="_cart"/>
                                                    <input type="hidden" name="add" value="1"/>
                                                    <input type="hidden" name="business" value=" "/>
                                                    <input type="hidden" name="item_name" value="basmati rise"/>
                                                    <input type="hidden" name="amount" value="11.99"/>
                                                    <input type="hidden" name="discount_amount" value="1.00"/>
                                                    <input type="hidden" name="currency_code" value="USD"/>
                                                    <input type="hidden" name="return" value=" "/>
                                                    <input type="hidden" name="cancel_return" value=" "/>
                                                    <input type="submit" name="submit" value="Add to cart"
                                                           class="button"/>
                                                </fieldset>
                                            </form>
                                        </div>
                                    </div>
                                </figure>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
    </div>
</div>

