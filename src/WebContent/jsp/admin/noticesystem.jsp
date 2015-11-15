<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>后台管理通知中心</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_houtai_public.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_new_list.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bg_houtai_index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bg_new_list.js"></script>
</head>
<body>
<!----新闻列表新增弹出框--->
<div class="pop_msg_bnew_list">
   <form id="noticeaddid" action="${pageContext.request.contextPath}/notice/noticeadd.action" method="post">
		<div class="bnew_list_title">发布新闻<img class="bnew_list_pop_close" src="${pageContext.request.contextPath}/images/houtai/new_list_pop_close.png" alt=""/></div>
	    <div class="bnew_list_content">
	    	<div class="bnew_list_link clearfloat">
	        	<div class="bnew_list_left_txt">新闻正题</div>
	            <div class="bnew_list_right">
	            	<input type="text" class="bnew_list_add_input width_680px" name="noticeTitle"id="noticeTitle"/>
	            </div>
	            <div class="checkbox_notice">
	            <span>温馨提示</span>
	            <i class="checkbox_notice_chosebox_selected" onclick="chooseOption(this);"id="noticeselectA"></i>
	             </div>
	            <div class="checkbox_notice">
	            <span style="color:#FF0000;">紧急通知</span>
	            <i class="checkbox_notice_chosebox"onclick="chooseOption(this);"id="noticeselectB"></i>
	            </div>
	        </div>
	        <div class="bnew_list_link clearfloat">
	        	<div class="bnew_list_left_txt">新闻正文</div>
	            <div class="bnew_list_right">
	            	<textarea class="bnew_list_add_title_textarea" name="noticeContent"id="noticeContent"></textarea>
	            </div>
	        </div>
	        <div class="bnew_list_link clearfloat">
	        	<div class="bnew_list_send_btn">
	        	<input type='hidden' id='editflag' name='editflag' />
        	  <input type='hidden' id='noticeId' name='noticeId' />
        	  <input type='hidden' id='noticeTitleBefore' name='noticeTitleBefore'/>
        	  <input type='hidden' id='noticeContentBefore' name='noticeContentBefore'/>
        	   <input type='hidden' id='noticeCharacterBefore' name='noticeCharacterBefore'/>
        	   <input type='hidden' id='noticeCharacter' name='noticeCharacter'value='0'/>
        	   <input type='hidden' id='noticeType' name='noticeType' value='1'/>
	           <span><a onClick="noticefb();">发&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp布</a></span>
	            </div>
	        </div>
	    </div>
    </form>
</div>
<div class="pop_msg_bg_bnew_list"></div>


<!----头部区域-->	
	<div class="inner_header">
		<jsp:include page="../head-school.jsp"/>
	</div>
<div class="houtai_con clearfloat">
<!---中间内容区域  开始---->
<jsp:include page="bg_left.jsp"/>

<!---中间内容区域  开始---->
        <div class="houtai_content_con">
        	<div class="houtai_right_con">
				<div class="houtai_bread_title">新闻列表</div>
				<div class="new_list_funtion_con">
                	<div class="new_list_function_detail clearfloat">
                    	<div class="news_add_con" onClick="newListAddPopCenter();">
                        	<img class="new_list_add_ico" src="${pageContext.request.contextPath}/images/houtai/new_list_add_ico.png" alt=""/>
                        	<span class="new_list_add_txt">新增</span>
                        </div>
                        <div class="news_search_con">
                        	<input type="text" class="news_search_input" id="findlikenotice" value="请输入要搜索的关键字" onclick="value=''"  onblur="this.value = this.value =='' ? '请输入要搜索的关键字' : this.value "/>
                            <div class="news_search_btn"onClick="findNotice();"><a>搜&nbsp&nbsp&nbsp索</a></div>
                        </div>
                    </div>
                	<table class="new_list_detail_table" cellpadding="0" cellspacing="0">
                        <thead>
                        	<tr>
                            	<td>主题</td>
                                <td>发布时间</td>
                                <td>浏览人数</td>
                                 <td>通知类型</td>
                                <td>操作</td>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${noticesysAlllist}" var="p">
                        	<tr>
                            	<td>${p.noticeTitle }</td>
                                <td><fmt:formatDate value="${p.noticeTime }" pattern="yyyy/MM/dd HH:mm:ss"/> </td>
                                <td>20000人</td>
                                <td>
                                <c:if test="${p.noticeCharacter==1}"> <span style="color:#FF0000;">紧急通知 </span></c:if>
                                <c:if test="${p.noticeCharacter==0}"> <span>温馨提示 </span></c:if>
                                </td>
                                <td >
                                <span class="new_list_detail_table_edit_notice" onClick="editNotice('${p.noticeId}','${p.noticeCharacter}');">编辑</span>
                                <span class="new_list_detail_table_del_notice"  onClick="delNotice('${p.noticeId}');">删除</span>
                                </td>
                            </tr>
                       </c:forEach> 
                        </tbody>
                    
                    </table>
	                <form action="${pageContext.request.contextPath}/notice/noticisyslist.action" id="searchform" name="searchform" method="post">
					<input type='hidden' id='courseId' name='courseId' value='${courseId}'/>
					<input type='hidden' id='courseName' name='courseName' value='${courseName}'/>
					<input type='hidden' id='searcharea' name='searcharea' value='${searcharea}'/>
					<input type='hidden' id='term' name='term' value='${term}'/>
					 <input type='hidden' id='noticeType' name='noticeType' value='1'/>
					</form>
         			<c:if test="${not empty noticesysAlllist}"> 			
         			<tags:pager pagerRange="10" pageSize="${page.pageSize}" totalPage="${page.pages}" curIndex="${page.pageNum}" formId="searchform"></tags:pager>              
           		 	</c:if> 
            		<c:if test="${empty noticesysAlllist}">
            		</c:if> 
                </div>
            </div>
        	
        </div>
</div>
</body>

<script type="text/javascript">
function delNotice(noticeId)
{
	if(confirm("确定删除该通知吗？"))
	  {
     window.location.href="${pageContext.request.contextPath}/notice/delNotice.action?noticeid="+noticeId+"&noticeType="+1;
	}
}

function editNotice(noticeId,noticeCharacter){
	 $("#noticeId").val(noticeId);
	 if(noticeCharacter==1){
		$("#noticeselectB").attr('class','checkbox_notice_chosebox_selected')
		$("#noticeselectA").attr('class','checkbox_notice_chosebox')
	 }
	$.ajax({
		type : "POST",
		 url : "${pageContext.request.contextPath}/notice/findtnotice.action",
		data : {"noticeId":noticeId},
		 dataType: "json",
		success : function(data) {
			  $("#noticeTitleBefore").val(data[0].noticeTitle);
			  $("#noticeTitle").val(data[0].noticeTitle);
			  $("#editflag").val(1);
			  $("#noticeCharacterBefore").val(noticeCharacter);
			  $("#noticeCharacter").val(noticeCharacter);
			  $("#noticeContent").val(data[0].noticeContent);
			  $("#noticeContentBefore").val(data[0].noticeContent);
		    },   
		error : function(jqXHR, textStatus) {
			util.error("操作失败请稍后尝试");
		}
	});
	  newListAddPopCenter();
	 
	 
	//window.location.href="${pageContext.request.contextPath}/notice/delsysnotice.action?noticeid="+noticeId;
		
}

   function findNotice()
   {
	   var noticelike=document.getElementById("findlikenotice").value;
	   window.location.href=encodeURI(encodeURI("${pageContext.request.contextPath}/notice/findlikenotice.action?noticett="+noticelike));
   }
   function noticefb()
   {
	 //先进行判断，确保新闻主题和新闻正文都不为空
		var noticeId=$("#noticeId").val();
		var noticeTitle=$("#noticeTitle").val();
		var noticeContent=$("#noticeContent").val();
		var editflag=$("#editflag").val();
		var noticeTitleBefore=$("#noticeTitleBefore").val();
		var noticeCharacter= $("#noticeCharacter").val();
		var noticeCharacterBefore= $("#noticeCharacterBefore").val();
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
				if(noticeContent==noticeContentBefore&&noticeTitle==noticeTitleBefore&&noticeCharacter==noticeCharacterBefore)
			{
				if(confirm("确定不修改吗？")){
					$('.pop_msg_bnew_list').css('display','none');
					$('.pop_msg_bg_bnew_list').css('display','none');
				}
					  
			}else{
				if(confirm("确定修改吗？")){
					window.location.href="${pageContext.request.contextPath}/notice/deitsysnotice.action?noticeId="+noticeId+"&noticeTitle="+noticeTitle+"&noticeContent="+noticeContent+"&noticeCharacter="+noticeCharacter+"&noticeType="+1;
					$(".pop_msg_bg_bnew_list").css('display','none');
				}
			}
			}else{
			  noticeaddid.submit();
			  $(".pop_msg_bnew_list").close();
			}
   }}
   
	/*****弹出框关闭****/
	 $('.bnew_list_pop_close').click(function(){
		 $('.pop_msg_bnew_list').css('display','none');
		 $('.pop_msg_bg_bnew_list').css('display','none');
		 $('.bnew_list_content').css('display','none');		 
	 });
	
		function chooseOption(obj){
			var optionFlag = $(obj).attr("class");
				$(obj).parent().parent().find(".checkbox_notice_chosebox_selected").each(function(){	
					$(this).attr("class","checkbox_notice_chosebox");
				});
				if(optionFlag == "checkbox_notice_chosebox"){
					$(obj).attr("class","checkbox_notice_chosebox_selected");
				}	
				if($("#noticeselectB").attr("class")=='checkbox_notice_chosebox_selected'){
					 $("#noticeCharacter").val(1);
				}
				if($("#noticeselectA").attr("class")=='checkbox_notice_chosebox_selected'){
					 $("#noticeCharacter").val(0);
				}
		}
		//后台左边菜单被选中
		 $("#leftbar").find("li").each(function(){
				$(this).removeClass();
			});
		   $("#news_notice ol").removeClass('display_none');
			$("#newsSet").attr("class","selected");
</script>

</html>
