<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/11/28
  Time: 13:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="header"></div>
<div>
    <div class="left-center"></div>
    <div class="right-center">

        <div class="row">
            <div class="col-lg-3 col-md-3">
                <a href="empls/base">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content panel-success">
                            <i class="fa fa-bar-chart pull-left"></i>
                            <span class="total text-center">试用期 /总人数</span>
                            <span class="title text-center"><span id="trialCount">22</span>人&nbsp;/ <span id="compEmplCount">381</span>人</span>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-lg-3 col-md-3">
                <a href="empls/base">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content panel-info">
                            <i class="fa fa-bar-chart pull-left"></i>
                            <span class="total text-center">新人人数 / 离职人数</span>
                            <span class="title text-center"><span id="newEmplCount">28</span>人&nbsp;/ <span id="leaveOfficeCount">9</span>人</span>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-lg-3 col-md-3">

                <a href="overviewsSalary">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content panel-primary">
                            <i class="fa fa-bar-chart pull-left"></i>
                            <span class="total text-center">无 / 无</span>
                            <span class="title text-center" style="overflow: hidden;   text-overflow: ellipsis;  white-space: nowrap;"><span id="lastMonthTotalWages">xxx</span>&nbsp;/ <span id="thisMonthTotalWages">xxxxx</span></span>
                        </div>
                    </div>
                </a>
            </div>
            <div class="col-lg-3 col-md-3">
                <a href="overviewsRecruit">
                    <div class="ibox float-e-margins">
                        <div class="ibox-content panel-warning">
                            <i class="fa fa-bar-chart pull-left"></i>
                            <span class="total text-center">在招岗位 / 待招人数</span>
									<span class="title text-center"><span id="recruitingPostCount">8</span>个&nbsp;/ <span id="theNumberToBeRecruited">6</span>人
									</span>
                        </div>
                    </div>
                </a>
            </div>
        </div>
        <div class="row">
            <div class="col-lg-4 col-md-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <div class="ibox-tools">
                            <a class="fullscreen-link">
                                <i class="fa fa-expand"></i>
                            </a>
                            <a id="loadOrgBtn">
                                <i class="fa fa-refresh"></i>
                            </a>
                        </div>
                        <h5><i class="fa fa-pie-chart"></i>&nbsp;&nbsp;部门统计</h5>
                    </div>
                    <div class="ibox-content">
                        <div id="chartOrg" class="height-chart">

                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4 col-md-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <div class="ibox-tools">
                            <a class="fullscreen-link">
                                <i class="fa fa-expand"></i>
                            </a>
                            <a id="loadOrgBtn">
                                <i class="fa fa-refresh"></i>
                            </a>
                        </div>
                        <h5><i class="fa fa-pie-chart"></i>&nbsp;&nbsp;在职统计</h5>
                    </div>
                    <div class="ibox-content">
                        <div id="chartOrg" class="height-chart">

                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4 col-md-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <div class="ibox-tools">
                            <a class="fullscreen-link">
                                <i class="fa fa-expand"></i>
                            </a>
                            <a id="loadOrgBtn">
                                <i class="fa fa-refresh"></i>
                            </a>
                        </div>
                        <h5><i class="fa fa-line-chart"></i>&nbsp;&nbsp;出勤统计</h5>
                    </div>
                    <div class="ibox-content">
                        <div id="chartOrg" class="height-chart">

                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-4 col-md-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><i class="fa fa-external-link"></i>&nbsp;&nbsp;快速入口</h5>
                    </div>
                    <div class="ibox-content">
                        <div id="chartOrg" class="height-chart">

                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4 col-md-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <div class="ibox-tools">
                            <a class="fullscreen-link">
                                <i class="fa fa-expand"></i>
                            </a>
                            <a id="loadOrgBtn">
                                <i class="fa fa-refresh"></i>
                            </a>
                        </div>
                        <h5><i class="fa fa-list"></i>&nbsp;&nbsp;待办事宜</h5>
                    </div>
                    <div class="ibox-content">
                        <div id="chartOrg" class="height-chart">

                        </div>
                    </div>
                </div>
            </div>

            <div class="col-lg-4 col-md-4">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <div class="ibox-tools">
                            <a class="fullscreen-link">
                                <i class="fa fa-expand"></i>
                            </a>
                            <a id="loadOrgBtn">
                                <i class="fa fa-refresh"></i>
                            </a>
                        </div>
                        <h5><i class="fa fa-pie-chart"></i>&nbsp;&nbsp;日历</h5>
                    </div>
                    <div class="ibox-content">
                        <div id="chartOrg" class="height-chart">

                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="row">
            <div class="col-lg-12 col-md-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5><i class="fa fa-list"></i>&nbsp;&nbsp;常用工具</h5>
                    </div>
                    <div class="ibox-content">
                    </div>
                </div>
            </div>
            <!-- end row -->
        </div>
    </div>
</div>

<script>
    var depChart = echarts.init(document.getElementById("chartOrg"));
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data: ['test1', 'test2', 'test3', 'test4', 'test5']
        },
        series: [{
            name: '访问来源',
            type: 'pie',
            radius: ['50%', '70%'],
            avoidLabelOverlap: false,
            label: {
                normal: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    show: true,
                    textStyle: {
                        fontSize: '30',
                        fontWeight: 'bold'
                    }
                }
            },
            labelLine: {
                normal: {
                    show: false
                }
            },
            data: [{
                value: 335,
                name: 'test1'
            }, {
                value: 310,
                name: 'test2'
            }, {
                value: 234,
                name: 'test3'
            }, {
                value: 135,
                name: 'test4'
            }, {
                value: 1548,
                name: 'test5'
            }]
        }]
    };
    depChart.setOption(option);
</script>
