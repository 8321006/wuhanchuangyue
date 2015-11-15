<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%-- <%@taglib uri="spring.tld" prefix="spring"%> --%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
   		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta charset="utf-8"><meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title>试题预览</title>
		<meta name="keywords" content="">
		<link rel="shortcut icon" href="<%=basePath%>resources/images/favicon.ico" />
		<link href="resources/bootstrap/css/bootstrap-huan.css" rel="stylesheet">
		<link href="resources/font-awesome/css/font-awesome.min.css" rel="stylesheet">
		<link href="resources/css/exam.css" rel="stylesheet" type="text/css">
		<link href="resources/css/style.css" rel="stylesheet">
		
	</head>
	<body>
		<header>
			<div class="container">
				<div class="row">
					<div class="col-xs-5">
						<div class="logo">
							<h1><a href="#">网站管理系统</a></h1>
							<div class="hmeta">
								专注互联网在线考试解决方案
							</div>
						</div>
					</div>
					<div class="col-xs-7" id="login-info">
						<c:choose>
							<c:when test="${not empty sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}">
								<div id="login-info-user">
									
									<a href="user-detail/${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}" id="system-info-account" target="_blank">${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}</a>
									<span>|</span>
									<a href="j_spring_security_logout"><i class="fa fa-sign-out"></i> 退出</a>
								</div>
							</c:when>
							<c:otherwise>
								<a class="btn btn-primary" href="user-register">用户注册</a>
								<a class="btn btn-success" href="user-login-page">登录</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</header>
		<!-- Navigation bar starts -->

		<div class="navbar bs-docs-nav" role="banner">
			<div class="container">
				<nav class="collapse navbar-collapse bs-navbar-collapse" role="navigation">
					<ul class="nav navbar-nav">
						<li>
							<a href="#"><i class="fa fa-home"></i>网站首页</a>
						</li>
						<li class="active">
							<a href="question/question-list"><i class="fa fa-edit"></i>试题管理</a>
						</li>

						<li>
							<a href="question/exampaper-list"><i class="fa fa-file-text-o"></i>试卷管理</a>
						</li>
						<li>
							<a href="admin/user-list"><i class="fa fa-user"></i>会员管理</a>
						</li>
						<li>
							<a href="admin/field-list-1"><i class="fa fa-cloud"></i>题库管理</a>
						</li>
						<li>
							<a href="admin/sys-backup"><i class="fa fa-cogs"></i>网站设置</a>
						</li>
					</ul>
				</nav>
			</div>
		</div>
		<div>
			<!-- Slider (Flex Slider) -->
			<div class="container" style="min-height:500px;">
				<div class="row">
					<div class="col-xs-3">
						<ul class="nav default-sidenav">
							<li>
								<a href="question/question-list.action"> <i class="fa fa-list-ul"></i> 试题管理 </a>
							</li>
							<li class="active">
								<a> <i class="fa fa-file-text"></i> 试题预览 </a>
							</li>
							<li>
								<a href="question/question-add.action"> <i class="fa fa-pencil-square-o"></i> 添加试题 </a>
							</li>
							<li>
								<a href="question/question-import.action"> <i class="fa fa-cloud-upload"></i> 导入试题 </a>
							</li>
						</ul>
					</div>
					         <div class="col-xs-9">
						           <div class="page-header">
							       <h1 style="margin-left: -15px;"><i class="fa fa-file-text"></i> 试题预览 </h1>
						     </div>					
										<div class="question-title">
											<div class="question-title-icon"></div>
											<span class="question-type" style="display: none;">singlechoice</span>
											<span class="knowledge-point-id" style="display: none;">1</span>
											<span class="question-type-id" style="display: none;">1</span>
											<span>${question.questionTypeName}</span>
											<span class="question-point-content">
												<span>(</span>
												<span class="question-point">${question.points }</span>
												<span>分)</span>
											</span>
											<span class="question-id" style="display:none;">${question.questionId }</span>
										</div>
										<form class="question-body">
											<input class="question-json" value='${question.content}' name="content" type="${question.questionTypeId}" style="display:none;"/>			
										</form> 								
										<div class="answer-desc">
											<div class="answer-desc-summary">
												<span>正确答案：</span>
												<span class="answer_value">${question.answer }</span><br>
											</div>
											<div class="answer-desc-detail">
												<label class="label label-warning">
												<i class="fa fa-flag"></i>
												<span> 解析</span></label>
												<div class="answer-desc-content"><p>${question.analysis }</p></div>
											</div>
											<div class="answer-desc-detail">
												<label class="label label-success">
												<i class="fa fa-bookmark"></i>
												<span> 考点</span></label>
												<div class="answer-desc-content"><p>初始考点</p></div>
											</div>
										</div>
									<div id="exampaper-footer" style="height: 187px;text-align:center;margin-top:40px;">
										<c:choose>
											<c:when test="${question.creator == sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}">
												<button class="btn btn-danger" id="delete-question-btn"><i class="fa fa-trash-o"></i> 删除该题</button>
												<button class="btn btn-info" onclick="javascript:window.close();"><i class="fa fa-times"></i> 关闭页面</button>
											</c:when>
											<c:otherwise>
												<button class="btn btn-danger" id="delete-question-btn" disabled="disabled" title="您只能删除你自己添加的题"><i class="fa fa-trash-o"></i> 删除该题</button>
												<button class="btn btn-info" onclick="javascript:window.close();"><i class="fa fa-times"></i> 关闭页面</button>
												<p>您只能删除你自己添加的题</p>
											</c:otherwise>
										</c:choose>	
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
		<script type="text/javascript" src="resources/js/jquery/jquery-1.9.0.min.js"></script>
		<script type="text/javascript" src="resources/js/all.js"></script>
		<script type="text/javascript" src="resources/js/jquery-ui-1.9.2.custom.min.js"></script>
		<script type="text/javascript" src="resources/js/uploadify/jquery.uploadify3.1Fixed.js"></script>
		<script type="text/javascript" src="resources/js/question-upload-img.js"></script>
		<script type="text/javascript" src="resources/js/field-2-point.js"></script>
		<script type="text/javascript" src="resources/js/question-add.js"></script>
		<script type="text/javascript" src="resources/bootstrap/js/bootstrap.min.js"></script>
		<script>
				$(document).ready(function(){ 
				 $(".question-json").each(function(index){	
					var obj = eval('(' + $(this).val() + ')');
					var obj1=$(this).attr("type");
					var html =  "<p class='question-body-text'>"+obj.title+"</p>"+
								 "<ul class='question-opt-list'>";
				if(obj1==1){
				 $.each(obj.choiceList,function(index,value) {  
					html = html + "<li class='question-list-item'>"+
					"<input type='radio' value='A' name='question-radio1' class='question-input'>"+
					"<span class='question-li-text'>"+value.option+":"+value.answer+"</span>"+"</li>";
					  }); 
					 }
				else if(obj1==2){
					$.each(obj.choiceList,function(index,value) {
					html = html + "<li class='question-list-item'>"+
					"<input type='checkbox'  name='question-radio1' class='question-input'>"+
					"<span class='question-li-text'>"+value.answer+"</span>"+"</li>";
					}); 					
				}
				else if(obj1==3){
					
					html = html + "<li class='question-list-item'>"+
					"<input type='radio' value='T' name='question-radio1' class='question-input'>"+
					"<span class='question-li-text'>"+"正确"+"</span>"+"</li>"+"<input type='radio' value='F' name='question-radio1' class='question-input'>"+
					"<span class='question-li-text'>"+"错误"+"</span>"+"</li>"	;
				}
				else{
					html = html + "<textarea class='question-textarea'></textarea>"
				}
					html = html + "</ul>";
					   $(this).after(html);});
				 
				 });								
		</script>
	</body>
</html>