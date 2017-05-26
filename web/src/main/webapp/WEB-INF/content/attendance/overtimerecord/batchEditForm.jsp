<%--
  Created by IntelliJ IDEA.
  User: ajkx
  Date: 2017/2/17
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/init.jsp" %>
<c:set var="action" value="${op != '修改' ? 'create' : 'update'}"/>
<c:set var="record" value="${t}"/>
<c:set var="type" value="${t.type.name()}"/>

<div style="margin-top: 15px; width: 1150px; background: rgb(255, 255, 255);">
    <div>
        <div class="" style="margin: 20px 0px 30px; opacity: 1; visibility: visible; transform: translateX(0px);">
            <form class="ant-form ant-form-horizontal" action="/attendance/overtimerecord/create/batch" onsubmit="return submitFormByPage(this,location.href)">
                <input type="hidden" name="type" id="recordType" value="${type == null ? 'ot_normal' : type}"/>
                <input type="hidden" name="resources" id="resourceIds" value="${record.resource.id}"/>
                <input type="hidden" id="currentNode"/>

                <div>
                    <a href="/attendance/overtimerecord.html" data-pjax="#main-content" class="returnLink">< 返回</a>
                </div>
                <div class="ant-row ant-form-item">
                    <div class="ant-col-2 ant-form-item-label"><label class="">加班人员</label></div>
                    <div class="ant-col-8">
                        <div class="ant-form-item-control ">
                            <button type="button" class="ant-btn ant-btn-ghost ant-btn-lg" onclick="chooseModal(this,'/hrm/resource/modal/multi')" data-index="resourceIds" id="resourcesBtn">
                                 <span>
                                    <c:choose>
                                        <c:when test="${record.resource != null}">
                                            ${record.resource.name}
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
                    <div class="ant-col-2 ant-form-item-label"><label class="">加班类型</label></div>
                    <div class="ant-col-18">
                        <div class="ant-form-item-control ">
                            <div class="ant-radio-group ant-radio-group-large">
                                <label style="font-weight: 500;" class="ant-radio-wrapper ant-radio-wrapper-checked">
                                    <span class="ant-radio <c:if test="${type == null || type == 'ot_normal'}">ant-radio-checked</c:if> ">
                                        <span class="ant-radio-inner"></span>
                                        <input type="radio" class="ant-radio-input" data-type="ot_normal" value="on">
                                    </span>
                                    <span>平时加班</span>
                                </label>
                                <label style="font-weight: 500;" class="ant-radio-wrapper">
                                    <span class="ant-radio <c:if test="${type == 'ot_weekend'}">ant-radio-checked</c:if>">
                                        <span class="ant-radio-inner"></span>
                                        <input type="radio" class="ant-radio-input" data-type="ot_weekend" value="on">
                                    </span><span>周末加班</span>
                                </label>
                                <label style="font-weight: 500;" class="ant-radio-wrapper">
                                    <span class="ant-radio <c:if test="${type == 'ot_festival'}">ant-radio-checked</c:if>">
                                        <span class="ant-radio-inner"></span>
                                        <input type="radio" class="ant-radio-input" data-type="ot_festival" value="on">
                                    </span>
                                    <span>节日加班</span>
                                </label>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="ant-row ant-form-item">
                    <div class="ant-col-2 ant-form-item-label"><label class="">加班开始时间</label></div>
                    <div class="ant-col-10">
                        <div class="ant-form-item-control ">
                            <input readonly="" value="${fn:substring(record.date.toString(),0,16)}" name="date" placeholder="请选择开始时间"
                                   class="ant-input datetimepicker" style="width: 200px" id="beginDate">
                        </div>
                    </div>
                </div>
                <div class="ant-row ant-form-item">
                    <div class="ant-col-2 ant-form-item-label"><label class="">加班结束时间</label></div>
                    <div class="ant-col-10">
                        <div class="ant-form-item-control ">
                            <input readonly="" value="${fn:substring(record.endDate.toString(),0,16)}" name="endDate" placeholder="请选择结束时间"
                                   class="ant-input datetimepicker" style="width: 200px" id="endDate">
                        </div>
                    </div>
                </div>
                <div class="ant-row ant-form-item">
                    合计加班时长<span id="timeCount">0小时0分钟</span>
                    <span style="color: rgb(196, 196, 196);">（统计加班时长，会以此时间为准）</span>
                </div>
                <div class="ant-row ant-form-item">
                    <div class="ant-col-2 ant-form-item-label"><label class="">加班原因</label></div>
                    <div class="ant-col-14">
                        <div class="ant-form-item-control ">
                            <textarea name="reason" rows="3" class="ant-input">${record.reason}</textarea>
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
        $('input[type="radio"]').click(function () {
            var type = $(this).attr("data-type");
            var recordType = $('#recordType');
            if (recordType.val() == type) {
                return;
            }
            $('.ant-radio-checked').removeClass('ant-radio-checked');
            $(this).parent().addClass('ant-radio-checked');
            recordType.val(type);
            switch (type) {
                case "ot_normal":
                    $('.type1').css("display", "block");
                    $('.type2').css("display", "none");
                    break;
                case "ot_weekend":
                    $('.type1').css("display", "none");
                    $('.type2').css("display", "block");
                    break;
                case "ot_festival":
                    $('.type1').css("display", "none");
                    $('.type2').css("display", "none");
                    break;
            }
        });

        //初始化日期选择器
        var datePicker = $('.datetimepicker');
        datePicker.datetimepicker({
            format: 'yyyy-mm-dd hh:ii',
            autoclose: true,
            startView: 1,
            minView: 0,
            maxView: 4,
            language: 'zh-CN',
            todayBtn: true,
            clearBtn:false,
//            minuteStep:1,
            timezone: "中国标准时间",
        }).on('changeDate',function(e){
            checkRule(this);
        });
        datePicker.focus(function(){
            beforeTime = $(this).val();
        });

        //初始计算合计时间
        setTime($('#beginDate').val(),$('#endDate').val())
    });

    var beforeTime;
    function checkRule(node){
        var currentNode = $(node);
        var beginDate = $('#beginDate');
        var endDate = $('#endDate');
        //判断是否为空
        if(beginDate.val() == "" || endDate.val() == ""){
            return;
        }
        //判断是否重复
        if(currentNode.attr("id") == beginDate.attr("id") && currentNode.val() == endDate.val()){
            currentNode.val(beforeTime);
            currentNode.datetimepicker('update');
            toastr.error("选择的时间不可重复！");
            return;
        }else if(currentNode.attr("id") == endDate.attr("id") && currentNode.val() == beginDate.val()){
            currentNode.val(beforeTime);
            currentNode.datetimepicker('update');
            toastr.error("选择的时间不可重复！");
            return;
        }

        //判断时间顺序
        if(beginDate.val() >= endDate.val()){
            currentNode.val(beforeTime);
            currentNode.datetimepicker('update');
            toastr.error("请按照时间顺序选择！");
            return;
        }
        //设置合计时间
        setTime(beginDate.val(),endDate.val())
    }

    function setTime(beginTime1,endTime1){
        var hour = 0;
        var min = 0;
        var minute;
        if(beginTime1 != "" && endTime1 != ""){
            var beginTime = CastStringToDate(beginTime1).getTime();
            var endTime = CastStringToDate(endTime1).getTime();
            minute = Math.floor((endTime - beginTime)/60000);
            hour = Math.floor(minute/60);
            min = minute%60;
        }
        var text = hour + "小时"+min+"分钟";
        $("#timeCount").text(text);
    }
    //选择人员的回调清空方法
    function resourceClearCallBack(){
        $('#resourcesBtn').text('请选择');
        $('#resourceIds').val('');
    }
</script>
