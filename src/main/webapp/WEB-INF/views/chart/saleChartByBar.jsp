<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<link href="/js/fancyBox/jquery.fancybox.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/fancyBox/jquery.fancybox.pack.js"></script>
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<title>PSS-销售报表柱形图</title>
<style>
	.alt td{ background:black !important;}
</style>

</head>
<body>
<%--页面查看取值--%>

<div id="main" style="height:450px; width:750px"></div>
<script src="/js/plugins/echarts/echarts-all.js"></script>
<script type="text/javascript">
	// 基于准备好的dom，初始化echarts图表
	var myChart = echarts.init(document.getElementById('main'));
	var option = {
		title : {
			text: '销售报表柱形图',
			subtext: '<s:property value="groupTypes"/>',
			x:'center'
		},
		tooltip : {
			trigger: 'axis'
		},
		legend: {
			data:['销售金额'],
			x:'left'
		},
		toolbox: {
			show : true,
			feature : {
				mark : {show: true},
				dataView : {show: true, readOnly: false},
				magicType : {show: true, type: ['line', 'bar']},
				restore : {show: true},
				saveAsImage : {show: true}
			}
		},
		calculable : true,
		xAxis : [
			{
				type : 'category',
				data : <s:property escape="false" value="#groupList"/>
			}
		],
		yAxis : [
			{
				type : 'value'
			}
		],
		series : [
			{
				name:'销售金额',
				type:'bar',
				data: <s:property escape="false" value="#totalAmounts"/>,
				markPoint : {
					data : [
						{type : 'max', name: '最大值'},
						{type : 'min', name: '最小值'}
					]
				},
				markLine : {
					data : [
						{type : 'average', name: '平均值'}
					]
				}
			}
		]
	};

	// 为echarts对象加载数据
	myChart.setOption(option);
</script>
</body>
</html>
    
