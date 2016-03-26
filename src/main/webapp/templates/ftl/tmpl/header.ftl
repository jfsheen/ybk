<!--=== Header ===-->
<div class="header">
    <div class="container">
        <!-- Logo -->
        <a class="logo" href="/">
            <img src="${basePath}/static/img/logo1-default.png" alt="Logo">
        </a>
        <!-- End Logo -->

        <!-- Topbar -->
        <div class="topbar">
            <ul class="loginbar pull-right">
                <li class="hoverSelector">
                    <i class="fa fa-globe"></i>
                    <a>Languages</a>
                    <ul class="languages hoverSelectorBlock">
                        <li class="active">
                            <a href="page_registration.html#">English <i class="fa fa-check"></i></a>
                        </li>
                        <li><a href="page_registration.html#">Spanish</a></li>
                        <li><a href="page_registration.html#">Russian</a></li>
                        <li><a href="page_registration.html#">German</a></li>
                    </ul>
                </li>
                <li class="topbar-devider"></li>
                <li><a href="page_faq.html">Help</a></li>
                <li class="topbar-devider"></li>
            <#if currentUser??>
                <li><a href="/user/${currentUser.user.id}">${currentUser.user.nickname}</a></li>
            <#else>
                <li><a href="/login">Login</a></li>
                <li><a href="/reg">Sign in</a></li>
            </#if>
            </ul>
        </div>
        <!-- End Topbar -->

        <!-- Toggle get grouped for better mobile display -->
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-responsive-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="fa fa-bars"></span>
        </button>
        <!-- End Toggle -->
    </div><!--/end container-->


    <div id="menu"></div>
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse mega-menu navbar-responsive-collapse">
        <div class="container">
            <ul class="nav navbar-nav">
                <!-- Home -->
                <li class="dropdown">
                    <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                        首页
                    </a>
                    <ul class="dropdown-menu">
                        <li><a href="/">Option 1: Default Page</a></li>

                        <!-- One Page -->
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">Option 2: One Page</a>
                            <ul class="dropdown-menu">
                                <li><a target="_blank" href="One-Page/index.html">- One Page Template</a></li>
                            </ul>
                        </li>
                        <!-- End One Page -->

                        <!-- Shop UI Page -->
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">Option 3: Shop UI</a>
                            <ul class="dropdown-menu">
                                <li><a target="_blank" href="Shop-UI/index.html">- Home Page</a></li>
                            </ul>
                        </li>
                        <!-- End Shop UI -->

                        <!-- Blog Template -->
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">Option 4: Blog Template</a>
                            <ul class="dropdown-menu">
                                <li><a target="_blank" href="Blog/index.html">- Home Page</a></li>
                                <li class="dropdown-submenu">
                                    <a href="javascript:void(0);">- Blog Page Layouts</a>
                                    <ul class="dropdown-menu">
                                        <li><a target="_blank" href="Blog/blog_page_layouts1.html">Page Layouts v1</a></li>
                                    </ul>
                                </li>
                                <li class="dropdown-submenu">
                                    <a href="javascript:void(0);">- Blog Post Layouts</a>
                                    <ul class="dropdown-menu">
                                        <li><a target="_blank" href="Blog/blog_post_layouts1.html">Post Layout v1</a></li>
                                    </ul>
                                </li>
                                <li class="dropdown-submenu">
                                    <a href="javascript:void(0);">- Grid Layouts</a>
                                    <ul class="dropdown-menu">
                                        <li><a target="_blank" href="Blog/blog_grid_1.html">Grid: 1 Col</a></li>
                                    </ul>
                                </li>
                                <li><a target="_blank" href="Blog/blog_single.html">- Single Page</a></li>
                            </ul>
                        </li>
                        <!-- End Blog Template -->

                        <li><a href="page_home8.html">Option 5: Home Discover</a></li>
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">Option 15: Home Sidebar</a>
                            <ul class="dropdown-menu">
                                <li><a href="page_home12.html">- Home Left Sidebar</a></li>
                                <li><a href="page_home13.html">- Home Right Sidebar</a></li>
                            </ul>
                        </li>
                        <li><a href="page_home1.html">Option 16: Home Basic v1</a></li>
                    </ul>
                </li>
                <!-- End Home -->

                <!-- Pages -->
                <li class="dropdown active">
                    <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                        Pages
                    </a>
                    <ul class="dropdown-menu">
                        <!-- About Pages -->
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">About Pages</a>
                            <ul class="dropdown-menu">
                                <li><a href="page_about2.html">About Us </a></li>
                            </ul>
                        </li>
                        <!-- End About Pages -->

                        <!-- Service Pages -->
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">Service Pages</a>
                            <ul class="dropdown-menu">
                                <li><a href="page_services.html">Our Services</a></li>
                            </ul>
                        </li>
                        <!-- End Service Pages -->

                        <!-- Contacts -->
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                                Contact Pages
                            </a>
                            <ul class="dropdown-menu">
                                <li><a href="page_contact1.html">Contacts Default</a></li>
                            </ul>
                        </li>
                        <!-- End Contacts -->

                        <!-- Profile Pages -->
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">Profile Pages</a>
                            <ul class="dropdown-menu">
                                <li><a href="page_profile.html">Profile Main Page</a></li>
                                <li><a href="page_profile_settings.html">Profile Settings</a></li>
                            </ul>
                        </li>
                        <!-- End Profile Pages -->

                        <!-- Job Pages -->
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">Job Pages</a>
                            <ul class="dropdown-menu">
                                <li><a href="page_jobs.html">Jobs Main Page</a></li>
                            </ul>
                        </li>
                        <!-- End Job Pages -->

                        <!-- Pricing Tables -->
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">Pricing Tables</a>
                            <ul class="dropdown-menu">
                                <li><a href="page_pricing_colorful.html">Colorful Pricing Tables</a></li>
                            </ul>
                        </li>
                        <!-- End Pricing Tables -->

                        <!-- Login and Registration -->
                        <li class="dropdown-submenu active">
                            <a href="javascript:void(0);">Login and Registration</a>
                            <ul class="dropdown-menu">
                                <li class="active"><a href="page_registration.html">Registration Page</a></li>
                                <li><a href="page_login.html">Login Page</a></li>
                                <li><a href="page_registration1.html">Registration Option</a></li>
                                <li><a href="page_login1.html">Login Option</a></li>
                            </ul>
                        </li>
                        <!-- End Login and Registration -->

                        <!-- FAQs Pages -->
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">FAQs Pages</a>
                            <ul class="dropdown-menu">
                                <li><a href="page_faq1.html">FAQs Page</a></li>
                                <li><a href="page_faq.html">FAQs Basic</a></li>
                            </ul>
                        </li>
                        <!-- End FAQs Pages -->

                        <!-- Email Tempaltes -->
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">Email Templates</a>
                            <ul class="dropdown-menu">
                                <li class="dropdown-submenu">
                                    <a href="javascript:void(0);">Email Corporate</a>
                                    <ul class="dropdown-menu">
                                        <li><a target="_blank" href="Email-Templates/corporate/email_corporate_aqua.html">Corporate Aqua Color</a></li>
                                    </ul>
                                </li>
                                <li class="dropdown-submenu">
                                    <a href="javascript:void(0);">Email Flat</a>
                                    <ul class="dropdown-menu">
                                        <li><a target="_blank" href="Email-Templates/flat/email_flat_aqua.html">Flat Aqua Color</a></li>
                                    </ul>
                                </li>
                                <li class="dropdown-submenu">
                                    <a href="javascript:void(0);">Email Modern</a>
                                    <ul class="dropdown-menu">
                                        <li><a target="_blank" href="Email-Templates/modern/email_modern_aqua.html">Modern Aqua Color</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <!-- End Email Tempaltes -->

                        <!-- Search Results -->
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">Search Results</a>
                            <ul class="dropdown-menu">
                                <li><a href="page_search_inner_full_width.html">Search Results Full Width</a></li>
                            </ul>
                        </li>
                        <!-- End Search Results -->

                        <!-- Coming Soon -->
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">Coming Soon Pages</a>
                            <ul class="dropdown-menu">
                                <li><a href="page_coming_soon.html">Coming Soon</a></li>
                                <li><a href="page_coming_soon1.html">Coming Soon 1</a></li>
                            </ul>
                        </li>
                        <!-- End Coming Soon -->

                        <!-- Error Pages -->
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">Error Pages</a>
                            <ul class="dropdown-menu">
                                <li><a href="page_404_error.html">404 Error Default</a></li>
                                <li><a href="page_404_error1.html">404 Error Option 1</a></li>
                                <li><a href="page_404_error2.html">404 Error Option 2</a></li>
                                <li><a href="page_404_error3.html">404 Error Option 3</a></li>
                            </ul>
                        </li>
                        <!-- End Error Pages -->

                        <!-- Invoice Page -->
                        <li><a href="page_invoice.html">Invoice Page</a></li>
                        <!-- End Invoice Page -->

                        <!-- Clients Page -->
                        <li><a href="page_clients.html">Clients Page</a></li>
                        <!-- End Clients Page -->

                        <!-- Column Pages -->
                        <li><a href="page_3_columns.html">Three Columns Page</a></li>
                        <!-- End Column Pages -->

                        <!-- Privacy Policy -->
                        <li><a href="page_privacy.html">Privacy Policy</a></li>
                        <!-- End Privacy Policy -->

                        <!-- Terms of Service -->
                        <li><a href="page_terms.html">Terms of Service</a></li>
                        <!-- End Terms of Service -->

                        <!-- Misc Pages -->
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">Misc</a>
                            <ul class="dropdown-menu">
                                <li><a href="page_misc_blank.html">Blank page</a></li>
                            </ul>
                        </li>
                        <!-- End Misc Pages -->

                        <!-- Sub Level Menu -->
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">Sub Level 1</a>
                            <ul class="dropdown-menu no-bottom-space">
                                <li><a href="http://htmlstream.com/preview/unify-v1.9///index.hmtl">Sub Level 2</a></li>
                                <li class="dropdown-submenu">
                                    <a href="javascript:void(0);">Sub Level 2</a>
                                    <ul class="dropdown-menu">
                                        <li><a href="http://htmlstream.com/preview/unify-v1.9///index.hmtl">Sub Level 3</a></li>
                                    </ul>
                                </li>
                                <li><a href="http://htmlstream.com/preview/unify-v1.9///index.hmtl">Sub Level 2</a></li>
                                <li class="dropdown-submenu">
                                    <a href="javascript:void(0);">Sub Level 2</a>
                                    <ul class="dropdown-menu no-bottom-space">
                                        <li><a href="http://htmlstream.com/preview/unify-v1.9///index.hmtl">Sub Level 3</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <!-- End Sub Level Menu -->
                    </ul>
                </li>
                <!-- End Pages -->



                <!-- Portfolio -->
                <li class="dropdown">
                    <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                        Portfolio
                    </a>
                    <ul class="dropdown-menu">
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">No Space Boxed</a>
                            <ul class="dropdown-menu">
                                <li><a href="portfolio_2_columns_grid_no_space.html">2 Columns</a></li>
                            </ul>
                        </li>
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">Grid Boxed</a>
                            <ul class="dropdown-menu">
                                <li><a href="portfolio_4_columns_grid.html">4 Columns</a></li>
                            </ul>
                        </li>
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">Grid Text Boxed</a>
                            <ul class="dropdown-menu">
                                <li><a href="portfolio_4_columns_grid_text.html">4 Columns</a></li>
                            </ul>
                        </li>
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">No Space Full Width</a>
                            <ul class="dropdown-menu">
                                <li><a href="portfolio_6_columns_fullwidth_no_space.html">6 Columns</a></li>
                            </ul>
                        </li>
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">Grid Full Width</a>
                            <ul class="dropdown-menu">
                                <li><a href="portfolio_6_columns_fullwidth.html">6 Columns</a></li>
                            </ul>
                        </li>
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">Grid Text Full Width</a>
                            <ul class="dropdown-menu">
                                <li><a href="portfolio_2_columns_fullwidth_text.html">2 Columns</a></li>
                            </ul>
                        </li>
                        <li><a href="portfolio_hover_colors.html">Portfolio Hover Colors</a></li>
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">Portfolio Items</a>
                            <ul class="dropdown-menu">
                                <li><a href="portfolio_single_item.html">Single Item</a></li>
                            </ul>
                        </li>
                        <li class="dropdown-submenu">
                            <a href="javascript:void(0);">Portfolio Basic Pages</a>
                            <ul class="dropdown-menu">
                                <li><a href="portfolio_old_text_blocks.html">Basic Grid Text</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <!-- End Portfolio -->



                <!-- Shortcodes -->
                <li class="dropdown mega-menu-fullwidth">
                    <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                        Shortcodes
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <div class="mega-menu-content disable-icons">
                                <div class="container">
                                    <div class="row equal-height">
                                        <div class="col-md-3 equal-height-in">
                                            <ul class="list-unstyled equal-height-list">
                                                <li><h3>Typography &amp; Components</h3></li>

                                                <!-- Typography -->
                                                <li><a href="shortcode_typo_general.html"><i class="fa fa-sort-alpha-asc"></i> General Typography</a></li>
                                                <!-- End Typography -->

                                                <!-- Components -->
                                                <li><a href="shortcode_compo_messages.html"><i class="fa fa-comment"></i> Alerts &amp; Messages</a></li>
                                                <!-- End Components -->
                                            </ul>
                                        </div>
                                        <div class="col-md-3 equal-height-in">
                                            <ul class="list-unstyled equal-height-list">
                                                <li><h3>Buttons &amp; Icons</h3></li>

                                                <!-- Buttons -->
                                                <li><a href="shortcode_btn_general.html"><i class="fa fa-flask"></i> General Buttons</a></li>
                                                <!-- End Buttons -->

                                                <!-- Icons -->
                                                <li><a href="shortcode_icon_glyph.html"><i class="fa fa-chevron-circle-right"></i> Glyphicons Icons (Bootstrap)</a></li>
                                                <!-- End Icons -->
                                            </ul>
                                        </div>
                                        <div class="col-md-3 equal-height-in">
                                            <ul class="list-unstyled equal-height-list">
                                                <li><h3>Common elements</h3></li>

                                                <!-- Common Elements -->
                                                <li><a href="shortcode_thumbnails.html"><i class="fa fa-image"></i> Thumbnails</a></li>
                                                <!-- End Common Elements -->
                                            </ul>
                                        </div>
                                        <div class="col-md-3 equal-height-in">
                                            <ul class="list-unstyled equal-height-list">
                                                <li><h3>Forms &amp; Infographics</h3></li>

                                                <!-- Forms -->
                                                <li><a href="shortcode_form_modals.html"><i class="fa fa-bars"></i> Modals</a></li>
                                                <!-- End Forms -->

                                                <!-- Infographics -->
                                                <li><a href="shortcode_compo_charts.html"><i class="fa fa-pie-chart"></i> Charts &amp; Countdowns</a></li>
                                                <!-- End Infographics -->
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </li>
                <!-- End Shortcodes -->



                <!-- Search Block -->
                <li>
                    <i class="search fa fa-search search-btn"></i>
                    <div class="search-open">
                        <div class="input-group animated fadeInDown">
                            <input type="text" class="form-control" placeholder="Search">
                                <span class="input-group-btn">
                                    <button class="btn-u" type="button">Go</button>
                                </span>
                        </div>
                    </div>
                </li>
                <!-- End Search Block -->
            </ul>
        </div><!--/end container-->
    </div><!--/navbar-collapse-->
</div>
<!--=== End Header ===-->