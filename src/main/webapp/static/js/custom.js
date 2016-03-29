function initMenu() {
    var arr = [];
    $.ajax({
        url:'/ajax/1000',
        async : false,
        cache : false,
        type : 'post',
        contentType:"application/json;charset=UTF-8",
        dataType : 'json',
        success : function(data) {
            if (data != null/* && $.isArray(data)*/) {
                var c1 = data[0].children;
                $(c1).each(function (i1, lvl1) {
                    if (lvl1.enabled) {
                        if (lvl1.children.length == 0) {
                            arr.push("<li class='dropdown'><a href='" + lvl1.funcPath + "' class='dropdown-toggle' data-toggle='dropdown'>" + lvl1.caption + "</a>");
                        }
                        else {
                            arr.push("<li class='dropdown'><a href='javascript:void(0);' class='dropdown-toggle' data-toggle='dropdown'>" + lvl1.caption + "</a>");
                            arr.push("<ul class='dropdown-menu'>");
                            $(lvl1.children).each(function (i2, lvl2) {
                                if (lvl2.enabled) {
                                    arr.push("<li><a href='" + lvl2.funcPath + "'>" + lvl2.caption + "</a></li>");
                                }
                            });
                            arr.push("</ul>");
                        }
                        arr.push("</li>");
                    }
                });
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.status);
            alert(XMLHttpRequest.readyState);
            alert(textStatus);
        }
    });
    $("#custommenu > ul").html(arr.join(""));
}