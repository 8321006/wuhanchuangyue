$(function(){
	$('.exam_pop_tit_close').click(function(){
		$('.pop_msg_exam').css('display','none');
	$('.pop_msg_bg_exam').css('display','none');
	});
	
	var w_width = $(window).width();
	var _x = (w_width-960)*0.5-180;
	$('.time_cal_con').css('left',_x);
	
	$("body input").keydown(function(){
		$(this).css('color','#fff');
		$(this).css('font-size','30px');
	})
	
});




//弹出层居中
function examPopCenter(){
	$('.pop_msg_exam').css('display','block');
	$('.pop_msg_bg_exam').css('display','block');
	var w_width = $(window).width();
	var w_height = $(window).height();
	var b_height = $(document.body).height();
	if(b_height<w_height){
		$('.pop_msg_bg_exam').css('height',w_height);
		}else{
			$('.pop_msg_bg_exam').css('height',b_height);
			}
	var w_self = $('.pop_msg_exam').width();
	var h_self = $('.pop_msg_exam').height();
	var _x = w_width/2 - w_self/2;
	var _y = w_height/2 - h_self/2;
	$('.pop_msg_exam').css({left:_x,top:_y});
}


