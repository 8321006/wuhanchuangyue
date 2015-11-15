<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>我的课程</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/my_course_detail.css"/>

<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/video.css"/>
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/my_course_video.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pop_block_login.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pagination.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.pagination.js"></script>
<script src="${pageContext.request.contextPath}/js/emswf.js"></script>
</head>
<body  onunload="checkLeave()">

<!----视频问题弹出框--->
<div class="pop_msg_video_ask">
	<div class="pop_video_ask_title"><span>《${course.courseName}》小测试</span></div>
    <div class="pop_video_ask_con">
     <c:forEach items="${questionList}" var="item" begin="0" end="1">
    <input class="question-json" questionNo="${item.questionNo}" answer="${item.answer}"  value='${item.content}' name="content" type="${item.questionTypeId}"style="display:none;"/>	
     </c:forEach>     
    </div> 
    <div class="pop_video_ask_submit_btn"onclick="openvideo();"><a>提交</a></div>
</div>
<div class="pop_msg_bg_video_ask"></div>

<!----头部区域-->	
<div class="inner_header">
	<jsp:include page="../head.jsp"></jsp:include>
</div>
<!---中间内容区域  开始---->
<div class="container_index">	
	<div class="video_con_content">
    	<div class="video_con_tit">
        	<span>课程</span>&nbsp;&gt;&nbsp;<span>${course.courseTypeName}</span>&nbsp;&gt;&nbsp;<span>${course.courseName}</span>
        </div>
        <div class="video_zone">
        <div class="video_zone_detail" id='video'></div>
       <div class="video_zone_right">
            	<ul class="video_look_tab clearfloat">
                    <li class="curr"><img src="${pageContext.request.contextPath}/images/my_course/video_tab_ico_01.png" alt=""/><span>目录</span></li>
                    <c:if test="${sessionScope.user.userType == '0'}">
                    <li><img src="${pageContext.request.contextPath}/images/my_course/video_tab_ico_02.png" alt=""/><span>笔记</span></li>
                    </c:if>
                </ul>
            	<div class="video_look_tab_con video_look_tab_change1">
                	<ul class="video_catalog_list">
                	<c:forEach items="${coursechapter}" var="p" varStatus="status">
                		<c:if test="${p.chapterId==chapterId}">
                    	<li chapterId="${p.chapterId}" videoId="${p.chapterVideoFile}" class="playlist" style="cursor:pointer">
                        	<b class="video_catalog_list_ico"></b>
                            <span class='selected' title='${p.chapterIndex} ${p.chapterName}'>${p.chapterIndex} ${p.chapterName}</span>
                        </li>
                        </c:if>
                        <c:if test="${p.chapterId!=chapterId}">
                    	<li chapterId="${p.chapterId}" videoId="${p.chapterVideoFile}" class="playlist" style="cursor:pointer">
                        	<b class="video_catalog_list_ico display_none"></b>
                            <span title='${p.chapterIndex} ${p.chapterName}'>${p.chapterIndex} ${p.chapterName}</span>
                        </li>
                        </c:if>
                    </c:forEach>
                    </ul>
        		</div>
                <div class="video_look_tab_con video_look_tab_change2 display_none">
                	<div class="video_look_notes clearfloat">
                	<c:forEach items="${notes}" var="p" varStatus="status">
                    	<div class="video_look_notes_list">
                        	<div class="video_look_notes_txt">${p.noteContent}</div>
                            <div class="video_look_notes_issue clearfloat">
                                <span class="video_look_notes_issuetime">发布时间：<fmt:formatDate value="${p.noteTime}" pattern="yyyy/MM/dd HH:mm"/></span>
                            </div>
                        </div>
                    </c:forEach>
                    </div>
                    <div class="video_look_notes_edit">
                    	<textarea class="video_look_notes_edit_textarea" maxlength="100"></textarea>
                        <div class="video_look_notes_edit_btn">
                            <span>可以输入100个字</span>
                            <a onclick="addnote()">发布</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="video_ques_info clearfloat">                                       
        	<div class="video_ques_detail">
        	<div class="mycourse_detail_cl_tit">视频讨论</div>
				
                <div class="mycourse_detail_discuss">
                <!-- 
                  <c:forEach items="${list}" var="t">
                    <div class="mycourse_detail_discuss_content clearfloat">
                        <div class="mycourse_detail_discuss_left">
                        	<img src="${pageContext.request.contextPath}/images/my_course/mycourse_person_ico_01.png" alt=""/>
                        </div>
                        <div class="mycourse_detail_discuss_right">
                            <div class="mycourse_detail_discuss_txt">${t["quizContent"]}</div>
                        </div>
                    </div>
                    <div class="mycourse_detail_discuss_content clearfloat">
						<c:if test="${t['questionType'] == 1}">
                        <p class="mycourse_detail_discuss_nametxt">${t["realName"]}</p>
						</c:if>
						<c:if test="${t['questionType'] == 0}">
                        <p class="mycourse_detail_discuss_nametxt">******</p>                      
                        </c:if>
                        <div class="mycourse_detail_discuss_issue">发布时间：<fmt:formatDate value='${t["quizTime"]}' pattern="yyyy/MM/dd HH:mm:ss"/></div>
                    </div>
                   </c:forEach>
                    -->
                </div>
                
                 <div id="Pagination" class="right flickr"></div>	
				             	
            	<div class="mycourse_detail_discuss_edit clearfloat" id="twdiv">
            	 <div class="mycourse_detail_cl_tit mt_16px">讨论</div>
                    <textarea id="fbtw" class="mycourse_detail_discuss_edit_zone" maxlength="140" onkeyup="checkLength(this);"></textarea>
                    <div class="mycourse_detail_discuss_issue_btn">
                        <span id="rightnum"></span>           
                        <span id="fbtwerr" style="color:red;"></span>
                         <!-- <a onclick="question()">发布</a>
                        <input type="checkbox" id='questionType' value="" onclick="show()" >匿名提问 -->
                        <b id='quizType' class="mycourse_noname_ask_txt mycourse_noname_ask" style="display: none">匿名提问</b>
                        <a onclick="question()">发布</a>
                    </div>
                </div>
            </div>
            
            <div class="video_info_detail">
            	<div class="mycourse_cr_tit">老师简介</div>
                <div class="mycourse_cr_teacher_detail clearfloat">
                    <div class="mycourse_cr_teacher_img"><img src="${pageContext.request.contextPath}/images/my_course/mycourse_teacher_ico_02.jpg" alt=""/></div>
                    <div class="mycourse_cr_teacher_namedetail">
                        <p class="mt_32px">${course.courseAuthor}</p>
                        <p class="mt_12px">${course.courseAuthorDetail}</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <form action="${pageContext.request.contextPath}/course/video.action" id="playform"  name="playform" method="post">
    <input type="hidden" id='beginTime' value='<fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd HH:mm:s:ss" />'>
    <input type="hidden" id='userId' value="${sessionScope.user.userId}">
    <input type="hidden" id='beforechapterId' name="beforechapterId" value="${chapterId}">
    <input type="hidden" id='chapterId' value="${chapterId}" name="chapterId">
    <input type="hidden" id='videoId' value="${videoId}">
    <input type="hidden" id='courseId' name="courseId" value="${course.courseId}">
    <input type="hidden" id='quizContent' name='quizContent' value="${quizContent}">
    <input type="hidden" id='classId' name='classId' value="${classId}">
    <input type="hidden" id='teacherId' name='teacherId' value="${teacherId}">
    <input type="hidden" id='userType' value="${sessionScope.user.userType}"> 
    </form>
</div>
<!---底部区域---->
</body>
<script type="text/javascript">
var flashVars ={};
flashVars["auto_play"] = 1;//是否自动播放
flashVars.skinnable =1;//是否使用默认皮肤
flashVars.uu ="${uu}";
flashVars.vu ="${vv}";
var url = location.search; //获取url中"?"符后的字串
if (url.indexOf("?") != -1) {
    var str = url.substr(1);
    strs = str.split("&");
    for(var i = 0; i < strs.length; i ++) {
        var key = strs[i].substr(0,strs[i].indexOf("="));
        var value =strs[i].substr(strs[i].indexOf("=")+1);
        flashVars[key] =value;
    }
}
var videoSwf = new FlashPlayer("video",{url:"http://yuntv.letv.com/bcloud.swf"},flashVars);
videoSwf.initialize();

if('${sessionScope.user.userType}' == '1')
	{
	    $("#twdiv").hide();
	}

//首次加载     
pageselectCallback(0); 
//分页事件   
$("#Pagination").pagination('${num}', {
          callback: pageselectCallback,//PageCallback() 为翻页调用次函数。
          prev_text: " 上一页",
          next_text: "下一页 ",
          items_per_page: 5, //每页的数据个数
          num_display_entries: 3, //两侧首尾分页条目数
          current_page: 0,   //当前页码
          num_edge_entries: 2, //连续分页主体部分分页条目数
});


function checkLength(gm) {
    var maxChars = 140;
    if (gm.value.length > maxChars){
         gm.value = gm.value.substring(0,maxChars);
    }
    var curr = maxChars - gm.value.length;
    document.getElementById("rightnum").innerHTML = "还可输入"+curr.toString()+"字";
}

function question(){
	var fbtw=document.getElementById("fbtw").value
	if(fbtw.trim()=="")
		{
		     document.getElementById("fbtwerr").innerHTML = "您没有填写内容";
		     $("#fbtwerr").delay(4000).fadeOut("slow");
		     return;
		}
	/**
	document.playform.action="${pageContext.request.contextPath}/quiz/questionAdd.action";
	$("#quizContent").val($(".mycourse_detail_discuss_edit_zone").val())
	document.playform.submit();
	**/	
	/**
	var wtnm=document.getElementById("quizType").value;	
	var courseId=document.getElementById("courseId").value;	
	var chapterId=document.getElementById("chapterId").value;	
	var classId=document.getElementById("classId").value;
	**/
	var teacherId=document.getElementById("teacherId").value;
  
	$.ajax({
	     type: 'POST',
	     url: '${pageContext.request.contextPath}/quiz/questionAdd.action',
	     data:{quizType:document.getElementById("quizType").value,courseId:document.getElementById("courseId").value,chapterId:document.getElementById("chapterId").value,classId:document.getElementById("classId").value,quizContent:document.getElementById("fbtw").value,pageIndex:0},
	     success:function (data) {
	    	 pageselectCallback(0);	
	    	 $("#Pagination").pagination(data.list.length, {
	              callback: pageselectCallback,  //PageCallback() 为翻页调用次函数。
	              prev_text: " 上一页",
	              next_text: "下一页 ",
	              items_per_page: 5, //每页的数据个数
	              num_display_entries: 3, //两侧首尾分页条目数
	              current_page: 0,   //当前页码
	              num_edge_entries: 2, //连续分页主体部分分页条目数
	    });
	     },
	     dataType: "json"
	});
   
	document.getElementById("fbtw").value="";
}
function checkLeave(){
	if($("#userType").val() == '0'){
		$.ajax({
		     type: 'POST',
		     url: '${pageContext.request.contextPath}/course/endplay.action',
		     data: {beginTime:$("#beginTime").val(),userId:$("#userId").val(),chapterId:$("#beforechapterId").val(),videoId:$("#videoId").val(),courseId:$("#courseId").val(),classId:$("#classId").val()},
		     success:function () {
		     },
		     dataType: "json"
		});
	}
}

$(".playlist").click(function(){
	if($(this).attr("videoid") != ""){
		$("#chapterId").val($(this).attr("chapterId"));
		document.playform.submit();
	}
});

function addnote(){
	if($(".video_look_notes_edit_textarea").val().trim() ==''){
		return;
	}
	$.ajax({
	     type: 'POST',
	     url: '${pageContext.request.contextPath}/course/addnote.action',
	     data: {creator:$("#userId").val(),chapterId:$("#beforechapterId").val(),courseId:$("#courseId").val(),noteContent:$(".video_look_notes_edit_textarea").val()},
	     success:function (data) {
	    	$(".video_look_notes_edit_textarea").val("");
	    	var html="";
	    	html=html+'<div class="video_look_notes_list">';
	    	html=html+'<div class="video_look_notes_txt">'+data.noteContent+'</div>';
	    	html=html+'<div class="video_look_notes_issue clearfloat">';
	    	html=html+'<span class="video_look_notes_issuetime">发布时间：'+data.reserve;
	    	html=html+'</span></div></div>';
	    	$(".video_look_notes").append(html)
	     },
	     dataType: "json"
	});
}
 
function learn(chapterId){
	alert(chapterId)
	//window.location.href="${pageContext.request.contextPath}/course/detail.action?courseId="+courseId;
}

function pageselectCallback(page_id, jq) {
	$.ajax({
	     type: 'POST',
	     url: '${pageContext.request.contextPath}/quiz/questionList.action',
	     data: {quizType:document.getElementById("quizType").value,courseId:document.getElementById("courseId").value,chapterId:document.getElementById("chapterId").value,classId:document.getElementById("classId").value,quizContent:document.getElementById("fbtw").value,pageIndex:page_id},
	     success:function (data) {
	    	$(".mycourse_detail_discuss").html("");
	    	var html="";
	    	for(var i =0;i<data.list.length;i++)
	    	{
	    		 var obj =data.list[i];
	    		 
	    		 html=html+'<div class="mycourse_detail_discuss">';
	    		 html=html+'<div class="mycourse_detail_discuss_content clearfloat">';
	    		 html=html+'<div class="mycourse_detail_discuss_left">';
	    		 
	    		 if('${sessionScope.user.sex}'==""||'${sessionScope.user.sex}' == '0')
	    		 {
	    			 if('${sessionScope.user.userType}' == '0')
	    			 {
	    				 html=html+'<img src="${pageContext.request.contextPath}/images/user_center/person_center_student_01.jpg" alt=""/></div>';   
	    			 }
	    		     if('${sessionScope.user.userType}' == '1')
	    			 {	 
	    			     html=html+'<img src="${pageContext.request.contextPath}/images/user_center/person_center_teacher_01.jpg" alt=""/></div>'; 
	    			 }
	    		     if('${sessionScope.user.userType}' == '2')
	    			 {
	    			     html=html+'<img src="${pageContext.request.contextPath}/images/user_center/person_center_shcool_01.jpg" alt=""/></div>';
	    			 } 
	    		 }	    		 
	    		 
	    		 if('${sessionScope.user.sex}' == '1')
	    		 {
	    			 if('${sessionScope.user.userType}' == '0')
	    				 {
	    				   html=html+'<img src="${pageContext.request.contextPath}/images/user_center/person_center_student_02.jpg" alt=""/></div>';
	    				 }
	    		    if('${sessionScope.user.userType}' == '1')
	    			 {
	    			       html=html+'<img src="${pageContext.request.contextPath}/images/user_center/person_center_teacher_02.jpg" alt=""/></div>';
	    			 }
	    		     if('${sessionScope.user.userType}' == '2')
	    			 {
	    			       html=html+'<img src="${pageContext.request.contextPath}/images/user_center/person_center_shcool_01.jpg" alt=""/></div>';
	    			 }
	    		  }
	    				
	    		 html=html+'<div class="mycourse_detail_discuss_right">';
	    		 html=html+'<div class="mycourse_detail_discuss_txt">'+obj.quizContent.replace("<","&lt;").replace(">","&gt;").replace("</","&lt;\/")+'</div>';
	    		 html=html+'</div></div><div class="mycourse_detail_discuss_content clearfloat">';  
	    		 if(obj.questionType== 1)
	    			 {
	    		        html=html+'<p class="mycourse_detail_discuss_nametxt">'+obj.realName+'</p>';
	    			 }
	    		 else
	    			 {
	    			     html=html+'<p class="mycourse_detail_discuss_nametxt">******</p>';
	    			 }	    		 
	    		 html=html+'<div class="mycourse_detail_discuss_issue">发布时间：'+obj.quizTimeString+'</div>';
	    		 html=html+'</div></div>';
	    	}
	    		 $(".mycourse_detail_discuss").html(html);
	     },
	     dataType: "json"
	});
   
	document.getElementById("fbtw").value="";
}


$('.mycourse_noname_ask_txt').click(function(){
	if($(this).hasClass('mycourse_noname_ask_selected')){
		$(this).removeClass('mycourse_noname_ask_selected');
		$(this).addClass('mycourse_noname_ask');
		}else{
			$(this).removeClass('mycourse_noname_ask');
			$(this).addClass('mycourse_noname_ask_selected');
			}

	var classvalue = $("#quizType").attr("class");
	if(classvalue=="mycourse_noname_ask_txt mycourse_noname_ask_selected"){
		$('#quizType').val("0");
		//alert("选中："+classvalue)
	}
	else{
		$('#quizType').val("1");
		//alert("不选中："+classvalue)
	}
	
}); 
var c=0;
var t;
$(document).ready(function(){
	timedCount();
});

function timedCount(){
	hour = parseInt(c / 3600);// 小时数
	min = parseInt(c / 60);// 分钟数
	if(min>=60){
	 min=min%60;
	}
	lastsecs = c % 60;
    c=c+1;
    t=setTimeout("timedCount()",1000);
     if(min==0&&lastsecs==5){
	   $('.pop_msg_video_ask').css('display','block');
	 $('.pop_msg_bg_video_ask').css('display','block');
	 var w_width = $(window).width();
		var w_height = $(window).height();
		var b_height = $(document.body).height();
		if(b_height<w_height){
			$('.pop_msg_bg_video_ask').css('height',w_height);
			}else{
				$('.pop_msg_bg_video_ask').css('height',b_height);
				}
		var w_self = $('.pop_msg_video_ask').width();
		var h_self = $('.pop_msg_video_ask').height();
		var _x = w_width/2 - w_self/2;
		var _y = w_height/2 - h_self/2;
		$('.pop_msg_video_ask').css({left:_x,top:_y});
    }
  // $("span[id='resultTime']").text(hour + "时" + min + "分" + lastsecs + "秒");
   //$("#userAnswerCostTime").val(hour*3600+min*60+lastsecs);
   
  // if(min==55&&lastsecs==0){
   // alert("考试时间还剩5分钟，请抓紧时间答题");
   //}
   //if(min==0&&lastsecs==60){
   //	clearInterval(t);
   //	$('.exam_submit_btn').click();
   	//clearInterval(t);
   //	$('.time_cal_detail').css('color','#FF0000');
   //}
   }
$(".question-json").each(function(index){
	 var obj = eval('(' + $(this).val() + ')');
	 var objType=$(this).attr("type");
	// var objScore=$(this).attr("score");
	 var questionNo=$(this).attr("questionNo");
	 var html = "<div class='pop_video_ask_subject'>";
	 if(objType==1){
		  html+="<div class='pop_video_ask_subject_tit'>"+questionNo+"、"+obj.title+"（ 单选  ）</div>";
	 $.each(obj.choiceList,function(index2,value) {  
			 html = html+"<div class='pop_video_ask_sub_chose clearfloat'>"+"<span class='pop_video_ask_sub_chosebox' name='"+value.option+"' onclick='chooseOption(this,"+objType+")'>"+"</span>"
			 +"<div class='pop_video_ask_sub_chose_tit'>"+value.option+"．"+value.answer+"</div>"+"</div>";
    }); }
	 if(objType==2){
		  html+="<div class='pop_video_ask_subject_tit'>"+questionNo+"、"+obj.title+"（  多选   ）</div>";
		 $.each(obj.choiceList,function(index2,value) {  
			 html = html+"<div class='pop_video_ask_sub_chose clearfloat'>"+"<span class='pop_video_ask_sub_chosebox' name='"+value.option+"' onclick='chooseOption(this,"+objType+")'>"+"</span>"
			 +"<div class='pop_video_ask_sub_chose_tit'>"+value.option+"．"+value.answer+"</div>"+"</div>";
	     }); }
	 if(objType==3){
		 html+="<div class='pop_video_ask_subject_tit'>"+questionNo+"、"+obj.title+"（  多选   ）</div>";
				 html = html +"<div class='pop_video_ask_sub_chose clearfloat'>"
				 +"<span class='pop_video_ask_sub_chosebox' name='T' onclick='chooseOption(this,"+objType+")'>"+"</span>"
				 +"<div class='pop_video_ask_sub_chose_tit'>"+"正确"+"</div>"+"</div>"
				 +"<div class='pop_video_ask_sub_chose clearfloat'>"
				 +"<span class='pop_video_ask_sub_chosebox' name='F' onclick='chooseOption(this,"+objType+")'>"+"</span>"
				 +"<div class='pop_video_ask_sub_chose_tit'>"+"错误"+"</div>"+"</div>";
	     }	
	 html+="</div>";
	 $(this).after(html);
});
function chooseOption(obj,questionType){
	var optionFlag = $(obj).attr("class");
	//单选题
	if(questionType==1 || questionType==3){
		$(obj).parent().parent().find(".pop_video_ask_sub_chosebox_selected").each(function(){
			$(this).attr("class","pop_video_ask_sub_chosebox");
		});
		if(optionFlag == "pop_video_ask_sub_chosebox"){
			$(obj).attr("class","pop_video_ask_sub_chosebox_selected");
		}
	}else{
		// 多选题
		if(optionFlag == "pop_video_ask_sub_chosebox"){
			$(obj).attr("class","pop_video_ask_sub_chosebox_selected");
		}else{
			$(obj).attr("class","pop_video_ask_sub_chosebox");
		}
	}
}
function openvideo(){
 $('.pop_msg_video_ask').css('display','none');
 $('.pop_msg_bg_video_ask').css('display','none');	
}
</script>
</html>
