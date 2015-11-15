'<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>个人中心</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/write_letter.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/write_letter.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
</head>
<body>
<input type="hidden" id='userId' value="${sessionScope.user.userId}">
<!----头部区域-->	
 <div class="inner_header">
      <jsp:include page="../head.jsp"></jsp:include>
</div>
<!---中间内容区域  开始---->
<div class="container">	
	<div class="write_letter_con clearfloat">
    	<div class="write_letter_left">
        	<ul class="write_letter_tab">
            	<li class="current" onClick="grsx()">站内信</li>
                <li>写信</li>
            </ul>
        </div>
        <div class="write_letter_tab_con1">
        	<div class="station_letter_middle">
            	<div class="station_letter_info_tab">
                	<ul class="station_letter_info_tab_tit">
                    	<li class="curr">个人私信</li>
                        <li class="syscurr">系统消息</li>
                    </ul>
                </div>
                <div class="station_letter_info_list_con station_letter_info_list_container1">
	                 <c:forEach items="${listmessage}" var="t">
	   	               <div class="station_letter_info_list">
	                    	<div class="station_letter_info_list_left">
	                        	<span class="station_letter_info_list_img_con sletter_ilist_img_04"></span>
	                            <p>${t.nickname }</p>
	                        </div>
	                        <div class="station_letter_info_list_right">                                       
	                        	<div class="station_letter_info_list_txt"><a class="privatemessid" name="${t.messuserid }">${fn:substring(t.message,0,90)}</a></div>
	                            <div class="station_letter_info_list_txttime"><span><fmt:formatDate value="${t.messagetime }" pattern="yyyy/MM/dd HH:mm:ss"/> </span></div>
	                        </div>
	                    </div>
	                 </c:forEach>                           
                </div>
                <div class="station_letter_info_list_con station_letter_info_list_container2 display_none">
                     <div class="station_letter_info_list">
                	  <c:forEach items="${listmessageadmin}" var="t">
                    	<div class="station_letter_info_list_sys">
                        	<div class="station_letter_info_sys_tit">系统消息</div>
                            <div class="station_letter_info_sys_tit_txt">                                                    
                                <div class="station_letter_info_list_systxt"><a class="messid" name="${t.messageId }">${t.message }</a></div>                             
                             <!-- 此方法暂时先注释掉，因为无法加载点击数据
                                <div class="station_letter_info_list_systxt"><a href="messageGetid.action?messageId=${t.messageId}" >${t.message }</a></div>
                             -->
                                <div class="station_letter_info_list_systxttime clearfloat"><span><fmt:formatDate value="${t.messagetime }" pattern="yyyy/MM/dd HH:mm:ss"/></span></div>
                            </div>
                        </div>  
                      </c:forEach>            
                    </div>    
                                     
                </div>
            </div>
        	<div class="station_letter_right">
            	<div class="station_letter_right_con1">
                	<div class="station_letter_right_tit_con">
                        <div class="station_letter_right_tit_txt"></div>
                    </div>
                    <div class="station_letter_right_send_message_zone">
                    </div>
                    <div class="station_letter_right_edit_message_zone">
                        <div class="station_letter_right_edit_message_zone_top"></div>
                        <div class="station_letter_right_edit_message_zone_middle">
                            <textarea class="station_letter_right_edit_message_textarea" onkeyup="checkLength(this);" maxlength=140 id="sendxx"></textarea>
                        </div>
                        <div class="station_letter_right_edit_message_zone_bottom">
                            <div class="station_letter_right_edit_send_btn">
                                <span id="leftnum"></span>                               
                                <a class="messagesend">发送</a>
                            </div>
                        </div>
                    </div>
                </div>
            	<div class="station_letter_right_con2 display_none">
					<div class="station_letter_right_tit_con">
                        <div class="station_letter_right_tit_txt">详情消息</div>
                    </div>
                    <div class="station_letter_right_sysinfo_zone">
                        <!-- 系统消息标题先去掉，暂时咱们系统不设标题 -->
                    	<!-- <div class="station_letter_right_sysinfo_tit">昨天教务处开会你去了吗？</div> -->
                    	<c:forEach items="${messageGetidList}" var="s">
                        <div class="station_letter_right_sysinfo_txt">${s.message }</div>
                        <div class="station_letter_right_sysinfo_time clearfloat">
                            <span class="station_letter_right_sysinfo_time_detail"><fmt:formatDate value="${s.messagetime }" pattern="yyyy/MM/dd HH:mm:ss"/></span>
                            <b class="station_letter_right_sysinfo_time_from">来自：系统管理员</b>
                        </div>
                        </c:forEach>
                    </div> 
                </div>
            </div>
        </div>
        <div class="write_letter_tab_con2 display_none">
        <!-- 
        	<div class="write_letter_middle">
            	<div class="search_zone">
                	<span class="search_zone_txt">昵称</span>
                    <div class="search_input_zone">
                    	<input type="text" class="search_input_zone_input"/>
                        <div class="search_search_ico_zone"><img src="${pageContext.request.contextPath}/images/user_center/letter_name_search_ico.png" alt=""/></div>
                    </div>
                </div>
                <div class="writer_letter_people_tit">最近联系人</div>
                <div class="writer_letter_people_zone">
                	<div class="writer_letter_people_list">
                    	<div class="wl_peo_list_checkbox"><b></b></div>
                        <div class="wl_peo_list_img"><span class="wl_peo_list_img_con letter_people_img_01"></span></div>
                        <div class="wl_peo_list_info">
                        	<p class="wl_peo_list_info_name">美队欧耶</p>
                            <div class="wl_peo_list_info_time">2015-05-23  11:45</div>
                        </div>
                    </div>
                    <div class="writer_letter_people_list">
                    	<div class="wl_peo_list_checkbox"><b></b></div>
                        <div class="wl_peo_list_img"><span class="wl_peo_list_img_con letter_people_img_02"></span></div>
                        <div class="wl_peo_list_info">
                        	<p class="wl_peo_list_info_name">orica</p>
                            <div class="wl_peo_list_info_time">2015-03-10  11:00</div>
                        </div>
                    </div>
                    <div class="writer_letter_people_list">
                    	<div class="wl_peo_list_checkbox"><b></b></div>
                        <div class="wl_peo_list_img"><span class="wl_peo_list_img_con letter_people_img_03"></span></div>
                        <div class="wl_peo_list_info">
                        	<p class="wl_peo_list_info_name">Jessica</p>
                            <div class="wl_peo_list_info_time">2015-03-10  11:00</div>
                        </div>
                    </div>
                    <div class="writer_letter_people_list">
                    	<div class="wl_peo_list_checkbox"><b></b></div>
                        <div class="wl_peo_list_img"><span class="wl_peo_list_img_con letter_people_img_04"></span></div>
                        <div class="wl_peo_list_info">
                        	<p class="wl_peo_list_info_name">斯嘉丽</p>
                            <div class="wl_peo_list_info_time">2015-04-02  15:03</div>
                        </div>
                    </div>
                    <div class="writer_letter_people_list">
                    	<div class="wl_peo_list_checkbox"><b class="selected"></b></div>
                        <div class="wl_peo_list_img"><span class="wl_peo_list_img_con letter_people_img_05"></span></div>
                        <div class="wl_peo_list_info">
                        	<p class="wl_peo_list_info_name">Katy Perry</p>
                            <div class="wl_peo_list_info_time">Katy Perry</div>
                        </div>
                    </div>
                    <div class="writer_letter_people_list">
                    	<div class="wl_peo_list_checkbox"><b></b></div>
                        <div class="wl_peo_list_img"><span class="wl_peo_list_img_con letter_people_img_06"></span></div>
                        <div class="wl_peo_list_info">
                        	<p class="wl_peo_list_info_name">权利的游戏</p>
                            <div class="wl_peo_list_info_time">2015-03-10  11:00</div>
                        </div>
                    </div>
                    <div class="writer_letter_people_list">
                    	<div class="wl_peo_list_checkbox"><b></b></div>
                        <div class="wl_peo_list_img"><span class="wl_peo_list_img_con letter_people_img_07"></span></div>
                        <div class="wl_peo_list_info">
                        	<p class="wl_peo_list_info_name">boss white</p>
                            <div class="wl_peo_list_info_time">2015-05-23  11:45</div>
                        </div>
                    </div>
                </div>
            </div>
             -->
        	<div class="write_letter_right">
                 <jsp:include page="../message/writeletters.jsp"></jsp:include>  
            </div>
        </div>
	</div>	
</div>
<!---底部区域---->
<div class="footer_con">
	 <jsp:include page="../footer.jsp"></jsp:include>
</div>
</body>
<script type="text/javascript">

//点击系统消息和个人私信的页面切换的界面JS
var tempindex=parseInt('${msgtype}');
$('.station_letter_info_tab_tit li').removeClass("curr");
$('.station_letter_info_tab_tit li').each(function(index){
	if(index==tempindex){
		$(this).addClass("curr");
	}
});
for(var i=0;i<$('.station_letter_info_tab_tit li').length;i++)
{
	$(".station_letter_info_list_container"+(i+1)).hide();
	$(".station_letter_right_con"+(i+1)).hide();
}
$(".station_letter_info_list_container"+(tempindex+1)).show();
$(".station_letter_right_con"+(tempindex+1)).show();


		  
function checkLength(gm) {
    var maxChars = 140;
    var cur = maxChars - gm.value.length;
    document.getElementById("leftnum").innerHTML = "还可输入"+cur.toString()+"字";
    //$("#leftnum").delay(4000).fadeOut("slow");
}
    //点击个人私信详情时调用  
	var messuserid="";
	var fsruserid="";
	var userId=document.getElementById("userId").value;
	$(".privatemessid").click(function(){
		var name=$(this).attr("name");
		$.ajax({
		     type: 'POST',
		     url: '${pageContext.request.contextPath}/msg/messprivatedetail.action?messuserid='+name,
		     success:function (data) {
		    	$(".station_letter_right_send_message_zone").html("");
		    	var html="";
		    	for(var i =0;i<data.length;i++)
		    	{
		    		 var obj =data[i];
		    		 if(obj.fsruser!=userId)
		    		 {
			    		 messuserid=obj.messuserid;
			    		 fsruserid=obj.fsruser;
			    		 html=html+'<div class="sletter_right_smessage_he clearfloat">';
			    		 html=html+'<table cellpadding="0" cellspacing="0" border="0">';
			    		 html=html+'<tr><td><div class="sletter_right_smessage_he_detail_left">';
			    		 html=html+'<span class="sletter_right_smessage_he_detail_left_img sletter_he_img_01"></span></div>';
			    		 html=html+'</td><td><div class="sletter_right_smessage_he_detail_right">'
			    		 html=html+'<div class="sletter_right_smessage_he_detail_righttxt">'+obj.message+'</div></div></td></tr>';    
			    		 html=html+'<tr><td><div class="sletter_right_smessage_he_name"><p>'+obj.nickname+'</p></div></td></tr></table></div>'
			    		 $(".station_letter_right_tit_txt").html("我和<b>"+obj.nickname+"</b>的私信(共<b>"+data.length+"</b>条)");
		    		 }
		    	
		    		 else
		    		 {
			    		 html=html+'<div class="sletter_right_smessage_me clearfloat"><table cellpadding="0" cellspacing="0" border="0">';
			    		 html=html+'<tr><td><div class="sletter_right_smessage_me_detail_left">';
			    		 html=html+'<div class="sletter_right_smessage_me_detail_righttxt">'+obj.message+'</div>';
			    		 html=html+'</div></td><td><div class="sletter_right_smessage_me_detail_right">';
			    		 html=html+'<span class="sletter_right_smessage_me_detail_left_img sletter_me_img_01"></span>';
			    		 html=html+'</div></td></tr><tr><td></td><td><div class="sletter_right_smessage_me_name">';
			    		 html=html+'<p>'+obj.nickname+'</p>';
			    		 html=html+'</div></td></tr></table></div>';
		    		 }
		    	}
		    		 $(".station_letter_right_send_message_zone").html(html);
		     },
		     dataType: "json"
		});
	});
	
	
	//点击系统消息详情时候调用
	$(".messid").click(function(){
		var name=$(this).attr("name");
		$.ajax({
		     type: 'POST',
		     url: '${pageContext.request.contextPath}/msg/messageGetid.action?messageId='+name,
		     success:function (data) {
		    	$(".station_letter_right_sysinfo_zone").html("");
		    	var html="";
		    		 var obj =data[0];
		    		 html=html+'<div class="station_letter_right_sysinfo_txt">'+obj.message+'</div>';
		    		 html=html+'<div class="station_letter_right_sysinfo_time clearfloat">';
		    		 html=html+'<span class="station_letter_right_sysinfo_time_detail">';    		
		    		 html=html+obj.messagetimeString;
		    		 html=html+'</span>'
		    		 html=html+'<b class="station_letter_right_sysinfo_time_from">来自：系统管理员</b></div>';    	 
		    		 $(".station_letter_right_sysinfo_zone").html(html);
		     },
		     dataType: "json"
		});
	});
		
	
	//点击发送时调用
	$(".messagesend").click(function(){
		if(messuserid=="")
		{
			alert("请点击左边选择收件人");
			return;
		}
		var sendxx=document.getElementById("sendxx").value;
		$.ajax({
		     type: 'POST',
		     url:'${pageContext.request.contextPath}/msg/messprivatesend.action',
		     data: {messuserid:messuserid,sendxx:sendxx,fsruserid:fsruserid},
		     success:function (data) {
		    	$(".station_letter_right_send_message_zone").html("");
		    	var html="";
		    	for(var i =0;i<data.length;i++)
		    	{
		    		 var obj =data[i];
		    		 if(obj.fsruser!=userId)
		    		 {
			    		 messuserid=obj.messageId;
			    		 html=html+'<div class="sletter_right_smessage_he clearfloat">';
			    		 html=html+'<table cellpadding="0" cellspacing="0" border="0">';
			    		 html=html+'<tr><td><div class="sletter_right_smessage_he_detail_left">';
			    		 html=html+'<span class="sletter_right_smessage_he_detail_left_img sletter_he_img_01"></span></div>';
			    		 html=html+'</td><td><div class="sletter_right_smessage_he_detail_right">'
			    		 html=html+'<div class="sletter_right_smessage_he_detail_righttxt">'+obj.message+'</div></div></td></tr>';    
			    		 html=html+'<tr><td><div class="sletter_right_smessage_he_name"><p>'+obj.nickname+'</p></div></td></tr></table></div>'
		    		 }
		    	
		    		 else
		    		 {
			    		 html=html+'<div class="sletter_right_smessage_me clearfloat"><table cellpadding="0" cellspacing="0" border="0">';
			    		 html=html+'<tr><td><div class="sletter_right_smessage_me_detail_left">';
			    		 html=html+'<div class="sletter_right_smessage_me_detail_righttxt">'+obj.message+'</div>';
			    		 html=html+'</div></td><td><div class="sletter_right_smessage_me_detail_right">';
			    		 html=html+'<span class="sletter_right_smessage_me_detail_left_img sletter_me_img_01"></span>';
			    		 html=html+'</div></td></tr><tr><td></td><td><div class="sletter_right_smessage_me_name">';
			    		 html=html+'<p>'+obj.nickname+'</p>';
			    		 html=html+'</div></td></tr></table></div>';
		    		 }
		    	}
		    	$(".station_letter_right_send_message_zone").html(html);
		     },
		     dataType: "json"
		});
		document.getElementById("sendxx").value="";
	});
	

	
	
	
	
	
	
	
	
	//点击站内信需要动态拼接
	function grsx()
	{	
		$.ajax({
		     type: 'POST',
		     url: '${pageContext.request.contextPath}/msg/getclickznx.action',
		     success:function (data) {
		     	$(".station_letter_info_list_con").html("");
		    	var html="";
			    	for(var i =0;i<data.length;i++)
			    	{
			    		 var obj =data[i];
				    		 messuserid=obj.messageId;
				    		 html=html+'<div class="station_letter_info_list">';
				    		 html=html+'<div class="station_letter_info_list_left">';
				    		 html=html+'<span class="station_letter_info_list_img_con sletter_ilist_img_04"></span>';
				    		 html=html+'<p>'+obj.nickname+'</p></div><div class="station_letter_info_list_right">';          
				    		 html=html+'<div class="station_letter_info_list_txt"><a class="privatemessid" onClick="sxxq('+"'"+obj.messuserid+"'"+')" name="'+obj.messuserid+'">'+obj.message.substring(0,90)+'</a></div>'
				    		 html=html+'<div class="station_letter_info_list_txttime"><span>'+obj.messagetimeString+'</span></div>';    
				    		 html=html+'</div></div>'		    		 
			    	}
			    	$(".station_letter_info_list_con").html(html);
		     },
		     dataType: "json"
		});
	}
	
	function sxxq(name)
	{
		$.ajax({
		     type: 'POST',
		     url: '${pageContext.request.contextPath}/msg/messprivatedetail.action?messuserid='+name,
		     success:function (data) {
		    	$(".station_letter_right_send_message_zone").html("");
		    	var html="";
		    	for(var i =0;i<data.length;i++)
		    	{
		    		 var obj =data[i];
		    		 if(obj.fsruser!=userId)
		    		 {
			    		 messuserid=obj.messuserid;
			    		 fsruserid=obj.fsruser;
			    		 html=html+'<div class="sletter_right_smessage_he clearfloat">';
			    		 html=html+'<table cellpadding="0" cellspacing="0" border="0">';
			    		 html=html+'<tr><td><div class="sletter_right_smessage_he_detail_left">';
			    		 html=html+'<span class="sletter_right_smessage_he_detail_left_img sletter_he_img_01"></span></div>';
			    		 html=html+'</td><td><div class="sletter_right_smessage_he_detail_right">'
			    		 html=html+'<div class="sletter_right_smessage_he_detail_righttxt">'+obj.message+'</div></div></td></tr>';    
			    		 html=html+'<tr><td><div class="sletter_right_smessage_he_name"><p>'+obj.nickname+'</p></div></td></tr></table></div>'
			    		 $(".station_letter_right_tit_txt").html("我和<b>"+obj.nickname+"</b>的私信(共<b>"+data.length+"</b>条)");
		    		 }
		    	
		    		 else
		    		 {
			    		 html=html+'<div class="sletter_right_smessage_me clearfloat"><table cellpadding="0" cellspacing="0" border="0">';
			    		 html=html+'<tr><td><div class="sletter_right_smessage_me_detail_left">';
			    		 html=html+'<div class="sletter_right_smessage_me_detail_righttxt">'+obj.message+'</div>';
			    		 html=html+'</div></td><td><div class="sletter_right_smessage_me_detail_right">';
			    		 html=html+'<span class="sletter_right_smessage_me_detail_left_img sletter_me_img_01"></span>';
			    		 html=html+'</div></td></tr><tr><td></td><td><div class="sletter_right_smessage_me_name">';
			    		 html=html+'<p>'+obj.nickname+'</p>';
			    		 html=html+'</div></td></tr></table></div>';
		    		 }
		    	}
		    		 $(".station_letter_right_send_message_zone").html(html);
		     },
		     dataType: "json"
		});
	}
	
	
</script>
</html>
