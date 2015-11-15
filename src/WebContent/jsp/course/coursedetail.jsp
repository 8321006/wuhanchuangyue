<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>我的课程</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/my_course_public.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/my_course_detail.css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/pagination.css" />
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/my_course.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pop_block_login.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.pagination.js"></script>
</head>
<body>

<!----头部区域-->	
<div class="inner_header">
	<jsp:include page="../head.jsp"></jsp:include>
     <!---banner 轮询 开始---->
    <div class="flexslider_02">
        <ul class="slides">
            <li style="background:url(${pageContext.request.contextPath}/images/my_course/mycourse_banner_01.jpg) no-repeat;"></li>
        </ul>
        <div class="my_course_detail"> 
            <div class="mycourse_tit_link">
            	<span>课程</span>&nbsp;&gt;&nbsp;<span>文学与艺术</span>&nbsp;&gt;&nbsp;<span>${course.courseName}</span>
            </div>
            <div class="mycourse_topmiddle_imgtxt clearfloat">
            	<img class="mycourse_center_banner_01" src="${pageContext.request.contextPath}${course.courseCoverUrl}" align=""/>
                <div class="mycourse_center_img_info">
                	<h7>${course.courseName}</h7>
                    <div class="mycourse_center_txt_imgtxt clearfloat">
                    	<div class="mycourse_center_txt_imgtxt_left">
                        	<div class="mycourse_center_txt_imgtxt_img"><img class="my_course_ico" src="${pageContext.request.contextPath}/images/my_course/my_course_ico_01.png"/></div>
                            <span class="mycourse_center_txt_imgtxt_span1">${course.num}人</span>
                        </div>
                        <div class="mycourse_center_txt_imgtxt_right">
                        	<div class="mycourse_center_txt_imgtxt_img"><img class="my_course_ico" src="${pageContext.request.contextPath}/images/my_course/my_course_ico_03.png"/></div>
                            <span class="mycourse_center_txt_imgtxt_span2">${chapterlength}节</span>
                        </div>
                    </div>
                    <!--  
                    <div class="mycourse_center_txt_imgtxt clearfloat">
                    	<div class="mycourse_center_txt_imgtxt_left">
                        	<div class="mycourse_center_txt_imgtxt_img"><img class="my_course_ico" src="${pageContext.request.contextPath}/images/my_course/my_course_ico_03.png"/></div>
                            <span class="mycourse_center_txt_imgtxt_span1">${chapterlength}节</span>
                        </div>
                        <div class="mycourse_center_txt_imgtxt_right">
                        	<div class="mycourse_center_txt_imgtxt_img"><img class="my_course_ico" src="${pageContext.request.contextPath}/images/my_course/my_course_ico_04.png"/></div>
                            <span class="mycourse_center_txt_imgtxt_span2">245人</span>
                        </div>
                    </div>
                    -->
                    <div class="mycourse_center_info_detailtxt">${course.courseDesc}</div>
                     <div class="mycourse_center_btn clearfloat">
                    <!--  	<a onclick="learn()">试看</a>-->
                    </div>
                </div>
            </div>
        </div>
    </div>  
    <!---banner 轮询 结束---->     
</div>

<!---中间内容区域  开始---->
<div class="container">	
	<div class="my_course_con clearfloat">
     	<div class="my_course_detail_con_left">
        	<ul class="mycourse_detail_cl_tabtit clearfloat">
            	<li class="selected">课程介绍</li>
                <li>课程目录</li>
            </ul>
 			<div class="my_course_detail_con my_course_detail_change1">
            	<div class="my_course_detail_img">
                	<img src="${pageContext.request.contextPath}/images/my_course/my_course_detail_img1.jpg" alt=""/>
                    <div class="my_course_detail_img_txt">
                    	<p>摄影技巧</p>
                        <div>在进行照相时，光通过小孔（更多时候是 一个透镜组）进入暗盒，在暗盒背部（相 对于光入射方向）的介质上成像。</div>
                    </div>
                </div>
                <div class="my_course_detail_img">
                	<img src="${pageContext.request.contextPath}/images/my_course/my_course_detail_img2.jpg" alt=""/>
                    <div class="my_course_detail_img_txt">
                    	<p>摄影技巧</p>
                        <div>在进行照相时，光通过小孔（更多时候是 一个透镜组）进入暗盒，在暗盒背部（相 对于光入射方向）的介质上成像。</div>
                    </div>
                </div>
            </div>
            <div class="my_course_detail_con my_course_detail_change2 display_none">
            <c:forEach items="${coursechapter}" var="p" varStatus="status">
            <c:if test="${status.last!=true}">
            	<div class="my_course_catalog">
                	<div class="my_course_catalog_list" style="cursor: pointer" onclick="learn('${p.chapterId}')">${p.chapterIndex}：${p.chapterName}</div>
                </div>
             </c:if>
            </c:forEach>
            </div>
            <div class="mycourse_detail_cl_tit mt_32px">课程讨论</div>
             <!-- 
			 <c:forEach items="${list}" var="t">			
            <div class="mycourse_detail_discuss">
            	<div class="mycourse_detail_discuss_content clearfloat">
                	<div class="mycourse_detail_discuss_left">
                        <img src="${pageContext.request.contextPath}/images/my_course/mycourse_person_ico_01.png" alt=""/>
                    </div>
                    <div class="mycourse_detail_discuss_right">
                        <div class="mycourse_detail_discuss_txt">${t.commentContent}</div>
                    </div>
                </div>
                <div class="mycourse_detail_discuss_content clearfloat">
                	<p class="mycourse_detail_discuss_nametxt">${t.realName}</p>
                    <div class="mycourse_detail_discuss_issue">发布时间：<fmt:formatDate value="${t.commentTime}" pattern="yyyy/MM/dd HH:mm:ss"/></div>
                </div>
            </div>
			</c:forEach>
			 -->					

	            <div class="mycourse_detail_discuss">
	            <!--  
	              <c:forEach items="${list}" var="t">
	            	<div class="mycourse_detail_discuss_content clearfloat">
	                	<div class="mycourse_detail_discuss_left">
	                        <img src="${pageContext.request.contextPath}/images/my_course/mycourse_person_ico_01.png" alt=""/>
	                    </div>
	                    <div class="mycourse_detail_discuss_right">
	                        <div class="mycourse_detail_discuss_txt">${t.commentContent}</div>
	                    </div>
	                </div>
	                <div class="mycourse_detail_discuss_content clearfloat">
	                	<p class="mycourse_detail_discuss_nametxt">${t.realName}</p>
	                    <div class="mycourse_detail_discuss_issue">发布时间：<fmt:formatDate value="${t.commentTime}" pattern="yyyy/MM/dd HH:mm:ss"/></div>
	                </div>
	              </c:forEach>
	              -->
	            </div>
	            <div id="Pagination" class="right flickr"></div>			
            <div class="mycourse_detail_discuss_edit clearfloat">
                <p>发布讨论</p>
                <textarea id="area" class="mycourse_detail_discuss_edit_zone" maxlength="140" onkeyup="checkLength(this);"></textarea>
                <div class="mycourse_detail_discuss_issue_btn">
                 <span id="tinum" style="color:red;"></span>
                    <span id="leftnum"></span>
                    <span id="faxx" style="color:red;"></span>
                    <a onclick="comment()" >发布</a>
                </div>
                <div class="mycourse_detail_discuss_total">讨论（共<span id="tlcount">${num}</span>条）</div>
            </div>
        </div>   
        <div class="my_course_con_right">
        	<div class="mycourse_cr_tit">老师简介</div>
            <div class="mycourse_cr_teacher_detail clearfloat">
            	<div class="mycourse_cr_teacher_img"><img src="${pageContext.request.contextPath}/images/my_course/mycourse_teacher_ico_02.jpg" alt=""/></div>
                <div class="mycourse_cr_teacher_namedetail">
                	<p class="mt_32px">${course.courseAuthor}</p>
                    <p class="mt_12px">${course.courseAuthorDetail}</p>
                </div>
            </div>
            <!-- 
            <div class="mycourse_cr_tit mt_14px">猜你喜欢</div>
            <div class="mycourse_cr_love_detail_con clearfloat">
                <div class="mycourse_cr_love_detail">
                    <img class="mycourse_cr_love_img" src="${pageContext.request.contextPath}/images/my_course/mycourse_love_img_01.jpg" alt=""/>
                    <div class="mycourse_cr_love_imgtxt">
                    	<p class="mt_12px">文学与艺术</p>
                        <p class="mt_10px">华中科技大学</p>
                        <p class="mt_10px">主讲老师：<span>不拉不</span></p>
                    </div>
                </div>
            </div>
            <div class="mycourse_cr_love_detail_con clearfloat">
                <div class="mycourse_cr_love_detail">
                    <img class="mycourse_cr_love_img" src="${pageContext.request.contextPath}/images/my_course/mycourse_love_img_02.jpg" alt=""/>
                    <div class="mycourse_cr_love_imgtxt">
                    	<p class="mt_12px">文学与艺术</p>
                        <p class="mt_10px">华中科技大学</p>
                        <p class="mt_10px">主讲老师：<span>不拉不</span></p>
                    </div>
                </div>
            </div>
            <div class="mycourse_cr_love_detail_con clearfloat">
                <div class="mycourse_cr_love_detail">
                    <img class="mycourse_cr_love_img" src="${pageContext.request.contextPath}/images/my_course/mycourse_love_img_03.jpg" alt=""/>
                    <div class="mycourse_cr_love_imgtxt">
                    	<p class="mt_12px">文学与艺术</p>
                        <p class="mt_10px">华中科技大学</p>
                        <p class="mt_10px">主讲老师：<span>不拉不</span></p>
                    </div>
                </div>
            </div>
            
            <div class="mycourse_cr_love_detail_con clearfloat">
                <div class="mycourse_cr_love_detail">
                    <img class="mycourse_cr_love_img" src="${pageContext.request.contextPath}/images/my_course/mycourse_love_img_05.jpg" alt=""/>
                    <div class="mycourse_cr_love_imgtxt">
                    	<p class="mt_12px">文学与艺术</p>
                        <p class="mt_10px">华中科技大学</p>
                        <p class="mt_10px">主讲老师：<span>不拉不</span></p>
                    </div>
                </div>
            </div>
        </div> 
         -->
    </div>
</div>
<form action="${pageContext.request.contextPath}/course/detail.action" id="searchform"  name="searchform" method="post">
<input type='hidden' id="courseId" name='courseId' value='${course.courseId}'/>
<input type='hidden' id='commentContent' name='commentContent' value=''/>
 </form>
<!---底部区域---->
</body>
<script type="text/javascript">

	//首次加载     
	pageselectCallback(0); 
	//分页事件   
	$("#Pagination").pagination('${num}', {
              callback: pageselectCallback,//PageCallback() 为翻页调用次函数。
              prev_text: " 上一页",
              next_text: "下一页 ",
              items_per_page: 5, //每页的数据个数
              num_display_entries: 3, //两侧首尾分页条目数
              current_page: 0,   //当前页码
              num_edge_entries: 2, //连续分页主体部分分页条目数
    });
	
	function comment(){
		var courseId=document.getElementById("courseId").value; 

		var areat = document.getElementById("area").value;	
		var localuserId = "${sessionScope.user.userId}";
		if(localuserId != ""){
			if(areat.trim()==""){
				document.getElementById("faxx").innerHTML = "您没有填写内容";
				$("#faxx").delay(4000).fadeOut("slow");
				return;
			}
			$.ajax({
			     type: 'POST',
			     url: '${pageContext.request.contextPath}/course/commentAdd.action',
			     data:{courseId:document.getElementById("courseId").value,commentContent:document.getElementById("area").value,pageIndex:0},
			     success:function (data) {
			    	 pageselectCallback(0);		    	 
			    	 $("#Pagination").pagination(data, {
			              callback: pageselectCallback,//PageCallback() 为翻页调用次函数。
			              prev_text: " 上一页",
			              next_text: "下一页 ",
			              items_per_page: 5, //每页的数据个数
			              num_display_entries: 3, //两侧首尾分页条目数
			              current_page: 0,   //当前页码
			              num_edge_entries: 2, //连续分页主体部分分页条目数
			    });
			    	 document.getElementById("tlcount").innerHTML = ${num};
			     },
			     dataType: "json"			    	 
			});
			/**此方法提交方式刷新了页面，因此改成ajax方式提交   
			document.searchform.action="${pageContext.request.contextPath}/course/commentAdd.action";
			$("#commentContent").val($(".mycourse_detail_discuss_edit_zone").val())
			document.searchform.submit();
			**/
			document.getElementById("area").value = "";		
		}else{
			document.getElementById("tinum").innerHTML = "您还没有登录";
			$("#tinum").delay(4000).fadeOut("slow");
		}
			
		
	}
	
	function checkLength(gm) {
	    var maxChars = 140;
	    if (gm.value.length > maxChars){
	         gm.value = gm.value.substring(0,maxChars);
	    }
	    var curr = maxChars - gm.value.length;
	    document.getElementById("leftnum").innerHTML = "还可输入"+curr.toString()+"字";
	    //$("#leftnum").delay(4000).fadeOut("slow");
	}

	function pageselectCallback(page_id, jq) {
		//拼接分页
		$.ajax({
		     type: 'POST',
		     url: '${pageContext.request.contextPath}/course/commentList.action',
		     data:{courseId:document.getElementById("courseId").value,commentContent:document.getElementById("area").value,pageIndex:page_id},
		     success:function (data) {
		    	$(".mycourse_detail_discuss").html("");
		    	var html="";
		    	for(var i =0;i<data.length;i++)
		    	{
		    		 var obj =data[i];
		    		 html=html+'<div class="mycourse_detail_discuss">';
		    		 html=html+'<div class="mycourse_detail_discuss_content clearfloat">';
		    		 html=html+'<div class="mycourse_detail_discuss_left">';
		    		 
		    		 if('${sessionScope.user.sex}'==""||'${sessionScope.user.sex}' == '0')
		    		 {
		    			 if('${sessionScope.user.userType}' == '0')
		    			 {
		    				 html=html+'<img src="${pageContext.request.contextPath}/images/user_center/person_center_student_01.jpg" alt=""/></div>';   
		    			 }
		    			 else if('${sessionScope.user.userType}' == '1')
		    			 {	 
		    			     html=html+'<img src="${pageContext.request.contextPath}/images/user_center/person_center_teacher_01.jpg" alt=""/></div>'; 
		    			 }
		    			 else if('${sessionScope.user.userType}' == '2')
		    			 {
		    			     html=html+'<img src="${pageContext.request.contextPath}/images/user_center/person_center_shcool_01.jpg" alt=""/></div>';
		    			 }
		    			 else
		    			 {
		    				 html=html+'<img src="${pageContext.request.contextPath}/images/user_center/person_center_student_01.jpg" alt=""/></div>';
		    			 }
		    		 }	    		 
		    		 
		    		 else if('${sessionScope.user.sex}' == '1')
		    		 {
		    			 if('${sessionScope.user.userType}' == '0')
		    				 {
		    				   html=html+'<img src="${pageContext.request.contextPath}/images/user_center/person_center_student_02.jpg" alt=""/></div>';
		    				 }
		    		     if('${sessionScope.user.userType}' == '1')
		    			 {
		    			       html=html+'<img src="${pageContext.request.contextPath}/images/user_center/person_center_teacher_02.jpg" alt=""/></div>';
		    			 }
		    		     if('${sessionScope.user.userType}' == '2')
		    			 {
		    			       html=html+'<img src="${pageContext.request.contextPath}/images/user_center/person_center_shcool_01.jpg" alt=""/></div>';
		    			 }
		    		  }
		
		    		 html=html+'<div class="mycourse_detail_discuss_right">';
		    		 html=html+'<div class="mycourse_detail_discuss_txt">'+obj.commentContent.replace("<","&lt;").replace(">","&gt;").replace("</","&lt;\/")+'</div>';
		    		 html=html+'</div></div>';    
		    		 html=html+'<div class="mycourse_detail_discuss_content clearfloat">';
		    		 html=html+'<p class="mycourse_detail_discuss_nametxt">'+obj.realName+'</p>';
		    		 html=html+'<div class="mycourse_detail_discuss_issue">'+obj.commenttimeString+'</div>'
		    		 html=html+'</div></div>';
		    	}
		    		 $(".mycourse_detail_discuss").html(html);
		    		 document.getElementById("tlcount").innerHTML = ${num};
		     },
		     dataType: "json"
		});
    }



</script>
</html>
