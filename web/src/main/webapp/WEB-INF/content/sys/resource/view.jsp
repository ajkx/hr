<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/29
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/init.jsp" %>
<div class="modal-dialog" style="width:500px">
    <div class="modal-content" id="edit-modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                权限信息
            </h4>
        </div>
        <div class="modal-body ">
            <form class="form-horizontal" id="modal-form" role="form" method="post">
                <%--eidtlist存放创建或者修改的编辑字段集合--%>
                <%--<input type="hidden" class="form-control" id="topicid" name="id" value="${map.id}"/>--%>
                <div class="form-group">
                    <div class="control-wrapper">
                        <p style="font-size: 14px"><b>权限名称 : </b>&nbsp;&nbsp;${t.name}</p>
                    </div>
                    <div class="control-wrapper">
                        <p style="font-size: 14px"><b>描述 : </b>&nbsp;&nbsp;${t.description}</p>
                    </div>
                    <div class="control-wrapper">
                        <p style="font-size: 14px"><b>相关角色 : </b>&nbsp;&nbsp;
                            <c:forEach items="${t.roles}" var="role">
                                ${role.name}&nbsp;&nbsp;
                            </c:forEach>
                        </p>
                    </div>
                </div>
                <div class="form-group">
                    <div class="control-wrapper">
                        <button type="button" class="btn btn-info" data-dismiss="modal" style="float: right">关闭</button>
                    </div>
                </div>
                <hr>
            </form>
        </div>
    </div>
</div>