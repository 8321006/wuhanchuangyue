$(function(){
	
	/*****课程详情tab切换****/
	$('.video_look_tab li').click(function(){
		  var index=$(this).index();
		  $('.video_look_tab li').removeClass("curr");
		  $(this).addClass('curr');
		  for(var i=0;i<$('.video_look_tab li').length;i++)
		  {
			  $(".video_look_tab_change"+(i+1)).hide();
		  }
		  $(".video_look_tab_change"+(index+1)).show();
	  }); 	
})

