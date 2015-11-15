<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="com.cy.model.User"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>统计分析 - 易启学后台管理</title>
<link type="text/css" rel="stylesheet" href="css/basic.css"/>
<link type="text/css" rel="stylesheet" href="css/medioadaption.css"/>
<link type="text/css" rel="stylesheet" href="css/bg_administrator_public.css"/>
<link type="text/css" rel="stylesheet" href="css/bg_administrator_studyanalysis.css"/>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bg_administrator_index.js"></script>
<script type="text/javascript" src="resources/highchart/highcharts.js"></script>
<script type="text/javascript" src="resources/highchart/modules/exporting.js"></script>
<%
User current = (User)session.getAttribute("user");
if(current == null || current.getUserType() != 3){
	response.sendRedirect(basePath + "index.action");
}
%>
</head>
<body>
<input type="hidden" id="courseTerm" name="courseTerm" value="${courseTerm }">
<!---中间内容区域  开始---->
<div class="administrator_container">
	<div class="bg_administrator_con">
    	<div class="administrator_left_menu">
        	<div class="administrator_img_con">
            	<div class="administrator_img_content">
                	<img class="administrator_person_img" src="images/administrator/person_img_01.png" alt=""/>
                </div>
                <p class="administrator_person_name">${user.realName }
            </div>
            <div class="administrator_left_menu_nav">
            	<ul class="administrator_menu_nav">
                    <li>
                        <span>
                            <b class="administrator_ico_1"></b>
                            <a href="university/listAll.action"><i>学校管理</i></a>
                        </span> 
                    </li>
                    <li>
                        <span>
                            <b class="administrator_ico_2"></b>
                            <a href="course/courseList.action"><i>新增课程</i></a>
                        </span> 
                    </li>
                    <li>
                        <span>
                            <b class="administrator_ico_3"></b>
                            <a href="cy/userImportList.action"><i>学生管理</i></a>
                        </span> 
                    </li>
                    <li class="curr">
                        <span>
                            <b class="administrator_ico_4"></b>
                            <a href="university/goAnalysis.action"><i>统计分析</i></a>
                        </span> 
                    </li>
                    <li>
                        <span>
                            <b class="administrator_ico_5"></b>
                            <a href="survey/coursesurveylist.action"><i>教学调查</i></a>
                        </span> 
                    </li>
                    <li>
                        <span>
                            <b class="administrator_ico_5"></b>
                            <a href="${pageContext.request.contextPath}/notice/gosystemNotice.action"><i>新闻通知</i></a>
                        </span> 
                    </li>
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
                	<span>统计分析</span>
                </div>
                <div class="administrator_studyanalysis_con">
                	<div class="administrator_studyanalysis_content">
                    	<div id="logs" style="min-width: 310px; max-width: 1800px; height: 300px; margin: 0 auto;text-align: center;"></div>
                    </div>
                    <br>
                	<div class="administrator_studyanalysis_content">
                    	<div id="counts" style="min-width: 310px; max-width: 1800px; height: 300px; margin: 0 auto;text-align: center;"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
	
</div>
<script type="text/javascript">
$(function(){
	loadLogCount();
});
debugger

function loadLogCount(){
	var url = "${pageContext.request.contextPath}/userLogger/listAll.action";
	debugger
	$.post(url,{},function(data){
		var days = [];
		var logs = [];
		if(data != null){
			for(var i= 0 ;i<data.length; i++){
				days[i] = data[i]['logTime'];
				logs[i] = data[i]['counts'];
			}
			drawReport('#logs', 'line', '本月登录人数统计', '(本月登录人数统计)', days, ['#ff634d'], '登录人数(人)', '人', '登录人数', logs);
		}
	},'json');
	// url2 为t_course_university_analysis表的数据
	var url2 = "${pageContext.request.contextPath}/universityanalysis/reportAll.action";
	var courseTerm = $("#courseTerm").val();
	var args = {"courseTerm" : courseTerm};
	$.post(url2,args,function(data){
		var categories = [];
		var imports = [];
		var finishs = [];
		if(data != null){
			for(var i= 0 ;i<data.length; i++){
				categories[i] = data[i].courseName;
				imports[i] = data[i].importCount;
				finishs[i] = data[i].finishCount;
			}
			draw("#counts",categories,imports,finishs);
		}
	},'json');
}

function draw(targer,categories,imports,finishs){
	$(targer).highcharts(
				{
					chart : {
						type : 'column'
					},
					title : {
						text : '课程选课人数分析'
					},
					subtitle : {
						text : '--选课人数情况'
					},
					xAxis : {
						categories : categories
					},
					yAxis : {
						min : 0,
						title : {
							text : '选课人数(人)'
						}
					},
					colors : ['#53b2ea','#ff634d'],
					tooltip : {
						headerFormat : '<span style="font-size:10px">{point.key}</span>',
						pointFormat : '' + '',
						footerFormat : '<table><tbody><tr><td style="color:{series.color};padding:0">{series.name}: </td><td style="padding:0"><b>{point.y} 人</b></td></tr></tbody></table>',
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
						name : '导入人数',
						data : imports,
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
						name : '完成人数',
						data : finishs,
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
					}]
				});
}


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
