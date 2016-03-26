<#assign basePath="${request.getContextPath()}">
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
    <link rel="shortcut icon" href="favicon.ico">

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

    <!-- CSS Customization -->
    <link rel="stylesheet" href="${basePath}/static/css/common.css">
    <!-- page custom head-->

    <#nested>
</head>
</#macro>
<#macro htmlBody project charset="utf-8" lang="zh-CN">
<body>
<div class="wrapper">
    <#include "header.ftl" />
    <#nested>
</div>
    <#include "footer.ftl" />
</#macro>
<#macro htmlFoot charset="utf-8" lang="zh-CN">
    <#nested>
</body>
</html>
</#macro>