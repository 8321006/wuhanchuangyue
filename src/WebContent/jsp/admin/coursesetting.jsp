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
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_administrator_teaching_inquire.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bg_houtai_index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bg_course.js"></script>
</head>
<body>
<!----新增试卷弹出框--->
<div class="pop_msg_administrator_teaching_inquire">
	<div class="pop_teaching_inquire_title clearfloat">
    	<span class="pop_teaching_inquire_titlename">试卷预览</span>
        <img class="teaching_inquire_pop_close" id="paperpreviewclose"src="${pageContext.request.contextPath}/images/administrator/addschool_pop_close.png" alt=""/>
    </div>
    <div class="pop_teaching_inquire_content">
        <div class="pop_teaching_inquire_type_con">
            <div class="pop_teaching_inquire_type_tit"></div>
        </div>
        <div class="pop_teaching_inquire_subject_con">
        </div>
        <div class="pop_teaching_inquire_type_con">
        	<div class="pop_teaching_inquire_type_btn clearfloat">
            </div> 
        </div>
    </div>
</div>
<!----课程设置弹出框--->
<div class="pop_msg_course_set">
	<div class="course_set_list_title">课程设置<img class="course_set_list_pop_close" src="${pageContext.request.contextPath}/images/houtai/new_list_pop_close.png" alt=""/></div>
    <div class="pm_course_set_content">
    	<div class="pm_course_set_type_list courseId">
        	<div class="pm_course_set_type_txt">课程编号</div>
            <div class="pm_course_set_type_input_zone">
            	<input type="text" id="courseId" class="pm_course_set_input"readonly/>
            </div>
        </div>
        <div class="pm_course_set_type_list clearfloat">
        	<div class="pm_course_set_type_txt">课程名称</div>
            <div class="pm_course_set_type_input_zone">
            	<input type="text"id="courseName" class="pm_course_set_input"readonly/>
            </div>
        </div>
        <div class="pm_course_set_type_list clearfloat">
        	<div class="pm_course_set_type_txt">课程负责人</div>
            <div class="pm_course_set_type_input_zone">
            	<input type="text"id="teacherName" class="pm_course_set_input"readonly/>
            	<input type="hidden" id="teacherId" value=""name="course.teacherId"readonly/>
            </div>
            <div class="pm_course_set_type_person_chose"id="TeacherSelect">请选择</div>
        </div>
        <div class="pm_course_set_type_list clearfloat">
        	<div class="pm_course_set_type_txt">期末试卷</div>
            <div class="pm_course_set_type_input_zone">
            	<input type="text"id="paperName" class="pm_course_set_input"readonly/>
            	<input type="hidden" id="paperId" value=""name="paperId"readonly/>
            	<input type="hidden" id="chapterId" value=""name="paperId"readonly/>
            </div>
            <div class="pm_course_set_type_person_chose" onclick="paperSelect();">请选择</div>
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
            	<input type="text" id="period"class="pm_course_set_input"readonly/>
            </div>
        </div>
        <input type="hidden" id="classId" name="classId"/>
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
    <!-- 教师选择列表 -->
    <div class="pm_course_set_chose_teacher_content display_none"id="teacherselectlist">
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
            	<span>确定</span>
                <b>取消</b>
            </div>
        </div>
    </div>
    <!-- 试卷选择列表 -->
    <div class="pm_course_set_chose_teacher_content display_none"id="paperselectlist">
    	<div class="pm_cset_cteacher_search_zone clearfloat">
        	<div class="pm_cset_cteacher_search_txt">名称</div>
            <div class="pm_cset_cteacher_search_input_zone">
            	<input type="text" id="papersearchName"class="pm_cset_cteacher_search_input"/>
            </div>
            <div class="pm_cset_cteacher_search_btn"onclick="listpaper();">搜索</div>
        </div>
        <div class="pm_cset_cteacher_search_result">
        	<table class="pm_cset_cteacher_search_result_table" cellpadding="0" cellspacing="0">
            	<thead>
                	<tr>
                    	<td></td>
                    	<td>课程名称</td>
                        <td>试卷名称</td>
                        <td>操作</td>
                    </tr>
                </thead>
            	<tbody id="paper_list">
                </tbody>
            </table>
        </div>
        <div class="pm_cset_cteacher_search_btn_con clearfloat">
        	<div class="pm_cset_cteacher_search_btn_detail">
            	<span>确定</span>
                <b>取消</b>
            </div>
        </div>
    </div>
</div>

<div class="pop_msg_bg_course_set"></div>




<!----头部区域-->	
<div class="inner_header">
	<jsp:include page="../head-school.jsp"/>
</div>
<div class="houtai_con clearfloat">
<!---中间内容区域  开始---->
<jsp:include page="bg_left.jsp"/>
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
                                <c:forEach items="${courseTermlist}" var="item">
                                    <li ><span id="courseTerm"onclick="courseTermval('${item.courseTerm}');">${item.courseTerm}</span>
                                    </li>
                                    </c:forEach>
                                </ul>
                            </div>
                            <div class="term_chose_txt"onclick="listcoursebyterm();">学期</div>
                        </div>
                        <div class="course_set_search_con">
                        	<input type="text" class="course_set_search_input body_input" id="searchclassName"value="请输入要搜索的关键字" onclick="value=''"  onblur="this.value = this.value =='' ? '请输入要搜索的关键字' : this.value "/>
                            <div class="course_set_search_btn"onclick="listcourse();">搜索</div>
                        </div>
                    </div>
                    <c:if test="${universityCourselist.size()==0}">
                    <div class="course_set_detail_con clearfloat">
                    <div class="course_set_detail_con_middle">
                    <span>未找到相关的信息</span></div></div>
                    </c:if>
                    <c:if test="${universityCourselist.size()>0}">
                    <c:forEach items="${universityCourselist}" var="item">  
                    <div class="course_set_detail_con clearfloat">
                    	<div class="course_set_img_detail_con">
                        	<img class="course_set_img" src="${pageContext.request.contextPath}${item.courseCoverUrl}" alt=""/>
                        </div>
                        <div class="course_set_detail_con_middle">
                        	<h7>${item.courseName}</h7>
                            <div class="course_set_detail_type">
                            	<table class="course_set_detail_type_table" cellpadding="0" cellspacing="0" border="0">
                                	<tr style="display: none;"><td>${item.courseId}</td><td><span id="courseteacherName">${item.teacherName}</span></td></tr>
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
                                        <td>学分：<span id="coursecredit">
                                        <c:if test="${item.credit ==null}">
										未设定	</c:if>
										${item.credit}
										</span></td>
										
                                    </tr>
                                    <tr>
                                    	<td>学时：${item.period}<i></i>选课人数：${item.userTotal}</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                     
                        <div class="course_set_detail_con_right">
                       <c:if test="${item.courseStart=='0'}">
						<span class="bg_6ad7f2 mt_25px"style="display:none"id="startcourse_${item.courseId}"onClick="startcourse(this,'${item.courseId}');"title="点击开课">开课</span>
						<span class="bg_6ad7f2 mt_25px" id="coursesetting_${item.courseId}"onClick="courseSetPopCenter('${item.courseId}','${item.universityId}','${item.courseTerm}');">设置</span>
						</c:if>
						 <c:if test="${item.courseStart=='1'}">
                        <span class="bg_f26a6a mt_25px">已开课</span>
                        </c:if>
                        </div>
                    </div>
                    </c:forEach>
                     </c:if>
                </div>
            </div>
        	
        </div>
        </div>
</body>
<script type="text/javascript">
//课程搜索
	function listcourse(){
	window.location.href="${pageContext.request.contextPath}/cy/universitycoursesearch.action?courseName="+$("#searchclassName").val();
 }
 
	function listTeacher(){
		//alert($("#searchName").val());
		$.ajax( {   
		    type : "POST",   
		    url : "${pageContext.request.contextPath}/cy/universitycourse.action",
		    data : {
		    	"realName" :$("#searchName").val()
		     },  
		    dataType: "json",   
		    success : function(teacherList) {   
		    	//alert("teacherList"+teacherList);
		    	if (teacherList!=null) { 
		    		var teacherlist = "";
					$.each(teacherList, function(i, item) {
						teacherlist+=getTeacherDiv(item)
			         });  
					$("#teacher_list").html(teacherlist);
					}
		    	 if (teacherList==null){   
			            alert("无该学校老师的信息！");  
			            $("#teacher_list").html("无该学校老师的信息！");
			        }   
			    },   
			    error :function(){   
			        alert("网络连接出错！");   
			    }   
			});   
		}

  function getTeacherDiv(item) {
	var info = "<tr><td>"+"<i class='pm_cset_cteacher_chosebox' name='teacher'value='" + item.realName
	+ "' id='"+ item.userId+ "'onclick='chooseOption(this,"+0+")'></i></td>" +"<td><span class='td-test-courseId'>"+item.realName
			+ "</span></td>  <td ><span class='td-test-courseName'>"+item.universityName
			+ "</span></td><td ><span class='td-test-courseName'> <c:if test='${item.phone !=null}'>"+item.phone
			+ "</c:if></span></td> </tr>";
	return info;
}
	function chooseOption(obj,flag){
		var optionFlag = $(obj).attr("class");
		var Id=$(obj).attr("id");
		var Name=$(obj).attr("value");
			$(obj).parent().parent().parent().find(".pm_cset_cteacher_chosebox_selected").each(function(){	
				$(this).attr("class","pm_cset_cteacher_chosebox");
			});
			if(optionFlag == "pm_cset_cteacher_chosebox"){
				$(obj).attr("class","pm_cset_cteacher_chosebox_selected");
			}
			//flag=0---老师列表选择
			//flag=1---试卷列表选择
			if(flag==0){
				$("#teacherName").val(Name);
				$("#teacherId").val(Id);
			}
			if(flag==1){
				var chapterId=$(obj).attr("chapterId");
				$("#paperName").val(Name);
				$("#paperId").val(Id);
				$("#chapterId").val(chapterId);
			}
			
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
		if(isNaN($("#ratecourse").val())||($("#ratecourse").val())>=100||($("#ratecourse").val())<0){
			$("#input").html("提示：请输入0-100之间的数");
			 flag=false;
			$("#ratecourse").focus();
		}
		else if(isNaN($("#rateTest").val())||($("#rateTest").val())>=100||($("#rateTest").val())<0||((($("#ratecourse").val() - 0) + ($("#rateTest").val() - 0))>100)){
			$("#input").html("提示：请输入0-100之间的数");
			 flag=false;
			$("#rateTest").focus();
		}
		else if(isNaN($("#rateWork").val())||($("#rateWork").val())>=100||($("#rateWork").val())<0||((($("#ratecourse").val() - 0) + ($("#rateTest").val() - 0)+($("#rateWork").val() - 0))>100)){
			$("#input").html("提示：请输入0-100之间的数");
			 flag=false;
			$("#rateWork").focus();
		}
		else if((($("#ratecourse").val())>0&&($("#rateTest").val())>0&&($("#rateWork").val())>0)&&((($("#ratecourse").val() - 0) + ($("#rateTest").val() - 0)+($("#rateWork").val() - 0))!=100)){
			$("#input").html("提示：比率之和必须为100%！请仔细检查后重新输入！");
			 flag=false;
		}
		/*
		else if((($("#ratecourse").val())>0&&($("#rateTest").val())>0&&($("#rateWork").val())>0)&&((($("#ratecourse").val() - 0) + ($("#rateTest").val() - 0)+($("#rateWork").val() - 0))==100)){
			$("#input").html("");
		   totalPeriod();
		}	*/
		else if(flag==true){
			$("#input").html("");
		   totalPeriod();
		}
	});
	
	$("#rateTest").focus(function(){
		if((!isNaN($("#ratecourse").val()))
				  &&(!isNaN($("#rateWork").val()))
				  &&$("#ratecourse").val()>0
				  &&$("#rateWork").val()>0
				  &&$("#ratecourse").val()<100
				  &&$("#rateWork").val()<100
				  &&(($("#ratecourse").val()- 0)+($("#rateWork").val() - 0))<100){
			$("#rateTest").val(100-$("#ratecourse").val()-$("#rateWork").val());
			//totalPeriod();
			$("#input").html("");
			}
	});
	$("#rateWork").focus(function(){
		  if(!isNaN($("#ratecourse").val())
				  &&!isNaN($("#rateTest").val())
				  &&$("#ratecourse").val()>0
				  &&$("#rateTest").val()>0
				  &&$("#ratecourse").val()<100
				  &&$("#rateTest").val()<100
				  &&$("#rateWork").val()<100
				  &&(($("#ratecourse").val()-0)+($("#rateTest").val()-0))<100){
				$("#rateWork").val(100-$("#ratecourse").val()-$("#rateTest").val());
				$("#input").html("");
				totalPeriod();
			}
		
	});
	$("#ratecourse").focus(function(){
		 if(!isNaN($("#rateWork").val())
				  &&!isNaN($("#rateTest").val())
				  &&$("#rateWork").val()>0
				  &&$("#rateTest").val()>0
				  &&$("#rateWork").val()<100
				  &&$("#rateTest").val()<100
				  &&$("#ratecourse").val()<100
				  &&(($("#rateWork").val()-0)+($("#rateTest").val()-0))<100){
				$("#ratecourse").val(100-$("#rateWork").val()-$("#rateTest").val());
				$("#input").html("");
			}	
	});
	/*
	$(".pm_course_set_input").focus(function(){
	  if((!isNaN($("#ratecourse").val()))
			  &&(!isNaN($("#rateWork").val()))
			  &&$("#ratecourse").val()>0
			  &&$("#rateWork").val()>0
			  &&$("#ratecourse").val()<100
			  &&$("#rateWork").val()<100
			  &&$("#rateTest").val()<100
			  &&(($("#ratecourse").val()-0)+($("#rateWork").val()-0))<100){
		$("#rateTest").val(100-$("#ratecourse").val()-$("#rateWork").val());
		totalPeriod();
		$("#input").html("");
		}
	  else if(!isNaN($("#ratecourse").val())
			  &&!isNaN($("#rateTest").val())
			  &&$("#ratecourse").val()>0
			  &&$("#rateTest").val()>0
			  &&$("#ratecourse").val()<100
			  &&$("#rateTest").val()<100
			  &&$("#rateWork").val()<100
			  &&(($("#ratecourse").val()-0)+($("#rateTest").val()-0))<100){
			$("#rateWork").val(100-$("#ratecourse").val()-$("#rateTest").val());
			$("#input").html("");
			totalPeriod();
		}
	  else if(!isNaN($("#rateWork").val())
			  &&!isNaN($("#rateTest").val())
			  &&$("#rateWork").val()>0
			  &&$("#rateTest").val()>0
			  &&$("#rateWork").val()<100
			  &&$("#rateTest").val()<100
			  &&$("#ratecourse").val()<100
			  &&(($("#rateWork").val()-0)+($("#rateTest").val()-0))<100){
			$("#ratecourse").val(100-$("#rateWork").val()-$("#rateTest").val());
			$("#input").html("");
		}	
	   else if( (($("#ratecourse").val() - 0) + ($("#rateTest").val() - 0)+($("#rateWork").val() - 0))==100){
			//$("#input").html("");
			totalPeriod();
		}
	  
		});
	*/
	//提交修改
	function submitsetting(){
		var flag=check();
		 var courseId=$("#courseId").val();
		  if (flag==0) {
		$.ajax( {   
		    type : "POST",   
		    url : "${pageContext.request.contextPath}/cy/universitycourseedit.action",
		    data : {
		      "courseId" :$("#courseId").val(),
		      "credit" :$("#credit").val(),
		      "period" :$("#period").val(),
		      "teacherName": $("#teacherName").val(),
		      "teacherId":$("#teacherId").val(),
		      "paperId":$("#paperId").val(),
		      "chapterId":$("#chapterId").val(),
		      "classId":$("#classId").val(),
		      "videoRatio":$("#ratecourse").val()/100,
		      "examRatio":$("#rateTest").val()/100,
		      "workRatio":$("#rateWork").val()/100
		     },  
		    dataType: "json",   
		    success : function(data) {   
		        if(data.success){   
		         alert("修改成功！");
		     	 $(".pop_msg_course_set").toggle();
		     	 $(".pop_msg_bg_course_set").css('display','none');
		     	$("#coursecredit").html($("#credit").val());
		     	//课程修改成功后，将开课设置按钮改为开课按钮
		     	 $("#coursesetting_"+courseId).css('display','none');
		     	 $("#startcourse_"+courseId).show();//显示div 
		        }   
		        else{   
		            alert("设置失败！");   
		        }   
		    },   
		    error :function(){   
		        alert("网络连接出错！");   
		    }   
		});   
	}else{
		$("#input").html("提示：您的修改不符合要求！");
		alert("您的修改不符合要求，请仔细检查后在提交!");
		}
	}
	   function startcourse(obj,courseId) {
		   var flag=check();
			  //alert("flag"+flag);
			  if (flag==0) {
			$.ajax({
				type : "POST",
				 url : "${pageContext.request.contextPath}/cy/courseStart.action",
				data : {"courseId":courseId},
				 dataType: "json",
				success : function(data) {
					 if(data.success){   
				         alert("开课成功！");
				         $("#startcourse_"+courseId).text("已开课");
				         $("#startcourse_"+courseId).css("background","#f26a6a");
				        // $(obj).parent().find("#startcourse").text("已开课");
				        // $(obj).parent().find("#startcourse").css("background","#f26a6a");
				          //$(obj).siblings("#coursesetting").css('display','none');
				        }   
				        else{   
				            alert("设置失败！");   
				        }   
				    },   
				error : function(jqXHR, textStatus) {
					util.error("操作失败请稍后尝试");
				}
			});
	}else{
		alert("该课程还未设置！不准许开课");
		}
	}
	   //提交前进行check操作
	   function check(){
			var flag=0;
			if($("#teacherName").val()==''){
			$("#input").html("提示：请选择老师！");
				//$("#rateWork").focus();
				flag++;
			}
			if($("#credit").val()==0){
				$("#input").html("提示：该课程的学分尚未设置！");
					$("#credit").focus();
					flag++;
				}
			if($("#paperName").val()==''){
				$("#input").html("提示：该课程的试卷尚未设置！");
					$("#paperName").focus();
					flag++;
				}
			if(isNaN($("#ratecourse").val())||($("#ratecourse").val())>100||($("#ratecourse").val())==0||
					isNaN($("#rateTest").val())||($("#rateTest").val())>100||($("#rateWork").val())==0||
					isNaN($("#rateWork").val())||($("#rateWork").val())>100||($("#rateWork").val())==0||
					((($("#ratecourse").val() - 0) + ($("#rateTest").val() - 0)+($("#rateWork").val() - 0))!=100)){	
						$("#input").html("提示：成绩比率总合必须为100%！");
						$("#ratecourse").focus();
						flag++;
					}
				return flag;
		}
	   //根据学期来查询
	   function listcoursebyterm(){
			window.location.href="${pageContext.request.contextPath}/cy/search.action?courseTerm="+$(".term_chose_content_detail").html();
		}
	   function courseTermval(courseTerm){
		  $("term_chose_content_detail").text(courseTerm);
	   }
	   function courseSetPopCenter(courseId,universityId,courseTerm){
			$('.pop_msg_course_set').css('display','block');
			$('.pop_msg_bg_course_set').css('display','block');
			$('.houtai_con clearfloat').css('display','block');
			var w_width = $(window).width();
			var w_height = $(window).height();
			var b_height = $(document.body).height();
			if(b_height<w_height){
				$('.pop_msg_bg_course_set').css('height',w_height);
				}else{
					$('.pop_msg_bg_course_set').css('height',b_height);
					}
			var w_self = $('.pop_msg_course_set').width();
			var h_self = $('.pop_msg_course_set').height();
			var _x = w_width/2 - w_self/2;
			var _y = w_height/2 - h_self/2;
			$('.pop_msg_course_set').css({left:_x,top:_y});
			//CourseList(courseId,universityId,courseTerm);
			$.ajax({
				 type : "POST",
				 url : "${pageContext.request.contextPath}/cy/courseSet.action",
				 data : {
					"courseId":courseId,
					"universityId":universityId,
					"courseTerm":courseTerm
				},
				 dataType: "json",   
				success : function(data) {
					if (data.length != 0) { 
						$("#courseId").val(courseId);
						$("#courseName").val(data[0].courseName);
						$("#teacherId").val(data[0].teacherId);
						$("#teacherName").val(data[0].teacherName);
						$("#credit").val(data[0].credit);
						$("#period").val(data[0].period);
						$("#classId").val(data[0].classId);
						$("#paperName").val(data[0].paperName);
						if(data[0].videoRatio>=0){
						$("#ratecourse").val(data[0].videoRatio*100);
						}
						if(data[0].examRatio>=0){
							$("#rateTest").val(data[0].examRatio*100);
						}
						if(data[0].workRatio>=0){
							$("#rateWork").val(data[0].workRatio*100);
						}
						$("#total").text(($("#ratecourse").val()-0)+($("#rateTest").val()-0)+($("#rateWork").val()-0));		
				         }		
				        else{   
				            alert("连接出错！");   
				        }   
				    },   
				    error :function(){   
				        alert("网络连接出错！");   
				    }   
				}); 
			}
	   $("#searchName").keyup(function(e){
			if(e.keyCode == 13) {
				listTeacher(0);
			}
		}) ;
	   
	   
	   $("#searchclassName").keyup(function(e){
			if(e.keyCode == 13) {
				listcourse(0);
			}
		}) ;
	   $("#papersearchName").keyup(function(e){
			if(e.keyCode == 13) {
				listpaper(0);
			}
		}) ;
	   
	 //教师选择
	   $('#TeacherSelect').click(function(){
			$('.pm_course_set_content').css('display','none');
			$('#teacherselectlist').css('display','block');
			listTeacher();
		});
	   //试卷选择
	   function paperSelect(){
		   $('.pm_course_set_content').css('display','none');
			$('#paperselectlist').css('display','block');
		   listpaper();
	   }
	   //试卷列表
	   function listpaper(){
			$.ajax( {   
			    type : "POST",   
			    url : "${pageContext.request.contextPath}/cy/paperlist.action",
			    data : {
			    	"paperName" :$("#papersearchName").val(),
			    	"courseId":$("#courseId").val()
			     },  
			    dataType: "json",   
			    success : function(data){
				    	var paperlist = "";
				    	for(var i=0 ; i<data.length; i++){
				    		paperlist+=getchoicehtml(data[i]);
				    	}		    	
				    	$("#paper_list").html(paperlist);
				    	},		
					    error :function(){   
					        alert("网络连接出错！");   
					    }   
					}); 
			}
	   
	   //试卷列表拼接html
	   function getchoicehtml(data) {
		var info = "<tr><td>"+"<i class='pm_cset_cteacher_chosebox' name='teacher'value='" + data.paperName
			+ "' id='"+ data.paperId+ "'chapterId='"+data.chapterId+"'onclick='chooseOption(this,"+1+")'></i></td>" +"<td><span class='td-test-courseId'>"+data.courseName
			+ "</span></td>  <td ><span class='td-test-courseName'>"+data.paperName
			+ "</span></td><td ><span class='administrator_addstudent_table_look' onclick='paperpreview(\""+data.paperId+"\",\""+data.paperName+"\")' >阅览</span></td> </tr>";
			return info;
		}
	   // 试卷预览
	   function paperpreview(paperId,paperName){
		   $(".pop_msg_course_set").css('display','none');
			$('.pop_msg_administrator_teaching_inquire').css('display','block');
			$('.pop_msg_bg_administrator_teaching_inquire').css('display','block');
			var w_width = $(window).width();
			var w_height = $(window).height();
			var b_height = $(document.body).height();
			if(b_height<w_height){
				$('.pop_msg_bg_administrator_teaching_inquire').css('height',w_height);
				}else{
					$('.pop_msg_bg_administrator_teaching_inquire').css('height',b_height);
					}
			var w_self = $('.pop_msg_administrator_teaching_inquire').width();
			var h_self = $('.pop_msg_administrator_teaching_inquire').height();
			var _x = w_width/2 - w_self/2;
			var _y = w_height/2 - h_self/2;
			$('.pop_msg_administrator_teaching_inquire').css({left:_x,top:_y});
			
			$.ajax({
				 type : "POST",
				 url : "${pageContext.request.contextPath}/survey/survey-preview.action",
				 data : {
					"paperId":paperId
				},
				 dataType: "json",   
				 success : function(data){
					 $(".pop_teaching_inquire_type_tit").html(paperName);
				    	var surveylist = "";
				    	for(var i=0 ; i<data.length; i++){
				    		surveylist+=getchoicepaperhtml(data[i]);
				    	}		    	
				    	$(".pop_teaching_inquire_subject_con").html(surveylist);
				    	},		
					    error :function(){   
					        alert("网络连接出错！");   
					    }   
					});
		}
	 //试卷遍历
	   function getchoicepaperhtml(data){
		   //var surveylist="";
		   var surveylist = "<div class='pop_teaching_inquire_subject_list'>";
		   if(data.questionTypeId==1){
			 var obj = eval(jQuery.parseJSON(data.content).choiceList);
			 //var obj2=eval(jQuery.parseJSON(data.content).title);
				surveylist=surveylist+"<div class='pop_teaching_inquire_sub_title'>"+data.questionNo+"、"+jQuery.parseJSON(data.content).title+"（ 单选  ）"+data.points+"分</div>";
			 $.each(obj,function(index2,value) {  
			 surveylist=surveylist+"<div class='pop_teaching_inquire_sub_chose clearfloat'>"+"<span class='pop_teaching_inquire_sub_chosebox' name='"+value.option+"' onclick='chooseOption(this,"+value.objType+")'>"+"</span>"
					 +" <div class='pop_teaching_inquire_sub_chose_tit'>"+value.option+"．"+value.answer+"</div>"+"</div></div>";
		    });
			} 
		   if(data.questionTypeId==2){
				 var obj = eval(jQuery.parseJSON(data.content).choiceList);
					surveylist=surveylist+"<div class='pop_teaching_inquire_sub_title'>"+data.questionNo+"、"+jQuery.parseJSON(data.content).title+"（ 多选  ）"+data.points+"分</div>";
				 $.each(obj,function(index2,value) {  
				 surveylist=surveylist+"<div class='pop_teaching_inquire_sub_chose clearfloat'>"+"<span class='pop_teaching_inquire_sub_chosebox' name='"+value.option+"' onclick='chooseOption(this,"+value.objType+")'>"+"</span>"
						 +" <div class='pop_teaching_inquire_sub_chose_tit'>"+value.option+"．"+value.answer+"</div>"+"</div></div>";
			    });
				} 
		   if(data.questionTypeId==3){
				 var obj = eval(jQuery.parseJSON(data.content).choiceList);
				 
					surveylist=surveylist+"<div class='pop_teaching_inquire_sub_title'>"+data.questionNo+"、"+jQuery.parseJSON(data.content).title+"（ 判断  ）"+data.points+"分</div>";
				 
				 surveylist=surveylist+"<div class='pop_teaching_inquire_sub_chose clearfloat'>"+"<span class='pop_teaching_inquire_sub_chosebox' name='T'>"+"</span>"
						 +" <div class='pop_teaching_inquire_sub_chose_tit'>正确</div>"+"</div>"+
						 "<div class='pop_teaching_inquire_sub_chose clearfloat'>"+"<span class='pop_teaching_inquire_sub_chosebox' name='F'>"+"</span>"
						 +" <div class='pop_teaching_inquire_sub_chose_tit'>错误</div></div>"+
						 "</div>";
			   
				} 
		   if(data.questionTypeId==4){
			   surveylist=surveylist+"<div class='pop_teaching_inquire_sub_title'>"+data.questionNo+"、"+jQuery.parseJSON(data.content).title+"（ 填空  ）"+data.points+"分</div><textarea class='pop_teaching_inquire_ask_textarea'></textarea></div>"+"</div></div>"; 
		   }  
		   if(data.questionTypeId==5){
			   surveylist=surveylist+"<div class='pop_teaching_inquire_sub_title'>"+data.questionNo+"、"+jQuery.parseJSON(data.content).title+"（ 简答  ）"+data.points+"分</div><textarea class='pop_teaching_inquire_ask_textarea'></textarea></div>"+"</div></div>"; 
		   }  
		   return surveylist
	}
	   $('#paperpreviewclose').click(function(){
			$('.pop_msg_administrator_teaching_inquire').css('display','none');
			$('.pop_msg_bg_administrator_teaching_inquire').css('display','none');
			$('#paperselectlist').css('display','block');
			$(".pop_msg_course_set").css('display','block');
		});
		//后台左边菜单被选中
	   $("#leftbar").find("li").each(function(){
			$(this).removeClass();
		});
	   $("#function_management ol").removeClass('display_none');
		$("#courseSet").attr("class","selected");
	</script>
</html>