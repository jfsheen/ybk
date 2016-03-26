<#include "tmpl/t1.ftl" />
<@htmlHead title="列表">
</@htmlHead>
<@htmlBody project="PT">
<!--=== start Content Part ===-->
<div id="pageContent">
    <ol>
    </ol>
</div>
<!--=== End Content Part ===-->
</@htmlBody>
<@htmlFoot>
<script charset="utf-8" src="${basePath}/static/jquery/jquery.min.js"></script>
<link rel="stylesheet" href="${basePath}/static/jquery/jquery-ffpagination/jquery.ffpage.css" type="text/css" />
<script type="text/javascript" src="${basePath}/static/jquery/jquery-ffpagination/jquery.ffpage.js"></script>
<script>
    function listPage(){
        $("#pageContent").xyPaginate({
            ajaxData:{url:"/ajax/1003/"}
        });
    }
    $(document).ready(function() {
        listPage();
    });
</script>
</@htmlFoot>