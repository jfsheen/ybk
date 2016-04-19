<#include "tmpl/t1.ftl" />
<@htmlHead title="列表">
</@htmlHead>
<@htmlBody project="PT">
<!--=== start Content Part ===-->
<div class="content container">
    <div class="row">
        <div id="pageContent">
            <ol>
            </ol>
        </div>
    </div>
</div>
<!--=== End Content Part ===-->
</@htmlBody>
<@htmlFoot>
<link rel="stylesheet" href="${basePath}/static/jquery/jquery-ffpagination/jquery.ffpage.css" type="text/css" />
<script type="text/javascript" src="${basePath}/static/jquery/jquery-ffpagination/jquery.ffpage.js"></script>
<script>
    function listPage(){
        $("#pageContent").xyPaginate({
            ajaxData:{url:"/ajax/1003"},
            toPage:1,
            pageSize:5
        });
    }
    $(document).ready(function() {
        listPage();
    });
</script>
</@htmlFoot>