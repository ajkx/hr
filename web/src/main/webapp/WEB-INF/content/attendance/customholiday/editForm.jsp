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
        var action = <c:choose><c:when test="${op == '修改'}">"/update"</c:when><c:otherwise>"create"</c:otherwise></c:choose>;
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
                ${op}节假日
            </h4>
        </div>
        <div class="modal-body ">
            <form class="form-horizontal" id="modal-form" action="/attendance/customholiday/" role="form" method="post"
                  onsubmit="return convertJson(this)">
                <%--eidtlist存放创建或者修改的编辑字段集合--%>
                <input type="hidden" class="form-control" id="topicid" name="id" value="${t.id}"/>
                <div class="form-group">
                    <div class="control-wrapper">
                        <label for="name">名称<span class="not_empty_tips">*</span></label>
                        <input type="text" class="form-control" id="name" name="name" value="${t.name}" required autocomplete="off">
                    </div>
                    <div class="control-wrapper">
                        <label for="name">日期</label>
                        <input readonly="" value="<fmt:formatDate type="date" value="${t.date}"/>" name="date" placeholder="请选择日期"
                               class="ant-input datetimepicker" style="width: 160px" id="date">
                    </div>
                    <div class="control-wrapper">
                        <label for="name">描述</label>
                        <textarea class="form-control" rows="3" name="description">${t.description}</textarea>
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
    $(function(){
        var date = $('#date');
        date.datetimepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
            startView: 2,
            minView: 2,
            maxView: 3,
            minuteStep: 1,
            language: 'zh-CN',
            todayBtn: false,
            clearBtn:false,
//            minuteStep:1,
            timezone: "中国标准时间",
        });
    })
</script>