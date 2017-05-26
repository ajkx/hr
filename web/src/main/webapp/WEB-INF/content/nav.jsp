<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/11
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/init.jsp" %>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="${ctx}/static/css/nav.css"/>
</head>

<body>

<div class="modal fade" id="changepassword-modal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog" style="width:500px">
        <div class="modal-content" id="changepassword-modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title">
                    修改密码
                </h4>
            </div>
            <div class="modal-body ">
                <form class="form-horizontal" role="form" action="/sys/user/changePassword" method="post"
                      onsubmit="return ajaxSubmit(this)">
                    <%--eidtlist存放创建或者修改的编辑字段集合--%>
                    <input type="hidden" class="form-control" id="topicid" name="id" value=""/>
                    <div class="form-group">
                        <div class="control-wrapper">
                            <label for="name">原密码</label>
                            <input type="text" class="form-control" id="name" name="currentPassword" value="" required>
                        </div>
                        <div class="control-wrapper">
                            <label for="name">新密码</label>
                            <input class="form-control" rows="3" name="password"/>
                        </div>
                        <div class="control-wrapper">
                            <label for="name">确认密码</label>
                            <input class="form-control" rows="3" name="confirmPassword"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="control-wrapper">
                            <button type="submit" style="float: right" class="btn btn-primary">保存</button>
                        </div>
                    </div>
                    <hr>
                </form>
            </div>
        </div>
    </div>
</div>


<nav class="navbar">
    <div class="left-panel">
        <a class="quickicon logo-text"><span>考勤管理系统</span></a>
    </div>
    <%--<div class="center-panel">--%>
        <%--<a class="quickicon" href="#">--%>
            <%--<span>排班管理</span>--%>
        <%--</a>--%>
        <%--<a class="quickicon" href="#">--%>
            <%--<span>人员调整</span>--%>
        <%--</a>--%>
        <%--<a class="quickicon" href="#">--%>
            <%--<span>考勤报表</span>--%>
        <%--</a>--%>
    <%--</div>--%>
    <ul class="right-panel" style="display: block;">
        <%--<li class="nav-btn nav-attendance">--%>
            <%--<div class="btn" id="attendance_sign">考勤签到</div>--%>
        <%--</li>--%>
        <li class="nav-btn nav-split">
            <div class="split-line"></div>
        </li>
        <li class="nav-btn nav-help">
            <i class="iconf icon-question_b icon-help-slim"></i>
        </li>
        <li class="nav-btn nav-message" id="nv-message">
            <i class="iconf icon-alarm icon-help-slim"></i>
        </li>
        <li class="nav-btn nav-menu">
            <a href="#" data-toggle="dropdown" style="color: #fff"><i class="iconf icon-menu icon-help-slim"></i></a>
            <ul class="dropdown-menu dropdown-menu-right my-dropdown-menu">
                <li><a href="#menu7" data-target="#changepassword-modal" data-toggle="modal">修改密码</a></li>
                <li><a href="/logout">退出</a></li>
            </ul>
        </li>
    </ul>
    <%--<ul class="right-panel" style="display: block">--%>
        <%--<li class="nav-btn nav-split"></li>--%>
        <%--<div class="userinfo dropdown">--%>
            <%--<a class="portrait" href="#"><img src="${ctx}/static/image/touxiang.png" width="40px" height="40px"/></a>--%>
            <%--<i class="fa fa-angle-down" style="font-size: 20px;font-weight: bold;color:#6F6F6F"></i>--%>

        <%--</div>--%>
    <%--</ul>--%>
</nav>
<hr style="border-top:1px solid #DFDEDA"/>
</body>

</html>