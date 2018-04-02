var Util = {};
$(document).ready(function () {
    //tab切换
    $(".page_tab span").bind("click", function () {
        var tab = $(".page_tab span");
        $.each(tab, function (i, n) {
            var tid = $(n).attr('data');
            $(n).removeClass('active');
            $("#" + tid).hide();
        });
        var current = $(this).attr('data');
        $(this).addClass('active');
        $("#" + current).show();
    });
});
Util.formParams = function (formId) {
    var nodes = $("#" + formId).find("input[type='text'],input[type='password'],input[type='hidden'],input[type='radio']:checked," +
    "input[type='checkbox']:checked,textarea,select");
    var params = {};
    for (var i = 0; i < nodes.length; i++) {
        var e = $(nodes[i]);
        var name = e.attr("name");
        if (name == undefined) continue;
        if (params[name] == undefined) {
            params[name] = e.attr("value");
        } else {
            params[name] += "|" + e.attr("value");
        }
    }
    return params;
};
//检查是否为int类型
function checkInt(_params) {
    return /^\d+$/.test(_params);
}
Util.checkInt = checkInt;
Util.checkFloat = checkFloat;
Util.checkMoney = checkMoney;
//检查是否为float类型
function checkFloat(_params) {
    var reg = /^(-|\+)?\d+\.\d*$/;
    return checkInt(_params) || reg.test(_params);
}
//检查是否为金钱类型
function checkMoney(_params) {
    var reg = /^(-?\d+)(\.\d{0,2})?$/;     //小数格式
    return reg.test(_params);
}

//检查输入金钱类型
Util.checkInputMoney = function (_input, _tipId) {
    var tipNode = $("#" + _tipId);
    var isNum = checkMoney(_input.value);
    if (!isNum) {
        tipNode.css("color", "Red");
    } else {
        tipNode.css("color", "Gray");
    }
}
//检查是否为email类型
function checkEmail(_params) {
    var t = /^([a-zA-Z0-9]+[_|\\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    return t.test(_params);
}
//检查是否数字类型
function checkSZ(_params) {
    var t = /^\d+$/;
    return t.test(_params);
}
//检查是否为手机号11位
function checkMobile(_params) {
    var t = /^[0-9]{11}$/;
    return t.test(_params);
}
function checkTelephone(_params) {
    var t = /(\d{3}-|\d{4}-)?(\d{8}|\d{7})?/;
    return t.test(_params);
}
//检查账号是否为字母数字下划线组成
function checkAccount(_params) {
    var t = /^(?!_)(?!.*?_$)[a-zA-Z0-9_]+$/;
    return t.test(_params);
}

function keyupEvent(input) {
    if (input.value == '') {
        input.parentNode.className = 'showplaceholder';
    } else {
        input.parentNode.className = 'hiddenplaceholder';
    }
}

function Arabia_to_Chinese(Num) {
    for (i = Num.length - 1; i >= 0; i--) {
        Num = Num.replace(",", "");
        Num = Num.replace(" ", "");
    }
    Num = Num.replace("￥", "")
    //替换掉可能出现的￥字符
    if (isNaN(Num)) {
        //alert("按信贷错误");
        return;
    }

    part = String(Num).split(".");
    newchar = "";

    for (i = part[0].length - 1; i >= 0; i--) {
        if (part[0].length > 10) {
            //alert("您在安心贷输入的金额位数过大，无法计算");
            return "";
        }
        tmpnewchar = ""
        perchar = part[0].charAt(i);
        switch (perchar) {
            case "0":
                tmpnewchar = "零" + tmpnewchar;
                break;
            case "1":
                tmpnewchar = "壹" + tmpnewchar;
                break;
            case "2":
                tmpnewchar = "贰" + tmpnewchar;
                break;
            case "3":
                tmpnewchar = "叁" + tmpnewchar;
                break;
            case "4":
                tmpnewchar = "肆" + tmpnewchar;
                break;
            case "5":
                tmpnewchar = "伍" + tmpnewchar;
                break;
            case "6":
                tmpnewchar = "陆" + tmpnewchar;
                break;
            case "7":
                tmpnewchar = "柒" + tmpnewchar;
                break;
            case "8":
                tmpnewchar = "捌" + tmpnewchar;
                break;
            case "9":
                tmpnewchar = "玖" + tmpnewchar;
                break;
        }
        switch (part[0].length - i - 1) {
            case 0:
                tmpnewchar = tmpnewchar + "元";
                break;
            case 1:
                if (perchar != 0) tmpnewchar = tmpnewchar + "拾";
                break;
            case 2:
                if (perchar != 0) tmpnewchar = tmpnewchar + "佰";
                break;
            case 3:
                if (perchar != 0) tmpnewchar = tmpnewchar + "仟";
                break;
            case 4:
                tmpnewchar = tmpnewchar + "万";
                break;
            case 5:
                if (perchar != 0) tmpnewchar = tmpnewchar + "拾";
                break;
            case 6:
                if (perchar != 0) tmpnewchar = tmpnewchar + "佰";
                break;
            case 7:
                if (perchar != 0) tmpnewchar = tmpnewchar + "仟";
                break;
            case 8:
                tmpnewchar = tmpnewchar + "亿";
                break;
            case 9:
                tmpnewchar = tmpnewchar + "拾";
                break;
        }
        newchar = tmpnewchar + newchar;
    } //for

    if (Num.indexOf(".") != -1) {
        if (part[1].length > 2) {
            alert("小数点之后只能保留两位,系统将自动截段");
            part[1] = part[1].substr(0, 2)
        }
        for (i = 0; i < part[1].length; i++) {//for2
            tmpnewchar = ""
            perchar = part[1].charAt(i)
            switch (perchar) {
                case "0":
                    tmpnewchar = "零" + tmpnewchar;
                    break;
                case "1":
                    tmpnewchar = "壹" + tmpnewchar;
                    break;
                case "2":
                    tmpnewchar = "贰" + tmpnewchar;
                    break;
                case "3":
                    tmpnewchar = "叁" + tmpnewchar;
                    break;
                case "4":
                    tmpnewchar = "肆" + tmpnewchar;
                    break;
                case "5":
                    tmpnewchar = "伍" + tmpnewchar;
                    break;
                case "6":
                    tmpnewchar = "陆" + tmpnewchar;
                    break;
                case "7":
                    tmpnewchar = "柒" + tmpnewchar;
                    break;
                case "8":
                    tmpnewchar = "捌" + tmpnewchar;
                    break;
                case "9":
                    tmpnewchar = "玖" + tmpnewchar;
                    break;
            }
            if (i == 0) tmpnewchar = tmpnewchar + "角";
            if (i == 1) tmpnewchar = tmpnewchar + "分";
            newchar = newchar + tmpnewchar;
        } //for2
    }
    //替换所有无用汉字
    while (newchar.search("零零") != -1)
        newchar = newchar.replace("零零", "零");
    newchar = newchar.replace("亿零万", "亿");
    newchar = newchar.replace("零亿", "亿");
    newchar = newchar.replace("亿万", "亿");
    newchar = newchar.replace("零万", "万");
    newchar = newchar.replace("零元", "元");
    newchar = newchar.replace("零角", "");
    newchar = newchar.replace("零分", "");

    if (newchar.charAt(newchar.length - 1) == "元" || newchar.charAt(newchar.length - 1) == "角")
        newchar = newchar + "整"
    return newchar;
}

//搜索框显示
function openSearch(id) {
    $("#" + id).show(1000);
}
//搜索框关闭
function closeSearch(id) {
    $("#" + id).hide(1000);
}
//打开弹出框
function showUrl(url, title, width, height) {
    $.jBox("iframe:" + url, {
        title: title,
        width: width,
        height: height,
        buttons: {'关闭': true}
    });
}
//关闭弹出框
function closeJBox() {
    window.parent.window.jBox.close();
}
//关闭弹出框后刷新父窗口
function reloadParent() {
    window.parent.window.reload();
}

//跳转连接
function go(url) {
    window.location.href = url;
}
//刷新
function reload() {
    window.location.reload();
}

/**
 * 上传图片 下一个图片替换上一个图片
 * @param fileUploadId 文件id
 * @param name img元素的name属性
 * @param viewDiv 显示图片的div的id属性值
 * @param width
 * @param height
 */
function uploadImage(fileUploadId, name, viewDiv, width, height) {
    $.ajaxFileUpload(
        {
            url: "/uploadImage.rest?_t=" + new Date().getTime(),            //需要链接到服务器地址
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: fileUploadId,                        //文件选择框的id属性
            dataType: 'json',                                     //服务器返回的格式，可以是json, xml
            success: function (data)  //服务器成功响应处理函数
            {
                // var str = $(data).find("body").text();//获取返回的字符串
                // var json = $.parseJSON(str);//把字符串转化为json对象
                // $("#" + viewDiv).text(json);
                if (data.success) {
                    var html = '<img style="cursor: pointer" onmousedown="deleteImage(event, this)" src="' + data.data + '" width="' + width + '" height="' + height + '" name="' + name + '" title="点击右键删除"/>';
                    $("#" + viewDiv).html(html);
                } else {
                    alert(data.msg);
                }
            }
        }
    );
}
//移除上传的图片依附于 uploadImage 这个方法
function deleteImage(e, node) {
    if (e.button == 2) {
        if (confirm("确定移除该图片吗？")) {
            $(node).remove();
        }
    }
}

/**
 * 下一个图片在上一个图片的后面
 * @param fileUploadId 文件id
 * @param name img元素的name属性
 * @param viewDiv 显示图片的div的id属性值
 * @param width
 * @param height
 * @param url  URL 的name属性
 * @param remark 备注的name属性
 */
function uploadImageS(fileUploadId, name, viewDiv, width, height, remark) {
    $.ajaxFileUpload(
        {
            url: "/uploadImage.do?_t=" + new Date().getTime(),            //需要链接到服务器地址
            secureuri: false,//是否启用安全提交，默认为false
            fileElementId: fileUploadId,                        //文件选择框的id属性
            dataType: 'json',                                     //服务器返回的格式，可以是json, xml
            success: function (data, status)  //服务器成功响应处理函数
            {
                if (data.success) {
                    add_image(name, viewDiv, width, height, data.data,'', remark);
                } else {
                    alert(data.msg);
                }
            }
        }
    );
}

//添加图片 不覆盖上一张图片
function add_image(name, viewDiv, width, height, src,remarkVal, remark) {
    var html = '<div class="albCt">' +
        '<img style="cursor: pointer"'+
        ' src="' + src + '" width="' + width + '" height="' + height + '" name="' + name + '"/>' +
        '<a onclick="delete_image(this);" href="javascript:void(0);">[删除]</a>' +
        '<a onclick="forward_image(this)" href="javascript:void(0);">[前移]</a>' +
        '<a onclick="move_backward_image(this)" href="javascript:void(0);">[后移]</a>' +
      /*  '<div style="margin-top:10px">&nbsp;URL：<input type="text" style="width:190px;" value="'+urlVal+'" name="'+url+'">' +*/
        '<div style="margin-top:10px">注释：<input type="text" style="width:190px;" value="'+remarkVal+'" name="'+remark+'">' +
        '</div>' +
        '</div>';
    $("#" + viewDiv).html($("#" + viewDiv).html() + html);
}

//删除上传的图片依附于  uploadImageS这个方法
function delete_image(node) {
    if(confirm("确定删除该图片吗？")) {
        $(node).parent().remove();
    }
}
//前移图片
function forward_image(node) {
    var c_parent_div = $(node).parent();//当前元素的父元素
    var q_div = $(node).parent().prev();//当前元素的父元素前面的div元素
    var c_parent_div_html = $(node).parent().html();//当前元素的父元素下的所有html
    var q_div_html = $(node).parent().prev().html();//当前元素的父元素前面的div元素中的所有html
    if(q_div_html == null || q_div_html == undefined || q_div_html == '') {
        return;
    }
    //把当前元素的父元素的内容和之前的内容进行替换
    c_parent_div.html(q_div_html);
    q_div.html(c_parent_div_html);
}
//后移图片
function move_backward_image(node) {
    var c_parent_div = $(node).parent();//当前元素的父元素
    var h_div = $(node).parent().next("div");//当前元素的父元素的后面的第一个div元素
    var c_parent_div_html = $(node).parent().html();//当前元素的父元素下的所有html
    var h_div_html = $(node).parent().next("div").html();//当前元素的父元素后面的div元素中的所有html
    if(h_div_html == null || h_div_html == undefined || h_div_html == '') {
        return;
    }
    //把当前元素的父元素的内容和之后的内容进行替换
    h_div.html(c_parent_div_html);
    c_parent_div.html(h_div_html);
}

function GetRequest() {
    var url = location.search; //获取url中"?"符后的字串
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        strs = str.split("&");
        for(var i = 0; i < strs.length; i ++) {
            theRequest[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
        }
    }
    return theRequest;
}


Util.editors = [];

