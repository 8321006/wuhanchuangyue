<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%> 
<%@page import="com.cy.model.User"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学生管理 - 易启学后台管理</title>
<link type="text/css" rel="stylesheet" href="css/basic.css"/>
<link type="text/css" rel="stylesheet" href="css/medioadaption.css"/>
<link type="text/css" rel="stylesheet" href="css/bg_administrator_public.css"/>
<link type="text/css" rel="stylesheet" href="css/bg_administrator_addstudent.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_administrator_teaching_inquire.css"/>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bg_administrator_index.js"></script>
<%
User current = (User)session.getAttribute("user");
if(current == null || current.getUserType() != 3){
	response.sendRedirect(basePath + "index.action");
}
%>
</head>
<body>

<!---中间内容区域  开始---->
<div class="administrator_container">	
	<div class="bg_administrator_con">
    	<div class="administrator_left_menu">
        	<div class="administrator_img_con">
            	<div class="administrator_img_content">
                	<img class="administrator_person_img" src="images/administrator/person_img_01.png" alt=""/>
                </div>
                <p class="administrator_person_name">${user.realName }</p>
            </div>
            <div class="administrator_left_menu_nav">
            	<ul class="administrator_menu_nav">
                    <li>
                        <span>
                            <b class="administrator_ico_1"></b>
                            <a href="university/listAll.action"><i>学校管理</i></a>
                        </span> 
                    </li>
                    <li>
                        <span>
                            <b class="administrator_ico_2"></b>
                           <a href="course/courseList.action"><i>新增课程</i></a>
                        </span> 
                    </li>
                    <li class="curr">
                        <span>
                            <b class="administrator_ico_3"></b>
                            <a href="cy/userImportList.action"><i>学生管理</i></a>
                        </span> 
                    </li>
                    <li>
                        <span>
                            <b class="administrator_ico_4"></b>
                            <a href="university/goAnalysis.action"><i>统计分析</i></a>
                        </span> 
                    </li>
                    <li>
                        <span>
                            <b class="administrator_ico_5"></b>
                              <a href="survey/coursesurveylist.action"><i>教学调查</i></a>
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
                	<span>新增学生</span>
                </div>
            <div class="administrator_addstudent_con">   
      <form id="importUser" action="cy/xkdr.action" method="post" enctype="multipart/form-data">
         
                	<div class="administrator_addstudent_upload_zone clearfloat">
                        <div class="administrator_addstudent_upload_looksearch_btn">浏览
         <input type="file" id="file" name="file" class="administrator_addstudent_upload_img_zone_filebtn" name="file"  onchange="document.getElementById('textfield').value=this.value"/>
         </div>
         
         <input type='text' name='textfield' id='textfield' class='administrator_addstudent_upload_img_zone_file' /> 
                        <span class="administrator_addstudent_upload_btn">上传</span>
                         <span class="administrator_addstudent_message">${message}</span>
       </div>
      </form>       
                  
                  
                    <table class="administrator_addstudent_table" cellpadding="0" cellspacing="0">
                        <thead>
                        	<tr>
                            	 <td>学校</td>
                            	 <td>课程名称</td>
                            	 <td>学期</td>
                                <td>状态</td>
                            </tr>
                        </thead>
                        <tbody>
                        <c:if test="${courselist.size()==0 }">
                         <tr>
                        <td colspan="4">暂无相关信息</td>
                        </tr>
                        </c:if>
                        <c:forEach items="${courselist}" var="item">
                        	<tr>
                            	 <td>${item.universityName}</td>
                            	 <td>${item.courseName}</td>
                            	 <td>${item.courseTerm}</td>
                                 <td>
                                 <c:if test="${item.courseStart==0}">暂未开课</c:if>
                                 <c:if test="${item.courseStart==1}"><span style="color:#f26a6a;"><b>已开课</b></span></c:if>
                                 </td>
                            </tr>
                          </c:forEach>
                        </tbody>
                    <form action="${pageContext.request.contextPath}/cy/userImportList.action" id="searchform" name="searchform" method="post">
                     </form>
                    <c:if test="${not empty courselist}"> 			
         			<tags:pager pagerRange="10" pageSize="${page.pageSize}" totalPage="${page.pages}" curIndex="${page.pageNum}" formId="searchform"></tags:pager>              
           		 	</c:if> 
            		<c:if test="${empty courselist}">
            		</c:if> 
                    </table>
                   
                </div>
            </div>
        </div>
    </div>
	
</div>

</body>
<!--  
<script src="${pageContext.request.contextPath}/js/jquery-1.9.1.min.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/webuploader/js/webuploader.js"></script>
<script type="text/javascript">
$(function(){
	// 实例化
    var uploader = WebUploader.create({

	    // swf文件路径
	    swf: "${pageContext.request.contextPath}/webuploader/js/Uploader.swf",

	    // 文件接收服务端。
	    server: '${pageContext.request.contextPath}/cy/xkdr.action',

	    // 选择文件的按钮。可选。
	    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
	    pick: '.administrator_addstudent_upload_looksearch_btn',

	    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
	    resize: false
	});

    uploader.on( 'uploadProgress', function( file, percentage ) {
        var $li = $( '#'+file.id ),
            $percent = $li.find('.progress .progress-bar');

        // 避免重复创建
        if ( !$percent.length ) {
            $percent = $('<div class="progress progress-striped active">' +
              '<div class="progress-bar" role="progressbar" style="width: 0%">' +
              '</div>' +
            '</div>').appendTo( $li ).find('.progress-bar');
        }

        $li.find('p.state').text('上传中');

        $percent.css( 'width', percentage * 100 + '%' );
    });
	
	$(".administrator_addstudent_upload_btn").click(function(){
		uploader.upload();
	});
	
	
});
</script>
-->

<script type="text/javascript">

	$(".administrator_addstudent_upload_btn").click(function(){
		
		if($("#textfield").val()=="")
			{
				alert("请选择上传文件");
			}
		else{
			$("#importUser").submit();
		
		   // alert("上传文件成功");
		   }
		
	});

	</script>
</html>