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
  <form id="mess">
               	<div class="recevice_search_zone">
                	<span class="recevice_search_zone_txt">收信人</span>
                    <div class="recevice_search_input_zone">
                    	<input type="text" name="user" id="userid" class="recevice_search_input_zone_input"/>
                        <img class="recevice_search_close_ico" src="${pageContext.request.contextPath}/images/user_center/letter_recevice_seacher_close_ico.png" alt=""/>
                    </div>                   
                </div>               
                <div class="write_letter_textarea_zone">
                	<div class="write_letter_textarea_zone_con">
                    	<textarea name="message" id="messageid" maxlength=1000 class="write_letter_textarea"></textarea>
                    </div>
                    <div class="letter_send_btn_con">
	                    <span id="messerr" style="color:red;"></span>
	                    <a name="submit" onclick="messageadd();">发送</a>                  
                    </div> 
                </div>
   </form>
</body>
<script type="text/javascript">
	function messageadd()
	{
		  var userid=document.getElementById("userid").value;
		  var messageid=document.getElementById("messageid").value;
		  if(userid.trim()=="")
			  {
			     alert("请填写收信人");
			     return;
			  }
		  if(messageid.trim()=="")
			  {
			     document.getElementById("messerr").innerHTML="请填写您所要发送信息的内容";
			     return;
			  }
		  else
			  {
			     document.getElementById("messerr").innerHTML="";
			  }
		  $.ajax({
			  type: 'POST',
			     url: '${pageContext.request.contextPath}/msg/messageadd.action',
			     data: {userid:userid,messageid:messageid},
			     success:function (data) {
			    	 if(data.length==0)
			    		 {
			    		    alert("不存在该用户，请确认用户在进行发送");
			    		    document.getElementById("userid").value="";
			    		 }
			    	 else
			    		 {
			    		    alert("发送成功！");
			    		    document.getElementById("userid").value="";
			    		    document.getElementById("messageid").value="";
			    		 }
			     },
			     dataType: "json"
			});
	}
</script>

</html>


