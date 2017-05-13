<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/29
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/init.jsp" %>

<c:set var="resources" value="${t.resources}"/>
<c:set var="resourceSize" value="${fn:length(resources)}"/>

<script>
    function convertJson(node){
        var data = $(node).serializeJSON();
        var resources = data.resources.split(",");

        var resourceArray = new Array();
        for(var i = 0; i < resources.length;i++) {
            if(resources[i] != ""){
                var obj = {"id":resources[i]};
                resourceArray.push(obj);
            }
        }
        var action = <c:choose><c:when test="${op == '修改'}">"${t.id}/update"</c:when><c:otherwise>"create"</c:otherwise></c:choose>;
        var url = $(node).attr("action")+action;
        data.resources = resourceArray;
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
                ${op}角色
            </h4>
        </div>
        <div class="modal-body ">
            <form class="form-horizontal" id="modal-form" action="/sys/role/" role="form" method="post"
                  onsubmit="return convertJson(this)">
                <%--eidtlist存放创建或者修改的编辑字段集合--%>
                <input type="hidden" class="form-control" id="topicid" name="id" value="${t.id}"/>
                <div class="form-group">
                    <div class="control-wrapper">
                        <label for="name">角色名<span class="not_empty_tips">*</span></label>
                        <input type="text" class="form-control" id="name" name="name" value="${t.name}" required autocomplete="off">
                    </div>
                    <div class="control-wrapper">
                        <label for="name">详细信息</label>
                        <textarea class="form-control" rows="3" name="description">${t.description}</textarea>
                    </div>
                    <div class="control-wrapper">
                        <label for="name">所有权限</label>
                        <input type="hidden" class="form-control mainid" name="resources" value="<c:forEach items="${resources}" var="resource">${resource.id},</c:forEach>">
                        <div class="create_input_div" onclick="showMulitSelectList(this,'sys/resource',event)">
                            <c:forEach items="${resources}" var="resource">
                                <li class="ant-tag ant-tag-blue" label-id="${resource.id}"><span>${resource.name}</span><i class="fa fa-times" onclick="clearChooseIcon(this,event)"></i></li>
                            </c:forEach>
                            <i class="fa fa-times" style="float: right;margin-top: 4px;<c:if test="${resourceSize <= 0}">display:none</c:if>" title="取消"
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