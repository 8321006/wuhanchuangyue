
$(function(){
	
	/*****弹出框关闭****/
	 $('.bnew_list_pop_close').click(function(){
		 $('.pop_msg_bnew_list').css('display','none');
		 $('.pop_msg_bg_teacher_list').css('display','none');
		 $('.bnew_list_content').css('display','block');
	 });
	 
	 $('.bnew_list_send_btn_center b').click(function(){
			$('.pop_msg_bnew_list').css('display','none');
			$('.pop_msg_bg_teacher_list').css('display','none');
			$('.bnew_list_content').css('display','block');
		});
	 	
});

function newTeacherAddPopCenter(){
	$('.pop_msg_bnew_list').css('display','block');
	$('.pop_msg_bg_teacher_list').css('display','block');
	var w_width = $(window).width();
	var w_height = $(window).height();
	var b_height = $(document.body).height();
	if(b_height<w_height){
		$('.pop_msg_bnew_list').css('height',w_height/3);
		}else{
			$('.pop_msg_bg_teacher_list').css('height',b_height/3);
			}
	var w_self = $('.pop_msg_bnew_list').width();
	var h_self = $('.pop_msg_bnew_list').height();
	var _x = w_width/2 - w_self/2;
	var _y = w_height/2 - h_self/2;
	$('.pop_msg_bnew_list').css({left:_x,top:_y});
}

	
	/*****弹出框关闭****/
	 $('.bnew_list_pop_close').click(function(){
		 $('.pop_msg_bnew_list').css('display','none');
		 $('.pop_msg_bg_bnew_list').css('display','none');
	 });

function newListAddPopCenter(){
	$('.pop_msg_bnew_list').css('display','block');
	$('.pop_msg_bg_bnew_list').css('display','block');
	var w_width = $(window).width();
	var w_height = $(window).height();
	var b_height = $(document.body).height();
	if(b_height<w_height){
		$('.pop_msg_bg_bnew_list').css('height',w_height);
		}else{
			$('.pop_msg_bg_bnew_list').css('height',b_height);
			}
	var w_self = $('.pop_msg_bnew_list').width();
	var h_self = $('.pop_msg_bnew_list').height();
	var _x = w_width/2 - w_self/2;
	var _y = w_height/2 - h_self/2;
	$('.pop_msg_bnew_list').css({left:_x,top:_y});
}