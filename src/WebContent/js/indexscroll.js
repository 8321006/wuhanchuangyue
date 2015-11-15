/**
 * 
 */
$(function(){
    var flag = true;
	var w_height = $(window).height();
    var list=[0,w_height,w_height*2,w_height*3];
    var i = 0;
    $('html,body').bind('mousewheel',function(event, delta, deltaX, deltaY){
		if(flag)
		{
			flag=false;
			/*window.event.returnValue = false;	*/
			if(delta<0&&i!==list.length)
			{
				i++;
				$("html,body").animate({scrollTop:list[i]}, {duration: 500,queue: false,complete:function()
					{ 									  
					  flag=true;  					  
					  if(i>=0&&i<list.length-1)
					  {
						$('.side_nav').show();						
						$('.side_nav li').removeClass('cur');
						$('.side_nav li').eq(i).addClass('cur');
					  }
					}
			    }); 
			}
			else if(delta>0&&i!==0)
			{
				i--;  
				$("html,body").animate({scrollTop:list[i]}, {duration: 500,queue: false,complete:function()
					{ 									  
					  flag=true;
					  if(i>=0&&i<list.length-1)
					  {
						$('.side_nav').show();						
						$('.side_nav li').removeClass('cur');
						$('.side_nav li').eq(i).addClass('cur');
					  }
					  else
					    $('.side_nav').hide();
					}
				}); 
			}
			else if(delta>0&&i==list.length)
			{
				i++;  
				$("html,body").animate({scrollTop:list[i]}, {duration: 500,queue: false,complete:function()
					{ 									  
					  flag=true;
					  if(i>=0&&i<list.length-1)
					  {
						$('.side_nav').show();						
						$('.side_nav li').removeClass('cur');
						$('.side_nav li').eq(i).addClass('cur');
					  }
					  else
					    $('.side_nav').hide();
					}
				}); 
			}
			else
			{
						//设置标记为true,打开滚轮事件
				flag=true; 
			}
		}
		else
		{   
		    return false;
		}
		
		 
    })
	
	$('.side_nav li').click(function(){
		var num=$(this).index();
		if(($(this).index()+3)>i)
		{			
			i=num;	
			$('.side_nav li').removeClass('cur');
		    $('.side_nav li').eq(num).addClass('cur');
			$("html,body").animate({scrollTop:list[i]}, {duration: 500,queue: false,complete:function()
					{ 									  
					  flag=true;
									   
					}
				}); 
		}
		else if(($(this).index()+3)<i)
		{  
		     i=num;
			 $('.side_nav li').removeClass('cur');
		     $('.side_nav li').eq(num).addClass('cur');		
			 $("html,body").animate({scrollTop:list[i]}, {duration: 500,queue: false,complete:function()
					{ 									  
					  flag=true;								   
					}
				}); 			
		}
		else
		{   
		    flag=true;
			return false;
		}
	         
	});
    
    $('.side_nav li').hover(function(){
    	$(this).addClass('cur');
    },function(){
    	$(this).removeClass('cur');
    });
	
		
})