<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/2/7
  Time: 8:25
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
        document.querySelector('table').GM('setQuery', query).GM('refreshGrid', function () {
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
        query:{'order':'asc','status':'0,1,2,3'},
        ajax_url: '/hrm/resource/list',
        ajax_type: 'GET',
        pageSize: 10,
        height:"auto",
        columnData: [
            {
                key: 'name',
                text: '姓名',
                template: function(name,rowObject) {
                    return "<a href='/hrm/resource/"+rowObject.id+".html' data-pjax='#main-content' class='font-color'>"+name+"</a>";
                }
            },
            {
                key: 'workCode',
                text: '工号',
            },
            {
                key: 'subCompany',
                text: '分部',
            },
            {
                key: 'department',
                text: '部门',
            },
            {
                key: 'manager',
                text: '直接上级',
            },
            {
                key: 'jobPosition',
                text: '岗位',
            },
            {
                key: 'status',
                text: '状态',
            },
            {
                key: 'mobile',
                text: '移动电话',
            },
            {
                key: 'email',
                text: '电子邮件',
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
    <%--<a style="font-size: 14px;color:#2db7f5" href="javascript:void(0)"--%>
       <%--onclick="showEditModal('/resource/edit')">新增员工</a>--%>
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

<div id="test1">

</div>
