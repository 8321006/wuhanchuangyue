<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>日历选择器</title>




<style type="text/css">
html, body {width:100%;height:100%;padding:0;margin:0; }
</style>

</head>
<body>
<!-- 
<div class="message1">
                    	<img class="rili_angle_arrow_left" src="${pageContext.request.contextPath}/images/user_center/rili_angle_arrow_left.png" alt=""/>
                        <div class="rilikongjian">
	                        <div class="rili_message_txt"><span>1、</span>古代英语文学赏析第三章学习</div>
	                        <div class="rili_message_txt"><span>2、</span>古代英语文学赏析第三章学习</div>
	                        <div class="rili_message_txt"><span>3、</span>古代英语文学赏析第三章学习</div>
	                        <div class="rili_message_txt"><span>4、</span>古代英语文学赏析第三章学习</div>
               </div>
</div>
-->     
<!--               
<div style="width:520px;margin:40px auto 0 auto;" id="demoDiv">
    <input type="hidden" id="calendarId" onClick = "calendar.show(this)" value=""/>
	<div id="dateId"></div>
</div>

 
                <img class="rili_angle_arrow_left" src="${pageContext.request.contextPath}/images/user_center/rili_angle_arrow_left.png" alt=""/>
                  <div class="dateId">
	                  <input type="hidden" id="calendarId" onClick = "calendar.show(this)" value=""/>
	                  <div id="dateId"></div>
                 </div>
 -->

<script type="text/javascript">
/**
$(document).ready(function (){ 
    var p= $("#calendarId");
	p.value = new Date()+"";
	calendar.init(p);
	$("#_tdCal tr:gt(0) td[date!='']").mouseover(function ()  {
		var d = $(this).attr("date");
		$("#dateId").html(d);
		calendar.config.messageData="无行事历！大爷的！";
	});
});
**/


calendar.show({
	ok: function (ths) {
		//alert(ths.value);
		/**这都是全部以前写的代码，因需求变更，因此这些代码都废了要重新写
	    $.post("./cy/finddate.action",{"date":ths.value},function(data){			
			if(data.length!=0)
				{
				  for(var i=0;i<data.length;i++)
					  {
					     var s = data[i].courseName;
					  }
				     calendar.config.messageData="今天你有课程没学习，请您抓紧时间学习！";
				}
			else
				{
				     //calendar.config.messageData="今天放假，你可以去happy啦！";
				     calendar.config.messageData="";
				}				
		})
		$.post("./cy/findpaper.action",{"date":ths.value},function(data){			
			if(data.length!=0)
				{
				      var p;
					  for(var i=0;i<data.length;i++)
					  {
						  p=data[i].information;
						  calendar.config.messageData=calendar.config.messageData+"<br>"+p;
					  }
				     //calendar.config.messageData=calendar.config.messageData+"<br>要考试了，同学们抓紧咯!";
				}
			else
				{
				    // calendar.config.messageData=calendar.config.messageData+"<br>还没到考试时间呢，别急亲们!";
				}				
		})
		**/
	
		
	    $.post("./findcourseid.action",function(data){	
			if(data.length!=0)
			{
			  calendar.config.messageData='';
			  for(var i=0;i<data.length;i++)
				  {		  
				     var s = data[i].chaptername;
				     var p=data[i].dayBefore;

				     if(ths.value==p)
				    	 {
				    	    calendar.config.messageData='<'+s+'>课程落后了，该抓紧时间学习了！';
				    	 }
				     else if(ths.value!=p&&calendar.config.messageData=="")
				    	 {
				    	    calendar.config.messageData="无行事历！";
				    	 }

				  }			     
			}
	    	
		})
		
	},
	// messageData:"今天放假，你可以去happy啦"
})



/* $("#Text2").click(function (e) {
	var ths = this;
	alert(ths);
	calendar.show({
		id: this, ok: function () {
			//alert(ths.value);
		}
	}); 
});*/
</script>

</body>
</html>