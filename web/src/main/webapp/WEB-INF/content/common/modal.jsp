<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/29
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/init.jsp" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
        &times;
    </button>
    <h4 class="modal-title" id="myModalLabel">
        ${topic}
    </h4>
</div>
<div class="modal-body">
    <form class="form-horizontal templatemo-container templatemo-login-form-1 margin-bottom-30" id="modal-form" role="form" action="${action}" method="post" onsubmit="return submitForm()">
        <%--eidtlist存放创建或者修改的编辑字段集合--%>
        <c:forEach items="${map}" var="map">
            <c:choose>
                <c:when test="${map.key.equals('id')}">
                    <input type="hidden" class="form-control" id="topicid" name="id" value="${map.value}"/>
                </c:when>
                <c:otherwise>
                    <div class="form-group">
                        <div class="col-xs-12">
                            <div class="control-wrapper">
                                <label for="${map.key}"><i class="control-label fa-label">${map.key}</i></label>
                                <input type="text" class="form-control" id="${map.key}" placeholder="${map.key}" name="${map.key}" value="${map.value}" autocomplete="off">
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        <div class="form-group">
            <div class="col-md-12">
                <div class="control-wrapper">
                    <input type="submit" value="确认" style="float: right" class="btn btn-info">
                </div>
            </div>
        </div>
        <hr>
    </form>
</div>
