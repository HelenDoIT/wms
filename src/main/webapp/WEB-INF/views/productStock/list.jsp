<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<title>PSS-即时库存报表</title>
<style>
	.alt td{ background:black !important;}
</style>
</head>
<body>
	<s:form id="searchForm" namespace="/" action="productStock">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_bottom">
							货品
							<s:textfield name="qo.keyword" placeholder="货品编码/货品名称" class="ui_input_txt02"/>
							仓库
							<s:select list="#depots" listKey="id" listValue="name" name="qo.depotId"
									  headerKey="-1" headerValue="所有仓库"
									  class="ui_select01"/>
							品牌
							<s:select list="#brands" listKey="id" listValue="name" name="qo.brandId"
									  headerKey="-1" headerValue="所有品牌"
									  class="ui_select01"/>
							阈值
							<s:textfield name="qo.limitNumber" placeholder="库存最高值" class="ui_input_txt02"/>
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
							<th>编码</th>
							<th>货品</th>
							<th>仓库</th>
							<th>品牌</th>
							<th>库存价格</th>
							<th>库存数量</th>
							<th>库存总金额</th>
						</tr>
						<tbody>
							<s:iterator value="#pageResult.data">
								<tr>
									<td><input type="checkbox" name="IDCheck" class="acb" /></td>
									<td><s:property value="product.sn"/> </td>
									<td><s:property value="product.name"/> </td>
									<td><s:property value="depot.name"/> </td>
									<td><s:property value="product.brand.name"/> </td>
									<td><s:property value="price"/> </td>
									<td><s:property value="storeNumber"/> </td>
									<td><s:property value="amount"/> </td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
					<%--导入通用的JSP片段--%>
				<jsp:include page="/WEB-INF/views/common/common_page.jsp"/>
			</div>
		</div>
	</s:form>
</body>
</html>
    
