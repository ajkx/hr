<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/20
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/init.jsp" %>
<script>
    $(function(){
        var schedule = $("#scheduleInfo");
        //控制已选择的班次的默认显示
        var nodestr = $('#currentNode').val();
        var currentId;
        if(nodestr == "all"){
            currentId = $("#currentSchedule").attr("data-id");
        }else{
            currentId = $('#'+nodestr).val();
        }
        if(currentId != ""){
            var node = $('input[data-id="' + currentId + '"]');
            node.parent().addClass("ant-radio-checked");
            schedule.attr("data-id", node.attr("data-id"));
            schedule.attr("data-name", node.attr("data-name"));
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
    function setSchedule(){
        var nodestr = $('#currentNode').val();
        var schedule = $("#scheduleInfo");
        var temp = $('#currentSchedule');
        $('#edit-modal').modal('hide');
        if(schedule.attr("data-id") == "")return;
        if(nodestr == "all"){
            temp.attr('data-id', schedule.attr("data-id"));
            temp.text(schedule.attr("data-name"));
            $("#checkAll").val("off");
            $('#checkAll').click();
        }else{
            $('#' + nodestr).val(schedule.attr("data-id"));
            var checkbox = $("input[data-input='"+nodestr+"']");
            checkbox.parent().addClass('ant-checkbox-checked');
            checkbox.val("on");
            checkbox.parents(".ant-table-selection-column").siblings(".scheduleName").text(schedule.attr("data-name"));
            if(temp.attr("data-id") == ""){
                temp.attr("data-id",schedule.attr("data-id"));
                temp.text(schedule.attr("data-name"));
            }
        }

    }
</script>
<div class="modal-dialog" style="width:520px">
    <div class="modal-content" id="schedule-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                &times;
            </button>
            <h5 class="modal-title" id="myModalLabel">
                选择班次
            </h5>
        </div>
        <div class="modal-body">
            <input type="hidden" value="" id="scheduleInfo" data-id="" data-name=""/>
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
            <button class="u-btn u-btn-primary u-btn-lg" onclick="setSchedule()" style="margin-left: 8px">确 定</button>
        </div>
    </div>
</div>















