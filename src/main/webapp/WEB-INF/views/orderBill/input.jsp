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
	<script>
		$(function(){
			//on 函数 针对整个表格元素进行监听
			$("#edit_table_body").on("click",".searchproduct",function () {
				//找到当前 tr 一层一层往上找
				var currentTr = $(this).closest("tr");
				var url="product_selectProductList.action";
				$.dialog.open(url, {
					id: 'ajxxList',
					title: '选择货品',
					width: 980,
					height: 660,
					close: function () {
						console.log($.dialog.data("data"));
						var data = $.dialog.data("data");
						if(data){
						currentTr.find("[tag=name]").val(data["name"]);
						currentTr.find("[tag=pid]").val(data["id"]);
						currentTr.find("[tag=costPrice]").val(data["costPrice"]);
						currentTr.find("[tag=brand]").html(data["brandName"]);
						}
					}
				});
				 //金额小计的显示
			}).on("change","[tag=costPrice],[tag=number]", function () {
						var currentTr = $(this).closest("tr");
						var costPrice =currentTr.find("[tag=costPrice]").val();
						var number =currentTr.find("[tag=number]").val();
						if(costPrice && number){
							//如果两者都不为空, 小计为两者的积
							var amount = (costPrice*number).toFixed(2);
							currentTr.find("[tag=amount]").html(amount);//选择器别漏写[]
						}
					}).on("click",".removeItem",function(){
						var currentTr = $(this).closest("tr");
						if($("#edit_table_body tr").size()==1){
							currentTr.find("[tag=name]").val("");
							currentTr.find("[tag=pid]").val("");
							currentTr.find("[tag=costPrice]").val("");
							currentTr.find("[tag=number]").val("");
							currentTr.find("[tag=brand]").html("");
							currentTr.find("[tag=amount]").html("");
							currentTr.find("[tag=remark]").val("");
						}else {
							currentTr.remove();
						}
			});

			//添加明细按钮 (在表格之外)
			$(".appendRow").click(function(){
				//找到tbody的第一行并克隆
				var newTr = $("#edit_table_body tr:first").clone(true);
				console.log(newTr);
				newTr.find("[tag=name]").val("");
				newTr.find("[tag=pid]").val("");
				newTr.find("[tag=costPrice]").val("");
				newTr.find("[tag=number]").val("");
				newTr.find("[tag=brand]").html("");
				newTr.find("[tag=amount]").html("");
				newTr.find("[tag=remark]").val("");
				newTr.appendTo("#edit_table_body");
			});
			//给保存按钮绑定点击事件, 获取tBody中的tr元素
			$(".submit_btn").click(function () {
				$("#edit_table_body tr").each(function(index,item){
//					alert(index);
					//临时变量
					var _item = $(item);
					_item.find("[tag=pid]").prop("name","orderBill.items["+index+"].product.id");
					_item.find("[tag=costPrice]").prop("name","orderBill.items["+index+"].costPrice");
					_item.find("[tag=number]").prop("name","orderBill.items["+index+"].number");
					_item.find("[tag=remark]").prop("name","orderBill.items["+index+"].remark");
				});
				$("#editForm").submit();
			});
			$("[name='orderBill.vdate']").click(function () {
				WdatePicker();
			})
		});
	</script>
</head>
<body>
<s:debug/>
<s:form name="editForm" namespace="/" action="orderBill_saveOrUpdate" method="post" id="editForm">
	<s:hidden name="orderBill.id"/>
	<div id="container">
		<div id="nav_links">
			<span style="color: #1A5CC6;">采购订单编辑</span>
			<div id="page_close">
				<a>
					<img src="/images/common/page_close.png" width="20" height="20" style="vertical-align: text-top;"/>
				</a>
			</div>
		</div>
		<div class="ui_content">
			<table cellspacing="0" cellpadding="0" width="100%" align="left" border="0">
				<tr>
					<td class="ui_text_rt" width="140">订单编码</td>
					<td class="ui_text_lt">
						<s:textfield name="orderBill.sn" cssClass="ui_input_txt02"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">供应商</td>
					<td class="ui_text_lt">
						<s:select list="#supplier" name="orderBill.supplier.id" listKey="id" listValue="name"
								  cssClass="ui_select03"/>
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">业务时间</td>
					<td class="ui_text_lt">
						<s:date name="orderBill.vdate" format="yyyy-MM-dd" var="vdate"/>
						<input name="orderBill.vdate" class="ui_input_btn02 Wdate" value="<s:property value="#vdate"/>">
					</td>
				</tr>
				<tr>
					<td class="ui_text_rt" width="140">订单明细</td>
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
							<s:if test="orderBill.id == null">
								<tr>
									<td></td>
									<td>
										<s:textfield disabled="true" readonly="true" cssClass="ui_input_txt04" tag="name"/>
										<img src="/images/common/search.png" class="searchproduct"/>
										<s:hidden name="orderBill.items[0].product.id" tag="pid"/>
									</td>
									<td><span tag="brand"></span></td>
									<td><s:textfield tag="costPrice" name="orderBill.items[0].costPrice"
													 cssClass="ui_input_txt04"/></td>
									<td><s:textfield tag="number" name="orderBill.items[0].number"
													 cssClass="ui_input_txt04"/></td>
									<td><span tag="amount"></span></td>
									<td><s:textfield tag="remark" name="orderBill.items[0].remark"
													 cssClass="ui_input_txt02"/></td>
									<td>
										<a href="javascript:;" class="removeItem">删除明细</a>
									</td>
								</tr>
							</s:if>
							<s:else>
								<s:iterator value="orderBill.items">
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

												<td><s:textfield tag="remark" name="remark"
														 cssClass="ui_input_txt02"/></td>
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