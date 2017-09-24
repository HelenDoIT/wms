<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<title>信息管理系统</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="/style/basic_layout.css" rel="stylesheet" type="text/css">
<link href="/style/common_style.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="/js/jquery/jquery.js"></script>
<script type="text/javascript" src="/js/plugins/artDialog/jquery.artDialog.js?skin=blue"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
</head>
<body>
<s:form name="editForm" namespace="/" action="product_saveOrUpdate" method="post" id="editForm" enctype="multipart/form-data">
	<s:hidden name="product.id"/>
	<s:hidden name="product.imagePath"/>
	<div id="container">
		<div id="nav_links">
			<span style="color: #1A5CC6;">商品编辑</span>
			<div id="page_close">
				<a>
					<img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
				</a>
			</div>
		</div>
		<div class="ui_content">
			<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td class="ui_text_rt" width="140">商品编码</td>
					<td class="ui_text_lt">
						<s:textfield name="product.sn" cssClass="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">商品名称</td>
					<td class="ui_text_lt">
						<s:textfield name="product.name" cssClass="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">商品品牌</td>
					<td class="ui_text_lt">
						<s:select list="#brands" name="product.brand.id" listKey="id" listValue="name"
								  cssClass="ui_select03"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">成本价格</td>
					<td class="ui_text_lt">
						<s:textfield name="product.costPrice" cssClass="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">销售价格</td>
					<td class="ui_text_lt">
						<s:textfield name="product.salePrice" cssClass="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">商品图片</td>
					<td class="ui_text_lt">
						<s:file name="pic" cssClass="ui_file"/>
						<img src="<s:property value="product.smallImagePath"/>" width="50"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">备注</td>
					<td class="ui_text_lt">
						<s:textarea name="product.intro" cssClass="ui_area01"/>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
						&nbsp;<input type="submit" value="确定保存" class="ui_input_btn01"/>
						&nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</s:form>
</body>
</html>