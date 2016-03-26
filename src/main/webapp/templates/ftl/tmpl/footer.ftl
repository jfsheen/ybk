<!--=== Footer Version 1 ===-->
<div class="footer-v1">
    <div class="footer">
        <div class="container">
            <div class="row">
                <!-- About -->
                <div class="col-md-3 md-margin-bottom-40">
                    <a href="/"><img id="logo-footer" class="footer-logo" src="logo2-default.png" alt=""></a>
                    <p>About YBK</p>
                    <p></p>
                </div><!--/col-md-3-->
                <!-- End About -->

                <!-- Latest -->
                <div class="col-md-3 md-margin-bottom-40">
                    <div class="posts">
                        <div class="headline"><h2>Latest Posts</h2></div>
                        <ul class="list-unstyled latest-list">
                            <li>
                                <a href="page_registration.html#">Incredible content</a>
                                <small>May 8, 2014</small>
                            </li>
                            <li>
                                <a href="page_registration.html#">Best shoots</a>
                                <small>June 23, 2014</small>
                            </li>
                            <li>
                                <a href="page_registration.html#">New Terms and Conditions</a>
                                <small>September 15, 2014</small>
                            </li>
                        </ul>
                    </div>
                </div><!--/col-md-3-->
                <!-- End Latest -->

                <!-- Link List -->
                <div class="col-md-3 md-margin-bottom-40">
                    <div class="headline"><h2>Useful Links</h2></div>
                    <ul class="list-unstyled link-list">
                        <li><a href="page_registration.html#">About us</a><i class="fa fa-angle-right"></i></li>
                        <li><a href="page_registration.html#">Portfolio</a><i class="fa fa-angle-right"></i></li>
                        <li><a href="page_registration.html#">Latest jobs</a><i class="fa fa-angle-right"></i></li>
                        <li><a href="page_registration.html#">Community</a><i class="fa fa-angle-right"></i></li>
                        <li><a href="page_registration.html#">Contact us</a><i class="fa fa-angle-right"></i></li>
                    </ul>
                </div><!--/col-md-3-->
                <!-- End Link List -->

                <!-- Address -->
                <div class="col-md-3 map-img md-margin-bottom-40">
                    <div class="headline"><h2>Contact Us</h2></div>
                    <address class="md-margin-bottom-40">
                        25, Lorem Lis Street, Orange <br />
                        California, US <br />
                        Phone: 800 123 3456 <br />
                        Fax: 800 123 3456 <br />
                        Email: <a href="mailto:info@anybiz.com" class="">info@anybiz.com</a>
                    </address>
                </div><!--/col-md-3-->
                <!-- End Address -->
            </div>
        </div>
    </div><!--/footer-->

    <div class="copyright">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <p>
                        2015 &copy; All Rights Reserved.
                        <a href="page_registration.html#">Privacy Policy</a> | <a href="page_registration.html#">Terms of Service</a>
                    </p>
                </div>

                <!-- Social Links -->
                <div class="col-md-6">
                    <ul class="footer-socials list-inline">
                        <li>
                            <a href="page_registration.html#" class="tooltips" data-toggle="tooltip" data-placement="top" title="" data-original-title="Facebook">
                                <i class="fa fa-facebook"></i>
                            </a>
                        </li>
                        <li>
                            <a href="page_registration.html#" class="tooltips" data-toggle="tooltip" data-placement="top" title="" data-original-title="Skype">
                                <i class="fa fa-skype"></i>
                            </a>
                        </li>
                        <li>
                            <a href="page_registration.html#" class="tooltips" data-toggle="tooltip" data-placement="top" title="" data-original-title="Google Plus">
                                <i class="fa fa-google-plus"></i>
                            </a>
                        </li>
                        <li>
                            <a href="page_registration.html#" class="tooltips" data-toggle="tooltip" data-placement="top" title="" data-original-title="Linkedin">
                                <i class="fa fa-linkedin"></i>
                            </a>
                        </li>
                        <li>
                            <a href="page_registration.html#" class="tooltips" data-toggle="tooltip" data-placement="top" title="" data-original-title="Pinterest">
                                <i class="fa fa-pinterest"></i>
                            </a>
                        </li>
                        <li>
                            <a href="page_registration.html#" class="tooltips" data-toggle="tooltip" data-placement="top" title="" data-original-title="Twitter">
                                <i class="fa fa-twitter"></i>
                            </a>
                        </li>
                        <li>
                            <a href="page_registration.html#" class="tooltips" data-toggle="tooltip" data-placement="top" title="" data-original-title="Dribbble">
                                <i class="fa fa-dribbble"></i>
                            </a>
                        </li>
                    </ul>
                </div>
                <!-- End Social Links -->
            </div>
        </div>
    </div><!--/copyright-->
</div>
<!--=== End Footer Version 1 ===-->


<!-- JS Global Compulsory -->
<script type="text/javascript" src="${basePath}/static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${basePath}/static/bootstrap/js/bootstrap.min.js"></script>
<!-- JS Implementing Plugins -->
<!-- JS Customization -->
<script type="text/javascript" src="${basePath}/static/js/custom.js"></script>
<!-- JS Page Level -->

<script type="text/javascript">
    $(document).ready(function(){
        $.ajax({
            url:"/ajax/1000",
            async: false,
            cache: false,
            type: 'post',
            dataType: "json",
            data: {
                root: 1
            },
            success: function (re) {
                if (re != null) {
                    $("#menu").html(re);
                }
            },
            error: function(){

            }
        });
    });
</script>
<!--[if lt IE 9]>
	<script src="${basePath}/static/js/plugins/respond.min.js"></script>
	<script src="${basePath}/static/js/plugins/html5shiv.min.js"></script>
	<script src="${basePath}/static/js/plugins/placeholder-IE-fixes.js"></script>
<![endif]-->