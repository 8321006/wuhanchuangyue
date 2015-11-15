<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%> 
<%@page import="com.cy.model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>平台后台</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/medioadaption.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_administrator_public.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_administrator_teaching_inquire.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bg_administrator_index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bg_administrator_teaching_inquire.js"></script>
<%
User current = (User)session.getAttribute("user");
if(current == null || current.getUserType() != 3){
	response.sendRedirect(basePath + "index.action");
}
%>
</head>
<body>
<!----新增教学调查弹出框--->
<div class="pop_msg_administrator_teaching_inquire">
	<div class="pop_teaching_inquire_title clearfloat">
    	<span class="pop_teaching_inquire_titlename">调查问卷</span>
        <img class="teaching_inquire_pop_close" src="${pageContext.request.contextPath}/images/administrator/addschool_pop_close.png" alt=""/>
    </div>
    <div class="pop_teaching_inquire_content">
        <div class="pop_teaching_inquire_type_con">
            <div class="pop_teaching_inquire_type_tit"></div>
        </div>
        <div class="pop_teaching_inquire_subject_con">
        <!-- 调查问卷-->
            <div class="pop_teaching_inquire_subject_list">
                <div class="pop_teaching_inquire_sub_title">1、你理想中的通识课是什么样子的：（  多选   ） 30分</div>
                <div class="pop_teaching_inquire_sub_chose clearfloat">
                    <span class="pop_teaching_inquire_sub_chosebox_selected"></span>
                    <div class="pop_teaching_inquire_sub_chose_tit">A．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
                <div class="pop_teaching_inquire_sub_chose clearfloat">
                    <span class="pop_teaching_inquire_sub_chosebox"></span>
                    <div class="pop_teaching_inquire_sub_chose_tit">B．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
                <div class="pop_teaching_inquire_sub_chose clearfloat">
                    <span class="pop_teaching_inquire_sub_chosebox"></span>
                    <div class="pop_teaching_inquire_sub_chose_tit">C．课堂自由、有趣，内容丰富，能增长见识</div>
                </div><div class="pop_teaching_inquire_sub_chose clearfloat">
                    <span class="pop_teaching_inquire_sub_chosebox"></span>
                    <div class="pop_teaching_inquire_sub_chose_tit">D．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
                <div class="pop_teaching_inquire_sub_chose clearfloat">
                    <span class="pop_teaching_inquire_sub_chosebox"></span>
                    <div class="pop_teaching_inquire_sub_chose_tit">E．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
            </div>
            <div class="pop_teaching_inquire_subject_list">
                <div class="pop_teaching_inquire_sub_title">1、你理想中的通识课是什么样子的：（  多选   ） 30分</div>
                <div class="pop_teaching_inquire_sub_chose clearfloat">
                    <span class="pop_teaching_inquire_sub_chosebox_selected"></span>
                    <div class="pop_teaching_inquire_sub_chose_tit">A．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
                <div class="pop_teaching_inquire_sub_chose clearfloat">
                    <span class="pop_teaching_inquire_sub_chosebox"></span>
                    <div class="pop_teaching_inquire_sub_chose_tit">B．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
                <div class="pop_teaching_inquire_sub_chose clearfloat">
                    <span class="pop_teaching_inquire_sub_chosebox"></span>
                    <div class="pop_teaching_inquire_sub_chose_tit">C．课堂自由、有趣，内容丰富，能增长见识</div>
                </div><div class="pop_teaching_inquire_sub_chose clearfloat">
                    <span class="pop_teaching_inquire_sub_chosebox"></span>
                    <div class="pop_teaching_inquire_sub_chose_tit">D．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
                <div class="pop_teaching_inquire_sub_chose clearfloat">
                    <span class="pop_teaching_inquire_sub_chosebox"></span>
                    <div class="pop_teaching_inquire_sub_chose_tit">E．课堂自由、有趣，内容丰富，能增长见识</div>
                </div>
        	</div>
            <div class="pop_teaching_inquire_subject_list">
                <div class="pop_teaching_inquire_sub_title">1、你理想中的通识课是什么样子的：（  问答   ） 30分</div>
                <textarea class="pop_teaching_inquire_ask_textarea"></textarea>
            </div>
        </div>
        <div class="pop_teaching_inquire_type_con">
        	<div class="pop_teaching_inquire_type_btn clearfloat">
            </div> 
        </div>
    </div>
</div>
<div class="pop_msg_bg_administrator_teaching_inquire"></div>
<!---中间内容区域  开始---->
<div class="administrator_container">	
	<div class="bg_administrator_con">
    	<div class="administrator_left_menu">
        	<div class="administrator_img_con">
            	<div class="administrator_img_content">
                	<img class="administrator_person_img" src="${pageContext.request.contextPath}/images/administrator/person_img_01.png" alt=""/>
                </div>
                <p class="administrator_person_name">${user.realName }</p>
            </div>
            <div class="administrator_left_menu_nav">
            	<ul class="administrator_menu_nav">
                    <li>
                        <span>
                            <b class="administrator_ico_1"></b>
                            <a href="${pageContext.request.contextPath}/university/listAll.action"><i>学校管理</i></a>
                        </span> 
                    </li>
                    <li>
                        <span>
                            <b class="administrator_ico_2"></b>
                             <a href="${pageContext.request.contextPath}/course/courseList.action"><i>新增课程</i></a>
                        </span> 
                    </li>
                    <li>
                        <span>
                            <b class="administrator_ico_3"></b>
                            <a href="${pageContext.request.contextPath}/cy/userImportList.action"><i>学生管理</i></a>
                        </span>  
                    </li>
                    <li>
                        <span>
                            <b class="administrator_ico_4"></b>
                            <a href="${pageContext.request.contextPath}/university/goAnalysis.action"><i>统计分析</i></a>
                        </span> 
                    </li>
                    <li class="curr">
                        <span>
                            <b class="administrator_ico_5"></b>
                              <a href="${pageContext.request.contextPath}/survey/coursesurveylist.action"><i>教学调查</i></a>
                        </span> 
                    </li>
                    <li>
                        <span>
                            <b class="administrator_ico_5"></b>
                              <a href="${pageContext.request.contextPath}/notice/gosystemNotice.action"><i>新闻通知</i></a>
                        </span> 
                    </li>
                </ul>
            </div>
        </div>
        <div class="administrator_content_con">
        	<div class="administrator_content_header clearfloat">
            	<div class="administrator_content_header_logo"><img src="${pageContext.request.contextPath}/images/administrator/admin_logo.png" alt=""/></div>
             <div class="admin_logout_btn"><a href="${pageContext.request.contextPath}/cy/exit.action">退出</a></div>
            </div>
            <div class="administrator_content_detail">
            	<div class="administrator_bread_title clearfloat">
                	<span class="administrator_add_icontxt" >教学调查</span>
                </div> 
                <div class="administrator_teaching_inquire_con clearfloat">
                <form id="importsurvey"action="${pageContext.request.contextPath}/survey/surveyimport.action" method="post"enctype="multipart/form-data">
                <div class="teaching_inquire_select_con">
                 <select id="course" class="teaching_inquire_option"onchange="selectcourseId(this);">
                   <option value="0">全平台</option>
                    <c:forEach items="${courses}" var="item">
				   <option value="${item.courseId}">${item.courseName}</option>
					</c:forEach>
                        </select> 
                        <span class="teaching_inquire_detail_btn">课程</span>
                  </div>
                  <div class="administrator_teaching_inquire_upload_zone">
                       
                        <input type='text' name='textfield' id='textfield' class='administrator_teaching_inquire_upload_img_zone_file' /> 
                         <div class="administrator_teaching_inquire_upload_looksearch_btn">浏览
                            <input type="file" class="administrator_teaching_inquire_upload_img_zone_filebtn" name="file"  onchange="document.getElementById('textfield').value=this.value"/>
                        </div>
                        <input type="submit" id="upload"class="administrator_teaching_inquire_upload_btn"value="上传">
                        <input type="hidden" id="courseId"name="courseId">
                        <input type="hidden" id="courseName"name="courseName">
                    </div>
                     <span><c:if test="${type==1}"> <span class="administrator_teaching_inquire_tips">${courseName}的调查问卷已经导入，请不要重复导入！</span></c:if></span>
                    </form>
                    <table class="administrator_teaching_inquire_table" cellpadding="0" cellspacing="0">
                       <c:if test="${surveylist.size()==0}">
                       <tr><td>暂无调查问卷</td></tr>
                        </c:if>
                         <c:if test="${surveylist.size()>0}">
                        <thead>
                        	<tr>
                                <td>调查问卷名称</td>
                                <td>发布时间</td>
                                <td>属性</td>
                                <td>操作</td>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${surveylist}" var="item"> 
                        	<tr>                         	
                                <td>${item.paperName}</td>
                                <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" /></td>
                               <td><c:if test="${empty(item.chapterId)}">针对平台</c:if>
                               <c:if test="${not empty(item.chapterId)}">针对课程</c:if>
                               </td>
                                <td>
                                <span class="administrator_addstudent_table_look"onClick="admTeachingInquirePopCenter('${item.paperId}','${item.paperName}');">查看</span>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                      </c:if>
                    </table>
                     <form action="${pageContext.request.contextPath}/survey/coursesurveylist.action" id="searchform" name="searchform" method="post">
                     </form>
                    <c:if test="${not empty surveylist}"> 			
         			<tags:pager pagerRange="10" pageSize="${page.pageSize}" totalPage="${page.pages}" curIndex="${page.pageNum}" formId="searchform"></tags:pager>              
           		 	</c:if> 
            		<c:if test="${empty surveylist}">
            		</c:if> 
                </div>
            </div>
        </div>
    </div>
	
</div>
<script type="text/javascript">
function admTeachingInquirePopCenter(paperId,paperName){
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
	//window.location.href="${pageContext.request.contextPath}/survey/survey-preview.action?paperId="+paperId;
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
		    		surveylist+=getchoicehtml(data[i]);
		    	}		    	
		    	$(".pop_teaching_inquire_subject_con").html(surveylist);
		    	},		
			    error :function(){   
			        alert("网络连接出错！");   
			    }   
			}); 
}
//调查问卷遍历
   function getchoicehtml(data){
	   //var surveylist="";
	   var surveylist = "<div class='pop_teaching_inquire_subject_list'>";
	   if(data.questionTypeId==1){
		 var obj = eval(jQuery.parseJSON(data.content).choiceList);
		 //var obj2=eval(jQuery.parseJSON(data.content).title);
			surveylist=surveylist+"<div class='pop_teaching_inquire_sub_title'>"+data.questionNo+"、"+jQuery.parseJSON(data.content).title+"（ 单选  ）"+"</div>";
			debugger
		 $.each(obj,function(index2,value) {  
		 surveylist=surveylist+"<div class='pop_teaching_inquire_sub_chose clearfloat'>"+"<span class='pop_teaching_inquire_sub_chosebox' name='"+value.option+"' onclick='chooseOption(this,"+value.objType+")'>"+"</span>"
				 +" <div class='pop_teaching_inquire_sub_chose_tit'>"+value.option+"．"+value.answer+"</div>"+"</div></div>";
	    });
		} 
	   if(data.questionTypeId==2){
			 var obj = eval(jQuery.parseJSON(data.content).choiceList);
				surveylist=surveylist+"<div class='pop_teaching_inquire_sub_title'>"+data.questionNo+"、"+jQuery.parseJSON(data.content).title+"（ 单选  ）"+"</div>";
			 $.each(obj,function(index2,value) {  
			 surveylist=surveylist+"<div class='pop_teaching_inquire_sub_chose clearfloat'>"+"<span class='pop_teaching_inquire_sub_chosebox' name='"+value.option+"' onclick='chooseOption(this,"+value.objType+")'>"+"</span>"
					 +" <div class='pop_teaching_inquire_sub_chose_tit'>"+value.option+"．"+value.answer+"</div>"+"</div></div>";
		    });
			} 
	   if(data.questionTypeId==5){
		   surveylist=surveylist+"<div class='pop_teaching_inquire_sub_title'>"+data.questionNo+"、"+jQuery.parseJSON(data.content).title+"（ 简答  ）"+"</div><textarea class='pop_teaching_inquire_ask_textarea'></textarea></div>"+"</div></div>"; 
	   }  
	   return surveylist
}
function selectcourseId(obj){
	var courseId=$(obj).val();
	var courseName=$("#course option:selected").text();  //获取选中的项
	$("#courseId").val(courseId);
	$("#courseName").val(courseName);
}
function importsurvey()
{
	importsurvey.submit();
}
$("#upload").click(function(){
	var courseId=$("#course").val();
	//alert(courseId);
	$("#courseId").val(courseId);
});
</script>
</body>
</html>
