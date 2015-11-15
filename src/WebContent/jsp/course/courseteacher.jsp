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
<link href="${pageContext.request.contextPath}/css/layim.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/my_course.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pop_block_login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/my_course_circle.js"></script>
<style type="text/css">
.mycourse_cl_chart_con li:hover{cursor: pointer;}
</style>
</head>
<body>

<!----提问--->
<div class="pop_msg_course1">
	<div class="course_pop_tit clearfloat">
    	<div class="course_pop_tit_left">问题</div>
        <div class="course_pop_tit_close"></div>
    </div>
    <div class="course_pop_ask">
    <!-- 
	    <c:forEach items="${courseQuiz}" var="p" varStatus="st">
	    	<div class="course_pop_ask_list clearfloat">
	        	<div class="course_pop_ask_title">${st.index+1}、${p.quizContent}
	        	  <c:if test="${empty p.quizAnswer }">
	        	    <img class="not_answer_ico" src="${pageContext.request.contextPath}/images/my_course/not_answer_ico.png" alt=""/>
	        	    <div class="course_pop_ask_title clearfloat">
		        	<span class="course_pop_ask_btn">解答</span>
		        	</div>
		        	<textarea></textarea>
			        <div class="course_pop_ask_textarea_submit clearfloat"><a>提交</a></div>
			      </c:if>
	        	</div>
	        	<c:if test="${not empty p.quizAnswer }">
	        	<div class="course_pop_ask_txt">	        	    
        	            <b>解答：</b>${p.quizAnswer }           	        
	        	</div>
				<div class="course_pop_ask_time">解答时间：<fmt:formatDate value="${p.quizTime}" pattern="yyyy-MM-dd  HH:mm" /></div>
				</c:if>  
	        </div>
	    </c:forEach>
	 -->
	 <c:forEach items="${courseQuiz}" var="p" varStatus="st">
		 <c:if test="${empty p.quizAnswer }">
	       <div class="course_pop_ask_list clearfloat">
        	<img class="not_answer_ico" src="${pageContext.request.contextPath}/images/my_course/not_answer_ico.png" alt=""/>
        	<div class="course_pop_ask_title clearfloat">${st.index+1}、${p.quizContent}<span class="course_pop_ask_btn" onClick="show('${p.id }')">解答</span></div>
        	<div class="course_pop_ask_textarea" id="btn${p.id }" style="display: none">
            	<textarea id="${p.id}"></textarea>
                <div class="course_pop_ask_textarea_submit clearfloat"><a class="submitAnswer" name=${p.id }>提交</a></div>
            </div>
           </div>
		 </c:if>
		 <c:if test="${not empty p.quizAnswer }">
	        <div class="course_pop_ask_list clearfloat">
	        	<div class="course_pop_ask_title">${st.index+1}、${p.quizContent}</div>
	        	<div class="course_pop_ask_txt"><b>解答：</b>${p.quizAnswer }</div>
				<div class="course_pop_ask_time">解答时间：<fmt:formatDate value="${p.answerTime}" pattern="yyyy-MM-dd  HH:mm" /></div>
	        </div>
		 </c:if>
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
                        	<span class="teachplan_con_ico_01" title='视屏'>1</span>
                            <span class="teachplan_con_ico_02">1</span>
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
                            <span class="teachplan_con_ico_02">1</span>
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
                    	<a onclick="learn()">预览</a>
                    </div>
                </div>
            </div>
        </div>
    </div>  
    <!---banner 轮询 结束---->     
</div>

<!---中间内容区域  开始---->
<div class="container_index">	
	<div class="my_course_con clearfloat">
     	<div class="my_course_con_left">
     		<div class="mycourse_cl_tit">作业与考试<a onclick="showAll(true)">全部显示&nbsp;&gt;</a></div>
            <table class="mycourse_cl_teacher_table" cellpadding="0" cellspacing="0" border="0">
            </table>
            <!-- 因需求变更，现在所有提问方式老师解答都以私信方式去解答，因此现在这一块老师解答的方式去掉
            <div class="mycourse_cl_tit mt_32px">我的回答</div>
            <div class="mycourse_cl_detail_txt">
              <c:forEach items="${discourseQuiz}" var="t" varStatus="st">
				<div class="mycourse_cl_detail_txt_bigtit mt_25px">${st.index+1}、${t.quizContent}</div>
				<p class="mt_12px">
					<c:if test="${not empty t.quizAnswer }">
					  <b>解答：</b>${t.quizAnswer } 
					</c:if>
				</p>
              </c:forEach>
                <div class="mycourse_cl_lookwhole clearfloat"><a  class="bg_color_4dd8fb" onClick="popCenter2();">查看全部</a></div>
            </div>
             -->
            <!--  
            <div class="mycourse_cl_tit mt_32px">在线考试</div>
            <table class="mycourse_cl_table" cellpadding="0" cellspacing="0" border="0">
            	<tr>
                	<td class="text_indent_25px">古代英语文学赏析在线考试</td>
                    <td>2015-06-25 15:30</td>
                    <td style="text-align:right;"><a class="mycourse_learn_btn bg_color_ff7373" href="exam.html">开始閱卷</a></td>
                </tr>
            </table>
            -->
            <div class="mycourse_cl_tit mt_32px">分数占比<a  onclick="goReport()">学情分析&nbsp;&gt;</a></div>
            <table class="mycourse_cl_exam_table" cellpadding="0" cellspacing="0" border="0">
            	<tr>
                	<td class="text_indent_25px">考勤成绩：20%</td>
                    <td>作业成绩：30%</td>
                    <td>考试成绩：50%</td>
                    <td style="text-align:right;">课程总成绩：100%</td>
                </tr>
            </table>
            <div class="mycourse_cl_chart_con">
            	<ul class="mycourse_cl_chart clearfloat">
                    <li onclick="goReport()">
                        <div class="mycourse_cl_chart_detail">${analysis.learnRate == null ? 0 : analysis.learnRate}<span>%</span></div>
                        <p>平均进度</p>
                    </li>
                    <li onclick="goReport()">
                        <div class="mycourse_cl_chart_detail">${analysis.taskCount == null ? 0 : analysis.taskCount }<span>次</span></div>
                        <p>课程作业</p>
                    </li>
                    <li onclick="goReport()">
                        <div class="mycourse_cl_chart_detail">${analysis.commentCount == null ? 0 : analysis.commentCount }<span>次</span></div>
                        <p>课程评论</p>
                    </li>
                    <li class="end" onclick="goReport()">
                        <div class="mycourse_cl_chart_detail">${analysis.quizCount == null ? 0 : analysis.quizCount }<span>次</span></div>
                        <p>课程提问</p>
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
                        <p class="mt_10px">主讲老师：<span>不拉不</span></p>
                    </div>
                </div>
            </div>
            <div class="mycourse_cr_love_detail_con clearfloat">
                <div class="mycourse_cr_love_detail">
                    <img class="mycourse_cr_love_img" src="${pageContext.request.contextPath}/images/my_course/mycourse_love_img_03.jpg" alt=""/>
                    <div class="mycourse_cr_love_imgtxt">
                    	<p class="mt_16px">文学与艺术</p>
                        <p class="mt_10px">华中科技大学</p>
                        <p class="mt_10px">主讲老师：<span>不拉不</span></p>
                    </div>
                </div>
            </div>
            <div class="mycourse_cr_love_detail_con clearfloat">
                <div class="mycourse_cr_love_detail">
                    <img class="mycourse_cr_love_img" src="${pageContext.request.contextPath}/images/my_course/mycourse_love_img_04.jpg" alt=""/>
                    <div class="mycourse_cr_love_imgtxt">
                    	<p class="mt_16px">文学与艺术</p>
                        <p class="mt_10px">华中科技大学</p>
                        <p class="mt_10px">主讲老师：<span>不拉不</span></p>
                    </div>
                </div>
            </div>
            <div class="mycourse_cr_love_detail_con clearfloat">
                <div class="mycourse_cr_love_detail">
                    <img class="mycourse_cr_love_img" src="${pageContext.request.contextPath}/images/my_course/mycourse_love_img_05.jpg" alt=""/>
                    <div class="mycourse_cr_love_imgtxt">
                    	<p class="mt_16px">文学与艺术</p>
                        <p class="mt_10px">华中科技大学</p>
                        <p class="mt_10px">主讲老师：<span>不拉不</span></p>
                    </div>
                </div>
            </div>
        </div> 
    </div>
</div>
<!---底部区域---->
<jsp:include page="../footer.jsp"></jsp:include>
<input type="hidden" id="classId" value="${classId}">
<input type="hidden" id="courseId" value="${course.courseId}">
</body>
<script type="text/javascript">
var classId=document.getElementById("classId").value;
var courseId=document.getElementById("courseId").value;

//提问回答问题后需要动态展示回答的问题列表
$(".submitAnswer").click(function(){
	var id=$(this).attr("name")
	var answer=$("#"+id).val();	
	var quizid=$(this).attr("name");
	$.ajax({
	     type: 'POST',
	     url: encodeURI(encodeURI('${pageContext.request.contextPath}/quiz/updateAnwser.action?answer='+answer+'&quizid='+quizid+'&classId='+classId+'&courseId='+courseId)),
	     success:function (data) {
	    	$(".course_pop_ask").html("");
	    	var html="";
	    	for(var i =0;i<data.length;i++)
	    	{
	    		 var obj =data[i];
	    		 if(obj.quizAnswer=="")
	    		 {
		    		 html=html+'<div class="course_pop_ask_list clearfloat">';
		    		 html=html+'<img class="not_answer_ico" src="${pageContext.request.contextPath}/images/my_course/not_answer_ico.png" alt=""/>';
		    		 html=html+'<div class="course_pop_ask_title clearfloat">';
		    		 html=html+(i+1)
		    		 html=html+'、'+obj.quizContent
		    		 html=html+'<span class="course_pop_ask_btn" onClick="show('+"'"+obj.id+"'"+')">解答</span></div>'
		    		 html=html+'<div class="course_pop_ask_textarea" id="btn'+obj.id+'" style="display: none">';
		    		 html=html+'<textarea id="'+obj.id+'"></textarea>'
		    		 html=html+'<div class="course_pop_ask_textarea_submit clearfloat"><a class="submitAnswer" onClick="quizAnser('+"'"+obj.id+"'"+')" name='+obj.id+'>提交</a></div>';    
		    		 html=html+'</div></div>'
	    		 }
	    	
	    		 else
	    		 {
		    		 html=html+'<div class="course_pop_ask_list clearfloat">';
		    		 html=html+'<div class="course_pop_ask_title">';
		    		 html=html+(i+1)
		    		 html=html+'、'+obj.quizContent
		    		 html=html+'</div>';
		    		 html=html+'<div class="course_pop_ask_txt"><b>解答：</b>'+obj.quizAnswer+'</div>';
		    		 html=html+'<div class="course_pop_ask_time">解答时间：'+obj.answerTimeString+'</div>';
		    		 html=html+'</div>';  
	    		 }
	    	}
	    		 $(".course_pop_ask").html(html);
	     },
	     dataType: "json"
	});
});

//点击提交后拼接
function quizAnser(quizid)
{
	var answer=document.getElementById(""+quizid).value
	$.ajax({
	     type: 'POST',
	     url: encodeURI(encodeURI('${pageContext.request.contextPath}/quiz/updateAnwser.action?answer='+answer+'&quizid='+quizid+'&classId='+classId+'&courseId='+courseId)),
	     success:function (data) {
	    	$(".course_pop_ask").html("");
	    	var html="";
	    	for(var i =0;i<data.length;i++)
	    	{
	    		 var obj =data[i];
	    		 if(obj.quizAnswer=="")
	    		 {
		    		 html=html+'<div class="course_pop_ask_list clearfloat">';
		    		 html=html+'<img class="not_answer_ico" src="${pageContext.request.contextPath}/images/my_course/not_answer_ico.png" alt=""/>';
		    		 html=html+'<div class="course_pop_ask_title clearfloat">';
		    		 html=html+(i+1)
		    		 html=html+'、'+obj.quizContent
		    		 html=html+'<span class="course_pop_ask_btn" onClick="show('+"'"+obj.id+"'"+')">解答</span></div>'
		    		 html=html+'<div class="course_pop_ask_textarea" id="btn'+obj.id+'" style="display: none">';
		    		 html=html+'<textarea id="'+obj.id+'"></textarea>'
		    		 html=html+'<div class="course_pop_ask_textarea_submit clearfloat"><a class="submitAnswer" onClick="quizAnser('+"'"+obj.id+"'"+')" name='+obj.id+'>提交</a></div>';    
		    		 html=html+'</div></div>'
	    		 }
	    	
	    		 else
	    		 {
		    		 html=html+'<div class="course_pop_ask_list clearfloat">';
		    		 html=html+'<div class="course_pop_ask_title">';
		    		 html=html+(i+1)
		    		 html=html+'、'+obj.quizContent
		    		 html=html+'</div>';
		    		 html=html+'<div class="course_pop_ask_txt"><b>解答：</b>'+obj.quizAnswer+'</div>';
		    		 html=html+'<div class="course_pop_ask_time">解答时间：'+obj.answerTimeString+'</div>';
		    		 html=html+'</div>';  
	    		 }
	    	}
	    		 $(".course_pop_ask").html(html);
	     },
	     dataType: "json"
	});
}



function show(id){
	$('#btn'+id).css('display','block');
}

$(document).ready(function(){ 
	getPaperList();
});

function learn(chapterId){
	window.location.href="${pageContext.request.contextPath}/course/video.action?courseId="+$("#courseId").val()+"&classId="+$("#classId").val();
}

// 进入批阅列表
function markingList(paperId){
	window.location.href="${pageContext.request.contextPath}/test/markingList.action?paperId="+paperId+"&classId="+$("#classId").val();
}

// 全部显示
function showAll(flag){
	$(".mycourse_cl_teacher_table").find("tr").each(function(index){
		if(!flag && index > 1){
			$(this).hide();
		}else if(flag && index > 1){
			$(this).fadeIn(800);
		}
	});
}
//老师查看单个课程的试卷、作业列表的详细信息，通过Ajax方式获取
function getPaperList(){
	$.ajax( {   
	    type : "GET",   
	    url : "${pageContext.request.contextPath}/test/listForTea.action", 
	    data : {
	      'courseId' :$("#courseId").val(),
	      'classId' :$("#classId").val(),
	     },  
	    dataType: "json",   
	    success : function(data) {   
	        if(data.length != 0){   
		    	for(var i=0 ; i<data.length ; i++){
		    		var html = "";
		    		html = html + "<tr><td><div class='teacher_homework_circle_con'>"+
                        	"<div class='teacher_homework_circle'><div id='pie_bg"+i+"' style='width:56px; height:56px;'>"+
                            "<div class='pie' id='pie"+i+"'></div></div></div>"+
                        	"<div class='teacher_homework_circle_detail color_4dd8fb'>已阅/已交&nbsp;&nbsp;"+data[i].markTotal+"/"+data[i].testTotal+"</div></div></td>"+
                			"<td><div class='teacher_homework_detail'>"+
                    		"<div class='teacher_homework_detail_tit'>"+data[i].paperName+"</div>"+
                        	"<div class='teacher_homework_detail_list clearfloat'>"+
                        	"<div class='teacher_homework_detail_list_left'>开始日期："+new Date(parseInt(data[i].startTime)).toLocaleDateString()+"</div>"+
                            "<div class='teacher_homework_detail_list_right'>截止日期："+new Date(parseInt(data[i].endTime)).toLocaleDateString()+"</div></div>"+
                        	"<div class='teacher_homework_detail_list clearfloat'>"+
                        	"<div class='teacher_homework_detail_list_left'>迟交/未交&nbsp;&nbsp;"+data[i].testDelay+"/"+(data[i].total-data[i].testTotal)+"</div>";
                    //        "<div class='teacher_homework_detail_list_right'>作业状态：<b>已过截止时间</b></div>";
		    		// 已经批阅完成
		    		if(data[i].testTotal == data[i].markTotal){
		    			html = html + "</div></div></td><td><a class='mycourse_learn_btn bg_color_4dd8fb' onclick='markingList(\""+data[i].paperId+"\")'>查看详情</a></td></tr>";
		    		}else{
		    			html = html + "</div></div></td><td><a class='mycourse_learn_btn bg_color_4dd8fb' onclick='markingList(\""+data[i].paperId+"\")'>开始批阅</a></td></tr>";
		    		}
		    		$(".mycourse_cl_teacher_table").append(html);
		    		initCircle("pie"+i,"pie_bg"+i,data[i].testTotal-data[i].markTotal,data[i].markTotal);
		    	}
		    	showAll(false);
	        }else{
	        	alert("查询出错！");   
	        }   
	    },   
	    error :function(){   
	        alert("网络连接出错！");   
	    }   
	});   
}

function goReport(){
	var url = "${pageContext.request.contextPath}/teacherAnalysis/goReport.action";
	var args = {"courseId":$("#courseId").val(),"classId":$("#classId").val()};
	$.get(url,args,function(data){
		if(data != null){
			window.open(${pageContext.request.contextPath}/+data);
		}
	});
	//window.location.href="${pageContext.request.contextPath}/teacherAnalysis/goReport.action?courseId="+$("#courseId").val()+"&classId="+$("#classId").val();
}
</script>
</html>
