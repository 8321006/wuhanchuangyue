$(function(){
	
	var w_height = $(window).height();
	var b_height = $(document.body).height();
	if(w_height < b_height){
		$('.houtai_menu_nav').height(b_height);	
		$('.houtai_menu_nav').height(b_height);	
		}else{
			$('.houtai_menu_nav').height(w_height);	
			$('.houtai_menu_nav').height(w_height);	
			}

	/****菜单二级菜单导航****/
	/*$('.houtai_menu_nav ul li').hover(function(){
		$(this).addClass('curr');
		$('.houtai_menu_nav ul li').css('border-top-color','#fff');
		$('.houtai_menu_nav ul li ol li').css('border-top-color','#d8edf2');
		$(this).siblings('li').removeClass('curr');
		$(this).find('.children_menu_nav').show();
		$(this).siblings('li').find('.children_menu_nav').hide();
	},
	function(){
		$(this).removeClass('curr');
		$('.houtai_menu_nav ul li').css('border-top-color','#58cce9');
		$('.houtai_menu_nav ul li ol li').css('border-top-color','#d8edf2');
		$(this).find('.children_menu_nav').hide();
	});*/
	
	/*$('.houtai_menu_nav ul li').mouseenter(function(){
		$(this).addClass('curr');
		$('.houtai_menu_nav ul li').css('border-top-color','#fff');
		$('.houtai_menu_nav ul li ol li').css('border-top-color','#d8edf2');
		$(this).siblings('li').removeClass('curr');
		$(this).find('.children_menu_nav').show();
		$('.houtai_con').css('padding-left','295px');
		$(this).siblings('li').find('.children_menu_nav').hide();
	});
		
	
	$('.houtai_menu_nav ol.houtai_menu_nav_index li').hover(function(){
		if($(this).hasClass('curr')){
			$(this).css('border-top-color','#fff');
			$(this).parent().siblings('ul').children('li').removeClass('curr');
			$(this).parent().siblings('ul').children('li').children('ol').css('display','none');
			$('.houtai_con').css('padding-left','165px');
			$('.houtai_menu_child_nav').css('display','none');
		}
		
	},
	function(){
		if($(this).hasClass('curr')){
		$(this).css('border-top-color','#58cce9');
		}
	});	*/
	
	
	/*$('.houtai_menu_nav ul').hover(function(){
		$('.houtai_con').animate({
		paddingLeft:'295px'},500);	
	},
	function(){
		$('.houtai_con').animate({
			paddingLeft:'295px'},500);
	});	*/
	$('.houtai_menu_nav ul li').click(function(){
		if($(this).children('div.nav_arrow').hasClass('nav_arrow_right')){
			$(this).children('div.nav_arrow').removeClass('nav_arrow_right');
			$(this).children('div.nav_arrow').removeClass('nav_arrow_down');
			$(this).children('div.nav_arrow').addClass('nav_arrow_down');
			$(this).children('ol').removeClass('display_none');
			$(this).children('ol').fadeIn();
			$(this).siblings('li').children('ol').addClass('display_none');
			$(this).siblings('li').children('div.nav_arrow').removeClass('nav_arrow_right');
			$(this).siblings('li').children('div.nav_arrow').removeClass('nav_arrow_down');
			$(this).siblings('li').children('div.nav_arrow').addClass('nav_arrow_right');
		}else{
			$(this).children('div.nav_arrow').removeClass('nav_arrow_right');
			$(this).children('div.nav_arrow').removeClass('nav_arrow_down');
			$(this).children('div.nav_arrow').addClass('nav_arrow_right');
			$(this).children('ol').addClass('display_none');
			$(this).children('ol').fadeOut();
		}
	
	});
	$('.houtai_menu_nav ul li ol li').click(function(e){
		e.stopPropagation();
	});
			
})




