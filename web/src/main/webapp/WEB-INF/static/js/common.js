/**
 * Created by ajkx on 2016/11/25.
 */
$(function () {

    //初始化a标记的pjax事件
    $(document).pjax('a[data-pjax]', '#main-content', {timeout: 5000});
    //每次载入页面完成时进行一次pjax请求
    $.pjax({url: '', container: '#main-content'});
    // $.pjax.defaults.timeout = 1200;

    //绑定模态框的隐藏事件
    $('edit-modal').on("hidden.bs.modal", function (e) {
        //重新载入当前页面
        console.log("test");
        $.pjax({url: location.href, container: '#main-content'});
        //移除模态框元素
        $('#edit-modal').find("#edit-modal-content").children().detach();
    });
});

toastr.options = {
    closeButton: false,
    debug: false,
    progressBar: false,
    positionClass: "toast-top-right",
    onclick: null,
    showDuration: "300",
    hideDuration: "1000",
    timeOut: "3000",
    extendedTimeOut: "1000",
    showEasing: "swing",
    hideEasing: "linear",
    showMethod: "fadeIn",
    hideMethod: "fadeOut"
};
var currentPage;
function chooseResource(url,page){
    if(currentPage == page) {
        $('#edit-modal').modal("show");
    }else {
        showEditModal(url);
        currentPage = page;
    }
}
//弹出修改的模态框
function showEditModal(url) {
    $('#edit-modal').load(url, function (resp) {
        $('#edit-modal').modal("show");
    });
}

function showDelModal(url) {
    $('#confirmModal').modal('show');
    $('#del-btn').attr('data-del-url', url);
}

function deleteData() {
    $.post($('#del-btn').attr('data-del-url'), function (data) {
        $('#confirmModal').modal("hide");
        if (data.status) {
            $.pjax({url: location.href, container: '#main-content'});
            toastr.success("删除成功");
        } else {
            toastr.error(data.msg);
        }
    });
}

function submitForm() {
    var form = $('#modal-form');
    $.ajax({
        url: form.attr('action'),
        type: "POST",
        dataType: "json",
        data: form.serialize(),
        success: function (result) {
            if (result.status) {
                $('#edit-modal').modal('hide');
                toastr.success(result.msg);
                $.pjax({url: location.href, container: '#main-content'});
            } else {
                toastr.error(result.msg);
            }
        },
        error: function (xhr, status) {
            alert("数据传输错误" + status + ",请联系系统管理员");
        }
    });

    return false;
}

function submitFormByPage(node,url) {
    var form = $(node);
    $.ajax({
        url: form.attr("action"),
        type: "POST",
        dataType: "json",
        data: form.serialize(),
        success: function (result) {
            if(result.status){
                toastr.success(result.msg);
                $.pjax({url:url,container:'#main-content'});
            }else{
                toastr.error("执行错误!");
            }
        },
        error: function (xhr, status) {
            alert("数据传输错误" + status + ",请联系系统管理员");
        }
    })
    return false;
}
function ajaxSubmit(node) {
    var form = $(node);
    console.log("ajax");
    $.ajax({
        url: form.attr('action'),
        type: "POST",
        dataType: "json",
        data: form.serialize(),
        success: function (result) {
            // window.location.href = "/login";
            console.log(result.status);
        }
    })
}

function ajaxSubmitGet(url,successFn){
    $.ajax({
        url:url,
        type:"GET",
        dataType:"json",

        success:function (result) {
            successFn(result);
        },
        error:function(e){
            toastr.error("error!");
        }
    })
}
function ajaxSubmitPost(node,successFn){
    var form = $(node);
    $.ajax({
        url:form.attr("action"),
        type:"POST",
        dataType:"json",
        data: form.serialize(),
        success:function (result) {
            successFn(result);
        },
        error:function(e){
            toastr.error("error!");
        }
    })
}
// 日期格式化,不是插件的代码,只用于处理时间格式化
Date.prototype.format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "D+": this.getDate(), //日
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "h+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/([Y,y]+)/.test(fmt)) {
        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for (var k in o) {
        if (new RegExp("(" + k + ")").test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        }
    }
    return fmt;
}

function Trim(str, is_global) {
    var result;
    result = str.replace(/(^\s+)|(\s+$)/g, "");
    if (is_global.toLowerCase() == "g") {
        result = result.replace(/\s/g, "");
    }
    return result;
}

function CastChar(str){
    var result;
    result = str.replace(/，/g, ",");
    return result;
}

function CastStringToDate(str){
    var dateStr;
    dateStr = str.replace(/-/g, "/");
    return new Date(dateStr);
}