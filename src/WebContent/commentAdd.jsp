<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="gbk"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form action="./commentAdd.action" method="post">

<table>
    <tr>  
            <td>
		        <c:forEach items="${list}" var="t">
		
		            <tr>
		                <td >${t.commentContent}</td>
		                <td >${t.commenterName}</td>
		                <td >${t.commentTime}</td>
		                <td style="border: hidden;"></td>
		            </tr>
		
		        </c:forEach>
            </td>
    </tr>
</table>

    <table>

        <tr> 
            <td>ÄÚÈÝ: </td>
            <td>
                <textarea rows="7" cols="60" name="commentContent"></textarea>
            </td>
        </tr>


        <tr>
            <td><input type="submit" value="ÆÀÂÛ"/></td>
        </tr>

    </table>

</form>

<br><br>



</body>
</html>