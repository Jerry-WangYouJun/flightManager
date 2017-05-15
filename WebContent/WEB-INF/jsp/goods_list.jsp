<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>饲料管理</title>
	<jsp:include page="/common.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			$('#data-table').datagrid( {
				url : '${basePath}/goods/query',
				rownumbers : true,
				autoRowHeight : true, 
				singleSelect : true,
				pagination : true,
				nowrap: false,
				toolbar: [{
					text:'添加',
					iconCls: 'icon-add',
					handler: function(){addGoods();}
				},'-',{
					text:'修改',
					iconCls: 'icon-edit',
					handler: function(){updateGoods();}
				},'-',{
					text:'删除',
					iconCls: 'icon-remove',
					handler: function(){deleteGoods();}
				}],
				columns:[[
				    {field : 'id',align : 'center',halign:'center',checkbox : true}, 
				    {field : 'productno',title : '饲料编码',halign:'center',width : 120},
				    {field : 'productname',title : '饲料名称',halign:'center',width : 150},
				    {field : 'producttype',title : '饲料分类',halign:'center',width : 80},
				    {field : 'productstandard',title : '饲料规格',halign:'center',width : 80}, 
				    {field : 'supplierid',title : '供应商',halign:'center',width : 150,formatter:function(value,rowData,rowIndex){
				    	if(value){
				    		return "未知"
				    	}
				    }},
				    {field : 'productdate',title : '生产日期',halign:'center',width : 80},
				    {field : 'unit',title : '计量单位',halign:'center',width : 80}, 
				    {field : 'price',title : '饲料单价',halign:'center',width : 80}, 
				    {field : 'remark',title : '备注',halign:'center',width : 200}
				]]
			});
			
			$('#dlg-frame').dialog( {
				title : '饲料管理',
				width :  900,
				height : 400,
				top:50,
				left:100,
				closed : true,
				cache : false,
				modal : true,
				buttons : [ {
					text : '确定',
					iconCls : 'icon-ok',
					handler : function() {
						if (confirm("确定执行下一步操作？")) {
							frameContent.window.doServlet();
						}
					}
				}, {
					text : '关闭',
					iconCls : 'icon-cancel',
					handler : function() {
						$('#dlg-frame').dialog("close");
					}
				} ]
			});
			
			initDictionarySelect("productType", "#productTypeCode");
			initSupplierSelect("#supplierNo");
		});
		
		function doSearch(){
			var productNo = $("#productno").val();
			var productName = $("#productname").val();
			var productTypeCode = $("#producttypecode").val();
			var productStandard = $("#productstandard").val();
			var supplierNo = $("#supplierno").val();
			var productDateStart = $("#productdatestart").datebox("getValue");
			var productDateEnd = $("#productdateend").datebox("getValue");
			$('#data-table').datagrid('reload',{
				productno:productNo,productname:productName,producttypecode:productTypeCode,
				productstandard:productStandard,supplierno:supplierNo,productdatestart:productDateStart,
				productdateend:productDateEnd
			} );
		}
		function doClear(){
			$("#productno").val("");
			$("#productname").val("");
			$("#producttypecode").val("");
			$("#productstandard").val("");
			$("#supplierno").val("");
			$("#productdatestart").combo("setText","");
			$("#productdatestart").combo("setValue","");
			$("#productdateend").combo("setText","");
			$("#productdateend").combo("setValue","");
		}
		function addGoods(){
			var path = "${basePath}/init/goods_add";
			document.getElementById('frameContent').src = path;
			$('#dlg-frame').dialog('open');
		}
		
		function updateGoods(){
			var obj = $('#data-table').datagrid('getSelected');
			if (obj == null || obj.id == null) {
				$.messager.alert('提示', "请先选中一行(只允许单行操作)", 'error');
				return;
			}		
			var path = "${basePath}/goods/updateinit?id=" + obj.id;
			document.getElementById('frameContent').src = path;
			$('#dlg-frame').dialog('open');
		}
		
		function deleteGoods(){
			var obj = $('#data-table').datagrid('getSelected');
			if (obj == null || obj.id == null) {
				$.messager.alert('提示', "请先选中一行(只允许单行操作)", 'error');
				return;
			}	
			var url = "${basePath}/goods/delete";
			$.ajax( {
				url : url,
				type : 'post',
				data : {
					id : obj.id
				},
				dataType : 'text',
				success : function(data) {
					if(data>0)
					doSearch();
				},
				error : function(transport) {
					$.messager.alert('提示', "系统产生错误,请联系管理员!", "error");
				}
			});
		}
	</script>
</head>
<body>
	<div id="tb" title="查询条件区" class="easyui-panel"  style="padding:3px;width:85%" iconCls="icon-search">
		<table align="center">
			<tr>
				<td><span>饲料编码:</span></td>
				<td><input id="productno" name="productno"/></td>
				<td><span>饲料名称:</span></td>
				<td><input id="productname" name="productname"/></td>
				<td><span>供应商:</span></td>
				<td>
					<select id="supplierNo" name="supplierno">
						<option value="">---请选择---</option>
					</select> 
				</td>
				<td>
					<a href="####" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="doSearch()">查询</a>
				</td>
			</tr>
			<tr>
				<td><span>饲料规格:</span></td>
				<td><input id="productstandard" name="productstandard"/></td>
				<td><span>饲料分类:</span></td>
				<td>
					<select id="productTypeCode" name="producttypecode">
		  				<option value="">---请选择---</option>
		  			</select>
				</td>
				<td><span>生产日期:</span></td>
				<td>
					<input id="productdatestart" class="easyui-datebox"  name="productdatestart" size="14" data-options="editable:false"/>-
					<input id="productdateend" class="easyui-datebox"  name="productdateend" size="14" data-options="editable:false"/>
				</td>
				<td>
					<a href="####" class="easyui-linkbutton" plain="true" iconCls="icon-clear" onclick="doClear()">清除</a>
				</td>
			</tr>
		</table>
	</div>
	<table id="data-table"  style="height:490px" title="数据列表" width="85%"></table>
	<div id="dlg-frame">
		<iframe width="99%" height="90%" name="frameContent" id="frameContent"
			frameborder="0"></iframe>
	</div>
	</body>
</html>