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
        //控制已选择的班次的默认显示
        var nodestr = $('#currentNode').val();
        var currentNode = $("#notPunch"+nodestr);
        //编辑时数据初始化
        if(currentNode.length > 0){
            var date = currentNode.find(".notDate").text();
            $("#notDate").val(date);
        }
    });

    function setData(){
        var nodestr = $('#currentNode').val();
        var currentNode = $("#notPunch"+nodestr);
        var date = $('#notDate');

        if(date.val() == ""){
            toastr.warning("请选择日期!");
            return;
        }

        if(currentNode.length > 0){
            currentNode.find(".notDate").text(date.val());
        }else{

            var punchDate = $(".notDate");
            punchDate.each(function(index,element){
                if($(element).text() == $("#notDate").val()){
                    toastr.warning("不可重复选择日期!");
                    $("#notDate").val("");
                    return;
                }
            });

            var index = $("#notId").val();
            var tr = $("<tr id=notPunch"+index+"'></tr>");
            var td1 = $("<td class='notDate'></td>").text(date.val());
            var td2 = $("<td></td>").text("休息");
            var td3 = $("<td></td>");
            var span = $("<span></span>");
            var a = $('<a href="javascript:void(0)" onclick="deleteRow(this)"></a>').text("删除");
            span.append(a);
            td3.append(span);
            tr.append(td1);
            tr.append(td2);
            tr.append(td3);
            $("#not_table").append(tr);
            $("#notId").val(parseInt(index)+1);
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
                选择不用打卡日期
            </h5>
        </div>
        <div class="modal-body">
            <input type="hidden" value="" id="scheduleInfo" data-id="" data-name=""/>
            <div style="margin-bottom: 15px">
                <span class="ant-calendar-picker" style="width: 80px; margin-top: 0px;">
                            <span>
                            <input readonly="" name="date" value="" placeholder="请选择日期"
                                   class="ant-input datetimepicker" id="notDate" style="width: 100px">
                            </span>
                    </span>
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
        var punchDate = $(".notDate");
        punchDate.each(function(index,element){
            if($(element).text() == $("#notDate").val()){
                toastr.warning("不可重复选择日期!");
                $("#notDate").val("");
                return false;
            }
        });
    }
</script>















