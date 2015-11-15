<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<%
      HttpSession s = request.getSession(); 
  int total=(Integer)s.getAttribute("totalwritesurvey");
  String Mtotal;
  if(total>0){
	  Mtotal=s.getAttribute("totalwritesurvey").toString();
  }
  else
  {
	  Mtotal="";
  }
  %>
</head>
<body>
            <div class="user_center_con_nav">
                <div class="user_center_con_nav_title">我的课程</div>
                <ul class="user_center_con_secnav">
                	<c:if test="${sessionScope.user.userType == '0'}">
                    <li type='0' class="curr"><span class="secnav_ico_01"></span><a href="${pageContext.request.contextPath}/cy/center.action">学习的课程</a></li>
                    <li type='1'><span class="secnav_ico_02"></span>收藏的课程</li>
                    <li type='2'><span class="secnav_ico_03"></span>完结的课程</li>
                    </c:if>
                    <c:if test="${sessionScope.user.userType == '1'}">
                    <li class="curr"><span class="secnav_ico_01"></span>负责的课程</li>
                    </c:if>
                </ul>
            </div>
            <div class="user_center_con_nav mt_50px">
                <div class="user_center_con_nav_title">教学管理</div>
                <ul class="user_center_con_secnav">
                	<li type='3'><span class="secnav_ico_04"></span>教学通知</li>
                    <li type='4'class="curr"><span class="secnav_ico_05"></span><a href="${pageContext.request.contextPath}/transaction/studentlist.action">教务处理</a></li>
                    <li type='5'><span class="secnav_ico_06"></span><a href="${pageContext.request.contextPath}/survey/surveylist.action">教学调查</a><c:if test="${totalwritesurvey>0}"><b id="surveytotal" class="circle_num"><%=Mtotal%></b></c:if></li>
                    <li type='6'><span class="secnav_ico_07"></span><a href="${pageContext.request.contextPath}/coursedata/detail.action">教学资料</a></li>
                    <li type='7'><span class="secnav_ico_04"></span><a href="${pageContext.request.contextPath}/cy/centerNotice.action">新闻通知</a></li>
                </ul>
            </div>
</body>
<script type="text/javascript">
function toMyResource(){
	window.location.href="${pageContext.request.contextPath}/coursedata/detail.action";
}
$('.user_center_con_secnav li').click(function(){
	$("#")
});
/****左侧菜单导航****/

$('.user_center_con_secnav li').click(function(){
	var index=$(this).index();
	alert("index"+index);
		if($(this).hasClass('curr')){
		//	$(this).css({'background':'#2c3e50','border-top-color':'#34495e','border-left-color':'#5faee3'});
			//$(this).siblings('li').css('background','#34495e');
			$(this).removeClass("curr");
			}else{
				//$(this).siblings('li').css('background','#34495e');
				$(this).addClass("curr");
				}	
},
function(){
	var index=$(this).index();
	if($(this).hasClass('curr')){
		//$(this).css({'background':'#34495e','border-top-color':'#34495e','border-left-color':'#5faee3'});
		$(this).removeClass("curr");
		}else{
			$(this).addClass("curr");
			}
});	

</script>
</html>