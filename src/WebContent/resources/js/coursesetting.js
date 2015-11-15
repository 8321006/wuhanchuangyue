$(function() {

	question_list.initial();
});

var question_list = {
	initial : function initial() {
		this.bindChangeSearchParam();
		this.bindChangeProperty();
		this.bindUpdateProperty();
		this.bindUpdateExampaper();
		this.StartCourse();
		this.deletePaper();
		this.offlinePaper();
		this.bindTecherProperty();
	},
	bindChangeSearchParam : function bindChangeSearchParam(){
		$("#question-filter dl dd span").click(function(){
			if($(this).hasClass("label"))return false;
			
			
			var genrateParamOld = question_list.genrateParamOld();
			
			if($(this).parent().parent().attr("id") == "question-filter-pagetype" ){
				genrateParamOld.pagetype = $(this).data("id");
				question_list.redirectUrl(genrateParamOld);
				
			}else return false;
			
		});
		
		$(".pagination li a").click(function(){
			var pageId = $(this).data("id");
			if(pageId == null || pageId == "")return false;
			var genrateParamOld = question_list.genrateParamOld();
			genrateParamOld.page = pageId;
			question_list.redirectUrl(genrateParamOld);
			
		});
	},
	
	//开课
	StartCourse : function StartCourse(){
		$("#startcourse").click(function(){
			var tr = $(this).parent().parent();
			var courseId = tr.find(".td-test-courseId").text();
			alert(courseId);
				$.ajax({
					type : "POST",
					 url : "/cy_moocs/cy/courseStart.action",
					data : {"courseId":courseId},
					 dataType: "json",
					success : function(data) {
						 if(data.success){   
					         alert("开课成功！");
					        // $("#startcourse").attr("class","btn btn-primary"); 
					       //  $("#startcourse").attr("css","btn btn-default");
					        }   
					        else{   
					            alert("设置失败！");   
					        }   
					    },   
					error : function(jqXHR, textStatus) {
						util.error("操作失败请稍后尝试");
					}
				});
			
		});
	},
	
	offlinePaper : function offlinePaper(){
		$(".offline-paper").click(function(){
			var paper_id = $(this).parent().parent().find("input").val();
			if (confirm("确定下线吗？下线后的试卷将无法再进行考试")) {
				$.ajax({
					headers : {
						'Accept' : 'application/json',
						'Content-Type' : 'application/json'
					},
					type : "POST",
					url : "admin/paper-offline",
					data : JSON.stringify(paper_id),
					success : function(message, tst, jqXHR) {
						if (!util.checkSessionOut(jqXHR))
							return false;
						if (message.result == "success") {
							util.success("试卷已成功下线",function(){
								window.location.reload();
							});
						} else {
							util.error("操作失败请稍后尝试:" + message.result);
						}
	
					},
					error : function(jqXHR, textStatus) {
						util.error("操作失败请稍后尝试");
					}
				});
			}
		});
	},
	
	deletePaper : function deletePaper(){
		$(".delete-paper").click(function(){
			var id = $(this).parent().parent().find(".td-test-Id").text();
			alert(id);
			var paper_id = $(this).parent().parent().find("input").val();
			if (confirm("确定删除？")) {
				$.ajax( {   
				    type : "POST",   
				    url : "/cy_moocs/cy/universitycoursedelete.action",
				    data : {
				     "id" : id
				     },  
				    dataType: "json",   
				    success :function(data) {   
				        if(data.success){   
				         alert("删除成功！");
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
		});
	},
	
	genrateParamOld :function genrateParamOld(){
		var pagetype = $("#question-filter-pagetype dd .label").data("id");
		var page = 1;
		
		var data = new Object();
		data.pagetype= pagetype;
		data.page = page;
		
		return data;
	},

	redirectUrl : function(newparam) {
		var paramurl = newparam.pagetype;
		paramurl = paramurl + "-" + newparam.page;
		paramurl = paramurl + ".html";

		document.location.href = document.getElementsByTagName('base')[0].href
				+ 'admin/exampaperfilter-' + paramurl;
	},
	
	bindChangeProperty : function bindChangeProperty(){
		$(".change-property").click(function(){
			$("#change-property-modal").modal({backdrop:true,keyboard:true});
			
			var tr = $(this).parent().parent();
			var paper_name = tr.find(".td-paper-name").text();
			var paper_type = tr.find(".td-paper-type").data("id");
			var paper_duration  = tr.find(".td-paper-duration").text();
			var paper_id =  $(this).parent().parent().find(":checkbox").val();
			$(".add-update-exampapername input").val(paper_name);
			$(".add-update-duration input").val(paper_duration);
			$("#exampaper-type-select").val(paper_type);
			$("#add-update-exampaperid").text(paper_id);
		});
	},
	//将课程的信息写入到课程设置的弹出框页面
	bindUpdateProperty : function bindUpdateProperty(){
		$(".update-property").click(function(){
			$("#change-property-modal").modal({backdrop:true,keyboard:true});
			var display =$('#change-property-modal').css('display');
			if(display == 'none'){
				$("#change-property-modal").show();
			}
			var tr = $(this).parent().parent();
			var courseId = tr.find(".td-test-courseId").text();
			var courseName = tr.find(".td-test-courseName").text();
			var credit = tr.find(".td-test-credit").text();
			var  teacherName=tr.find(".td-test-teacherName").text();
			var coursetest=$(".add-update-ratecourse input").val();
			var rateTest=$(".add-update-rateTest input").val();
			$(".add-update-courseId input").val(courseId);
			$(".add-update-courseName input").val(courseName);
			$(".add-update-credit select").val(credit);
			$(".exampaper-type input").val(teacherName);
			$("#total").text(totalPeriod());
			initData();
		});
	},
	//老师列表
	bindTecherProperty : function bindTecherProperty(){
		$(".select-property").click(function(){
			$("#change-property-modal1").modal({backdrop:true,keyboard:true});
			listTeacher();
		});
	},

	//提交修改
	bindUpdateExampaper : function bindUpdateExampaper(){
		$("#update-exampaper-btn").click(function(){
			var verify_result = question_list.verifyInput();
			var courseId = $("#add-update-exampaperid").text();
			if (verify_result) {
				var data = new Object();
				data.courseId = courseId;
				data.courseName = $(".add-update-exampapername input").val();
				data.credit = $(".add-update-credit").text();
				$.ajax({
					headers : {
						'Accept' : 'application/json',
						'Content-Type' : 'application/json'
					},
					type : "POST",
					url : "/cy/universitycourseedit.action",
					data : JSON.stringify(data),
					success : function(message, tst, jqXHR) {
						if (!util.checkSessionOut(jqXHR))
							return false;
						if (message.result == "success") {
							util.success("修改成功", function(){
								window.location.reload();
							});
						} else {
							util.error("操作失败请稍后尝试:" + message.result);
						}

					},
					error : function(jqXHR, textStatus) {
						util.error("操作失败请稍后尝试");
					}
				});
			}
		});
	},
	
	verifyInput : function verifyInput() {
		$(".form-message").empty();
		$(".has-error").removeClass("has-error");
		var result = true;
		var r_checkName = question_list.checkName();
		var r_checkDuration = question_list.checkDuration();
		result = r_checkName && r_checkDuration;
		return result;
	},
	checkDuration : function checkDuration() {
		var duration = $(".add-update-duration input").val();
		if (duration == "") {
			$(".add-update-duration .form-message").text("请输入考试时长（如：120）");
			return false;
		} else if (isNaN(duration)) {
			$(".add-update-duration .form-message").text("请输入数字");
			return false;
		} else if (!(duration > 30 && duration < 241)) { 
			$(".add-update-duration .form-message").text("数字范围无效，考试的时长必须设置在30到240的范围内");
			return false;
		} else {
			return true;
		}
	},
	
	checkName : function checkName() {
		var name = $(".add-update-exampapername input").val();
		if (name == "") {
			$(".add-update-exampapername .form-message").text("请输入试卷名称");
			$(".add-update-exampapername input").focus();
			$(".add-update-exampapername input").addClass("has-error");
			return false;
		} else if (name.length > 10) {
			$(".add-update-exampapername .form-message").text("内容过长，请保持在10个字符以内");
			$(".add-update-exampapername input").focus();
			$(".add-update-exampapername input").addClass("has-error");
			return false;
		} else {
			return true;
		}
	},
};
//课程总学时
function totalPeriod(){
	var total = ($("#ratecourse").val() - 0) + ($("#rateTest").val() - 0)+($("#rateWork").val() - 0) ;
	$("#total").text(total);
	return total;
}
//计算课程总学时
$(".df-input-narrow1").blur(function(){
	var flag=true;
	if(isNaN($("#ratecourse").val()||($("#ratecourse").val())>100 ) ){
		$("#input").html("提示：请输入0-100的数");
		 flag=false;
		$("#ratecourse").focus();
	}
	else if(isNaN($("#rateTest").val())||($("#rateTest").val())>100){
		$("#input").html("提示：请输入0-100的数");
		 flag=false;
		$("#rateTest").focus();
	}
	else if(isNaN($("#rateWork").val())||($("#rateWork").val())>100){
		$("#input").html("提示：请输入0-100的数");
		 flag=false;
		$("#rateWork").focus();
	}
	else if( (($("#ratecourse").val() - 0) + ($("#rateTest").val() - 0)+($("#rateWork").val() - 0))>100){
		$("#input").html("提示：比率之和不能超过100！请仔细检查后重新输入！");
		 flag=false;
	}
	else if(flag==true){
		$("#input").html("");
	totalPeriod();
	}	
});
//选择课程负责人
function selectTeacher() {
	var teacherId = $('input[name="teacher"]:checked ').attr("id");
	var teacherName = $('input[name="teacher"]:checked ').val();
   $("#teacherId").val(teacherId);
   alert( $("#teacherId").val());
   $("#teacherName").val(teacherName);
};
$("#searchName").keyup(function(e){
	if(e.keyCode == 13) {
		listTeacher(0);
	}
}) ;