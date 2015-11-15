<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%  
    String name="";  
    String psw="";  
    String checked="";  
    Cookie[] cookies=request.getCookies();  
    if(cookies!=null&&cookies.length>0){   
        //遍历Cookie  
        for(int i=0;i<cookies.length;i++){  
            Cookie cookie=cookies[i];  
            //此处类似与Map有name和value两个字段,name相等才赋值,并处理编码问题   
            if("name".equals(cookie.getName())){  
                name=URLDecoder.decode(cookie.getValue(),"utf-8");  
                //将"记住我"设置为勾选   
                checked="checked";
            }  
            if("psw".equals(cookie.getName())){  
                psw=cookie.getValue();
            }  
        }  
    }  
 %> 
 <% 
String path = request.getContextPath(); 
// 获得本项目的地址(例如: http://localhost:8080/MyApp/)赋值给basePath变量 
String basePath = request.getScheme()+"://"+request.getServerName()
+":"+request.getServerPort()+path+"/"; 
// 将 "项目路径basePath" 放入pageContext中，待以后用EL表达式读出。 
pageContext.setAttribute("basePath",basePath); 
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html> 
<head> 
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta property="qc:admins" content="3624216216773364510063757" /> 
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/medioadaption.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_houtai_public.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_houtai_study_progess.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pop_block_login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/footer.js"></script>
<title>Insert title here</title> 
</head>
<!----登录弹出框--->
<input type="hidden" value="${sessionScope.user.loginName}" id="username" />
<input type="hidden" value="${sessionScope.user.realName}" id="realName" />
<form action="${pageContext.request.contextPath}/cy/login.action"  name="loginform" method="post">
<div class="top_area_scroll">
    	<div class="top_area_con clearfloat">
            <img class="inner_logo" onClick="window.location.href='${pageContext.request.contextPath}'" src="${pageContext.request.contextPath}/images/inner/logo_ico.png" alt=""/>
            <img class="logo_fenge" src="${pageContext.request.contextPath}/images/houtai/logo_fenge.png" alt=""/>
            <img class="bg_school_logo" onClick="${pageContext.request.contextPath}'" src="${pageContext.request.contextPath}/${sessionScope.user.universityLogo}" alt=""/>
            <span class="bg_school_name_txt">${sessionScope.user.universityName}</span>
            <div class="logined_zone">
            	<div class="user_ico" onClick="topersonalcenter();">
            	 <c:if test="${empty(sessionScope.user_logo)}">
            		<img src="${pageContext.request.contextPath}/images/user_center/person_center_shcool_01.jpg" alt=""/>
            	</c:if>
            	<c:if test="${not empty(sessionScope.user_logo)}">
            		<img src="${sessionScope.user_logo}" alt=""/>
            	</c:if>
            	</div>
                <div class="user_txt"><span>${sessionScope.user.realName}</span>，欢迎你！</div>
                <div class="logout_link" onclick="exit();">退出</div>
            </div>
        </div>
    </div>
</form>
<form action="${pageContext.request.contextPath}/cy/exit.action" name="exitform" method="post">
</form>
    <script type="text/javascript">
	var url = window.location.href;
	if(url.substring(url.length-"${pageContext.request.contextPath}/".length) != "${pageContext.request.contextPath}/"){
	$("#top_area").attr("class","top_area_scroll");
		}else{
			$("#top_area").attr("class","top_area_scroll");
}
	
function skip1(){
	    window.location.href="${pageContext.request.contextPath}/msg/getletter.action";
	}

function skip2(){
	window.location.href="${pageContext.request.contextPath}/msg/getsysmsg.action";
	}
	
function login(){
	$.ajax({
		url:'${pageContext.request.contextPath}/cy/checklogin.action',
		type:'post',
		datatype:'json',
		data: {phone:$("#login").val(),loginPassword:$("#pass").val()},
		success:function(response){
			if(jQuery.parseJSON(response).msg==""){
				if($(".login_checkbox_ico").attr("src") == "${pageContext.request.contextPath}/images/inner/login_checkbox_ico_selected.png"){
					$("#rememberMe").val("1");
				}else{
					$("#rememberMe").val("0");
				}
				document.loginform.submit();
			}
			else{
				$(".login_error_tips").html('<span>'+jQuery.parseJSON(response).msg+'</span>');
				$(".login_error_tips span").delay(4000).fadeOut("slow");
			}
		},		
	});

}
	 
function exit(){
	document.exitform.submit();
}
function toindex(){
	window.location.href="${pageContext.request.contextPath}";
}
function goadmin(){
	window.location.href="${pageContext.request.contextPath}/cy/universitycourse.action";
}

function findpwd(){
	window.location.href="${pageContext.request.contextPath}/jsp/find_pwd.jsp";
}

function topersonalcenter()
{
	window.location.href="${pageContext.request.contextPath}"+"/cy/center.action";
}

function tosearch(){
	window.location.href="${pageContext.request.contextPath}/index/search.action";
}

function register(){
	window.location.href="${pageContext.request.contextPath}/jsp/user/register.jsp";
}

$().ready(function(){
	var localuserId = "${sessionScope.user.userId}";
	if(localuserId != ""){
		$.ajax({
			url:'${pageContext.request.contextPath}/msg/getlist.action',
			type:'post',
			data:'',
			success:function(data){
				if(jQuery.parseJSON(data).letter!=0)
				{
				 // $(".total_num").hide();
					$("#haha").show();
				}
					$(".total_num").html(jQuery.parseJSON(data).allmes);
					$("#letter").html(jQuery.parseJSON(data).letter);
					$("#sysmsg").html(jQuery.parseJSON(data).sysmsg);
				}				
		});
	}
	
	
	
$('.login_forgetpwd_left').click(function(){
	if($(this).children('img').attr('src')=='${pageContext.request.contextPath}/images/inner/login_checkbox_ico.png'){
		$(this).children('img').attr('src','${pageContext.request.contextPath}/images/inner/login_checkbox_ico_selected.png');
		}else{
			$(this).children('img').attr('src','${pageContext.request.contextPath}/images/inner/login_checkbox_ico.png');
			}
		
});

function topersonalcenter()
{
	window.location.href="${pageContext.request.contextPath}"+"/cy/center.action";
}

});
</script>
</html>