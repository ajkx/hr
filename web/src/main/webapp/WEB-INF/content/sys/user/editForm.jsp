<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/29
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/init.jsp" %>

<c:set var="roles" value="${t.roles}"/>
<c:set var="roleSize" value="${fn:length(roles)}"/>

<script>
    function convertJson(node){
        var data = $(node).serializeJSON();
        var roles = data.roles.split(",");
        var resource = data.hrmResource;
        if(resource.id == "") {
            data.hrmResource = null;
        }
        var roleArray = new Array();
        for(var i = 0; i < roles.length;i++) {
            if(roles[i] != ""){
                var obj = {"id":roles[i]};
                roleArray.push(obj);
            }
        }

        var action = <c:choose><c:when test="${op == '修改'}">"/update"</c:when><c:otherwise>"create"</c:otherwise></c:choose>;
        var url = $(node).attr("action")+action;
        data.roles = roleArray;
        jsonSubmit(url, data,location.href);
        return false;
    }
</script>
<div class="modal-dialog" style="width:500px">
    <div class="modal-content" id="edit-modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h4 class="modal-title" id="myModalLabel">
                ${op}操作员
            </h4>
        </div>
        <div class="modal-body ">
            <form class="form-horizontal" id="modal-form" action="/sys/user/" role="form" method="post"
                  onsubmit="return convertJson(this)">
                <%--eidtlist存放创建或者修改的编辑字段集合--%>
                <input type="hidden" class="form-control" id="topicid" name="id" value="${t.id}"/>
                <div class="form-group">
                    <div class="control-wrapper">
                        <label for="name">账号<span class="not_empty_tips">*</span></label>
                        <input type="text" class="form-control" <c:if test="${op == '修改'}">readonly</c:if> id="name" name="name" value="${t.name}" required autocomplete="off">
                    </div>
                    <c:if test="${op == '新增'}">
                    <div class="control-wrapper">
                        <label for="name">密码</label>
                        <input class="form-control" rows="3" name="password" type="password" value="${t.password}"/>
                    </div>
                    </c:if>
                    <div class="control-wrapper">
                        <label for="name">所属员工</label>
                        <input type="hidden" class="form-control mainid" name="hrmResource[id]" value="${t.hrmResource.id}">
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
                        <input type="hidden" class="form-control mainid" name="roles" value="<c:forEach items="${roles}" var="role">${role.id},</c:forEach>">
                        <div class="create_input_div" onclick="showMulitSelectList(this,'sys/role',event)">
                            <c:forEach items="${roles}" var="role">
                                <li class="ant-tag ant-tag-blue" label-id="${role.id}"><span>${role.name}</span><i class="fa fa-times" onclick="clearChooseIcon(this,event)"></i></li>
                            </c:forEach>
                            <i class="fa fa-times" style="float: right;margin-top: 4px;<c:if test="${roleSize <= 0}">display:none</c:if>" title="取消"
                               onclick="clearSelectList(this,event)"></i>
                        </div>
                        <div class="selectlist" style="display : none">
                            <ul>
                            </ul>
                        </div>
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