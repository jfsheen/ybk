/**
 * jquery ajax分页插件
 * 实现功能：
 * 1、一次性把数据加载到页面内存，在页面进行分页。
 * 2、使用jquery的ajax每次从服务器取数据分页。
 */
(function($) {
    var xyPaginate = new function(){
        this.cssWidgets = [];
        this.ajaxpage = function(param){
            this.config ={
                /*data:二维数组，一次性数据传入data进行页面分页，data与ajaxData只有一个生效，data优先
                 * data格式：{data:[["iteye","iteye.com"],["CSDN","csdn.net"],["qq","qq.com"]]}
                 */
                data: null,

                /*url:后台取数据的地址；params：参数
                 * 返回数据格式为：{data:[["iteye","iteye.com"],["CSDN","csdn.net"],["qq","qq.com"]],totalRows:0}
                 * 返回的数据说明：data：为每次返回的记录，totalRows：为总记录数
                 */
                ajaxData:{url:"",params:{}},



                //pageSize:每页的大小，默认是8条记录
                pageSize : 8,

                //当前页码
                toPage : 1,

                //不需要列出的data内容key数组
                excludeData:["Ids"],

                //每row后面添加动作组件
                itemActions:[],
                //动作组件ajax url
                actionUrl:"",
                //使用的渲染组件。默认定义了一些组件，用户可以自定义组件注入cssWidgets数组中。
                cssWidgetIds:[],



                //分页工具条的位置down：表格下方,up:表格上方,both:上下都有
                position:"down",

                maxPageNumCount: 10,  //google分页样式时，最大的分页条上页码显示数量，默认10个

                //回调函数，在分页操作执行后调用的函数，比如点击下一页后再调用这个函数
                callback:null
            };
            $.extend(this.config,param);


            //是否第一页
            this.isFirstPage = function(){
                if(this.config.toPage == 1){
                    return true;
                }
                return false;
            };

            //第一页
            this.firstPage = function(){
                if(this.config.toPage == 1){
                    return this;
                }
                this.config.toPage = 1;
                this.applyBuildContainer();
                return this;
            };

            //上一页
            this.prevPage = function(){
                if(this.config.toPage <= 1){
                    return this;
                }
                this.config.toPage --;
                this.applyBuildContainer();
                return this;
            };
            //下一页
            this.nextPage = function(){
                if(this.config.toPage >= this.config.totalPage){
                    return this ;
                }
                this.config.toPage ++;
                this.applyBuildContainer();
                return this;
            };
            //最后一页
            this.lastPage = function(){
                if(this.config.toPage == this.config.totalPage){
                    return this;
                }
                this.config.toPage = this.config.totalPage;
                this.applyBuildContainer();
                return this;
            };

            //是否最后一页
            this.isLastPage = function(){
                if(this.config.toPage == this.config.totalPage){
                    return true;
                }
                return false;
            };


            //跳转到指定页
            this.skipPage = function(toPage_){
                var numberValue = Number(toPage_);
                if(isNaN(numberValue))return;
                with(this.config){
                    toPage = numberValue;
                    if(toPage < 1 || toPage > totalPage){
                        toPage = toPage < 1? 1 : totalPage;
                    }
                }
                this.applyBuildContainer();
                return this;
            };

            this.receive = function(ids, obj) {
                var actionurl = this.config.actionUrl;
                if (actionurl.charAt(actionurl.length - 1) != "/") {
                    actionurl += "/";
                }
                $.post(actionurl+ids, function(res){
                    if(res.code == "200") {
                        obj.find("a").remove();
                        var data = res.data;
                        var lis = [];
                        lis.push("<li>Supplier:");
                        lis.push(data["supplier"]);
                        lis.push("</li><li>ReceiveAt:");
                        lis.push(data["receiveAt"]);
                        lis.push("</li>");
                        obj.find("li:last").append(lis.join(""));
                    }
                });
            };

            //得到分页的数据
            this.getSubData = function(){
                if (this.config.data != null && $.isArray(this.config.data)) {
                    var totalRows = this.config.totalRows;
                    if(totalRows <= 0){
                        return [];
                    }
                    var startRow = (this.config.toPage - 1) * this.config.pageSize;
                    var endRow = this.config.toPage * this.config.pageSize;
                    if(startRow > totalRows){
                        return [];
                    }
                    if(endRow > totalRows){
                        endRow = totalRows;
                    }
                    return this.config.data.slice(startRow,endRow)
                }else if(this.config.ajaxData.data  && $.isArray(this.config.ajaxData.data)){
                    return this.config.ajaxData.data;
                }else{
                    return [];
                }
            };

            this.search = function(searchParam){
                this.config.ajaxData.params = this.config.ajaxData.params || {};
                $.extend(this.config.ajaxData.params,searchParam);
                this.config.toPage = 1;
                this.applyBuildContainer();
            };



            this.applyBuildContainer = function(){
                var $pageContainer = this;
                var data = this.config.data;
                if (data != null && $.isArray(data)) {
                    this.config.totalRows = data.length;
                    this.config.totalPage = totalPageFun(data.length, this.config.pageSize);
                    buildContainer();
                }else if (!xyPaginate.isNull(this.config.ajaxData.url) ) {//ajax请求数据
                    this.config.ajaxData.params = this.config.ajaxData.params || {};
                    $.extend(this.config.ajaxData.params,{toPage:this.config.toPage,pageSize:this.config.pageSize});

                    $.ajax({
                        type: "POST",
                        url: this.config.ajaxData.url,
                        data: this.config.ajaxData.params,
                        dataType:"json",
                        success: function(result){
                            //var result = res.data;
                            $pageContainer.config.totalRows = result.totalRows;
                            $pageContainer.config.totalPage = totalPageFun(result.totalRows, $pageContainer.config.pageSize);
                            $pageContainer.config.ajaxData.data = result.data;
                            buildContainer();
                        },
                        beforeSend:function(){
                            $pageContainer.addClass("bigpage-ajax-loading");
                        },
                        complete:function(){
                            $pageContainer.removeClass("bigpage-ajax-loading");
                        }
                    });
                }

                //总页数计算函数
                function totalPageFun(totalRows,pageSize){
                    var intRows=parseInt(totalRows);
                    if(intRows <= 0)return 0;
                    var totalPage = parseInt((intRows + pageSize -1)/pageSize,10);
                    return isNaN(totalPage)? 0 : totalPage;
                }

                function buildContainer(){
                    xyPaginate.applyCssWidget($pageContainer);
                    if ($pageContainer.config.callback && $.isFunction($pageContainer.config.callback)){
                        $pageContainer.config.callback($pageContainer)
                    }
                }
            };

            this.applyBuildContainer();
            return this;
        };


        this.isNull = function(obj){
            return !!(obj == null || $.trim(obj) == "" || typeof(obj) == "undefined");

        };

        //向CssWidget数组中添加渲染组件，会覆盖已有的组件。
        this.addCssWidget = function(cssWidget){
            this.cssWidgets.pushEx(cssWidget);
            return this;
        };

        //把渲染组件应用到页面的样式上，默认使用appendToContainer，ajaxpageBar1两个组件
        this.applyCssWidget = function($pageContainer){
            var this_ = this;
            var cssWidgetIds = $pageContainer.config.cssWidgetIds;
            if(cssWidgetIds.length <= 0){
                cssWidgetIds[0] = "appendToContainer";
                cssWidgetIds[1] = "ajaxpageBar1";
            }else{
                var hasAppendToContainer = false;
                for(var i=0;i<cssWidgetIds.length;i++){
                    if(cssWidgetIds[i] == "appendToContainer"){
                        hasAppendToContainer = true;
                    }
                }
                if(!hasAppendToContainer){
                    cssWidgetIds = ["appendToContainer"].concat(cssWidgetIds);
                }
            }

            for(var i=0;i<cssWidgetIds.length;i++){
                var cssWidget = getCssWidgetById(cssWidgetIds[i]);
                if(cssWidget){
                    cssWidget.format($pageContainer);
                }
            }
            //根据id从CssWidget中取得组件
            function getCssWidgetById(name) {
                if(this_.isNull(name)){
                    return false;
                }
                var len = this_.cssWidgets.length;
                for (var i = 0; i < len; i++) {
                    if (this_.cssWidgets[i].id.toLowerCase() == name.toLowerCase()) {
                        return this_.cssWidgets[i];
                    }
                }
                return false;
            }
        };

        //扩张Array的push()方法，使数组内的数据不重复。
        Array.prototype.pushEx = function(obj){
            var a = true;
            for (var i = 0; i < this.length; i++) {
                if (this[i].id.toLowerCase() == obj.id.toLowerCase()) {
                    this[i] = obj;
                    a = false;
                    break;
                }
            }
            if(a){
                this.push(obj);
            }
            return this.length;
        }

    }

    $.extend({xyPaginate: xyPaginate});
    $.fn.xyPaginate = xyPaginate.ajaxpage;

    //添加渲染table内容
    $.xyPaginate.addCssWidget({
        id:"appendToContainer",
        format :function($pageContainer){
            var subData = $pageContainer.getSubData();
            var $ol = $pageContainer.find("ol:first");
            var lisArray = [];
            for(var i=0;i<subData.length;i++){
                var kv = subData[i];
                var liArray = [];
                var exArray = [];
                liArray.push("<li><div><ul>");
                liArray.push("<li><a href='/page/content/"+ kv['id'] +"'>[  "+kv['title']+"  ]</a></li>");
                liArray.push("<li>"+kv['postAt']+"</li>");
                liArray.push("</ul></div></li>");
                lisArray.push(liArray.join(""));
            }
            $ol.html(lisArray.join(""));

            /*$itemBtn=$pageContainer;
            $itemBtn.find("a").die().live("click", function () {     //die()避免live重复绑定fn
                var $div=$(this).parent("div");
                var ids = $div.find("input[name='Ids']").val();
                $pageContainer.receive(ids, $div);
                return false;
            });*/
        }
    });


    //添加分页条组件1
    $.xyPaginate.addCssWidget({
        id:"ajaxpageBar1",
        format :function($pageContainer){
            var prevClass = "current prev";
            var nextClass = "current next";
            if($pageContainer.config.toPage > 1){
                prevClass = "prev"
            }
            if($pageContainer.config.toPage < $pageContainer.config.totalPage){
                nextClass = "next";
            }

            var maxCount = $pageContainer.config.maxPageNumCount;//google分页样式时，最大的页码显示数量
            var currentOption = $pageContainer.config.toPage;
            var endOption = currentOption + parseInt(maxCount/2) ;
            if(endOption > $pageContainer.config.totalPage){
                endOption =  $pageContainer.config.totalPage;
            }
            var beginOption = endOption - maxCount + 1;
            if(beginOption <= 0){
                beginOption = 1;
            }
            var as = "";
            for(var i=beginOption;i<=endOption;i++){
                if(currentOption == i){
                    as += "<li class='active'><a href='javascript:void(0)'>" + i + "</a></li>";
                }else{
                    as += "<li><a href='javascript:void(0)' ajaxpage='skip' pageNumber=" + i + "  >" + i + "</a></li>";
                }
            }
            var footPageHtml = '<div ajaxpage="foot" class="text-center"><ul class="pagination">' +
                '<li><a href="javascript:void(0)" ajaxpage="prev" >Pervious</a></li>' +
                as + '<li><a href="javascript:void(0)" ajaxpage="next"  >Next</a></li>' +
                '&nbsp;&nbsp;Total&nbsp;<span ajaxpage="count" style="color: lawngreen" >0</span>&nbsp;Pages,  Go To Page' +
                '<input type="text" size="3" maxlength="5" ajaxpage="text" >' +
                '<a href="javascript:void(0)" ajaxpage="skipA" >GO</a></div>';
            $pageContainer.siblings("div[ajaxpage='foot']").remove();
            if($pageContainer.config.position == "up"){
                $pageContainer.before(footPageHtml);
            }else if($pageContainer.config.position == "both"){
                $pageContainer.before(footPageHtml);
                $pageContainer.after(footPageHtml);
            }else{
                $pageContainer.after(footPageHtml);
            }

            $footDiv = $pageContainer.siblings("div[ajaxpage='foot']");
            /*$footDiv.data("cntn",$pageContainer);*/
            //a链接注册事件
            $footDiv.find("a").click(function(){
                var $a = $(this);
                /*var cntn = $a.parent().data("cntn");*/
                var cntn = $pageContainer;
                var opType = $a.attr("ajaxpage");
                if(opType == "prev"){
                    cntn.prevPage();
                }else if(opType == "next"){
                    cntn.nextPage();
                }else if(opType == "skip"){
                    cntn.skipPage($a.attr("pageNumber"));
                }else if(opType == "skipA"){
                    cntn.skipPage($a.siblings(":text[ajaxpage='text']").val());
                };
            });
            //文本框输入页码按回车跳转
            $footDiv.find(":text[ajaxpage='text']").keyup(function(event){
                var k = event.keyCode;
                if(k == 13){
                    $(this).siblings("a[ajaxpage='skipA']").click();
                }
            });

            $footDiv.find("a").each(function(i,v){
                var opType = $(v).attr("ajaxpage");
                if(opType == "first" || opType == "prev"){
                    $(v).removeClass().addClass(prevClass);
                }else if(opType == "next" || opType == "last"){
                    $(v).removeClass().addClass(nextClass);
                }
            })
            $footDiv.find("span[ajaxpage='count']").html($pageContainer.config.totalPage);
            $footDiv.find(":text[ajaxpage='text']").val($pageContainer.config.toPage);
        }
    });

    //添加分页条组件2
    $.xyPaginate.addCssWidget({
        id:"ajaxpageBar2",
        format :function($pageContainer){

            var prevClass = "current prev";
            var nextClass = "current next";
            if($pageContainer.config.toPage > 1){
                prevClass = "prev"
            }
            if($pageContainer.config.toPage < $pageContainer.config.totalPage){
                nextClass = "next";
            }
            var $footDiv = $pageContainer.siblings("div[ajaxpage='foot']");
            if($footDiv.length <= 0){
                var footPageHtml = '<div ajaxpage="foot" class="bigpage">共&nbsp;<span ajaxpage="count" style="color: red" >0</span>&nbsp;页&nbsp;<a class="' + prevClass + '" href="javascript:void(0)" ajaxpage="first"  >第一页</a><a class="' + prevClass +'" href="javascript:void(0)" ajaxpage="prev" >上一页</a><a class="' + nextClass + '" href="javascript:void(0)" ajaxpage="next"  >下一页</a><a class="' + nextClass + '" href="javascript:void(0)" ajaxpage="last"  >末一页</a>当前&nbsp;<span style="color: red" ajaxpage="current"></span>&nbsp;页 跳转到 <input type="text" size="5" maxlength="5" ajaxpage="text" > 页  <a href="javascript:void(0)" ajaxpage="skip" >GO</a></div>';
                if($pageContainer.config.position == "up"){
                    $pageContainer.before(footPageHtml);
                }else if($pageContainer.config.position == "both"){
                    $pageContainer.before(footPageHtml);
                    $pageContainer.after(footPageHtml);
                }else{
                    $pageContainer.after(footPageHtml);
                }

                $footDiv = $pageContainer.siblings("div[ajaxpage='foot']");
                $footDiv.data("table",$pageContainer);
                //a链接注册事件
                $footDiv.find("a").click(function(){
                    var $a = $(this);
                    var table2 = $a.parent().data("table");
                    var opType = $a.attr("ajaxpage");
                    if(opType == "first"){
                        table2.firstPage();
                    }else if(opType == "prev"){
                        table2.prevPage();
                    }else if(opType == "next"){
                        table2.nextPage();
                    }else if(opType == "last"){
                        table2.lastPage();
                    }else if(opType == "skip"){
                        table2.skipPage($a.siblings(":text[ajaxpage='text']").val());
                    }
                });
                //文本框输入页码按回车跳转
                $footDiv.find(":text[ajaxpage='text']").keyup(function(event){
                    var k = event.keyCode;
                    if(k == 13){
                        $(this).siblings("a[ajaxpage='skip']").click();
                    }
                });
            }
            $footDiv.find("a").each(function(i,v){
                var opType = $(v).attr("ajaxpage");
                if(opType == "first" || opType == "prev"){
                    $(v).removeClass().addClass(prevClass);
                }else if(opType == "next" || opType == "last"){
                    $(v).removeClass().addClass(nextClass);
                }
            })
            $footDiv.find("span[ajaxpage='count']").html($pageContainer.config.totalPage);
            $footDiv.find("span[ajaxpage='current']").html($pageContainer.config.toPage);
            $footDiv.find(":text[ajaxpage='text']").val($pageContainer.config.toPage);

        }
    });


})(jQuery);
