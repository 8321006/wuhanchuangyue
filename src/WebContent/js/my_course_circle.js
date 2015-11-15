// x 已用， y  剩余
function initCircle(pie,pie_bg,x,y){
/*环形进度条*/	
//var pieH=parseInt($('#pie_bg').css('height'));
var pieH=parseInt($("#"+pie_bg).css('height'));
var pieword_x=-3*pieH/7;
var pieword_y=-pieH/12;
pie = new Highcharts.Chart({
		chart: {
			renderTo:pie,
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false,
			margin:0,
			padding:0,
			height:pieH
        },	 
     
		 title: {                
                text: '', 
                y:-10, 
				align: 'center',
				verticalAlign:'bottom',                           
                 style: {                    
                        //fontSize: '15px',
                        fontFamily: '微软雅黑',
						color:'#898989',
                    }
			},	
		xAxis:{
			title: false,
			lineWidth:0,
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
                    enabled: true,
                   // color: '#F00',
                    connectorColor: '',
					distance: pieword_x,					
					y:pieword_y,
					
					style:{
						color:'#4dd8fb',
					    fontSize: pieH/4,
                        fontFamily: '微软雅黑'
						//fontWeight:'blod'											
					},
					verticalAlign:'middle',
					align: 'center',   
                    format: '{point.percentage:.0f}%' //{point.percentage:.1f}
                
				}
            }
        },
        series: [{
            type: 'pie',
            name: 'Browser share',
            data: [{
					//y:40,//已用
					y:x,//已用
					color:'#fff',
					dataLabels: {
                    enabled: false                    
                }},
			     {
                	//y:60,//剩余
                    y:y,//剩余
					color:'#4dd8fb',
					 sliced: true
					
                }
				
            ],
			size:  pieH,
            innerSize: pieH-10,
			startAngle:0
        }]
    });	
}
	
