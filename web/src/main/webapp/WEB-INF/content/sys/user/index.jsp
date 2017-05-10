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
        ajax_url: '/sys/user/list',
        ajax_type: 'GET',
        pageSize: 10,
        height:"auto",
        columnData: [
            {
                key : 'name',
                text : '账号名'
            },
            {
                key : 'hrmResource',
                text : '所属员工',
                template:function(hrmResource,rowObject){
                    if(hrmResource == null){return "";}
                    return "<a href='/hrm/hrmresource/"+hrmResource.id+".html' data-pjax='#main-content' class='font-color'>"+hrmResource.name+"</a>";
                }
            },
            {
                key : 'roles',
                text : '所有角色',
                template:function(roles,rowObject) {
                    if(roles == null){return "";}
                    var el = '';
                    $(roles).each(function(index,element) {
                        el += "<a href='javascript:void(0)' onclick='showEditModal(\"/sys/role/"+element.id+"\")' class='font-color'>"+element.name+"</a>&nbsp;&nbsp;"
                    });
                    return el;
                }
            },
            {
                key: 'id',
                text: '操作',
                template:function(id,rowObject){
                    return '<div><a <shiro:lacksPermission name="user:update">disable=""</shiro:lacksPermission> class="font-color" href="javascript:void(0)" onclick="showEditModal(\'/sys/user/'+id+'/update\')">编辑</a><span class="ant-divider"></span><a <shiro:lacksPermission name="user:delete">disable=""</shiro:lacksPermission> class="font-color" href="javascript:void(0)" onclick="showDelModal(\'/sys/user/'+id+'/delete\')">删除</a> </div>';
                }
            }
        ]
    });

    $(function() {
        $(".table-div").addClass("ant-table");
        $("thead").addClass("ant-table-thead");
        $('tbody').addClass("ant-table-tbody");
    });
    function test(){
        var data = {"user":{"name":"aaa","hrmResource":{"id":2212}},"roles":[{"id":1}]};
        $.ajax({
            url: "/sys/user/test",
            type: "POST",
            contentType: 'application/json',
            dataType: "json",
            data: JSON.stringify(data),
            success: function (result) {

            },
            error: function (xhr, status) {
                alert("数据传输错误" + status + ",请联系系统管理员");
            }
        });
    }
</script>
<div class="topic-toolbar">
    <button onclick="test()">test</button>
    <a style="font-size: 14px;color:#2db7f5" href="javascript:void(0)"
       onclick="showEditModal('/sys/user/create')">新增操作员</a>
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
