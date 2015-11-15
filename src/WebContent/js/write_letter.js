$(function(){
	
	/*****站内信和写信 tab切换****/
	$('.write_letter_tab li').click(function(){
		  var index=$(this).index();
		  $('.write_letter_tab li').removeClass("current");
		  $(this).addClass('current');
		  for(var i=0;i<$('.write_letter_tab li').length;i++)
		  {
			  $(".write_letter_tab_con"+(i+1)).hide();  
		  }
		  $(".write_letter_tab_con"+(index+1)).show();  
	  });
	  
	  /*****站内信中个人消息和系统消息 tab切换****/
	$('.station_letter_info_tab_tit li').click(function(){
		  var index=$(this).index();
		  $('.station_letter_info_tab_tit li').removeClass("curr");
		  $(this).addClass('curr');
		  for(var i=0;i<$('.station_letter_info_tab_tit li').length;i++)
		  {
			  $(".station_letter_info_list_container"+(i+1)).hide();
			  $(".station_letter_right_con"+(i+1)).hide();
		  }
		  $(".station_letter_info_list_container"+(index+1)).show();
		  $(".station_letter_right_con"+(index+1)).show();
	  });
});



