<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<img class="pop_msg_notice_close" src="${pageContext.request.contextPath}/images/exam/exam_pop_close.png" alt=""/>
    <div class="pop_msg_notice_innercon">
    <c:forEach items="${findBynoticedetail}" var="t">
    	<div class="pop_msg_notice_title">${t.noticeTitle }</div>
        <div class="pop_msg_notice_txtcon">
        	<div class="pop_msg_notice_wenhou_txt">${t.noticeContent}</div>
        </div>
        <div class="pop_msg_notice_time clearfloat">
        	<span class="pop_msg_notice_time_detail"><fmt:formatDate value="${t.noticeTime }" pattern="yyyy/MM/dd HH:mm:ss"/></span>
            <b class="pop_msg_notice_time_from">来自：<span>不拉不拉客服</span></b>
        </div>
    </c:forEach>
    </div>
</body>

<script type="text/javascript">

</script>

</html>