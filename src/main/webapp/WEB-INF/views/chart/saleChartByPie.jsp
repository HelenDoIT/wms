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
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/fancyBox/jquery.fancybox.pack.js"></script>
<script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<title>PSS-销售报表饼状图</title>
<style>
	.alt td{ background:black !important;}
</style>

</head>
<body>
<%--页面查看取值--%>
<%--<s:property escape="false" value="#datas"/>--%>

<div id="main" style="height:480px; width: 580px"></div>
<script src="/js/plugins/echarts/echarts-all.js"></script>
<script type="text/javascript">
	// 基于准备好的dom，初始化echarts图表
	var myChart = echarts.init(document.getElementById('main'));
	var option ={
				title : {
					text: '销售报表',
					subtext: '<s:property value="groupTypes"/>',
					x:'center'
				},
				tooltip : {
					trigger: 'item',
					formatter: "{a} <br/>{b} : {c} ({d}%)",
					x:'center'
				},
				legend: {
					orient : 'vertical',
					x : 'left',
					data:<s:property escape="false" value="#groupList"/>
				},
				toolbox: {
					show : true,
					feature : {
						mark : {show: true},
						dataView : {show: true, readOnly: false},
						magicType : {
							show: true,
							type: ['pie', 'funnel'],
							option: {
								funnel: {
									x: '25%',
									width: '50%',
									funnelAlign: '50%',
									max: <s:property escape="false" value="maxAmount"/>
								}
							}
						},
						restore : {show: true},
						saveAsImage : {show: true}
					}
				},
				calculable : true,
				series : [
					{
						name:'访问来源',
						type:'pie',
						radius : '55%',
						center: ['50%', '60%'],
						data:<s:property escape="false" value="#datas"/>,
						x:'center'
					}
				]
			};

	// 为echarts对象加载数据
	myChart.setOption(option);
</script>
</body>
</html>
    
