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
<script type="text/javascript" src="/js/plugins/artDialog/iframeTools.js"></script>
<script type="text/javascript" src="/js/plugins/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/commonAll.js"></script>
	<script type="text/javascript">
		$(function(){
			$(".Wdate").click(function(){
				WdatePicker();
			});
			//点击放大镜 选择商品并回显数据
			$(".searchproduct").click(function () {
				var currentTr = $(this).closest("tr");
				var url="product_selectProductList.action";
				$.dialog.open(url,{
					width:960,
					height:660,
					close:function (){
						//获取 selectProductList.jsp 回传的数据
						var data = $.dialog.data("data");
						if(data){
						//根据当前行找到对应得元素并显示出来
						currentTr.find("[tag=name]").val(data["name"]);
						currentTr.find("[tag=pid]").val(data["id"]);
						currentTr.find("[tag=brand]").html(data["brandName"]);
						currentTr.find("[tag=costPrice]").val(data["costPrice"]);
						}
					}
				});
			});
			//金额小计
			$("[tag=number],[tag=costPrice]").change(function () {
				var currentTr = $(this).closest("tr");
				var costPrice = currentTr.find("[tag=costPrice]").val();
				var number = currentTr.find("[tag=number]").val();
				console.log(costPrice,number);
				//判断有值
				if(costPrice && number){
					// toFixed(2) 保留两位小数
					var amount = (costPrice * number).toFixed(2);
					console.log(amount);
					currentTr.find("[tag=amount]").text(amount);
				}
			});
			//添加明细
			$(".appendRow").click(function () {
				var newTr = $("#edit_table_body tr:first").clone(true);
				newTr.find("[tag=name]").val("");
				newTr.find("[tag=pid]").val("");
				newTr.find("[tag=brand]").html("");
				newTr.find("[tag=costPrice]").val("");
				newTr.find("[tag=number]").val("");
				newTr.find("[tag=amount]").html("");
				newTr.find("[tag=remark]").val("");
				newTr.appendTo($("#edit_table_body"));
			});
			//删除明细
			$(".removeItem").click(function(){
				var currentTr = $(this).closest("tr");
				if($("#edit_table_body tr").size() == 1){
					currentTr.find("[tag=name]").val("");
					currentTr.find("[tag=pid]").val("");
					currentTr.find("[tag=brand]").html("");
					currentTr.find("[tag=costPrice]").val("");
					currentTr.find("[tag=number]").val("");
					currentTr.find("[tag=amount]").html("");
					currentTr.find("[tag=remark]").val("");
				}else{
					currentTr.remove();
				}
			});
			//保存按钮: 修改明细参数 并提交表单
			$(".submit_btn").click(function () {
				//拿到tbody中所有的行, 进行遍历
				$("#edit_table_body tr").each(function(index,item){
					$(item).find("[tag=pid]").prop("name","stockIncomeBill.items["+index+"].product.id");
					$(item).find("[tag=costPrice]").prop("name","stockIncomeBill.items["+index+"].costPrice");
					$(item).find("[tag=number]").prop("name","stockIncomeBill.items["+index+"].number");
					$(item).find("[tag=remark]").prop("name","stockIncomeBill.items["+index+"].remark");
				});
			});
		});
	</script>
</head>
<body>
<s:debug/>
<s:form name="editForm" namespace="/" action="stockIncomeBill_saveOrUpdate" method="post" id="editForm">
	<s:hidden name="stockIncomeBill.id"/>
	<div id="container">
		<div id="nav_links">
			<span style="color: #1A5CC6;">采购入库单编辑</span>
			<div id="page_close">
				<a>
					<img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
				</a>
			</div>
		</div>
		<div class="ui_content">
			<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td class="ui_text_rt" width="140">入库单编号</td>
					<td class="ui_text_lt">
						<s:textfield name="stockIncomeBill.sn" cssClass="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">仓库</td>
					<td class="ui_text_lt">
						<s:select list="#depots" name="stockIncomeBill.depot.id" listKey="id" listValue="name"
								  cssClass="ui_select03"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">业务时间</td>
					<td class="ui_text_lt">
						<s:date name="stockIncomeBill.vdate" format="yyyy-MM-dd" var="vdate"/>
						<input name="stockIncomeBill.vdate" class="ui_input_btn02 Wdate" value="<s:property value="#vdate"/>">
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">入库单明细</td>
				</tr>
				<tr>
					<td></td>
					<td>
						<input type="button" value="添加明细" class="ui_input_btn01 appendRow"/>
						<table class="edit_table" cellspacing="0" cellpadding="0" border="0" style="width: auto">
							<thead>
							<tr>
								<th width="10"></th>
								<th width="200">货品</th>
								<th width="120">品牌</th>
								<th width="80">价格</th>
								<th width="80">数量</th>
								<th width="80">金额小计</th>
								<th width="150">备注</th>
								<th width="60"></th>
							</tr>
							</thead>
							<tbody id="edit_table_body">
							<s:if test="stockIncomeBill.id == null">
								<tr>
									<td></td>
									<td>
										<s:textfield disabled="true" readonly="true" cssClass="ui_input_txt04" tag="name"/>
										<img src="/images/common/search.png" class="searchproduct"/>
										<s:hidden name="stockIncomeBill.items[0].product.id" tag="pid"/>
									</td>
									<td><span tag="brand"></span></td>
									<td><s:textfield tag="costPrice" name="stockIncomeBill.items[0].costPrice"
													 cssClass="ui_input_txt04"/></td>
									<td><s:textfield tag="number" name="stockIncomeBill.items[0].number"
													 cssClass="ui_input_txt04"/></td>
									<td><span tag="amount"></span></td>
									<td><s:textfield tag="remark" name="stockIncomeBill.items[0].remark"
													 cssClass="ui_input_txt02"/></td>
									<td>
										<a href="javascript:;" class="removeItem">删除明细</a>
									</td>
								</tr>
							</s:if>
							<s:else>
								<s:iterator value="stockIncomeBill.items">
									<tr>
										<td></td>
										<td>
											<s:textfield disabled="true" readonly="true" name="product.name" cssClass="ui_input_txt04" tag="name"/>
											<img src="/images/common/search.png" class="searchproduct"/>
											<s:hidden name="product.id" tag="pid"/>
										</td>
										<td><span tag="brand">
											<s:property value="product.brand.name"/>
										</span></td>
										<td><s:textfield tag="costPrice" name="costPrice"
														 cssClass="ui_input_txt04"/></td>
										<td><s:textfield tag="number" name="number"
														 cssClass="ui_input_txt04"/></td>
										<td><span tag="amount"><s:property value="amount"/> </span></td>
										<td><s:textfield tag="remark" name="remark" cssClass="ui_input_txt02"/></td>
										<td>
											<a href="javascript:;" class="removeItem">删除明细</a>
										</td>
									</tr>
								</s:iterator>
							</s:else>
							</tbody>
						</table>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td class="ui_text_lt">
						&nbsp;<input type="submit" value="确定保存" class="ui_input_btn01 submit_btn"/>
						&nbsp;<input id="cancelbutton" type="button" value="重置" class="ui_input_btn01"/>
					</td>
				</tr>
			</table>
		</div>
	</div>
</s:form>
</body>
</html>