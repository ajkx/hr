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
        //初始化已有的班次
        var nodeStr = $('#currentNode').val();
        var currentList = $('#'+nodeStr).val();
        if(currentList != ""){
            var list = currentList.split(",")
            for(var i = 0; i < list.length; i++) {
                if(list[i] != ""){
                    var node = $("#tbody input[data-id='"+list[i]+"']");
                    if(node != null) {
                        node.val("on");
                        node.parent().addClass("ant-checkbox-checked");
                    }
                }
            }
        }

        //初始化checkbox的点击事件
        $("#tbody .ant-checkbox").click(function(){
            var node = $(this);
           if(node.hasClass("ant-checkbox-checked")){
               node.removeClass("ant-checkbox-checked");
               node.children("input").val("off");
           }else{
               node.addClass("ant-checkbox-checked");
               node.children("input").val("on");
           }
        });
    });
    function addScheduleTag(){
        var checks = $("#tbody input[value='on']");
        var nodeStr = $('#currentNode').val();
        var currentButton = $("button[data-index='"+nodeStr+"']");
        var currentDiv = currentButton.siblings("div");
        currentDiv.empty();
        var value = "";
        var i = 1;
        checks.each(function(index,element) {
            if(i == 5)i = 1;
            var div;
            switch (i){
                case 1:
                    div = $("<div class='ant-tag ant-tag-blue'></div>");
                    break;
                case 2:
                    div = $("<div class='ant-tag ant-tag-red'></div>");
                    break;
                case 3:
                    div = $("<div class='ant-tag ant-tag-green'></div>");
                    break;
                case 4:
                    div = $("<div class='ant-tag ant-tag-yellow'></div>");
                    break;

            }
            var span = $("<span class='ant-tag-text'></span>").text($(element).attr("data-name"));
            value += $(element).attr("data-id") + ",";
            div.append(span);
            currentDiv.append(div);
            i++;
        });
        value = value.substring(0, value.length - 1);
        $("#" + nodeStr).val(value);
        $("#edit-modal").modal('hide');
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
                                                            <tbody class="ant-table-tbody" id="tbody">
                                                                <c:forEach items="${list}" var="schedule" varStatus="current">
                                                                 <tr class="ant-table-row  ant-table-row-level-0">
                                                                    <td class="ant-table-selection-column">
                                                                        <span>
                                                                            <label class="ant-checkbox-wrapper">
                                                                                <span class="ant-checkbox">
                                                                                    <span class="ant-checkbox-inner"></span>
                                                                                    <input class="ant-checkbox-input" value="" data-id="${schedule.id}" data-name="${schedule.name}:${schedule.time}" type="checkbox" value="off">
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
            <button class="u-btn u-btn-primary u-btn-lg" onclick="addScheduleTag()" style="margin-left: 8px">确 定</button>
        </div>
    </div>
</div>















