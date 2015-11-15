<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>内页</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/inner.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js"></script>
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
    
     <!---中间搜索区域---->
    <div class="bg_inner_search">
    	<img class="bg_inner_barnner" src="${pageContext.request.contextPath}/images/inner/banner_02.jpg" alt=""/>
        <div class="bg_inner_search_zone">
        	<div class="search_course_con">
                <div class="search_course clearfloat">
                	<div class="search_course_info">
                    	<span>课程</span>
                        <img src="${pageContext.request.contextPath}/images/search_arrow_right_ico.png" alt=""/>
                    </div>
                    <div class="search_course_input_con">
                    	<input type="text" class="search_course_input" value="<c:if test="${name==''}">请输入关键字</c:if><c:if test="${name !=''}">${name}</c:if>" onclick="value=''"  onblur="this.value = this.value =='' ? '请输入关键字' : this.value "/>
                        <img class="search_close_ico" src="${pageContext.request.contextPath}/images/search_close_ico.png" alt=""/>
                    </div>
                    
                    <div class="search_ico_con" onclick="window.location.href='inner_index.html'"><img src="${pageContext.request.contextPath}/images/search_ico.png" alt=""/></div>
                </div>
                <div class="hot_search_txt_con clearfloat">
                	<span>热门搜索：</span>
                    <ul>
                    	<li>美剧英语</li>
                        <li>旅游餐饮文化</li>
                        <li>变态心理学</li>
                        <li>职场交际与礼仪</li>
                    </ul>
                </div>
            </div>
        </div> 	
    </div>
    
</div>

<!---中间内容区域  开始---->
<div class="container_index">
<div class="con_content">
	<c:if test="${not empty(dourselist)}">
				<div class="con_title">${typeName}</div>
				<c:forEach items="${dourselist}" var="p" varStatus="status">
					<c:if test="${status.index%3==0}">
						<ul class="img_txt_detail clearfloat">
					</c:if>
					<li <c:if test="${(status.index+1)%3==0}">class="end" </c:if>>
						<div class="img_pop_block_con">
							<div class="img_pop_block_bg"></div>
							<div class="img_pop_block_content">
								<div class="begin_learn_btn1">
									<a>开始学习</a>
								</div>
								<div class="img_pop_block_content_txt1">艺术形象贯穿于艺术活动的全过程。艺术家
									在创造的过程中始终离不开具体的形象。正如郑板桥画竹子.</div>
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
		<c:if test="${ empty(dourselist)}">
			<div class="emptycourseSet">该课程正在努力建设中！</div>
		</c:if>
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