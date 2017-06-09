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
<c:set var="record" value="${t}"/>
<script>
    function convertJson(node){
//        if(!$("#modal-form").valid()){
//            return;
//        }
        var data = $(node).serializeJSON();
        //人员集合
        console.log(data);
        var url = $(node).attr("action");
        jsonSubmit(url, data,"/attendance/repairrecord.html");
        return false;
    }

</script>
<div style="margin-top: 15px; width: 1150px; background: rgb(255, 255, 255);">
    <div>
        <div class="" style="margin: 20px 0px 30px; opacity: 1; visibility: visible; transform: translateX(0px);">
            <form class="ant-form ant-form-horizontal" action="/attendance/repairrecord/${action}" onsubmit="return convertJson(this)">
                <input type="hidden" name="id" value="${record.id}"/>
                <%--<input type="hidden" name="type" id="recordType" value="${record.type == null ? 1 : record.type}"/>--%>
                <input type="hidden" name="resource[id]" id="resourceIds" value="${record.resource.id}"/>
                <input type="hidden" name="position" id="position" value="${record.position}"/>
                <input type="hidden" id="currentNode"/>

                <div>
                    <a href="/attendance/repairrecord.html" data-pjax="#main-content" class="returnLink">< 返回</a>
                </div>
                <div class="ant-row ant-form-item">
                    <div class="ant-col-2 ant-form-item-label"><label class="">补卡人员</label></div>
                    <div class="ant-col-8">
                        <div class="ant-form-item-control ">
                            <button type="button" class="ant-btn ant-btn-ghost ant-btn-lg" onclick="chooseModal(this,'/hrm/resource/modal/single')" data-index="resourceIds" id="resourcesBtn">
                                 <span>
                                    <c:choose>
                                        <c:when test="${record.resource != null}">
                                            ${record.resource.name}
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
                    <div class="ant-col-2 ant-form-item-label"><label class="">补卡日期</label></div>
                    <div class="ant-col-10">
                        <div class="ant-form-item-control ">
                            <input readonly="" value="${record.date}" name="date" placeholder="请选择开始时间"
                                   class="ant-input datetimepicker" style="width: 200px" id="beginDate">
                        </div>
                    </div>
                </div>
                <div class="ant-row ant-form-item">
                    <div class="ant-col-2 ant-form-item-label"><label class="">补卡时间</label></div>
                    <div class="ant-col-16">
                        <div class="ant-form-item-control ">
                            <span>
                                <label class="ant-checkbox-wrapper">
                                    <span class="ant-checkbox <c:if test="${fn:contains(record.position,'1')}">ant-checkbox-checked</c:if>">
                                        <span class="ant-checkbox-inner"></span>
                                            <input type="checkbox" class="ant-checkbox-input checkTime"
                                                   data-input="1" value="<c:if test="${fn:contains(record.position,'1')}">1</c:if>"/>
                                    </span>
                                </label>
                            </span>
                            第一次上班&nbsp;&nbsp;
                            <span>
                                <label class="ant-checkbox-wrapper">
                                    <span class="ant-checkbox <c:if test="${fn:contains(record.position,'2')}">ant-checkbox-checked</c:if>">
                                        <span class="ant-checkbox-inner"></span>
                                            <input type="checkbox" class="ant-checkbox-input checkTime"
                                                   data-input="2" value="<c:if test="${fn:contains(record.position,'2')}">1</c:if>"/>
                                    </span>
                                </label>
                            </span>
                            第一次下班&nbsp;&nbsp;
                                                        <span>
                                <label class="ant-checkbox-wrapper">
                                    <span class="ant-checkbox <c:if test="${fn:contains(record.position,'3')}">ant-checkbox-checked</c:if>">
                                        <span class="ant-checkbox-inner"></span>
                                            <input type="checkbox" class="ant-checkbox-input checkTime"
                                                   data-input="3" value="<c:if test="${fn:contains(record.position,'3')}">1</c:if>"/>
                                    </span>
                                </label>
                            </span>
                            第二次上班&nbsp;&nbsp;
                                                        <span>
                                <label class="ant-checkbox-wrapper">
                                    <span class="ant-checkbox <c:if test="${fn:contains(record.position,'4')}">ant-checkbox-checked</c:if>">
                                        <span class="ant-checkbox-inner"></span>
                                            <input type="checkbox" class="ant-checkbox-input checkTime"
                                                   data-input="4" value="<c:if test="${fn:contains(record.position,'4')}">1</c:if>"/>
                                    </span>
                                </label>
                            </span>
                            第二次下班&nbsp;&nbsp;
                                                        <span>
                                <label class="ant-checkbox-wrapper">
                                    <span class="ant-checkbox <c:if test="${fn:contains(record.position,'5')}">ant-checkbox-checked</c:if>">
                                        <span class="ant-checkbox-inner"></span>
                                            <input type="checkbox" class="ant-checkbox-input checkTime"
                                                   data-input="5" value="<c:if test="${fn:contains(record.position,'5')}">1</c:if>"/>
                                    </span>
                                </label>
                            </span>
                            第三次上班&nbsp;&nbsp;
                                                        <span>
                                <label class="ant-checkbox-wrapper">
                                    <span class="ant-checkbox <c:if test="${fn:contains(record.position,'6')}">ant-checkbox-checked</c:if>">
                                        <span class="ant-checkbox-inner"></span>
                                            <input type="checkbox" class="ant-checkbox-input checkTime"
                                                   data-input="6" value="<c:if test="${fn:contains(record.position,'6')}">1</c:if>"/>
                                    </span>
                                </label>
                            </span>
                            第三次下班&nbsp;&nbsp;
                                                        <span>
                                <label class="ant-checkbox-wrapper">
                                    <span class="ant-checkbox <c:if test="${fn:contains(record.position,'7')}">ant-checkbox-checked</c:if>">
                                        <span class="ant-checkbox-inner"></span>
                                            <input type="checkbox" class="ant-checkbox-input checkTime"
                                                   data-input="7" value="<c:if test="${fn:contains(record.position,'7')}">1</c:if>"/>
                                    </span>
                                </label>
                            </span>
                            加班上班&nbsp;&nbsp;
                                                        <span>
                                <label class="ant-checkbox-wrapper">
                                    <span class="ant-checkbox <c:if test="${fn:contains(record.position,'8')}">ant-checkbox-checked</c:if>">
                                        <span class="ant-checkbox-inner"></span>
                                            <input type="checkbox" class="ant-checkbox-input checkTime"
                                                   data-input="8" value="<c:if test="${fn:contains(record.position,'8')}">1</c:if>"/>
                                    </span>
                                </label>
                            </span>
                            加班下班&nbsp;&nbsp;
                        </div>
                    </div>
                </div>
                <div class="ant-row ant-form-item">
                    <div class="ant-col-2 ant-form-item-label"><label class="">补卡原因</label></div>
                    <div class="ant-col-14">
                        <div class="ant-form-item-control ">
                            <textarea name="reason" rows="3" class="ant-input">${record.reason}</textarea>
                        </div>
                    </div>
                </div>
                <div class="ant-row" style="margin: 24px 0px;">
                    <div class="ant-col-16 ant-col-offset-8">
                        <button type="submit" class="ant-btn ant-btn-primary"><span>保存</span></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $(function () {
        var position = $("#position");
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
//            minuteStep:1,
            timezone: "中国标准时间",
        });

        $('.checkTime').click(function () {
            var node = $(this);
            if (node.val() == '1') {
                node.parent().removeClass('ant-checkbox-checked');
                node.val("");
            } else {
                node.parent().addClass(' ant-checkbox-checked');
                node.val("1");
            }
            var values = "";
            $(".checkTime").each(function(index,element) {
                if($(element).val() != ""){
                    values += $(element).attr("data-input") + ",";
                }
            });
            if(values.substring(values.length - 1,values.length) == ","){
                console.log("aa");
                values = values.substring(0, values.length - 1);
            }
            console.log(values);
            position.val(values);
        });
    });

    //选择人员的回调清空方法
    function resourceClearCallBack(){
        $('#resourcesBtn').text('请选择');
        $('#resourceIds').val('');
    }
</script>
