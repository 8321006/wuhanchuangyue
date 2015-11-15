<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>后台管理——老师列表</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_houtai_public.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_new_list.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_course_style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bg_houtai_index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bg_new_list.js"></script>
</head>
<body>
<!----新增弹出框--->
<div class="pop_msg_bnew_list">
	<div class="bnew_list_title">新增老师<img class="bnew_list_pop_close" src="${pageContext.request.contextPath}/images/houtai/new_list_pop_close.png" alt=""/></div>
    <div class="bnew_list_content">
    	<div class="bnew_list_link clearfloat">
        	<div class="bnew_list_left_txt">姓名</div>
            <div class="bnew_list_right">
            	<input type="hidden" id="userId" class="bnew_list_add_input width_680px"/>
            	<input type="hidden" id="loginName" class="bnew_list_add_input width_680px"/>
            	<input type="hidden" id="loginPassword" class="bnew_list_add_input width_680px"/>
            	<input type="hidden" id="userType" class="bnew_list_add_input width_680px"/>
            	<input type="hidden" id="universityId" class="bnew_list_add_input width_680px"/>
            	<input type="text" id="realName" class="bnew_list_add_input width_680px"/>
            	<span id="xm" style="color:red;"></span>
            </div>
        </div>
        <div class="bnew_list_link clearfloat">
        	<div class="bnew_list_left_txt">手机号码</div>
            <div class="bnew_list_right">
            	<input type="text" id="phone" class="bnew_list_add_input width_680px"/>
            	<span id="sjhm" style="color:red;"></span>
            </div>
        </div>
         <div class="bnew_list_link clearfloat">
        	<div class="bnew_list_left_txt letter_spacing_12px">性别</div>
            <div class="bnew_list_right">
            <div class="sex_chose_zone">
            	<span type='0' class="sex_chosebox_selected"></span>男
            </div>
            <div class="sex_chose_zone">
            	<span type='1' class="sex_chosebox"></span>女
            </div>
            </div>
        </div>
        <div class="bnew_list_link clearfloat">
        	<div class="bnew_list_send_btn_center">
            	<span onclick="getInsert();">确定</span>
                <b>取消</b>
            </div>
        </div>
    </div>
    </div>
<div class="pop_msg_bg_teacher_list"></div>


<!----头部区域-->	
<div class="inner_header">
	<jsp:include page="../head-school.jsp"/>
</div>



<div class="houtai_con clearfloat">

<!---中间内容区域  开始---->

<jsp:include page="bg_left.jsp"/>


        <div class="houtai_content_con">
        	<div class="houtai_right_con">
				<div class="houtai_bread_title">老师列表</div>
				<div class="new_list_funtion_con">
                	<div class="new_list_function_detail clearfloat">
                    	<div class="news_add_con" onClick="newTeacherAddPopCenter();">
                        	<img class="new_list_add_ico" src="${pageContext.request.contextPath}/images/houtai/new_list_add_ico.png" alt=""/>
                        	<span class="new_list_add_txt">新增</span>
                        </div>
                        <div class="news_search_con">
                        	<input id="realname" type="text" class="news_search_input" value="${searcharea == null ? '请输入要搜索的姓名': searcharea}" onclick="value=''"  
                        	onblur="this.value = this.value =='' ? '请输入要搜索的姓名' : this.value "/>
                            <div class="news_search_btn" onclick="tinsearch()">搜索</div>
                        </div>
                       <div class="term_chose_content_select_con">
						<select id="tcourseterm" class="term_chose_content_select_con term_chose_content_detail">
							<option value="">全部</option>
							<c:forEach items="${tcourselist}" var="t">
								<option value="${t.courseId}" ${courseName == t.courseName ? 'selected': '' }>${t.courseName}</option>
							</c:forEach>
						</select> 
						<span class="term_chose_content_select_con term_chose_content_detail_btn">课程</span>
					 </div>
                        
                        
                        
                    </div>
                	<table class="new_list_detail_table" cellpadding="0" cellspacing="0">
                        <thead>
                        	<tr>
                        		<td>教师名称</td>
                        		<!-- <td>教师编号</td> -->
                        		<td>性别</td>
                                <td>联系方式</td>  
                            	<!-- <td>课程名称</td>
                            	<td>课程学年</td>                                                                       
                                <td>学校</td>
                                <td>操作</td> -->
                            </tr>
                        </thead>
                        <tbody>
                         <c:if test="${list.size()==0}">
                         <tr><td colspan="3">暂无老师信息</td></tr>
                        </c:if>
                         <c:if test="${list.size()>0}">
                         <c:forEach items="${list}" var="u">
                        	<tr>
                        		<td>${u.realName}</td>
                        		<%-- <td>${u.userId}</td> --%>
                                <td>${u.sex == 0 ? '男' : '女'}</td>
                                <td>${u.phone}</td>
                            	<%--  <td>${u.courseName}</td>
                                <td>${u.courseTerm}</td>                               
                                <td>${u.universityName}</td>                              
                               <td>
                                	<span class="new_list_table_edit">编辑</span>
                                    <span class="new_list_table_del">删除</span>
                                </td> --%>
                            </tr>
                            </c:forEach>
                            </c:if>
                        </tbody>
                    
                    </table>
                    <form action="${pageContext.request.contextPath}/cy/teacherlist.action" id="searchform"  name="searchform" method="post">
                    	<input type='hidden' id='searcharea' name='searcharea' value='${searcharea}'/>
						<input type='hidden' id='courseName' name='courseName' value='${courseName}'/>
						<input type='hidden' id='courseId' name='courseId' value='${courseId}'/>
						<input type='hidden' id='universityId' name='universityId' value='${universityId}'>
					</form>
					<c:if test="${not empty list}"> 			
         			<tags:pager pagerRange="10" pageSize="${page.pageSize}" totalPage="${page.pages}" curIndex="${page.pageNum}" formId="searchform"></tags:pager>              
           		 	</c:if> 
            		<c:if test="${empty list}">
            		</c:if>                
            	</div>
            </div>
        	
        </div>
    </div>	
<script type="text/javascript">	
$('.sex_chose_zone').click(function(){
	$('.sex_chose_zone').children('span').removeClass('sex_chosebox_selected');
	$('.sex_chose_zone').children('span').addClass('sex_chosebox');
	$(this).children('span').addClass('sex_chosebox_selected');
	
});

	function tinsearch(){
		$("#courseName").val($("#tcourseterm option:selected").text());
		var realName = $("#realname").val();
		$("#searcharea").val(realName == '请输入要搜索的姓名'?"":realName);
		//$("#courseId").val($("#tcourseterm").val());
		 var courseId = $("#tcourseterm").val();
		$("#courseId").val(courseId == '全部'?'':courseId); 
		$("#searchform").submit();
	}
	
	function getInsert(){
		var val = document.getElementById('phone').value;
		var reg = /(^13\d{9}$)|(^14)[5,7]\d{8}$|(^15[0,1,2,3,5,6,7,8,9]\d{8}$)|(^17)[6,7,8]\d{8}$|(^18\d{9}$)/g ;   
		var realname = document.getElementById("realName").value;
		var mophone = document.getElementById("phone").value;
		if(realname==""){
			document.getElementById("xm").innerHTML = "请填写姓名";
			$("#xm").delay(4000).fadeOut("slow");
			return;
		}
		if(mophone==""){
			document.getElementById("sjhm").innerHTML = "请填写手机号码";
			$("#sjhm").delay(4000).fadeOut("slow");
			return;
		}
		if(reg.test(val)){  
			$.ajax({
				type:"POST",
				url:"${pageContext.request.contextPath}/cy/teacherInsert.action",
				data:{
				      "userId" :$("#userId").val(),
				      "loginName" :$("#loginName").val(),
				      "loginPassword" :$("#loginPassword").val(),
				      "realName" :$("#realName").val(),
				      "phone" :$("#phone").val(),
				      "sex":$(".sex_chosebox_selected").attr("type"),
				      "universityId":$("#universityId").val(),
				      "userType" :$("#userType").val(),
				},
				datatype:"json",
				success:function(data){
						var res = data.result;
						if(res != null){
							if(res == "1"){
							getpwd();	
							alert("添加老师成功");
							}else if(res == "0"){
								alert("添加老师失败");
								
							}else{
								alert("该手机号码已经存在");						
							}
							$(".pop_msg_bnew_list").toggle();
					     	$(".pop_msg_bg_teacher_list").css('display','none');
					     	document.searchform.submit();
					}
				}	
			});
		    }else{  
		    	document.getElementById("sjhm").innerHTML = "输入有误，请重新输入";
				$("#sjhm").delay(4000).fadeOut("slow");
				return;
		    }  
	}
	
	function getpwd(){
		$.ajax({
		     type: 'POST',
		     url: '${pageContext.request.contextPath}/cy/teacherMsg.action',
		     data: {phone:$("#phone").val()},
		     success:function (data) {
		     },
		     dataType: "json"
		});
}
//后台左边菜单被选中
	$("#leftbar").find("li").each(function(){
	$(this).removeClass();
	});
   $("#function_management ol").removeClass('display_none');
	$("#teacherSet").attr("class","selected");
</script>
</body>
</html>
