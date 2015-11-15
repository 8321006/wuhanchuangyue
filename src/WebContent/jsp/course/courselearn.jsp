<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>我的课程</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/my_course_public.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/my_course_learned.css"/>
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/my_course.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pop_block_login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<style type="text/css">
.mycourse_cl_chart_con li:hover{ cursor: pointer;}
</style>
</head>
<body>

<!----随堂笔记弹出框--->
<div class="pop_msg_course">
	<div class="course_pop_tit clearfloat">
    	<div class="course_pop_tit_left">笔记</div>
        <div class="course_pop_tit_close"></div>
    </div>
    <div class="course_pop_note">
        <c:forEach items="${notes}" var="p" varStatus="status">
        <div class="course_pop_note_list clearfloat">
        	<div class="course_pop_note_txt">${status.index+1}、${p.noteContent}</div>
			<div class="course_pop_note_time">笔记时间：<fmt:formatDate value="${p.noteTime}" pattern="yyyy-MM-dd  HH:mm" /></div>
        </div>
        </c:forEach>
    </div>
</div>
<div class="pop_msg_bg_course"></div>
<!----提问--->
<div class="pop_msg_course1">
	<div class="course_pop_tit clearfloat">
    	<div class="course_pop_tit_left">问题</div>
        <div class="course_pop_tit_close"></div>
    </div>
    <div class="course_pop_ask">
    <c:forEach items="${courseQuiz}" var="p" varStatus="st">
    	<div class="course_pop_ask_list clearfloat">
        	<div class="course_pop_ask_title">${st.index+1}、${p.quizContent}</div>
        	<c:if test="${not empty p.quizAnswer }">
        	<div class="course_pop_ask_txt">   	
        	   <b>解答：</b>${p.quizAnswer }            	     	
        	</div>
			<div class="course_pop_ask_time">解答时间：<fmt:formatDate value="${p.answerTime}" pattern="yyyy-MM-dd  HH:mm" /></div>
			</c:if> 
        </div>
    </c:forEach>
    </div>
</div>
<div class="pop_msg_bg_course1"></div>

<!----教学计划弹出框--->
<div class="pop_msg_teachplan">
	<img class="pop_msg_teachplan_close" src="${pageContext.request.contextPath}/images/exam/exam_pop_close.png" alt=""/>
    <div class="pop_msg_teachplan_titdetail">
    	<div class="teachplan_titdetail_first">《${course.courseName}》教学计划</div>
        <div class="teachplan_titdetail_second">课程开始时间 <fmt:formatDate value="${courseclass.courseStartTime}" pattern="yyyy-MM-dd" /></div>
    </div>
    <ul class="pop_msg_teachplan_tab clearfloat">
    	<li><b class="teachplan_arrow_tabdown"></b>教学计划</li>
        <li><b class="teachplan_arrow_tabdown"></b><span class="teachplan_tab_ico"></span>时间轴</li>
        <li class="end"><b class="teachplan_arrow_tabdown"></b>学习计划</li>
    </ul>
    <div class="pop_msg_teachplan_con">
    	<div class="teachplan_time_top clearfloat">
        	<div class="teachplan_beginbtn bg_color_b20303">开始学习</div>
            <span class="teachplan_begintime"><fmt:formatDate value="${courseclass.courseStartTime}" pattern="yyyy-MM-dd" /></span>
            <span class="teachplan_arrow_down"></span>
        </div>
        <c:forEach items="${coursechapter}" var="p" varStatus="status">
        <c:if test="${status.last!=true}">
        <div class="teachplan_time_middle">
        	<div class="teachplan_time_middle_right height_218px">
            	<div class="teachplan_time_mr_con clearfloat loopheight1">
                	<div class="teachplan_time_mr_con_left"><span>${p.plan}天</span></div>
                    <div class="teachplan_time_mr_con_right">
                    	<img class="teachplan_arrow_left_blue" src="${pageContext.request.contextPath}/images/my_course/teachplan_arrow_left_blue.png" alt=""/>
                        <div class="teachplan_time_mr_con_righttit">${p.chapterIndex}${p.chapterName}</div>
                        <div class="teachplan_time_mr_con_righttxt">
                        	<span>${p.outline}</span>
                        </div>
                        <div class="teachplan_time_mr_con_righico clearfloat">
                        	<span class="teachplan_con_ico_01">1</span>
                            <span class="teachplan_con_ico_02">0</span>
                            <span class="teachplan_con_ico_03">1</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="teachplan_time_middle_left">
            	<div class="teachplan_beginbtn bg_color_33c5db">作业/测试</div>
            	<span class="teachplan_begintime"><fmt:formatDate value="${p.updateTime}" pattern="yyyy-MM-dd" /></span>
            	<span class="teachplan_arrow_down"></span>
            </div>
        </div>
        </c:if>
         <c:if test="${status.last==true}">
         	<div class="teachplan_time_middle clearfloat">
        	<div class="teachplan_time_middle_right height_218px">
            	<div class="teachplan_time_mr_con clearfloat loopheight3">
                	<div class="teachplan_time_mr_con_left"><span>${p.plan}天</span></div>
                    <div class="teachplan_time_mr_con_right">
                    	<img class="teachplan_arrow_left_blue" src="${pageContext.request.contextPath}/images/my_course/teachplan_arrow_left_blue.png" alt=""/>
                        <div class="teachplan_time_mr_con_righttit">${p.chapterName}</div>
                        <div class="teachplan_time_mr_con_righttxt">
                        	<span>考试${p.outline}</span>
                        </div>
                        <div class="teachplan_time_mr_con_righico clearfloat">
                        	<span class="teachplan_con_ico_01">1</span>
                            <span class="teachplan_con_ico_02">0</span>
                            <span class="teachplan_con_ico_03">1</span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="teachplan_time_middle_left">
            	<div class="teachplan_beginbtn bg_color_b20303">教程结业考试</div>
            	<span class="teachplan_begintime"><fmt:formatDate value="${p.updateTime}" pattern="yyyy-MM-dd" /></span>
            	<span class="teachplan_arrow_down"></span>
            </div>
        </div>
         </c:if>
        </c:forEach>
        <div class="teachplan_time_bottom">
        	<div class="teachplan_time_middle_left">
            	<div class="teachplan_beginbtn bg_color_b20303">课程结束</div>
            	<span class="teachplan_begintime"> <fmt:formatDate value="${courseclass.courseEndTime}" pattern="yyyy-MM-dd" /></span>
            	<span class="teachplan_arrow_heng"></span>
            </div>
        </div>
    </div>
</div>
<div class="pop_msg_bg_teachplan"></div>

<!----头部区域-->	
<div class="inner_header">
	<jsp:include page="../head.jsp"></jsp:include>
	
     <!---banner 轮询 开始---->
    <div class="flexslider_02">
        <ul class="slides">
            <li style="background:url(${pageContext.request.contextPath}/images/my_course/mycourse_banner_01.jpg) no-repeat;"></li>
        </ul>
        <div class="my_course_detail"> 
            <div class="mycourse_tit_link">
            	<span>课程</span>&nbsp;&gt;&nbsp;<span>文学与艺术</span>&nbsp;&gt;&nbsp;<span>${course.courseName}</span>
            </div>
            <div class="mycourse_topmiddle_imgtxt clearfloat">
            	<img class="mycourse_center_banner_01" src="${pageContext.request.contextPath}/${course.courseCoverUrl}" align=""/>
                <div class="mycourse_center_img_info">
                	<h7>${course.courseName}</h7>
                	<input value="${course.courseId}" id="courseId" style="display: none;"/>
                    <div class="mycourse_center_txt_imgtxt clearfloat">
                    	<div class="mycourse_center_txt_imgtxt_left">
                        	<div class="mycourse_center_txt_imgtxt_img"><img class="my_course_ico" src="${pageContext.request.contextPath}/images/my_course/my_course_ico_01.png"/></div>
                            <span class="mycourse_center_txt_imgtxt_span1">${course.num}人</span>
                        </div>
                        <div class="mycourse_center_txt_imgtxt_right">
                        	<div class="mycourse_center_txt_imgtxt_img"><img class="my_course_ico" src="${pageContext.request.contextPath}/images/my_course/my_course_ico_02.png"/></div>
                            <span class="mycourse_center_txt_imgtxt_span2">24小时</span>
                        </div>
                    </div>
                    <div class="mycourse_center_txt_imgtxt clearfloat">
                    	<div class="mycourse_center_txt_imgtxt_left">
                        	<div class="mycourse_center_txt_imgtxt_img"><img class="my_course_ico" src="${pageContext.request.contextPath}/images/my_course/my_course_ico_03.png"/></div>
                            <span class="mycourse_center_txt_imgtxt_span1">${chapterlength}节</span>
                        </div>
                        <div class="mycourse_center_txt_imgtxt_right">
                        	<div class="mycourse_center_txt_imgtxt_img"><img class="my_course_ico" src="${pageContext.request.contextPath}/images/my_course/my_course_ico_04.png"/></div>
                            <span class="mycourse_center_txt_imgtxt_span2">245人</span>
                        </div>
                    </div>
                    <div class="mycourse_center_info_detailtxt">${course.courseDesc}</div>
                    <div class="mycourse_center_btn clearfloat">
                    	<span onClick="teachPlanPopCenter();">教学计划</span>
                    	<a onclick="learn()">开始学习</a>
                    </div>
                </div>
            </div>
        </div>
    </div>  
    <!---banner 轮询 结束---->     
</div>

<!---中间内容区域  开始---->
<div class="container">	
	<div class="my_course_con clearfloat">
     	<div class="my_course_con_left">
        	<div class="mycourse_cl_tit">随堂作业</div>
            <table class="mycourse_cl_table" id="homeworkList" cellpadding="0" cellspacing="0" border="0">
            </table>
            <div class="mycourse_cl_tit mt_32px">在线考试</div>
            <table class="mycourse_cl_table" id="paperList" cellpadding="0" cellspacing="0" border="0">
            </table>
            
            <div class="mycourse_cl_tit mt_32px">随堂笔记</div>
            <div class="mycourse_cl_detail_txt">
        		<c:forEach items="${disnotes}" var="p" varStatus="status">
				<p class="mt_20px">${status.index+1}、${p.noteContent}</p>
				</c:forEach>
                <div class="mycourse_cl_lookwhole clearfloat"><a  class="bg_color_4dd8fb" onClick="popCenter1();">查看全部</a></div>
            </div>
            <!-- 因需求变更，提问这一块老师的回复现在完全由老师以私信方式替代，因此这块没用了
            <div class="mycourse_cl_tit mt_32px">我的提问</div>
            <div class="mycourse_cl_detail_txt">
              <c:forEach items="${discourseQuiz}" var="t" varStatus="st">
				<div class="mycourse_cl_detail_txt_bigtit mt_25px">${st.index+1}、${t.quizContent}</div>
				<c:if test="${not empty p.quizAnswer }">
				<p class="mt_12px">
				<b>解答：</b>${t.quizAnswer }</p>
				</c:if>
              </c:forEach>
                <div class="mycourse_cl_lookwhole clearfloat"><a  class="bg_color_4dd8fb" onClick="popCenter2();">查看全部</a></div>
            </div>
             -->
            <div class="mycourse_cl_tit mt_32px">课程分析
            <a onclick="goReport()"<c:if test="${countsurvey>0}">title="您有该课程的调查问卷未填写"</c:if>>学情分析&nbsp;&gt;</a></div>
            <table class="mycourse_cl_exam_table" cellpadding="0" cellspacing="0" border="0">
            	<tr>
                	<td class="text_indent_25px">考勤成绩：<fmt:formatNumber type="number" value="${courseScoreResult.videoScore } " maxFractionDigits="0"/>分</td>
                    <td>作业成绩：<fmt:formatNumber type="number" value="${courseScoreResult.homeWorkScore } " maxFractionDigits="0"/>分</td>
                    <td>考试成绩：<fmt:formatNumber type="number" value="${courseScoreResult.paperScore } " maxFractionDigits="0"/>分</td>
                    <td style="text-align:right;"><b><font size="3"color="#ef4f4f">课程总成绩：<fmt:formatNumber type="number" value="${courseScoreResult.totalScore } " maxFractionDigits="0"/>分</font></b></td>
                </tr>
            </table>
            <div class="mycourse_cl_chart_con">
            	<ul class="mycourse_cl_chart clearfloat">
                    <li onclick="goReport()">
                        <div class="mycourse_cl_chart_detail"<c:if test="${countsurvey>0}">title="您有该课程的调查问卷未填写"</c:if>>${learningrateRank }<span>名</span></div>
                        <p>进度 / 已完成${analysis.learnRate==null ? 0 : analysis.learnRate}%</p>
                    </li>
                    <li onclick="goReport()">
                        <div class="mycourse_cl_chart_detail"<c:if test="${countsurvey>0}">title="您有该课程的调查问卷未填写"</c:if>>${taskRank }<span>名</span></div>
                        <p>作业 / 已完成${analysis.taskCount==null ? 0 : analysis.taskCount}次</p>
                    </li>
                    <li onclick="goReport()">
                        <div class="mycourse_cl_chart_detail"<c:if test="${countsurvey>0}">title="您有该课程的调查问卷未填写"</c:if>>${commentRank }<span>名</span></div>
                        <p>评论 / 已完成${analysis.commentCount==null ? 0 : analysis.commentCount}次</p>
                    </li>
                    <li class="end" onclick="goReport()">
                        <div class="mycourse_cl_chart_detail"<c:if test="${countsurvey>0}">title="您有该课程的调查问卷未填写"</c:if>>${quizRank }<span>名</span></div>
                        <p>提问 / 已完成${analysis.quizCount==null ? 0 :  analysis.quizCount}次</p>
                    </li>
                </ul>
            </div>
        </div>   
        <div class="my_course_con_right">
        	<div class="mycourse_cr_tit">老师简介</div>
            <div class="mycourse_cr_teacher_detail clearfloat">
            	<div class="mycourse_cr_teacher_img"><img src="${pageContext.request.contextPath}/images/my_course/mycourse_teacher_ico_02.jpg" alt=""/></div>
                <div class="mycourse_cr_teacher_namedetail">
                	<p class="mt_32px">${course.courseAuthor}</p>
                    <p class="mt_12px">${course.courseAuthorDetail}</p>
                </div>
            </div>
            <div class="mycourse_cr_tit mt_14px">猜你喜欢</div>
            <div class="mycourse_cr_love_detail_con clearfloat">
                <div class="mycourse_cr_love_detail">
                    <img class="mycourse_cr_love_img" src="${pageContext.request.contextPath}/images/my_course/mycourse_love_img_01.jpg" alt=""/>
                    <div class="mycourse_cr_love_imgtxt">
                    	<p class="mt_16px">文学与艺术</p>
                        <p class="mt_10px">华中科技大学</p>
                        <p class="mt_10px">主讲老师：<span>不拉不</span></p>
                    </div>
                </div>
            </div>
            <div class="mycourse_cr_love_detail_con clearfloat">
                <div class="mycourse_cr_love_detail">
                    <img class="mycourse_cr_love_img" src="${pageContext.request.contextPath}/images/my_course/mycourse_love_img_02.jpg" alt=""/>
                    <div class="mycourse_cr_love_imgtxt">
                    	<p class="mt_16px">文学与艺术</p>
                        <p class="mt_10px">华中科技大学</p>
                        <p class="mt_10px">主讲老师：<span>张</span></p>
                    </div>
                </div>
            </div>
            <div class="mycourse_cr_love_detail_con clearfloat">
                <div class="mycourse_cr_love_detail">
                    <img class="mycourse_cr_love_img" src="${pageContext.request.contextPath}/images/my_course/mycourse_love_img_03.jpg" alt=""/>
                    <div class="mycourse_cr_love_imgtxt">
                    	<p class="mt_16px">文学与艺术</p>
                        <p class="mt_10px">华中科技大学</p>
                        <p class="mt_10px">主讲老师：<span>张</span></p>
                    </div>
                </div>
            </div>
            <div class="mycourse_cr_love_detail_con clearfloat">
                <div class="mycourse_cr_love_detail">
                    <img class="mycourse_cr_love_img" src="${pageContext.request.contextPath}/images/my_course/mycourse_love_img_04.jpg" alt=""/>
                    <div class="mycourse_cr_love_imgtxt">
                    	<p class="mt_16px">文学与艺术</p>
                        <p class="mt_10px">华中科技大学</p>
                        <p class="mt_10px">主讲老师：<span>张</span></p>
                    </div>
                </div>
            </div>
            <div class="mycourse_cr_love_detail_con clearfloat">
                <div class="mycourse_cr_love_detail">
                    <img class="mycourse_cr_love_img" src="${pageContext.request.contextPath}/images/my_course/mycourse_love_img_05.jpg" alt=""/>
                    <div class="mycourse_cr_love_imgtxt">
                    	<p class="mt_16px">文学与艺术</p>
                        <p class="mt_10px">华中科技大学</p>
                        <p class="mt_10px">主讲老师：<span>张</span></p>
                    </div>
                </div>
            </div>
        </div> 
    </div>
</div>
<!---底部区域---->
<input type="hidden" id="classId" value="${classId}">
</body>
<script type="text/javascript">

var url = "${pageContext.request.contextPath}";

$(document).ready(function(){ 
	getPaperList();
});

// 学生查看单个课程的试卷、作业列表，通过Ajax方式获取
function getPaperList(){
	$.ajax( {   
	    type : "GET",   
	    url : url+"/test/listForStu.action", 
	    data : {
	      "courseId" :$("#courseId").val(),
	      "classId" :$("#classId").val()
	     },  
	    dataType: "json",   
	    success : function(data) {   
	        if(data.length != 0){   
	        	var homework = "";
	        	var paper = "";
	        	var classId = $("#classId").val();
	        	var courseId = $("#courseId").val();
		    	for(var i=0 ; i<data.length ; i++){
		    		if(data[i].testType == 0 && data[i].testId == null){
		    			// 未写的作业
		    			homework = homework + "<tr><td class='text_indent_25px'>"+data[i].paperName+"</td><td></td>"+
	                    "<td><a  target='_blank' href='"+url+"/paper/preview.action?chapter="+data[i].paperName+"&classId="+classId+"&courseId="+courseId+"&paperId="+data[i].paperId+"' class='mycourse_learn_btn bg_color_4dd8fb'>开始答题</a></td></tr>";
		    		}else if(data[i].testType == 0 && data[i].testId != null){
		    			// 已经完成的作业分两种，一是看过答案的二是没看答案可以重复做题
		    			if(data[i].checkStatus == 0){
		    				homework = homework + "<tr><td class='text_indent_25px'>"+data[i].paperName+"</td><td></td>"+
		                    "<td><a  target='_blank' href='"+url+"/paper/preview.action?chapter="+data[i].paperName+"&classId="+classId+"&courseId="+courseId+"&paperId="+data[i].paperId+"' class='mycourse_learn_btn bg_color_4dd8fb mr_15px'>重新作答</a>"+
		                    "<a  target='_blank' href='"+url+"/test/preview.action?chapter="+data[i].paperName+"&testId="+data[i].testId+"' class='mycourse_learn_btn bg_color_4dd8fb'>查看详情</a></td></tr>";
		    			}else{
		    				homework = homework + "<tr><td class='text_indent_25px'>"+data[i].paperName+"</td><td></td>"+
		                    "<td><a  target='_blank' href='"+url+"/test/preview.action?chapter="+data[i].paperName+"&testId="+data[i].testId+"' class='mycourse_learn_btn bg_color_4dd8fb'>查看详情</a></td></tr>";
		    			}
		    		}else if(data[i].testType == 1 && data[i].testId == null && data[i].userId == 1){
		    			// 未参加的考试，可以参加ok
		    			paper = paper + "<tr><td class='text_indent_25px'>"+data[i].paperName+"</td>"+
		    			"<td>"+new Date(parseInt(data[i].startAnswerTime)).toLocaleDateString()+"至"+new Date(parseInt(data[i].endAnswerTime)).toLocaleDateString()+"</td>"+
	                    "<td><a  target='_blank' href='"+url+"/paper/preview.action?chapter="+data[i].paperName+"&classId="+classId+"&courseId="+courseId+"&paperId="+data[i].paperId+"' class='mycourse_learn_btn bg_color_ff7373'>开始考试</a></td></tr>";
		    		}else if(data[i].testType == 1 && data[i].testId == null && data[i].userId == 0){
		    			// 未参加的考试，时间未到，不能参加ok
		    			paper = paper + "<tr><td class='text_indent_25px'>"+data[i].paperName+"</td>"+
		    			"<td>"+new Date(parseInt(data[i].startAnswerTime)).toLocaleDateString()+"至"+new Date(parseInt(data[i].endAnswerTime)).toLocaleDateString()+"</td>"+
	                    "<td><a class='mycourse_learn_btn bg_color_ff7373' >暂未开始</a></td></tr>";
		    		}else if(data[i].testType == 1 && data[i].testId == null && data[i].userId == 2){
		    			// 未参加的考试，时间过了，申请补考
		    			paper = paper + "<tr><td class='text_indent_25px'>"+data[i].paperName+"</td>"+
		    			"<td>"+new Date(parseInt(data[i].startAnswerTime)).toLocaleDateString()+"至"+new Date(parseInt(data[i].endAnswerTime)).toLocaleDateString()+"</td>"+
	                    "<td><a onclick='apply(this,\""+data[i].paperId+"\")' class='mycourse_learn_btn bg_color_ff7373'>申请补考</a></td></tr>";
		    		}else if(data[i].testType == 1 && data[i].testId == null && data[i].userId == 3){
		    			// 未参加的考试，时间过了，申请补考通过
		    			paper = paper + "<tr><td class='text_indent_25px'>"+data[i].paperName+"</td>"+
		    			"<td>"+new Date(parseInt(data[i].startAnswerTime)).toLocaleDateString()+"至"+new Date(parseInt(data[i].endAnswerTime)).toLocaleDateString()+"</td>"+
	                    "<td><a  target='_blank' href='"+url+"/paper/preview.action?chapter="+data[i].paperName+"&classId="+classId+"&courseId="+courseId+"&paperId="+data[i].paperId+"' class='mycourse_learn_btn bg_color_ff7373'>补考</a></td></tr>";
		    		}else if(data[i].testType == 1 && data[i].testId == null && data[i].userId == 4){
		    			// 未参加的考试，时间过了，申请补考被拒绝
		    			paper = paper + "<tr><td class='text_indent_25px'>"+data[i].paperName+"</td>"+
		    			"<td>"+new Date(parseInt(data[i].startAnswerTime)).toLocaleDateString()+"至"+new Date(parseInt(data[i].endAnswerTime)).toLocaleDateString()+"</td>"+
	                    "<td><a  class='mycourse_learn_btn bg_color_ff7373'>补考被拒绝</a></td></tr>";
		    		}else if(data[i].testType == 1 && data[i].testId == null && data[i].userId == 5){
		    			// 未参加的考试，时间过了，申请补考
		    			paper = paper + "<tr><td class='text_indent_25px'>"+data[i].paperName+"</td>"+
		    			"<td>"+new Date(parseInt(data[i].startAnswerTime)).toLocaleDateString()+"至"+new Date(parseInt(data[i].endAnswerTime)).toLocaleDateString()+"</td>"+
	                    "<td><a  class='mycourse_learn_btn bg_color_ff7373'>等待审核</a></td></tr>";
		    		}else if(data[i].testType == 1 && data[i].testId != null && data[i].markStatus== 1){
		    			// 已经参加的考试已经批阅ok
		    			paper = paper + "<tr><td class='text_indent_25px'>"+data[i].paperName+"</td><td>得分: "+data[i].userScore+"分</td>"+
	                    "<td><a  target='_blank' href='"+url+"/test/preview.action?chapter="+data[i].paperName+"&testId="+data[i].testId+"' class='mycourse_learn_btn bg_color_ff7373'>查看详情</a></td></tr>";
		    		}else if(data[i].testType == 1 && data[i].testId != null && data[i].markStatus== 0){
		    			// 已经参加的考试暂未批阅ok
		    			paper = paper + "<tr><td class='text_indent_25px'>"+data[i].paperName+"</td><td>暂未批阅</td>"+
	                    "<td><a  target='_blank' href='"+url+"/test/preview.action?chapter="+data[i].paperName+"&testId="+data[i].testId+"' class='mycourse_learn_btn bg_color_ff7373'>查看详情</a></td></tr>";
		    		
		    		}
		    	}
		    	$("#homeworkList").append(homework);
		    	$("#paperList").append(paper);
		    	// 最后一场考试
		    	var endUrl = $(".mycourse_learn_btn").last().attr("href");
		    	if("开始考试" == $(".mycourse_learn_btn").last().html()){
		    		$(".mycourse_learn_btn").last().attr("href",endUrl+"&finish=1");
		    	}
	        }else{
	        	alert("查询出错！");   
	        }   
	    },   
	    error :function(){   
	        alert("网络连接出错！");   
	    }   
	});   
}

// 申请补考
function apply(obj,paperId){
	$.ajax( {   
	    type : "GET",   
	    url : url+"/transaction/apply.action", 
	    data : {
	      'paperId' : paperId,
	      'classId' : $("#classId").val(),
	      'courseId' : $("#courseId").val()
	     },  
	    dataType: "json",   
	    success : function(data) {   
	        if(data.success){   
	            alert("申请成功，请等待老师审核！");
	            $(obj).html("等待审核");
	        }   
	        else{   
	            alert("申请失败！");   
	        }   
	    },   
	    error :function(){   
	        alert("网络连接出错！");   
	    }   
	});   
}

function learn(){
	window.open("${pageContext.request.contextPath}/course/video.action?courseId="+$("#courseId").val()+"&classId="+$("#classId").val());
}

function goReport(){
	var url = "${pageContext.request.contextPath}/analysis/goReport.action";
	var args = {"courseId":$("#courseId").val(),"classId":$("#classId").val()};
	$.get(url,args,function(data){
		if(data != null){
			window.open(${pageContext.request.contextPath}/+data);
		}
	});
	//window.location.href="${pageContext.request.contextPath}/analysis/goReport.action?courseId="+$("#courseId").val()+"&classId="+$("#classId").val();
}
</script>
</html>
