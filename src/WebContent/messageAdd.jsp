<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<form action="./messageadd.action" method="post">
    <table>

        <tr>
            <td>���͸�: </td>  
            <td>
                <input type="text" name="user" />
            </td>
        </tr>

        <tr> 
            <td>����: </td>
            <td>
                <textarea rows="7" cols="60" name="message"></textarea>
            </td>
        </tr>


        <tr>
            <td><input type="submit" value="˽��"/></td>
        </tr>

    </table>

</form>

<br><br>



</body>
</html>