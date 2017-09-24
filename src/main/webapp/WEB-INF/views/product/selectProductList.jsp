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
<script type="text/javascript" src="/js/plugins/artDialog/iframeTools.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
<title>PSS-商品管理</title>
<style>
	.alt td{ background:black !important;}
</style>
	<script type="text/javascript">
		$(function(){
			$(".fancybox").fancybox();
			
			$(".select").click(function () {
				var json = $(this).data("json");
				//把 json存到data中 传给采购订单的input页面
				$.dialog.data("data",json);
				$.dialog.close();
			});
		});
	</script>
</head>
<body>
	<s:form id="searchForm" namespace="/" action="product_selectProductList">
		<div id="container">
			<div class="ui_content">
				<div class="ui_text_indent">
					<div id="box_border">
						<div id="box_top">搜索</div>
						<td>
							<input type="button" value="查询" class="ui_input_btn01 btn_input"/>
						</td>
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
										<input type="button" value="选择此商品" class="ui_input_btn01 select" data-json='<s:property value="jsonString" escape="false"/>'/>
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
    
