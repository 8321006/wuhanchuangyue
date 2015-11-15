<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理——学生列表</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_houtai_public.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_new_list.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bg_houtai_index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bg_new_list.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bg_course.js"></script>
</head>
<body>
<input type='hidden' id='universityId1' name='universityId' value='${universityId}'/>
<input type='hidden' id='userId' name="userId" value='${u.userId}'/>
	<!----头部区域-->
	<div class="inner_header">
		<jsp:include page="../head-school.jsp"/>
	</div>
	<div class="houtai_con clearfloat">
	<!---中间内容区域  开始---->
	<jsp:include page="bg_left.jsp"/>



	<div class="houtai_content_con">
		<div class="houtai_right_con">
			<div class="houtai_bread_title">学生列表</div>
			<div class="new_list_funtion_con">
				<div class="new_list_function_detail clearfloat">
					<div class="news_add_con" onClick="newListAddPopCenter();">
						<img class="new_list_add_ico"
							src="${pageContext.request.contextPath}/images/houtai/new_list_add_ico.png"
							alt="" /> <span class="new_list_add_txt">新增</span>
					</div>
				
					<div class="news_search_con">
						<input id="realname" type ="text" class="news_search_input" value="${searcharea == null ? '请输入要搜索的姓名': searcharea}"
							onclick="value=''"
							onblur="this.value = this.value =='' ? '请输入要搜索的姓名' : this.value " />
						<div class="news_search_btn" onclick="insearch()">搜索</div>
					</div>
					<div class="term_chose_content_select_con">
						<select id="course" class="term_chose_content_select_con term_chose_content_detail">
						<c:if test="${empty(courselist)}">
							<option value="">全部</option>
						</c:if>
						<c:if test="${not empty(courselist)}">
						<c:forEach items="${courselist}" var="t">
								<option value="${t.courseId}" <c:if test="${t.courseName ==courseName}">selected</c:if>	>${t.courseName}</option>
						</c:forEach>
						</c:if>	
						</select> 
						<span class="term_chose_content_select_con term_chose_content_detail_btn">课程</span>
					</div>
					<div class="term_chose_content_select_con">
						<select id="courseterm" class="term_chose_content_select_con term_chose_content_detail">
							<option value="">全部</option>
							<c:forEach items="${termlist}" var="t">
								<option value="${t.courseTerm}" ${term == t.courseTerm ? 'selected': '' }>${t.courseTerm}</option>
							</c:forEach>
						</select> 
						<span class="term_chose_content_select_con term_chose_content_detail_btn">学期</span>
					</div>
				</div>
				<table class="new_list_detail_table" cellpadding="0" cellspacing="0">
					<thead> 
						<tr>
							<td>学生名称</td>
							<td>学号</td>
							<td>性别</td>
							<td>联系方式</td>
						<!-- 	<td>课程名称</td>
							<td>课程学年</td>							
							<td>学校</td>
							<td>操作</td> -->
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="u">
							<tr>
								<td>${u.realName}</td>
								<td>${u.studentId}</td>
								<td>${u.sex == 0 ? '男' : '女'}</td>
								<td>${u.phone}</td>
								<%-- <td>${u.courseName}</td>
								<td>${u.courseTerm}</td> 								
								<td>${u.universityName}</td>
								<td>
									<span class="new_list_table_edit" >编辑</span> 
								    <span class="new_list_table_del" >删除</span>
								</td>  --%>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<form action="${pageContext.request.contextPath}/cy/userlist.action" id="searchform" name="searchform" method="post">
				<input type='hidden' id='courseId' name='courseId' value='${courseId}'/>
				<input type='hidden' id='courseName' name='courseName' value='${courseName}'/>
				<input type='hidden' id='searcharea' name='searcharea' value='${searcharea}'/>
				<input type='hidden' id='term' name='term' value='${term}'/>
				</form>
				<c:if test="${not empty list}"> 			
         			<tags:pager pagerRange="10" pageSize="${page.pageSize}" totalPage="${page.pages}" curIndex="${page.pageNum}" formId="searchform"></tags:pager>              
           		 </c:if> 
            	<c:if test="${empty list}">
            	</c:if>
			</div>
		</div>

	</div>
</div>
</body>
<script type="text/javascript">
function insearch(){
	$("#courseName").val($("#course option:selected").text());
	$("#term").val($("#courseterm").val());
	var realName = $("#realname").val();
	$("#searcharea").val(realName == '请输入要搜索的姓名'?"":realName);
	 var courseId = $("#course").val();
	$("#courseId").val(courseId == '全部'?'':courseId); 
	//$("#courseId").val($("#course").val())
	$("#searchform").submit();
	
}

	$(function(){
		$("#courseterm").change(function(){
			var courseterm = $("#courseterm option:selected").val();
			var u = $("#universityId1").val();			
			$.ajax({
				url:'${pageContext.request.contextPath}/cy/getCourseList.action',
				type:'post',
				datatype:'json',
				data: {"universityId" : u,"courseTerm" : courseterm},
				success:function(data){
					var obj = jQuery.parseJSON(data);
					$("#course").html("");
					$("#course").append("<option value=''>全部</option>");
		              $.each(obj,function(i){                					 
		                  $("#course").append("<option value="+obj[i].courseId+">"+obj[i].courseName+"</option>");	                 					 
		              });
				},
			});
		});
	});
	//后台左边菜单被选中
	$("#leftbar").find("li").each(function(){
		$(this).removeClass();
	});
   $("#function_management ol").removeClass('display_none');
	$("#userSet").attr("class","selected");
</script>
</html>
