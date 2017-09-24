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
<script type="text/javascript" src="/js/commonAll.js"></script>
<title>PSS-商品管理</title>
<style>
	.alt td{ background:black !important;}
</style>
	<script type="text/javascript">
		$(function(){
			$(".fancybox").fancybox();
		})
	</script>
</head>
<body>
	<s:form id="searchForm" namespace="/" action="product">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<div id="box_bottom">
							编码/名称
							<s:textfield name="qo.keyword" class="ui_input_txt02"/>
							品牌
							<s:select list="#brands" listKey="id" listValue="name" name="qo.brandId"
									  headerKey="-1" headerValue="所有品牌"
									  class="ui_select01"/>
							<input type="button" value="查询" class="ui_input_btn01 btn_page" data-page="1"/>
							<input type="button" value="新增" class="ui_input_btn01 btn_input"
								   data-inputurl="<s:url namespace="/" action="product_input"/>"/>
						</div>
					</div>
				</div>
			</div>
			<div class="ui_content">
				<div class="ui_tb">
					<table class="table" cellspacing="0" cellpadding="0" width="100%" align="center" border="0">
						<tr>
							<th width="30"><input type="checkbox" id="all" /></th>
							<th>货品图片</th>
							<th>货品名称</th>
							<th>货品编码</th>
							<th>货品品牌</th>
							<th>成本价格</th>
							<th>销售价格</th>
							<th>操作</th>
						</tr>
						<tbody>
							<s:iterator value="#pageResult.data">
								<tr>
									<td><input type="checkbox" name="IDCheck" class="acb" /></td>
									<td>
										<a class="fancybox" href="<s:property value="imagePath"/>"
										   title="<s:property value="name"/>">
											<img src="<s:property value="smallImagePath"/>" width="80">
										</a>
									</td>
									<td><s:property value="name"/> </td>
									<td><s:property value="sn"/> </td>
									<td><s:property value="brand.name"/> </td>
									<td><s:property value="costPrice"/> </td>
									<td><s:property value="salePrice"/> </td>
									<td>
										<s:a namespace="/" action="product_input">
											<s:param name="product.id" value="id"/>编辑</s:a>

										<s:url action="product_delete" var="deleteUrl" escapeAmp="false">
											<s:param name="product.id" value="id"/>
											<s:param name="product.imagePath" value="imagePath"/>
										</s:url>
										<a href="javascript:;" class="btn_delete" data-url="<s:property value="#deleteUrl"/>">删除</a>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<!-- 分页操作 -->
				<%@ include file="/WEB-INF/views/common/common_page.jsp" %>
			</div>
		</div>
	</s:form>
</body>
</html>
    
