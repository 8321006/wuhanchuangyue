function getpercent(){
/*环形进度条*/	
$(".pie").each(function(i){
var used = parseInt($(this).attr("percent"));
var notused = 100- parseInt(used);
var pieH=parseInt($('#pie_bg').css('height'));
var pieword_x=-3*pieH/7;
var pieword_y=-pieH/8;
pie = new Highcharts.Chart({
		chart: {
			renderTo:'pie'+i,
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
			margin:0,
			padding:0,
			height:pieH,
			 
        },	 
     
		 title: {                
                text: '', 
                y:-10, 
				align: 'center',
				verticalAlign:'bottom',                           
                 style: {                    
                        fontFamily: '微软雅黑',
						color:'#3e454d',
                    }
			},	
		xAxis:{
			title: false,
			lineWidth:45,
			labels:{
				enabled: false
				}
			},
		yAxis:{
			title: false,
			labels:{
				enabled: false
				}
			},
        tooltip: {
			enabled:false    	   
        },
		
        plotOptions: {
            pie: {
                	slicedOffset: 0,         
                    dataLabels: {
                    enabled: false,
                    color: '#3e454d',
                    connectorColor: '',
					distance: pieword_x,					
					y:pieword_y,
					verticalAlign:'middle',
					align: 'center',   
                    format: '{point.percentage:.0f}%' // 保留几位小数
                
				}
            }
        },
        series: [{
            type: 'pie',
            name: 'Browser share',
            data: [{
					y:used,//已用
					color:'#3e454d',
					dataLabels: {
                    enabled: false                    
                }},
			     {
                    y:notused,//剩余
					color:'#80cdff',
					enabled: false 
					
                }
				
            ],
			size:  pieH,
            innerSize: pieH-8,
			startAngle:0
        }]
    });	
});
}
