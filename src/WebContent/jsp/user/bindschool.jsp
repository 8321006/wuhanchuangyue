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
       <div class="register_step step_go_2">
            <div class="register_step_detail">
            	<div class="register_school_info">
                	<div class="register_tit_txt">请完善以下信息完成注册</div>
                    <div class="register_school_img_con">
                    	<img class="register_school_img" src="${pageContext.request.contextPath}/images/register/register_school_home_img.png" alt=""/>
                    </div>
                </div>
            	<div class="register_input_con clearfloat">
                    <span class="register_type_txt">学校选择</span>
                    <div class="register_option_con">
                    	<div class="register_option_detail">
                        	<span class="school_option_txt"></span>
                    		<img class="register_option_arrow" src="${pageContext.request.contextPath}/images/houtai/term_chose_arrow_option.png" alt=""/>
                        </div>
                        <ul class="school_chose_option_down">
                        	<c:forEach items="${universitys}" var="t">
                            <li pic="${t.universityLogo}" schid="${t.universityId}">${t.universityName}</li>
                            </c:forEach>
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
<form action="${pageContext.request.contextPath}/cy/addcount.action" id="addcount"  name="addcount" method="post">
    <input type="hidden" id='phone' name='phone' value="${phone}">
    <input type="hidden" id='studentId' name='studentId' value=""> 
    <input type="hidden" id='resuniversityId' name='resuniversityId' value="">
    <input type="hidden" id='loginPassword' name='loginPassword' value="${login_password}"> 
    </form>
<!---底部区域---->
<jsp:include page="../footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
$(".register_option_detail").click(function(){
	if($(".school_chose_option_down").css("display")=="none"){
		$(".school_chose_option_down").slideDown("fast");
	}else{
		$(".school_chose_option_down").slideUp("fast");
	}
});
$(".school_chose_option_down li").click(function(){
	var txt = $(this).text();
	$(".register_option_detail .school_option_txt").text(txt);
	$(".register_school_img").attr("src",$(this).attr("pic"));
	$("#resuniversityId").val($(this).attr("schid"))
	$(".school_chose_option_down").hide();
});
$(".school_chose_option_down").mouseleave(function(){
	$(".school_chose_option_down").hide();
})

$(".step_go_btn_2").click(function(){
	//验证学号
	 $.ajax({
	     type: 'POST',
	     url: '${pageContext.request.contextPath}/cy/checkschoolId.action',
	     data: {studentId:$(".register_input_big").val(),universityId:$("#resuniversityId").val()},
	     success:function (data) {
	    	 if(data.type=='success'){
	    		 $("#studentId").val($(".register_input_big").val());
		    	 $("#addcount").submit();
	    	 }else{
	    		 alert(data.msg);
	    		 return;
	    	 }
	     },
	     dataType: "json"
	});
})
</script>
</html>
