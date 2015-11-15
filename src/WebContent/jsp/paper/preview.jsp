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
<title>在线考试</title>
<link href="${pageContext.request.contextPath}/css/basic.css" rel="stylesheet"/>
<link href="${pageContext.request.contextPath}/css/exam.css"rel="stylesheet"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/exam.js"></script>

<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
</head>

<body onload="timedCount()">
<!--分析报告弹出框--->
<div class="pop_msg_exam">
	<div class="exam_pop_tit clearfloat">
    	<div class="exam_pop_tit_left">提示</div>
        <div class="exam_pop_tit_close"></div>
    </div>
    <div class="exam_pop_score_txt">本次获得的成绩是<span id="resultScore">100</span>分</div>
    <div class="exam_pop_analyse">
    	<div class="exam_pop_analyse_tit clearfloat">
        	<img src="${pageContext.request.contextPath}/images/exam/analyse_ico.png" alt=""/>
            <span>分析报告</span>
        </div>
        <div class="exam_pop_analyse_list">
        	<table><tr>
        <td>
        <div class="exam_pop_analyse_list_detail clearfloat">
            	<b>考试名称：</b>
                <span>${paper.paperName}</span>
                </div>
                
            <div class="exam_pop_analyse_list_detail clearfloat">
            	<b>试卷总题数：</b>
                <span>${questionList.size()}</span>
                <span>题</span>
                </div>
               <div class="exam_pop_analyse_list_detail clearfloat">
            	<b>客观题总数：</b>
               <span id="ObjectivequestionsAll"></span>
               <span>题</span>
            </div>
        </td>
        <td>
        <div class="exam_pop_analyse_list_detail clearfloat">
            	<b>考试用时：</b>
                <span id="resultTime"></span>
                </div>
        
        <div class="exam_pop_analyse_list_detail clearfloat">
            	<b>正确题数：</b><span id="Correctquestions"></span>
                <span>题</span>
            </div>
            <div class="exam_pop_analyse_list_detail clearfloat">
            	<b>错误题数：</b><span id="errorquestions"></span>
            	 <span>题</span>
            </div>
        </td></tr> </table>
        </div>
        <div class="exam_pop_analyse_table_con">
        	<table class="exam_pop_analyse_table" cellpadding="0" cellspacing="0">
            	<thead>
                    <tr>
                        <td>题型</td>
                        <td>答题情况</td>
                        <td>正确率</td>
                    </tr>
                </thead>
                <tbody>
                     <tr>
                        <td>客观题</td>
                        <td id="AnswerSituation"></td>
                        <td id="Correctrate"></td>
                    </tr>
                    <tr id="Hiderow">
                        <td>主观题</td>
                        <td>暂未批阅</td>
                        <td>----</td>
                    </tr>
                </tbody>
               
            </table>
        </div>
        <div class="exam_pop_analyse_btn">
        	<!--<span onclick="queryDetail()">查看详情</span>-->
            <!--  <b onclick="queryDetail('${courseId}')">继续学习</b> -->
        </div>
    </div>
</div>
<div class="pop_msg_bg_exam"></div>
<!----头部区域-->	
<div class="inner_header">
<jsp:include page="../head.jsp"></jsp:include>
</div>
<!---中间内容区域  开始---->
<div class="container_index">		
	<div class="exam_con">
		<c:if test="${(sessionScope.user.userType== 0 && test.content == null) || (sessionScope.user.userType== 1)}"> 
    	<div class="time_cal_con">
        	<img class="time_cal_ico" src="${pageContext.request.contextPath}/images/exam/time_cal_ico.png" alt=""/>
            <p>已用时间</p>
            <div class="time_cal_detail">
            <span><span id="hour"></span>
            	</span>:<span id="minute"></span>:<span id="Seconds"></span>
            </div>
				<c:if test="${sessionScope.user.userType== 0}"> 
				<div class="exam_submit_btn" id="submitButton" >提交试卷</div>
				</c:if>
				<c:if test="${sessionScope.user.userType== 1}">
				<div class="exam_submit_btn" onclick="autoMarking();marking();submitResultByAjax('marking');">批阅完成</div> 
				</c:if>
        </div>
        </c:if>
    	<div class="exam_title">${paper.paperName}</div>
    	<!--  
        <div class="exam_info">
        	<table class="exam_info_table" cellpadding="0" cellspacing="0" border="0">
            	<tr>
                	<td>试卷名称：${paper.paperName}</td>
                    <td>对应章节：${chapter}</td>
                    <td>成绩类型：分数制</td>
                </tr>
                <tr>
                	<td>特殊说明：</td>
                    <td>题目总数：${questionList.size()}</td>
                    <td>总分数值：${paper.score}</td>
                </tr>
                <tr>
                	<td>及格分数：${paper.passScore}</td>
                	<td>测试得分：${test.userScore}</td>
                </tr>
            </table>
        </div>
        -->
        <!-- 试卷 -->
        <c:forEach items="${questionList}" var="item">
        <div class="exam_subject" id="question-${item.questionNo}">
        	<c:if test="${test.content != null}">
        	<div class="exam_score_con">
         		<p>得分</p>
                <div class="exam_score_detail"></div>	
            </div>
            </c:if>
			<input class="question-json" questionNo="${item.questionNo}" answer="${item.answer}" score="${item.points}" value='${item.content}' name="content" type="${item.questionTypeId}"style="display:none;"/>
			
			<c:if test="${item.questionTypeId == 4 || item.questionTypeId == 5}">
			<br>
			<script id="editor-${item.questionNo}" type="text/plain" style="width:1000px;height:290px;"></script>
			</c:if>
			
			
			<c:if test="${test.content != null}">
				 <div class="exam_sub_jiexi">
	            	<div class="exam_sub_jiexi_tit">
	                	<img src="${pageContext.request.contextPath}/images/exam/jiexi_ico.png" alt=""/>
	                    <span>解析</span>
	                </div>
	                <div class="exam_sub_jiexi_txt">${item.analysis}</div>
	            </div>
			</c:if>
		</div>
		</c:forEach>
		<input id="testId" value="${test.testId}" style="display: none;"/>
		<input id="userScore" style="display: none;"/>
		<input id="userAnswerCostTime" style="display: none;"/>
		<input id="answerList" value='${test.content}' style="display: none;"/>
		<input value='' id="answerContent" name="content" style="display:none;"/>		
		<input value='${paper.paperId}' id="paperId" type='${paper.subjective}' name="paperId" style="display:none;"/>		
		<input value='${classId}' id="classId"  style="display:none;"/>	
		<input value='${finish}' id="finish"  style="display:none;"/>	
		<input  id="testType" name="testType" style="display:none;" value="${paper.testType}"/>					
    </div>
</div>
<!---底部区域---->
<script type="text/javascript">

$(document).ready(function(){ 
	 $("#submitButton").one("click",function(){
		 autoMarking();
		 examPopCenter();
		 $(".exam_submit_btn").css("background-color","#ccc");
		 submitResultByAjax('submit');
  	 });
	
	 $(".question-json").each(function(index){
		 var obj = eval('(' + $(this).val() + ')');
		 var objType=$(this).attr("type");
		 var objScore=$(this).attr("score");
		 var questionNo=$(this).attr("questionNo");
		// var html = "<div class='exam_sub_title'>";
		 if(objType==1){
			 var html = "<div class='exam_sub_title'>"+questionNo+"、"+obj.title+"（ 单选  ）"+objScore+"分"+"</div>";
		 $.each(obj.choiceList,function(index2,value) {  
				 html = html+
				 "<div class='exam_sub_chose clearfloat'>"+"<span class='exam_sub_chosebox' name='"+value.option+"' onclick='chooseOption(this,"+objType+")'>"+"</span>"
				 +"<div class='exam_sub_chose_tit'>"+value.option+"．"+value.answer+"</div>"+"</div>";
	     }); }
		 if(objType==2){
			 var html = "<div class='exam_sub_title'>"+questionNo+"、"+obj.title+"（  多选   ）"+objScore+"分"+"</div>";
			 $.each(obj.choiceList,function(index2,value) {  
					 html = html +"<div class='exam_sub_chose clearfloat' >"+
					 "<span class='exam_sub_chosebox' name='"+value.option+"' onclick='chooseOption(this,"+objType+")'>"+"</span>"
					 +"<div class='exam_sub_chose_tit' >"+value.option+"．"+value.answer+"</div>"+"</div>";
		     }); }
		 if(objType==3){
			 var html = "<div class='exam_sub_title'>"+questionNo+"、"+obj.title+"（ 判断 ）"+objScore+"分"+"</div>";
					 html = html +"<div class='exam_sub_chose clearfloat'>"
					 +"<span class='exam_sub_chosebox' name='T' onclick='chooseOption(this,"+objType+")'>"+"</span>"
					 +"<div class='exam_sub_chose_tit'>"+"正确"+"</div>"+"</div>"
					 +"<div class='exam_sub_chose clearfloat'>"
					 +"<span class='exam_sub_chosebox' name='F' onclick='chooseOption(this,"+objType+")'>"+"</span>"
					 +"<div class='exam_sub_chose_tit'>"+"错误"+"</div>"+"</div>";
		     }	
		 if(objType==4){
			 var html = "<div class='exam_sub_title'>"+questionNo+"、"+obj.title+"（ 填空  ）"+objScore+"分"+"</div>";
			 if($("#answerList").val() == ""){
				UE.getEditor("editor-"+questionNo);
			 }
			 }	
		 if(objType==5){
			 var html = "<div class='exam_sub_title'>"+questionNo+"、"+obj.title+"（ 简答  ）"+objScore+"分"+"</div>";
			//实例化编辑器
			//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
			 if($("#answerList").val() == ""){
				UE.getEditor("editor-"+questionNo);
			}
		 }	
		 $(this).after(html);
	 });
	// 答卷后才需要解析答案与分数
	if($("#answerList").val() != ""){
		analyzeAnswer();
		editScore();
	}
});

//解析答案
function analyzeAnswer(){
	clearInterval(t);
	var answer = eval($("#answerList").val());
	 for(var i=0 ; i<answer.length ; i++){
		 var img = "wrong_img";
		 if(answer[i].userAnswer == answer[i].answer){
			 img = "right_img";
		 }
		 var html = "<div class='exam_sub_choseresult clearfloat'>"+
     				"<span class='exam_sub_mychoseresult'>你的答案："+answer[i].userAnswer+"</span>"+
        			"<span class='exam_sub_imgchoseresult'><span class='"+img+"'></span></span>"+
     				"<span class='exam_sub_rightchoseresult'>正确答案："+answer[i].answer+"</span></div>";
		if($("#question-"+answer[i].questionNo).find(".exam_sub_chose").last().size() != 0){
			$("#question-"+answer[i].questionNo).find(".exam_sub_chose").last().append(html);
		}else{
			// 填空以及主观题
			UE.getEditor("editor-"+answer[i].questionNo, {
				initialContent:answer[i].userAnswer ,
				readonly:true
			});
		}
		// 分数
		$("#question-"+answer[i].questionNo).find(".exam_score_detail").html(answer[i].userScore+"分");
	 }
}

// 老师批阅时主观题分数可以编辑
function editScore(){
	 var answer = eval($("#answerList").val());
	 for(var i=0 ; i<answer.length ; i++){
		// 主观题分数
		if($("#question-"+answer[i].questionNo).find(".question-json").attr("type") == 5){
			$("#question-"+answer[i].questionNo).find(".exam_score_detail").remove();
			var html =  "<input type='text' class='exam_score_input' onblur='checkScore(this)' tag='"+answer[i].questionScore+"' value='"+answer[i].userScore+"' />";
			$("#question-"+answer[i].questionNo).find(".exam_score_con").append(html);
		}
	 }
}

function checkScore(obj){
	var reg = /^([0-9]|[1-9]\d|100)$/;
	if(!reg.test($(obj).val())){
		 alert("批阅分数必须是0-100的整数!");
		 $(obj).val("0");
	}
	if(parseInt($(obj).val())>parseInt($(obj).attr("tag"))){
		alert("批阅分数不能大于题目分数!");
		$(obj).val("0");
	}
}

// 选择选项
function chooseOption(obj,questionType){
	var optionFlag = $(obj).attr("class");
	//单选题
	if(questionType==1 || questionType==3){
		$(obj).parent().parent().find(".exam_sub_chosebox_selected").each(function(){
			$(this).attr("class","exam_sub_chosebox");
		});
		if(optionFlag == "exam_sub_chosebox"){
			$(obj).attr("class","exam_sub_chosebox_selected");
		}
	}else{
		// 多选题
		if(optionFlag == "exam_sub_chosebox"){
			$(obj).attr("class","exam_sub_chosebox_selected");
		}else{
			$(obj).attr("class","exam_sub_chosebox");
		}
	}
}

//自动批阅
function autoMarking(){
	clearInterval(t);
	var score = 0;
	var ele=document.getElementById("Hiderow");
	var Correctquestions=0;
	var flag=0;//记录是否有主观题
	var ObjectivequestionsAll=0;
	var errorquestions=0;
	var testJson = "[";
	$(".question-json").each(function(index){
		var userAnswer = "";
		if($(this).attr("type") == "1" || $(this).attr("type") == "3"){
			// 单选、判断题
			userAnswer = $("#question-"+$(this).attr("questionNo")).find(".exam_sub_chosebox_selected").attr("name");
			ObjectivequestionsAll++;
		}else if($(this).attr("type") == "2"){
			ObjectivequestionsAll++;
			// 多选
			$("#question-"+$(this).attr("questionNo")).find(".exam_sub_chosebox_selected").each(function(){ 
				userAnswer = userAnswer + $(this).attr("name")+ ",";
			}); 
			if(userAnswer.length > 1){
				userAnswer = userAnswer.substr(0,userAnswer.length-1);
			}
		}else if($(this).attr("type") == "4" || $(this).attr("type") == "5"){
			// 填空、简答
			// 此处需要转义，双引号会影响json的解析
			userAnswer = (UE.getEditor("editor-"+$(this).attr("questionNo")).getContent()).replace(/"/g, "\\\"");
			if($(this).attr("type") == "5"){
				flag++;
			}else{
				ObjectivequestionsAll++;
			}
		}
		if(userAnswer == null){
			userAnswer = "";
		}
		if(userAnswer != "" && userAnswer == $(this).attr("answer")){
			Correctquestions++;
			score = score + parseInt($(this).attr("score"));
			testJson = testJson + "{"+
		        "\"questionId\": \""+$(this).attr("questionNo")+"\","+
		        "\"questionNo\": \""+$(this).attr("questionNo")+"\","+
		        "\"questionScore\": \""+$(this).attr("score")+"\","+
		        "\"answer\": \""+$(this).attr("answer")+"\","+
		        "\"userScore\": \""+$(this).attr("score")+"\","+
		        "\"userAnswer\": \""+$(this).attr("answer")+"\""+
		    						"},";
		}else{
			testJson = testJson + "{"+
	        "\"questionId\": \""+$(this).attr("questionNo")+"\","+
	        "\"questionNo\": \""+$(this).attr("questionNo")+"\","+
	        "\"questionScore\": \""+$(this).attr("score")+"\","+
	        "\"answer\": \""+$(this).attr("answer")+"\","+
	        "\"userScore\": \"0\","+
	        "\"userAnswer\": \""+userAnswer+"\""+
	    						"},";
		}
		//错误的题数
		errorquestions=ObjectivequestionsAll-Correctquestions;
	 });
	if(flag==0){
		ele.style.display="none";
	}
	$("#resultScore").html(score);
	//客观题正确题数
	$("#Correctquestions").html(Correctquestions);
	//客观题总题数
	$("#ObjectivequestionsAll").html(ObjectivequestionsAll);
	//客观题错误题数
	$("#errorquestions").html(errorquestions);
     //答题情况
     $("#AnswerSituation").html(Correctquestions+"/"+ObjectivequestionsAll);
     //正确率
       $("#Correctrate").html(((Correctquestions/ObjectivequestionsAll)*100).toFixed(2)+"%"); 
	$("#userScore").val(score);
	testJson = testJson.substring(0,testJson.length-1)+"]";
	$("#answerContent").val(testJson);
}

// 人工批阅
function marking(){
	var answer = eval($("#answerList").val());
	var score = 0;
	for(var i=0 ; i<answer.length ; i++){
		// 主观题
	 	if(answer[i].answer == ""){
	 		// 分数
			answer[i].userScore = $("#question-"+answer[i].questionNo).find(".exam_score_input").val();
	 	}
	 	score = score + parseInt(answer[i].userScore);
	}
	// json对象转字符串
	$("#answerContent").val(JSON.stringify(answer));
	$("#resultScore").html(score);
	$("#userScore").val(score);
}

// 提交考试成绩
function submitResultByAjax(url){
	$.ajax( {   
	    type : "POST",   
	    url : "${pageContext.request.contextPath}/test/"+url+".action", 
	    data : {
	      'testId':$("#testId").val(),
	      'testType':$("#testType").val(),
	      'markStatus' : $("#paperId").attr("type"),
	      'content' : $("#answerContent").val(),
	      'paperId' :$("#paperId").val(),
	      'classId' :$("#classId").val(),
	      'userScore' : $("#userScore").val(),
	      'userAnswerCostTime' : $("#userAnswerCostTime").val(),
	      'finish' : $("#finish").val()
	     },  
	    dataType: "json",   
	    success : function(data) {   
	        if(data.success == "true"){   
	            $("#testId").val(data.testId)
	            if(url == "marking"){
	            	window.location.href="${pageContext.request.contextPath}/test/markingList.action?paperId="+$("#paperId").val()+"&classId="+$("#classId").val();
	            }
	        }   
	        else{   
	            alert("请勿重复提交试卷！");   
	        }   
	    },   
	    error :function(){   
	        alert("网络连接出错！");   
	    }   
	});   
}

// 查看测评结果
function queryDetail(courseId){
	if(courseId != null){
		window.location.href="${pageContext.request.contextPath}/course/learn.action?courseId="+courseId+"&classId="+$("#classId").val();
	}else{
		window.location.href="${pageContext.request.contextPath}/test/preview.action?testId="+$("#testId").val();
	}
}

 var c=0;
 var t;
 function timedCount(){
	hour = parseInt(c / 3600);// 小时数
	min = parseInt(c / 60);// 分钟数
	if(min>=60){
	 min=min%60;
	}
	lastsecs = c % 60;
	if(hour==0){
		$("span[id='hour']").text("00"); 
		
	}
	else{
		$("span[id='hour']").text(hour); 
	}
	if(min==0){
		$("span[id='minute']").text("00"); 
		
	}else if(min/10<1){
		$("span[id='minute']").text("0"+min); 
	}
	else{
		$("span[id='minute']").text(min); 
	}
	if(lastsecs==0){
		$("span[id='Seconds']").text("00"); 
		
	}else if(lastsecs/10<1){
		$("span[id='Seconds']").text("0"+lastsecs); 
	}
	else{
		$("span[id='Seconds']").text(lastsecs); 
	}
    c=c+1;
    t=setTimeout("timedCount()",1000);
    $("span[id='resultTime']").text(hour + "时" + min + "分" + lastsecs + "秒");
    $("#userAnswerCostTime").val(hour*3600+min*60+lastsecs);
    if(min==55&&lastsecs==0){
     alert("考试时间还剩5分钟，请抓紧时间答题");
    }
    if(min==0&&lastsecs==60){
    //	clearInterval(t);
    	$('.exam_submit_btn').click();
    	clearInterval(t);
    	$('.time_cal_detail').css('color','#FF0000');
    }
}
</script>
</body>
</html>
