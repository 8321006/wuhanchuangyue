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
     <c:forEach items="${listmessage}" var="t">
   	               <div class="station_letter_info_list">
                    	<div class="station_letter_info_list_left">
                        	<span class="station_letter_info_list_img_con sletter_ilist_img_01"></span>
                            <p>${t.fsruser }</p>
                        </div>
                        <div class="station_letter_info_list_right">
                        	<div class="station_letter_info_list_txt"><a href="#" class="privatemessid" name="${t.messageId }">${t.message }</a></div>
                            <div class="station_letter_info_list_txttime"><span><fmt:formatDate value="${t.messagetime }" pattern="yyyy/MM/dd HH:mm:ss"/> </span></div>
                        </div>
                    </div>
     </c:forEach>    
</body>
<script type="text/javascript">
$(".privatemessid").click(function(){
	debugger
	var name=$(this).attr("name");
	$.ajax({
	     type: 'POST',
	     url: '${pageContext.request.contextPath}/msg/messageGetid.action?messageId='+name,
	     success:function (data) {
	    	$(".station_letter_right_send_message_zone").html("");
	    	var html="";
	    		 var obj =data[0];
	    		 html=html+'<div class="sletter_right_smessage_he clearfloat">';
	    		 html=html+'<table cellpadding="0" cellspacing="0" border="0">';
	    		 html=html+'<tr><td><div class="sletter_right_smessage_he_detail_left">';
	    		 html=html+'<span class="sletter_right_smessage_he_detail_left_img sletter_he_img_01"></span></div>';
	    		 html=html+'</td><td><div class="sletter_right_smessage_he_detail_right">'
	    		 html=html+'<div class="sletter_right_smessage_he_detail_righttxt">'+obj.message+'</div></div></td></tr>';    
	    		 html=html+'<tr><td><div class="sletter_right_smessage_he_name"><p>Jessica</p></div></td></tr></table></div>'
	    		 $(".station_letter_right_send_message_zone").html(html);
	     },
	     dataType: "json"
	});
});
</script>
</html>