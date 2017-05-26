<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/17
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/init.jsp" %>


<script>

    $('table[grid-manager="main"]').GM({
        gridManagerName: 'test',
        useDefaultStyle: false,
        supportAjaxPage: true,
        supportDrag: false,
        supportAutoOrder: false,
        supportCheckbox: false,
        disableCache: true,
        supportAdjust: false,
        supportSorting: true,
        isCombSorting: true,
        ajax_url: '/attendance/detail/collect/list',
        ajax_type: 'GET',
        pageSize: 10,
        height: "auto",
        columnData: [
            {
                key: 'name',
                text: '姓名',
                width: '120px',
                remind: '',
            },
            {
                key: 'department',
                text: '部门',
                width: '140px',
                remind: '',
            },
            {
                key: 'workCode',
                text: '工号',
                width: '120px',
                remind: '',
            },
            {
                key: 'should_attendance',
                text: '应出勤天数',
                width: '100px',
                remind: '',
            },
            {
                key: 'actual_attendance',
                text: '实出勤天数',
                width: '100px',
                remind: '',
                template:function(actual_attendance,rowObject){
                    return Math.round(actual_attendance);
                }
            },
            {
                key: 'should_attendance_time',
                text: '应出勤时长',
                width: '100px',
                remind: '',
                template:function(should_attendance_time,rowObject){
                    return Math.floor(should_attendance_time/36000)/100;
                }
            },
            {
                key: 'actual_attendance_time',
                text: '实出勤时长',
                width: '100px',
                remind: '',
                template:function(actual_attendance_time,rowObject){
                    return Math.floor(actual_attendance_time/36000)/100;
                }
            },
            {
                key: 'late_count',
                text: '迟到次数',
                width: '100px',
                remind: '',
            },
            {
                key: 'late_time',
                text: '迟到时长',
                width: '100px',
                remind: '',
                template:function(late_time,rowObject){
                    return Math.floor(late_time/36000)/100;
                }
            },
            {
                key: 'early_count',
                text: '早退次数',
                remind: '',
                width: '100px',
            },
            {
                key: 'early_time',
                text: '早退时长',
                remind: '',
                width: '100px',
                template:function(early_time,rowObject){
                    return Math.floor(early_time/36000)/100;
                }
            },
            {
                key: 'absenteeism_count',
                text: '旷工次数',
                remind: '',
                width: '100px',

            },
            {
                key: 'absenteeism_time',
                text: '旷工时长',
                remind: '',
                width: '100px',
                template:function(absenteeism_time,rowObject){
                    return Math.floor(absenteeism_time/36000)/100;
                }
            },
            {
                key: 'ot_normal',
                text: '平时加班',
                remind: '',
                width: '100px',
                template:function(ot_normal,rowObject){
                    return Math.floor(ot_normal/36000)/100;
                }
            },
            {
                key: 'ot_weekend',
                text: '周末加班',
                remind: '',
                width: '100px',
                template:function(ot_weekend,rowObject){
                    return Math.floor(ot_weekend/36000)/100;
                }
            },
            {
                key: 'ot_festival',
                text: '节日加班',
                remind: '',
                width: '100px',
                template:function(ot_festival,rowObject){
                    return Math.floor(ot_festival/36000)/100;
                }
            },
            {
                key: 'leave_personal',
                text: '事假',
                remind: '',
                width: '100px',
                template:function(leave_personal,rowObject){
                    return Math.floor(leave_personal/36000)/100;
                }
            },
            {
                key: 'leave_rest',
                text: '调休',
                remind: '',
                width: '100px',
                template:function(leave_rest,rowObject){
                    return Math.floor(leave_rest/36000)/100;
                }
            },
            {
                key: 'leave_business',
                text: '出差',
                remind: '',
                width: '100px',
                template:function(leave_business,rowObject){
                    return Math.floor(leave_business/36000)/100;
                }
            }
        ],
        ajax_success: function () {
            $('tr').css("height", "39px");
            $('td').css("padding", "10px 8px");
        }
    });

    $(function () {
        $(".table-div").addClass("ant-table");
        $("table").addClass("table-bordered");
        $("table").css("border-collapse", "collapse");


        //让表头th下边框为1px并且居中
        $('th').css("border-bottom-width", "1px").css("text-align", "center").css("padding", "10px 8px");
        $('th div').css("text-align", "center");
        $('table').css("text-align", "center");
        $("thead").addClass("ant-table-thead");
        $('tbody').addClass("ant-table-tbody");
        $('tr').css("height", "39px");


        //初始化日期选择器
        var datePicker = $('.datetimepicker');
        datePicker.datetimepicker({
            format: 'yyyy-mm',
            autoclose: true,
            startView: 3,
            minView: 3,
            maxView: 3,
            language: 'zh-CN',
            todayBtn: true,
            clearBtn:false,
            timezone: "中国标准时间"
        }).on('changeDate',function(e){
            searchData();
        });
    });

    //人员选择后回调触发搜索
    function resourceCallBack(value,text){
        if(text.length > 10){
            text = text.substring(0, 9) + "....";
        }
        $('#resourceBtn').text(text);
        $('#resourceStr').val(value);
        searchData();

    }

    //选择人员的回调清空方法
    function resourceClearCallBack(){
        $('#resourceBtn').text('全公司');
        $('#resourceStr').val('');
        searchData();
    }


    function searchData(){
        var query = {
            beginDate: document.querySelector('[name="beginDate"]').value,
            resources: document.querySelector('[name="resources"]').value,
        }
        document.querySelector('table').GM('setQuery', query).GM('refreshGrid', true,function () {
            console.log('搜索成功...');
        });
    }
</script>
<div class="topic-toolbar">
</div>
<div>
    <div style="margin-bottom: 18px">
        <div>
            <div class="app-statistics-detail-index-date-member">
                <div class="dtui-date-member">
                    <span>时间：</span>
                     <span class="ant-calendar-picker" style="width: 80px; margin-top: 0px;">
                            <span>
                            <input readonly="" name="beginDate" value="${beginDate}" placeholder="请选择日期"
                                   class="ant-input datetimepicker">
                            </span>
                    </span>
                    <%--<span style="margin:0 8px">至</span>--%>
                     <%--<span class="ant-calendar-picker" style="width: 110px; margin-top: 0px;">--%>
                            <%--<span>--%>
                            <%--<input readonly="" value="${endDate}" placeholder="请选择结束日期"--%>
                                   <%--class="ant-input datetimepicker">--%>
                            <%--</span>--%>
                    <%--</span>--%>
                    <input type="hidden" value="" name="resources" id="resourceStr">
                    <span style="margin-left: 50px;">部门/人员：</span>
                    <button type="button" id="resourceBtn" onclick="chooseResource('/organization/modal/list','attendanceCollect');" class="ant-btn">
                        <span>全公司</span>
                    </button>
                    <div class="detail-line"></div>
                </div>
            </div>
            <div>
                <button type="button" class="ant-btn ant-btn-primary">
                    <span>导出每月汇总表</span>
                </button>
            </div>
        </div>
    </div>
    <table grid-manager="main"></table>
</div>

