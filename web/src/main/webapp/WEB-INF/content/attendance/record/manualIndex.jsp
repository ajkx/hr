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
        query: {"type" : "manual"},
        ajax_url: '/attendance/record/list',
        ajax_type: 'GET',
        pageSize: 10,
        height: "auto",
        columnData: [
            {
                key: 'name',
                text: '姓名',
//                width: '120px',
                remind: '',
            },
            {
                key: 'department',
                text: '部门',
//                width: '150px',
                remind: '',
            },
            {
                key: 'workCode',
                text: '工号',
//                width: '120px',
                remind: '',
            },
            {
                key: 'date',
                text: '签卡日期',
//                width: '250px',
                remind: '',
                template:function(date,rowObject){
                    return new Date(date).format('yyyy-MM-dd hh:mm');
                }
            },
            {
                key: 'reason',
                text: '签卡原因',
                width: '200px',
                remind: ''
            },
            {
                key: 'id',
                text: '操作',
                template:function(id,rowObject){
                    var str = "";
                    str = '<a <shiro:lacksPermission name="ManualRecord:update">disable=""</shiro:lacksPermission> class="font-color" href="/attendance/record/'+id+'/update.html"  data-pjax="#main-content">编辑</a>'
                            + '<span class="ant-divider"></span>'
                            +'<a  <shiro:lacksPermission name="ManualRecord:delete">disable=""</shiro:lacksPermission> class="font-color" href="javascript:void(0)" onclick="showDelModal(\'/attendance/record/'+id+'/delete\')">删除</a>';

                    return  str;
                }
            }
        ]
    });

    $(function () {
        $(".table-div").addClass("ant-table");
        $("thead").addClass("ant-table-thead");
        $('tbody').addClass("ant-table-tbody");
//        $("table").addClass("table-bordered");
//        $("table").css("border-collapse", "collapse");


        //初始化日期选择器
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
            timezone: "中国标准时间",
        }).on('changeDate',function(e){
            searchData();
        });
    });

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
            endDate: document.querySelector('[name="endDate"]').value,
            resources: document.querySelector('[name="resources"]').value,
            type: "manual"
        }
        document.querySelector('table').GM('setQuery', query).GM('refreshGrid',true, function () {
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
                     <span class="ant-calendar-picker" style="width: 110px; margin-top: 0px;">
                            <span>
                            <input readonly="" value="${beginDate}" name="beginDate" placeholder="请选择开始日期"
                                   class="ant-input datetimepicker">
                            </span>
                    </span>
                    <span style="margin:0 8px">至</span>
                     <span class="ant-calendar-picker" style="width: 110px; margin-top: 0px;">
                            <span>
                            <input readonly="" value="${endDate}" name="endDate" placeholder="请选择结束日期"
                                   class="ant-input datetimepicker">
                            </span>
                    </span>
                    <input type="hidden" value="" name="resources" id="resourceStr">
                    <span style="margin-left: 50px;">部门/人员：</span>
                    <button type="button" id="resourceBtn" class="ant-btn" onclick="chooseResource('/organization/modal/list','manualRecord');">
                        <span>全公司</span>
                    </button>
                    <button type="button" class="ant-btn ant-btn-primary" style="margin-left: 50px">
                        <span>导出签卡记录</span>
                    </button>
                    <div class="detail-line"></div>
                </div>
            </div>
            <div>
            <a style="font-size: 14px;color:#2db7f5" href="/attendance/record/create.html" data-pjax="#main-content">签卡登记</a>
            </div>
        </div>
    </div>
    <table grid-manager="main"></table>
</div>