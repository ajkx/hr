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
        ajax_url: '/attendance/overtimerecord/list',
        ajax_type: 'GET',
        pageSize: 10,
        height: "auto",
        columnData: [
            {
                key: 'name',
                text: '姓名',
                width: '100px',
                remind: '',
            },
            {
                key: 'department',
                text: '部门',
                width: '120px',
                remind: '',
            },
            {
                key: 'type',
                text: '加班类型',
                width: '120px',
                remind: '',
            },
            {
                key: 'beginDate',
                text: '加班开始时间',
                width: '150px',
                remind: '',
                template:function(beginDate,rowObject){
                    return beginDate == null ? "" :new Date(beginDate).format('yyyy-MM-dd hh:mm');
                }
            },
            {
                key: 'endDate',
                text: '加班结束时间',
                width: '150px',
                remind: '',
                template:function(endDate,rowObject){
                    return endDate == null ? "" :new Date(endDate).format('yyyy-MM-dd hh:mm');
                }
            },
            {
                key: 'actualUp',
                text: '上班打卡',
                width: '150px',
                remind: '',
                template:function(actualUp,rowObject){
                    return actualUp == null ? "" :new Date(actualUp).format('yyyy-MM-dd hh:mm:ss');
                }
            },
            {
                key: 'actualDown',
                text: '下班打卡',
                width: '150px',
                remind: '',
                template:function(actualDown,rowObject){
                    return actualDown == null ? "" :new Date(actualDown).format('yyyy-MM-dd hh:mm:ss');
                }
            },
            {
                key: 'sum',
                text: '申请时数',
                width: '80px',
                remind: '',
                template:function(sum,rowObject){
                    var hour = Math.floor(sum/3600000*100);
                    return hour/100;
                }
            },
            {
                key: 'actualSum',
                text: '实际时数',
                width: '80px',
                remind: '',
                template:function(actualSum,rowObject){
                    var hour = Math.floor(actualSum/3600000*100);
                    return hour/100;
                }
            },
            {
                key: 'reason',
                text: '加班原因',
                width: '200px',
                remind: '',
            },
            {
                key: 'status',
                text: '状态',
                width: '80px',
                remind: '',
                template:function(status,rowObject){
                    return status == "异常"
                            ?
                    "<span class='updateButton'" +
                    " data-toggle='popover'" +
                    " data-content='<button class=\"ant-btn ant-btn-primary\" data-id=\""+rowObject.id+"\" >改为正常</button>'" +
                    ">"+status+"</span>"
                    : status;
                }
            },
            {
                key: 'remark',
                text: '备注',
                remind: '',
                width: '140px',
                template:function(remark,rowObject){
                    return remark == null ? "" : remark;
              }
            },
            {
                key: 'id',
                text: '操作',
                template:function(id,rowObject){
                    var str = "";
                    if(rowObject.status == "完成" || rowObject.status == "异常"){
                        str = '<a disable="" class="font-color" href="#"  data-pjax="#main-content">编辑</a>'
                        + '<span class="ant-divider"></span>'
                        +'<a  disable="" class="font-color" href="javascript:void(0)">删除</a>';
                    }else if(rowObject.status == "待计算"){
                        str = '<a <shiro:lacksPermission name="OverTimeRecord:update">disable=""</shiro:lacksPermission> class="font-color" href="/attendance/overtimerecord/'+id+'/update.html"  data-pjax="#main-content">编辑</a>'
                                + '<span class="ant-divider"></span>'
                                +'<a  <shiro:lacksPermission name="OverTimeRecord:update">disable=""</shiro:lacksPermission> class="font-color" href="javascript:void(0)" onclick="showDelModal(\'/attendance/overtimerecord/'+id+'/delete\')">删除</a>'
                    }else if(rowObject.status == "作废"){
                        str = '<a disable="" class="font-color" href="/attendance/overtimerecord/'+id+'/update.html"  data-pjax="#main-content">编辑</a>'
                                + '<span class="ant-divider"></span>'
                                +'<a  <shiro:lacksPermission name="OverTimeRecord:delete">disable=""</shiro:lacksPermission> class="font-color" href="javascript:void(0)" onclick="showDelModal(\'/attendance/overtimerecord/'+id+'/delete\')">删除</a>'
                    }
                    return str;
                }
            }
        ],
        ajax_success: function () {
            $("[data-toggle='popover']").popover({placement:"top",html:true})
                    .on('shown.bs.popover', function () {
                        var node = this;
                        $("button[data-id]").click(function(){
                            var id = $(this).attr("data-id");
                            updateType('/attendance/overtimerecord/update/'+id,node);
                        });
                    });

        }
    });

    function updateType(url,node){
        ajaxSubmitGet(url,function(result){
            $(node).popover('hide');
            if(result.status){
                document.querySelector('table').GM('refreshGrid', true,function () {
                    console.log('更新...');
                });
                toastr.success("更改成功!");
            }else{
                toastr.error("执行错误!");
            }
        });
    }

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
        }
        document.querySelector('table').GM('setQuery', query).GM('refreshGrid', function () {
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
                    <button type="button" id="resourceBtn" class="ant-btn" onclick="chooseResource('/organization/modal/list','overTime');">
                        <span>全公司</span>
                    </button>
                    <button type="button" class="ant-btn ant-btn-primary" style="margin-left: 50px">
                        <span>导出加班表</span>
                    </button>
                    <div class="detail-line"></div>
                </div>
            </div>
            <%--<div>--%>
                <%--<a style="font-size: 14px;color:#2db7f5" href="/overtime/setting.html" data-pjax="#main-content">加班申请</a>--%>
            <%--</div>--%>
            <div class="btn-group">
                <button type="button" class="ant-btn dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    加班申请 <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" style="font-size: 12px;min-width: 93px">
                    <li><a href="/attendance/overtimerecord/create.html" data-pjax="#main-content">单个申请</a></li>
                    <li><a href="/attendance/overtimerecord/create/batch.html" data-pjax="#main-content">批量申请</a></li>
                </ul>
            </div>
        </div>
    </div>
    <table grid-manager="main"></table>
</div>