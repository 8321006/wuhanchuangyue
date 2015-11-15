<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文件-上传</title>
</head>
<body>
<h1><a href="${pageContext.request.contextPath }/file/file.action">文件列表</a></h1><br>
	<form action="${pageContext.request.contextPath }/file/fileadd.action" enctype="multipart/form-data" method="post">
		<table>
			<tr>
				<td>文件说明:</td>
				<td><input type="text" name="desc" value="文件上传测试"></td>
			</tr>
			<tr>
				<td>选择文件:</td>
				<td><input type="file" id="file" name="file"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="上传"></td>
			</tr>
		</table>
	</form>
</body>
</html>