<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>个人中心</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/user_center.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/wrong404.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Autoscoll.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/circle.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_center.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/banner.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hDate2.js"></script> 
<link href="${pageContext.request.contextPath}/css/hDate2.css" rel="stylesheet" />
</head>
<body>
<!----头部区域-->	
<div class="inner_header">
    <jsp:include page="../head.jsp"></jsp:include>
</div>

<!---中间内容区域  开始---->
<div class="container">	
	<div class="user_center_con">
	<div class="user_center_con_info">
            <div class="user_ico" onClick="window.location.href='user_center.html'"><img src="images/user_center/user_img_01.jpg" alt=""/></div>
            <dl class="user_center_con_list">
                <dt>基本资料</dt>
                <dd>莫妮卡 Monica</dd>
                <dd>UID:123456789</dd>
                <dd>社区活跃度：中级</dd>
                <dd>收藏课程：12</dd>
            </dl>
        </div>
    	<div class="user_center_content clearfloat">
            <jsp:include page="../user/personalcenter-left.jsp"></jsp:include>
            <div class="user_center_con_right">
                <div class="user_center_bread_tit"></div>
                <div class="user_center_course_con">
                <c:if test="${empty courselist}">
                                      <div class="wrong404_img_con">
        	<img src="${pageContext.request.contextPath}/images/endcourse.png" style="width:120px;height:120px;"alt=""/>
        	<span>亲，暂无完结课程！</span>
        	
           </div>
                </c:if>
                 <c:if test="${not empty courselist}">
                <c:forEach items="${courselist}" var="p" varStatus="status">
                    <div class="user_center_course_list clearfloat">
                        <div class="uc_course_list_left"><img src="${pageContext.request.contextPath}${p.courseCoverUrl}" alt=""/></div>
                        <div class="uc_course_list_middle">
                            <h7>古代英语文学赏析</h7>
                            <div class="uc_course_list_type clearfloat">
                                <div class="uc_course_list_type_left">讲师:&nbsp;&nbsp;${p.courseName}</div>
                                <div class="uc_course_list_type_right"><img class="uc_course_total_peo_ico" src="${pageContext.request.contextPath}/images/inner/total_peo_ico.png"/>${p.num}人</div>
                            </div>
                            <div class="uc_course_list_type clearfloat">
                                <div class="uc_course_list_type_left">视频数:${p.videonum}</div>
                                <c:if test="${sessionScope.user.userType == '0'}">
                                <div class="uc_course_list_type_right">作业数:${p.exampercent}</div>
                                </c:if>
                            </div>
                            <div class="uc_course_list_type clearfloat">
                                <div class="uc_course_list_type_left">开始时间：<c:if test="${not empty(p.studystarttime)}"><fmt:formatDate value="${p.studystarttime}" pattern="yyyy-MM-dd"/></c:if></div>
                                <div class="uc_course_list_type_right">结束时间：<c:if test="${not empty(p.studyendtime)}"><fmt:formatDate value="${p.studyendtime}" pattern="yyyy-MM-dd"/></c:if></div>
                            </div>
                        </div>
                        <div class="uc_course_list_right">
                            <a onclick="godetail('${p.courseId}','${p.classId}')">查看课程</a>
                        </div>
                    </div>
                    </c:forEach>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>

<!---底部区域---->
<div class="footer_con">
    <jsp:include page="../user/personalcenterbottom.jsp"></jsp:include>
</div>
<script type="text/javascript">
$("#leftbar").find("li").each(function(){
	$(this).removeClass();
});
$("#endcourse").attr("class","curr");
function godetail(courseId,classId){
	window.location.href="${pageContext.request.contextPath}/course/learn.action?courseId="+courseId+"&classId="+classId;
}
</script>
</body>
</html>
