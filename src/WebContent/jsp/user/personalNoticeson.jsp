<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>个人中心——教务处理</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/user_center.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/hDate2.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/user_center.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/footer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Autoscoll.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_center.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/circle.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hDate2.js"></script>

</head>
<body onload="noticeSelect()">
<!----通知查看全文弹出框--->
<div class="pop_msg_notice">
	<jsp:include page="../user/personalNoticedetail.jsp"></jsp:include>
</div>
<div class="pop_msg_bg_notice"></div>


<!----头部区域-->	
<div class="inner_header">
	<jsp:include page="../head.jsp"></jsp:include>       
</div>

<!---中间内容区域  开始---->
<div class="container">	
	<div class="user_center_con">
	<div class="user_center_con_info">
            <div class="user_ico" onClick="window.location.href='user_center.html'"><img src="images/user_center/user_img_01.jpg" alt=""/></div>
            <dl class="user_center_con_list">
                <dt>基本资料</dt>
                <dd>莫妮卡 Monica</dd>
                <dd>UID:123456789</dd>
                <dd>社区活跃度：中级</dd>
                <dd>收藏课程：12</dd>
            </dl>
        </div>
         <input id="noticeAlllistlength" type="hidden"value="${noticeAlllist.size()}">
          <input id="noticeAlllist" type="hidden"value="${noticeAlllist}">
    	<div class="user_center_content clearfloat">
            <div class="user_center_con_left">
                   <jsp:include page="../user/personalcenter-left.jsp"></jsp:include>
            </div>
            <div class="user_center_con_right" >  
                <div class="user_center_bread_tit"></div>         
                <div class="user_center_notice">
                    <ul class="user_center_notice_tab clearfloat">
                        <li id="noticeAll"class="selected">全部通知</li>
                        <li id="noticeCourse">课程通知</li>
                        <li id="noticeSystem">系统通知</li>
                    </ul>
                     <!-- 全部通知 -->
                   <div class="user_center_notice_detail user_center_notice_tabcon1"id="user_center_notice_detail">
                    <c:forEach items="${noticeAlllist}" var="t">
                        <div class="user_center_notice_list">
                            <div class="user_center_notice_list_tit">
                            <c:if test="${t.noticeCharacter==1}">
                             <span style="color:#ea0006;">【紧急通知】</span>
                            <span style="color:#ea0006;"> ${t.noticeTitle }</span>
                            </c:if>
                             <c:if test="${t.noticeCharacter==0}">
                                                                   【温馨提示】
                            ${t.noticeTitle }  
                            </c:if>
                            <span id="new_notice" class="new_notice"><img src="images/user_center/new.gif"></span>
                           </div>
                            <div class="user_center_notice_list_txt">
                            <c:if test="${fn:length(t.noticeContent)>118}"><span>${fn:substring(t.noticeContent,0,118)}...</span>
                            </c:if>
   　　                                                            <c:if test="${fn:length(t.noticeContent)<=118}">${fn:substring(t.noticeContent,0,118)}</c:if>
                            </div>
                            <div class="user_center_notice_timeinfo clearfloat">
                                <div class="user_center_notice_timeinfo_left">
                                    <span class="user_center_notice_timeinfo_left_time"><fmt:formatDate value="${t.noticeTime }" pattern="yyyy/MM/dd HH:mm:ss"/></span>
                                    <b class="user_center_notice_timeinfo_left_name">来自：<span>
                                    <c:if test="${t.noticetype==1}">
                                                                                    教务通知
                                    </c:if>
                                    <c:if test="${t.noticetype==2}">
                                                                                    系统通知
                                    </c:if>
                                    </span></b>
                                </div>
                                <div class="user_center_notice_timeinfo_right"><a onClick="noticePopCenter('${t.noticeId }',this);">查看全文</a></div>
                            </div>
                        </div>
                     </c:forEach>
                     <form action="${pageContext.request.contextPath}/cy/centerNotice.action" id="searchform" name="searchform" method="post">
					<input type='hidden' id='noticetype' name='noticetype' value='3'/>	
					</form>
					<input type="hidden" id="noticetypeflag" name="noticetypeflag" value="${noticetype}"/>
                      <c:if test="${not empty noticeAlllist}"> 			
         			  <tags:pager pagerRange="10" pageSize="${page.pageSize}" totalPage="${page.pages}" curIndex="${page.pageNum}" formId="searchform"></tags:pager>              
           		 	</c:if> 
                    </div>
                   <div class="loadnotice" id="loadnotice"style="display:none;"> <span>加载更多</span></div>
                     <!-- 课程通知 -->
                    <div class="user_center_notice_detail user_center_notice_tabcon2 display_none">
                    <c:forEach items="${noticeCourselist}" var="t">
                        <div class="user_center_notice_list">
                            <div class="user_center_notice_list_tit"><c:if test="${t.noticeCharacter==1}">
                             <span style="color:#ea0006;">【紧急通知】</span>
                            <span style="color:#ea0006;"> ${t.noticeTitle }</span>
                            </c:if>
                             <c:if test="${t.noticeCharacter==0}">
                                                                 【温馨提示】
                            ${t.noticeTitle}
                            </c:if></div>
                            <div class="user_center_notice_list_txt">
                            <c:if test="${fn:length(t.noticeContent)>118}">${fn:substring(t.noticeContent,0,118)}...</c:if>
   　　                                                            <c:if test="${fn:length(t.noticeContent)<=118}"> ${fn:substring(t.noticeContent,0,118)}</c:if>
                               </div>
                            <div class="user_center_notice_timeinfo clearfloat">
                                <div class="user_center_notice_timeinfo_left">
                                    <span class="user_center_notice_timeinfo_left_time"><fmt:formatDate value="${t.noticeTime }" pattern="yyyy/MM/dd HH:mm:ss"/></span>
                                    <b class="user_center_notice_timeinfo_left_name">来自：<span>教务通知</span></b>
                                </div>
                                <div class="user_center_notice_timeinfo_right"><a onClick="noticePopCenter('${t.noticeId }');">查看全文</a></div>
                            </div>
                        </div>
                    </c:forEach>
                     <form action="${pageContext.request.contextPath}/cy/centerNotice.action" id="searchcourseform" name="searchcourseform" method="post">
					<input type='hidden' id='noticetype' name='noticetype' value='1'/>	
					</form>
                      <c:if test="${not empty noticeCourselist}"> 			
         			  <tags:pager pagerRange="10" pageSize="${page1.pageSize}" totalPage="${page1.pages}" curIndex="${page1.pageNum}" formId="searchcourseform"></tags:pager>
         			  </c:if>
                    </div>
                    
                    <!-- 系统通知 -->
                    <div class="user_center_notice_detail user_center_notice_tabcon3 display_none">
                    <c:forEach items="${noticeSystemlist}" var="t">
                        <div class="user_center_notice_list">
                            <div class="user_center_notice_list_tit"><c:if test="${t.noticeCharacter==1}">
                             <span style="color:#ea0006;">【紧急通知】</span>
                            <span style="color:#ea0006;"> ${t.noticeTitle }</span>
                            </c:if>
                             <c:if test="${t.noticeCharacter==0}">
                                                                 【温馨提示】
                            ${t.noticeTitle }
                            </c:if></div>
                            <div class="user_center_notice_list_txt">
                            <c:if test="${fn:length(t.noticeContent)>118}">${fn:substring(t.noticeContent,0,118)}...</c:if>
   　　                                                            <c:if test="${fn:length(t.noticeContent)<=118}"> ${t.noticeContent}</c:if>
                           </div>
                            <div class="user_center_notice_timeinfo clearfloat">
                                <div class="user_center_notice_timeinfo_left">
                                    <span class="user_center_notice_timeinfo_left_time"><fmt:formatDate value="${t.noticeTime }" pattern="yyyy/MM/dd HH:mm:ss"/></span>
                                    <b class="user_center_notice_timeinfo_left_name">来自：<span>系统客服</span></b>
                                </div>
                                <div class="user_center_notice_timeinfo_right"><a onClick="noticePopCenter('${t.noticeId }');">查看全文</a></div>
                            </div>
                        </div>
                     </c:forEach>
                     <form action="${pageContext.request.contextPath}/cy/centerNotice.action" id="searchsystemform" name="searchsystemform" method="post">
					<input type='hidden' id='noticetype' name='noticetype' value='2'/>	
					</form>
                      <c:if test="${noticeSystemlist.size()>10}">
                     ${page2.pages}
         			  <tags:pager pagerRange="10" pageSize="${page2.pageSize}" totalPage="${page2.pages}" curIndex="${page2.pageNum}" formId="searchsystemform"></tags:pager>
         			  </c:if>
                    </div>
                    </div>
                </div>
            </div>
            
        </div>
    	
    	
        
    </div>
</div>
<!---底部区域---->
<div class="footer_con">
	<div class="footer clearfloat">	
		<div class="footer_copyright">京ICP备09043258号-2  京公网安备1101052730</div>
    </div>
</div>


<script type="text/javascript">
$("#leftbar").find("li").each(function(){
	$(this).removeClass();
});
$("#jxtz").attr("class","curr");

//弹出层居中
function noticePopCenter(noticeid,ob){
	//var optionFlag = $(obj1).attr("class");
	var flag=0;
   
	//$("#new_notice").css('display','none');if( $("#test").css("display")=='none' ) { // TO DO .. }
	if($(ob).parent().parent().parent().find("#new_notice").css("display") =='none'){
		flag++;
		}
	//alert(flag);
	//利用js修改session的值
	//首先获取session的值(Integer)s.getAttribute("totalwritesurvey");
	 if(flag==0){
	    $(ob).parent().parent().parent().find("#new_notice").css('display','none');
		var a=$("#noticetotal").text();
		if(a>0){
			var noticetotal=a-1;
			$("#noticetotal").text(noticetotal);
		}
	}
	$.ajax({
		type:"POST",
		url: '${pageContext.request.contextPath}/cy/noticedetail.action?noticeid='+noticeid,
		success:function (data) {
			$(".pop_msg_notice_innercon").html("");
			var html="";
			var obj=data[0];
			html=html+'<div class="pop_msg_notice_title">'+obj.noticeTitle+'</div>';
			html=html+'<div class="pop_msg_notice_txtcon">';
			html=html+'<div class="pop_msg_notice_wenhou_txt">'+obj.noticeContent+'</div>';
			html=html+'</div>';
			html=html+'<div class="pop_msg_notice_time clearfloat">';
			html=html+'<b class="pop_msg_notice_time_from">来自：<span>不拉不拉客服</span></b>';
			html=html+'<span class="pop_msg_notice_time_detail">'+obj.noticetimeString+'</span>';
			html=html+'</div>';
	        $(".pop_msg_notice_innercon").html(html);
		},
		dataType: "json"
	});	
	
	$('.pop_msg_notice').css('display','block');
	$('.pop_msg_bg_notice').css('display','block');
	var w_width = $(window).width();
	var w_height = $(window).height();
	var b_height = $(document.body).height();
	if(b_height<w_height){
		$('.pop_msg_bg_notice').css('height',w_height);
		}else{
			$('.pop_msg_bg_notice').css('height',b_height);
			}
	var w_self = $('.pop_msg_notice').width();
	var h_self = $('.pop_msg_notice').height();
	var _x = w_width/2 - w_self/2;
	var _y = w_height/2 - h_self/2;
	$('.pop_msg_notice').css({left:_x,top:_y});
}
function loadNotice() {
	/*
	 var noticesize=$("#noticeAlllistlength").val();
	 var noticelist=$("#noticeAlllist").val();
      alert(noticesize);
      alert(noticelist);
      var obj ='(' +noticelist + ')';
      alert(obj);
      var obj0 = jQuery.parseJSON(obj);
      alert(obj0);
	 var obj1 = eval(jQuery.parseJSON(obj))	
	 var html = "";
	 */
}
/*
$(document).ready(function () {

    $(window).scroll(function () {
        var $body = $("body");
        var $html = "";
        $html += "<br/>" + ($(window).height() + $(window).scrollTop());
        $html += "<br/>window.height: " + $(window).height();
        $html += "<br/>body.height: " + $body.height();
        $html += "<br/>window.scrollTop: " + $(window).scrollTop();
       // $("#user_center_notice_detail").html($html);

  //*判断窗体高度与竖向滚动位移大小相加 是否 超过内容页高度*/
 /*
        if (($(window).height() + $(window).scrollTop()) >= $body.height()) {
            $("#loadnotice").show();
            setTimeout(insertcode, 1000);
            insertcode();
        }
    });
});*/

function noticeSelect(){
	var noticetypeflag=$("#noticetypeflag").val();
	
	if(noticetypeflag==0||noticetypeflag==3){
		$(".user_center_notice_tabcon1").show();
		$(".user_center_notice_tabcon2").hide();
		$(".user_center_notice_tabcon3").hide();
		 $('#noticeAll').addClass("selected");
		 $('#noticeCourse').removeClass("selected");
		 $('#noticeSystem').removeClass("selected");
	}
    if(noticetypeflag==1){
    	
    	$(".user_center_notice_tabcon2").show();
		$(".user_center_notice_tabcon1").hide();
		$(".user_center_notice_tabcon3").hide();
		
		$('#noticeCourse').addClass("selected");
		 $('#noticeAll').removeClass("selected");
		 $('#noticeSystem').removeClass("selected");
	}
    if(noticetypeflag==2){
    	$(".user_center_notice_tabcon3").show();
		$(".user_center_notice_tabcon1").hide();
		$(".user_center_notice_tabcon2").hide();
		$('#noticeCourse').removeClass("selected");
		 $('#noticeAll').removeClass("selected");
		 $('#noticeSystem').addClass("selected");
	}
}

</script>
</body>
</html>
