<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <ul class="slides">
            <li style="background:url(${pageContext.request.contextPath}/images/user_center/user_banner_01.jpg) no-repeat;background-size:100% 370px;"></li>
        </ul>
        <div class="user_center_detail clearfloat">
        	<div class="user_center_detail_left">
        		<c:if test="${empty(sessionScope.user.sex) || sessionScope.user.sex == '0'}">
        		<c:if test="${sessionScope.user.userType == '0'}">
        			<img src="${pageContext.request.contextPath}/images/user_center/person_center_student_01.jpg" alt=""/>
        		</c:if>
            	<c:if test="${sessionScope.user.userType == '1'}">
        			<img src="${pageContext.request.contextPath}/images/user_center/person_center_teacher_02.jpg" alt=""/>
        		</c:if>
            	</c:if>
            	<c:if test="${sessionScope.user.sex == '1'}">
            	<img src="${pageContext.request.contextPath}/images/user_center/person_center_student_02.jpg" alt=""/>
            	</c:if>
                <div class="user_info_con">
                	<span>${sessionScope.user.realName}</span>
                	<c:if test="${not empty(sessionScope.user.sex) && sessionScope.user.sex == '0'}">
            		<b class="ml_12px">男</b>
            		</c:if>
                    <c:if test="${not empty(sessionScope.user.sex) && sessionScope.user.sex == '1'}">
            		<b class="ml_12px">女</b>
            		</c:if>
                </div>
                <div class="user_info_con pt_12px">
                    <b class='universityname'>华中科技大学</b>
                </div>
            </div>
            <div class="user_center_detail_middle">
            	<div class="user_ino_title">基本资料</div>
                <div class="user_ino_detail_con">
                	<div class="user_ino_detail clearfloat">
                    	<div class="user_ino_detail_left">
                        	<span>${sessionScope.user.realName}</span>
                        </div>
                         <c:if test="${sessionScope.user.userType == '0'}">
                        <div class="user_ino_detail_right">
                        	<span>UID：</span>
                            <span>${sessionScope.user.studentId}</span>
                        </div>
                        </c:if>
                    </div>
                    <c:if test="${sessionScope.user.userType == '0'}">
                    <div class="user_ino_detail clearfloat">
                    	<div class="user_ino_detail_left">
                        	<span>所选课程：</span>
                            <span class='learncount'>5</span>
                        </div>
                        <div class="user_ino_detail_right">
                        	<span>完结课程：</span>
                            <span class='overcount'>3</span>
                        </div>
                    </div>
                    <div class="user_ino_detail clearfloat">
                    <!-- 
                    	<div class="user_ino_detail_left">
                        	<span>社区活跃度：</span>
                            <span>中级/span>
                        </div>
                    -->    
                        <div class="user_ino_detail_left">
                        	<span>收藏课程：</span>
                            <span class='collectcount'>12</span>
                        </div>
                    </div>
                    </c:if>
                    <c:if test="${sessionScope.user.userType == '1'}">
                    <div class="user_ino_detail clearfloat">
                    <div class="user_ino_detail_left">
                        	<span>负责的课程：</span>
                            <span class='learncount'>5</span>
                        </div>
                    </div>
                    </c:if>
                </div>
            </div>
            <div class="user_center_detail_right">
            	<ul class="user_time_zone clearfloat">
                	<li>待办事项</li>
                    <li class="curr">八月</li>
                </ul>
                <div class="user_calendar" id="user_calendar">
                     <jsp:include page="../user/personalcentertodo.jsp"></jsp:include>
                </div>
            </div>
        </div>
    </div>  
</body>
<script type="text/javascript">
//初始化数据
$.ajax({
	url:'${pageContext.request.contextPath}/course/countcourse.action',
	type:'post',
	datatype:'json',
	data: '',
	success:function(response){
		var data = jQuery.parseJSON(response);
		$(".universityname").html(data.universityname);
		$(".learncount").html(data.courseCount.learncount);
		$(".overcount").html(data.courseCount.overcount);
		$(".collectcount").html(data.courseCount.collectcount);
	},		
});
</script>
</html>