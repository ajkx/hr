<%--
  Created by IntelliJ IDEA.
  User: ajkx
  Date: 2017/2/17
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../../common/init.jsp" %>
<c:set var="action" value="${op != '修改' ? 'create' : 'update'}"/>
<c:set var="groupType" value="${t.groupType.name()}"/>

<script>
    function convertJson(node) {
//        if(!$("#modal-form").valid()){
//            return;
//        }
        var data = $(node).serializeJSON();
        if (data.monday.id == "") {
            data.monday = null;
        }
        if (data.tuesday.id == "") {
            data.tuesday = null;
        }
        if (data.wednesday.id == "") {
            data.wednesday = null;
        }
        if (data.thursday.id == "") {
            data.thursday = null;
        }
        if (data.friday.id == "") {
            data.friday = null;
        }
        if (data.saturday.id == "") {
            data.saturday = null;
        }
        if (data.sunday.id == "") {
            data.sunday = null;
        }

        //人员集合
        var resources = data.resources.split(",");
        var resourceArray = new Array();
        for (var i = 0; i < resources.length; i++) {
            if (resources[i] != "") {
                var obj = {"id": resources[i]};
                resourceArray.push(obj);
            }
        }
        data.resources = resourceArray;

        //排班相关班次
        var schedules = data.schedules.split(",");
        var scheduleArray = new Array();
        for (var i = 0; i < schedules.length; i++) {
            if (schedules[i] != "") {
                var obj = {"id": schedules[i]};
                scheduleArray.push(obj);
            }
        }
        data.schedules = scheduleArray;


        if (data.holidayRest == "1") {
            data.holidayRest = true;
        } else {
            data.holidayRest = false;
        }
        var specialArray = new Array();
        var punchRow = $("#must_table").children();
        var notRow = $("#not_table").children();
        if(punchRow.length > 0){

            punchRow.each(function(index,element) {
                var temp = {"date":"","schedule":{"id":""},"specialType":"mustPunch"};
                temp.date = $(element).find(".punchDate").text();
                temp.schedule.id = parseInt($(element).find(".punchSchedule").attr("data-id"));
                specialArray.push(temp);
            });
        }
        if(notRow.length > 0){
            notRow.each(function(index,element) {
               var temp = {"date":"","specialType":"noPunch"};
                temp.date = $(element).find(".notDate").text();
                specialArray.push(temp);
            });
        }
        if(specialArray.length > 0){
            data.specialDates = specialArray;
        }
        var url = $(node).attr("action");
        jsonSubmit(url, data, '/attendance/group.html');
        return false;
    }

</script>

<div style="margin-top: 15px; width: 1150px; background: rgb(255, 255, 255);">
    <div>
        <div class="" style="margin: 20px 0px 30px; opacity: 1; visibility: visible; transform: translateX(0px);">
            <form class="ant-form ant-form-horizontal" action="/attendance/group/${action}"
                  onsubmit="return convertJson(this)">
                <input type="hidden" name="id" value="${t.id}"/>
                <input type="hidden" name="groupType" id="groupType"
                       value="${groupType == null ? 'fixed' : groupType}"/>
                <input type="hidden" name="holidayRest" id="autoRest" value="${t.holidayRest == true ? 1 : 0}"/>
                <input type="hidden" name="monday[id]" id="monday" value="${t.monday.rest == true ? "" : t.monday.id}"/>
                <input type="hidden" name="tuesday[id]" id="tuesday"
                       value="${t.tuesday.rest == true ? "" : t.tuesday.id}"/>
                <input type="hidden" name="wednesday[id]" id="wednesday"
                       value="${t.wednesday.rest == true ? "" : t.wednesday.id}"/>
                <input type="hidden" name="thursday[id]" id="thursday"
                       value="${t.thursday.rest == true ? "" : t.thursday.id}"/>
                <input type="hidden" name="friday[id]" id="friday" value="${t.friday.rest == true ? "" : t.friday.id}"/>
                <input type="hidden" name="saturday[id]" id="saturday"
                       value="${t.saturday.rest == true ? "" : t.saturday.id}"/>
                <input type="hidden" name="sunday[id]" id="sunday" value="${t.sunday.rest == true ? "" : t.sunday.id}"/>
                <input type="hidden" name="resources" id="resourceIds"
                       value="<c:forEach items="${t.resources}" var="resource">${resource.id},</c:forEach>"/>
                <input type="hidden" name="schedules" id="schedules"
                       value="<c:forEach items="${t.schedules}" var="schedule">${schedule.id},</c:forEach>"/>
                <input type="hidden" id="currentNode" value=""/>

                <div>
                    <a href="/attendance/group.html" data-pjax="#main-content" class="returnLink">< 返回</a>
                </div>

                <div class="ant-row ant-form-item">
                    <div class="ant-col-2 ant-form-item-label"><label class="">考勤组名称</label></div>
                    <div class="ant-col-8">
                        <div class="ant-form-item-control "><span class="ant-input-wrapper">
                            <input type="text" placeholder="请输入考勤组名称" class="ant-input ant-input-lg" name="name"
                                   value="${t.name}" autocomplete="off"></span></div>
                    </div>
                </div>
                <div class="ant-row ant-form-item">
                    <div class="ant-col-2 ant-form-item-label"><label class="">考勤人员</label></div>
                    <div class="ant-col-8">
                        <div class="ant-form-item-control ">
                            <button type="button" class="ant-btn ant-btn-ghost ant-btn-lg"
                                    onclick="chooseModal(this,'/hrm/resource/modal/multi')" data-index="resourceIds"
                                    id="resourceBtn">
                                 <span>
                                    <c:choose>
                                        <c:when test="${t.resources.size() > 0}">
                                            ${t.resources.size()}人
                                        </c:when>
                                        <c:otherwise>
                                            请选择
                                        </c:otherwise>
                                    </c:choose>
                                 </span>
                            </button>
                        </div>
                    </div>
                </div>
                <div class="ant-row ant-form-item">
                    <div class="ant-col-2 ant-form-item-label"><label class="">考勤类型</label></div>
                    <div class="ant-col-18">
                        <div class="ant-form-item-control ">
                            <div class="ant-radio-group ant-radio-group-large">
                                <label style="font-weight: 500;" class="ant-radio-wrapper ant-radio-wrapper-checked">
                                    <span class="ant-radio <c:if test="${groupType == null || groupType == 'fixed'}">ant-radio-checked</c:if> ">
                                        <span class="ant-radio-inner"></span>
                                        <input type="radio" class="ant-radio-input" data-type="fixed" value="on">
                                    </span>
                                    <span>固定班制 (每天考勤时间一样)</span>
                                </label>
                                <label style="font-weight: 500;" class="ant-radio-wrapper">
                                    <span class="ant-radio <c:if test="${groupType == 'arrange'}">ant-radio-checked</c:if>">
                                        <span class="ant-radio-inner"></span>
                                        <input type="radio" class="ant-radio-input" data-type="arrange" value="on">
                                    </span><span>排班制 (自定义设置考勤时间)</span>
                                </label>
                                <label style="font-weight: 500;" class="ant-radio-wrapper">
                                    <span class="ant-radio <c:if test="${groupType == 'free'}">ant-radio-checked</c:if>">
                                        <span class="ant-radio-inner"></span>
                                        <input type="radio" class="ant-radio-input" data-type="free" value="on">
                                    </span>
                                    <span>自由工时（不设置班次，随时打卡）</span>
                                </label>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="ant-row ant-form-item type1" style="<c:if test="${groupType != null && groupType != 'fixed'}">display:none;</c:if> ">
                    <div class="ant-col-2 ant-form-item-label"><label class="">工作日设置</label></div>
                    <div class="ant-col-14">
                        <div class="ant-form-item-control ">
                            <div>
                                <div>
                                    <div style="margin-bottom: 5px;">
                                        快捷设置班次：<span id="currentSchedule" data-id=""></span><a data-index="all"
                                                                                               style="margin-left: 10px;"
                                                                                               href="javascript:void(0)"
                                                                                               onclick="chooseModal(this,'/attendance/schedule/modal/singleList')">更改班次</a>
                                    </div>
                                    <div class=" clearfix">
                                        <div class="">
                                            <div class="ant-spin"><span class="ant-spin-dot"></span>
                                                <div class="ant-spin-text">加载中...</div>
                                            </div>
                                            <div class="ant-spin-container">
                                                <div class="ant-table ant-table-middle ant-table-scroll-position-left">
                                                    <div class="ant-table-content">
                                                        <div class=""><span><div class="ant-table-body"><table class=""><colgroup><col><col
                                                                style="width: 70px; min-width: 70px;"><col
                                                                style="width: 300px; min-width: 300px;"><col
                                                                style="width: 120px; min-width: 120px;"></colgroup><thead
                                                                class="ant-table-thead"><tr><th
                                                                class="ant-table-selection-column"><span><label
                                                                class="ant-checkbox-wrapper"><span class="ant-checkbox"><span
                                                                class="ant-checkbox-inner"></span>
                                                            <input
                                                                    type="checkbox" id="checkAll"
                                                                    class="ant-checkbox-input" value="off"></span>
                                                                </label>
                                                                </span>
                                                                </th>
                                                                <th class=""><span>工作日</span></th>
                                                                <th class=""><span>班次时间段</span></th>
                                                                <th class=""><span>操作</span></th>
                                                                </tr>
                                                                </thead>
                                                                <tbody class="ant-table-tbody">
                                                                    <tr class="ant-table-row  ant-table-row-level-0">
                                                                        <td class="ant-table-selection-column"><span><label
                                                                                class="ant-checkbox-wrapper">
                                                                            <span class="ant-checkbox <c:if test="${t.monday != null && t.monday.rest != true}">ant-checkbox-checked</c:if> "><span
                                                                                    class="ant-checkbox-inner"></span>
                                                                            <input type="checkbox"
                                                                                   class="ant-checkbox-input"
                                                                                   data-input="monday" data-check="true"
                                                                                   value="<c:choose><c:when test="${t.monday != null && t.monday.rest != true}">on</c:when><c:otherwise>off</c:otherwise></c:choose>">
                                                                            </span>
                                                                            </label>
                                                                            </span>
                                                                        </td>
                                                                        <td class=""><span
                                                                                class="ant-table-row-indent indent-level-0"
                                                                                style="padding-left: 0px;"></span>

                                                                            周一

                                                                        </td>
                                                                        <td class="scheduleName">
                                                                            <c:choose>
                                                                                <c:when test="${t.monday != null && t.monday.rest != true}">
                                                                                    ${monday}
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    休息
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </td>
                                                                        <td class=""><a data-index="monday"
                                                                                        href="javascript:void(0)"
                                                                                        onclick="chooseModal(this,'/attendance/schedule/modal/single')">更改班次</a></td>
                                                                    </tr>
                                                                    <tr class="ant-table-row  ant-table-row-level-0">
                                                                        <td class="ant-table-selection-column"><span><label
                                                                                class="ant-checkbox-wrapper">
                                                                            <span class="ant-checkbox <c:if test="${t.tuesday != null && t.tuesday.rest != true}">ant-checkbox-checked</c:if>"><span
                                                                                    class="ant-checkbox-inner"></span>
                                                                                <input type="checkbox"
                                                                                       class="ant-checkbox-input"
                                                                                       data-input="tuesday"
                                                                                       data-check="true"
                                                                                       value="<c:choose><c:when test="${t.tuesday != null && t.tuesday.rest != true}">on</c:when><c:otherwise>off</c:otherwise></c:choose>"></span>
                                                                            </label>
                                                                            </span>
                                                                        </td>
                                                                        <td class=""><span
                                                                                class="ant-table-row-indent indent-level-0"
                                                                                style="padding-left: 0px;"></span>

                                                                            周二

                                                                        </td>
                                                                        <td class="scheduleName">
                                                                            <c:choose>
                                                                                <c:when test="${t.tuesday != null && t.tuesday.rest != true}">
                                                                                    ${tuesday}
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    休息
                                                                                </c:otherwise>
                                                                            </c:choose>

                                                                        </td>
                                                                        <td class=""><a data-index="tuesday"
                                                                                        href="javascript:void(0)"
                                                                                        onclick="chooseModal(this,'/attendance/schedule/modal/single')">更改班次</a></td>
                                                                    </tr>
                                                                    <tr class="ant-table-row  ant-table-row-level-0">
                                                                        <td class="ant-table-selection-column"><span><label
                                                                                class="ant-checkbox-wrapper">
                                                                            <span class="ant-checkbox <c:if test="${t.wednesday != null && t.wednesday.rest != true}">ant-checkbox-checked</c:if>"><span
                                                                                    class="ant-checkbox-inner"></span>
                                                                                <input type="checkbox"
                                                                                       class="ant-checkbox-input"
                                                                                       data-input="wednesday"
                                                                                       data-check="true"
                                                                                       value="<c:choose><c:when test="${t.wednesday != null && t.wednesday.rest != true}">on</c:when><c:otherwise>off</c:otherwise></c:choose>"></span>
                                                                            </label>
                                                                            </span>
                                                                        </td>
                                                                        <td class=""><span
                                                                                class="ant-table-row-indent indent-level-0"
                                                                                style="padding-left: 0px;"></span>

                                                                            周三

                                                                        </td>
                                                                        <td class="scheduleName">
                                                                            <c:choose>
                                                                                <c:when test="${t.wednesday != null && t.wednesday.rest != true}">
                                                                                    ${wednesday}
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    休息
                                                                                </c:otherwise>
                                                                            </c:choose>

                                                                        </td>
                                                                        <td class=""><a data-index="wednesday"
                                                                                        href="javascript:void(0)"
                                                                                        onclick="chooseModal(this,'/attendance/schedule/modal/single')">更改班次</a></td>
                                                                    </tr>
                                                                    <tr class="ant-table-row  ant-table-row-level-0">
                                                                        <td class="ant-table-selection-column"><span><label
                                                                                class="ant-checkbox-wrapper">
                                                                            <span class="ant-checkbox <c:if test="${t.thursday != null && t.thursday.rest != true}">ant-checkbox-checked</c:if>"><span
                                                                                    class="ant-checkbox-inner"></span>
                                                                                <input type="checkbox"
                                                                                       class="ant-checkbox-input"
                                                                                       data-input="thursday"
                                                                                       data-check="true"
                                                                                       value="<c:choose><c:when test="${t.thursday != null && t.thursday.rest != true}">on</c:when><c:otherwise>off</c:otherwise></c:choose>"></span>
                                                                            </label>
                                                                            </span>
                                                                        </td>
                                                                        <td class=""><span
                                                                                class="ant-table-row-indent indent-level-0"
                                                                                style="padding-left: 0px;"></span>

                                                                            周四

                                                                        </td>
                                                                        <td class="scheduleName">
                                                                            <c:choose>
                                                                                <c:when test="${t.thursday != null && t.thursday.rest != true}">
                                                                                    ${thursday}
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    休息
                                                                                </c:otherwise>
                                                                            </c:choose>

                                                                        </td>
                                                                        <td class=""><a data-index="thursday"
                                                                                        href="javascript:void(0)"
                                                                                        onclick="chooseModal(this,'/attendance/schedule/modal/single')">更改班次</a></td>
                                                                    </tr>
                                                                    <tr class="ant-table-row  ant-table-row-level-0">
                                                                        <td class="ant-table-selection-column"><span><label
                                                                                class="ant-checkbox-wrapper">
                                                                            <span class="ant-checkbox <c:if test="${t.friday != null && t.friday.rest != true}">ant-checkbox-checked</c:if>"><span
                                                                                    class="ant-checkbox-inner"></span>
                                                                                <input type="checkbox"
                                                                                       class="ant-checkbox-input"
                                                                                       data-input="friday"
                                                                                       data-check="true"
                                                                                       value="<c:choose><c:when test="${t.friday != null && t.friday.rest != true}">on</c:when><c:otherwise>off</c:otherwise></c:choose>"></span>
                                                                            </label>
                                                                            </span>
                                                                        </td>
                                                                        <td class=""><span
                                                                                class="ant-table-row-indent indent-level-0"
                                                                                style="padding-left: 0px;"></span>

                                                                            周五

                                                                        </td>
                                                                        <td class="scheduleName">
                                                                            <c:choose>
                                                                                <c:when test="${t.friday != null && t.friday.rest != true}">
                                                                                    ${friday}
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    休息
                                                                                </c:otherwise>
                                                                            </c:choose>

                                                                        </td>
                                                                        <td class=""><a data-index="friday"
                                                                                        href="javascript:void(0)"
                                                                                        onclick="chooseModal(this,'/attendance/schedule/modal/single')">更改班次</a></td>
                                                                    </tr>
                                                                    <tr class="ant-table-row  ant-table-row-level-0">
                                                                        <td class="ant-table-selection-column"><span><label
                                                                                class="ant-checkbox-wrapper">
                                                                            <span class="ant-checkbox <c:if test="${t.saturday != null && t.saturday.rest != true}">ant-checkbox-checked</c:if>"><span
                                                                                    class="ant-checkbox-inner"></span>
                                                                                <input type="checkbox"
                                                                                       class="ant-checkbox-input"
                                                                                       data-input="saturday"
                                                                                       data-check="true"
                                                                                       value="<c:choose><c:when test="${t.saturday != null && t.saturday.rest != true}">on</c:when><c:otherwise>off</c:otherwise></c:choose>"></span>
                                                                            </label>
                                                                            </span>
                                                                        </td>
                                                                        <td class=""><span
                                                                                class="ant-table-row-indent indent-level-0"
                                                                                style="padding-left: 0px;"></span>

                                                                            周六

                                                                        </td>
                                                                        <td class="scheduleName">
                                                                            <c:choose>
                                                                                <c:when test="${t.saturday != null && t.saturday.rest != true}">
                                                                                    ${saturday}
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    休息
                                                                                </c:otherwise>
                                                                            </c:choose>

                                                                        </td>
                                                                        <td class=""><a data-index="saturday"
                                                                                        href="javascript:void(0)"
                                                                                        onclick="chooseModal(this,'/attendance/schedule/modal/single')">更改班次</a></td>
                                                                    </tr>
                                                                    <tr class="ant-table-row  ant-table-row-level-0">
                                                                        <td class="ant-table-selection-column"><span><label
                                                                                class="ant-checkbox-wrapper">
                                                                            <span class="ant-checkbox <c:if test="${t.sunday != null && t.sunday.rest != true}">ant-checkbox-checked</c:if>"><span
                                                                                    class="ant-checkbox-inner"></span>
                                                                                <input type="checkbox"
                                                                                       class="ant-checkbox-input"
                                                                                       data-input="sunday"
                                                                                       data-check="true"
                                                                                       value="<c:choose><c:when test="${t.sunday != null && t.sunday.rest != true}">on</c:when><c:otherwise>off</c:otherwise></c:choose>"></span>
                                                                            </label>
                                                                            </span>
                                                                        </td>
                                                                        <td class=""><span
                                                                                class="ant-table-row-indent indent-level-0"
                                                                                style="padding-left: 0px;"></span>

                                                                            周日

                                                                        </td>
                                                                        <td class="scheduleName">
                                                                            <c:choose>
                                                                                <c:when test="${t.sunday != null && t.sunday.rest != true}">
                                                                                    ${sunday}
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    休息
                                                                                </c:otherwise>
                                                                            </c:choose>

                                                                        </td>
                                                                        <td class=""><a data-index="sunday"
                                                                                        href="javascript:void(0)"
                                                                                        onclick="chooseModal(this,'/attendance/schedule/modal/single')">更改班次</a></td>
                                                                    </tr>
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
                                    <div style="margin-top: 5px;">
                                        <label class="ant-checkbox-wrapper">
                                            <span class="ant-checkbox">
                                                <span class="ant-checkbox-inner"></span>
                                                <input type="checkbox" id="isAuto" class="ant-checkbox-input"
                                                       value="off">
                                            </span>
                                        </label>
                                        法定节假日自动排休

                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="ant-row ant-form-item type1">
                    <div class="ant-col-2 ant-form-item-label"><label class="">特殊日期</label></div>
                    <div class="ant-col-14">
                        <div class="ant-form-item-control ">
                            <div>
                                <div>
                                    <button type="button" class="ant-btn ant-btn-ghost" data-index="-1"
                                            onclick="chooseModal(this,'/attendance/group/modal/punch')"><span>添 加</span>
                                    </button>
                                    <span style="margin-left: 12px; font-size: 12px; color: rgb(153, 153, 153);">必须打卡的日期</span>
                                </div>
                                <input type="hidden" id="mustId" value="${punchSize == null ? 1 : punchSize + 1}"/>
                                <div style="width: 550px; padding-top: 10px; padding-bottom: 10px;" id="must_panel">
                                    <div class="ant-table-wrapper">
                                        <div class="ant-spin-container">
                                            <div class="ant-table ant-table-small ant-table-scroll-position-left">
                                                <div class="ant-table-content">
                                                    <div class="ant-table-body">
                                                        <table class="">
                                                            <colgroup>
                                                                <col style="width: 150px; min-width: 150px;">
                                                                <col style="width: 270px; min-width: 270px;">
                                                                <col>
                                                            </colgroup>
                                                            <thead class="ant-table-thead">
                                                            <tr>
                                                                <th class="">
                                                                    <span>日期</span>
                                                                </th>
                                                                <th class="">
                                                                    <span>考勤时间</span>
                                                                </th>
                                                                <th class="">
                                                                    <span>操作</span>
                                                                </th>
                                                            </tr>
                                                            </thead>
                                                            <tbody class="ant-table-tbody" id="must_table">
                                                            <c:forEach items="${punchList}" var="spe" varStatus="index">
                                                                <tr class="ant-table-row  ant-table-row-level-0" id="mustPunch${index.count}">
                                                                    <td class="punchDate">${spe.date}</td>
                                                                    <td data-id="${spe.scheduleId}" class="punchSchedule">${spe.scheduleName}</td>
                                                                    <td class="">
                                                                        <span>
                                                                            <a  href="javascript:void(0)" onclick="chooseModal(this,'/attendance/group/modal/punch')" data-index="${index.count}">编辑</a>
                                                                            <span class="ant-divider"></span>
                                                                            <a href="javascript:void(0)" onclick="deleteRow(this)">删除</a>
                                                                        </span>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <button type="button" class="ant-btn ant-btn-ghost" data-index="-1"
                                        onclick="chooseModal(this,'/attendance/group/modal/not')"><span>添 加</span></button>
                                <span style="margin-left: 12px; font-size: 12px; color: rgb(153, 153, 153);">不用打卡的日期</span>
                            </div>
                            <input type="hidden" id="notId" value="${notSize == null ? 1 : notSize + 1}"/>
                            <div style="width: 550px; padding-top: 10px; padding-bottom: 0px;" id="not_panel">
                                <div class="ant-table-wrapper">
                                    <div class="ant-spin-container">
                                        <div class="ant-table ant-table-small ant-table-scroll-position-left">
                                            <div class="ant-table-content">
                                                <div class="ant-table-body">
                                                    <table class="">
                                                        <colgroup>
                                                            <col style="width: 150px; min-width: 150px;">
                                                            <col style="width: 270px; min-width: 270px;">
                                                            <col>
                                                        </colgroup>
                                                        <thead class="ant-table-thead">
                                                        <tr>
                                                            <th class="">
                                                                <span>日期</span>
                                                            </th>
                                                            <th class="">
                                                                <span>考勤时间</span>
                                                            </th>
                                                            <th class="">
                                                                <span>操作</span>
                                                            </th>
                                                        </tr>
                                                        </thead>
                                                        <tbody class="ant-table-tbody" id="not_table">
                                                        <c:forEach items="${notList}" var="spe" varStatus="index">
                                                            <tr class="ant-table-row  ant-table-row-level-0" id="notPunch${index.count}">
                                                                <td class="punchDate">${spe.date}</td>
                                                                <td>休息</td>
                                                                <td class="">
                                                                    <span>
                                                                        <a href="javascript:void(0)" onclick="deleteRow(this)">删除</a>
                                                                    </span>
                                                                </td>
                                                            </tr>
                                                        </c:forEach>
                                                        </tbody>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
        <div class="ant-row ant-form-item type2"
             style="<c:if test="${groupType == null || groupType != 'arrange'}">display:none;</c:if>">
            <div class="ant-col-2 ant-form-item-label"><label class="">考勤班次</label></div>
            <div class="ant-col-14">
                <div class="ant-form-item-control ">
                    <button type="button" class="ant-btn ant-btn-ghost ant-btn-lg"
                            onclick="chooseModal(this,'/attendance/schedule/modal/multi')" data-index="schedules"><span>选择班次</span>
                    </button>
                    <div style="margin-top: 15px; margin-bottom: -10px;"></div>
                </div>
            </div>
        </div>
        <div class="ant-row ant-form-item">
            <div class="ant-col-2 ant-form-item-label"><label class="">详细描述</label></div>
            <div class="ant-col-14">
                <div class="ant-form-item-control ">
                    <textarea name="description" rows="3" class="ant-input"></textarea>
                </div>
            </div>
        </div>
        <div class="ant-row" style="margin: 24px 0px;">
            <div class="ant-col-16 ant-col-offset-8">
                <button type="submit" class="ant-btn ant-btn-primary"><span>保存设置</span></button>
            </div>
        </div>
        </form>
    </div>
</div>
</div>
<script>
    $(function () {
        $('input[type="radio"]').click(function () {
            var type = $(this).attr("data-type");
            var groupType = $('#groupType');
            if (groupType.val() == type) {
                return;
            }
            $('.ant-radio-checked').removeClass('ant-radio-checked');
            $(this).parent().addClass('ant-radio-checked');
            groupType.val(type);
            switch (type) {
                case "fixed":
                    $('.type1').css("display", "block");
                    $('.type2').css("display", "none");
                    break;
                case "arrange":
                    $('.type1').css("display", "none");
                    $('.type2').css("display", "block");
                    break;
                case "free":
                    $('.type1').css("display", "none");
                    $('.type2').css("display", "none");
                    break;
            }
        });
        $('input[data-check="true"]').click(function () {
            var node = $(this);
            var nodeStr = node.attr('data-input');
            if (node.val() == 'on') {
                node.parent().removeClass('ant-checkbox-checked');
                node.val("off");
                $('#' + nodeStr).val("");
                node.parents(".ant-table-selection-column").siblings(".scheduleName").text("休息");
            } else {
                node.parent().addClass(' ant-checkbox-checked');
                node.val("on");
                $('#' + nodeStr).val($("#currentSchedule").attr("data-id"));
                var text = $("#currentSchedule").text();
                node.parents(".ant-table-selection-column").siblings(".scheduleName").text(text == "" ? "休息" : text);
            }
        });
        $('#checkAll').click(function () {
            var checks = $('input[data-check="true"]');
            var node = $(this);
            if (node.val() == 'on') {
                node.parent().removeClass('ant-checkbox-checked');
                node.val('off');
                checks.each(function () {
                    $(this).parent().removeClass('ant-checkbox-checked');
                    $(this).val("off");
                    var nodeStr = $(this).attr('data-input');
                    $("#" + nodeStr).val("");
                    $(this).parents(".ant-table-selection-column").siblings(".scheduleName").text("休息");
                });
            } else {
                node.parent().addClass('ant-checkbox-checked');
                node.val("on");
                var text = $("#currentSchedule").text();
                checks.each(function () {
                    $(this).parent().addClass('ant-checkbox-checked');
                    $(this).val('on');
                    var nodeStr = $(this).attr('data-input');
                    $("#" + nodeStr).val($("#currentSchedule").attr("data-id"));
                    $(this).parents(".ant-table-selection-column").siblings(".scheduleName").text(text == "" ? "休息" : text);
                });

            }
        });
        $("#isAuto").click(function () {
            var auto = $(this);
            if (auto.val() == 'on') {
                auto.parent().removeClass("ant-checkbox-checked");
                auto.val("off");
                $("#autoRest").val(0);
            } else {
                auto.parent().addClass("ant-checkbox-checked");
                auto.val("on");
                $("#autoRest").val(1);
            }
        });

    });

    function getDatePanel(id) {
        var panel = $("#"+id);
        var trs = panel.children();
        if(trs.length > 0){
            return true;
        }else{
            return false;
        }
    }
    //选择人员的回调清空方法
    function resourceClearCallBack() {
        $('#resourceBtn').text('请选择');
        $('#resourceIds').val('');
    }

    function deleteRow(node) {
        $(node).parent().parent().parent().remove();
    }
</script>
