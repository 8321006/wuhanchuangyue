$(function(){	

	/*****通知区域tab切换******/
	$('.user_center_notice_tab li').click(function(){
		  var index=$(this).index();
		  $('.user_center_notice_tab li').removeClass("selected");
		  $(this).addClass('selected');
		  for(var i=0;i<$('.user_center_notice_tab li').length;i++)
		  {
			  $(".user_center_notice_tabcon"+(i+1)).hide();
		  }
		  $(".user_center_notice_tabcon"+(index+1)).show();
	  });
	  
	  
	  /*****课程资料tab切换**样式在jsp页面已经存在
	$('.user_center_course_data_tab li').click(function(){
		  var index=$(this).index();
		  $('.user_center_course_data_tab li').removeClass("selected");
		  $(this).addClass('selected');
		  for(var i=0;i<$('.user_center_course_data_tab li').length;i++)
		  {
			  $(".user_center_course_data_tabcon"+(i+1)).hide();
		  }
		  $(".user_center_course_data_tabcon"+(index+1)).show();
	  });
	  ****/
	  
	  //教务事务弹出框新增选择下拉菜单开始 
	$(".jiaowu_add_option_down_con").click(function(){
		if($(".jiaowu_add_option_down").css("display")=="none"){
			$(".jiaowu_add_option_down").slideDown("fast");
		}else{
			$(".jiaowu_add_option_down").slideUp("fast");
		}
	});
	$(".jiaowu_add_option_down li").click(function(){
		var txt = $(this).text();
		$(".jiaowu_add_option_down_con span").text(txt);
		$(".jiaowu_add_option_down").hide();
	});
	$(".jiaowu_add_option_down").mouseleave(function(){
		$(".jiaowu_add_option_down").hide();
	})
	//教务事务弹出框新增选择下拉菜单开始
	
	
	/****教学调查满意度评价****/ 
//	 $('.uc_tinquire_txt_list ul li').hover(function() {
//		$(this).addClass('on');
//		$(this).prevAll().addClass('on');
//		$(this).nextAll().removeClass('on');
//	});
	
	/****教学调查弹出框选中课程****/ 
	$(".teaching_inquire_add_list span").click(function(){
		if($(this).hasClass('selected')){
			$(this).removeClass('selected');
			}else{
				$(this).addClass('selected');
			}
	});
	
	
	
	
	  
	/*****弹出框关闭****/
	$('.pop_msg_notice_close').click(function(){
		$('.pop_msg_notice').css('display','none');
		$('.pop_msg_bg_notice').css('display','none');
	});
	
	$('.jiaowu_add_list_pop_close').click(function(){
		$('.pop_msg_jiaowu_add').css('display','none');
		$('.pop_msg_bg_jiaowu_add').css('display','none');
	});
	
	$('.teaching_inquire_add_list_pop_close').click(function(){
		$('.pop_msg_teaching_inquire_add').css('display','none');
		$('.pop_msg_bg_teaching_inquire_add').css('display','none');
	});
	
	
	$('.jiaowu_add_btn_detail b').click(function(){
		$('.pop_msg_jiaowu_add').css('display','none');
		$('.pop_msg_bg_jiaowu_add').css('display','none');
	});
	
	
	
	/****图片左右滚动****/
	new AutoScroll({
		scrollObj:'.box_scroll ul',
		step:286,
		toLeftBtn:'.arr_right',
		toRightBtn:'.arr_left'
	});
})



//弹出层居中
function noticePopCenter(){
	$('.pop_msg_notice').css('display','block');
	$('.pop_msg_bg_notice').css('display','block');
	var w_width = $(window).width();
	var w_height = $(window).height();
	var b_height = $(document.body).height();
	if(b_height<w_height){
		$('.pop_msg_bg_notice').css('height',w_height);
		}else{
			$('.pop_msg_bg_notice').css('height',b_height);
			}
	var w_self = $('.pop_msg_notice').width();
	var h_self = $('.pop_msg_notice').height();
	var _x = w_width/2 - w_self/2;
	var _y = w_height/2 - h_self/2;
	$('.pop_msg_notice').css({left:_x,top:_y});
}


function jiaowuAddPopCenter(){
	$('.pop_msg_jiaowu_add').css('display','block');
	$('.pop_msg_bg_jiaowu_add').css('display','block');
	var w_width = $(window).width();
	var w_height = $(window).height();
	var b_height = $(document.body).height();
	if(b_height<w_height){
		$('.pop_msg_bg_jiaowu_add').css('height',w_height);
		}else{
			$('.pop_msg_bg_jiaowu_add').css('height',b_height);
			}
	var w_self = $('.pop_msg_jiaowu_add').width();
	var h_self = $('.pop_msg_jiaowu_add').height();
	var _x = w_width/2 - w_self/2;
	var _y = w_height/2 - h_self/2;
	$('.pop_msg_jiaowu_add').css({left:_x,top:_y});
}



