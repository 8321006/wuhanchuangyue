$(function(){	
	
	/*****底部固定底部*****/
	var w_height = $(window).height();
	var b_height = $(document.body).height();
	if(b_height<w_height){
		$('.footer_con').css('position','absolute');
		}else{
			$('.footer_con').css('position','inherit');
			}
})




