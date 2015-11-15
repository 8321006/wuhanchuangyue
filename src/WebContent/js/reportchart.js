function analysisDay(targer,chartType,xaxis,yaxis){
	$(targer).highcharts(
			{
				chart: {
	            type: chartType
	        },
				title : {
					text : '本月学习进度汇总'
				//center
				},
				subtitle : {
					text : '（本月每日学习进度统计）'
				},
				xAxis : {
					categories : createDays()
				},
				colors : ['#53b2ea'],
				yAxis : {
					min : 0,
					max : 100,
					title : {
						text : '学习进度（%）'
					}
				},
				tooltip : {
					valueSuffix : '%'
				},
				legend : {
					layout : 'vertical',
					align : 'right',
					verticalAlign : 'middle',
					borderWidth : 0
				},
				series : [ {
					name : '学习进度',
					data : createDate5()
				} ]
			});
}

