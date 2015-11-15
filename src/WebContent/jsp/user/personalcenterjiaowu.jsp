<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>个人中心——教务处理</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/user_center.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/hDate2.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/user_center.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/footer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/highcharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/Autoscoll.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.flexslider-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/user_center.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/circle.js"></script>
</head>
<body>
<!----教务事务新增弹出框--->
<div class="pop_msg_jiaowu_add">
	<div class="jiaowu_add_list_title">新增课程事务<img class="jiaowu_add_list_pop_close" src="${pageContext.request.contextPath}/images/houtai/new_list_pop_close.png" alt=""/></div>
    <div class="jiaowu_add_content">
        <div class="jiaowu_add_type_list clearfloat">
        	<div class="jiaowu_add_type_txt">用户名称</div>
            <div class="jiaowu_add_type_input_zone">
            	<input type="text" id="realName"class="jiaowu_add_input"/>
            </div>
        </div>
        <div class="jiaowu_add_type_list clearfloat">
        	<div class="jiaowu_add_type_txt">班级名称</div>
            <div class="jiaowu_add_type_input_zone">
            	<input type="text" id="className"class="jiaowu_add_input"/>
            </div>
        </div>
        <div class="jiaowu_add_type_list clearfloat">
        	<div class="jiaowu_add_type_txt">课程名称</div>
            <div class="jiaowu_add_type_input_zone">
            	<input type="text" id="courseName"class="jiaowu_add_input"/>
            </div>
            </div>
         <div class="jiaowu_add_type_list clearfloat">
        	<div class="jiaowu_add_type_txt">负责人</div>
            <div class="jiaowu_add_type_input_zone">
            	<input type="text" id="teacherName"class="jiaowu_add_input"/>
            </div>
          </div>
        <div class="jiaowu_add_type_list clearfloat">
        	<div class="jiaowu_add_type_txt">申请事务</div>
            <div class="jiaowu_add_option_down_con">
            	<span id="bexam">补考</span>
                <img class="jiaowu_add_option_down_arrow" src="${pageContext.request.contextPath}/images/houtai/term_chose_arrow_option.png" alt=""/>
            	<ul class="jiaowu_add_option_down">
                    <li>补考</li>
                    <li>换课</li>
                </ul>
            </div>
            </div>
         <div class="jiaowu_add_type_list clearfloat">
        	<div class="jiaowu_add_type_txt">申请原因</div>
            <div class="jiaowu_add_type_input_zone">
            	<input type="text"id="transactionReason" class="jiaowu_add_input"/>
            </div>
        </div>
      
        <div class="jiaowu_add_btn_con clearfloat">
        	<div class="jiaowu_add_btn_detail">
            	<span onclick="submitadd();">提交</span>
                <b>关闭</b>
            </div>
        </div>
    </div>
</div>
<div class="pop_msg_bg_jiaowu_add"></div>
<!----头部区域-->	
<div class="inner_header">
    <jsp:include page="../head.jsp"></jsp:include>
</div>

<!---中间内容区域  开始---->
<div class="container">	
<div class="user_center_con">
<div class="user_center_con_info">
            <div class="user_ico" onClick="window.location.href='user_center.html'"><img src="images/user_center/user_img_01.jpg" alt=""/></div>
            <dl class="user_center_con_list">
                <dt>基本资料</dt>
                <dd>莫妮卡 Monica</dd>
                <dd>UID:123456789</dd>
                <dd>社区活跃度：中级</dd>
                <dd>收藏课程：12</dd>
            </dl>
        </div>
	<div class="user_center_content clearfloat">
    	<div class="user_center_con_left">
            <jsp:include page="../user/personalcenter-left.jsp"></jsp:include>
        </div>
    	
        <div class="user_center_con_right">
         <div class="user_center_bread_tit"></div>
        	<div class="user_center_jiaowu">
            	<div class="user_center_jiaowu_title clearfloat">
                	<span>教务处理</span>
                    <div class="user_center_jiaowu_add_con" onClick="jiaowuAddPopCenter();"style="display: none;">
                        <img class="user_center_jiaowu_add_ico" src="${pageContext.request.contextPath}/images/houtai/new_list_add_ico.png" alt=""/>
                        <span class="user_center_jiaowu_add_txt">新增</span>
                    </div>
                </div>
                <div class="user_center_jiaowu_table_con">
                	<table class="user_center_jiaowu_table" cellpadding="0" cellspacing="0">
                	<c:if test="${Transactionlist.size()==0}">
                      <span>无相关信息</span>
                    </c:if>
                     <c:if test="${Transactionlist.size()>0}">
                    	<thead>
                        	<tr>
                                <td>学生姓名</td>
                                <td>班级名称</td>
                                <td>课程名称</td>
                                <td>试卷名称</td>
                                <td>负责人</td>
                                <td>申请事务</td>
                                <td>申请原因</td>
                                <td>申请时间</td>
                                <td>审核状态</td>
                                <c:if test="${userType == '1' }">
								 <td>操作</td>
								</c:if>	
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${Transactionlist}" var="item">
                        <input  type="hidden"id=courseId value="${item.courseId}">
                        <input  type="hidden"id=userId value="${item.userId}">
                        </tr>
                        	<tr>
                        	   <td style="display: none;">${item.userId}</td>
                                <td>${item.realName}</td>
                                <td><span id="td-test-Id">${item.className}</span></td>
                                <td>${item.courseName}</td>
                                <td>${item.paperName}</td>
                                <td>${item.teacherName}</td>
                                <td><span><c:if test="${item.transactionType == '1' }">
									补考</c:if></span>
									<span><c:if test="${item.transactionType == '2' }">
									换课	</c:if></span></td>
									 <td>${item.transactionReason}</td>
								 <td><fmt:formatDate value="${item.transactionTime}"/></td>
                                <td><span id="transactionState">
                                 <c:if test="${item.transactionState =='0'}">
									未通过</c:if>
									<c:if test="${item.transactionState == '1'}">
									审核中</c:if>
									<c:if test="${item.transactionState == '2'}">
									已通过</c:if>
								</span></td>
								<c:if test="${userType == '1' }">
								<td>
								 <c:if test="${item.transactionState== '1'}">
								 <span class="table_operation_yn_btn mr_5px"onClick="agree(this,'${item.userId}','2','${item.id}');">同意</span>
                                 <span class="table_operation_yn_btn mr_5px"onClick="agree(this,'${item.userId}','0','${item.id}');">拒绝</span>
                                </c:if>
                                <c:if test="${item.transactionState== '2'||item.transactionState== '0' }">
                                <span class="table_operation_yn_btn mr_5px"style="background:#bbbbbb">同意</span>
                                <span class="table_operation_yn_btn mr_5px" style="background:#bbbbbb">拒绝</span>
                                </c:if>
								</td>
								</c:if>
                            </tr>
                            </c:forEach>
                        </tbody>
                         </c:if>
                    </table>
                </div>
            </div>
        </div>
        </div>
    </div>
</div>
<!---底部区域---->
<div class="footer_con">
    <jsp:include page="../user/personalcenterbottom.jsp"></jsp:include>
</div>
<script type="text/javascript">
//提交新增信息
	function submitadd(){
		$.ajax( {   
		    type : "POST",   
		    url : "${pageContext.request.contextPath}/transaction/add.action",
		    data : {
		       "userId":$("#userId").html(),
		       "className":$("#className").val(),
		       "courseName" :$("#courseName").val(),
		       "teacherName":$("#teacherName").val()
		     //"transactionType"::$("#transactionType").val()
		     },  
		    dataType: "json",   
		    success : function(data) {   
		        if(data.success){   
		         alert("修改成功！");
		     	//$(".pop_msg_course_set").toggle();
		     	// $(".pop_msg_bg_course_set").css('display','none');
		     	//
		        }   
		        else{   
		            alert("设置失败！");   
		        }   
		    },   
		    error :function(){   
		        alert("网络连接出错！");   
		    }   
		});  
	}
function agree(obj,userId,transactionState,id) {
		$.ajax({
			type : "POST",
			 url : "${pageContext.request.contextPath}/transaction/agree.action",
			data : {"userId":userId,
				 "transactionState":transactionState,
				 "id":id
				 },
			 dataType: "json",
			success : function(data) {
				 if(data.success){
			         if(transactionState=='2')
			         {
			        	 alert("确定通过吗？");
			         $(obj).parent().find(".table_operation_yn_btn").css("background"," #CCC");
			         $(obj).parent().parent().find("#transactionState").text("已通过");
			         }
				   if(transactionState=='0')
				   {
					   alert("确定拒绝吗！");
				     $(obj).parent().find(".table_operation_yn_btn").css("background"," #CCC");
				     $(obj).parent().parent().find("#transactionState").text("未通过");
			        }  
				   } 
			        else{   
			            alert("失败！");   
			        }   
			    },   
			error : function(jqXHR, textStatus) {
				util.error("操作失败请稍后尝试");
			}
		});
}
debugger
$("#leftbar").find("li").each(function(){
	$(this).removeClass();
});
$("#jiaowu").attr("class","curr");
</script>
</body>
</html>
