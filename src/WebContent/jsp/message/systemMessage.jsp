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
                	<div class="station_letter_info_list">
                	  <c:forEach items="${listmessageadmin}" var="t">
                    	<div class="station_letter_info_list_sys">
                        	<div class="station_letter_info_sys_tit">系统消息</div>
                            <div class="station_letter_info_sys_tit_txt">                                                    
                                <div class="station_letter_info_list_systxt"><a href="#" class="messid" name="${t.messageId }">${t.message }</a></div>                             
                             <!-- 此方法暂时先注释掉，因为无法加载点击数据
                                <div class="station_letter_info_list_systxt"><a href="messageGetid.action?messageId=${t.messageId}" >${t.message }</a></div>
                             -->
                                <div class="station_letter_info_list_systxttime clearfloat"><span><fmt:formatDate value="${t.messagetime }" pattern="yyyy/MM/dd HH:mm:ss"/></span></div>
                            </div>
                        </div>  
                      </c:forEach>            
                    </div>    
</body>
<script type="text/javascript">
/**
$(".messid").click(function(){
	var name=$(this).attr("name");
	var url="./messageGetid.action?messageId="+name
			$.post(url,function(msg){			
			});
});
**/


$(".messid").click(function(){
	var name=$(this).attr("name");
	$.ajax({
	     type: 'POST',
	     url: '${pageContext.request.contextPath}/msg/messageGetid.action?messageId='+name,
	     success:function (data) {
	    	$(".station_letter_right_con2 display_none").html("");
	    	var html="";
	    		 var obj =data[0];
	    		 alert(obj.message);
	    		 alert(obj.messagetime);
	    		 html=html+'<div class="station_letter_right_tit_con">';
	    		 html+='<div class="station_letter_right_tit_txt">详情消息</div></div>';
	    		 html=html+'<div class="station_letter_right_sysinfo_zone">';
	    		 html=html+'<div class="station_letter_right_sysinfo_txt">'+obj.message+'</div>';
	    		 html=html+'<div class="station_letter_right_sysinfo_time clearfloat">';
	    		 html=html+'<span class="station_letter_right_sysinfo_time_detail">';
	    		 html=html+obj.messagetime;
	    		 html=html+'</span>'
	    		 html=html+'<b class="station_letter_right_sysinfo_time_from">来自：系统管理员</b></div></div>';    	 
	    		 $(".station_letter_right_con2 display_none").html(html);
	     },
	     dataType: "json"
	});
});



</script>
