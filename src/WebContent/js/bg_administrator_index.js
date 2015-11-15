$(function(){
	
	var w_height = $(window).height();
	var b_height = $(document.body).height();
	if(w_height < b_height){
		$('.administrator_container').height(b_height);	
		$('.administrator_left_menu').height(b_height);	
		}else{
			$('.administrator_container').height(w_height);	
			$('.administrator_left_menu').height(w_height);	
			}
	

	/****左侧菜单导航****/
	
	$('.administrator_menu_nav li').hover(function(){
		if($(this).hasClass('curr')){
			$(this).css({'background':'#2c3e50','border-left-color':'#5faee3'});
			}else{
				$(this).css({'background':'#2c3e50','border-left-color':'#2c3e50'});
				}
	},
	function(){
		if($(this).hasClass('curr')){
			$(this).css({'background':'#2c3e50','border-left-color':'#5faee3'});
			}else{
				$(this).css({'background':'#34495e','border-left-color':'#34495e'});
				}
		
	});
			
})




