<#include "tmpl/t1.ftl" />
<@htmlHead title="列表">
</@htmlHead>
<@htmlBody project="PT">
<div class="container content">
<div class="row">
<div class="editorDiv">
			<textarea id="editorId" name="content" rows="20" cols="100" style="width:95%;height:200px;visibility:hidden;">
&lt;p&gt;Think Defferent&lt;/p&gt;
&lt;p&gt;Here’s to the crazy ones. The misfits. The rebels. The troublemakers. The round pegs in the square holes. The ones who see things differently. They’re not fond of rules. And they have no respect for the status quo. You can quote them, disagree with them, glorify or vilify them. About the only thing you can’t do is ignore them. Because they change things. They push the human race forward. And while some may see them as the crazy ones, we see genius. Because the people who are crazy enough to think they can change the world, are the ones who do.&nbsp;&lt;/p&gt;
&lt;p&gt;- Apple Inc.&lt;/p&gt;
			</textarea>
    <input type="button" id="btnSubmit" value="submit" />
</div>
<div id="content">
</div>
</div>
</div>
</@htmlBody>
<@htmlFoot>
<script charset="utf-8" src="${basePath}/static/jquery/jquery.min.js"></script>
<script charset="utf-8" src="${basePath}/static/kindeditor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="${basePath}/static/kindeditor/lang/zh_CN.js"></script>
<script>
    $(document).ready(function() {
        var options = {
            uploadJson: '/ajax/1001',
            allowFileManager : false,
            cssPath : '/css/index.css',
            filterMode : true
        };
        var editor;
        KindEditor.ready(function(K){
            editor = K.create('#editorId',  options);
        });
        $("#btnSubmit").click(function(){
            editor.sync();
            var content = $("#editorId").val();
            $.post("/ajax/1002", {content:content}, function(re){if (re != null) {$("#content").html(re);}});
        });
    });

</script>
</@htmlFoot>