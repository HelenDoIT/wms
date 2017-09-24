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
	<script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<title>PSS-订货报表</title>
<style>
	.alt td{ background:black !important;}
</style>
<script type="text/javascript">
	$(function () {
		//日期的大小关系
		$("[name='oqo.beginDate']").click(function () {
			WdatePicker({
				maxDate:$("[name='oqo.endDate']").val(),
			});
		});
		$("[name='oqo.endDate']").click(function () {
			WdatePicker({
				minDate:$("[name='oqo.beginDate']").val(),
			});
		})
	});
</script>
</head>
<body>
	<s:form id="searchForm" namespace="/" action="chart_orderChart">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
					</div>
					<div id="box_center">
						业务时间
						<s:date name="oqo.beginDate" format="yyyy-MM-dd" var="beginDate"/>
						<input name="oqo.beginDate" class="ui_input_btn02 Wdate" value="<s:property value="#beginDate"/>">~
						<s:date name="oqo.endDate" format="yyyy-MM-dd" var="endDate"/>
						<input name="oqo.endDate" class="ui_input_btn02 Wdate" value="<s:property value="#endDate"/>">
						货品
						<s:textfield name="oqo.keyword" cssClass="ui_select01"/>
						供应商
						<s:select list="#suppliers" listKey="id" listValue="name" name="oqo.supplierId"
								  headerKey="-1" headerValue="全部"
								  class="ui_select01"/>
						品牌
						<s:select list="#brands" listKey="id" listValue="name" name="oqo.brandId"
								  headerKey="-1" headerValue="全部"
								  class="ui_select01"/>
						分组
						<s:select list="#groupTypes" name="oqo.groupType"
								  class="ui_select01"/>
					</div>
					<div id="box_bottom">
							<input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>分组类型</th>
							<th>采购总数量</th>
							<th>采购总金额</th>
						</tr>
						<tbody>
							<s:iterator value="#pageResult">
								<tr>
									<td><input type="checkbox" name="IDCheck" class="acb" /></td>
									<td><s:property value="groupType"/> </td>
									<td><s:property value="number"/> </td>
									<td><s:property value="amount"/> </td>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</s:form>
</body>
</html>
    
