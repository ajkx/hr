<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/9
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/init.jsp" %>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1.0">
    <title>主页</title>
    <link rel="stylesheet" href="${ctx}/static/css/GridManager.css" />
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css" />
    <link rel="stylesheet" href="${ctx}/static/css/font-awesome.min.css" />
    <link rel="stylesheet" href="${ctx}/static/css/antd.css" />

    <link rel="stylesheet" href="${ctx}/static/css/base.css" />
    <link rel="stylesheet" href="${ctx}/static/css/menu.css" />

    <link rel="stylesheet" href="${ctx}/static/css/detailcontainer.css" />
    <link rel="stylesheet" href="${ctx}/static/css/tree.css" />

    <link rel="stylesheet" href="${ctx}/static/css/home.css" />
    <%--时间选择控件--%>
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap-clockpicker.min.css" />
    <link rel="stylesheet" href="${ctx}/static/css/bootstrap-datetimepicker.min.css" />
    <%--checkbox控件--%>
    <link rel="stylesheet" href="${ctx}/static/css/checkbox.css" />
    <link rel="stylesheet" href="${ctx}/static/css/modal.css" />
    <link rel="stylesheet" href="${ctx}/static/css/toastr.css">

    <script type="text/javascript" src="${ctx}/static/js/jquery.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/jquery.pjax.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/jquery.serializejson.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/bootstrap-treeview.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/jquery.validate.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/messages_zh.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/GridManager.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/echarts.min.js"></script>

    <script type="text/javascript" src="${ctx}/static/js/ElementAction.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/bootstrap-clockpicker.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/bootstrap-datetimepicker.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/bootstrap-datetimepicker.zh-CN.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/icheck.min.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/toastr.js"></script>

    <script type="text/javascript" src="${ctx}/static/js/common.js"></script>

</head>
<body>

<%--Model--%>
<div class="modal fade" id="edit-modal" tabindex="-1" role="dialog" aria-hidden="true">

</div>

<%--确认删除模态框--%>
<div class="modal fade" id="confirmModal">
    <div class="modal-dialog">
        <div class="confirm-content">
            <div class="text-confirm">
                你确定要删除这条数据吗？删除不可恢复！
            </div>
            <div style="text-align:center">
                <button type="button" class="btn-gray btn-confirm" id="del-btn" data-del-url="" onclick="deleteData()">确认
                </button>
                <button type="button" class="btn-gray btn-cancel" data-dismiss="modal">
                    取消
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<%--margin-left: 219px;border-left: 1px solid #e9e9e9;padding: 10px 0px 5px 25px;--%>

<jsp:include page="../nav.jsp"/>
<div style="position: absolute;left:0;right: 0;bottom: 0;top: 61px">
<jsp:include page="../menu.jsp"/>
<div id="content" style="position:absolute;left:220px;right: 0;bottom: 0;top:0;
padding: 10px 25px 5px 25px;">
    <div style="margin: 15px 0px 30px;" id="main-content"></div>
</div>
<%--<div id="content" style="margin-left: 220px;height: 100%;width:100%;padding: 10px 0px 5px 25px;">--%>
    <%--<div style="margin: 15px 0px 30px;" id="main-content"></div>--%>
<%--</div>--%>
</div>
</body>
</html>
