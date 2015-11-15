<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>个人中心</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/user_center.css"/>
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
                <c:forEach items="${courselist}" var="p" varStatus="status">
                    <div class="user_center_course_list clearfloat">
                        <div class="uc_course_list_left"><img src="${pageContext.request.contextPath}${p.courseCoverUrl}" alt=""/></div>
                        <div class="uc_course_list_middle">
                            <h7>${p.courseName}</h7>
                            <div class="uc_course_list_type clearfloat">
                                <div class="uc_course_list_type_left">试卷名称：&nbsp;&nbsp;${p.courseAuthor}</div>
                                <div class="uc_course_list_type_right"><img class="uc_course_total_peo_ico" src="${pageContext.request.contextPath}/images/inner/total_peo_ico.png"/>${p.num}人</div>
                            </div>
                            <div class="uc_course_list_type clearfloat">
                                <div class="uc_course_list_type_left">课程负责人：</div>
                                <c:if test="${sessionScope.user.userType == '0'}">
                                <div class="uc_course_list_type_right">属性：尚未考试</div>
                                </c:if>
                                <c:if test="${sessionScope.user.userType == '1'}">
                                <div class="uc_course_list_type_right">属性：尚未批阅</div>
                                </c:if>
                            </div>
                            <div class="uc_course_list_type clearfloat">
                                <div class="uc_course_list_type_left">开始时间：<c:if test="${not empty(p.studystarttime)}"><fmt:formatDate value="${p.studystarttime}" pattern="yyyy-MM-dd"/></c:if></div>
                                <div class="uc_course_list_type_right">结束时间：<c:if test="${not empty(p.studyendtime)}"><fmt:formatDate value="${p.studyendtime}" pattern="yyyy-MM-dd"/></c:if></div>
                            </div>
                        </div>
                        <div class="uc_course_list_right">
                            <a onclick="startexam('${p.courseId}','${p.classId}')">开始考试</a>
                        </div>
                    </div>
                    </c:forEach>
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
$("#examlist").attr("class","curr");
function startexam(courseId,classId){
	window.location.href="${pageContext.request.contextPath}/paper/preview.action?courseId="+courseId+"&classId="+classId+"&paperId="+1;
}
</script>
</body>
</html>
