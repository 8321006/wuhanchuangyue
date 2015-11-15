<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>后台管理——上传学生名单</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_houtai_public.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_new_list.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bg_houtai_index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bg_new_list.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_student_list.css"/>
</head>
<body>

<!----头部区域-->	
<div class="bg_houtai_header clearfloat">
	<jsp:include page="../head.jsp"/>
</div>
<div class="houtai_con clearfloat">
<jsp:include page="bg_left.jsp"/>

<!---中间内容区域  开始---->
<div class="container">	

        <div class="houtai_content_con">
			<form id="drxsmd" action="./xkdr.action" method="post" enctype="multipart/form-data">
        	<div class="houtai_right_con">
				<div class="houtai_bread_title">上传学生名单</div>
                <div class="student_list_upload_zone clearfloat">
                	<div class="slist_upload_looksearch_btn">浏览
                        <input type="file" class="pers_upload_img_zone_filebtn" name="file"  onchange="document.getElementById('textfield').value=this.value"/>
                    </div>
                    <input type='text' name='textfield' id='textfield' class='pers_upload_img_zone_file' /> 
                    <span class="student_list_upload_btn"><a onClick="importfunc();">上&nbsp&nbsp&nbsp&nbsp传</a></span>
                </div>
            </div>
			</form>       	
        </div>
</div>
</div>
</body>

<script type="text/javascript">
    function importfunc()
    {
    	drxsmd.submit();
    }
</script>
</html>
