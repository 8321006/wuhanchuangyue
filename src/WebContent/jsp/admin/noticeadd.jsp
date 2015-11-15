<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>通告发布</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_houtai_public.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_new_list.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bg_houtai_index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bg_new_list.js"></script>
</head>
<body>
<!----新闻列表新增弹出框--->
<form id="noticeaddid" action="${pageContext.request.contextPath}/notice/noticeadd.action" method="post">
	<div class="pop_msg_bnew_list">
		<div class="bnew_list_title">发布新闻<img class="bnew_list_pop_close" src="${pageContext.request.contextPath}/images/houtai/new_list_pop_close.png" alt=""/></div>
	    <div class="bnew_list_content">
	    	<div class="bnew_list_link clearfloat">
	        	<div class="bnew_list_left_txt">新闻正题</div>
	            <div class="bnew_list_right">
	            	<input type="text" class="bnew_list_add_input width_680px" name="noticeTitle"/>
	            </div>
	        </div>
	        <div class="bnew_list_link clearfloat">
	        	<div class="bnew_list_left_txt">新闻正文</div>
	            <div class="bnew_list_right">
	            	<textarea class="bnew_list_add_title_textarea" name="noticeContent"></textarea>
	            </div>
	        </div>
	        <!-- 发布时间应该是系统时间，没必要手动去填写
		        <div class="bnew_list_link clearfloat">
		        	<div class="bnew_list_left_txt">发布时间</div>
		            <div class="bnew_list_right">
		            	<input type="text" class="bnew_list_add_input width_228px"/>
		            </div>
		        </div>
	         -->
	        <div class="bnew_list_link clearfloat">
	        	<div class="bnew_list_send_btn">
	            	<span><a onClick="noticefb();">发&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp布</a></span>
	            	
	            	<!-- 
	                <b>取消</b>
	                 -->
	            </div>
	        </div>
	    </div>
	</div>
</form>
</body>

<script type="text/javascript">
   function noticefb()
   {
	   noticeaddid.submit();
	 //  window.location.href="${pageContext.request.contextPath}/notice/noticeadd.action";
	   $(".pop_msg_bnew_list").close();
	 //  alert(1)
   }
   
	/*****弹出框关闭****/
	 $('.bnew_list_pop_close').click(function(){
		 $('.pop_msg_bnew_list').css('display','none');
		 $('.pop_msg_bg_bnew_list').css('display','none');
		 $('.bnew_list_content').css('display','none');		 
	 });
   
</script>

</html>
