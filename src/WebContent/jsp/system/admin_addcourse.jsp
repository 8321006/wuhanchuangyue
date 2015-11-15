<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%> 
<%@page import="com.cy.model.User"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生管理 - 易启学后台管理</title>
<link type="text/css" rel="stylesheet" href="css/basic.css"/>
<link type="text/css" rel="stylesheet" href="css/medioadaption.css"/>
<link type="text/css" rel="stylesheet" href="css/bg_administrator_public.css"/>
<link type="text/css" rel="stylesheet" href="css/bg_administrator_addstudent.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_administrator_teaching_inquire.css"/>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bg_administrator_index.js"></script>
<%
User current = (User)session.getAttribute("user");
if(current == null || current.getUserType() != 3){
	response.sendRedirect(basePath + "index.action");
}
%>
</head>
<body>
<!---中间内容区域  开始---->
<div class="administrator_container">	
	<div class="bg_administrator_con">
    	<div class="administrator_left_menu">
        	<div class="administrator_img_con">
            	<div class="administrator_img_content">
                	<img class="administrator_person_img" src="${pageContext.request.contextPath}/images/administrator/person_img_01.png" alt=""/>
                </div>
                <p class="administrator_person_name">${user.realName }</p>
            </div>
            <div class="administrator_left_menu_nav">
            	<ul class="administrator_menu_nav">
                    <li>
                        <span>
                            <b class="administrator_ico_1"></b>
                            <a href="university/listAll.action"><i>学校管理</i></a>
                        </span> 
                    </li>
                    <li class="curr">
                        <span>
                            <b class="administrator_ico_2"></b>
                            <a href="course/courseList.action"><i>新增课程</i></a>
                        </span> 
                    </li>
                    <li>
                        <span>
                            <b class="administrator_ico_3"></b>
                             <a href="cy/userImportList.action"><i>学生管理</i></a>
                        </span> 
                    </li>
                    <li>
                        <span>
                            <b class="administrator_ico_4"></b>
                            <a href="university/goAnalysis.action"><i>统计分析</i></a>
                        </span> 
                    </li>
                    <li>
                        <span>
                            <b class="administrator_ico_5"></b>
                            <a href="survey/coursesurveylist.action"><i>教学调查</i></a>
                        </span> 
                    </li>
                    <li>
                        <span>
                            <b class="administrator_ico_5"></b>
                            <a href="notice/gosystemNotice.action"><i>新闻通知</i></a>
                        </span> 
                    </li>
                </ul>
            </div>
        </div>
        <div class="administrator_content_con">
        	<div class="administrator_content_header clearfloat">
            	<div class="administrator_content_header_logo"><img src="${pageContext.request.contextPath}/images/administrator/admin_logo.png" alt=""/></div>
             <div class="admin_logout_btn"><a href="${pageContext.request.contextPath}/cy/exit.action">退出</a></div>
            </div>
            <div class="administrator_content_detail">
            	<div class="administrator_bread_title clearfloat">
                	<span>新增课程</span>
                </div>
            <div class="administrator_addstudent_con">   
      <form id="importUser" action="${pageContext.request.contextPath}/course/add.action" method="post" enctype="multipart/form-data">
         
                	<div class="administrator_addstudent_upload_zone clearfloat">
                        <div class="administrator_addstudent_upload_looksearch_btn">浏览
         <input type="file" id="file" name="file" class="administrator_addstudent_upload_img_zone_filebtn" name="file"  onchange="document.getElementById('textfield').value=this.value"/>
         </div>
         
         <input type='text' name='textfield' id='textfield' class='administrator_addstudent_upload_img_zone_file' /> 
                        <span class="administrator_addstudent_upload_btn">上传</span>
                       <span class="administrator_addstudent_message"><c:if test="${courseflag==1}">该课程已经导入，请不要重复导入！</c:if></span>
       </div>
       
      </form>   
      <!-- -课程列表开始 -->
      <table class="administrator_teaching_inquire_table" cellpadding="0" cellspacing="0">
                        <thead>
                        	<tr>
                                <td>课程名称</td>
                                <td>课程作者</td>
                                <td>发布时间</td>
                                <td>类型</td>
                            </tr>
                        </thead>
                        <tbody>
                       
                         <c:if test="${courselist.size()==0}">
                        <tr>
                        <td colspan="4">暂无课程信息</td>
                        </tr>
                         </c:if>
                        <c:if test="${courselist.size()>0}">
                        <c:forEach items="${courselist}" var="item"> 
                        	<tr>                         	
                                <td>${item.courseName}</td>
                                <td>${item.courseAuthor}</td>
                                <td><fmt:formatDate value="${item.courseCreateTime}" pattern="yyyy-MM-dd" /></td>
                               <td>${item. courseTypeName}</td>
                            </tr>
                        </c:forEach>
                         </c:if>
                        </tbody>
                     
                    </table>
                    <!-- 课程列表结束 -->
                    <form action="${pageContext.request.contextPath}/course/courseList.action" id="searchform" name="searchform" method="post">
					</form>
         			<c:if test="${not empty courselist}"> 			
         			<tags:pager pagerRange="10" pageSize="${page.pageSize}" totalPage="${page.pages}" curIndex="${page.pageNum}" formId="searchform"></tags:pager>              
           		 	</c:if> 
            		<c:if test="${empty courselist}">
            		</c:if> 
      
                </div>
            </div>
        </div>
    </div>
	
</div>

</body>

<script type="text/javascript">

	$(".administrator_addstudent_upload_btn").click(function(){
		
		if($("#textfield").val()=="")
			{
				alert("请选择上传文件");
			}
		else{
			$("#importUser").submit();
		    }
		
	});

	</script>
</html>