<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>库存查询</title>
	<script type="text/javascript" src="${basePath}/js/common.js"></script>
	<script type="text/javascript">
	$(function(){
		$('#data-table').datagrid( {
			url : '${basePath}/inventory/query',
			rownumbers : true,
			autoRowHeight : true, 
			pagination : true,
			nowrap: false,
			singleSelect : true,
			columns:[[
			    {field : 'stockName',title : '库房',halign:'center',width : 80},
			    {field : 'productNo',title : '饲料编号',halign:'center',width : 120},
			    {field : 'productName',title : '饲料名称',halign:'center',width : 160},
			    {field : 'productStandard',title : '饲料规格',halign:'center',width : 120}, 
			    {field : 'unit',title : '计量单位',halign:'center',width : 80},
			    {field : 'numbers',title : '数量',halign:'center',width : 80},
			    {field : 'price',title : '金额',halign:'center',width : 80}
			]]
		});	
		
		//仓库
		initStockSelect("#search-stockId");
	});
	
	function doSearch(){
		var itemNo = $("#search-itemNo").val();
		var itemNanme = $("#search-itemName").val();
		var stockId = $("#search-stockId").val();
		$('#data-table').datagrid('reload',{
			itemNo:itemNo,itemName:itemNanme,stockId:stockId
		} );
	}
	
	function doClear(){
		$("#search-itemNo").val("");
		$("#search-itemName").val("");
		$("#search-stockId").val("");
	}
	</script>
</head>
<body>
	<div id="tb" title="查询条件区" class="easyui-panel"  style="padding:3px;width:85%" iconCls="icon-search">
		<span>饲料编号:</span>
		<input id="search-itemNo" name="ItemNo"/>
		<span>饲料名称:</span>
		<input id="search-itemName" name="ItemName"/>
		<span>库房:</span>
		<select id="search-stockId" name="stockId">
			<option value="">---请选择---</option>
		</select> 
		<a href="####" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="doSearch()">查询</a>
		<a href="####" class="easyui-linkbutton" plain="true" iconCls="icon-clear" onclick="doClear()">清除</a>
	</div>
	<table id="data-table"  style="height:510px" title="数据列表" width="85%"></table>
	<div id="dlg-frame">
		<iframe width="99%" height="98%" name="frameContent" id="frameContent"
			frameborder="0"></iframe>
	</div>
	
</body>
</html>