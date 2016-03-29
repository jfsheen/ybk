<#-- @ftlvariable name="m" type="cc.aisc.ybk.content.model.Menu" -->
<#assign basePath="${request.getContextPath()}">
<#include "header.ftl" />
<#include "footer.ftl" />
<#macro htmlHead title charset="utf-8" lang="zh-CN">
    <#import "/spring.ftl" as spring>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh-CN" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="zh-CN" class="ie9"> <![endif]-->
<!--[if !IE]> <html lang="zh-CN"> <![endif]-->
<html>
<head>
    <title>平台 - ${title}</title>

    <!-- Meta -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Favicon -->
    <link rel="shortcut icon" href="${basePath}/img/favicon.ico">

    <!-- Web Fonts -->
    <link rel='stylesheet' type='text/css' href="${basePath}/static/fonts/fonts.css">

    <!-- CSS Global Compulsory -->
    <link rel="stylesheet" href="${basePath}/static/bootstrap/css/bootstrap.min.css">
    <!-- CSS Header and Footer -->
    <link rel="stylesheet" href="${basePath}/static/css/headerandfooter/header-default.css">
    <link rel="stylesheet" href="${basePath}/static/css/headerandfooter/footer-v1.css">

    <!-- CSS Implementing Plugins -->
    <link rel="stylesheet" href="${basePath}/static/css/font-awesome/css/font-awesome.min.css">
    <!-- CSS Theme -->
    <link rel="stylesheet" href="${basePath}/static/css/home_3col.css">
    <!-- CSS Customization -->
    <link rel="stylesheet" href="${basePath}/static/css/common.css">
    <link rel="stylesheet" href="${basePath}/static/css/style.css">
    <!-- page custom head-->

    <#nested>
</head>
</#macro>
<#macro htmlBody project charset="utf-8" lang="zh-CN">
<body onload="initMenu()">
<div class="wrapper">
    <@header>
        <div class="collapse navbar-collapse mega-menu navbar-responsive-collapse">
            <div id="custommenu" class="container">
                <ul class='nav navbar-nav'>
                </ul>
            </div>
        </div>
    </@header>
    <!--=== Breadcrumbs v1 ===-->
    <div class="breadcrumbs-v1">
        <div class="container">
            <span>Blog Page</span>
            <h1>Basic Medium Posts</h1>
        </div>
    </div>
    <!--=== End Breadcrumbs v1 ===-->
<#--    <div class="container content-md">
        <div class="row">-->
            <#nested>
<#--        </div>
    </div>-->
    <@footer>
    </@footer>
</#macro>
<#macro htmlFoot charset="utf-8" lang="zh-CN">
    <#nested>
</div>
</body>
</html>
</#macro>