<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>后台——图表</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/basic.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_houtai_public.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/bg_houtai_study_progess.css"/>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/medioadaption.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bg_houtai_index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/highchart/highcharts.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/highchart/modules/exporting.js"></script>
</head>
<body>
<input type="hidden" id="universityId" value="${user.universityId }">
<!----头部区域-->	
<!-- 


<div class="bg_houtai_header clearfloat">
	<div class="header_logo_con">
    	<span class="header_logo_img">武汉纺织大学</span>
    </div>
    <div class="login_detail_con logined_zone">
    	<div class="user_ico" onClick="window.location.href='user_center.html'"><img src="images/user_center/user_img_01.jpg" alt=""/></div>
        <div class="user_txt"><span>莫妮卡</span>，欢迎你！</div>
        <div class="tips_ico_con">
            <span class="total_num">99</span>
            <div class="tips_down_pop">
                <img class="tips_arrow_up_ico" src="images/user_center/tips_arrow_up_ico.png" alt=""/>
                <div class="tips_down_pop_img_txt mb_12px">
                    <img class="tips_down_ico_01" src="images/user_center/tips_down_ico_01.png" alt=""/>
                    <span>站内私信</span>
                    <b class="add_num">+99</b>
                </div>
                <div class="tips_down_pop_img_txt">
                    <img class="tips_down_ico_01" src="images/user_center/tips_down_ico_02.png" alt=""/>
                    <span>系统消息</span>
                    <b class="add_num">+99</b>
                </div>
            </div>
        </div>
        <div class="logout_link">退出</div>
    </div>
</div>


 -->
<div class="inner_header">
	<jsp:include page="../head-school.jsp"/>
</div>
<div class="houtai_con clearfloat">

<!---中间内容区域  开始---->
<jsp:include page="bg_left.jsp"/>
        <div class="houtai_content_con">
        	<div class="houtai_right_con">
            	<ul class="houtai_right_nav clearfloat">
                	<li title="暂无资源">
                    	<img class="bg_index_ico" src="${pageContext.request.contextPath}/images/houtai/bg_index_ico_01.png" alt=""/>
                        <div class="houtai_right_nav_name">
                        	<table>
                            	<tr>
                                	<td>学</td>
                                    <td>校</td>
                                </tr>
                                <tr>
                                	<td>管</td>
                                    <td>理</td>
                                </tr>
                            </table>
                        </div>
                    </li>
                    <li onclick="studentmanage()"title="学生管理">
                    	<img class="bg_index_ico" src="${pageContext.request.contextPath}/images/houtai/bg_index_ico_02.png" alt=""/>
                        <div class="houtai_right_nav_name">
                        	<table >
                            	<tr>
                                	<td>学</td>
                                    <td>生</td>
                                </tr>
                                <tr>
                                	<td>管</td>
                                    <td>理</td>
                                </tr>
                            </table>
                        </div>
                    </li>
                    <li onclick="teachermanage()"title="老师管理">
                    	<img class="bg_index_ico" src="${pageContext.request.contextPath}/images/houtai/bg_index_ico_03.png" alt=""/>
                        <div class="houtai_right_nav_name" >
                        	<table>
                            	<tr>
                                	<td>老</td>
                                    <td>师</td>
                                </tr>
                                <tr>
                                	<td>管</td>
                                    <td>理</td>
                                </tr>
                            </table>
                        </div>
                    </li>
                    <li onclick="coursemanage()" title="课程管理">
                    	<img class="bg_index_ico" src="${pageContext.request.contextPath}/images/houtai/bg_index_ico_04.png" alt=""/>
                        <div class="houtai_right_nav_name">
                        	<table>
                            	<tr>
                                	<td>课</td>
                                    <td>程</td>
                                </tr>
                                <tr>
                                	<td>管</td>
                                    <td>理</td>
                                </tr>
                            </table>
                        </div>
                    </li>
                </ul>
            </div>
        	<div class="houtai_right_con clearfloat">
            	<div class="houtai_study_progess_left">
                	<div class="houtai_bread_title">选课人数分布</div>
                	
                    <div class="houtai_study_progess_litter_content" style="height:500px;"><div id="courses" style="min-width: 500px; max-width: 600px; height: 500px; margin: 0 auto;"></div></div>
                </div>
                <div class="houtai_study_progess_right">
                	<div class="houtai_bread_title">月登录人数统计</div>
                    <div class="houtai_study_progess_litter_content" style="height:500px;"><div id="logs" style="min-width: 500px; max-width: 1000px; height: 500px; margin: 0 auto;"></div></div>
                </div>
            </div>
        </div>
</div>
<script type="text/javascript">

function studentmanage(){
	window.location.href="${pageContext.request.contextPath}/cy/userlist.action";
}

function teachermanage(){
	window.location.href="${pageContext.request.contextPath}/cy/teacherlist.action";
}
function coursemanage(){
	window.location.href="${pageContext.request.contextPath}/cy/universitycourse.action";
}


function getColor(){
	var chars = ['a','b','c','d','e','f','0','1','2','3','4','5','6','7','8','9'];
	var color = "#";
	for(var i = 0; i < 6; i++){
		color += chars[Math.round(Math.random() * 15)];		
	}
	return color;
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

function drawCountReport(target,colors,datas){	
	 $(target).highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: null,
	            plotShadow: false
	        },
	        title: {
	            text: '选课人数分布'
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
	                    format: '<b>{point.name}</b>: {point.y} 人'
	                }
	            }
	        },
	        colors: ['#d87a80', 
	                 '#2ec7c9',
	                 '#ffb980', 
	                 '#5ab1ef',
	                 '#8d98b3', 
	                 '#97b552',
	                 '#b6a2de',
	                 '#e5cf0d', 
	                 '#ffff00'],
	        series: [{
	            type: 'pie',
	            name: '人数比例',
	            data: datas
	        }]
	    });
	}

function loadLogCount(){
	var universityId = $("#universityId").val();
	var url = "${pageContext.request.contextPath}/userLogger/list.action";
	var args = {"universityId" : universityId};
	$.post(url,args,function(data){
		var days = [];
		var logs = [];
		if(data != null){
			for(var i= 0 ;i<data.length; i++){
				days[i] = data[i]['logTime'];
				logs[i] = data[i]['counts'];
			}
			drawReport('#logs', 'line', '本月登录人数统计', '(本月登录人数统计)', days, ['#5ab1ef'], '登录人数(人)', '人', '登录人数', logs);
		}
	},'json');
	
	var url2 = "${pageContext.request.contextPath}/universityanalysis/report.action";
	$.post(url2,args,function(result){
		var colors = [];
		var courses = [];
		for(var i = 0 ; i < result.length; i++){
			colors[i] = getColor();
			courses[i] = [result[i].courseName,result[i].importCount];
		}
		drawCountReport('#courses',colors,courses);
	},'json');
}


$(function(){
	loadLogCount();
});

</script>
</body>
</html>
