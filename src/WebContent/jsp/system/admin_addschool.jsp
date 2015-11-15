<%@page import="com.cy.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学校管理 - 易启学后台管理</title>
<link type="text/css" rel="stylesheet" href="css/basic.css" />
<link type="text/css" rel="stylesheet" href="css/medioadaption.css" />
<link type="text/css" rel="stylesheet"
	href="css/bg_administrator_public.css" />
<link type="text/css" rel="stylesheet"
	href="css/bg_administrator_addschool.css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bg_administrator_index.js"></script>
<script type="text/javascript" src="js/bg_administrator_addschool.js"></script>
<style type="text/css">
.school_logo:HOVER {
	cursor: pointer;
}
</style>
<%
	User current = (User) session.getAttribute("user");
	if (current == null || current.getUserType() != 3) {
		response.sendRedirect(basePath + "index.action");
	}
%>
</head>
<body>
	<!----新增学校弹出框--->
	<div id="popSchool" class="pop_msg_administrator_addschool">
		<div class="pop_addschool_title clearfloat">
			<span class="pop_addschool_titlename">新增学校</span> <img
				class="addschool_pop_close"
				src="images/administrator/addschool_pop_close.png" alt="" />
		</div>
		<div class="pop_addschool_content">
			<h6>校徽图片上传说明</h6>
			<div class="pop_addschool_img_info">请上传纵横比例为1:1的校徽图片，图片格式仅限JPG,GIF,PNG,BMP三种格式，且文件小于2M。</div>
			<div class="pop_addschool_img_upload_zone clearfloat">
            <input type='text' name='textfield' id='textfield' class='pop_addschool_upload_zone_file' />
            <div class="pop_addschool_upload_looksearch_btn">浏览
                <input type="file" class="pop_addschool_upload_zone_filebtn" name="file"  onchange="document.getElementById('textfield').value=this.value"/>
            </div>
        </div>
			<form id="schoolForm" method="post">
				<div class="pop_addschool_img_con">
					<img id="universityLogo1" src="images/inner/school_ico_04.png"
						alt="" /> <input type="hidden" id="universityLogo"
						name="universityLogo" />
					<div class="pop_addschool_img_btn">上传校徽图片</div>
				</div>
				<div class="pop_addschool_type_con clearfloat">
					<div class="pop_addschool_type_list mr_15px">
						<span class="pop_addschool_type_txt width_60px">学校名称</span> <input
							type="hidden" id="universityId" name="universityId" /> <input
							type="text" id="universityName" name="universityName"
							class="pop_addschool_type_input_normal" />
					</div>
					<div class="pop_addschool_type_list mr_15px">
						<span class="pop_addschool_type_txt width_60px">学校类别</span> <input
							type="text" id="classify" name="classify"
							class="pop_addschool_type_input_normal" value="大学" />
					</div>
					<div class="pop_addschool_type_list">
						<span class="pop_addschool_type_txt width_30px">网址</span> <input
							type="text" id="website" name="website"
							class="pop_addschool_type_input_normal" />
					</div>
				</div>
				<div class="pop_addschool_type_con clearfloat">
					<div class="pop_addschool_type_list mr_15px">
						<span class="pop_addschool_type_txt width_60px">电话</span> <input
							type="text" id="phone" name="phone"
							class="pop_addschool_type_input_normal" />
					</div>
					<div class="pop_addschool_type_list mr_15px">
						<span class="pop_addschool_type_txt width_60px">传真</span> <input
							type="text" id="fax" name="fax"
							class="pop_addschool_type_input_normal" />
					</div>
					<div class="pop_addschool_type_list">
						<span class="pop_addschool_type_txt width_30px">电邮</span> <input
							type="text" id="email" name="email"
							class="pop_addschool_type_input_normal" />
					</div>
				</div>
				<div class="pop_addschool_type_con clearfloat">
					<div class="pop_addschool_type_list">
						<span class="pop_addschool_type_txt width_60px">学校地址</span> <input
							type="text" id="address" name="address"
							class="pop_addschool_type_input_address" />
					</div>
				</div>
				<div class="pop_addschool_type_con clearfloat">
					<div class="pop_addschool_type_list">
						<span class="pop_addschool_type_txt width_60px">学校简介</span>
						<textarea id="introduction" name="introduction"
							class="pop_addschool_type_textarea"></textarea>
					</div>
				</div>
				<div class="pop_addschool_type_con clearfloat">
					<div class="pop_addschool_type_btn">
						<span class="pop_addschool_btn_cancle" onclick="closePop()">取消</span>
						<span onclick="edit()" class="pop_addschool_btn_confirm">确定</span>
					</div>
				</div>
			</form>
		</div>

	</div>
	<div class="pop_msg_bg_administrator_addschool"></div>




	<!---中间内容区域  开始---->
	<div class="administrator_container">
		<div class="bg_administrator_con">
			<div class="administrator_left_menu">
				<div class="administrator_img_con">
					<div class="administrator_img_content">
						<img class="administrator_person_img"
							src="images/administrator/person_img_01.png" alt="" />
					</div>
					<p class="administrator_person_name">${user.realName }</p>
				</div>
				<div class="administrator_left_menu_nav">
					<ul class="administrator_menu_nav">
						<li class="curr"><span> <b class="administrator_ico_1"></b>
								<a href="university/listAll.action"><i>学校管理</i></a>
						</span></li>
						<li><span> <b class="administrator_ico_2"></b>
						 <a href="course/courseList.action"><i>新增课程</i></a>
						</span></li>
						<li><span> <b class="administrator_ico_3"></b>  
						<a href="cy/userImportList.action"><i>学生管理</i></a>
						</span></li>
						<li><span> <b class="administrator_ico_4"></b> <a
								href="university/goAnalysis.action"><i>统计分析</i></a>
						</span></li>
						<li><span> <b class="administrator_ico_5"></b>  
						 <a href="survey/coursesurveylist.action"><i>教学调查</i></a>
						</span></li>
						<li><span> <b class="administrator_ico_5"></b> <a
								href="${pageContext.request.contextPath}/notice/gosystemNotice.action"><i>新闻通知</i></a>
						</span></li>
					</ul>
				</div>
			</div>
			<div class="administrator_content_con">
				<div class="administrator_content_header clearfloat">
            	<div class="administrator_content_header_logo"><img src="${pageContext.request.contextPath}/images/administrator/admin_logo.png" alt=""/></div>
             <div class="admin_logout_btn"><a href="${pageContext.request.contextPath}/cy/exit.action">退出</a></div>
            </div>
				<div class="administrator_content_detail">
					<div class="administrator_bread_title clearfloat">
						<span class="administrator_add_icontxt"
							onClick="admAddschoolPopCenter();">新增学校</span>
					</div>
					<div class="administrator_addschool_con">
						<ul class="administrator_school_list">
							<c:if test="${empty universityList }">
								<font color="red"> 未查到学校！</font>
							</c:if>
							<c:if test="${!empty universityList }">
								<c:forEach items="${universityList }" var="university">
									<li><img class="addschool_del_ico"
										src="images/administrator/addschool_del_ico.png" alt=""
										onclick="delUniversity('${university.universityId}','${university.universityName}')" />
										<img class="school_logo" src="${university.universityLogo }"
										onclick="editUniversity('${university.universityId}')" />
										<p>${university.universityName }</p> <a
										class="school_edit_txt"
										onclick="editUniversity('${university.universityId}')">编辑资料</a>
									</li>
								</c:forEach>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
		</div>

	</div>
	<script type="text/javascript">
		$(function() {

		});

		function closePop() {
			$('.pop_msg_administrator_addschool').css('display', 'none');
			$('.pop_msg_bg_administrator_addschool').css('display', 'none');
		}

		function edit() {
			var id = $('#universityId').val();
			$('#schoolForm').attr(
					"action",
					id == '' ? 'university/add.action'
							: 'university/edit.action');
			if (confirm("确认操作吗?")) {
				$('#schoolForm').submit();
			}
		}

		function editUniversity(id) {
			//alert(id);
			var url = 'university/edit.action?universityId=' + id;
			$.get(url, {}, function(result) {
				if (result != null) {
					admAddschoolPopCenter();
					$("#universityLogo1").attr('src', result.universityLogo);
					$("#universityLogo").val(result.universityLogo);
					$("#universityId").val(result.universityId);
					$("#universityName").val(result.universityName);
					$("#classify").val(result.classify);
					$("#website").val(result.website);
					$("#phone").val(result.phone);
					$("#fax").val(result.fax);
					$("#email").val(result.email);
					$("#address").val(result.address);
					$("#introduction").val(result.introduction);
				}
			});
		}

		function delUniversity(id, name) {
			if (confirm("重要操作 : 确定要删除[ " + name + " ]吗?")) {
				var url = 'university/delete.action';
				var data = {
					'universityId' : id
				};
				$.post(url, data, function(result) {
					if (result.result == 1) {
						alert('删除成功!');
						window.location.href = "university/listAll.action";
					} else {
						alert('删除失败!');
					}
				});
			}
		}
		
		function exit(){
			if(confirm("确认退出系统吗?")){
			var url = "${pageContext.request.contextPath}/cy/exit.action";
			$.post(url,{},function(){
			window.location.href = "${pageContext.request.contextPath}/index.action";		
			});			
			}
		}
	</script>
</body>
</html>