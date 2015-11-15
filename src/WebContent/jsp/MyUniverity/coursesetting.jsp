<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%-- <%@taglib uri="spring.tld" prefix="spring"%> --%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>后台管理——课程设置</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_houtai_public.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_course_style.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bg_houtai_index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bg_course.js"></script>
<script type="text/javascript" src="resources/js/coursesetting.js"></script>
</head>
<body>

<!----课程设置弹出框--->
<div class="pop_msg_course_set">
	<div class="course_set_list_title">课程设置<img class="course_set_list_pop_close" src="${pageContext.request.contextPath}/images/houtai/new_list_pop_close.png" alt=""/></div>
    <div class="pm_course_set_content">
    	<div class="pm_course_set_type_list courseId">
        	<div class="pm_course_set_type_txt">课程编号</div>
            <div class="pm_course_set_type_input_zone">
            	<input type="text" id="courseId" class="pm_course_set_input"/>
            </div>
        </div>
        <div class="pm_course_set_type_list clearfloat">
        	<div class="pm_course_set_type_txt">课程名称</div>
            <div class="pm_course_set_type_input_zone">
            	<input type="text"id="courseName" class="pm_course_set_input"/>
            </div>
        </div>
        <div class="pm_course_set_type_list clearfloat">
        	<div class="pm_course_set_type_txt">课程负责人</div>
            <div class="pm_course_set_type_input_zone">
            	<input type="text"id="teacherName" class="pm_course_set_input"/>
            	<input type="hidden" id="teacherId" name="course.teacherId"/>
            </div>
            <div class="pm_course_set_type_person_chose">请选择</div>
        </div>
        <div class="pm_course_set_type_list clearfloat">
        	<div class="pm_course_set_type_txt">学分</div>
        	<div class="pm_course_set_type_input_zone">
            	<input type="text"id="credit" class="pm_course_set_input"/>
            </div>
            
        </div>
        <div class="pm_course_set_type_list clearfloat">
        	<div class="pm_course_set_type_txt letter_spacing_8px">总学时</div>
            <div class="pm_course_set_type_input_zone">
            	<input type="text" id="period"class="pm_course_set_input"/>
            </div>
        </div>
        <div class="pm_course_set_type_list clearfloat">
        	<div class="pm_course_set_type_txt letter_spacing_5px">成绩比例</div>
            <div class="pm_course_set_type_input_zone">
                                       平时:
            	<input type="text" id="ratecourse"class="pm_course_set_input"style="width:60px;"/>
            	考试：
            	<input type="text" id="rateTest"class="pm_course_set_input"style="width:60px;"/>
            	作业：
            	<input type="text" id="rateWork"class="pm_course_set_input"style="width:60px;"/>
            	合计：<span id="total"></span>%
            </div>   
            <br>
           
        </div>
         <div class="pm_course_set_type_list clearfloat">
        	<div class="pm_course_set_type_txt letter_spacing_5px"></div>
            <div class="pm_course_set_type_input_zone">
            <span id="input" style="color:#FF0000;"></span>
            </div>   
        </div>
        
        <div class="pm_course_set_btn_con clearfloat">
        	<div class="pm_course_set_btn_detail">
            	<span onclick="submitsetting();">确定</span>
                <b>取消</b>
            </div>
        </div>
    </div>
    <div class="pm_course_set_chose_teacher_content display_none">
    	<div class="pm_cset_cteacher_search_zone clearfloat">
        	<div class="pm_cset_cteacher_search_txt">名称</div>
            <div class="pm_cset_cteacher_search_input_zone">
            	<input type="text" id="searchName"class="pm_cset_cteacher_search_input"/>
            </div>
            <div class="pm_cset_cteacher_search_btn"onclick="listTeacher();">搜索</div>
        </div>
        <div class="pm_cset_cteacher_search_result">
        	<table class="pm_cset_cteacher_search_result_table" cellpadding="0" cellspacing="0">
            	<thead>
                	<tr>
                    	<td></td>
                    	<td>教师名称</td>
                        <td>学校名称</td>
                        <td>手机</td>
                    </tr>
                </thead>
            	<tbody id="teacher_list">
                </tbody>
            </table>
        </div>
        <div class="pm_cset_cteacher_search_btn_con clearfloat">
        	<div class="pm_cset_cteacher_search_btn_detail">
            	<span >确定</span>
                <b>取消</b>
            </div>
        </div>
    </div>
</div>
<div class="pop_msg_bg_course_set"></div>




<!----头部区域-->	
<div class="bg_houtai_header clearfloat">
	<div class="header_logo_con">
    	<span class="header_logo_img">武汉纺织大学</span>
    </div>
    <div class="login_detail_con logined_zone">
    	<div class="user_ico" onClick="window.location.href='user_center.html'"><img src="${pageContext.request.contextPath}/images/user_center/user_img_01.jpg" alt=""/></div>
        <div class="user_txt"><span>莫妮卡</span>，欢迎你！</div>
        <div class="tips_ico_con">
            <span class="total_num">99</span>
            <div class="tips_down_pop">
                <img class="tips_arrow_up_ico" src="${pageContext.request.contextPath}/images/user_center/tips_arrow_up_ico.png" alt=""/>
                <div class="tips_down_pop_img_txt mb_12px">
                    <img class="tips_down_ico_01" src="${pageContext.request.contextPath}/images/user_center/tips_down_ico_01.png" alt=""/>
                    <span>站内私信</span>
                    <b class="add_num">+99</b>
                </div>
                <div class="tips_down_pop_img_txt">
                    <img class="tips_down_ico_01" src="${pageContext.request.contextPath}/images/user_center/tips_down_ico_02.png" alt=""/>
                    <span>系统消息</span>
                    <b class="add_num">+99</b>
                </div>
            </div>
        </div>
        <div class="logout_link">退出</div>
    </div>
</div>

<!---中间内容区域  开始---->
<div class="container">	
	<div class="houtai_con clearfloat">
    	<div class="houtai_menu_nav">
            <ul>
                <li>
                	<span class="ico_1">教学设置</span>
                    <ol class="children_menu_nav">
                    	<li>教学设置</li>
                        <li>教学设置</li>
                        <li>教学设置</li>
                        <li>教学设置</li>
                        <li>教学设置</li>
                        <li>教学设置</li>
                    </ol>
                </li>
                <li>
                	<span class="ico_2">主页维护</span>
                    <ol class="children_menu_nav">
                    	<li>主页维护</li>
                        <li>主页维护</li>
                        <li>主页维护</li>
                        <li>主页维护</li>
                        <li>主页维护</li>
                        <li>主页维护</li>
                    </ol>
                </li>
                <li>
                	<span class="ico_3">功课管理</span>
                	<ol class="children_menu_nav">
                    	<li>开课管理</li>
                        <li>招生管理</li>
                        <li>学生管理</li>
                        <li>教学计划</li>
                        <li>款项发放</li>
                        <li>学情分析</li>
                    </ol>
                </li>
                <li>
                	<span class="ico_4">选课管理</span>
                    <ol class="children_menu_nav">
                    	<li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                    </ol>
                </li>
                <li>
                	<span class="ico_5">学籍管理</span>
                    <ol class="children_menu_nav">
                    	<li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                    </ol>
                </li>
                <li>
                	<span class="ico_6">教务处理</span>
                    <ol class="children_menu_nav">
                    	<li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                    </ol>
                </li>
                <li>
                	<span class="ico_7">教学资源</span>
                    <ol class="children_menu_nav">
                    	<li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                    </ol>
                </li>
            </ul>
        </div>
        <div class="houtai_content_con">
        	<div class="houtai_right_con">
				<div class="houtai_bread_title">课程详情</div>
				<div class="course_set_funtion_con">
                	<div class="course_set_function_detail clearfloat">
                    	<div class="term_chose_con">
                        	<div class="term_chose_content">
                            	<div class="term_chose_content_detail"></div>
                                <div class="term_chose_content_downclick"><img class="term_chose_arrow_option" src="${pageContext.request.contextPath}/images/houtai/term_chose_arrow_option.png" alt=""/></div>
                                <ul class="term_chose_option_down">
                                    <li>2014-2015上学期</li>
                                    <li>2014-2015下学期</li>
                                    <li>2013-2014上学期</li>
                                    <li>2013-2014下学期</li>
                                </ul>
                            </div>
                            <div class="term_chose_txt">学期</div>
                        </div>
                        <div class="course_set_search_con">
                        	<input type="text" class="course_set_search_input" id="courseName"value="请输入要搜索的关键字" onclick="value=''"  onblur="this.value = this.value =='' ? '请输入要搜索的关键字' : this.value "/>
                            <div class="course_set_search_btn"onclick="listcourse();">搜索</div>
                        </div>
                    </div>
                    <c:forEach items="${universityCourselist}" var="item">  
                    <div class="course_set_detail_con clearfloat">
                    	<div class="course_set_img_detail_con">
                        	<img class="course_set_img" src="${pageContext.request.contextPath}/images/houtai/bg_course_set_img1.jpg" alt=""/>
                        </div>
                        <div class="course_set_detail_con_middle">
                        	<h7>${item.courseName}</h7>
                            <div class="course_set_detail_type">
                            	<table class="course_set_detail_type_table" cellpadding="0" cellspacing="0" border="0">
                                	<tr style="display: none;"><td>${item.courseId}</td><td>${item.teacherName}</td></tr>
                                	<tr>
                                    	<td>招生对象：学生</td>
                                        <td>教学方式：网上教学</td>
                                    </tr>
                                    <tr>
                                    <td>课程开始时间：
                                    <c:if test="${item.courseStartTime ==null}">
										未设定	</c:if>
									<span id ="courseStartTime">	
									<fmt:formatDate value="${item.courseStartTime}" pattern="yyyy-MM-dd  HH:mm" />
                                    </span>
										</td>
                                        <td>学分：
                                        <c:if test="${item.credit ==null}">
										未设定	</c:if>
										${item.credit}</td>
                                    </tr>
                                    <tr>
                                    	<td>学时：${item.period}<i></i>选课人数：${item.userTotal}</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                     
                        <div class="course_set_detail_con_right">
                       <c:if test="${item.credit ==null||item.courseStartTime ==null}">
						<span class="bg_6ad7f2 mt_25px"id="startcourse"onClick="startcourse('${item.courseId}');">开课</span>
						<span class="bg_6ad7f2 mt_25px"id="coursesetting" onClick="courseSetPopCenter('${item.courseId}','${item.teacherName}','${item.credit}','${item.courseName}','${item.period}');">设置</span>
						</c:if>
						 <c:if test="${item.credit !=null&&item.courseStartTime !=null}">
                        <span class="bg_f26a6a mt_25px">已开课</span>
                        </c:if>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
        	
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
//课程搜索
	function listcourse(){
	window.location.href="/cy_moocs/cy/universitycoursesearch.action?courseName="+$("#courseName").val();
 }
 
	function listTeacher(){
		$.ajax( {   
		    type : "POST",   
		    url : "/cy_moocs/cy/universitycourse.action",
		    data : {
		    	"realName" :$("#searchName").val()
		     },  
		    dataType: "json",   
		    success : function(teacherList) {   
		    	//alert("teacherList"+);
		    	if (teacherList!=null) { 
		    		var teacherlist = "";
					$.each(teacherList, function(i, item) {
						teacherlist+=getTeacherDiv(item)
			         });  
					$("#teacher_list").html(teacherlist);
					}
			        else{   
			            alert("设置失败！");   
			        }   
			    },   
			    error :function(){   
			        alert("网络连接出错！");   
			    }   
			});   
		}

  function getTeacherDiv(item) {

	//var email = item.email == null ? item.email : item.email;
	//var username = item.username == null ? "" : item.realName;
	var info = "<tr><td>"+"<div id='warp'><i class='pm_cset_cteacher_chosebox' name='teacher'value='" + item.realName
	+ "' id='"+ item.userId+ "'onclick='chooseOption(this)'></i></td></div>" +"<td><span class='td-test-courseId'>"+item.realName
			+ "</span></td>  <td ><span class='td-test-courseName'>"+item.universityName
			+ "</span></td><td ><span class='td-test-courseName'>"+item.phone
			+ "</span></td> </tr>";
	return info;
}
	function getTeacherDiv1(item) {

		//var email = item.email == null ? item.email : item.email;
		//var username = item.username == null ? "" : item.realName;
		var info = "<tr><td class='pct22'><div id='warp'>" + "<input type='radio' name='teacher'id='"
		+ item.userId+ "' class='radioType' value='" + item.realName
				+ "' /><span class='td-test-courseId'>"+item.realName
				+ "</span></div></td>  <td class='pct22'><span class='td-test-courseName'>"+item.universityName
				+ "</span></td><td></td><td class='pct22'><span class='td-test-courseName'>"+item.phone
				+ "</span></td> </tr>";
		return info;
	}
	function chooseOption(obj){
		var optionFlag = $(obj).attr("class");
		var teacherId=$(obj).attr("id");
		var teacherName=$(obj).attr("value");
		alert(teacherName);
			$(obj).parent().find(".pm_cset_cteacher_chosebox_selected").each(function(){
				$(this).attr("class","pm_cset_cteacher_chosebox");
			});
			if(optionFlag == "pm_cset_cteacher_chosebox"){
				$(obj).attr("class","pm_cset_cteacher_chosebox_selected");
			}
			//var teacherId = $('input[name="teacher"]:checked ').attr("id");
			//var teacherName = $('input[name="teacher"]:checked ').val();
			// var teacherId=$(".").attr("id");
			$("#teacherName").val(teacherName);
			$("#teacherId").val(teacherId);
		}
	//课程总学时
	function totalPeriod(){
		var total = ($("#ratecourse").val() - 0) + ($("#rateTest").val() - 0)+($("#rateWork").val() - 0) ;
		$("#total").text(total);
		return total;
	}
	//比率限制
	$(".pm_course_set_input").blur(function(){
		var flag=true;
		if(isNaN($("#ratecourse").val()||($("#ratecourse").val())>100 ) ){
			$("#input").html("提示：请输入0-100的数");
			 flag=false;
			$("#ratecourse").focus();
		}
		else if(isNaN($("#rateTest").val())||($("#rateTest").val())>100){
			$("#input").html("提示：请输入0-100的数");
			 flag=false;
			$("#rateTest").focus();
		}
		else if(isNaN($("#rateWork").val())||($("#rateWork").val())>100){
			$("#input").html("提示：请输入0-100的数");
			 flag=false;
			$("#rateWork").focus();
		}
		else if( (($("#ratecourse").val() - 0) + ($("#rateTest").val() - 0)+($("#rateWork").val() - 0))>100){
			$("#input").html("提示：比率之和不能超过100！请仔细检查后重新输入！");
			 flag=false;
		}
		else if(flag==true){
			$("#input").html("");
		totalPeriod();
		}	
	});
	//提交修改
	function submitsetting(){
		 alert($("#rateWork").val());
		$.ajax( {   
		    type : "POST",   
		   
		    url : "/cy_moocs/cy/universitycourseedit.action",
		    data : {
		      "courseId" :$("#courseId").val(),
		      "credit" :$("#credit").val(),
		      "period" :$("#period").val(),
		      "teacherName": $("#teacherName").val(),
		      "teacherId":$("#teacherId").val(),
		      "examRatio":$("#rateTest").val()/100,
		      "workRatio":$("#rateWork").val()/100
		     },  
		    dataType: "json",   
		    success : function(data) {   
		        if(data.success){   
		         alert("修改成功！");
		     	$(".pop_msg_course_set").toggle();
		     	 $(".pop_msg_bg_course_set").css('display','none');
		     	//
		        }   
		        else{   
		            alert("设置失败！");   
		        }   
		    },   
		    error :function(){   
		        alert("网络连接出错！");   
		    }   
		});   
	}
	   function startcourse(courseId) {
		alert(courseId);
		alert($("#startcourse").val());
			$.ajax({
				type : "POST",
				 url : "/cy_moocs/cy/courseStart.action",
				data : {"courseId":courseId},
				 dataType: "json",
				success : function(data) {
					 if(data.success){   
				         alert("开课成功！");
				         $("#coursesetting").css('display','none');
				        // $("#startcourse").attr("class","btn btn-primary"); 
				       $("#startcourse").removeClass("btn btn-primary")
				       $("#startcourse").addClass("btn btn-default");
				     //  $("#startcourse").attr("text","暂停");
				      // $(this).css('color','#999');$("#two").addClass("divClass2")
				        }   
				        else{   
				            alert("设置失败！");   
				        }   
				    },   
				error : function(jqXHR, textStatus) {
					util.error("操作失败请稍后尝试");
				}
			});
	}
	   $(document).ready(function(){ 
		 var date1=  $("span[id='courseStartTime']").text();
		  
		   
		     //$("span[id='courseStartTime']").text(dd + mmm + yyyy);
		 // alert(new Date(parseInt(date1)).toLocaleDateString());
		 // new Date(parseInt(date1)).toLocaleDateString();
		// $("span[id='courseStartTime']").text(new Date(parseInt(date1)).toLocaleDateString());
		 //alert("1"+$("span[id='courseStartTime']").text());
	   });
	</script>
</html>