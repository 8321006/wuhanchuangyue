<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>  
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>后台管理——学生列表</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_houtai_public.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_new_list.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/user_center.css"/>
<link type="text/css" rel="stylesheet" href="css/study_report.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bg_houtai_index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bg_new_list.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_center.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/circle.js"></script>
<script src="${pageContext.request.contextPath}/js/highcharts.js" type="text/javascript"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/hDate2.js"></script>
</head>
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

.term_chose_content_detail{
    float:left;
    width:130px;
    height:28px;
    line-height:28px;
    color:#333;
    background:#fff;
    text-align:left;
    text-indent:15px;
    border:1px solid #ccc;
    font-size:12px;
    cursor:pointer;
}

.term_chose_content_downclick{
    float:left;
    width:120px;
    height:28px;
    text-align:center;
    border:1px solid #ccc;
    cursor:pointer;
}

.term_chose_content_downclick img.term_chose_arrow_option{
    width:10px;
    height:7px;
}

.term_chose_txt{
    float:left;
    width:95px;
    height:30px;
    line-height:30px;
    background:#6ad7f2;
    color:#fff;
    text-align:center;
    font-size:12px;
}
}
</style>
<body>
<!----头部区域-->    
<div class="inner_header">
    <jsp:include page="../head-school.jsp"/>
</div>
<div class="houtai_con clearfloat">

<!---中间内容区域  开始---->
<jsp:include page="bg_left.jsp"/>
            <div class="houtai_content_con">
            <div class="houtai_right_con">
                <div class="houtai_bread_title">课程资料列表</div>
                <div class="new_list_funtion_con">
                    <div class="new_list_function_detail clearfloat">
                            <div class="term_chose_con">
                                <div class="term_chose_content_downclick">
                                <select name = "course" onChange = "jump()" class = "term_chose_content_downclick" >
                                        <c:forEach items="${courses}"  var="p">
                                        <option <c:if test="${p.courseId ==courseId}">  selected</c:if> value = "${p.courseId}" > ${p.courseName}</option>
                                          </c:forEach>
                                        </select>  
                            </div>
                            <div class="term_chose_txt">课程名</div>
                        </div>
                    </div>
            <div class = "user_center_course_data">
        <div class = "user_center_course_data_detail user_center_course_data_tabcon1">
            <table class="user_center_course_data_table" cellpadding="0" cellspacing="0">
                <thead>
                    <tr>
                        <td>文件名称</td>
                        <td>文件大小</td>
                        <td>文件上传日期</td>
                        <td>文件上传人</td>
                        <td>课程学年</td>
                        <td>操作</td>
                    </tr>
                </thead>
            <tbody>
                <tr>
                    <c:forEach items="${courseData}"  var="file">
                        <tr>
                            <td>${file.fileName}</td>
                            <td>
                                <c:if test="${file.fileSize < '1024'}">${file.fileSize}KB</c:if>
                                <c:if test="${file.fileSize > '1024'}"><fmt:formatNumber type="number" value="${file.fileSize / 1024} " maxFractionDigits="2"/> M</c:if>
                            </td>
                            <td><fmt:formatDate value="${file.fileTime}" pattern="yyyy-MM-dd  HH:mm" /></td>
                            <td>${file.fileUpload}</td>
                             <td>${file.courseTerm}</td>
                            <td class='download' path='${file.filePath}' ><span class="user_center_course_data_download">下载</span></td>
                </tr>
                    </c:forEach>
                  </tr>
            </tbody>
        </table>
         </div>
         </div>
    </div>
    </div>
    </div>
    </div>
</body>
<form action="${pageContext.request.contextPath}/coursedata/coursedatalist.action" id="dataform"  name="dataform" method="POST">
<input type="hidden" name="thecourseId" id="thecourseId" value="">
</form>
<form action="${pageContext.request.contextPath}/coursedata/download.action" id="downloadform"  name="downloadform" method="POST">
<input type="hidden" name="path" id="path" value="">
</form>
<script type="text/javascript">
/* Jquery Click事件,最后用户提交表单 */
$(".download").click(function(){
    $("#path").val($(this).attr("path"));
    $("#downloadform").submit();
})
</script>
<script>
/*****课程资料下拉列表选择******/
function jump(){
     //获取被选中的option标签
     var txtVal= $('select  option:selected').val();
     $("#thecourseId").val(txtVal);
     $("#dataform").submit();
    }
</script>
</html>
