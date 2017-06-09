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
        ajax_url: '/attendance/detail/list',
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
                key: 'date',
                text: '日期',
                width: '120px',
                remind: '',
            },
            {
                key: 'schedule',
                text: '班次',
                width: '130px',
                remind: '',
            },
//            {
//                key: 'attendanceType',
//                text: '考勤结果',
//                width: '100px',
//                remind: '',
//            },
            {
                key: 'actual_first_up',
                text: '上班1',
                width: '160px',
                remind: '',
                template:function(actual_first_up,rowObject) {
                    return actual_first_up == '' ? '' : actual_first_up.substring(0, 5);
                }
            },
            {
                key: 'first_up_type',
                text: '上班1',
                width: '80px',
                remind: '',
                template:function(first_up_type,rowObject){
                    if(first_up_type == "正常" || first_up_type == "请假"){
                        return first_up_type;
                    }else{
                        return "<span class='updateButton'" +
                                " data-toggle='popover'" +
                                " data-content='<select style=\"margin-bottom:5px;\"><option></option> <option>个人忘记</option> <option>因公延误</option> <option>出差</option> <option>调休</option> <option>卡丢失或失效</option> </select><button class=\"ant-btn ant-btn-primary\" data-id=\""+rowObject.id+"\" data-type=\"1\">改为正常</button>'" +
                                ">"+first_up_type+"</span>";
                    }

                }
            },
//            onclick='updateType(\"/detail/update/"+rowObject.id+"/1\",this)'
            {
                key: 'actual_first_down',
                text: '下班1',
                width: '160px',
                remind: '',
                template:function(actual_first_down,rowObject) {
                    return actual_first_down == '' ? '' : actual_first_down.substring(0, 5);
                }
            },
            {
                key: 'first_down_type',
                text: '下班1',
                remind: '',
                width: '80px',
                template:function(first_down_type,rowObject){
                    if(first_down_type == "正常" || first_down_type == "请假"){
                        return first_down_type;
                    }else{
                        return "<span class='updateButton'" +
                                " data-toggle='popover'" +
                                " data-content='<select style=\"margin-bottom:5px;\"><option></option> <option>个人忘记</option> <option>因公延误</option> <option>出差</option> <option>调休</option> <option>卡丢失或失效</option> </select><button class=\"ant-btn ant-btn-primary\" data-id=\""+rowObject.id+"\" data-type=\"2\">改为正常</button>'" +
                                ">"+first_down_type+"</span>";                    }

                }
            },
            {
                key: 'actual_second_up',
                text: '上班2',
                remind: '',
                width: '160px',
                template:function(actual_second_up,rowObject) {
                    return actual_second_up == '' ? '' : actual_second_up.substring(0, 5);
                }
            },
            {
                key: 'second_up_type',
                text: '上班2',
                remind: '',
                width: '80px',
                template:function(second_up_type,rowObject){
                    if(second_up_type == "正常" || second_up_type == "请假"){
                        return second_up_type;
                    }else{
                        return "<span class='updateButton'" +
                                " data-toggle='popover'" +
                                " data-content='<select style=\"margin-bottom:5px;\"><option></option> <option>个人忘记</option> <option>因公延误</option> <option>出差</option> <option>调休</option> <option>卡丢失或失效</option> </select><button class=\"ant-btn ant-btn-primary\" data-id=\""+rowObject.id+"\" data-type=\"3\">改为正常</button>'" +
                                ">"+second_up_type+"</span>";
                    }

                }
            },
            {
                key: 'actual_second_down',
                text: '下班2',
                remind: '',
                width: '160px',
                template:function(actual_second_down,rowObject) {
                    return actual_second_down == '' ? '' : actual_second_down.substring(0, 5);
                }
            },
            {
                key: 'second_down_type',
                text: '下班2',
                remind: '',
                width: '80px',
                template:function(second_down_type,rowObject){
                    if(second_down_type == "正常" || second_down_type == "请假"){
                        return second_down_type;
                    }else{
                        return "<span class='updateButton'" +
                                " data-toggle='popover'" +
                                " data-content='<select style=\"margin-bottom:5px;\"><option></option> <option>个人忘记</option> <option>因公延误</option> <option>出差</option> <option>调休</option> <option>卡丢失或失效</option> </select><button class=\"ant-btn ant-btn-primary\" data-id=\""+rowObject.id+"\" data-type=\"4\">改为正常</button>'" +
                                ">"+second_down_type+"</span>";                    }

                }
            },
            {
                key: 'actual_third_up',
                text: '上班3',
                remind: '',
                width: '160px',
                template:function(actual_third_up,rowObject) {
                    return actual_third_up == '' ? '' : actual_third_up.substring(0, 5);
                }
            },
            {
                key: 'third_up_type',
                text: '上班3',
                remind: '',
                width: '80px',
                template:function(third_up_type,rowObject){
                    if(third_up_type == "正常" || third_up_type == "请假"){
                        return third_up_type;
                    }else{
                        return "<span class='updateButton'" +
                                " data-toggle='popover'" +
                                " data-content='<select style=\"margin-bottom:5px;\"><option></option> <option>个人忘记</option> <option>因公延误</option> <option>出差</option> <option>调休</option> <option>卡丢失或失效</option> </select><button class=\"ant-btn ant-btn-primary\" data-id=\""+rowObject.id+"\" data-type=\"5\">改为正常</button>'" +
                                ">"+third_up_type+"</span>";                    }

                }
            },
            {
                key: 'actual_third_down',
                text: '下班3',
                remind: '',
                width: '160px',
                template:function(actual_third_down,rowObject) {
                    return actual_third_down == '' ? '' : actual_third_down.substring(0, 5);
                }
            },
            {
                key: 'third_down_type',
                text: '下班3',
                remind: '',
                width: '80px',
                template:function(third_down_type,rowObject){
                    if(third_down_type == "正常" || third_down_type == "请假"){
                        return third_down_type;
                    }else{
                        return "<span class='updateButton'" +
                                " data-toggle='popover'" +
                                " data-content='<select style=\"margin-bottom:5px;\"><option></option><option>个人忘记</option> <option>因公延误</option> <option>出差</option> <option>调休</option> <option>卡丢失或失效</option> </select><button class=\"ant-btn ant-btn-primary\" data-id=\""+rowObject.id+"\" data-type=\"6\">改为正常</button>'" +
                                ">"+third_down_type+"</span>";                    }

                }
            },
            {
                key: 'lateTime',
                text: '迟到时间',
                remind: '',
                width: '120px',
                template:function(lateTime,rowObject){
                    return Math.ceil(lateTime/60000);
                }
            },
            {
                key: 'earlyTime',
                text: '早退时间',
                remind: '',
                width: '120px',
                template:function(earlyTime,rowObject){
                    return Math.ceil(earlyTime/60000);
                }
            },
            {
                key: 'absenteeismTime',
                text: '旷工时间',
                remind: '',
                width: '120px',
                template:function(absenteeismTime,rowObject){
                    return Math.ceil(absenteeismTime/60000);
                }
            },
            {
                key: 'ot_normal',
                text: '平时加班',
                remind: '',
                width: '120px',
                template:function(ot_normal,rowObject){
                    return Math.round(ot_normal/60000);
                }
            },
            {
                key: 'ot_weekend',
                text: '周末加班',
                remind: '',
                width: '120px',
                template:function(ot_weekend,rowObject){
                    return Math.round(ot_weekend/60000);
                }
            },
            {
                key: 'ot_festival',
                text: '节日加班',
                remind: '',
                width: '120px',
                template:function(ot_festival,rowObject){
                    return Math.round(ot_festival/60000);
                }
            },
            {
                key: 'level_time',
                text: '请假时间',
                remind: '',
                width: '120px',
                template:function(level_time,rowObject){
                    return Math.round(level_time/60000);
                }
            },
            {
                key: 'setting_time',
                text: '规定出勤时间',
                remind: '',
                width: '120px',
                template:function(setting_time,rowObject){
                    return Math.round(setting_time/60000);
                }
            },
            {
                key: 'actual_time',
                text: '实际出勤时间',
                remind: '',
                width: '120px',
                template:function(actual_time,rowObject){
                    return Math.floor(actual_time/60000);
                }
            }
        ],
        ajax_success: function () {
            $('tr').css("height", "39px");
            $('td').css("padding", "10px 8px");
            $("[data-toggle='popover']").popover({placement:"top",html:true})
                    .on('shown.bs.popover', function () {
                        var node = this;
                        $("button[data-id]").click(function(){
                            var id = $(this).attr("data-id");
                            var type = $(this).attr("data-type");
                            var str = $(this).siblings("select").val();
                            updateType('/attendance/repairrecord/updatedetail/'+id +'/'+type+'?reason='+str,node);
                        });
                    });

        }
    });

    $(function () {
        $(".table-div").addClass("ant-table");
        $("table").addClass("table-bordered");
        $("table").css("border-collapse", "collapse");


        var tr = $("<tr></tr>");
        for (var i = 0; i < 12; i++) {
            tr.append($("<th></th>").text("打卡时间"));
            tr.append($("<th></th>").text("打卡结果"));
            i++;
        }
        $('thead').append(tr);
        //让表头th下边框为1px并且居中
        $('th').css("border-bottom-width", "1px").css("text-align", "center").css("padding", "10px 8px");
        $('th div').css("text-align", "center");
        $('table').css("text-align", "center");
        $("thead").addClass("ant-table-thead");
        $('tbody').addClass("ant-table-tbody");
        $('tr').css("height", "39px");

        var trs = $("thead tr:first");
        var tds = trs.children();
        for (var i = 0; i < tds.length; i++) {
            if (i == 5 || i == 7 || i == 9 || i == 11 || i == 13 || i == 15) {
                $(tds[i]).attr("colspan", "2");
                $(tds[i + 1]).remove();
                i++;
            }
            else {
                $(tds[i]).attr("rowspan", "2");
            }
        }

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

    //搜索
    function searchData(){
        var query = {
            beginDate: document.querySelector('[name="beginDate"]').value,
            endDate: document.querySelector('[name="endDate"]').value,
            resources: document.querySelector('[name="resources"]').value,
        }
        document.querySelector('table').GM('setQuery', query).GM('refreshGrid', true,function () {
            console.log('搜索成功...');
        });
    }

    function updateType(url,node){
        ajaxSubmitGet(url,function(result){
            $(node).popover('hide');
            if(result.status){
                //刷新列表
                searchData();
                toastr.success("更改成功!");
            }else{
                toastr.error("执行错误!");
            }
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
                    <button type="button" id="resourceBtn" class="ant-btn" onclick="chooseResource('/organization/modal/list','attendanceDetail');">
                        <span>全公司</span>
                    </button>
                    <div class="detail-line"></div>
                </div>
            </div>
            <div>
                <button type="button" class="ant-btn ant-btn-primary">
                    <span>导出每日统计表</span>
                </button>
            </div>
        </div>
    </div>
    <table grid-manager="main"></table>
</div>