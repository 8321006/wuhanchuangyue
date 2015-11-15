<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>注册</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/register.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pop_block_login.js"></script>
</head>
<body>

<!----头部区域-->	
<div class="inner_header">
	<jsp:include page="../head.jsp"></jsp:include>
</div>

<!---中间内容区域  开始---->
<div class="container">	
	<div class="register_con">
       	<div class="register_step step_go_1">
            <div class="register_step_detail">
            <!--  
                <div class="register_input_con clearfloat">
                    <span class="register_type_txt">真实姓名</span>
                    <input type="text" class="register_input_big body_input"/>
                </div>
            -->    
                <div class="register_input_con mt_20px clearfloat">
                    <span class="register_type_txt letter_spacing_5px" >手机号</span>
                    <input type="text" class="register_input_big body_input" id="phone"/>
                </div>
                <p class="register_tips_txt"></p>
                <div class="register_input_con mt_20px clearfloat">
                    <span class="register_type_txt letter_spacing_8px">密码</span>
                    <div class="register_pwd_input_con">
                        <input type="password" id='password' class="register_input_big body_input"/>
                        <img class="pwd_view_ico" src="images/register/pwd_view.png" alt=""/>
                    </div> 
                </div>
                <div class="register_input_con mt_20px clearfloat">
                    <span class="register_type_txt letter_spacing_8px">确认密码</span>
                    <div class="register_pwd_input_con">
                        <input type="password" id='passwordagain' class="register_input_big body_input"/>
                        <img class="pwd_view_ico" src="images/register/pwd_view.png" alt=""/>
                    </div> 
                </div>
                <div class="register_input_con mt_20px clearfloat">
                    <span class="register_type_txt letter_spacing_5px">验证码</span>
                    <input type="text" class="register_yzm_input body_input" id='code'/>
                    <input class="register_yzmclick_txt" id="btn" value='点击获取短信验证码'/>
                </div>
                <div class="register_input_con mt_20px clearfloat"> 
                    <div class="register_read_agreement">
                    	<span class="register_read_agreement_chose" id='agree'></span><i>我已阅读并接受<b onclick="aggrement()">《平台协议》</b></i>
                        <a class="register_btn step_go_btn_1">立即注册</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="register_step step_go_2 display_none">
            <div class="register_step_detail">
            	<div class="register_input_con clearfloat">
                    <span class="register_type_txt">学校选择</span>
                    <div class="register_option_con">
                    	<div class="register_option_detail">
                        	<span class="school_option_txt"></span>
                    		<img class="register_option_arrow" src="images/houtai/term_chose_arrow_option.png" alt=""/>
                        </div>
                        <ul class="school_chose_option_down">
                            <li>湖北工业大学</li>
                            <li>湖北经济学院</li>
                            <li>湖北第二师范学院</li>
                            <li>湖北美术学院</li>
                        </ul>
                    </div>
                </div>
                <div class="register_input_con mt_20px clearfloat">
                    <span class="register_type_txt">学&nbsp;&nbsp;&nbsp;&nbsp;号</span>
                    <input type="text" class="register_input_big body_input"/>
                </div>
                <div class="register_input_con clearfloat"> 
                    <div class="register_read_agreement">
                        <a class="register_btn step_go_btn_2">确认</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="register_step step_go_3 display_none">
        	<div class="register_step_detail">
                <div class="register_finish_con clearfloat"> 
                    <span class="register_finish_ico"></span>
                    <div class="register_finish_txt">恭喜您已成为不拉平台的一员啦，快去学习吧！</div>
                </div>
                <div class="register_finish_con clearfloat"> 
                    <div class="register_finish_btn" onClick="window.location.href='inner_index_login.html'">立即登录</div>
                </div>
            </div> 
        </div>
    </div>
</div>
<form action="${pageContext.request.contextPath}/cy/bindschool.action" id="registerform"  name="registerform" method="post">
    <input type="hidden" id='bindphone' name='phone' value="">
    <input type="hidden" id='login_password' name='login_password' value=""> 
    </form>
<!---底部区域---->
<jsp:include page="../footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
var wait=60;  

function aggrement(){
	window.open("${pageContext.request.contextPath}/jsp/user/agreement.jsp");
}

$("#btn").click(function(){
	if($("#phone").val() != ''){
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
		     url: '${pageContext.request.contextPath}/cy/sendmes.action',
		     data: {phone:$("#phone").val()},
		     success:function (data) {
		     },
		     dataType: "json"
		});
}

$(".register_btn").click(function(){
	validate();
})

function validate(){
	//手机号
	var reg = /^0?1[3|4|5|8][0-9]\d{8}$/;
	if (reg.test($("#phone").val())) {
	 }else{
	      alert("手机号码有误~");
	      return;
	 };
	 //密码
	 if($("#password").val().length<6 || $("#passwordagain").val().length<6){
		 alert("密码必须超过6位");
		 return;
	 }
	 if($("#password").val() != $("#passwordagain").val()){
		 alert("两次密码不一致");
		 return;
	 }
	 //平台协议
	 if($("#agree").attr('class')!='register_read_agreement_chose_selected'){
		 alert("请先阅读平台协议");
		 return;
	 }
	 //验证码
	 $.ajax({
	     type: 'POST',
	     url: '${pageContext.request.contextPath}/cy/checkcode.action',
	     data: {phone:$("#phone").val(),code:$("#code").val()},
	     success:function (data) {
	    	 if(data.type=='success'){
	    		 $("#bindphone").val(data.phone);
	    		 $("#login_password").val($("#passwordagain").val());
		    	 $("#registerform").submit();
	    	 }else{
	    		 alert(data.msg);
	    		 return;
	    	 }
	     },
	     dataType: "json"
	});
	 
}
</script>
</html>
