<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  	<head>
   		<base href="<%=basePath%>">
   	 	<meta charset="utf-8"><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>试卷管理</title>
		<meta name="keywords" content="">
		<link rel="shortcut icon" href="<%=basePath%>resources/images/favicon.ico" />
		<link href="resources/bootstrap/css/bootstrap-huan.css" rel="stylesheet">
		<link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">
		<link href="resources/css/style.css" rel="stylesheet">
		<link href="resources/css/question-add.css" rel="stylesheet">
		<link href="resources/chart/morris.css" rel="stylesheet">
	</head>
	<body>	
		<div>
			<div class="container" style="min-height:500px;">
			<div class="row">
					<div class="col-xs-3">
						<ul class="nav default-sidenav">
							<li class="active">
								<a href="question/question-list.action"> <i class="fa fa-list-ul"></i> 问卷管理 </a>
							</li>
							<li>
								<a href="question/question-add.action"> <i class="fa fa-pencil-square-o"></i> 添加问卷 </a>
							</li>
							<li>
								<a href="${pageContext.request.contextPath}/survey/surveycourselist.action"> <i class="fa fa-cloud-upload"></i> 导入调查问卷 </a>
							</li>
						</ul>
					</div>
					<div class="col-xs-9">
						<div class="page-header">
							<h1><i class="fa fa-cloud-upload"></i> 导入调查问卷</h1>
						</div>
						<div class="page-content row">
							<form id="from-question-import"action="${pageContext.request.contextPath}/survey/surveyimport.action" method="post"enctype="multipart/form-data">
							<div class="form-line upload-question-group">
								<span class="form-label">选择课程：</span> 
								<select class="df-input-narrow">
									<c:forEach items="${courses}" var="item">
										<option value="${item.courseId}"onClick="selectcourseId('${item.courseId}');">${item.courseName}</option>
									</c:forEach>
								</select>
								<span class="form-message"></span>
							</div>
							<div class="form-line template-download">
								<span class="form-label">下载模板：</span>
								<a href="resources/template/survey.xls" style="color:rgb(22,22,22);text-decoration: underline;">点击下载</a>
							</div>
							<div class="form-line control-group">
								<span class="form-label"><span class="warning-label">*</span>上传文件：</span>
								<div class="controls file-form-line">
									<input type="file" name="file"id="file"> 
									<input type="hidden" name="courseId" id="thecourseId" value="">
									<span class="help-inline form-message"></span>
								</div>
							</div>
							<div class="form-line">
								<input value="提交" type="submit" class="df-submit">
							</div>
						</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<footer>
			<div class="container">
				<div class="row">
					<div class="col-md-12">
						<div class="copy">
							<p>
								Exam++ Copyright © <a href="http://www.examxx.net/" target="_blank">Exam++</a> - <a href="." target="_blank">主页</a> | <a href="http://www.examxx.net/" target="_blank">关于我们</a> | <a href="http://www.examxx.net/" target="_blank">FAQ</a> | <a href="http://www.examxx.net/" target="_blank">联系我们</a>
							</p>
						</div>
					</div>
				</div>

			</div>

		</footer>

		<!-- Slider Ends -->

		<!-- Javascript files -->
		<!-- jQuery -->
		<script type="text/javascript" src="resources/js/jquery/jquery-1.9.0.min.js"></script>
		<script type="text/javascript" src="resources/js/all.js"></script>
		<script type="text/javascript" src="resources/js/uploadify/jquery.uploadify3.1Fixed.js"></script>
		<script type="text/javascript" src="resources/js/question-import.js"></script>
		<!-- Bootstrap JS -->
		<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>
		<script type="text/javascript">
		  function selectcourseId(obj){
			  //$(obj).attr("value");
			  alert("1");
		    //alert(obj.value());
		 }
		
		</script>
	</body>
</html>