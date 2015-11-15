$(function(){	

	/****头部鼠标移入下拉菜单****/
	
	$('body input').click(function(){
		$(this).css('border-color','#4dd8fb'); 	
	});	

	$('.tips_ico_con').hover(function() {
			$(this).find('.tips_down_pop').stop().show();
		},
		function() {
			$(this).find('.tips_down_pop').stop().hide();
		});
	 	
	/****输入框文字颜色改变****/
	$(".body_input").keydown(function(){
		$(this).css('color','#333');
	})
	$(".body_input").click(function(){
	if($(this).val()==""){
		$(this).css('color','#ccc');
		}else{
			$(this).css('color','#333');
		}
	})
	
	
	var w_height = $(window).height();
	var b_height = $(document.body).height();
	/*if(b_height<w_height){
		$('.index_con1').height(w_height);
		$('.index_con2').height(w_height);
		$('.index_con3').height(w_height);
		$('.index_con1 .index_con_bg img').height(w_height);
		$('.index_con2 .index2_con_bg img').height(w_height);
		}else{
			$('.index_con1').height(b_height);
			$('.index_con2').height(b_height);
			$('.index_con3').height(b_height);
			$('.index_con1 .index_con_bg img').height(b_height);
			$('.index_con2 .index2_con_bg img').height(b_height);
			}*/
	$('.index_con1').height(w_height);
	$('.index_con2').height(w_height);
	$('.index_con3').height(w_height);
	$('.index_con1 .index_con_bg img').height(w_height);
	$('.index_con2 .index2_con_bg img').height(w_height);
	$('.index_con3 .index3_con_bg img').height(w_height);

	
	var ucright_height = $('.user_center_con_right').height();
	if(ucright_height < 401){
		$('.user_center_con_left').css('height','401px');
	}else{
		$('.user_center_con_left').css('height',ucright_height);
		}
	
	
	/***侧边导航定位****/
	/*var w_width = $(window).width();
	var _x = (w_width-1200)*0.5-200;
	$('.side_nav_con').css('right',_x);*/
	
	/****第二页图片 鼠标移动上去js****/
	$('.index_con2_right ul li').mouseenter(function(){
		$(this).children('.index2_img_txt').css('background-color','#d6f1f8');
		$(this).children('.img_pop_block_con').slideDown(300);
	});
	$('.index_con2_right ul li').mouseleave(function(){
		$(this).children('.index2_img_txt').css('background-color','#fff');
		$(this).children('.img_pop_block_con').slideUp(300);
	});
	//首頁點擊全部課程后的課程頁面
	/****第二页图片 鼠标移动上去js****/
	$('.img_txt_detail li').mouseenter(function(){
		$(this).children('.img_pop_block_con').slideDown(300);
		$(this).children('.index2_img_txt').css('background-color','#d6f1f8');
	});
	$('.img_txt_detail li').mouseleave(function(){
		$(this).children('.img_pop_block_con').slideUp(300);
		$(this).children('.index2_img_txt').css('background-color','#fff');
	});
	
	
	/*****身份选择弹出框点击切换****/
	$('.login_id_chose_con ul li').click(function(){
		  var index=$(this).index();
		  $('.login_id_chose_con ul li').removeClass("selected");
		  $(this).addClass('selected');
	  });	 
	  
})



