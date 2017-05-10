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
                ${t}操作员
            </h4>
        </div>
        <div class="modal-body ">
            <form class="form-horizontal" id="modal-form" role="form" action="${action}" method="post"
                  onsubmit="return submitForm()">
                <%--eidtlist存放创建或者修改的编辑字段集合--%>
                <input type="hidden" class="form-control" id="topicid" name="id" value="${t.id}"/>
                <div class="form-group">
                    <div class="control-wrapper">
                        <label for="name">账号<span class="not_empty_tips">*</span></label>
                        <input type="text" class="form-control" <c:if test="${op == '修改'}">readonly</c:if> id="name" name="name" value="${t.name}" required>
                    </div>
                    <div class="control-wrapper">
                        <label for="name">密码</label>
                        <input class="form-control" rows="3" name="password" type="password"/>
                    </div>
                    <div class="control-wrapper">
                        <label for="name">密码确认</label>
                        <input class="form-control" rows="3" name="password_confirm" type="password"/>
                    </div>
                    <div class="control-wrapper">
                        <label for="name">所属员工</label>
                        <input type="hidden" class="form-control mainid" name="resource.id" value="${t.hrmResource.id}">
                        <div class="create_input_div" onclick="">
                            <%--showSelectList(this,'resource',event)--%>
                            <span style="margin-left: 7px">${t.hrmResource.name}</span>
                            <i class="fa fa-times" style="float: right;margin-top: 4px;display:none" title="取消"
                               onclick="clearSelectList(this,event)"></i>
                        </div>
                        <div class="selectlist" style="display : none">
                            <ul>
                            </ul>
                        </div>
                    </div>
                    <div class="control-wrapper">
                        <label for="name">所有角色</label>
                        <select id="roles" multiple="multiple">
                            <c:forEach items="${permissions}" var="p">
                                <option value="${p.id}" title="${p.description}">${p.name}&nbsp;&nbsp;(${p.permission})</option>
                            </c:forEach>
                        </select>
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