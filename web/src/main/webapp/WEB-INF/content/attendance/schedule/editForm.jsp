<%--
  Created by IntelliJ IDEA.
  User: ajkx
  Date: 2017/1/4
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/init.jsp" %>
<c:set var="action" value="${op != '修改' ? 'create' : 'update'}"/>
<fmt:parseNumber var="scope_up" integerOnly="true" value="${t.scope_up/60000}"/>
<fmt:parseNumber var="scope_down" integerOnly="true" value="${t.scope_down/60000}"/>
<fmt:parseNumber var="offsetTime" integerOnly="true" value="${t.offsetTime/60000}"/>

<script>
    function convertJson(node) {
        if (!$("#modal-form").valid()) {
            return;
        }

        var data = $(node).serializeJSON();
        console.log(data);
        switch (data.scheduleType) {
            case "oneSchedule":
                delete data.second_time_up;
                delete data.second_time_down;
                delete data.third_time_up;
                delete data.third_time_down;
                break;
            case "twoSchedule":
                delete data.third_time_up;
                delete data.third_time_down;
                break;
        }
        if (data.punch == "1") {
            data.punch = true;
        } else {
            data.punch = false;
        }

        if($("#rest_check").val() == "1"){
            data.haveRest = true;
        }else{
            data.haveRest = false;
        }

        if (data.acrossDay == "1") {
            data.acrossDay = true;
        } else {
            data.acrossDay = false;
        }
        var url = $(node).attr("action");
        jsonSubmit(url, data, location.href);
        return false;
    }

</script>

<div class="modal-dialog" style="width:700px">
    <div class="modal-content" id="edit-modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <div class="modal-title" id="myModalLabel">
                ${op}班次
            </div>
        </div>
        <form class="form-horizontal" id="modal-form" role="form" action="/attendance/schedule/${action}" method="post"
              onsubmit="return convertJson(this)">
            <div class="modal-body">
                <c:if test="${size > 0}">
                    <div class="ant-alert ant-alert-info" data-show="true">
                        <i class="fa fa-exclamation-triangle"
                           style="color: #2db7f5;margin-right: 8px;font-size: 14px;top: 1px;position: relative;"></i>
                        <span class="ant-alert-message">该班次正在被${size}个考勤组使用, 修改将会影响到考勤组${group}</span>
                        <span class="ant-alert-description"></span>
                    </div>
                </c:if>
                <div class="div-group">
                    <span>班次名称</span>
                    <input type="text" class="form-control u-input"
                           style="width: 140px; margin-right: 20px; margin-left: 10px" name="name"
                           value="${t.name}"/>
                </div>
                <div style="color: rgb(196, 196, 196); margin-top: 10px; margin-bottom: 30px; margin-left: 60px;">
                    最多6个字符（中英文或数字），首个字符会作为班次简称
                </div>
                <input type="hidden" name="id" value="${t.id}"/>
                <input type="hidden" name="scheduleType" id="scheduleType"
                       value="${scheduleType == null ? 'oneSchedule' : t.scheduleType.name() }"/>
                <input type="hidden" name="acrossDay" id="acrossDay" value="${t.acrossDay == true ? 1 : 0}"/>
                <input type="hidden" name="attendanceTime" id="attendanceTime" value="${t.attendanceTime/1000}"/>
                <div class="div-group">
                    <span style="margin-right: 20px;">设置该班次一天内上下班的次数</span>
                    <div class="btn-group">
                        <div class="div-component <c:if test="${scheduleType == null || scheduleType == 0}" >scheduletype</c:if>"
                             style="border-top-right-radius: 0;
border-bottom-right-radius: 0" onclick="setScheduleType('oneSchedule',this)">1天1次上下班
                        </div>
                        <div class="div-component <c:if test="${scheduleType == 1}" >scheduletype</c:if>"
                             style="border-radius: 0;border-left: 0;border-right:0"
                             onclick="setScheduleType('twoSchedule',this)">1天2次上下班
                        </div>
                        <div class="div-component <c:if test="${scheduleType == 2}" >scheduletype</c:if>"
                             style="border-top-left-radius: 0;
border-bottom-left-radius: 0" onclick="setScheduleType('threeSchedule',this)">1天3次上下班
                        </div>
                    </div>
                </div>
                <div style="margin-top: 15px;">
                    <div class="div-group" id="oneSchedule">
                        <div style="width: 285px; display: inline-block;">
                            <span style="width: 200px; margin-right: 20px;">第1次上下班</span>
                            上班:
                            <span class="timepick-group" style="margin-right: 10px; margin-left: 5px;">
                                <input class="form-control u-input timepick timepick1"
                                       value="${fn:substring(t.first_time_up,0,5)}" name="first_time_up"
                                       style="width:100px" readonly id="first_up">
                                <span class="timepick-icon fa fa-clock-o"></span>
                            </span>
                        </div>
                        <div style="width: 275px; display: inline-block;">
                            下班:
                            <span class="timepick-group" style="margin-right: 10px; margin-left: 5px;"><input
                                    class="form-control u-input timepick timepick1"
                                    value="${fn:substring(t.first_time_down,0,5)}" name="first_time_down"
                                    style="width:100px" readonly id="first_down"><span
                                    class="timepick-icon fa fa-clock-o"></span></span>
                        </div>
                    </div>
                    <div class="div-group" id="oneScheduleRest">
                        <span>
                            <label class="ant-checkbox-wrapper">
                                <span class="ant-checkbox <c:if test="${t.haveRest == true}">ant-checkbox-checked</c:if>">
                                    <span class="ant-checkbox-inner"></span>
                                        <input type="checkbox" class="ant-checkbox-input"
                                               data-input="thursday"
                                               data-check="true"
                                               value="<c:choose><c:when test="${t.haveRest == true}">1</c:when><c:otherwise>0</c:otherwise></c:choose>" id="rest_check" name="haveRest"/>
                                </span>
                            </label>
                        </span>
                        <div style="width: 285px; display: inline-block; margin-left: 6px">
                            <span style="width: 200px; margin-right: 20px;">休息时间</span>
                            休息开始:
                            <span class="timepick-group" style="margin-right: 10px; margin-left: 5px;">
                                <input class="form-control u-input timepick timepick2"
                                       value="${fn:substring(t.beginRest,0,5)}" name="beginRest"
                                       style="width:100px" readonly id="begin_rest">
                                <span class="timepick-icon fa fa-clock-o"></span>
                            </span>
                        </div>
                        <div style="width: 275px; display: inline-block;">
                            休息结束:
                            <span class="timepick-group" style="margin-right: 10px; margin-left: 5px;"><input
                                    class="form-control u-input timepick timepick2"
                                    value="${fn:substring(t.endRest,0,5)}" name="endRest"
                                    style="width:100px" readonly id="end_rest"><span
                                    class="timepick-icon fa fa-clock-o"></span></span>
                        </div>
                    </div>
                    <div class="div-group" id="twoSchedule" style="<c:if
                            test="${scheduleType != 1 && scheduleType != 2}">display: none;</c:if>">
                        <div style="width: 285px; display: inline-block;">
                            <span style="width: 200px; margin-right: 20px;">第2次上下班</span>
                            上班:
                            <span class="timepick-group" style="margin-right: 10px; margin-left: 5px;">
                                <input class="form-control u-input timepick timepick1"
                                       value="${fn:substring(t.second_time_up,0,5)}" name="second_time_up"
                                       style="width:100px" readonly id="second_up">
                                <span class="timepick-icon fa fa-clock-o"></span>
                            </span>
                        </div>
                        <div style="width: 275px; display: inline-block;">
                            下班:
                            <span class="timepick-group" style="margin-right: 10px; margin-left: 5px;"><input
                                    class="form-control u-input timepick timepick1"
                                    value="${fn:substring(t.second_time_down,0,5)}" name="second_time_down"
                                    style="width:100px" readonly id="second_down"><span
                                    class="timepick-icon fa fa-clock-o"></span></span>
                        </div>
                    </div>
                    <div class="div-group" id="threeSchedule"
                         style="<c:if test="${scheduleType != 2}">display: none;</c:if>">
                        <div style="width: 285px; display: inline-block;">
                            <span style="width: 200px; margin-right: 20px;">第3次上下班</span>
                            上班:
                            <span class="timepick-group" style="margin-right: 10px; margin-left: 5px;">
                                <input class="form-control u-input timepick timepick1"
                                       value="${fn:substring(t.third_time_up,0,5)}" name="third_time_up"
                                       style="width:100px" readonly id="third_up">
                                <span class="timepick-icon fa fa-clock-o"></span>
                            </span>
                        </div>
                        <div style="width: 275px; display: inline-block;">
                            下班:
                            <span class="timepick-group" style="margin-right: 10px; margin-left: 5px;"><input
                                    class="form-control u-input timepick timepick1"
                                    value="${fn:substring(t.third_time_down,0,5)}" name="third_time_down"
                                    style="width:100px" readonly id="third_down"><span
                                    class="timepick-icon fa fa-clock-o"></span></span>
                        </div>
                    </div>
                </div>
                <div class="div-group">
                    <input type="hidden" name="timecount"/>
                    合计工作时长<span id="timeCount">9小时0分钟</span>
                    <span style="color: rgb(196, 196, 196);">（考勤统计工作时长，会以此时间为准）</span>
                </div>

                <div class="div-group">
                        <span>
                            <label class="ant-checkbox-wrapper">
                                <span class="ant-checkbox <c:if test="${t.punch == true}">ant-checkbox-checked</c:if>">
                                    <span class="ant-checkbox-inner"></span>
                                        <input type="checkbox" class="ant-checkbox-input"
                                               data-input="thursday"
                                               data-check="true"
                                               value="<c:choose><c:when test="${t.punch == true}">1</c:when><c:otherwise>0</c:otherwise></c:choose>" id="punch" name="punch"/>
                                </span>
                            </label>
                        </span>
                <span>下班不用打卡（开启后，下班不打卡也不会记作异常)</span>
                </div>
                <div class="div-group"
                     style="margin-top: 20px; border-top-color: rgb(228, 228, 228); border-top-width: 1px; border-top-style: solid;"></div>
                <div class="div-group" style="color: rgb(148, 148, 148);">弹性时间设置</div>
                <div class="div-group">
                    <span>上班打卡时长(分钟)</span>
                    <input type="number" class="form-control u-input input-number" name="scope_up"
                           style="width:80px; margin-left: 20px" min="0" max="1440"
                           value="${t.scope_up != null ? scope_up : 30}" autocomplete="off"/>
                </div>
                <div class="div-group">
                    <span>下班打卡时长(分钟)</span>

                    <input type="number" class="form-control u-input input-number" name="scope_down"
                           style="width:80px; margin-left: 20px" min="0" max="1440"
                           value="${t.scope_down != null ? scope_down : 30}" autocomplete="off"/>
                </div>
                <div class="div-group">
                    <span>迟到/早退限定时长（超过则记作旷工）</span>
                    <input type="number" class="form-control u-input input-number" name="offsetTime"
                           style="width:80px; margin-left: 20px" min="0" max="1440"
                           value="${t.offsetTime != null ? offsetTime : 30}" autocomplete="off"/>
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
            <div class="modal-footer">
                <button class="u-btn u-btn-lg" data-dismiss="modal" type="button">取 消</button>
                <button class="u-btn u-btn-primary u-btn-lg" type="submit" style="margin-left: 8px">确 定</button>
            </div>
        </form>
    </div>
</div>

<script>
    var input = $('.timepick-group');
    var isPunch = $('#isPunch');

    var first_up = $('#first_up');
    var first_down = $('#first_down');
    var second_up = $('#second_up');
    var second_down = $('#second_down');
    var third_up = $('#third_up');
    var third_down = $('#third_down');

    var beginRest = $('#begin_rest');
    var endRest = $('#end_rest');
    var restCheck = $('#rest_check');
    var punch = $("#punch");
    var scheduleType = $('#scheduleType');
    //改变前的input值
    var beforeTime;
    //合计时间
    var time;
    //初始化clockPicker
    input.clockpicker({
        autoclose: true
    });

    //初始化时间
    if (first_up.val() == "")first_up.val("08:10");
    if (first_down.val() == "")first_down.val("17:40");
    if (second_up.val() == "")second_up.val("12:00");
    if (second_down.val() == "")second_down.val("15:00");
    if (third_up.val() == "")third_up.val("16:00");
    if (third_down.val() == "")third_down.val("18:00");
    if (third_up.val() == "")third_up.val("16:00");
    if (third_down.val() == "")third_down.val("18:00");

    if (beginRest.val() == "")beginRest.val("12:00");
    if (endRest.val() == "")endRest.val("13:30");

    $('#timeCount').text(getTime());


    //是否下班不用打卡设置
    var checkbox = $('.icheckbox');
    checkbox.iCheck({
        checkboxClass: 'icheckbox_flat-blue',
        radioClass: 'iradio_flat-blue'
    });
    <c:if test="${t.punch == true}">
    checkbox.iCheck('check');
    </c:if>
    checkbox.on("ifChanged", function (event) {
        if (event.target.checked) {
            isPunch.val(1);
        } else {
            isPunch.val(0);
        }
    });


    function getNodes(type) {
        var array = new Array();
        array[0] = first_up;
        array[1] = first_down;
        if (type == 'twoSchedule') {
            array[2] = second_up;
            array[3] = second_down;
        } else if (type == 'threeSchedule') {
            array[2] = second_up;
            array[3] = second_down;
            array[4] = third_up;
            array[5] = third_down;
        }
        return array;
    }
    function getTime() {

        var f1 = getAllTime(first_up);
        var f2 = getAllTime(first_down);
        time = f2 - f1;
        if (scheduleType.val() == 'twoSchedule') {
            time += getAllTime(second_down) - getAllTime(second_up);
        } else if (scheduleType.val() == 'threeSchedule') {
            time += getAllTime(second_down) - getAllTime(second_up);
            time += getAllTime(third_down) - getAllTime(third_up);
        }
        var hour = Math.floor(time / 60);
        var min = time % 60;
        $('#attendanceTime').val(time * 60);
        return hour + "小时" + min + "分钟";
    }
    function getAllTime(node) {
        var str = node.val();
        var array = str.split(":");
        if (array[0] == 0) {
            array[0] == 1;
        }
        if (node.attr("data-across") == "1") {
            return array[0] * 60 + parseInt(array[1]) + 1440;
        } else {
            return array[0] * 60 + parseInt(array[1]);
        }

    }
    function checkRepeat(value, node) {
        var array = getNodes(value);
        var currentNode = $(node);
        $(array).each(function (index, element) {
            if (currentNode.attr("id") != element.attr("id") && currentNode.val() == element.val()) {
                currentNode.val(beforeTime);
                toastr.error("选择的时间不可重复！");
                return;
            }
        });
        var flag = checkOrder(array);
        if (!flag) {
            currentNode.val(beforeTime);
            toastr.error("请按照时间顺序设置！");
        } else {
            if(scheduleType.val() == 'oneSchedule' && restCheck.val() == '1'){
                if(first_up.val() > beginRest.val() || endRest.val() > first_down.val()){
                    currentNode.val(beforeTime);
                    toastr.error("请按照时间顺序设置！");
                }else{
                    var f1 = getAllTime(first_up);
                    var f2 = getAllTime(first_down);
                    var r1 = getAllTime(beginRest);
                    var r2 = getAllTime(endRest);
                    var time = (f2 - r2) + (r1 - f1);
                    var hour = Math.floor(time / 60);
                    var min = time % 60;
                    $('#attendanceTime').val(time * 60);
                    $('#timeCount').text(hour + "小时" + min + "分钟");
                }
            }else{
                $('#timeCount').text(getTime());
            }

        }
    }

    function checkRestTime(node){
        var currentNode = $(node);
        if(beginRest.val() >= endRest.val() || first_up.val() > beginRest.val() || endRest.val() > first_down.val()){
            currentNode.val(beforeTime);
            toastr.error("请按照时间顺序设置！");
        }else{
            if(restCheck.val() == "1"){
                var f1 = getAllTime(first_up);
                var f2 = getAllTime(first_down);
                var r1 = getAllTime(beginRest);
                var r2 = getAllTime(endRest);
                var time = (f2 - r2) + (r1 - f1);
                var hour = Math.floor(time / 60);
                var min = time % 60;
                $('#attendanceTime').val(time * 60);
                $('#timeCount').text(hour + "小时" + min + "分钟");
            }else{
                var f1 = getAllTime(first_up);
                var f2 = getAllTime(first_down);
                var time = f2 - f1;
                var hour = Math.floor(time / 60);
                var min = time % 60;
                $('#attendanceTime').val(time * 60);
                $('#timeCount').text(hour + "小时" + min + "分钟");
            }
        }
    }

    function checkOrder(array) {

        var lengths = array.length;
        var acrossArray = new Array();
        for (var i = 0; i < lengths; i++) {
            //判断跨天
            if ($(array[i]).val() > $(array[i + 1]).val()) {
                for (var j = i + 1; j < lengths; j++) {
                    //排除二次跨天
                    if ($(array[j]).val() > $(array[j + 1]).val()) {
                        return false;
                    } else {
                        acrossArray.push($(array[j]));
                    }
                }
            }
        }

        //初始化
        $('.ant-tag-red').remove();
        $("#acrossDay").val(0);
        $(".timepick").attr("data-across", "0");

        for (var i = 0; i < acrossArray.length; i++) {
            var div = $("<div class='ant-tag ant-tag-red'></div>");
            var span = $("<span></span>").text("次日");
            div.append(span);
            acrossArray[i].parent().after(div);
            acrossArray[i].attr("data-across", "1");
            $("#acrossDay").val(1);
        }
        return true;
    }

    function setScheduleType(value, node) {
        var node = $(node);
        if ($('#scheduleType').val() == value)return;
        $('#scheduleType').val(value);
        node.siblings().removeClass("scheduletype");
        var nodes = node.parent().children();
        switch (value) {
            case "oneSchedule":
                nodes.last().css("border-left", "1px solid #ccc");
                $('#oneScheduleRest').css("display","block");
                $('#twoSchedule').css("display", "none");
                $('#threeSchedule').css("display", "none");
                $('#timeCount').text("合计9小时30分钟");
                $('#attendanceTime').val(34200);
                restCheck.val("0");
                restCheck.parent().removeClass(' ant-checkbox-checked');
                first_up.val("08:10");
                first_down.val("17:40");
                beginRest.val("12:00");
                endRest.val("13:30");
                break;
            case "twoSchedule":
                nodes.first().css("border-right", "1px solid #2CB7F5");
                nodes.last().css("border-left", "1px solid #2CB7F5");
                $('#oneScheduleRest').css("display","none");
                $('#twoSchedule').css("display", "block");
                $('#threeSchedule').css("display", "none");
                $('#timeCount').text("合计8小时0分钟");
                first_up.val("08:10");
                first_down.val("12:10");
                second_up.val("13:30");
                second_down.val("17:30");
                $('#attendanceTime').val(28800);
                break;
            case "threeSchedule":
                nodes.first().css("border-right", "1px solid #ccc");
                $('#oneScheduleRest').css("display","none");
                $('#twoSchedule').css("display", "block");
                $('#threeSchedule').css("display", "block");
                $('#timeCount').text("合计10小时0分钟");

                first_up.val("08:10");
                first_down.val("12:10");
                second_up.val("13:30");
                second_down.val("17:30");
                third_up.val("18:30");
                third_down.val("20:30");
                $('#attendanceTime').val(36000);
                break;
        }
        node.addClass("scheduletype");
        $('.ant-tag-red').remove();
        $("#acrossDay").val(0);
        $(".timepick").attr("data-across", "0");
    }

    //判断focu事件，存起改变前的值，用于当有重复的时间还原
    $(".timepick").focus(function (event) {
        beforeTime = $(this).val();
    });
    $(".timepick1").change(function () {
        console.log(scheduleType.val());
        checkRepeat(scheduleType.val(), this);
    });

    $(".timepick2").change(function () {
        checkRestTime(this);
    });
    $(function () {
        //初始化跨天的判断
        first_up.change();

        $("#modal-form").validate({
            rules: {
                name: {
                    required: true,
                    maxlength: 6,
                }
            },
        });
        $(restCheck).click(function () {
            var node = $(this);
            if (node.val() == '1') {
                node.parent().removeClass('ant-checkbox-checked');
                node.val("0");
                checkRestTime(null);
            } else {
                node.parent().addClass('ant-checkbox-checked');
                node.val("1");
                checkRestTime(null);
            }
        });
        $(punch).click(function () {
            var node = $(this);
            if (node.val() == '1') {
                node.parent().removeClass('ant-checkbox-checked');
                node.val("0");
            } else {
                node.parent().addClass('ant-checkbox-checked');
                node.val("1");
            }
        });
    });

</script>