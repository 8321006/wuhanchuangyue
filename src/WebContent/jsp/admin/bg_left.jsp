<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<style>
.houtai_menu_nav ul li span b.ico_8{
	background:url(${pageContext.request.contextPath}/images/user_center/nav_ico_04.png) no-repeat; 
}
</style>

<input type="hidden" value="${sessionScope.user.universityId}" id="universityId" />
<!--  
    	<div class="houtai_menu_nav">
    		<ol class="houtai_menu_nav_index">
            	<li class="curr">
                	<span onClick="window.location.href='bg_index.html'">
                    	<b class="ico_0"></b>
                    	<i>首页</i>
                    </span> 
                </li>
            </ol>
    		
            <ul>
               
                <li>
                    <span>
                    	<b class="ico_2"></b>
                    	<i>学校信息维护</i>
                    </span>
                    <ol class="children_menu_nav">
                    	<li>信息维护</li>
                  
                    </ol>
                </li>
                <li>
                    <span>
                    	<b class="ico_3"></b>
                    	<i>课程管理</i>
                    </span>
                	<ol class="children_menu_nav">
                    	<li><a href="${pageContext.request.contextPath}/cy/universitycourse.action">开课管理</a></li>
                       
                        <li id="userMgm">学生管理</li>
                     
                         <li id="teacherMgm">老师管理</li>
                        <li><a href="${pageContext.request.contextPath}/jsp/admin/reportUniversity.jsp">学情分析</a></li>
                    </ol>
                </li>
               
                <li>
                    <span>
                    	<b class="ico_5"></b>
                    	<i>新闻通知</i>
                    </span>
                    <ol class="children_menu_nav">
                    	<li><a href="${pageContext.request.contextPath}/notice/noticisyslist.action">新闻通知</a></li>
                    

                    </ol>
                </li>
               
                <li>
                    <span>
                    	<b class="ico_7"></b>
                    	<i>教学资源</i>
                    </span>
                    <ol class="children_menu_nav">
                    	<li>课程资源管理</li>
                 		<li id = "courseData">课程资料管理</li>
                    </ol>
                </li>
            </ul>
        </div>
        <div class="houtai_menu_child_nav display_none"></div>
        
     -->   
        
        
        <div class="houtai_menu_nav">
        	<ol class="houtai_menu_nav_index">
            	<li id='curr'>
                	<span onClick="window.location.href='./jsp/admin/bg_index.jsp'">
                    	<b class="ico_0"></b>
                    	<i>首页</i>
                    </span> 
                </li>
            </ol>
            <ul id="leftbar">
             <!--  
                <li>
                    <span>
                    	<b class="ico_1"></b>
                    	<i>教学设置</i>
                    </span>
            		<div class="nav_arrow nav_arrow_right"></div>
                    <ol class="children_menu_nav display_none">
                    	<li>教学设置</li>
                        <li>教学设置</li>
                        <li>教学设置</li>
                        <li>教学设置</li>
                        <li>教学设置</li>
                        <li>教学设置</li>
                    </ol>
                </li>              
                <li>
                    <span>
                    	<b class="ico_2"></b>
                    	<i>主页维护</i>
                    </span>
                    <div class="nav_arrow nav_arrow_right"></div>
                    <ol class="children_menu_nav display_none">
                    	<li>主页维护</li>
                        <li>主页维护</li>
                        <li>主页维护</li>
                        <li>主页维护</li>
                        <li>主页维护</li>
                        <li>主页维护</li>
                    </ol>
                </li>
                  -->
                   
                <li id="function_management">
                    <span>
                    	<b class="ico_3"></b>
                    	<i>功课管理</i>
                    </span>
                    <div class="nav_arrow nav_arrow_right"></div>
                	<ol class="children_menu_nav display_none">
                    	<li id='courseSet'onclick="coursemanage()">开课管理</li>
                          <!--   <li>招生管理</li>-->
                        <li id='userSet'onclick="studentmanage();">学生管理</li>
                        <li id='teacherSet' onclick="teachermanage();">老师管理</li>
                         <!--  <li>教学计划</li>-->
                        <li id='reportSet'onclick="goReport()">学情分析</li>
                    </ol>
                </li>
                 <!--  
                <li>
                    <span>
                    	<b class="ico_4"></b>
                    	<i>选课管理</i>
                    </span>
                    <div class="nav_arrow nav_arrow_right"></div>
                    <ol class="children_menu_nav display_none">
                    	<li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                    </ol>
                </li>
                
                <li>
                    <span>
                    	<b class="ico_5"></b>
                    	<i>学籍管理</i>
                    </span>
                    <div class="nav_arrow nav_arrow_right"></div>
                    <ol class="children_menu_nav display_none">
                    	<li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                    </ol>
                </li>
                <li>
                    <span>
                    	<b class="ico_6"></b>
                    	<i>教务处理</i>
                    </span>
                    <div class="nav_arrow nav_arrow_right"></div>
                    <ol class="children_menu_nav display_none">
                    	<li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                    </ol>
                </li>
                 -->
                <li id='news_notice'>
                    <span>
                    	<b class="ico_8"></b>
                    	<i>新闻通知</i>
                    </span>
                    <div class="nav_arrow nav_arrow_right"></div>
                    <ol class="children_menu_nav display_none">
                    	<li id='newsSet'onClick="noticelist();">新闻通知</li>
                    </ol>
                </li>
                 <!--  
                <li>
                    <span>
                    	<b class="ico_7"></b>
                    	<i>教学资源</i>
                    </span>
                    <ol class="children_menu_nav display_none">
                    	<li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                        <li>开课管理</li>
                    </ol>
                </li>
                 -->
            </ul>
        </div>
        
        
        
        

<script type="text/javascript">
function noticelist()
{
	window.location.href="${pageContext.request.contextPath}/notice/noticisyslist.action";
}

function studentmanage(){
	window.location.href="${pageContext.request.contextPath}/cy/userlist.action";
}

function teachermanage(){
	window.location.href="${pageContext.request.contextPath}/cy/teacherlist.action";
}
function coursemanage(){
	window.location.href="${pageContext.request.contextPath}/cy/universitycourse.action";
}

/* $("#userMgm").live('click', function(){
>>>>>>> .r1489
	
	window.location.href="${pageContext.request.contextPath}/cy/userlist.action";
});

$("#teacherMgm").live('click', function(){
	
	window.location.href="${pageContext.request.contextPath}/cy/teacherlist.action";
}); */

$("#courseData").live('click', function(){
/* 	window.location.href="${pageContext.request.contextPath}/cy_moocs/coursedata/coursedatalist.action"; */
 	window.location.href="${pageContext.request.contextPath}/coursedata/coursedatalist.action"; 
});

function goReport(){
	var basePath = '${pageContext.request.contextPath}/';
	var universityId = $("#universityId").val();
	var url = "${pageContext.request.contextPath}/universityanalysis/goReport.action";
	var args = {"universityId" : universityId};
	$.get(url,args,function(data){
		window.open(basePath + data);
	});
}
</script>
