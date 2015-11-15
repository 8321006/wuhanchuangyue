<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>主页</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/pop_block_login.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/medioadaption.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/wrong404.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.mousewheel.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/indexscroll.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/banner.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pop_block_login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/inner.js"></script>
<script type="text/javascript">       
 var DispClose = true;     
 function CloseEvent()   
 {         
    if (DispClose)
	{ 
	  $(window).scrollTop(0);  
	}   
 }
</script>
</head>
<body style="overflow:hidden;" onbeforeunload="return CloseEvent();">
<!----头部区域-->	
<div class="inner_header">
	
	<c:if test="${sessionScope.user.userType != '2'}">
	<jsp:include page="head.jsp"></jsp:include>
	</c:if>
	<c:if test="${sessionScope.user.userType == '2'}">
	<jsp:include page="head-school.jsp"></jsp:include>
	</c:if>
	
    <ul class="side_nav">
         <li class="cur"><i class="selected">搜索</i><span><b class="slider_nav_ico_01"></b></span></li>
        <li><i class="selected">课程分类</i><span><b class="slider_nav_ico_02"></b></span></li>
        <li class="end"><i class="selected">社区</i><span><b class="slider_nav_ico_03"></b></span></li>
    </ul>
    <div class="index_con1">
    	<div class="index_con_bg"><img src="images/index_bannerimg_01.jpg" alt=""/></div>  
        <div class="index_middle_con">
        	<div class="search_course_con">
            	<div class="search_course_tit">寻找你最爱的课程</div>
                <div class="search_course clearfloat">
                	<div class="search_course_info">
                    	<span>课程</span>
                        <img src="images/search_arrow_right_ico.png" alt=""/>
                    </div>
                    <div class="search_course_input_con">
                    	<input type="text" class="search_course_input" value="请输入关键字" onclick="value=''"  onblur="this.value = this.value =='' ? '请输入关键字' : this.value "/>
                        <img class="search_close_ico" src="images/search_close_ico.png" alt=""/>
                    </div>
                    <div class="search_ico_con"><img src="images/search_ico.png" alt=""/></div>
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
    <div class="index_con2">
        <div class="index2_con_bg"><img src="images/index_bannerimg_02.jpg" alt=""/></div> 
        <div class="index_con2_content clearfloat">
        	<div class="index_con2_left_nav">
            	<ul>
            	<!--  
                	<c:forEach items="${courseType}" var="p" varStatus="status">
                	<li type="${p.courseType}" <c:if test="${status.first==true}">class="curr"</c:if>><b></b>${p.courseTypeName}</li>
                	</c:forEach>
                	-->
                		<li type="1" class="curr"><b></b>摄影爱好</li>
                		<li type="2" <c:if test="${status.first==true}">class="curr"</c:if>><b></b>文史交流</li>
                		<li type="3" <c:if test="${status.first==true}">class="curr"</c:if>><b></b>影视欣赏</li>
                		<li type="4" <c:if test="${status.first==true}">class="curr"</c:if>><b></b>管理学</li>
                		<li type="5" <c:if test="${status.first==true}">class="curr"</c:if>><b></b>经济学</li>
                		<li type="6" <c:if test="${status.first==true}">class="curr"</c:if>><b></b>社会学</li>
                		<li type="7" <c:if test="${status.first==true}">class="curr"</c:if>><b></b>心理学</li>
                		<li type="8" <c:if test="${status.first==true}">class="curr"</c:if>><b></b>法学</li>
                </ul>
            </div>
            <div class="index_con2_right">
            	<ul>
                	<c:forEach items="${courses}" var="ls" varStatus="status">
                	<li courseId='${ls.courseId}' class="courseselect">
                    	<div class="img_pop_block_con">
                        	<div class="img_pop_block_bg"></div>
                            <div class="img_pop_block_content">
                            	<div class="begin_learn_btn"><a>开始学习</a></div>
                                <div class="img_pop_block_content_txt">${ls.courseDesc}</div>
                            </div>
                        </div>
                    	<img class="index2_img" src="${pageContext.request.contextPath}${ls.courseCoverUrl}" alt=""/>
                        <div class="index2_img_txt">
                        	<p>${ls.courseName}</p>
                            <div class="index2_img_txt_detail">
                                <span class="index2_img_txt_detail_name">华中科技大学&nbsp;&nbsp;${ls.courseAuthor}</span>
                                <span class="index2_img_txt_detail_total">
                                    <img src="images/total_per_ico.png" alt=""/>
                                    <b>${ls.num}人</b>
                                </span>
                            </div>
                        </div>
                    </li>
                  	</c:forEach>
                  	<c:if test="${not empty(size)}">
                  	<c:forEach begin="1" end="${size}">
                    <li>
                    	<div class="img_pop_block_con">
                        	<div class="img_pop_block_bg"></div>
                            <div class="img_pop_block_content">
                            	<div class="begin_learn_btn"><a>开始学习</a></div>
                                <div class="img_pop_block_content_txt">艺术形象贯穿于艺术活动的全过程。艺术家 在创造的过程中始终离不开具体的形象。正 如郑板桥画竹子，他观察、体验竹子的形象 </div>
                            </div>
                        </div>
                    	<img class="index2_img" src="images/index2_img_01.jpg" alt=""/>
                        <div class="index2_img_txt">
                        	<p>当广告遇上插画</p>
                            <div class="index2_img_txt_detail">
                                <span class="index2_img_txt_detail_name">华中科技大学&nbsp;&nbsp;钟云翼</span>
                                <span class="index2_img_txt_detail_total">
                                    <img src="images/total_per_ico.png" alt=""/>
                                    <b>18659人</b>
                                </span>
                            </div>
                        </div>
                    </li>
                    </c:forEach>
                    </c:if>
                </ul>
                <div class="all_course_btn" style="cursor:pointer"><a>全部课程</a></div>
            </div>
        </div>
    </div> 
    <div class="index_con3">
    	<div class="index3_con_bg"><img src="images/index_bannerimg_03.jpg" alt=""/></div>  
        <div class="index_con3_content">
        	<!---banner 轮询 开始---->
            <div class="flexslider">
                <ul class="slides">
                    <li style="background:url(images/index3_img_01.jpg) no-repeat;"></li>
                    <li style="background:url(images/index3_img_02.jpg) no-repeat;"></li>
                </ul>
            </div>  
    <!---banner 轮询 结束---->  
        </div>
    </div>    
</div>

<form name="searchform" action="${pageContext.request.contextPath}/index/search.action" method="post">
<input name="name" type="hidden" id="searchname" >
</form>
<script type="text/javascript">
$(".search_ico_con").click(function(){
	var text = $(".search_course_input").val();
	if(text=="请输入关键字"){
		$("#searchname").val("");
	}else{
		$("#searchname").val(text);
	}
	document.searchform.submit();
})

$(".courseselect").live('click', function(){
	window.location.href="${pageContext.request.contextPath}/course/detail.action?courseId="+$(this).attr("courseId");
});

$(".all_course_btn").live('click', function(){
	window.location.href="${pageContext.request.contextPath}/course/coursetype.action?type="+$('.curr').attr("type");
});

$(".index_con2_left_nav").children().find("li").click(function(){
	 $(".index_con2_left_nav").children().find("li").removeClass("curr");
	 $(this).addClass("curr");
	$.ajax({
	     type: 'POST',
	     url: '${pageContext.request.contextPath}/index/coursetype.action',
	     data: {type:$(this).attr("type")},
	     success:function (data) {
	    	$(".index_con2_right").html("");
	    	 var html = "<ul>";
	    	 for(var i =0;i<data.length;i++){
	    		 var obj =data[i];
	    		 html=html+"<li courseId='"+obj.courseId+"' class='courseselect'onMouseOver='MouseOver(this)' onmouseleave='mouseleave(this)'>";
	    		 html+='<div class="img_pop_block_con" style="display: none;"><div class="img_pop_block_bg"></div><div class="img_pop_block_content"><div class="begin_learn_btn"><a onMouseOver="C(this)">开始学习</a></div>';
	    		 html=html+'<div class="img_pop_block_content_txt">'+obj.courseDesc+'</div>';
	    		 html=html+'</div></div><img class="index2_img" src="'+'${pageContext.request.contextPath}'+obj.courseCoverUrl+'" alt=""/>';
	    		 html=html+'<div class="index2_img_txt">';
	    		 html=html+'<p>'+obj.courseName+'</p>';
	    		 html=html+'<div class="index2_img_txt_detail">';
	    		 html=html+'<span class="index2_img_txt_detail_name">华中科技大学&nbsp;&nbsp;'+obj.courseAuthor+'</span>';
	    		 html=html+'<span class="index2_img_txt_detail_total"><img src="images/total_per_ico.png" alt=""/>';
	    		 html=html+'<b>'+obj.num+'人</b>';
	    		 html=html+'</span></div></div></li>';
	    	 }
	    	 
	    	 if(i<6){
	    		 for(i;i<6;i++){
	    			 html=html+"<li onMouseOver='MouseOver(this)' onmouseleave='mouseleave(this)'>";
		    		 html+='<div class="img_pop_block_con"style="display: none;"><div class="img_pop_block_bg"></div><div class="img_pop_block_content"><div class="begin_learn_btn"><a>开始学习</a></div>';
		    		 html=html+'<div class="img_pop_block_content_txt">艺术形象贯穿于艺术活动的全过程。艺术家 在创造的过程中始终离不开具体的形象。正 如郑板桥画竹子，他观察、体验竹子的形象</div>';
		    		 html=html+'</div></div><img class="index2_img" src="images/index2_img_03.jpg" alt=""/>';
		    		 html=html+'<div class="index2_img_txt">';
		    		 html=html+'<p>当广告遇上插画</p>';
		    		 html=html+'<div class="index2_img_txt_detail">';
		    		 html=html+'<span class="index2_img_txt_detail_name">华中科技大学&nbsp;&nbsp;钟云翼</span>';
		    		 html=html+'<span class="index2_img_txt_detail_total"><img src="images/total_per_ico.png" alt=""/>';
		    		 html=html+'<b>18659人</b>';
		    		 html=html+'</span></div></div></li>';
	    		 }
	    	 }
	    	 html=html+'<div class="all_course_btn" style="cursor:pointer"><a>全部课程</a></div>';
	    	 html=html+'</ul>';
	    	 $(".index_con2_right").html(html);
	     },
	     dataType: "json"
	});
})

var url = window.location.href;
if(url.substring(url.length-"${pageContext.request.contextPath}/".length) != "${pageContext.request.contextPath}/"){
	$("#top_area").attr("class","top_area_scroll");
}else{
	$("#top_area").attr("class","top_area");
}

$(".search_close_ico").click(function(){
	$(".search_course_input").val("");
});
function MouseOver(obj){
	$(obj).children('.index2_img_txt').css('background-color','#d6f1f8');
	$(obj).children('.img_pop_block_con').slideDown(300);
}
//onMouseOut
function mouseleave(obj){
	$(obj).children('.index2_img_txt').css('background-color','#fff');
	$(obj).children('.img_pop_block_con').slideUp(300);
}
</script>
</body>
</html>
