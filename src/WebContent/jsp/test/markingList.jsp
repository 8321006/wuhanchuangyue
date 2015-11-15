<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%> 

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>我的课程</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/my_course_public.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/my_course_learned.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/footer.js"></script>

</head>
<body>



<!----头部区域-->	
<div class="inner_header">
	<jsp:include page="../head.jsp"></jsp:include>
</div>

<!---中间内容区域  开始---->
<div class="container_index">	
	<div class="course_teacher_piyue">
    	<div class="course_teacher_piyue_tit">批阅列表<div class="course_teacher_piyue_exportdata">
        <a onclick="exportdata();"><span>导出成绩</span></a></div></div>
    	<table class="course_teacher_piyue_table" cellpadding="0" cellspacing="0">
            <thead>
                <tr>
                    <td><div class="fontset">姓名</div></td>
                    <td><div class="fontset">成绩</div></td>
                    <td><div class="fontset">状态</div></td>
                    <td><div class="fontset">操作</div></td>
                    <td><div class="fontset">备注</div></td>
                </tr>
            </thead>
            <tbody>
            	<c:forEach items="${markingList }" var="item">
                <tr>
                    <td>${item.userName }</td>
                    <td>
	                    <c:if test="${empty item.userScore}"> <div class="fonttdset"> 暂无成绩</div> </c:if>
	                    <c:if test="${not empty item.userScore}">${item.userScore} </c:if>
                    </td>
                    <td>
                    	<c:if test = "${item.markStatus == 1}">已经批阅</c:if>
                    	<c:if test = "${item.markStatus == null}">暂未交卷</c:if>
                    	<c:if test = "${item.markStatus == 0}">暂未批阅</c:if>
                    </td>
                    <td>	
                         <c:if test = "${item.markStatus == null}"><span>无法批阅</span></c:if>
                         <c:if test = "${item.markStatus == 0}">
                         <span><a href="${pageContext.request.contextPath}/test/preview.action?testId=${item.testId}">批阅</a></span>
                         </c:if>
                         <c:if test = "${item.markStatus == 1}">
                         <span><a href="${pageContext.request.contextPath}/test/preview.action?testId=${item.testId}">点击查看</a></span>
                         </c:if>
                    </td>
                    <td>备注</td>
                </tr>
                </c:forEach>
            </tbody>
        </table>
        <input type="hidden"id="paperId"name="paperId"value="${paperId}">
         <input type="hidden"id="classId"name="classId"value="${classId}">
         <div class="bottomset">
         <form action="${pageContext.request.contextPath}/test/markingList.action" id="searchform" name="searchform" method="post">
        <input type="hidden"id="paperId"name="paperId"value="${paperId}">
        <input type="hidden"id="classId"name="classId"value="${classId}">
         </form>
         <c:if test="${not empty markingList}"> 			
         			<tags:pager pagerRange="10" pageSize="${page.pageSize}" totalPage="${page.pages}" curIndex="${page.pageNum}" formId="searchform"></tags:pager>              
           		 	</c:if>  </div>
    </div>
     
</div>
<!---底部区域---->
</body>
<script type="text/javascript">
   //导出excel数据
   function exportdata(){
	   alert("1");
	   var paperId=$("#paperId").val();
	   var classId=$("#classId").val();
	   //classId,?paperId="+$("#paperId").val()+"&classId="+$("#classId").val();
	   window.location.href="${pageContext.request.contextPath}/test/exportExcel.action?paperId="+$("#paperId").val()+"&classId="+$("#classId").val();
   }
</script>
</html>
