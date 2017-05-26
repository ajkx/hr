<%--
  Created by IntelliJ IDEA.
  User: ajkx
  Date: 2017/2/17
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/init.jsp" %>
<c:set var="returnUrl" value="${op != '修改' ? '/attendance/record/create.html' : '/attendance/record/manual.html'}"/>


<script>
    function convertJson(node){
        var data = $(node).serializeJSON();
        data.type = "manual";
        var action = <c:choose><c:when test="${op == '修改'}">"${t.id}/update"</c:when><c:otherwise>"create"</c:otherwise></c:choose>;
        var url = $(node).attr("action")+action;
        jsonSubmit(url, data,"${returnUrl}");
        return false;
    }

</script>

<div style="margin-top: 15px; width: 1150px; background: rgb(255, 255, 255);">
    <div>
        <div class="" style="margin: 20px 0px 30px; opacity: 1; visibility: visible; transform: translateX(0px);">
            <form class="ant-form ant-form-horizontal" action="/attendance/record/" onsubmit="return convertJson(this)">
                <input type="hidden" name="id" value="${t.id}"/>
                <input type="hidden" name="resource[id]" id="resourceIds" value="${t.resource.id}"/>
                <input type="hidden" id="currentNode"/>

                <div>
                    <a href="/attendance/record/manual.html" data-pjax="#main-content" class="returnLink">< 返回</a>
                </div>
                <div class="ant-row ant-form-item">
                    <div class="ant-col-2 ant-form-item-label"><label class="">签卡人员</label></div>
                    <div class="ant-col-8">
                        <div class="ant-form-item-control ">
                            <button type="button" class="ant-btn ant-btn-ghost ant-btn-lg" onclick="chooseModal(this,'/hrm/resource/modal/single')" data-index="resourceIds" id="resourcesBtn">
                                 <span>
                                    <c:choose>
                                        <c:when test="${t.resource != null}">
                                            ${t.resource.name}
                                        </c:when>
                                        <c:otherwise>
                                            请选择
                                        </c:otherwise>
                                    </c:choose>
                                 </span>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="ant-row ant-form-item">
                    <div class="ant-col-2 ant-form-item-label"><label class="">签卡日期</label></div>
                    <div class="ant-col-10">
                        <div class="ant-form-item-control ">
                            <input readonly="" value="<fmt:formatDate type="both" value="${t.date}"/>" name="date" placeholder="请选择日期"
                                   class="ant-input datetimepicker" style="width: 160px" id="date">
                        </div>
                    </div>
                </div>
                <div class="ant-row ant-form-item">
                    <div class="ant-col-2 ant-form-item-label"><label class="">签卡原因</label></div>
                    <div class="ant-col-14">
                        <div class="ant-form-item-control ">
                            <textarea name="reason" rows="3" class="ant-input">${t.reason}</textarea>
                        </div>
                    </div>
                </div>
                <div class="ant-row" style="margin: 24px 0px;">
                    <div class="ant-col-16 ant-col-offset-8">
                        <button type="submit" class="ant-btn ant-btn-primary"><span>保存设置</span></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $(function () {

        //初始化日期选择器
        var date = $('#date');
        var time = $('#time');
        date.datetimepicker({
            format: 'yyyy-mm-dd hh:ii:ss',
            autoclose: true,
            startView: 1,
            minView: 0,
            maxView: 3,
            minuteStep: 1,
            language: 'zh-CN',
            todayBtn: true,
            clearBtn:false,
//            minuteStep:1,
            timezone: "中国标准时间",
        });


    });
    //选择人员的回调清空方法
    function resourceClearCallBack(){
        $('#resourcesBtn').text('请选择');
        $('#resourceIds').val('');
    }

</script>
