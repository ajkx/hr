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
            name: document.querySelector('[name="name"]').value
        }
        console.log(query);
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
        ajax_url: '/attendance/schedule/list',
        ajax_type: 'GET',
        pageSize: 10,
        height:"auto",
        columnData: [
            {
                key : 'name',
                text : '班次名称'
            },
            {
                key : 'time',
                text : '考勤时间',
            },
            {
                key : 'totalTime',
                text : '合计时间'
            },
            {
                key : 'description',
                text : '描述',
            },
            {
                key: 'id',
                text: '操作',
                template:function(id,rowObject){
                    return '<div><a <shiro:lacksPermission name="AttendanceSchedule:update">disable=""</shiro:lacksPermission> class="font-color" href="javascript:void(0)" onclick="showEditModal(\'/attendance/schedule/'+id+'/update\')">编辑</a><span class="ant-divider"></span><a <shiro:lacksPermission name="AttendanceSchedule:delete">disable=""</shiro:lacksPermission> class="font-color" href="javascript:void(0)" onclick="showDelModal(\'/attendance/schedule/'+id+'/delete\')">删除</a> </div>';
                }
            }
        ]
    });

    $(function() {
        $(".table-div").addClass("ant-table");
        $("thead").addClass("ant-table-thead");
        $('tbody').addClass("ant-table-tbody");
    });
</script>
<div class="topic-toolbar">
    <a style="font-size: 14px;color:#2db7f5" href="javascript:void(0)"
       onclick="showEditModal('/attendance/schedule/create')">新增班次</a>
    <div class="ant-search-input-wrapper" style="width: 200px; float: right;margin-bottom: 10px">
        <span class="ant-input-group ant-search-input">
            <div class="ant-select ant-select-combobox ant-select-enabled">
                <div class="ant-select-selection ant-select-selection--single" role="combobox"
                     aria-autocomplete="list" aria-haspopup="true" aria-expanded="false">
                    <div class="ant-select-selection__rendered">
                        <ul>
                            <li class="ant-select-search ant-select-search--inline">
                                <div class="ant-select-search__field__wrap">
                                    <input placeholder="请输入名称" class="ant-select-search__field"
                                           id="searchinput" name="name" autocomplete="off">
                                    <span class="ant-select-search__field__mirror">
                                    </span>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="ant-input-group-wrap">
                <button type="button" id="searchbtn" class="ant-btn ant-search-btn" style="margin-top: 0px;">
                    <i class="fa fa-search" style="margin-top: 0px;">
                    </i>
                </button>
            </div>
        </span>
    </div>
    <div class="clearfix" style="clear: both"></div>
</div>
<div>
    <table grid-manager="main"></table>
</div>
