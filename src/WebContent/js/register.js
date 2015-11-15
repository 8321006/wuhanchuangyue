$(function(){
	
	$('.register_read_agreement span').click(function(){
		if($(this).hasClass('register_read_agreement_chose')){
			$(this).removeClass('register_read_agreement_chose');
			$(this).addClass('register_read_agreement_chose_selected');
		}else{
			$(this).removeClass('register_read_agreement_chose_selected');
			$(this).addClass('register_read_agreement_chose');
			}
	});
	
	
	//第二步选择学校下拉菜单开始 
	$(".register_option_detail").click(function(){
		if($(".school_chose_option_down").css("display")=="none"){
			$(".school_chose_option_down").slideDown("fast");
		}else{
			$(".school_chose_option_down").slideUp("fast");
		}
	});
	$(".school_chose_option_down li").click(function(){
		var txt = $(this).text();
		$(".register_option_detail .school_option_txt").text(txt);
		$(".school_chose_option_down").hide();
	});
	$(".school_chose_option_down").mouseleave(function(){
		$(".school_chose_option_down").hide();
	})
	//下拉菜单结束
		
	
	
	
	/****找回密码步骤****/
//	$('.step_go_btn_1').click(function(){
//		$('.step_go_1').css('display','none');	
//		$('.step_go_2').css('display','block');
//	});
//	$('.step_go_btn_2').click(function(){
//		$('.step_go_1').css('display','none');	
//		$('.step_go_2').css('display','none');
//		$('.step_go_3').css('display','block');
//	});
	 	
})

