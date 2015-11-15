<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件-列表</title>
</head>
<body>
<h1><a href="${pageContext.request.contextPath }/more.jsp">上传文件</a></h1><br>
	<table border="1" cellpadding="10" cellspacing="0">
		<tr>
			<th>编号</th>
			<th>文件id</th>
			<th>文件名</th>
			<th>文件大小</th>
			<th>创建时间</th>
			<th>上传人</th>
			<th>文件备注</th>
			<th>Download</th>
			<th>Delete</th>
		</tr>
		<c:forEach items="${files }" var="file" varStatus="status">
			<tr>
				<td>${status.index+1 }</td>
				<td>${file.fileId }</td>
				<td>${fn:substring(file.fileName,33,-1) }</td>
				<td>${file.fileSize }</td>
				<td><fmt:formatDate value="${file.fileTime }" type="date" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>${file.fileUpload }</td>
				<td>${file.fileRemark }</td>
				<td><a href="${pageContext.request.contextPath }/file/filedown.action?fileId=${file.fileId}">Download</a></td>
				<td><a href="${pageContext.request.contextPath }/file/filedelete.action?fileId=${file.fileId}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>