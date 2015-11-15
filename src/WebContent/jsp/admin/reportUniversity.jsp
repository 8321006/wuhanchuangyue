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
		
		
	function drawRateReport(target,title,xAxis,yAxis,data60,data80,data100){
		$(target).highcharts({
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: title
	        },
	        xAxis: {
	            categories: xAxis
	        },
	        yAxis: {
	            min: 0,
	            title: {
	                text: yAxis
	            },
	            stackLabels: {
	                enabled: true,
	                style: {
	                    fontWeight: 'bold',
	                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
	                }
	            }
	        },
	        legend: {
	            align: 'right',
	            x: -70,
	            verticalAlign: 'top',
	            y: 20,
	            floating: true,
	            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid) || 'white',
	            borderColor: '#CCC',
	            borderWidth: 1,
	            shadow: false
	        },
	        colors : ['#ff634d','#28b779','#00AEFF' ],
	        tooltip: {
	        	formatter: function() {
	                return '<b>'+ this.x +'</b><br>'+
	                    this.series.name +': '+ this.y +'<br>'+
	                    'Total: '+ this.point.stackTotal;
	            }
	        },
	        plotOptions: {
	        	 column: {
	                 stacking: 'normal',
	                 dataLabels: {
	                     enabled: true,
	                     color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
	                 }
	             }
	        },
	            series: [{
	            name: '分数低于60',
	            data: data60
	        }, {
	            name: '分数在60-80之间',
	            data: data80
	        }, {
	            name: '分数高于80',
	            data: data100
	        }]
	    });
	}
		
	function drawReport(target,title,subtitle,xAxis,yAxis,series1,series2,importCount,finishCount){		
		$(target)
				.highcharts(
						{
							chart : {
								type : 'column'
							},
							title : {
								text : title
							},
							subtitle : {
								text : subtitle
							},
							xAxis : {
								categories : xAxis
							},
							yAxis : {
								min : 0,
								title : {
									text : yAxis
								}
							},
							colors : ['#53b2ea','#ff634d' ],
							tooltip : {
								headerFormat : '<span style="font-size:10px">{point.key}</span>',
								pointFormat : '' + '',
								footerFormat : '<table><tbody><tr><td style="color:{series.color};padding:0">{series.name}: </td><td style="padding:0"><b>{point.y:.1f} 人</b></td></tr></tbody></table>',
								shared : true,
								useHTML : true
							},
							plotOptions : {
								column : {
									pointPadding : 0,
									borderWidth : 0
								}
							},
							series : [ {
								name : series1,
								data : importCount,
								dataLabels : {
									enabled : true,
									rotation : -90,
									color : '#FFFFFF',
									align : 'right',
									x : 4,
									y : 10,
									style : {
										fontSize : '13px',
										fontFamily : 'Verdana, sans-serif',
										textShadow : '0 0 3px black'
									}
								}
							}, {
								name : series2,
								data : finishCount,
								dataLabels : {
									enabled : true,
									rotation : -90,
									color : '#FFFFFF',
									align : 'right',
									x : 4,
									y : 10,
									style : {
										fontSize : '13px',
										fontFamily : 'Verdana, sans-serif',
										textShadow : '0 0 3px black'
									}
								}
							} ]
						});

	}
	$(function(){
		var courseTerm = $("#courseTerm").val();
		choseReport(courseTerm);
	})
	
	function choseReport(courseTerm){
		var url = 'universityanalysis/report.action';
		var universityId = $('#universityId').val();
		var data = {'universityId' : universityId,'courseTerm' : courseTerm};
		$.ajax({
			type : "POST",
			url : url,
			data : data,
			dataType : 'json',
			success : function(data){
				if(data != null && data.length != 0){
					var course = [];
					var importCount = [];
					var finishCount = [];
					var passCount = [];
					var failCount = [];
					var sixCount = [];
					var eightCount = [];
					var tenCount = [];
					var html = "<thead><tr><td>课程</td><td>班级</td><td>老师</td><td>课程状态</td><td>导入人数</td><td>完成人数</td><td>及格人数</td><td>不及格人数</td><td>低于60人数</td><td>60-80之间人数</td><td>高于80人数</td></tr></thead>";					
					html = html +"<tbody>";
					for(var i = 0;i<data.length;i++){
						course[i] = data[i].courseName;
						importCount[i] = data[i].importCount;
						finishCount[i] = data[i].finishCount;
						passCount[i] = data[i].passCount;
						failCount[i] = data[i].failCount;
						sixCount[i] = data[i].scoreSix;
						eightCount[i] = data[i].scoreEight;
						tenCount[i] = data[i].scoreTen;
						html = html +"<tr>";
						html = html +"<td>"+data[i].courseName+"</td>";
						html = html +"<td>"+data[i].className+"</td>";
						html = html +"<td>"+data[i].teacherName+"</td>";
						var courseState = data[i].courseState == 1 ? '结束' : '未结束';
						html = html +"<td>"+ courseState +"</td>";
						html = html +"<td>"+data[i].importCount+"</td>";
						html = html +"<td>"+data[i].finishCount+"</td>";
						html = html +"<td>"+data[i].passCount+"</td>";
						html = html +"<td>"+data[i].failCount+"</td>";
						html = html +"<td>"+data[i].scoreSix+"</td>";
						html = html +"<td>"+data[i].scoreEight+"</td>";
						html = html +"<td>"+data[i].scoreTen+"</td>"; 
						html = html +"</tr>";
					}
					html = html +"</tbody>";
					$("#courses").html(html);
					drawReport("#report1","课程人数分析","选课人数/完成人数对比",course,"选课人数/人","选课人数","完成人数",importCount,finishCount);
					drawReport("#report2","课程人数分析","及格人数/不及格人数对比",course,"人数/人","及格人数","不及格人数",passCount,failCount);
					drawRateReport("#report3","课程成绩分析",course,"成绩人数/人",sixCount,eightCount,tenCount);
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
            	<div class="sr_con1_bg_bottom_titletxt">${university.universityName }</div>
                <div class="sr_con1_bg_bottom_option">
                	<div class="sr_con1_option_div"><b>请选择学年学情分析报告</b><span class="option_angle_ico"></span></div>
                    <input type="hidden" id="courseTerm" value=${terms[0] }>
                    <input type="hidden" id="universityId" value=${user.universityId }>
                    <ul class="sr_con1_option_down">
                    <c:forEach items="${terms }" var="term">
                    	<li onclick="choseReport('${term }')">${term }学情分析报告</li>
                    </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="sr_con1_bg_bottom_tips">
            	<div>学情报告每学年结课后由易启学平台自动生成</div>
                <div>统计数据从本学年第一天0:00到本周最后一天23:59截止</div>
        </div>
    </div>
</div>
</div>
<div class="study_report_container">
    <div class="sr_con_content">
    	<div class="sr_con2_bg_top">
        	<div class="sr_con2_header clearfloat">
            	<img src="images/administrator/admin_logo.png"/>
                <b>学年课程分析报告</b>
            </div>
        </div>
        <div class="sr_con2_bg_bottom">
        	<h6>尊敬的${user.realName }，您好！</h6>
            <div class="sr_con2_bg_bottom_txt"><b></b>您在易启学平台选择的课程学习情况统计如下：</div>
            <div class="sr_con2_bg_bottom_chat">
            <p>表1</p>
            	 <table id="courses" class="school_info_table" cellpadding="0" cellspacing="0" border="0" style="text-align: center;">

                </table>
            </div>
        </div>
    </div>
</div>
<div class="study_report_container">
    <div class="sr_con_content">
    	<div class="sr_con2_bg_top">
        	<div class="sr_con2_header clearfloat">
            	<img src="images/administrator/admin_logo.png"/>
                <b>学年课程分析报告</b>
            </div>
        </div>
        <div class="sr_con2_bg_bottom">
        	<h6>尊敬的${user.realName }，您好！</h6>
            <div class="sr_con2_bg_bottom_txt">您在易启学平台选择的课程分析如下
            <div class="sr_con2_bg_bottom_tit">1、选课数/完成数对比</div>
            <div class="sr_con2_bg_bottom_chat">
            	<p>图1</p>
                <div id="report1" style="min-width: 310px; max-width: 1400px; height: 300px; margin: 0 auto;text-align: center;"></div>
            </div>
        </div>
    </div>
</div>
</div>
<div class="study_report_container">
    <div class="sr_con_content">
    	<div class="sr_con2_bg_top">
        	<div class="sr_con2_header clearfloat">
            	<img src="images/administrator/admin_logo.png"/>
                <b>学年课程分析报告</b>
            </div>
        </div>
        <div class="sr_con2_bg_bottom">
        	<h6>尊敬的${user.realName }，您好！</h6>
            <div class="sr_con2_bg_bottom_txt">您在易启学平台选择的课程分析如下
            <div class="sr_con2_bg_bottom_tit">2、及格人数/不及格人数对比</div>
            <div class="sr_con2_bg_bottom_chat">
            	<p>图2</p>
                <div id="report2" style="min-width: 310px; max-width: 1400px; height: 300px; margin: 0 auto;text-align: center;"></div>
            </div>
        </div>
    </div>
</div>
</div>
<div class="study_report_container">
    <div class="sr_con_content">
    	<div class="sr_con2_bg_top">
        	<div class="sr_con2_header clearfloat">
            	<img src="images/administrator/admin_logo.png"/>
                <b>学年课程分析报告</b>
            </div>
        </div>
        <div class="sr_con2_bg_bottom">
        	<h6>尊敬的${user.realName }，您好！</h6>
            <div class="sr_con2_bg_bottom_txt">您在易启学平台选择的课程分析如下
            <div class="sr_con2_bg_bottom_tit">3、课程分数人数分布</div>
            <div class="sr_con2_bg_bottom_chat">
            	<p>图3</p>
                <div id="report3" style="min-width: 310px; max-width: 1400px; height: 300px; margin: 0 auto;text-align: center;"></div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>
