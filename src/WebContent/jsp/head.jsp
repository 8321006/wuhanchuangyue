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
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/pop_block_login.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pop_block_login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/footer.js"></script>
<title>Insert title here</title> 
</head>
<!----登录弹出框--->
<input type="hidden" value="${sessionScope.user.loginName}" id="username" />
<input type="hidden" value="${sessionScope.user.realName}" id="realName" />
<form action="${pageContext.request.contextPath}/cy/login.action"  name="loginform" method="post">
<div class="pop_msg_login">
   <div class="pop_close"></div>
    <div class="right_login_con">
        <img class="login_logo_ico" src="${pageContext.request.contextPath}/images/administrator/admin_logo.png" alt=""/>
        <div class="login_txt">欢迎登录易启学学习平台</div>
        <div class="login_content_con"> 
            <div class="login_input">
                <img class="login_name_ico" src="${pageContext.request.contextPath}/images/inner/login_name_ico.png" alt=""/>
                <input type="text" id="login" name="phone" value="<%=name %>"/>
            </div>
            <p class="login_error_tips"></p>
            <div class="login_input">
                <img class="login_pwd_ico" src="${pageContext.request.contextPath}/images/inner/login_pwd_ico.png" alt=""/>
                <input type="password" id="pass" name="loginPassword" value="<%=psw %>"/>
            </div>
            <div class="login_forgetpwd clearfloat">
                <div class="login_forgetpwd_left">
                    <img class="login_checkbox_ico" src="${pageContext.request.contextPath}/images/inner/login_checkbox_ico.png" alt=""/>
                    <span class="checkbox_txt">下次自动登录</span>
                    <input type="hidden" name="rememberMe"  id="rememberMe">
                </div>
                <div class="login_forgetpwd_right" onclick="findpwd()"><a>忘记密码</a></div>
            </div>
            <div class="login_btn" id="login_btn"onclick="login();">登录</div>
        </div>
        <div class="third_login_qq">
        	<span class="third_login_qq_txt">第三方账号登录：</span>
        	<a href="${pageContext.request.contextPath}/qq.action">
            <img class="bg_login_qq_img" src="${pageContext.request.contextPath}/images/inner/bg_login_qq.png" alt=""/>
            </a>
        </div>
    </div>
</div>
</form>
<form action="${pageContext.request.contextPath}/cy/exit.action" name="exitform" method="post">
<div class="pop_msg_bg_login"></div>
<div class="top_area" id ="top_area">
    	<div class="top_area_con clearfloat">
    	
    		   
             <img class="inner_logo" onclick="toindex()" src="${pageContext.request.contextPath}/images/inner/logo_ico.png" alt=""/>
            <ul class="jump_nav">
            	<li onclick="toindex();"><b class="top_ico_01"></b><span>首页</span></li>
                <li onclick="tosearch();" target="_blank"><b class="top_ico_02"></b><span>课程</span></li>
                <li class="end"><b class="top_ico_03"></b><span>社区</span></li>
            </ul>
            
           
            <c:if test="${empty(sessionScope.user.loginName)}">
            <div class="reg_login_btn">
            	<div class="reg_btn" onclick='register()'>注册</div>
                <div class="login_btn">登录</div>
            </div>
            </c:if>
            ${sessionScope.user.sex}
            <c:if test="${not empty(sessionScope.user.loginName)}">
            <div class="logined_zone">
            	<div class="user_ico"  onClick="topersonalcenter();"title="个人中心">
            	<c:if test="${empty(sessionScope.user_logo)}">
            	<c:if test="${empty(sessionScope.user.sex) || sessionScope.user.sex == '0'}">
        		<c:if test="${sessionScope.user.userType == '0'}">
        			<img src="${pageContext.request.contextPath}/images/user_center/person_center_student_01.jpg" alt=""/>
        		</c:if>
            	<c:if test="${sessionScope.user.userType == '1'}">
        			<img src="${pageContext.request.contextPath}/images/user_center/person_center_teacher_01.jpg" alt=""/>
        		</c:if>
        		<c:if test="${sessionScope.user.userType == '2'}">
        			<img src="${pageContext.request.contextPath}/images/user_center/person_center_shcool_01.jpg" alt=""/>
        		</c:if>
            	</c:if>
            	<c:if test="${sessionScope.user.sex == '1'}">
            	<c:if test="${sessionScope.user.userType == '0'}">
            	<img src="${pageContext.request.contextPath}/images/user_center/person_center_student_02.jpg" alt=""/>
            	</c:if>
            	<c:if test="${sessionScope.user.userType == '1'}">
        			<img src="${pageContext.request.contextPath}/images/user_center/person_center_teacher_02.jpg" alt=""/>
        		</c:if>
            	<c:if test="${sessionScope.user.userType == '2'}">
        			<img src="${pageContext.request.contextPath}/images/user_center/person_center_shcool_01.jpg" alt=""/>
        		</c:if>
            	</c:if>
            	</c:if>
            		<c:if test="${not empty(sessionScope.user_logo)}">
            			<img src="${sessionScope.user_logo}" alt=""/>
            		</c:if>
            	</div>
                <div class="user_txt"><span>${user.realName }</span>，欢迎你！</div>
                <!-- 
                <div class="message_ico_con"><span class="total_num">99</span></div>
                <div class="comment_ico"></div>
                 -->
               <!--     <div class="tips_ico_con">
                	<span class="total_num">${msgnum}</span>
                	<img class="tips_arrow_up_ico" src="${pageContext.request.contextPath}/images/user_center/tips_arrow_up_ico.png" alt=""/>
                	<div class="tips_down_pop">
                    	<div class="tips_down_pop_img_txt mb_12px" onclick="skip1()" >
                        	<img class="tips_down_ico_01" src="${pageContext.request.contextPath}/images/user_center/tips_down_ico_01.png" alt=""/>
                            <span>站内私信<b class="add_num">+99</b></span>
                             
                        </div>
                        <div class="tips_down_pop_img_txt" onclick="skip2()">
                        	<img class="tips_down_ico_01" src="${pageContext.request.contextPath}/images/user_center/tips_down_ico_02.png" alt=""/>
                            <span>系统消息</span>
                        </div>
                    </div>                    
                </div>--> 
              <c:if test="${sessionScope.user.userType == '0'|| sessionScope.user.userType == '1'}">
            	<div class="tips_ico_con" id="mes">
            	 
            	  <div style="display: none;" id="haha">
                	<span id="count" class="total_num" ></span>              	
                	<span class="total_num"></span>
                  </div>
                	
                	<div class="tips_down_pop">
                    	<img class="tips_arrow_up_ico" src="${pageContext.request.contextPath}/images/user_center/tips_arrow_up_ico.png" alt=""/>
                    	<div class="tips_down_pop_img_txt mb_12px" onclick="skip1()">
                        	<img class="tips_down_ico_01" src="${pageContext.request.contextPath}/images/user_center/tips_down_ico_01.png" alt=""/>
                            <span>站内私信</span>
                           
                            <b class="add_num" id="letter"></b>
                            
                        </div>
                        <div class="tips_down_pop_img_txt" onclick="skip2()">
                        	<img class="tips_down_ico_01" src="${pageContext.request.contextPath}/images/user_center/tips_down_ico_02.png" alt=""/>
                            <span>系统消息</span>
                             
                            <b class="add_num" id="sysmsg"></b>
                            
                        </div>
                    </div>
                </div>
              </c:if>
            	
                <div onclick="exit()" class="logout_link">退出</div>
            </div>
            </c:if>
        </div>
</div>
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
		success:function(data){
			if(data['msg']==""){
				if($(".login_checkbox_ico").attr("src") == "${pageContext.request.contextPath}/images/inner/login_checkbox_ico_selected.png"){
					$("#rememberMe").val("1");
				}else{
					$("#rememberMe").val("0");
				}
				document.loginform.submit();
			}
			else{
				$(".login_error_tips").html('<span>'+data['msg']+'</span>');
				$(".login_error_tips span").delay(4000).fadeOut("slow");
			}
		},		
	});

}
$("#login").keyup(function(e){
	if(e.keyCode == 13) {
		login(0);
	}
}) ;
$("#pass").keyup(function(e){
	if(e.keyCode == 13) {
		login(0);
	}
}) ;
	 
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
	window.open("${pageContext.request.contextPath}/index/search.action");
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

});
</script>
</html>