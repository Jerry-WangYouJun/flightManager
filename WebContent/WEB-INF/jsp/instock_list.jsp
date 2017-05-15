<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>入库查询</title>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/common.css">
	<script type="text/javascript" src="${basePath}/js/datagrid-detailview.js"></script>
	
	<script type="text/javascript">
		$(function(){
			$('#data-table').datagrid( {
				url : '${basePath}/instock/query',
				rownumbers : true,
				autoRowHeight : true, 
				singleSelect : true,
				pagination : true,
				nowrap: false,
				toolbar: [{
					text:'添加',
					iconCls: 'icon-add',
					handler: function(){addInstock();}
				},'-',{
					text:'修改',
					iconCls: 'icon-edit',
					handler: function(){updateInstock();}
				},'-',{
					text:'删除',
					iconCls: 'icon-remove',
					handler: function(){deleteInstock();}
				}],
				columns:[[
				    {field : 'id',align : 'center',halign:'center',checkbox : true}, 
				    {field : 'instockNo',title : '入库单号',halign:'center',width : 150},
				    {field : 'productNo',title : '饲料编号',halign:'center',width : 120},
				    {field : 'productname',title : '饲料名称',halign:'center',width : 120},
				    {field : 'numbers',title : '数量',halign:'center',width : 120},
				    {field : 'stockName',title : '仓库名称',halign:'center',width : 100},
				    {field : 'supplierName',title : '供货供应商名称',halign:'center',width : 120},
				    {field : 'instockState',title : '入库单状态',halign:'center',width : 80,formatter:function(value,rowData,rowIndex){
				    		if (value == "00") {
								return "创建";
							}else if (value == "02") {
								return "记账"
							} else {
								return value;
							}
				    }}, 
				    {field : 'instockDate',title : '入库日期',halign:'center',width : 100},
				    {field : 'remark',title : '备注',halign:'center',width : 200}
				]],
			});
			
			$('#dlg-frame').dialog( {
				title : '入库管理',
				width :  800,
				height : 300,
				top:20,
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
				},{
					text : '关闭',
					iconCls : 'icon-cancel',
					handler : function() {
						$('#dlg-frame').dialog("close");
					}
				} ]
			});
			
			$('#dlg-detailFrame').dialog( {
				title : '入库管理',
				width :  900,
				height : 520,
				top:20,
				left:100,
				closed : true,
				cache : false,
				modal : true
			});
			
			//初始化下拉框
			
			//入库状态
			initDictionarySelect("stockState","#search-instockState");
			//仓库
			initStockSelect("#search-stockId");
			//供应商
			initSupplierSelect("#search-supplierId");
			
		});
		
		function doSearch(){
			var instockNo = $("#search-instockNo").val();
			var stockId = $("#search-stockId").val();
			var supplierId = $("#search-supplierId").val();
			var instockState = $("#search-instockState").val();
			var instockDateStart = $("#search-instockDateStart").datebox("getValue");
			var instockDateEnd = $("#search-instockDateEnd").datebox("getValue");
			$('#data-table').datagrid('reload',{
				instockNo:instockNo,stockId:stockId,supplierId:supplierId,instockState:instockState,
				instockDateStart:instockDateStart,instockDateEnd:instockDateEnd
			} );
		}
		function doClear(){
			$("#search-instockNo").val("");
			$("#search-stockId").val("");
			$("#search-supplierId").val("");
			$("#search-instockState").val("");
			$("#search-instockDateStart").combo("setText","");
			$("#search-instockDateStart").combo("setValue","");
			$("#search-instockDateEnd").combo("setText","");
			$("#search-instockDateEnd").combo("setValue","");
		}
		function addInstock(){
			var path = "${basePath}/instock/save_input";
			document.getElementById('frameContent').src = path;
			$('#dlg-frame').dialog('open');
		}
		
		function updateInstock(){
			var obj = $('#data-table').datagrid('getSelected');
			if (obj == null || obj.id == null) {
				$.messager.alert('提示', "请先选中一行(只允许单行操作)", 'error');
				return;
			}		
			var path = "${basePath}/instock/save_input/" + obj.id;
			document.getElementById('frameContent').src = path;
			$('#dlg-frame').dialog('open');
		}
		
		function deleteInstock(){
			var obj = $('#data-table').datagrid('getSelected');
			if (obj == null || obj.id == null) {
				$.messager.alert('提示', "请先选中一行(只允许单行操作)", 'error');
				return;
			}	
			$.messager.confirm("操作提示", "确定要进行删除操作么?", function(data){
				if(data){
					var url = "${basePath}/instock/delete/"+obj.id;
					$.ajax( {
						url : url,
						type : 'post',
						data : $("#dataForm").serialize(),
						dataType : 'json',
						success : function(data) {
							$.messager.alert('提示', data.msg);
							doSearch();
						},
						error : function(transport) {
							$.messager.alert('提示', "系统产生错误,请联系管理员!", "error");
						}
					});
				}
			});
		}
	</script>

</head>
<body>
	<div id="tb" title="查询条件区" class="easyui-panel"  style="padding:3px;width:85%" iconCls="icon-search">
		<span>入库单号:</span>
		<input id="search-instockNo" name="instockNo"/>
		<span>所在仓库:</span>
		<select id="search-stockId" name="stockId">
			<option value="">---请选择---</option>
		</select> 
		<span>供货供应商:</span>
		<select id="search-supplierId" name=supplierId>
			<option value="">---请选择---</option>
		</select> <br/>
		<span>入库单状态:</span>
		<select id="search-instockState" name="instockState">
			<option value="">---请选择---</option>
		</select>
		<span>入库日期:</span>
		<input id="search-instockDateStart" class="easyui-datebox"  name="instockDateStart" data-options="editable:false"/>-
		<input id="search-instockDateEnd" class="easyui-datebox"  name="instockDateEnd" data-options="editable:false"/>
		<a href="####" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="doSearch()">查询</a>
		<a href="####" class="easyui-linkbutton" plain="true" iconCls="icon-clear" onclick="doClear()">清除</a>
	</div>
	<form:form id="dataForm" action="${basePath}/instock/delete" method="post">
		<input type="hidden" name="_method" value="DELETE"/>
		<table id="data-table" style="height:510px" title="数据列表" width="85%"></table>
	</form:form>
	<div id="dlg-frame">
		<iframe width="99%" height="98%" name="frameContent" id="frameContent"
			frameborder="0"></iframe>
	</div>
	<div id="dlg-detailFrame">
		<iframe width="99%" height="98%" name="frameDetailContent" id="frameDetailContent"
			frameborder="0"></iframe>
	</div>
	
</body>
</html>