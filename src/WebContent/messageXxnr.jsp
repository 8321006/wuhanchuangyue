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
<form>

    <table border="1" cellpadding="10" cellspacing="0">

        <tr>
            <td width="100%">Ë½ĞÅÄÚÈİ:</td>
            <td style="border: none"></td>
        </tr>

        <c:forEach items="${messageGetidList}" var="t">

            <tr>
                <td width="100%">${t.message }</td>
                <td style="border: hidden;"></td>
            </tr>

        </c:forEach>
 

    </table>

</form>

<br><br>



</body>
</html>