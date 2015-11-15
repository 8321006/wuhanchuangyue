<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>个人中心——教学调查问卷</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/questionnaire_student.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/user_center.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/questionnaire_student.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ueditor1_4_3-utf8-jsp/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ueditor1_4_3-utf8-jsp/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${pageContext.request.contextPath}/resources/ueditor1_4_3-utf8-jsp/lang/zh-cn/zh-cn.js"></script>
</head>
<body>

<!----头部区域-->	
<div class="inner_header">
    <jsp:include page="../head.jsp"></jsp:include>
</div>
<!---中间内容区域  开始---->
<div class="container_index">		
	<div class="questionnaire_con">
    	<div class="questionnaire_title">${paper.paperName}</div>
        <div class="questionnaire_info clearfloat">
        	<div class="questionnaire_info_course_img_left">
        	<c:if test="${empty(courseCoverUrl)}"><img class="questionnaire_info_course_img" src="images/questionnaire/questionnaire_couse_img_01.jpg" alt=""/>
        	</c:if>
        	<c:if test="${not empty(courseCoverUrl)}">
        	<img class="questionnaire_info_course_img" src="${pageContext.request.contextPath}${courseCoverUrl}" alt=""/>
        	</c:if>
        	</div>
            <div class="questionnaire_info_content_degree">
            	<span class="questionnaire_info_content_degree_txt">课程整体满意度</span>
            	   <c:if test="${state=='0'}">
                 <ul class="questionnaire_info_content_degree_star"id="starChange">
                	<li value="1"><a href="javascript:;">1</a></li>
                    <li value="2"><a href="javascript:;">2</a></li>
                    <li value="3"><a href="javascript:;">3</a></li>
                    <li value="4"><a href="javascript:;">4</a></li>
                    <li value="5"><a href="javascript:;">5</a></li>
                     </ul></c:if>
                  <c:if test="${state=='1'}">
                  <ul class="questionnaire_info_content_degree_star">
                 <c:forEach begin="1" end="${surveyResult}">
                   <li class="on"></li>
                 </c:forEach>
                 <c:forEach begin="1" end="${5-surveyResult}">
                    <li></li>
                 </c:forEach>
                  </ul>
                </c:if>
            </div>
        </div>
         <c:forEach items="${questionList}" var="item">
          	<div id="question-${item.questionNo}">
          	<input class="question-json" questionNo="${item.questionNo}" selectATotal="${item.selectATotal}" selectBTotal="${item.selectBTotal}" selectCTotal="${item.selectCTotal}" selectDTotal="${item.selectDTotal}" userTotal="${userTotal}" state="${state}" questionId="${item.questionId}" answer="${item.answer}" score="${item.points}" value='${item.content}' name="content" type="${item.questionTypeId}"style="display:none;"/>
        	<c:if test="${item.questionTypeId == 4 || item.questionTypeId == 5}">
			<br>
			<script id="editor-${item.questionNo}" type="text/plain" style="width:1080px;height:290px;"></script>
			</c:if>
			</div>
        </c:forEach>
        <input value='' id="answerContent" name="content" style="display:none;"/>
        <input  id="testType" name="testType" style="display:none;" value="${paper.testType}"/>	
        <input  id="surveyResult" name="surveyResult" style="display:none;"/>
        <input  id="userWriteTotal" name="userWriteTotal" value="${userWriteTotal}" style="display:none;"/>
		<input value='${paper.paperId}' id="paperId" type='${paper.subjective}' name="paperId" style="display:none;"/>	
		<c:if test="${state==0}">
		<div class="questionnaire_submit_btn_con" id="submitButton">提交结果</div>
		</c:if>
        <c:if test="${state==1}">
        <a href="${pageContext.request.contextPath}/survey/surveylist.action">
        <div class="questionnaire_submit_btn_con" id="returnButton"> 返回</div>
        </a>
        </c:if>
    </div>
</div>
<!---底部区域---->

<script type="text/javascript">
$(".question-json").each(function(index){
	 var obj = eval('(' + $(this).val() + ')');
	 var objType=$(this).attr("type");
	 var objScore=$(this).attr("score");
	 var userTotal=$(this).attr("userTotal");
	 var selectATotal=$(this).attr("selectATotal");
	 var selectATotal=$(this).attr("selectATotal");
	 var selectBTotal=$(this).attr("selectBTotal");
	 var selectCTotal=$(this).attr("selectCTotal");
	 var selectDTotal=$(this).attr("selectDTotal");
	 var state=$(this).attr("state");
	 var questionNo=$(this).attr("questionNo");
	 var html ="<div class='questionnaire_subject'>";
	 if(objType==1){
		 var html = html+"<div class='questionnaire_sub_title'>"+questionNo+"、"+obj.title+"（ 单选  ）</div>";
		 $.each(obj.choiceList,function(index2,value) {  
				 html = html+
				 "<div class='questionnaire_sub_chose_con clearfloat'><div class='questionnaire_sub_chose'> ";
				 if(state==0){
					 html = html+ "<span class='questionnaire_sub_chosebox' name='"+value.option+"' onclick='chooseOption(this,"+objType+")'></span> ";
				 }
				 html = html+ "<div class='questionnaire_sub_chose_tit'>"+value.option+"．"+value.answer+"</div>"+"</div>";
	      if(state==1){
	    	  html+=getchoicehtml(value.option,selectATotal,selectBTotal,selectCTotal,selectDTotal,userTotal);
	    	  }
	      html=html+"</div>";
		 }); }
	  if(objType==2){
		  var html = html+"<div class='questionnaire_sub_title'>"+questionNo+"、"+obj.title+"（ 多选  ）</div>";
		 $.each(obj.choiceList,function(index2,value) {  
			 html = html+"<div class='questionnaire_sub_chose_con clearfloat'><div class='questionnaire_sub_chose'>";
			 if(state==0){
				html = html+ "<span class='questionnaire_sub_chosebox' name='"+value.option+"' onclick='chooseOption(this,"+objType+")'></span> ";
				 }
			   html = html+"<div class='questionnaire_sub_chose_tit'>"+value.option+"．"+value.answer+"</div>"+"</div>";
			 if(state==1){
		      html+=getchoicehtml(value.option,selectATotal,selectBTotal,selectCTotal,selectDTotal,userTotal);
		    	 }
			 html=html+"</div>";
	     }); }
	 
	 if(objType==5){
// 		var html =html+ "<div class='questionnaire_sub_title'>"+questionNo+"、"+obj.title+"（ 简答  ）</div>";
// 			html = html+"<textarea class='questionnaire_ask_textarea'></textarea></div></div>"
		var html =html+"<div class='questionnaire_sub_title'>"+questionNo+"、"+obj.title+"（ 简答  ）</div>";
		//实例化编辑器
		//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
		UE.getEditor("editor-"+questionNo);
	 }
	 html=html+"</div>"
	 $(this).after(html);
});


function chooseOption(obj,questionType){
	var optionFlag = $(obj).attr("class");
	//单选题
	if(questionType==1){
		$(obj).parent().parent().parent().find(".questionnaire_sub_chosebox_selected").each(function(){
			$(this).attr("class","questionnaire_sub_chosebox");
		});
		if(optionFlag == "questionnaire_sub_chosebox"){
			$(obj).attr("class","questionnaire_sub_chosebox_selected");
		}
	}else{
		// 多选题
		if(optionFlag == "questionnaire_sub_chosebox"){
			$(obj).attr("class","questionnaire_sub_chosebox_selected");
		}else{
			$(obj).attr("class","questionnaire_sub_chosebox");
		}
	 }
}
	
	
function autoMarking(){	
	//var ele=document.getElementById("Hiderow");
	var Correctquestions=0;
	//var flag=0;//记录是否有主观题
	//var ObjectivequestionsAll=0;
	var testJson = "[";
	$(".question-json").each(function(index){
		var userAnswer = "";
		if($(this).attr("type") == "1"){
			// 单选、判断题
			userAnswer = $("#question-"+$(this).attr("questionNo")).find(".questionnaire_sub_chosebox_selected").attr("name");
		}else if($(this).attr("type") == "2"){
			// 多选
			$("#question-"+$(this).attr("questionNo")).find(".questionnaire_sub_chosebox_selected").each(function(){ 
				userAnswer = userAnswer + $(this).attr("name")+ ",";
			}); 
			if(userAnswer.length > 1){
				userAnswer = userAnswer.substr(0,userAnswer.length-1);
			}
		}else if($(this).attr("type") == "4" || $(this).attr("type") == "5"){
			// 填空、简答
			// 此处需要转义，双引号会影响json的解析
			userAnswer = (UE.getEditor("editor-"+$(this).attr("questionNo")).getContent()).replace(/"/g, "\\\"");
		}
		if(userAnswer == null){
			userAnswer = "";
		}
		if(userAnswer != "" && userAnswer == $(this).attr("answer")){
			Correctquestions++;
			//score = score + parseInt($(this).attr("score"));
			testJson = testJson + "{"+
				"\"questionId\": \""+$(this).attr("questionId")+"\","+
		        "\"userAnswer\": \""+$(this).attr("answer")+"\""+
		    						"},";
		}else{
			testJson = testJson + "{"+
			"\"questionId\": \""+$(this).attr("questionId")+"\","+
	        "\"userAnswer\": \""+userAnswer+"\""+
	    						"},";
		}
		//错误的题数
		
	 });
	testJson = testJson.substring(0,testJson.length-1)+"]";
	$("#answerContent").val(testJson);
	return testJson;
}

//提交考试成绩
function submitResultByAjax(url){	
	var flag=check();
	if (flag==0) {
	$.ajax( {   
	    type : "POST",   
	    url : "${pageContext.request.contextPath}/test/"+url+".action", 
	    data : {
	      'testId':$("#testId").val(),
	      'testType':$("#testType").val(),
	      'markStatus':$("#paperId").attr("type"),
	      'content' : $("#answerContent").val(),
	      'paperId' :$("#paperId").val(),
	      'classId' :$("#classId").val(),
	      'userWriteTotal':$("#userWriteTotal").val(),
	      'userScore' : $("#surveyResult").val(),
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
	            window.location.href="${pageContext.request.contextPath}/survey/surveylist.action";
	        }   
	        else{   
	            alert("请勿重复提交试卷！");   
	        }   
	    },   
	    error :function(){   
	        alert("网络连接出错！");   
	    }   
	});   
}else{
	alert("你有题目选项未勾选或调查问卷星级未评定!");
	}
	}
function getchoicehtml(data,selectATotal,selectBTotal,selectCTotal,selectDTotal,userTotal){
	 var html ="";
	 var selectA=Math.round(selectATotal/userTotal * 100)+"%";
	 var selectB=Math.round(selectBTotal/userTotal * 100)+"%";
	 var selectC=Math.round(selectCTotal/userTotal * 100)+"%";
	 var selectD=Math.round(selectDTotal/userTotal * 100)+"%";
	if(data=="A"){
		 html=html+"<div class='questionnaire_sub_right_progess'><span class='questionnaire_sub_right_progess_name'>"+data+"选项：</span>"
	  	  +"<div class='questionnaire_sub_right_progess_line'><div class='questionnaire_sub_right_progess_line_used bg_ffda81' style='width:"+selectA+";'></div></div>"+
	  	  "<span class='questionnaire_sub_right_progess_total'>"+selectATotal+"人</span></div>";
	  }
	  if(data=="B"){
		  html=html+"<div class='questionnaire_sub_right_progess'><span class='questionnaire_sub_right_progess_name'>"+data+"选项：</span>"
  	  +"<div class='questionnaire_sub_right_progess_line'><div class='questionnaire_sub_right_progess_line_used bg_73bdec'style='width:"+selectB+";'></div></div>"+
  	  "<span class='questionnaire_sub_right_progess_total'>"+selectBTotal+"人</span></div>";
	  }
	  if(data=="C"){
		  html=html+"<div class='questionnaire_sub_right_progess'><span class='questionnaire_sub_right_progess_name'>"+data+"选项：</span>"
  	  +"<div class='questionnaire_sub_right_progess_line'><div class='questionnaire_sub_right_progess_line_used bg_ff8f81' style='width:"+selectC+";'></div></div>"+
  	  "<span class='questionnaire_sub_right_progess_total'>"+selectCTotal+"人</span></div>";
	  }
	  if(data=="D"){
		  html=html+"<div class='questionnaire_sub_right_progess'><span class='questionnaire_sub_right_progess_name'>"+data+"选项：</span>"
  	  +"<div class='questionnaire_sub_right_progess_line'><div class='questionnaire_sub_right_progess_line_used bg_73bdec'style='width:"+selectD+";'></div></div>"+
  	  "<span class='questionnaire_sub_right_progess_total'>"+selectDTotal+"人</span></div>";
	  }
	  return html;
}

$("#submitButton").click(function(){
	 autoMarking();
	 submitResultByAjax('submit');
	 });
 $('#starChange li').hover(function(){
	 var surveyResult=$(this).val();
	  //alert("surveyResult"+surveyResult);
	  $(this).addClass('on');
		$(this).prevAll().addClass('on');
		$(this).nextAll().removeClass('on');
	$("#surveyResult").val(surveyResult);
});
   function selectcourseId(obj){
	var courseId=$(obj).val();
	alert(courseId);
}
   //提交时进行check，检查该用户是否填写完整
   function check(){
	   var flag=0;
	   var testJson=jQuery.parseJSON(autoMarking(testJson));
	   var questiontotal=testJson.length;
	   var questionwritetotal=0;
	   for(var i=0;i<questiontotal;i++) 
	   {
		if(testJson[i].userAnswer!=''){
			questionwritetotal++;
		}
	   }
	   if(questionwritetotal<questiontotal){
		   flag++;
	   }
	   if($("#surveyResult").val()==''){
		flag++;
	}
	   return flag;
   }
</script>
</body>
</html>