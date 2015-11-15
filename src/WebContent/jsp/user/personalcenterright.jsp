<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
var userType ="${sessionScope.user.userType}"
$.ajax({
    type: 'POST',
    url: '${pageContext.request.contextPath}/course/list.action',
    data: {type:'0'},
    success:function (data) {
    	//绘图
   		$(".uc_con_right_imgtxt").html(gethtml(data));
   		//加进度环节
   		getpercent();
    },
    dataType: "json"
});

$(".user_center_con_secnav").find("li").live('click',function(){
	$(".user_center_con_secnav").find("li").removeClass("curr");
	$.ajax({
	     type: 'POST',
	     url: '${pageContext.request.contextPath}/course/list.action',
	     data: {type:$(this).attr("type")},
	     success:function (data) {
	    	//绘图
	    	$(".uc_con_right_imgtxt").html(gethtml(data));
	    	//加进度环节
	    	getpercent();
	     },
	     dataType: "json"
	});
	$(this).addClass("curr");
})

function gethtml(data){
	var html = "";
  	 for(var i =0;i<data.length;i++){
  		var obj =data[i];
  		html=html+"<li>";
  		html=html+'<img courseId='+obj.courseId+' classId='+obj.classId+' class="uc_adv_img" src="${pageContext.request.contextPath}'+obj.courseCoverUrl+'" alt=""/>';
  		html=html+'<div class="uc_con_right_imgtxt_title clearfloat">';
  		html=html+'<div class="uc_cr_imgtt_txt">'+obj.courseName+'</div>';
  		html=html+'<div class="uc_cr_imgtt_circle">';
  		if(userType == '0'){
  			html=html+'<div id="pie_bg" style="width:45px;height:45px; line-height:82px;" >';
  	  		html=html+'<div id="pie'+i+'" class="pie" percent='+obj.percent+' style="font-size:12px;"></div></div>';
  	  		html=html+'<div class="uc_cr_imgtt_circle_pertxt">'+obj.percent+'%</div>';
  		}
  		html=html+'</div></div><div class="uc_cr_txt_name_peo"><div class="uc_cr_txt_name_peo_left">';
  		html=html+'<span>华中科技大学</span><span class="uc_cr_person_name">'+obj.courseAuthor+'</span></div>';
  		html=html+'<div class="uc_cr_txt_name_peo_right"><img src="${pageContext.request.contextPath}/images/inner/total_peo_ico.png" alt=""/>';
  		html=html+'<span>'+obj.num+'人</span></div></div></li>';
  	 }
  	 return html;
}

$(".uc_adv_img").live('click',function(){
	window.location.href="${pageContext.request.contextPath}/course/learn.action?courseId="+$(this).attr("courseId")+"&classId="+$(this).attr("classId");
});
</script>
</head>
<div class="user_center_img_scroll">
            	<div class="arr_left"><img src="${pageContext.request.contextPath}/images/user_center/arrow_left_srcoll.png"/></div>
                <div class="box_scroll">
                    <ul class="uc_con_right_imgtxt clearfloat">
                    </ul>
                </div>
				<div class="arr_right"><img src="${pageContext.request.contextPath}/images/user_center/arrow_right_srcoll.png"/></div>
            </div>
</html>