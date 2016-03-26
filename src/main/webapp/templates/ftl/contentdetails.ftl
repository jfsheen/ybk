<#-- @ftlvariable name="re" type="cc.aisc.ybk.content.model.WebContent" -->
<#include "tmpl/t1.ftl" />
<@htmlHead title="列表">
</@htmlHead>
<@htmlBody project="PT">
<!--=== start Content Part ===-->
<div class="content container">
    <div id="details">
        <#if details??>
            <h1>${details.title?default("THIS IS THE TITLE")}</h1>
            <h3>${details.subtitle?default("this is the subtitle")}</h3>
            <h5>${(details.postAt?string("yyyy-MM-dd"))!}  By ${details.postByUserId?default("poster")}</h5>
            <p>${details.outline?default("outline")}</p>
            <div>${details.content?default("CONTENT")}</div>
        </#if>
    </div>
</div>
<!--=== End Content Part ===-->
</@htmlBody>
<@htmlFoot>
<script charset="utf-8" src="${basePath}/static/jquery/jquery.min.js"></script>
<link rel="stylesheet" href="${basePath}/static/jquery/jquery-ffpagination/jquery.ffpage.css" type="text/css" />
<script type="text/javascript" src="${basePath}/static/jquery/jquery-ffpagination/jquery.ffpage.js"></script>
</@htmlFoot>