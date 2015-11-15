<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>平台后台</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/medioadaption.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_administrator_public.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_administrator_newsnotice.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bg_administrator_index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bg_administrator_newsnotice.js"></script>
</head>
<body>

<!----新闻通知新增弹出框--->
<form id="noticesystemadd" action="${pageContext.request.contextPath}/notice/noticesystemadd.action" method="post">
<div class="pop_msg_administrator_newsnotice">
	<div class="admin_newsnotice_title">发布新闻<img class="admin_newsnotice_pop_close" src="${pageContext.request.contextPath}/images/houtai/new_list_pop_close.png" alt=""/></div>
    <div class="admin_newsnotice_content">
    	<div class="admin_newsnotice_link clearfloat">
        	<div class="admin_newsnotice_left_txt">新闻正题</div>
            <div class="admin_newsnotice_right">
            	<input type="text" class="admin_newsnotice_add_input width_680px" id="noticeTitle"name="noticeTitle"/>
            </div>
        </div>
        <div class="admin_newsnotice_link clearfloat">
        	<div class="admin_newsnotice_left_txt">新闻正文</div>
            <div class="admin_newsnotice_right">
            	<textarea class="admin_newsnotice_add_title_textarea" id="noticeContent"name="noticeContent"></textarea>
            </div>
        </div>
        <div class="admin_newsnotice_link clearfloat">
        	<div class="admin_newsnotice_send_btn_center">
        	<input type='hidden' id='editflag' name='editflag' />
        	<input type='hidden' id='noticeId' name='noticeId' />
        	<input type='hidden' id='noticeTitleBefore' name='noticeTitleBefore'/>
        	<input type='hidden' id='noticeContentBefore' name='noticeContentBefore'/>
            	<span><a onClick="noticesysfb();">发布</a></span>
                <b>取消</b>
            </div>
        </div>
    </div>
</div>
</form>
<div class="pop_msg_bg_administrator_newsnotice"></div>




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
                    <li>
                        <span>
                            <b class="administrator_ico_5"></b>
                           <a href="${pageContext.request.contextPath}/survey/coursesurveylist.action"> <i>教学调查</i></a>
                        </span> 
                    </li>
                    <li class="curr">
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
                	<span class="administrator_add_icontxt" onClick="admNewsNoticePopCenter();">新闻通知</span>
                </div> 
                <div class="admin_newsnotice_funtion_con">
                	<div class="admin_newsnotice_function_detail clearfloat">
                        <div class="news_search_con">
                        	<input type="text" class="news_search_input body_input" id="findlikenotice" value="请输入要搜索的关键字" onclick="value=''"  onblur="this.value = this.value =='' ? '请输入要搜索的关键字' : this.value "/>
                            <div class="news_search_btn"><a onClick="findsystemNotice();">搜&nbsp;&nbsp;&nbsp;索</a></div>
                        </div>
                    </div>
                	<table class="admin_newsnotice_detail_table" cellpadding="0" cellspacing="0">
                          <thead>
                        	<tr>
                            	<td>主题</td>
                                <td>发布时间</td>
                                <td>浏览人数</td>
                                <td>操作</td>
                            </tr>
                        </thead>
                        <tbody>
                        <c:if test="${noticesystemAlllist.size()==0}">
                        <tr>
                        <td colspan="4">暂无通知信息</td>
                        </tr>
                         </c:if>
                        <c:if test="${noticesystemAlllist.size()>0}">
                        <c:forEach items="${noticesystemAlllist}" var="p">
                        	<tr>                        	
                            	<td>${p.noticeTitle }</td>
                            	<td><fmt:formatDate value="${p.noticeTime}" pattern="yyyy-MM-dd" /></td>                                
                                <td>200000人</td>
                                <td>
                                	<span class="admin_newsnotice_table_edit_notice" onClick="editNotice('${p.noticeId}','${p.noticeTitle}','${p.noticeContent}');">编辑</span>
                                    <span class="admin_newsnotice_table_del_notice" onClick="delNotice('${p.noticeId}');">删除</span>
                                </td>
                            </tr>
                       </c:forEach>
</c:if>
                        </tbody>
                    
                    </table>
                    <form action="${pageContext.request.contextPath}/notice/gosystemNotice.action" id="searchform" name="searchform" method="post">
					<input type='hidden' id='courseId' name='courseId' value='${courseId}'/>
					<input type='hidden' id='courseName' name='courseName' value='${courseName}'/>
					<input type='hidden' id='searcharea' name='searcharea' value='${searcharea}'/>
					<input type='hidden' id='term' name='term' value='${term}'/>
					</form>
         			<c:if test="${not empty noticesystemAlllist}"> 			
         			<tags:pager pagerRange="10" pageSize="${page.pageSize}" totalPage="${page.pages}" curIndex="${page.pageNum}" formId="searchform"></tags:pager>              
           		 	</c:if> 
            		<c:if test="${empty noticesystemAlllist}">
            		</c:if> 
                </div>
            </div>
        </div>
    </div>
    </div>
	

</body>



<script type="text/javascript">
function noticesysfb()
{	
	//先进行判断，确保新闻主题和新闻正文都不为空
	var noticeId=$("#noticeId").val();
	var noticeTitle=$("#noticeTitle").val();
	var noticeContent=$("#noticeContent").val();
	var editflag=$("#editflag").val();
	var noticeTitleBefore=$("#noticeTitleBefore").val();
	var noticeContentBefore=$("#noticeContentBefore").val();
	var mesg="";
	if(noticeTitle==null||noticeTitle==''){
		mesg+="新闻主题不能为空！";
	}
	if(noticeContent==null||noticeContent==''){
		mesg+="新闻正文不能为空！";
	}
	if(mesg!=null&&mesg!=''){
		alert(mesg);
	}
	else{
		if(editflag==1){
			//判断用户是否编辑
			if(noticeContent==noticeContentBefore&&noticeTitle==noticeTitleBefore)
		{
			if(confirm("确定不修改吗？")){
				$('.pop_msg_administrator_newsnotice').css('display','none');
				$('.pop_msg_bg_administrator_newsnotice').css('display','none');
			}
				  
		}else{
			if(confirm("确定修改吗？")){
				window.location.href="${pageContext.request.contextPath}/notice/deitsysnotice.action?noticeId="+noticeId+"&noticeTitle="+noticeTitle+"&noticeContent="+noticeContent+"&noticeCharacter="+0+"&noticeType="+2;
				$(".pop_msg_administrator_newsnotice").css('display','none');
			}
		}
		}else{
		noticesystemadd.submit();
		$(".pop_msg_administrator_newsnotice").close();
		}
	}
}
function closefb()
{
	$(".pop_msg_administrator_newsnotice").hide();
}
function findsystemNotice()
{
	   var noticelike=document.getElementById("findlikenotice").value;
	   window.location.href=encodeURI(encodeURI("${pageContext.request.contextPath}/notice/findlikesystemnotice.action?noticett="+noticelike));
}
function delNotice(noticeId)
{
	if(confirm("确定执行该程序？"))
	  {
		window.location.href="${pageContext.request.contextPath}/notice/delsysnotice.action?noticeid="+noticeId;
		}
}
function editNotice(noticeId,noticeTitle,noticeContent){
	  admNewsNoticePopCenter();
	  $("#noticeId").val(noticeId);
		$.ajax({
			type : "POST",
			 url : "${pageContext.request.contextPath}/notice/findtnotice.action",
			 data : {"noticeId":noticeId},
			 dataType: "json",
			 success : function(data) {
				  $("#noticeTitleBefore").val(data[0].noticeTitle);
				  $("#noticeTitle").val(data[0].noticeTitle);
				  $("#editflag").val(1);
				  $("#noticeContent").val(data[0].noticeContent);
				  $("#noticeContentBefore").val(data[0].noticeContent);
			    },   
			error : function(jqXHR, textStatus) {
				util.error("操作失败请稍后尝试");
			}
		});
		  newListAddPopCenter();
		
}
$('.admin_newsnotice_send_btn_center b').click(function(){
	$('.pop_msg_administrator_newsnotice').css('display','none');
	$('.pop_msg_bg_administrator_newsnotice').css('display','none');
	$('.admin_newsnotice_content').css('display','block');
});

</script>


</html>