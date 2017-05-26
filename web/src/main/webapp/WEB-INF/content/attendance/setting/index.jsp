<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/28
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/init.jsp" %>
<c:set var="calculateType" value="${overTime.calculateType.ordinal()}"/>
<div class="topic-toolbar">
    <ul id="tab1" class="nav nav-tabs">
        <li>
            <a href="#overtime" data-toggle="tab">
                加班设置
            </a>
        </li>
    </ul>
</div>

<div>
    <div class="tab-pane fade in active" id="overtime">
        <form class="ant-form ant-form-horizontal" id="overTimeForm" action="/attendance/setting/overtime" onsubmit="">
            <input type="hidden" id="calculateType" name="calculateType" value="${calculateType}"/>
            <div class="ant-row ant-form-item">
                <div class="ant-col-2 ant-form-item-label"><label class="">加班时数计算方式</label></div>
                <div class="ant-col-18">
                    <div class="ant-form-item-control ">
                        <div class="ant-radio-group ant-radio-group-large">
                            <label style="font-weight: 500;" class="ant-radio-wrapper ant-radio-wrapper-checked">
                                    <span class="ant-radio <c:if test="${calculateType == null || calculateType == 0}">ant-radio-checked</c:if> ">
                                        <span class="ant-radio-inner"></span>
                                        <input type="radio" class="ant-radio-input" data-type="0" value="on">
                                    </span>
                                <span>按登记时间计算</span>
                            </label>
                            <label style="font-weight: 500;" class="ant-radio-wrapper">
                                    <span class="ant-radio <c:if test="${calculateType == 1}">ant-radio-checked</c:if>">
                                        <span class="ant-radio-inner"></span>
                                        <input type="radio" class="ant-radio-input" data-type="1" value="on">
                                    </span><span>按刷卡时间计算</span>
                            </label>
                        </div>
                    </div>
                </div>
            </div>

            <fmt:parseNumber var="offsetTimeUp" type="number" value="${overTime.offsetTimeUp != null ? overTime.offsetTimeUp/60000 : 30}"/>
            <fmt:parseNumber var="offsetTimeDown" type="number" value="${overTime.offsetTimeDown != null ? overTime.offsetTimeDown/60000 : 30}"/>
            <div class="ant-row ant-form-item">
                <div class="ant-col-2 ant-form-item-label"><label class="">上班提前范围(分)</label></div>
                <div class="ant-col-3">
                    <input type="number" class="form-control u-input input-number" name="offsetTimeUp"
                           style="width:80px;" min="0" max="1440" value="${offsetTimeUp}" autocomplete="off"/>

                </div>
            </div>

            <div class="ant-row ant-form-item">
                <div class="ant-col-2 ant-form-item-label"><label class="">下班延后范围(分)</label></div>
                <div class="ant-col-3">
                    <input type="number" class="form-control u-input input-number" name="offsetTimeDown"
                           style="width:80px;" min="0" max="1440" value="${offsetTimeDown}" autocomplete="off"/>

                </div>
            </div>

            <div class="ant-row" style="margin: 24px 0px;">
                <div class="ant-col-16 ant-col-offset-2">
                    <button type="submit" class="ant-btn ant-btn-primary"><span>保存设置</span></button>
                </div>
            </div>
        </form>
    </div>
</div>

<script>
    $(function(){
        $('input[type="radio"]').click(function () {
            var type = $(this).attr("data-type");
            var calculateType = $('#calculateType');
            if (calculateType.val() == type) {
                return;
            }
            $('.ant-radio-checked').removeClass('ant-radio-checked');
            $(this).parent().addClass('ant-radio-checked');
            calculateType.val(type);
        });

        $('#overTimeForm').validate({
//            errorElement: "span",
//            errorClass: "error",
            submitHandler: function (form) {
                console.log("validate success!");
                ajaxSubmitPost(form,function(result){
                    if(result.status){
                        toastr.success("更新成功");
                    }else{
                        toastr.error("执行错误!");
                    }
                });

            },
            rules: {
                offsetTimeUp: {
                    required: true,
                    digits: true
                },
                offsetTimeDown: {
                    required: true,
                    digits: true
                }
            },
        });
    });
</script>