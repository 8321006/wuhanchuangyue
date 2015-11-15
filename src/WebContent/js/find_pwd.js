$(function(){
	
	$('.tips_ico_con').click(function(){
		$(this).children().toggle();
	});
	
	$('body input').click(function(){
		$(this).css('border-color','#4dd8fb'); 	
	});	
	
	$("body input").keydown(function(){
		$(this).css('color','#333');
	})
	$("body input").click(function(){
	if($(this).val()==""){
		$(this).css('color','#ccc');
		}else{
			$(this).css('color','#333');
		}
	})
	
	$('.send_yzm_btn').click(function(){
		$(this).text('重新发送（43）');
		$('.yzm_sended_txt').css('display','block');
	});
	 	
})

