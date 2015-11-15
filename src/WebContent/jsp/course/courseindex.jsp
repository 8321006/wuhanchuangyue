<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>内页</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/inner.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/wrong404.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/banner.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/inner.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pop_block_login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
</head>
<body>

	<!----头部区域-->
	<div class="inner_header">

		<jsp:include page="../head.jsp"></jsp:include>

		<!---banner 轮询 开始---->
		<div class="flexslider">
			<ul class="slides">
				<li style="background:url(${pageContext.request.contextPath}/images/inner/banner_01.jpg) no-repeat;"></li>
				<li style="background:url(${pageContext.request.contextPath}/images/inner/banner_01.jpg) no-repeat;"></li>
				<li style="background:url(${pageContext.request.contextPath}/images/inner/banner_01.jpg) no-repeat;"></li>
			</ul>
		</div>
		<!---banner 轮询 结束---->
	</div>

	<!---中间内容区域  开始---->
	<div class="container_index">
		<div class="con_content">
			<c:if test="${not empty(courses)}">
				<div class="con_title">${typeName}</div>
				<c:forEach items="${courses}" var="p" varStatus="status">
					<c:if test="${status.index%3==0}">
						<ul class="img_txt_detail clearfloat">
					</c:if>
					<li <c:if test="${(status.index+1)%3==0}">class="end" </c:if>>
						<div class="img_pop_block_con">
							<div class="img_pop_block_bg"></div>
							<div class="img_pop_block_content">
								<div class="begin_learn_btn">
									<a>开始学习</a>
								</div>
								<div class="img_pop_block_content_txt">艺术形象贯穿于艺术活动的全过程。艺术家
									在创造的过程中始终离不开具体的形象。正 如郑板桥画竹子，他观察、体验竹子的形象</div>
							</div>
						</div> 
						<img class="index2_img"
						src="${pageContext.request.contextPath}${p.courseCoverUrl}"
						onclick="godetail('${p.courseId}')" style="cursor: pointer" alt="" />
						<div class="index2_img_txt">
							<p style="cursor: pointer" onclick="godetail('${p.courseId}')">${p.courseName}</p>
							<div class="txt_name_peo">
								<div class="txt_name_peo_left">
									<span>华中科技大学</span> <span class="person_name">${p.courseAuthor}</span>
								</div>
								<div class="txt_name_peo_right">
									<img
										src="${pageContext.request.contextPath}/images/inner/total_peo_ico.png"
										alt="" /> <span>${p.num}</span>
								</div>
							</div>
						</div>
					</li>
					<c:if test="${(status.index+1)%3==0}">
						</ul>
					</c:if>
				</c:forEach>
			</c:if>

		</div>
		<c:if test="${ empty(courses)}">
			<div class="emptycourseSet">该课程正在努力建设中！</div>
		</c:if>
	</div>

</body>
<script type="text/javascript">
function godetail(courseId){
	window.location.href="${pageContext.request.contextPath}/course/detail.action?courseId="+courseId;
}
</script>
</html>