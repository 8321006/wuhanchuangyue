<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>内页</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/wrong404.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/banner.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/inner.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pop_block_login.js"></script>
</head>
<body>

<!----头部区域-->	
<div class="inner_header">
	
    <jsp:include page="head.jsp"></jsp:include>
    
</div>

<!---中间内容区域  开始---->
<div class="container">
	<div class="wrong404_con">
		<div class="wrong404_img_con">
        	<img src="${pageContext.request.contextPath}/images/404notfind.png" alt=""/>
        </div>
    </div>
</div>

<form name="searchform" action="${pageContext.request.contextPath}/index/search.action" method="post">
<input name="name" type="hidden" id="searchname" >
</form>
</body>
<script type="text/javascript">
function godetail(courseId){
	window.location.href="${pageContext.request.contextPath}/course/detail.action?courseId="+courseId;
}

$(".search_ico_con").click(function(){
	var text = $(".search_course_input").val();
	if(text=="请输入关键字"){
		$("#searchname").val("");
	}else{
		$("#searchname").val(text);
	}
	document.searchform.submit();
})

$(".search_close_ico").click(function(){
	$(".search_course_input").val("");
});
</script>
</html>