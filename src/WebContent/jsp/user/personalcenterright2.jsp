<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>个人中心</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/user_center.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/study_report.css"/>
<link type="text/css" rel="stylesheet"href="${pageContext.request.contextPath}/css/hDate2.css"  />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/study_report.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_center.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/circle.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts.js" ></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>


<style type="text/css">
table.hovertable {
	font-family: verdana,arial,sans-serif;
	font-size:11px;
	color:#333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: separate;
}
table.hovertable th {
	background-color:#c3dde0;
	
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
table.hovertable tr {

	background-color:#d4e3e5;
}
table.hovertable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
</style>

<style>
.school_info_table{
	width:100%;
}
.school_info_table tr td{
	padding:12px 0;
}
.school_info_table thead tr td:nth-child(1){
	width:25%;
	text-align:center;
	border-left:1px solid #3b7f92;
	border-top:1px solid #3b7f92;
	border-bottom:1px solid #3b7f92;
}
.school_info_table thead tr td:nth-child(2){
	width:15%;
	text-align:center;
	border:1px solid #3b7f92;
}
.school_info_table thead tr td:nth-child(3){
	width:25%;
	text-align:center;
	border:1px solid #3b7f92;
}
.school_info_table thead tr td:nth-child(4){
	width:10%;
	text-align:center;
	border:1px solid #3b7f92;
}
.school_info_table thead tr td:nth-child(5){
	width:8%;
	text-align:center;
	border:1px solid #3b7f92;
}

.school_info_table tbody tr td:nth-child(1){
	width:25%;
	text-align:center;
	border-left:1px solid #323232;
	border-right:1px solid #323232;
	border-bottom:1px solid #323232;
}
.school_info_table tbody tr td:nth-child(2){
	width:15%;
	text-align:center;
	border-right:1px solid #323232;
	border-bottom:1px solid #323232;
}
.school_info_table tbody tr td:nth-child(3){
	width:25%;
	text-align:center;
	border-right:1px solid #323232;
	border-bottom:1px solid #323232;
}
.school_info_table tbody tr td:nth-child(4){
	width:10%;
	text-align:center;
	border-right:1px solid #323232;
	border-bottom:1px solid #323232;
}

.school_info_table tbody tr td:nth-child(5){
	width:8%;
	text-align:center;
	border-right:1px solid #323232;
	border-bottom:1px solid #323232;
}
.school_info_table thead tr td{
	background:#46d6ff;
	color:#fff;
}


</style>
</head>
<body>

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
        	<div class = "user_center_course_data">
        		  <ul class="user_center_course_data_tab clearfloat">
        			<c:forEach items="${courses}"  var="p">
        				<li id="courseNameNew"onclick="filelist('${p.courseId}');"<c:if test="${p.courseId == courseId }">class='selected'</c:if> courseid="${p.courseId}">${p.courseName}</li>
		  			</c:forEach>
		  			</ul>
        <div class = "user_center_course_data_detail user_center_course_data_tabcon1">
        	<table class="user_center_course_data_table" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<td>文件名称</td>
						<td>文件大小</td>
						<td>文件上传日期</td>
						<td>文件上传人</td>
						<td>操作</td>
					</tr>
				</thead>
			<tbody id="file_list">
				<tr id="data">
				<c:if test="${empty courseData}">
				  <tr>
				<td colspan="5">暂无相关资料</td></tr>
				</c:if>
				<c:if test="${not empty courseData}">
					<c:forEach items="${courseData}"  var="file">
						<tr>
							<td >${file.fileName}</td>
							<td >
								<c:if test="${file.fileSize < '1024'}">${file.fileSize}KB</c:if>
								<c:if test="${file.fileSize > '1024'}"><fmt:formatNumber type="number" value="${file.fileSize / 1024} " maxFractionDigits="2"/> M</c:if>
							</td>
							<td id="fileTime"><fmt:formatDate value="${file.fileTime}" pattern="yyyy-MM-dd  HH:mm" /></td>
							<td id="fileUpload">${file.fileUpload}</td>
							<td class='download' path='${file.filePath}'><span class="user_center_course_data_download">下载</span></td>
				</tr>
					</c:forEach></c:if>
		  		</tr>
			</tbody>
		</table>
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
<form action="${pageContext.request.contextPath}/coursedata/detailcourse.action" id="dataform"  name="dataform" method="POST">
<input type="hidden" name="thecourseId" id="thecourseId" value="">
</form>
<form action="${pageContext.request.contextPath}/coursedata/download.action" id="downloadform"  name="downloadform" method="POST">
<input type="hidden" name="path" id="path" value="">
</form>
</body>
<script type="text/javascript">
/*****课程资料tab切换******/
$('.user_center_course_data_tab li').click(function(){
	 // alert("11");
	  var index=$(this).index();
	  $('.user_center_course_data_tab li').removeClass("selected");
	  $(this).addClass('selected');
	  $("#thecourseId").val($(this).attr("courseId"));
  });
   function filelist(courseId){
	   $.ajax({   
		    type : "POST",   
		    url : "${pageContext.request.contextPath}/coursedata/detailcourse.action",
		    data : {
		    	"courseId" :courseId
		     },  
		    dataType: "json",   
		    success : function(data){
		    	var courselist = "";
		    	for(var i=0 ; i<data.length ; i++){
		    		if(data[i].fileSize>1024){
		    		var fileSize=data[i].fileSize/1024;
		    		fileSize1= Math.round(fileSize * 100) / 100;
		    		courselist = courselist + "<tr id='data'><td>"+data[i].fileName+"</td><td>"+fileSize1+"M</td><td>"+new Date(parseInt(data[i].fileTime)).toLocaleDateString()+"</td><td></td><td class='download' path='"+data[i].filePath+"'><span id='"+data[i].filePath+"'class='user_center_course_data_download' onclick='upload(this)'>下载</span></td></tr>";
		    		}
		    		if(data[i].fileSize<1024){
			    	var fileSize=data[i].fileSize;
			    	courselist = courselist + "<tr id='data'><td>"+data[i].fileName+"</td><td>"+fileSize1+"KB</td><td>"+new Date(parseInt(data[i].fileTime)).toLocaleDateString()+"</td><td></td><td class='download' path='"+data[i].filePath+"'><span id='"+data[i].filePath+"'class='user_center_course_data_download' onclick='upload(this)'>下载</span></td></tr>";
			    	}
		    	}
		    	if(data.length==0){
		    		courselist+="<tr id='data'style='text-align:center;'><center><td colspan='5'>暂无相关资料</td></center></tr>";	
		    	}
		    		$("#file_list").html(courselist);
		    	},		
			    error :function(){   
			        alert("网络连接出错！");   
			    }   
			}); 
   }
   function upload(obj){
	   var filePath=$(obj).attr("id");
	   $("#path").val(filePath);
	   $("#downloadform").submit();
	}
/* Jquery Click事件,最后用户提交表单 */
 $(".download").click(function(){
	$("#path").val($(this).attr("path"));
	$("#downloadform").submit();
})
$("#leftbar").find("li").each(function(){
	$(this).removeClass();
});
$("#data").attr("class","curr");
</script>
</html>
