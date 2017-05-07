<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common/init.jsp" %>
<script type="text/javascript">
    $(document).ready(function () {
        $(".sub-module li").click(function () {
            $('#module .active').removeClass("active");
            $(this).addClass("active");
        });

        $('.collapse').on("show.bs.collapse", function () {
            $(this).siblings("div").addClass("on");
        });

        $('.collapse').on("hide.bs.collapse", function () {
            $(this).siblings("div").removeClass("on");
        });

        $('#home').click(function(){
            $('#module .active').removeClass("active");
            $(this).addClass("active");
        });
    });
</script>
<div id="menu">
    <ul id="module">
        <li class="menu-item" style="padding: 0" id="home">
            <a class="link-item" style="padding: 0 16px 0 28px;" href="/home.html" data-pjax="#main-content">
				<span class="module-name">
				<i class="iconf icon-fire module-icon"></i>
			    </span>
                首页
            </a>

        </li>
        <c:forEach items="${menu}" var="map" varStatus="status">
            <li class="module-item">
                <div class="module-name menu-item" data-toggle="collapse" data-parent="#module"
                     href="#col${status.index}">
                    <i class="module-icon ${map.key.icon}" aria-hidden="true"></i>
                        ${map.key.name}
                    <i class="icon-angle-down fa fa-angle-down" aria-hidden="true"></i>
                </div>
                <ul class="sub-module collapse" id="col${status.index}">
                    <c:forEach items="${map.value}" var="list">
                        <li><a class="link-item" href="${list.url}" data-pjax="#main-content">${list.name}</a></li>
                    </c:forEach>
                </ul>
            </li>
        </c:forEach>
    </ul>
</div>
