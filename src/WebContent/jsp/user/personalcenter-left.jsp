<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
      HttpSession s = request.getSession(); 
  int total=(Integer)s.getAttribute("totalwritesurvey");
  int noticetotal=(Integer)s.getAttribute("noticetotal");
  String Mtotal;
  String Ntotal;
  if(total>0){
	  Mtotal=s.getAttribute("totalwritesurvey").toString();
  }
  else
  {
	  Mtotal="";
  }
  if(noticetotal>0){
	  Ntotal=s.getAttribute("noticetotal").toString();
  }
  else
  {
	  Ntotal="";
  }
  %>
<div class="user_center_con_left">
 <div class="user_center_bread_tit1"></div>
                <ul id ='leftbar'>
                	<c:if test="${sessionScope.user.userType == '0'}">
                    <li id='learncourse'onclick='leandcourse()'><span class="nav_ico_01"></span><i>学习的课程</i></li>
                    <!-- 
                    <li id='collectcourse'><span class="nav_ico_02"></span><i>收藏的课程</i></li>
                     -->
                    </c:if>
                    <c:if test="${sessionScope.user.userType == '1'}">
                    <li id='learncourse' class="curr" onclick='leandcourse()'><span class="nav_ico_01"></span><i>负责的课程</i></li>
                    </c:if>
                    <li id='endcourse' onclick='endcourse()'><span class="nav_ico_03"></span><i>完结的课程</i></li>
                    <li id='jxtz' onClick="window.location.href='${pageContext.request.contextPath}/cy/centerNotice.action'"><span class="nav_ico_04"></span><i>教学通知</i><c:if test="${noticetotal>0}"><b id="noticetotal" class="circle_num"><%=Ntotal%></b></c:if></li>
                    <li id='jiaowu'onClick="window.location.href='${pageContext.request.contextPath}/transaction/studentlist.action'"><span class="nav_ico_05"></span><i>教务处理</i></li>
                     <c:if test="${sessionScope.user.userType == '0'}">
                     <li id='examlist'onclick='onlineExam()'><span class="nav_ico_06"></span><i>在线考试</i></li>
                    </c:if>
                    <c:if test="${sessionScope.user.userType == '1'}">
                     <li id='examlist'onClick="window.location.href='${pageContext.request.contextPath}/survey/surveylist.action'"><span class="nav_ico_06"></span><i>在线批阅</i><c:if test="${totalwritesurvey>0}"><b id="surveytotal" class="circle_num"><%=Mtotal%></b></c:if></li>
                    </c:if>
                    <li id='survey'onClick="window.location.href='${pageContext.request.contextPath}/survey/surveylist.action'"><span class="nav_ico_06"></span><i>教学调查</i><c:if test="${totalwritesurvey>0}"><b id="surveytotal" class="circle_num"><%=Mtotal%></b></c:if></li>
                    <li id='data'onClick="window.location.href='${pageContext.request.contextPath}/coursedata/detail.action'"><span class="nav_ico_07"></span><i>教学资料</i></li>
                </ul>
</div>
<script type="text/javascript">
function leandcourse(){
	window.location.href="${pageContext.request.contextPath}/cy/center.action";
}
function endcourse(){
	window.location.href="${pageContext.request.contextPath}/cy/endcourse.action";
}
function onlineExam(){
	window.location.href="${pageContext.request.contextPath}/cy/onlineExamlist.action";
}
</script>