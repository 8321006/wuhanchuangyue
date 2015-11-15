$(function(){
	
	/***性别选择切换***/
	$('.sex_chose_zone').click(function(){
		$('.sex_chose_zone').children('span').removeClass('sex_chosebox_selected');
		$('.sex_chose_zone').children('span').addClass('sex_chosebox');
		$(this).children('span').addClass('sex_chosebox_selected');
		
	});
	
	/***弹出层打开 关闭****/
	$('.upload_img_txt').click(function(){
		pers_popCenter();
	});
	$('.person_center_pop_tit_close').click(function(){
		$('.pop_msg_person_center').css('display','none');
		$('.pop_msg_bg_person_center').css('display','none');
	});
	 	
})


//弹出层居中
function pers_popCenter(){
	$('.pop_msg_person_center').css('display','block');
	$('.pop_msg_bg_person_center').css('display','block');
	var w_width = $(window).width();
	var w_height = $(window).height();
	var b_height = $(document.body).height();
	if(b_height<w_height){
		$('.pop_msg_bg_person_center').css('height',w_height);
		}else{
			$('.pop_msg_bg_person_center').css('height',b_height);
			}
	var w_self = $('.pop_msg_person_center').width();
	var h_self = $('.pop_msg_person_center').height();
	var _x = w_width/2 - w_self/2;
	var _y = w_height/2 - h_self/2;
	$('.pop_msg_person_center').css({left:_x,top:_y});
}