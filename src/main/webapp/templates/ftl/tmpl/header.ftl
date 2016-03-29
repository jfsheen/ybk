<#macro header>
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



    <!-- Collect the nav links, forms, and other content for toggling -->
    <!--/navbar-collapse-->
    <#nested>

</div>
</#macro>
<!--=== End Header ===-->