<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body onload="Substring();">
            <div class="user_center_notice">
            	<ul class="user_center_notice_tab clearfloat">
                	<li class="selected">全部通知</li>
                    <li>学校通知</li>
                    <li>系统通知</li>
                </ul>
                <div class="user_center_notice_detail user_center_notice_tabcon1">
                <c:forEach items="${noticeAlllist}" var="t">
                	<div class="user_center_notice_list">
                    	<div class="user_center_notice_list_tit" id="laiceshiyiba">${t.noticeTitle }</div>
                        <div class="user_center_notice_list_txt">${t.noticeContent}</div>
                        <div class="user_center_notice_timeinfo clearfloat">
                        	<div class="user_center_notice_timeinfo_left">
                            	<span class="user_center_notice_timeinfo_left_time"><fmt:formatDate value="${t.noticeTime }" pattern="yyyy/MM/dd HH:mm:ss"/></span>
                                <b class="user_center_notice_timeinfo_left_name">来自：<span>不拉不拉客服</span></b>
                            </div>
                            <div class="user_center_notice_timeinfo_right"><a onClick="noticePopCenter('${t.noticeId }');">查看全文</a></div>
                          <!--   获取值的方法
                              <div class="user_center_notice_timeinfo_right"><a noticeid="${t.noticeTitle}" onClick="noticePopCenter(this)">查看测试哈子</a></div>
                           -->
                        </div>                 
                    </div>
                </c:forEach>
                </div>
                <div class="user_center_notice_detail user_center_notice_tabcon2 display_none">
                <c:forEach items="${noticeCourselist}" var="t">
                	<div class="user_center_notice_list">
                    	<div class="user_center_notice_list_tit">${t.noticeTitle }</div>
                        <div class="user_center_notice_list_txt">${t.noticeContent}</div>
                        <div class="user_center_notice_timeinfo clearfloat">
                        	<div class="user_center_notice_timeinfo_left">
                            	<span class="user_center_notice_timeinfo_left_time"><fmt:formatDate value="${t.noticeTime }" pattern="yyyy/MM/dd HH:mm:ss"/></span>
                                <b class="user_center_notice_timeinfo_left_name">来自：<span>课程管理员</span></b>
                            </div>
                            <div class="user_center_notice_timeinfo_right"><a onClick="noticePopCenter('${t.noticeId }');">查看全文</a></div>
                        </div>
                    </div>
                </c:forEach>
                </div>
                <div class="user_center_notice_detail user_center_notice_tabcon3 display_none">
                <c:forEach items="${noticeSystemlist}" var="t">
                	<div class="user_center_notice_list">
                    	<div class="user_center_notice_list_tit">${t.noticeTitle }</div>
                        <div class="user_center_notice_list_txt">${t.noticeContent}</div>
                        <div class="user_center_notice_timeinfo clearfloat">
                        	<div class="user_center_notice_timeinfo_left">
                            	<span class="user_center_notice_timeinfo_left_time"><fmt:formatDate value="${t.noticeTime }" pattern="yyyy/MM/dd HH:mm:ss"/></span>
                                <b class="user_center_notice_timeinfo_left_name">来自：<span>系统客服</span></b>
                            </div>
                            <div class="user_center_notice_timeinfo_right"><a onClick="noticePopCenter('${t.noticeId }');">查看全文</a></div>
                        </div>
                    </div>
                </c:forEach>
                </div>
            </div>
</body>

<script type="text/javascript">

//弹出层居中
function noticePopCenter(noticeid){
	//var hhh=$(noticeid).attr("noticeid");	
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
			html=html+'<span class="pop_msg_notice_time_detail">'+obj.noticetimeString+'</span>';
			html=html+'<b class="pop_msg_notice_time_from">来自：<span>不拉不拉客服</span></b>';
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
   function Substring(){
	   var s = $(".user_center_notice_list_txt").text();
	    ss = s.substring(0,10);   // 取子字符串。
	    $(".user_center_notice_list_txt").text(ss);  // 返回子字符串。
 }
</script>
</html>