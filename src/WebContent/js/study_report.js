$(function(){
	//下拉菜单开始 
	$(".sr_con1_option_div").click(function(){
		if($(".sr_con1_option_down").css("display")=="none"){
			$(".sr_con1_option_down").slideDown("fast");
		}else{
			$(".sr_con1_option_down").slideUp("fast");
		}
	});
	$(".sr_con1_option_down li").click(function(){
		var txt = $(this).text();
		$(".sr_con1_option_div b").text(txt);
		$(".sr_con1_option_down").hide();
	});
	$(".sr_con1_option_down").mouseleave(function(){
		$(".sr_con1_option_down").hide();
	})
	//下拉菜单结束
	
	
	
	/*****弹出框关闭****/
	 $('.report_pop_close').click(function(){
		 $('.pop_msg_school_seq').css('display','none');
		 $('.pop_msg_bg_school_seq').css('display','none');
	 });
	 	
});

function schoolSeqPopCenter(){
	$('.pop_msg_school_seq').css('display','block');
	$('.pop_msg_bg_school_seq').css('display','block');
	var w_width = $(window).width();
	var w_height = $(window).height();
	var b_height = $(document.body).height();
	if(b_height<w_height){
		$('.pop_msg_bg_school_seq').css('height',w_height);
		}else{
			$('.pop_msg_bg_school_seq').css('height',b_height);
			}
	var w_self = $('.pop_msg_school_seq').width();
	var h_self = $('.pop_msg_school_seq').height();
	var _x = w_width/2 - w_self/2;
	var _y = w_height/2 - h_self/2;
	$('.pop_msg_school_seq').css({left:_x,top:_y});
}

