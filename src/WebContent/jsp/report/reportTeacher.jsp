<%@page import="com.cy.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>学情分析 - 易启学平台</title>
<base href="<%=basePath %>" />
<link type="text/css" rel="stylesheet" href="css/basic.css"/>
<link type="text/css" rel="stylesheet" href="css/study_report.css"/>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/study_report.js"></script>
<script type="text/javascript" src="resources/highchart/highcharts.js"></script>
<script type="text/javascript" src="resources/highchart/modules/exporting.js"></script>
<%
User user = (User)session.getAttribute("user");
if(user == null){
	response.sendRedirect(basePath+"index.action");
}
%>
<script type="text/javascript">
$(function () {
		var classId = $('#classId').val();
		var timebucket = $('#timebucket').val();
		choseReport(classId,timebucket);
	});
	
	function drawExamReport(target,data1,data2,data3){	
		 $(target).highcharts({
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false
		        },
		        title: {
		            text: '考试成绩分布'
		        },
		        tooltip: {
		    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		        },
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    color: '#000000',
		                    connectorColor: '#000000',
		                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
		                }
		            }
		        },
		        colors : ['#ff634d','#53b2ea','#28b779' ],
		        series: [{
		            type: 'pie',
		            name: '人数比例',
		            data: [
		                ['60分以下',   data1],
		                {
		                    name: '60到80分之间',
		                    y: data2,
		                    sliced: true,
		                    selected: true
		                },
		                ['高于80分以上',    data3]
		            ]
		        }]
		    });
		}
	
	
		function drawReportWithMax(targer,type,title,subtitle,xAxis,color,ytitle,valueSuffix,series,yAxis){
			$(targer).highcharts(
					{
						chart: {
			            type: type
			        },
						title : {
							text : title
						//center
						},
						subtitle : {
							text : subtitle
						},
						xAxis : {
							categories : xAxis
						},
						colors : color,
						yAxis : {
							min : 0,
							max : 100,//设置最大值
							title : {
								text : ytitle
							}
						},
						tooltip: {
				            enabled: true,
				            valueSuffix: valueSuffix,
				            formatter: function() {
				                return '<b>'+ this.series.name +'</b><br>'+this.x +' : '+ this.y +valueSuffix;
				            }
				        }, legend: {
				            layout: 'vertical',
				            align: 'right',
				            verticalAlign: 'middle',
				            borderWidth: 0
				        },
				        plotOptions: {
				            line: {
				                dataLabels: {
				                    enabled: true
				                },
				                enableMouseTracking: true
				            }
				        },
						series : [ {
							name : series,
							data : yAxis
						} ]
					});
		}
		//y轴没有最大值
		function drawReport(targer,type,title,subtitle,xAxis,color,ytitle,valueSuffix,series,yAxis){
			$(targer).highcharts(
					{
						chart: {
			            type: type
			        },
						title : {
							text : title
						//center
						},
						subtitle : {
							text : subtitle
						},
						xAxis : {
							categories : xAxis
						},
						colors : color,
						yAxis : {
							min : 0,
							title : {
								text : ytitle
							}
						},
						tooltip: {
				            enabled: true,
				            valueSuffix: valueSuffix,
				            formatter: function() {
				                return '<b>'+ this.series.name +'</b><br>'+this.x +' : '+ this.y +valueSuffix;
				            }
				        },
				        legend: {
				            layout: 'vertical',
				            align: 'right',
				            verticalAlign: 'middle',
				            borderWidth: 0
				        },
				        plotOptions: {
				            line: {
				                dataLabels: {
				                    enabled: true
				                },
				                enableMouseTracking: true
				            }
				        },
						series : [ {
							name : series,
							data : yAxis
						} ]
					});
		}	
	function choseReport(classId,timebucket){
		var url = 'teacherAnalysis/report.action';
		var data = {'classId' : classId,'timebucket' : timebucket};
		$.ajax({
			type : "POST",
			url : url,
			data : data,
			dataType : 'json',
			success : function(data){
				if(data.days.length != 0){
				var rates = data.rates;
				var comms = data.comments;
				var tasks = data.tasks;
				var quizs = data.quizs;
				var videos = data.videos;
				var days = data.days;
				var scoreRates = data.scoreRates;
				if(scoreRates != null){
					var data1 = scoreRates['lrate'];
					var data2 = scoreRates['brate'];
					var data3 = scoreRates['mrate'];
					$('#scoreRates').css('display','block');
					drawExamReport('#rates',data1,data2,data3);
				}else{
					$('#scoreRates').css('display','none');					
					$('#rates').html('<img style="width: 520px; height: 200px; margin: 0 auto;" src="images/study_report/noreport.png"/>');					
				}
				drawReportWithMax('#leanringRate', 'line', '本月学习进度汇总', '(本月每日学习进度统计)', days, ['#00AEFF'], '学习进度(%)', '%', '学习进度', rates);
				drawReport('#tasks', 'line', '本月学习课程作业汇总', '(本月每日课程作业统计)', days, ['#ff634d'], '完成作业(次)', '次', '完成作业', tasks);
				drawReport('#comments', 'line', '本月学习课程评论汇总', '(本月每日课程评论统计)', days, ['#c180e6'], '提交评论(次)', '次', '提交评论', comms);
				drawReport('#quizs', 'line', '本月学习课程提问汇总', '(本月每日课程提问统计)', days, ['#f2ae43'], '提交提问(次)', '次', '提交提问', quizs);
				drawReport('#videos', 'line', '本月学习课程视频汇总', '(本月每日课程视频统计)', days, ['#28b779'], '观看视频(分钟)', '分钟', '观看视频', videos);
				}else{
					noReport();
					//alert('暂时没有相关数据');
				}
			},
			error : function(){
				noReport();
				alert("查询出错,请稍后再试！");
			}
		});
	}
	function noReport(){
		var image = '<img style="width: 520px; height: 200px; margin: 0 auto;" src="images/study_report/noresult.png"/>';
		$("#leanringRate").html(image);
		$("#tasks").html(image);
		$("#comments").html(image);
		$("#quizs").html(image);
		$("#videos").html(image);
		$("#scoreRates").html(image);		
	}
	
</script>
</head>
<!----详情弹出框--->
<div class="pop_msg_school_seq">
	<div class="school_seq_title">学情分析<img class="report_pop_close" src="images/study_report/report_pop_close.png" alt=""/></div>
    <div class="school_info_detail">
    	<table class="school_info_table" cellpadding="0" cellspacing="0" border="0">
        	<thead>
            	<tr>
                	<td>学生姓名</td>
                    <td>学习进度</td>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${details }" var="detail">
                <tr>
                	<td>${detail.studentName }</td>
                	<td>${detail.learnRate }</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="school_info_fenye">
    	<div class="school_info_fenye_list">
        	<div class="school_info_fenye_num clearfloat">
            	<span class="fenye_arr_pre">前一页</span>
                <b class="fenye_num_box">1</b>
                <b class="fenye_num_box">2</b>
                <b class="fenye_num_box">3</b>
                <b class="fenye_fenge">...</b>
                <span class="fenye_arr_next">后一页</span>
                <div class="all_num_txt">共<span>32</span>页，到第<input type="text"/>页</div>
                <span class="fenye_confirm_btn">确认</span>
            </div>
        </div>
    </div>
</div>
<div class="pop_msg_bg_school_seq"></div>


<!----报告正文--->
<body style="background:#2c2c2c;">
<div class="study_report_container">
    <div class="sr_con_content">
    	<div class="sr_con1_bg_top">
        	<div class="sr_con1_bg_top_block">
            	<div>武汉南湖十大高校联盟</div>
                <p>2015年春夏学期共享课程学情报告</p>
            </div>
        </div>
        <div class="sr_con1_bg_bottom">
        	<div class="sr_con1_bg_bottom_title clearfloat">
            	<div class="sr_con1_bg_bottom_titletxt">${currClass.className }</div>
                <div class="sr_con1_bg_bottom_option">
                	<div class="sr_con1_option_div"><b>请选择月学情分析报告</b><span class="option_angle_ico"></span></div>
                    <input type="hidden" id="courseId" value=${courseId }>
                    <input type="hidden" id="timebucket" value=${timebucket }>
                    <input type="hidden" id="classId" value=${classId }>
                    <ul class="sr_con1_option_down">
                    <c:forEach items="${months }" var="month">
                    	<li onclick="choseReport('${classId }','${month.value }')" value=${month.value }>${month.key }学情分析报告</li>
                    </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="sr_con1_bg_bottom_tips">
            	<div>学情报告每周由不拉易启学平台在每周最后一天24:00生成</div>
                <div>统计数据从本周第一天0:00到本周最后一天23:59截止</div>
            </div>
        </div>
    </div>
</div>
<div class="study_report_container">
    <div class="sr_con_content">
    	<div class="sr_con2_bg_top">
        	<div class="sr_con2_header clearfloat">
            	<img src="images/administrator/admin_logo.png"/>
                <b>月学情质量报告</b>
            </div>
        </div>
        <div class="sr_con2_bg_bottom">
        	<h6>尊敬的${user.realName }，您好！</h6>
            <div class="sr_con2_bg_bottom_txt"><b></b>您在易启学平台讲授的课程《${currCourse.courseName }》学习情况统计如下：</div>
            <div class="sr_con2_bg_bottom_tit">1、学习进度</div>
            <div class="sr_con2_bg_bottom_txt"><b></b>您的班级总人数：<font color="#53b2ea" size="5">${studentCount }</font>人.</div>
            <div class="sr_con2_bg_bottom_chat">
            	<p>图1</p>
                <div id="leanringRate" style="min-width: 310px; max-width: 1400px; height: 300px; margin: 0 auto;text-align: center;"></div>
            </div>
        </div>
    </div>
</div>
<div class="study_report_container">
    <div class="sr_con_content">
    	<div class="sr_con2_bg_top">
        	<div class="sr_con2_header clearfloat">
            	<img src="images/administrator/admin_logo.png"/>
                <b>月学情质量报告</b>
            </div>
        </div>
        <div class="sr_con2_bg_bottom">
            <div class="sr_con2_bg_bottom_tit">2、学情分析</div>
            <div class="sr_con2_bg_bottom_txtcon">根据您讲授班级学习的情况，将从<font color="#53b2ea" size="3">课程作业，课程讨论，课程提问，课程视频</font>以及<font color="#53b2ea" size="3">课程考试</font>5个角度做全面详细的分析，以便让你全方位快速地了解您讲授的课程情况。</div>
            <div class="sr_con2_bg_bottom_chat">
            	<div class="sr_con2_bg_bottom_sectit clearfloat">
                	<div class="sr_con2_bg_bottom_tit">2.1、课程作业</div>
                    
                </div>
                <p>图2</p>
                <div id="tasks" style="min-width: 310px; max-width: 1400px; height: 300px; margin: 0 auto;text-align: center;"></div>
            </div>
        </div>
    </div>
</div>
<div class="study_report_container">
    <div class="sr_con_content">
    	<div class="sr_con2_bg_top">
        	<div class="sr_con2_header clearfloat">
            	<img src="images/administrator/admin_logo.png"/>
                <b>月学情质量报告</b>
            </div>
        </div>
        <div class="sr_con2_bg_bottom">
        <div class="sr_con2_bg_bottom_txtcon">根据您讲授班级学习的情况，将从<font color="#53b2ea" size="3">课程作业，课程讨论，课程提问，课程视频</font>以及<font color="#53b2ea" size="3">课程考试</font>5个角度做全面详细的分析，以便让你全方位快速地了解您讲授的课程情况。</div>
            <div class="sr_con2_bg_bottom_chat">
            	<div class="sr_con2_bg_bottom_sectit clearfloat">
                	<div class="sr_con2_bg_bottom_tit">2.2、课程讨论</div>
                  
                </div>
                <p>图3</p>
                <div id="comments" style="min-width: 310px; max-width: 1400px; height: 300px; margin: 0 auto;text-align: center;"></div>
            </div>
        </div>
    </div>
</div>
<div class="study_report_container">
    <div class="sr_con_content">
    	<div class="sr_con2_bg_top">
        	<div class="sr_con2_header clearfloat">
            	<img src="images/administrator/admin_logo.png"/>
                <b>月学情质量报告</b>
            </div>
        </div>
        <div class="sr_con2_bg_bottom">
        <div class="sr_con2_bg_bottom_txtcon">根据您讲授班级学习的情况，将从<font color="#53b2ea" size="3">课程作业，课程讨论，课程提问，课程视频</font>以及<font color="#53b2ea" size="3">课程考试</font>5个角度做全面详细的分析，以便让你全方位快速地了解您讲授的课程情况。</div>
            <div class="sr_con2_bg_bottom_chat">
            	<div class="sr_con2_bg_bottom_sectit clearfloat">
                	<div class="sr_con2_bg_bottom_tit">2.3、课程提问</div>
                    
                </div>
                <p>图4</p>
                <div id="quizs" style="min-width: 310px; max-width: 1400px; height: 300px; margin: 0 auto;text-align: center;"></div>
            </div>
        </div>
    </div>
</div>
<div class="study_report_container">
    <div class="sr_con_content">
    	<div class="sr_con2_bg_top">
        	<div class="sr_con2_header clearfloat">
            	<img src="images/administrator/admin_logo.png"/>
                <b>月学情质量报告</b>
            </div>
        </div>
        <div class="sr_con2_bg_bottom">
        <div class="sr_con2_bg_bottom_txtcon">根据您讲授班级学习的情况，将从<font color="#53b2ea" size="3">课程作业，课程讨论，课程提问，课程视频</font>以及<font color="#53b2ea" size="3">课程考试</font>5个角度做全面详细的分析，以便让你全方位快速地了解您讲授的课程情况。</div>
            <div class="sr_con2_bg_bottom_chat">
            	<div class="sr_con2_bg_bottom_sectit clearfloat">
                	<div class="sr_con2_bg_bottom_tit">2.4、课程视频</div>
                    
                </div>
                <p>图5</p>
                <div id="videos" style="min-width: 310px; max-width: 1400px; height: 300px; margin: 0 auto;text-align: center;"></div>
            </div>
        </div>
    </div>
</div>
<div class="study_report_container">
    <div class="sr_con_content">
    	<div class="sr_con2_bg_top">
        	<div class="sr_con2_header clearfloat">
            	<img src="images/administrator/admin_logo.png"/>
                <b>月学情质量报告</b>
            </div>
        </div>
        <div class="sr_con2_bg_bottom">
        <div class="sr_con2_bg_bottom_txtcon">根据您讲授班级学习的情况，将从<font color="#53b2ea" size="3">课程作业，课程讨论，课程提问，课程视频</font>以及<font color="#53b2ea" size="3">课程考试</font>5个角度做全面详细的分析，以便让你全方位快速地了解您讲授的课程情况。</div>
            <div class="sr_con2_bg_bottom_chat">
            	<div class="sr_con2_bg_bottom_sectit clearfloat">
                	<div class="sr_con2_bg_bottom_tit">2.5、课程考试</div>
                   
                </div>
                <p>图6</p>
                <c:if test="${empty scoreRates  }">
                 <div id="exams" style="min-width: 310px; max-width: 1400px; height: 300px; margin: 0 auto;text-align: center;"><img style="width: 520px; height: 200px; margin: 0 auto;" src="images/study_report/noreport.png"/></div>
                </c:if>
                <c:if test="${!empty scoreRates  }">
                 <div id="rates" style="min-width: 310px; max-width: 1400px; height: 300px; margin: 0 auto;text-align: center;"></div>
                </c:if>
            </div>
        </div>
    </div>
</div>
<div class="study_report_container">
    <div class="sr_con_content">
    	<div class="sr_con2_bg_top">
        	<div class="sr_con2_header clearfloat">
            	<img src="images/administrator/admin_logo.png"/>
                <b>月学情质量报告</b>
            </div>
        </div>
        <div class="sr_con2_bg_bottom">
            <div class="sr_con2_bg_bottom_tit">2、学情分析</div>
            <div class="study_report_table_detail">
                <table class="school_info_table" cellpadding="0" cellspacing="0" border="0">
                   <thead>
                   <tr>
                   <td>学生姓名</td>
                   <td>学习进度(%)</td>
                   <td>课程作业(次)</td>
                   <td>课程评论(次)</td>
                   <td>课程提问(次)</td>
                   <td>观看视频(分钟)</td>
                   </tr>
                   </thead>
				<tbody>
				<c:forEach items="${details }" var="detail">
				<tr>
				<td>${detail.studentName }</td>
				<td>${detail.learnRate == null ? 0 : detail.learnRate}</td>
				<td>${detail.taskCount == null ? 0 : detail.taskCount}</td>
				<td>${detail.commentCount == null ? 0 : detail.commentCount}</td>
				<td>${detail.quizCount == null ? 0 : detail.quizCount}</td>
				<td>${detail.videoCount== null ? 0 : detail.videoCount}</td>
				</tr>
				</c:forEach>
				</tbody>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
