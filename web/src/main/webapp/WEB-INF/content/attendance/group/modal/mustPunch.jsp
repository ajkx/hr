<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/20
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../../common/init.jsp" %>
<script>
    $(function(){
        var schedule = $("#scheduleInfo");
        //控制已选择的班次的默认显示
        var nodestr = $('#currentNode').val();
        var currentNode = $("#mustPunch"+nodestr);
        //编辑时数据初始化
        if(currentNode.length > 0){
            var scheduleId = currentNode.find(".punchSchedule").attr("data-id");
            var date = currentNode.find(".punchDate").text();
            var node = $('input[data-id="' + scheduleId + '"]');
            node.parent().addClass("ant-radio-checked");
            schedule.attr("data-id", node.attr("data-id"));
            schedule.attr("data-name", node.attr("data-name"));
            $("#punchDate").val(date);
        }

        $('.modal-body input[type="radio"]').click(function(){
            var id = $(this).attr("data-id");
            var name = $(this).attr("data-name");
            if(schedule.attr("data-id") == id) {
                return;
            }
            $('.modal-body .ant-radio-checked').removeClass("ant-radio-checked");
            $(this).parent().addClass('ant-radio-checked');
            schedule.attr("data-id", $(this).attr("data-id"));
            schedule.attr("data-name", $(this).attr("data-name"));
        });
    });

    function setData(){
        var nodestr = $('#currentNode').val();
        var currentNode = $("#mustPunch"+nodestr);
        var schedule = $("#scheduleInfo");
        var date = $('#punchDate');

        if(date.val() == ""){
            toastr.warning("请选择日期!");
            return;
        }
        if(schedule.attr("data-id") == ""){
            toastr.warning("请选择班次!");
            return;
        }

        if(currentNode.length > 0){
            currentNode.find(".punchDate").text(date.val());
            currentNode.find(".punchSchedule").text(schedule.attr("data-name"));
            currentNode.find(".punchSchedule").attr("data-id",schedule.attr("data-id"));
        }else{

            var punchDate = $(".punchDate");
            punchDate.each(function(index,element){
                if($(element).text() == $("#punchDate").val()){
                    toastr.warning("不可重复选择日期!");
                    $("#punchDate").val("");
                    return;
                }
            });

            var index = $("#mustId").val();
            var tr = $("<tr id='mustPunch"+index+"'></tr>");
            var td1 = $("<td class='punchDate'></td>").text(date.val());
            var td2 = $("<td class='punchSchedule' data-id='"+schedule.attr("data-id")+"'></td>").text(schedule.attr("data-name"));
            var td3 = $("<td></td>");
            var span = $("<span></span>");
            var a1 = $('<a href="javascript:void(0)" onclick="chooseModal(this,\'/attendance/group/modal/punch\')" data-index="'+index+'"></a>').text("编辑");
            var span2 = $("<span class='ant-divider'></span>");
            var a2 = $('<a href="javascript:void(0)" onclick="deleteRow(this)"></a>').text("删除");
            span.append(a1);
            span.append(span2);
            span.append(a2);
            td3.append(span);
            tr.append(td1);
            tr.append(td2);
            tr.append(td3);
            $("#must_table").append(tr);
            $("#mustId").val(parseInt(index)+1);
        }
        $('#edit-modal').modal('hide');

    }
</script>
<div class="modal-dialog" style="width:520px">
    <div class="modal-content" id="schedule-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h5 class="modal-title" id="myModalLabel">
                选择必须打卡日期
            </h5>
        </div>
        <div class="modal-body">
            <input type="hidden" value="" id="scheduleInfo" data-id="" data-name=""/>
            <div style="margin-bottom: 15px">
                <span class="ant-calendar-picker" style="width: 80px; margin-top: 0px;">
                            <span>
                            <input readonly="" name="date" value="" placeholder="请选择日期"
                                   class="ant-input datetimepicker" id="punchDate" style="width: 100px">
                            </span>
                    </span>
            </div>
            <div style="margin: 15px 0px 30px; background: rgb(255, 255, 255);">
                <div>
                    <div class="" style="margin-bottom: -40px; margin-top: -15px; opacity: 1; visibility: visible; transform: translateX(0px);">
                        <div></div>
                        <div class=" clearfix">
                            <div class="">
                                <div class="ant-spin"><span class="ant-spin-dot"></span>
                                    <div class="ant-spin-text">加载中...</div>
                                </div>
                                <div class="ant-spin-container">
                                    <div class="ant-table ant-table-large ant-table-scroll-position-left">
                                        <div class="ant-table-content">
                                            <div class="">
                                                <span>
                                                    <div class="ant-table-body">
                                                        <table class="">
                                                            <colgroup>
                                                                <col>
                                                                <col style="width: 160px; min-width: 160px;"><col>
                                                            </colgroup>
                                                            <thead class="ant-table-thead">
                                                                <tr>
                                                                    <th class="ant-table-selection-column">
                                                                        <span></span>
                                                                    </th>
                                                                    <th class="">
                                                                        <span>班次名称</span>
                                                                    </th>
                                                                    <th class="">
                                                                        <span>考勤时间</span>
                                                                    </th>
                                                                </tr>
                                                            </thead>
                                                            <tbody class="ant-table-tbody">
                                                                <c:forEach items="${list}" var="schedule" varStatus="current">
                                                                 <tr class="ant-table-row  ant-table-row-level-0">
                                                                    <td class="ant-table-selection-column">
                                                                        <span>
                                                                            <label class="ant-radio-wrapper ant-radio-wrapper-checked">
                                                                                <span class="ant-radio">
                                                                                    <span class="ant-radio-inner"></span>
                                                                                    <input class="ant-radio-input" value="" data-id="${schedule.id}" data-name="${schedule.name}:${schedule.time}" type="radio">
                                                                                </span>
                                                                            </label>
                                                                        </span>
                                                                    </td>
                                                                    <td class="">
                                                                        <span class="ant-table-row-indent indent-level-0" style="padding-left: 0px;">
                                                                        </span>${schedule.name}
                                                                    </td>
                                                                    <td class="">${schedule.time}</td>
                                                                </tr>
                                                                </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div></div>
        </div>
        <div class="modal-footer">
            <button class="u-btn u-btn-lg" data-dismiss="modal" type="button">取 消</button>
            <button class="u-btn u-btn-primary u-btn-lg" onclick="setData()" style="margin-left: 8px">确 定</button>
        </div>
    </div>
</div>
<script>
    var datePicker = $('.datetimepicker');
    datePicker.datetimepicker({
        format: 'yyyy-mm-dd',
        autoclose: true,
        startView: 2,
        minView: 2,
        maxView: 4,
        language: 'zh-CN',
        todayBtn: true,
        clearBtn:false,
        timezone: "中国标准时间"
    }).on('changeDate',function(e){
        checkRepeat();
    });
    function checkRepeat(){
        var punchDate = $(".punchDate");
        punchDate.each(function(index,element){
            if($(element).text() == $("#punchDate").val()){
                toastr.warning("不可重复选择日期!");
                $("#punchDate").val("");
                return false;
            }
        });
    }
</script>















