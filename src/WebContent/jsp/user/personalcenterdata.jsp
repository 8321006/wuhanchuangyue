<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>个人中心——教学调查</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/user_center.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/hDate2.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/footer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Autoscoll.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_center.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/circle.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/banner.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hDate2.js"></script>
</head>
<body>
<!----教务事务新增弹出框--->
<div class="pop_msg_teaching_inquire_add">
	<div class="teaching_inquire_add_list_title">请勾选需要发布调查问卷的课程<img class="teaching_inquire_add_list_pop_close" src="${pageContext.request.contextPath}/images/exam/exam_pop_close.png" alt=""/></div>
    <div class="teaching_inquire_add_content">
    	 
    </div>
    <div class="teaching_inquire_add_submit_btn"><a >确认提交</a></div>
</div>
<div class="pop_msg_bg_teaching_inquire_add"></div>

<!----头部区域-->	
<!----头部区域-->	
<div class="inner_header">
    <jsp:include page="../head.jsp"></jsp:include>
</div>
<!---中间内容区域  开始---->
<div class="container">	
<div class="user_center_con">
<div class="user_center_con_info">
            <div class="user_ico" onClick="window.location.href='user_center.html'"><img src="images/user_center/user_img_01.jpg" alt=""/></div>
            <dl class="user_center_con_list">
                <dt>基本资料</dt>
                <dd>莫妮卡 Monica</dd>
                <dd>UID:123456789</dd>
                <dd>社区活跃度：中级</dd>
                <dd>收藏课程：12</dd>
            </dl>
        </div>
	<div class="user_center_content clearfloat">
    	<div class="user_center_con_left">
           <jsp:include page="../user/personalcenter-left.jsp"></jsp:include>
        </div>
        <div class="user_center_con_right">
         <div class="user_center_bread_tit"></div>
        	<div class="user_center_teaching_inquire">
            	<div class="user_center_teaching_inquire_title clearfloat">
                	<span>历史列表</span>
                	 <c:if test="${userType==1}">
                    <div class="user_center_teaching_inquire_add_con" onClick="teachingInquireAddPopCenter();">
                        <img class="user_center_teaching_inquire_add_ico" src="images/user_center/teaching_inquire_add_ico.png" alt=""/>
                        <span class="user_center_teaching_inquire_add_txt">新增调查问卷</span>
                    </div>
                     </c:if>
                </div>
                <div class="user_center_teaching_inquire_con">
                <c:if test="${surveylist.size()==0}">
                <div class="user_center_teaching_inquire_list">
                  <span>暂时还没有任何信息哦</span></div>
                  </c:if>
                   <c:if test="${surveylist.size()>0}">
                    <c:forEach items="${surveylist}" var="item">
                	<div class="user_center_teaching_inquire_list clearfloat">
                    	<div class="uc_tinquire_left"><img class="uc_tinquire_left_img" src="${pageContext.request.contextPath}${item.courseCoverUrl}" alt=""/></div>
                        <div class="uc_tinquire_txt_list_con">
                        	<div class="uc_tinquire_txt_list_title">${item.courseName}当前学员</div>
                        	<div class="uc_tinquire_txt_list clearfloat">
                            	<span>学习人数：</span>
                                <b>${item.userTotal}人</b>
                            </div>
                            <div class="uc_tinquire_txt_list clearfloat">
                            	<span>开始时间：</span>
                                <b><fmt:formatDate value="${item.courseStartTime}" pattern="yyyy-MM-dd" /></b>                  
                            	<span>结束时间：</span>
                                <b><fmt:formatDate value="${item.courseEndTime}" pattern="yyyy-MM-dd" /></b>
                            </div>
                            <c:if test="${userType==0}">
                             <div class="uc_tinquire_txt_list clearfloat">
                            	<span>问卷状态：</span>
                            	<b>
                            	<c:if test="${item.exsitUser==0}"> <span style="color:red;">未填写</span></c:if>
                                <c:if test="${item.exsitUser>0}"> <span>已填写</span></c:if>                
                            	</b>
                            	</div>
                            </c:if>
                            <div class="uc_tinquire_txt_list clearfloat">
                            	<span>课程满意度：</span>
                                <ul>
                                	<li></li>
                                    <li></li>
                                    <li></li>
                                    <li></li>
                                    <li></li>
                                </ul>
                            </div>
                        </div>
                        <c:if test="${userType==0}">
                        <div class="uc_tinquire_list_btn"><c:if test="${item.exsitUser==0}">              
                           <a onclick="surveydetail('${item.paperId}','${item.classId}','0','${item.userTotal}')">点击填写</a>
                           </c:if>
                           <c:if test="${item.exsitUser>0}">
                            <a onclick="surveydetail('${item.paperId}','${item.classId}','1','${item.userTotal}')">查看详情</a>
                           </c:if>
                    </div>
                    </c:if>
                    <c:if test="${userType==1}">
                    <div class="uc_tinquire_list_btn">
                     <a onclick="surveydetail('${item.paperId}','${item.classId}','1','${item.userTotal}')">查看详情</a>
                     </div>
                    </c:if>
                    </div>
                   </c:forEach>
                     </c:if>
                </div>
               
            </div>
        </div>
    </div>
    </div>
</div>
<!---底部区域---->
<div class="footer_con">
    <jsp:include page="../user/personalcenterbottom.jsp"></jsp:include>
</div>
<script type="text/javascript">
calendar.show({
	ok: function (ths) {
	    $.post("./cy/findcourseid.action",function(data){	
			if(data.length!=0)
			{
			  calendar.config.messageData=null;
			  for(var i=0;i<data.length;i++)
				  {
				     var s = data[i].chaptername;
				     var p=data[i].dayBefore;

				     if(ths.value==p)
				    	 {
				    	    calendar.config.messageData="<"+s+">"+"课程落后了，该抓紧时间学习了！";
				    	 }
				     else if(calendar.config.messageData!=null)
				    	 {
				    	     calendar.config.messageData+="<"+s+"  课程落后了，该抓紧时间学习了！";
				    	 }
				      
				  }			     
			}
		})		
	},
})
function teachingInquireAddPopCenter(){
	$('.pop_msg_teaching_inquire_add').css('display','block');
	$('.pop_msg_bg_teaching_inquire_add').css('display','block');
	var w_width = $(window).width();
	var w_height = $(window).height();
	var b_height = $(document.body).height();
	if(b_height<w_height){
		$('.pop_msg_bg_teaching_inquire_add').css('height',w_height);
		}else{
			$('.pop_msg_bg_teaching_inquire_add').css('height',b_height);
			}
	var w_self = $('.pop_msg_teaching_inquire_add').width();
	var h_self = $('.pop_msg_teaching_inquire_add').height();
	var _x = w_width/2 - w_self/2;
	var _y = w_height/2 - h_self/2;
	$('.pop_msg_teaching_inquire_add').css({left:_x,top:_y});
	$.ajax( {   
	    type : "POST",   
	    url : "${pageContext.request.contextPath}/survey/sendsurveycourse.action",
	    data : {},  
	    dataType: "json",   
	    success : function(data){
	    	var courselist = "";
	    	for(var i=0 ; i<data.length ; i++){
             courselist=courselist+"<div class='teaching_inquire_add_list clearfloatt'><div class='uc_tinquire_left'><img class='uc_tinquire_left_img'src='${pageContext.request.contextPath}"+data[i].courseCoverUrl+"'/></div>"+
             "<div class='uc_tinquire_txt_list_con'> <div class='uc_tinquire_txt_list_title'><span>课程名称："+data[i].courseName+"</span></div><div class='uc_tinquire_txt_list clearfloat'> <span>学习人数："+data[i].userTotal+"人</span></div>"+
             "<div class='uc_tinquire_txt_list clearfloat'><span>开始时间：</span> <b>"+new Date(parseInt(data[i].courseStartTime)).toLocaleDateString()+"</b></div>"+
             "<div class='uc_tinquire_txt_list clearfloat'><span>结束时间：</span> <b>"+new Date(parseInt(data[i].courseEndTime)).toLocaleDateString()+"</b></div>"+
             "</div>"+
             "<div class='uc_tinquire_list_chose_con'><span paperId='"+data[i].paperId+"'courseTerm='"+data[i].courseTerm+"'id='"+data[i].courseId+"'class='no_selected'onclick='chooseOption(this)'></span></div></div>";
	    	}		    	
	    	$(".teaching_inquire_add_content").html(courselist);
	    	},		
		    error :function(){   
		        alert("网络连接出错！");   
		    }   
		}); 
	}
function chooseOption(obj){
	var optionFlag = $(obj).attr("class");
	var courseId=$(obj).attr("id");
	var courseTerm=$(obj).attr("courseTerm");
	var paperId=$(obj).attr("paperId");
	if($(obj).hasClass('selected'))
	{
	 $(obj).removeClass('selected');
	}else
	 {
	  $(obj).addClass('selected');
	 }
	var list = new Array(); 
	$(".teaching_inquire_add_submit_btn").click(function(){
		$.ajax({
			type : "POST",
			 url : "${pageContext.request.contextPath}/survey/sendsurvey.action",
			data : {"courseId":courseId,
				   "courseTerm":courseTerm,
				   "paperId":paperId
				},
			 dataType: "json",
			success : function(data) {
				 if(data.success){   
			        alert("发送成功！");
			        $(".pop_msg_teaching_inquire_add").css('display','none');
			        $(".pop_msg_bg_teaching_inquire_add").css('display','none');
			        }   
			        else{   
			            alert("设置失败！");   
			        }   
			    },   
			error : function(jqXHR, textStatus) {
			util.error("操作失败请稍后尝试");
			}
		});
	});}
	//发送调查问卷
   /*
   function surveywrite(paperId,classId,state,userTotal)
	{
	alert("paperId"+paperId);
	
    window.location.href="${pageContext.request.contextPath}/survey/surveystudent-preview.action?paperId="+paperId+"&classId="+classId+"&state="+state+"&userTotal="+userTotal;
	 }
	*/
	function surveydetail(paperId,classId,state,userTotal){
		 window.location.href="${pageContext.request.contextPath}/survey/surveystudent-preview.action?paperId="+paperId+"&classId="+classId+"&state="+state+"&userTotal="+userTotal;
	}
	//调查问卷查看详情
</script>
</body>
</html>
