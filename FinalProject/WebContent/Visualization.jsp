<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Visualizing</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/mycloud.css" rel="stylesheet">
<script src = "js/echarts.min.js"></script>
<script>

</script>
</head>

<body>
	<div class="navbar">
  		<a href="start.html">Back</a>
	</div>
	<div class="header">
  		<h1 id="region"></h1>
  		<p id="date"></p>
	</div>
 

 
	<div class="row">
	
  		<div class="main">
      		<div id = "association" style="width: 1000px; height:1000px;"></div>
  		</div>
  		
  		<div class="rightside">
  	  		<div id = "pie1" style = "width: 350px; height:300px; border: 1px solid black;background-color:white"></div>
  	  		<div id = "pie2" style = "width: 350px; height:300px; border: 1px solid black;background-color:white"></div>
  	  		<div id = "pie3" style = "width: 350px; height:400px; border: 1px solid black;background-color:white"></div>
  		</div>
  		
	</div>
 
	<div class="footer">
  		<div class = "left">
  			<div id = "Line" style = "width: 600px; height:300px;"></div>
  		</div>
  	<div class = "right">
  		<div id = "Parallel" style = "width: 700px; height:300px"></div>
  	</div>
	</div>
</body>
<script type="text/javascript">
	/**Match the incoming data package into the container in JS**/
	function matching(){
		/**initiate the parameter of the spiral**/
    	let a = 0.05;
		let i;
		let j = 0;
		let n = 0;
		/**Map the incoming data into available form**/
		for(i=0;i<date.length+10*365;i++){
			/**Generate Archimedia sprial**/
			let angle = i*Math.PI/183;
			let radius = a*angle;
			let x = radius*angle*Math.cos(angle);
			let y = radius*angle*Math.sin(angle);
			/**Map data after 10th cycle**/
			if(i>=10*365){
				/**Map the data**/
				data.push({name:mainName+" "+date[j],value:[x,y,8,mainNormal[j],-1,""]});
            	data2.push({name:integratedAttribute[j]+date[j],value:[x*1.1,y*1.1,8,integration[j],-1,""]});
            	/**Judge whether current data exist assoication rules**/
            	if(j==index[n]){
            		data[j].value[4] = parseFloat(association[n]).toFixed(2);
            		data2[j].value[4] = parseFloat(association[n]).toFixed(2);
            		data2[j].value[5] = relation[n];
            		/**Classify the Association based on the value of the association**/
            		if(association[n]<=0.6&&association[n]>0.5){
            			match2(ass1,x,y);
            			Map(n,j);
            		}
            		else if(association[n]<=0.7&&association[n]>0.6){
            			match2(ass2,x,y);
            			Map(n,j);
            		}
            		else if(association[n]<=0.8&&association[n]>0.7){
            			match2(ass3,x,y);
            			Map(n,j);
            		}
            		else if(association[n]<=0.9&&association[n]>0.8){
            			match2(ass4,x,y);
            			Map(n,j);
            		}
            		else if(association[n]<=1.0&&association[n]>0.9){
            			match2(ass5,x,y);
            			Map(n,j);
            		}
            			
            		n++
            	}
            	j++;
			}
		}
		
		/**Draw line, each line represent 1 month, namely from Jan to Dec**/
		for(i=0;i<data.length;i++){
			if(i==0||i==30 || i==58 || i==89 || i==119 || i==150 || i==180 || i==211||
	           i==242|| i==272 || i==303 || i==333){
				month.push([data[i].value[0],data[i].value[1]]);
				for(j=1;j<data.length/366;j++){
					if(i+j*366<data.length){
						month.push([data2[i+j*366].value[0],data2[i+j*366].value[1]]);
					}
				}
				month.push([]);
			}
		}
	}
	
	/**Fill the data with coordinate**/
	function match2(data,x,y){
		data.push([x,y]);
		data.push([x*1.1,y*1.1]);
		data.push([]);
	}
	
	/**Map the rules**/
	function Map(n,j){
		let obj = {value:1,name:relation[n]+"-> "+mainName+parseInt(Main[j])+" : "+parseFloat(association[n]).toFixed(2)}
		let flag = 0
		for(let i=0;i<data.length;i++){
			if(ruleName[i] == obj.name){
				flag = 1;
				rule[i]++;
				break;
			}
		}
		if(flag == 0){
			ruleName.push(obj.name);
			rule.push(obj.value);
		}
	}
	
	
	/**Receiving incoming data from the Server side**/
	var region = '<%=request.getAttribute("region")%>';
	document.getElementById("region").innerHTML = region;
	var mainName = '<%=request.getAttribute("mainName")%>';
	
	var date = '<%=request.getAttribute("date")%>';
	date = date.substring(1,date.length-1);
	date = date.split(",");
	
	/**Print the date on the corresponding part**/
	document.getElementById("date").innerHTML = date[0]+" - "+date[date.length-1];
	
	var Main = '<%=request.getAttribute("main")%>';
	Main = Main.substring(1,Main.length-1);
	Main = Main.split(",");
	var integration = '<%=request.getAttribute("integration")%>';
	integration = integration.substring(1,integration.length-1);
	integration = integration.split(",");
	var index = '<%=request.getAttribute("index")%>';
	index = index.substring(1,index.length-1);
	index = index.split(",");
	var association = '<%=request.getAttribute("association")%>';
	association = association.substring(1,association.length-1);
	association = association.split(",");
	var relation = '<%=request.getAttribute("relation")%>';
	relation = relation.substring(1,relation.length-1);
	relation = relation.split(",");
	relation[0]=" "+relation[0];
	
	var mainNormal = '<%=request.getAttribute("mainNormal")%>';
	mainNormal = mainNormal.substring(1,mainNormal.length-1);
	mainNormal = mainNormal.split(",");
	
	
	var integratedAttribute = '<%=request.getAttribute("intergratedAttribute")%>';
	integratedAttribute = integratedAttribute.substring(2,integratedAttribute.length-2);
	integratedAttribute = integratedAttribute.split("], [");
	console.log(integratedAttribute);
	
	var data = [];var data2 = [];
    var ass1 = [];var ass2 = [];var ass3 = [];var ass4 = [];var ass5 = [];
    var month = [];
    var max = Math.max.apply(null,integration);
    var min = Math.min.apply(null,integration); 
    var max2 = Math.max.apply(null,mainNormal);
    var min2 = Math.min.apply(null,mainNormal);
    var rule = [];
    var ruleName = [];
    
	matching();

	/**Initiate the Echarts container**/
	var myChart = echarts.init(document.getElementById('association'));
	var myChart2 = echarts.init(document.getElementById('pie1'));
	var myChart3 = echarts.init(document.getElementById('Line'));
	var myChart4 = echarts.init(document.getElementById('Parallel'));
	var myChart5 = echarts.init(document.getElementById('pie2'));
	var myChart6 = echarts.init(document.getElementById('pie3'));
	
	/**Predefine the emphasis style**/
	var emphasisStyle = {
		    itemStyle: {
		    	
		        shadowBlur: 10,
		        shadowOffsetX: 0,
		        shadowOffsetY: 0,
		        shadowColor: 'rgba(0,0,0,20)'
		    }
		};
	
	/**Container for ring association chart**/
    myChart.setOption({
    	title:{
    		text: 'Ring Association charts',
    		subtext: 'Dependence among Multidimensional time-series',
    		left: 'center',
    		align: 'right',
    		textStyle:{
    			fontSize: 25
    		},
    		subtextStyle:{
    			fontSize: 20
    		}
    		
    	},
		xAxis:{show:false, type: 'value', splitLine:{show: false}},
		yAxis:{show:false, type: 'value', splitLine:{show: false}},
		/**Label text**/
		tooltip:{
			formatter: function(params){
				if(params.value[4]==-1)
					return params.name+" : "+params.value[3];
				else
					return params.name+" : "+params.value[3]+"<br>association:"+params.value[4]+"<br>"+params.value[5];
			}
		},
		color:['#003377','#45b649','#DDDDDD','#AAAAAA','#888888','#666666','#444444'],
		legend:
		{
			data:[mainName,'Integration','ass level 1','ass level 2','ass level 3','ass level 4','ass level 5'],
			left:10, 
			top: 100
		},
		/**Enable the zooming interaction strategies**/
		dataZoom: [
        {
            type: 'slider',
            xAxisIndex: 0,
            filterMode: 'empty'
        },
        {
            type: 'slider',
            yAxisIndex: 0,
            filterMode: 'empty'
        },
        {
            type: 'inside',
            xAxisIndex: 0,
            filterMode: 'empty'
        },
        {
            type: 'inside',
            yAxisIndex: 0,
            filterMode: 'empty'
        }
    ],
    /**Map the data onto colors**/
    visualMap: [
    	{
            dimension: 3,
            seriesIndex:0,
            min:min2,
            max:max2,
            orient: 'vertical',
            right: 50,
            top: 330,
            text: ['HIGH', 'LOW'],
            calculable: true,
            hoverLink:true, 
            inRange: {
                color: ['#CCDDFF', '#003377']
            }
    	
        },
        {
        	dimension: 3,
            seriesIndex:1,
            min:min,
            max:max,
            orient: 'vertical',
            right: 50,
            top: 600,
            text: ['HIGH', 'LOW'],
            calculable: true,
            hoverLink:true, 
            inRange: {
                color: ['#dce35b', '#45b649']
            }
        }
    ],
    series: [
    	{
    		name: mainName,
        	type: 'scatter',
        	emphasis:emphasisStyle,
        	symbolSize: 6,
        	data: data
        	
     	},
     	{
     		name: "Integration",
    		type: 'scatter',
    		emphasis:emphasisStyle,
        	symbolSize: 6,
    		data: data2
     	},
     	{
     		name: "ass level 1",
     		type: 'line',
     		data:ass1,
     		symbolSize:0,
     		itemStyle :{
     			normal:{
     				lineStyle:{
                        width:5,
                        color:"#DDDDDD"
                    }
     			}
     		}
     	},
     	{
     		name: "ass level 2",
     		type: 'line',
     		data:ass2,
     		symbolSize:0,
     		itemStyle :{
     			normal:{
     				lineStyle:{
                        width:5,
                        color:"#AAAAAA"
                    }
     			}
     		}
     	},
     	{
     		name: "ass level 3",
     		type: 'line',
     		data:ass3,
     		symbolSize:0,
     		itemStyle :{
     			normal:{
     				lineStyle:{
                        width:5,
                        color:"#888888"
                    }
     			}
     		}
     	},
     	{
     		name: "ass level 4",
     		type: 'line',
     		data:ass4,
     		symbolSize:0,
     		itemStyle :{
     			normal:{
     				lineStyle:{
                        width:5,
                        color:"#666666"
                    }
     			}
     		}
     	},
     	{
     		name: "ass level 5",
     		type: 'line',
     		data:ass5,
     		symbolSize:0,
     		itemStyle :{
     			normal:{
     				lineStyle:{
                        width:5,
                        color:"#444444"
                    }
     			}
     		}
     	},
     	{
     		name: "Month",
     		type: 'line',
     		data: month,
     		symbolSize:0,
     		itemStyle :{
     			normal:{
     				lineStyle:{
                        width:1,
                        color: "#000000"
                    },
     			}
     		}
     		
     	}
     	]
    });
	
	/**Main Pie chart**/
    let level0=0;let level1=0;let level2=0;let level3=0;let level4=0;
    let min_max1 = [];let min_max2 = [];let min_max3 = [];let min_max4 = [];let min_max5 = [];
	/**Find the interval for each level**/
    for(let i=0;i<Main.length;i++){
		if(Main[i]==0){
			level0++;
			min_max1.push(mainNormal[i]);
		}
		else if(Main[i]==1){
			level1++;
			min_max2.push(mainNormal[i]);
		}	
		else if(Main[i]==2){
			level2++;
			min_max3.push(mainNormal[i]);
		}
		else if(Main[i]==3){
			level3++;
			min_max4.push(mainNormal[i]);
		}
			
		else if(Main[i]==4){
			level4++
			min_max5.push(mainNormal[i]);
		}	
	}
    myChart2.setOption({
    	title:{
    		text: mainName,
    		left: 'center',
            align: 'right'
    	},
    	/**Label**/
    	tooltip:{
    		trigger: 'item',
			formatter: function(params){
				return params.value[1]+" - "+params.value[2];
			}
		},
    	color:['#CCDDFF','#33CCFF','#00BBFF','#009FCC','#003377'],
		series:[
	        {
	            name: 'Pie chart',
	            type: 'pie',
	            radius: '55%',
	            data:[
	                {value:[level0,Math.min.apply(null,min_max1).toFixed(2),Math.max.apply(null,min_max1).toFixed(2)], name:'L 0: '+ level0},
	                {value:[level1,Math.min.apply(null,min_max2).toFixed(2),Math.max.apply(null,min_max2).toFixed(2)], name:'L 1: '+ level1},
	                {value:[level2,Math.min.apply(null,min_max3).toFixed(2),Math.max.apply(null,min_max3).toFixed(2)], name:'L 2: '+ level2},
	                {value:[level3,Math.min.apply(null,min_max4).toFixed(2),Math.max.apply(null,min_max4).toFixed(2)], name:'L 3: '+ level3},
	                {value:[level4,Math.min.apply(null,min_max5).toFixed(2),Math.max.apply(null,min_max5).toFixed(2)], name:'L 4: '+ level4}
	            ],
	            emphasis: {
	                itemStyle: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]}
	)
	
	/**Intergrated Pie chart**/
	level0=0;level1=0;level2=0;level3=0;level4=0;
	for(let i=0;i<integration.length;i++){
		if(integration[i]==0)
			level0++;
		else if(integration[i]==1)
			level1++;
		else if(integration[i]==2)
			level2++;
		else if(integration[i]==3)
			level3++;
		else if(integration[i]==4)
			level4++
		
	}
	
	myChart5.setOption({
		title:{
    		text: 'Integrated feature',
    		left: 'center',
            align: 'right'
    	},
    	color:['#dce35b','#99FF33','#77FF00','#66DD00','#45b649'],
		series:[
	        {
	            name: 'Pie chart',
	            type: 'pie',
	            radius: '55%',
	            data:[
	                {value:level0, name:'L 0: '+ level0},
	                {value:level1, name:'L 1: '+ level1},
	                {value:level2, name:'L 2: '+ level2},
	                {value:level3, name:'L 3: '+ level3},
	                {value:level4, name:'L 4: '+ level4}
	            ],
	            emphasis: {
	                itemStyle: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]}
	)
	
	/**Bar chart**/
	myChart6.setOption({
		title:{
    		text: 'Association Rule('+ruleName.length+', '+(ass1.length+ass2.length+ass3.length+ass4.length+ass5.length)/3+')',
    		left: 'center',
            align: 'right'
    	},
    	xAxis:
    	{
    		type:'category',
    		data:ruleName, 
    		show:false
        },
    	yAxis:{type:'value'},
    	dataZoom: [
            {
                type: 'inside'
            }
        ],
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'shadow'        
            }
        },
    	series:[
	        {
	            name: 'total',
	            type: 'bar',
	            itemStyle: {
	                color: new echarts.graphic.LinearGradient(
	                    0, 0, 0, 1,
	                    [
	                        {offset: 0, color: '#83bff6'},
	                        {offset: 0.5, color: '#188df0'},
	                        {offset: 1, color: '#188df0'}
	                    ]
	                )
	            },
	            emphasis: {
	                itemStyle: {
	                    color: new echarts.graphic.LinearGradient(
	                        0, 0, 0, 1,
	                        [
	                            {offset: 0, color: '#2378f7'},
	                            {offset: 0.7, color: '#2378f7'},
	                            {offset: 1, color: '#83bff6'}
	                        ]
	                    )
	                }
	            },
	            data:rule
	        },
	        {
				name:"Association Rule",
				type:'pie',
				radius: '15%',
				center:['60%','20%'],
				color:['#DDDDDD','#AAAAAA','#888888','#666666','#444444'],
				data: [
					{value:ass1.length/3, name:'0.5-0.6:'+ass1.length/3},
					{value:ass2.length/3, name:'0.6-0.7:'+ass2.length/3},
					{value:ass3.length/3, name:'0.7-0.8:'+ass3.length/3},
					{value:ass4.length/3, name:'0.8-0.9:'+ass4.length/3},
					{value:ass5.length/3, name:'0.9-1.0:'+ass5.length/3}
				],
				emphasis: {
		                itemStyle: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
			}
	    ]
	});
	
	
	/**Line chart**/
	Line = [];
	for(let i=0;i<date.length;i++){
			Line.push([date[i],parseFloat(mainNormal[i]).toFixed(2)])
	}
			
	
	myChart3.setOption({
		title:{
    		text: 'Line chart for '+mainName,
    		left: 'center',
            align: 'right'
    	},
			xAxis:{type: 'category',data:date},
			yAxis:{type: 'value'},
			dataZoom: [
				{
				  type: 'inside'
				}
			],
			tooltip:{
				trigger: 'axis'
		    },
			series : [
			    {
			       name: mainName,
			       type: 'line',
			            
			       itemStyle: {
			            color: new echarts.graphic.LinearGradient(
			             0, 0, 0, 1,
			             [ 
			                  {offset: 0, color: '#ffde33'},
			                  {offset: 0.5, color: '#ff9933'},
			                  {offset: 1, color: '#cc0033'}
			              ]
			             )

			            },
		            	symbolSize:2,
			            data:Line
			        }
			    ]
			});
	
	/**Parallel coordinate chart**/
	var norm = '<%=request.getAttribute("Norm")%>';
	norm = norm.substring(2,norm.length-2);
	norm = norm.split("], [");
	
	var column = '<%=request.getAttribute("Column")%>';
	column = column.split(",");
	
	
	for(let i=0;i<norm.length;i++){
		norm[i]=norm[i].split(",");
	}
	
	var parallel = [];
	for(let i=0;i<norm[0].length;i++){
		var col = []
		for(let j=0;j<norm.length;j++){
			col.push(norm[j][i]);
		}
		parallel.push(col);
	}
	
	var schema = [];
	for(let i=0;i<column.length;i++){
		let object = {name:column[i],index:i,text:column[i]};
		schema.push(object);
	}
	
	var parallelaxis = []
	for(let i=0;i<column.length;i++){
		let object = {dim:i,name:schema[i].text};
		parallelaxis.push(object);
	}
	
	var lineStyle = {
		    normal: {
		        width: 1,
		        opacity: 50
		    }
		};
	
	myChart4.setOption({
		parallelAxis: parallelaxis,
	    title:{
    		text: 'Parallel chart for All feature',
    		left: 'center',
            align: 'right'
    	},
	    visualMap: [
	    	{
	            dimension: 0,
	            top: 10,
	            right: 0,
	            text:["1.0","0.0"],
	            pieces: [
	            {
	            	gt:0,
	            	lte:0.2,
	            	color: '#CCEEFF'
	            },
	            {
	            	gt:0.2,
	            	lte:0.4,
	            	color: '#77FFEE'
	            },
	            {
	            	gt: 0.4,
	            	lte:0.6,
	            	color:'#FFFF33'
	            },
	            {
	            	gt: 0.6,
	            	lte:0.8,
	            	color: '#FFAA33'
	            },
	            {
	            	gt: 0.8,
	            	lte: 1.0,
	            	color: '#FF3333'
	            }
	            ]
	    	
	        }
	    	],
	        parallel: {                         
	            left: '5%',                     
	            right: '13%',
	            bottom: '10%',
	            top: '20%',
	            parallelAxisDefault: {          
	                type: 'value',
	                nameLocation: 'end',
	                nameGap: 20
	            }
	        },
	        series:[
	        	{
	        		name: "Parallel",
	        		type:"parallel",
	        		lineStyle: lineStyle,
	        		data: parallel
	        	}
	        ]
	});
</script>

</html>