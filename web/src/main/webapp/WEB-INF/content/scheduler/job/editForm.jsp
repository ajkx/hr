<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/29
  Time: 13:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/init.jsp" %>

<script>
    function convertJson(node){
        var data = $(node).serializeJSON();
        console.log(data);

        if(data.autoStart == "1")data.autoStart = true;else data.autoStart = false;
        if(data.runHistory == "1")data.runHistory = true;else data.runHistory = false;

        var action = <c:choose><c:when test="${op == '修改'}">"/update"</c:when><c:otherwise>"/create"</c:otherwise></c:choose>;
        var url = $(node).attr("action")+action;
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
                ${op}计划任务
            </h4>
        </div>
        <div class="modal-body ">
            <form class="form-horizontal" id="modal-form" action="/scheduler/job" role="form" method="post"
                  onsubmit="return convertJson(this)">
                <%--eidtlist存放创建或者修改的编辑字段集合--%>
                <input type="hidden" class="form-control" id="topicid" name="id" value="${t.id}"/>
                <div class="form-group">
                    <div class="control-wrapper">
                        <label for="jobclass">类名<span class="not_empty_tips">*</span></label>
                        <input type="text" class="form-control" id="jobclass" name="jobClass" value="${t.jobClass}" required autocomplete="off">
                    </div>
                    <div class="control-wrapper">
                        <label for="cron">Cron表达式</label>
                        <input value="${t.cronExpression}" name="cronExpression"
                               class="form-control"  id="cron">
                    </div>
                    <div class="div-group">
                        <span>
                            <label class="ant-checkbox-wrapper">
                                <span class="ant-checkbox <c:if test="${t.autoStart == true}">ant-checkbox-checked</c:if>">
                                    <span class="ant-checkbox-inner"></span>
                                        <input type="checkbox" class="ant-checkbox-input"
                                               value="<c:choose><c:when test="${t.autoStart == true}">1</c:when><c:otherwise>0</c:otherwise></c:choose>" id="autoStart" name="autoStart"/>
                                </span>
                            </label>
                        </span>
                        <span>自动初始运行</span>
                    </div>
                    <div class="div-group">
                        <span>
                            <label class="ant-checkbox-wrapper">
                                <span class="ant-checkbox <c:if test="${t.runHistory == true}">ant-checkbox-checked</c:if>">
                                    <span class="ant-checkbox-inner"></span>
                                        <input type="checkbox" class="ant-checkbox-input"
                                               value="<c:choose><c:when test="${t.runHistory == true}">1</c:when><c:otherwise>0</c:otherwise></c:choose>" id="runHistory" name="runHistory"/>
                                </span>
                            </label>
                        </span>
                        <span>启用历史记录</span>
                    </div>
                    <div class="ant-row ant-form-item">
                        <div class="ant-col-2 ant-form-item-label"><label class="">详细描述</label></div>
                        <div class="ant-col-14">
                            <div class="ant-form-item-control" style="margin-left: 20px">
                                <textarea name="description" rows="2" class="ant-input">${t.description}</textarea>
                            </div>
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

<script>
    $(function() {
        var autoStart = $("#autoStart");
        var runHistory = $("#runHistory");

        $(autoStart).click(function () {
            var node = $(this);
            if (node.val() == '1') {
                node.parent().removeClass('ant-checkbox-checked');
                node.val("0");
            } else {
                node.parent().addClass('ant-checkbox-checked');
                node.val("1");
            }
        });
        $(runHistory).click(function () {
            var node = $(this);
            if (node.val() == '1') {
                node.parent().removeClass('ant-checkbox-checked');
                node.val("0");
            } else {
                node.parent().addClass('ant-checkbox-checked');
                node.val("1");
            }
        });
    })
</script>