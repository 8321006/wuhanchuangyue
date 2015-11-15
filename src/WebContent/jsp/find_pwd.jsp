<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>找回密码</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/find_pwd.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/find_pwd.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/my_course_public.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/my_course_detail.css"/>
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/my_course.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pop_block_login.js"></script>
<style>
.register_yzmclick_txt{
	float: left;
    line-height: 35px;
    text-align: left;
    color: #333;
    font-size: 12px;
    cursor: pointer;
}
</style>
</head>
<body>

<!----头部区域-->	
<div class="inner_header">
	<jsp:include page="head.jsp"></jsp:include>
</div>
	<!---中间内容区域  开始---->
<div class="container">	
	<div class="find_pwd_con">
    	<div class="find_pwd_title">找回密码</div>
       	<div class="find_pwd_step step_go_1">
        	<img class="step_img" src="images/find_pwd/step_img_01.png" alt=""/>
            <div class="step_detail_con">
            	<div class="step_detail">
                	<p>请填写您需要找回的手机号码</p>
                    <input type="text" id="phone" class="pwd_input_big mt_16px" name="email" value="手机号码" onclick="value=''"  onblur="this.value = this.value =='' ? '手机号码' : this.value "/>
                    <div class="pwd_yzm_con clearfloat">
                    	<input type="text" id='code' name="checkcode" class="yzm_input" value="验证码" onclick="value=''"  onblur="this.value = this.value =='' ? '请输入验证码' : this.value "/>
                    	<!-- <div class="send_yzm_btn">发送验证码</div>
                         <div class="send_yzm_btn display_none">重新发送（43）</div> 
                         <span class="yzm_sended_txt display_none">验证码已发送</span> -->
                         <input class="register_yzmclick_txt" id="btn" value='点击获取短信验证码'/>
                    </div>
                    <div class="step_btn step_go_btn_1">下一步</div>
                </div>
            </div>
        </div>
        <div class="find_pwd_step step_go_2 display_none">
        	<img class="step_img" src="images/find_pwd/step_img_02.png" alt=""/>
            <div class="step_detail_con">
            	<div class="step_detail">
                    <div class="pwd_yzm_con mt_16px clearfloat">
                    	<span class="newpwd_txt_tit">新密码</span>
                    	<input type="password" id='password' class="pwd_input_changepwd" />
                    </div>
                    <div class="pwd_yzm_con clearfloat">
                    	<span class="newpwd_txt_tit">确认新密码</span>
                    	<input type="password" id='passwordagain' class="pwd_input_changepwd" />
                    </div>
                    <div class="step_confirm_btn step_go_btn_2">确认</div>
                </div>
                
            </div>
        </div>
        <div class="find_pwd_step step_go_3 display_none">
        	<img class="step_img" src="images/find_pwd/step_img_03.png" alt=""/>
            <div class="step_detail_con">
                <div class="pwd_change_wancheng">
                	<div class="pwd_change_wanchengtxt clearfloat">
                    	<b class="pwd_change_wanchengtxt_ico"></b>
                        <div class="pwd_change_wanchengtxt_detail">恭喜您,密码修改成功</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!---底部区域---->
<jsp:include page="footer.jsp"></jsp:include>
<script type="text/javascript">
var wait=60;  

$("#btn").click(function(){
	if($("#phone").val() != ''){
	//判断号码是否存在
	time($(this));
	}
});

function time(obj){
	if (wait == 0) {
		obj.removeAttr("disabled");
		obj.val("点击获取短信验证码");
        wait = 60;  
    } else {
    	obj.attr("disabled", true);
    	obj.val("重新发送(" + wait + ")");
        wait--;
        if(wait==59){
        	//生成验证码并发短信
        	getcode();
        }
        setTimeout(function() {  
            time(obj)
        },  
        1000)  
    }
}

function getcode(){
		$.ajax({
		     type: 'POST',
		     url: '${pageContext.request.contextPath}/cy/findpwd.action',
		     data: {phone:$("#phone").val()},
		     success:function (data) {
		     },
		     dataType: "json"
		});
}

$(".step_go_btn_1").click(function(){
	validate1();
})

function validate1(){
	 //验证码
	 if($("#phone").val() != '' && $("#phone").val() != '手机号码' && $("#code").val() != '验证码' && $("#code").val() != ''){
	 $.ajax({
	     type: 'POST',
	     url: '${pageContext.request.contextPath}/cy/checkpwdcode.action',
	     data: {phone:$("#phone").val(),code:$("#code").val()},
	     success:function (data) {
	    	 if(data.type == 'success'){
	    		 $('.step_go_1').css('display','none');	
		 		 $('.step_go_2').css('display','block');
	    	 }else{
	    		 alert(data.msg)
	    	 }
					
	     },
	     dataType: "json"
	});
	 }
	 
}

$(".step_go_btn_2").click(function(){
	validate2();
})

function validate2(){
	if($("#password").val().length<6 || $("#passwordagain").val().length<6){
	 	alert("密码必须超过6位");
	 	return;
	}
	if($("#password").val() != $("#passwordagain").val()){
		 alert("两次密码不一致");
		 return;
	}
	 $.ajax({
	     type: 'POST',
	     url: '${pageContext.request.contextPath}/cy/updatepwd.action',
	     data: {loginPassword:$("#password").val(),phone:$("#phone").val()},
	     success:function (data) {
	    		$('.step_go_1').css('display','none');	
		 		$('.step_go_2').css('display','none');
		 		$('.step_go_3').css('display','block');
	     },
	     dataType: "json"
	});
}
</script>
</body>
</html>