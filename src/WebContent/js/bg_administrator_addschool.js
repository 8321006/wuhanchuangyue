$(function(){
	
	/****新增学校弹出框关闭***/
	$('.addschool_pop_close').click(function(){
		$('.pop_msg_administrator_addschool').css('display','none');
		$('.pop_msg_bg_administrator_addschool').css('display','none');
	});
	
			
})


function admAddschoolPopCenter(){
	$("#universityLogo1").attr('src', '');
	$("#universityLogo").val('');
	$("#universityId").val('');
	$("#universityName").val('');
	$("#classify").val('大学');
	$("#website").val('');
	$("#phone").val('');
	$("#fax").val('');
	$("#email").val('');
	$("#address").val('');
	$("#introduction").val('');
	$('.pop_msg_administrator_addschool').css('display','block');
	$('.pop_msg_bg_administrator_addschool').css('display','block');
	var w_width = $(window).width();
	var w_height = $(window).height();
	var b_height = $(document.body).height();
	if(b_height<w_height){
		$('.pop_msg_bg_administrator_addschool').css('height',w_height);
		}else{
			$('.pop_msg_bg_administrator_addschool').css('height',b_height);
			}
	var w_self = $('.pop_msg_administrator_addschool').width();
	var h_self = $('.pop_msg_administrator_addschool').height();
	var _x = w_width/2 - w_self/2;
	var _y = w_height/2 - h_self/2;
	$('.pop_msg_administrator_addschool').css({left:_x,top:_y});
}

