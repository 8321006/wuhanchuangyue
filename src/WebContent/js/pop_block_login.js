$(function(){
	
	/*****地区学校选择tab切换****/
	$('.school_tab li').click(function(){
		  var index=$(this).index();
		  $('.school_tab li').removeClass("selected");
		  $(this).addClass('selected');
		  for(var i=0;i<$('.school_tab li').length;i++)
		  {
			  $(".school_chose_0"+(i+1)).hide();
		  }
		  $(".school_chose_0"+(index+1)).show();
	  });
	  
	$('.school_detail li').click(function(){
		$(this).css('background','#d8edf2');
		$(this).siblings('li').css('background','#fff'); 
		$(this).parent('ul').siblings().children('li').css('background','#fff'); 
		var imgSrc = ($(this).children('img').attr('src'));
		$('.login_school_ico').attr('src',imgSrc);
		$("#universityId").val($(this).children('p').attr("universityid"));
		$(".login_txt").html($(this).children('p').html())
	});
	
	
	/*****弹出框****/
	$('.login_btn').click(function(){
		popCenter();	
	});
	$('.pop_close').click(function(){
		$('.pop_msg_bg_login').css('display','none');
		$('.pop_msg_login').css('display','none');	
	});	
	
	$('.login_input input').click(function(){
		$(this).css('border-color','#4dd8fb'); 
		$(this).parent().siblings().children('input').css('border-color','#ccc'); 	
	});	
	
})


//弹出层居中
function popCenter(){
	$('.pop_msg_login').css('display','block');
	$('.pop_msg_bg_login').css('display','block');
	var w_width = $(window).width();
	var w_height = $(window).height();
	var b_height = $(document.body).height();
	if(b_height<w_height){
		$('.pop_msg_bg_login').css('height',w_height);
		}else{
			$('.pop_msg_bg_login').css('height',b_height);
			}
	var w_self = $('.pop_msg_login').width();
	var h_self = $('.pop_msg_login').height();
	var _x = w_width/2 - w_self/2;
	var _y = w_height/2 - h_self/2;
	$('.pop_msg_login').css({left:_x,top:_y});
}


