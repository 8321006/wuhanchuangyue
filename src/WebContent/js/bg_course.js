$(function(){
	
	//下拉菜单开始 
	$(".term_chose_content").click(function(){
		if($(".term_chose_option_down").css("display")=="none"){
			$(".term_chose_option_down").slideDown("fast");
		}else{
			$(".term_chose_option_down").slideUp("fast");
		}
	});
	$(".term_chose_content").mouseover(function(){
		if($(".term_chose_option_down").css("display")=="none"){
			$(".term_chose_option_down").slideDown("fast");
		}/*else{
			$(".term_chose_option_down").slideUp("fast");
		}*/
	});
	$(".term_chose_content").mouseleave(function(){
		if($(".term_chose_option_down").css("display")=="none"){
			$(".term_chose_option_down").slideDown("fast");
		}else{
			$(".term_chose_option_down").slideUp("fast");
		}
	});
	
	$(".term_chose_option_down li").click(function(){
		var txt = $(this).text();
		$(".term_chose_content_detail").text(txt);
		
		$(".term_chose_option_down").hide();
	});
	$(".term_chose_option_down").mouseleave(function(){
		$(".term_chose_option_down").hide();
	})
	//下拉菜单结束
	
	//课程设置开始 
	$(".course_set_couse_tab_con ul li").click(function(){
		$('.term_chose_content').css('background-color','#fff');
		var index=$(this).index();
		$('.course_set_couse_tab_con ul li').removeClass("curr");
		$(this).addClass('curr');
		for(var i=0;i<$('.course_set_couse_tab_con ul li').length;i++)
		{
		  $(".cset_tab_con"+(i+1)).hide();  
		}
		$(".cset_tab_con"+(index+1)).show();  
		
	}
	);
	
	//课程设置结束
	
	//输入框文字改变
	/*
	$(".course_set_search_input").keydown(function(){
		$(this).css('color','#333');
	})
	$(".input_style input").click(function(){
		if($(this).val()==""){
			$(this).css('color','#ccc');
			}else{
				$(this).css('color','#333');
			}
	})*/
	
	/*****课程设置弹出框选择老师
	$('#TeacherSelect').click(function(){
		$('.pm_course_set_content').css('display','none');
		$('.pm_course_set_chose_teacher_content').css('display','block');
		listTeacher();
	});****/
	
	
	$('.pm_cset_cteacher_search_result_table tr td .pm_cset_cteacher_chosebox').click(function(){
		$('.pm_cset_cteacher_search_result_table tr td i').removeClass('pm_cset_cteacher_chosebox_selected');
		$('.pm_cset_cteacher_search_result_table tr td i').addClass('pm_cset_cteacher_chosebox');
		$(this).removeClass('pm_cset_cteacher_chosebox');
		$(this).addClass('pm_cset_cteacher_chosebox_selected');
	});
	
	$('.pm_cset_cteacher_search_btn_detail span').click(function(){
		$('.pm_course_set_content').css('display','block');
		$('.pm_course_set_chose_teacher_content').css('display','none');
	});
	
	$('.pm_cset_cteacher_search_btn_detail b').click(function(){
		$('.pm_course_set_content').css('display','block');
		$('.pm_course_set_chose_teacher_content').css('display','none');
	});
	$('.pm_course_set_btn_detail b').click(function(){
		$('.pop_msg_course_set').css('display','none');
		 $('.pop_msg_bg_course_set').css('display','none');
		 $('.pm_course_set_content').css('display','block');
		 $('.pm_course_set_chose_teacher_content').css('display','none');
	});
	
	
	/*****弹出框关闭****/
	 $('.course_set_list_pop_close').click(function(){
		 $('.pop_msg_course_set').css('display','none');
		 $('.pop_msg_bg_course_set').css('display','none');
		 $('.pm_course_set_content').css('display','block');
		 $('.pm_course_set_chose_teacher_content').css('display','none');
	 });
	 	
});

	


