<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/22
  Time: 11:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/init.jsp" %>
<script>
    $(function(){
        var search = $('#searchinput');
        search.bind("keydown",function(e){
            if(e.keyCode == "13"){
                searchData();
            }
        });
        var searchBtn = $('#searchbtn');
        searchBtn.click(function(){
            searchData();
        });
    });
    function searchData(){
        var query = {
            name: $('#searchinput').val()
        }
        document.querySelector('table').GM('setQuery', query).GM('refreshGrid',true, function () {
            console.log('搜索成功...');
        });
    }

    $('table[grid-manager="main"]').GM({
        gridManagerName: 'test',
        useDefaultStyle: false,
        supportAjaxPage: true,
        supportDrag: false,
        supportAutoOrder: false,
        supportCheckbox:false,
        disableCache: true,
        supportAdjust: false,
        supportSorting: true,
        isCombSorting: true,
        ajax_url: '/scheduler/job/list',
        ajax_type: 'GET',
        pageSize: 10,
        height:"auto",
        columnData: [
            {
                key : 'jobClass',
                text : '类名'
            },
            {
                key : 'cronExpression',
                text : 'Cron表达式',
            },
            {
                key : 'autoStart',
                text : '自动初始运行',
            },
            {
                key : 'runHistory',
                text : '启用历史记录',
            },
            {
                key : 'description',
                text : '描述',
            },
            {
                key : "stateLabel",
                text : "当前状态"
            },
            {
                key : "previousFireTime",
                text : '上次触发时间',
                template:function(actualDown,rowObject){
                    return actualDown == null ? "" :new Date(actualDown).format('yyyy-MM-dd hh:mm:ss');
                }
            },
            {
                key : "nextFireTime",
                text : '下次触发时间',
                template:function(actualDown,rowObject){
                    return actualDown == null ? "" :new Date(actualDown).format('yyyy-MM-dd hh:mm:ss');
                }
            },
            {
                key : 'id',
                text : '实时控制',
                template:function(id,rowObject) {
                    return '<div>' +
                            '<a <shiro:lacksPermission name="Scheduler:control">disable=""</shiro:lacksPermission> class="font-color" href="javascript:void(0)" onclick="operation(\'resume\',\''+id+'\')">启动</a><span class="ant-divider"></span>'  +
                            '<a <shiro:lacksPermission name="Scheduler:control">disable=""</shiro:lacksPermission> class="font-color" href="javascript:void(0)" onclick="operation(\'pause\',\''+id+'\')">暂停</a><span class="ant-divider"></span>'  +
                            '<a <shiro:lacksPermission name="Scheduler:control">disable=""</shiro:lacksPermission> class="font-color" href="javascript:void(0)" onclick="execute(\''+id+'\')">立即执行</a>' +
                            '</div>';
                }
            },
            {
                key: 'id',
                text: '操作',
                template:function(id,rowObject){
                    return '<div><a <shiro:lacksPermission name="Scheduler:update">disable=""</shiro:lacksPermission> class="font-color" href="javascript:void(0)" onclick="showEditModal(\'/scheduler/job/'+id+'/update\')">编辑</a><span class="ant-divider"></span><a <shiro:lacksPermission name="Scheduler:delete">disable=""</shiro:lacksPermission> class="font-color" href="javascript:void(0)" onclick="showDelModal(\'/scheduler/job/'+id+'/delete\')">删除</a> </div>';
                }
            }
        ]
    });
    function operation(value,id) {
        var str = "";
        if(value == "pause"){
            str = "确认 停止 所选任务？"
        }else if(value == "resume") {
            str = "确认 启动 所选任务？";
        }
        var result = confirm(str);
        if(result){
            $.post("/scheduler/job/state",{"id":id,"state":value},function (result) {
                if(result.status){

                    toastr.success(result.msg);
                    $.pjax({url:location.href,container:'#main-content'});
                }else{
                    toastr.error(result.msg);
                }
            });
        }
    }

    function execute(id) {
        var result = confirm("确认 执行 所选任务?");
        if(result) {
            $.post("/scheduler/job/run?id="+id,function (result) {
                if(result.status){
                    $.pjax({url:location.href,container:'#main-content'});
                    toastr.success(result.msg);
                }else{
                    toastr.error(result.msg);
                }
            });
        }
    }
    $(function() {
        $(".table-div").addClass("ant-table");
        $("thead").addClass("ant-table-thead");
        $('tbody').addClass("ant-table-tbody");
    });
</script>
<div class="topic-toolbar">
    <a style="font-size: 14px;color:#2db7f5" href="javascript:void(0)"
       onclick="showEditModal('/scheduler/job/create')">新增计划任务</a>
    <div class="clearfix" style="clear: both"></div>
</div>
<div>
    <table grid-manager="main"></table>
</div>
