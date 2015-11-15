$(function(){
	
	/*****课程详情tab切换****/
	$('.mycourse_detail_cl_tabtit li').click(function(){
		  var index=$(this).index();
		  $('.mycourse_detail_cl_tabtit li').removeClass("selected");
		  $(this).addClass('selected');
		  for(var i=0;i<$('.mycourse_detail_cl_tabtit li').length;i++)
		  {
			  $(".my_course_detail_change"+(i+1)).hide();
		  }
		  $(".my_course_detail_change"+(index+1)).show();
	  });
	  
	  /*****弹出框关闭****/
	  $('.pop_msg_course .course_pop_tit_close').click(function(){
		  $('.pop_msg_course').css('display','none');
		  $('.pop_msg_bg_course').css('display','none');
		 });
	
	  $('.pop_msg_course1 .course_pop_tit_close').click(function(){
		  $('.pop_msg_course1').css('display','none');
		  $('.pop_msg_bg_course1').css('display','none');
		 });
	  
	  $('.pop_msg_teachplan .pop_msg_teachplan_close').click(function(){
		  $('.pop_msg_teachplan').css('display','none');
		  $('.pop_msg_bg_teachplan').css('display','none');
		 });
	 	
})



//弹出层居中
function popCenter1(){
	$('.pop_msg_course').css('display','block');
	$('.pop_msg_bg_course').css('display','block');
	var w_width = $(window).width();
	var w_height = $(window).height();
	var b_height = $(document.body).height();
	if(b_height<w_height){
		$('.pop_msg_bg_course').css('height',w_height);
		}else{
			$('.pop_msg_bg_course').css('height',b_height);
			}
	var w_self = $('.pop_msg_course').width();
	var h_self = $('.pop_msg_course').height();
	var _x = w_width/2 - w_self/2;
	var _y = w_height/2 - h_self/2;
	$('.pop_msg_course').css({left:_x,top:_y});
}

function popCenter2(){
	$('.pop_msg_course1').css('display','block');
	$('.pop_msg_bg_course1').css('display','block');
	var w_width = $(window).width();
	var w_height = $(window).height();
	var b_height = $(document.body).height();
	if(b_height<w_height){
		$('.pop_msg_bg_course1').css('height',w_height);
		}else{
			$('.pop_msg_bg_course1').css('height',b_height);
			}
	var w_self = $('.pop_msg_course1').width();
	var h_self = $('.pop_msg_course1').height();
	var _x = w_width/2 - w_self/2;
	var _y = w_height/2 - h_self/2;
	$('.pop_msg_course1').css({left:_x,top:_y});
}

function teachPlanPopCenter(){
	$('.pop_msg_teachplan').css('display','block');
	$('.pop_msg_bg_teachplan').css('display','block');
	var w_width = $(window).width();
	var w_height = $(window).height();
	var b_height = $(document.body).height();
	if(b_height<w_height){
		$('.pop_msg_bg_teachplan').css('height',w_height);
		}else{
			$('.pop_msg_bg_teachplan').css('height',b_height);
			}
	var w_self = $('.pop_msg_teachplan').width();
	var h_self = $('.pop_msg_teachplan').height();
	var _x = w_width/2 - w_self/2;
	var _y = w_height/2 - h_self/2;
	$('.pop_msg_teachplan').css({left:_x,top:_y});
	
	
	
	/***** 教学计划 时间轴左右边高度相等****/
	var timeRightHeight1 = $('.loopheight1 .teachplan_time_mr_con_right').height();
	$('.loopheight1 .teachplan_time_mr_con_left').height(timeRightHeight1);
	$('.loopheight1 .teachplan_time_mr_con_left span').css('padding-top',timeRightHeight1*0.5-15);
	$('.loopheight1 .teachplan_time_mr_con_right .teachplan_arrow_left_blue').css('top',timeRightHeight1*0.5-10);
	
	var timeRightHeight2 = $('.loopheight2 .teachplan_time_mr_con_right').height();	
	$('.loopheight2 .teachplan_time_mr_con_left').height(timeRightHeight2);
	$('.loopheight2 .teachplan_time_mr_con_left span').css('padding-top',timeRightHeight2*0.5-15);
	$('.loopheight2 .teachplan_time_mr_con_right .teachplan_arrow_left_blue').css('top',timeRightHeight2*0.5-10);
	
	var timeRightHeight3 = $('.loopheight3 .teachplan_time_mr_con_right').height();
	$('.loopheight3 .teachplan_time_mr_con_left').height(timeRightHeight3);
	$('.loopheight3 .teachplan_time_mr_con_left span').css('padding-top',timeRightHeight3*0.5-15);
	$('.loopheight3 .teachplan_time_mr_con_right .teachplan_arrow_left_blue').css('top',timeRightHeight3*0.5-10);
}
