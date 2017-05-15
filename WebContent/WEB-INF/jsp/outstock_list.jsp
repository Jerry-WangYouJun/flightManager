<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出库查询</title>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/common.css">
	<script type="text/javascript" src="${basePath}/js/datagrid-detailview.js"></script>
	
	<script type="text/javascript">
		$(function(){
			$('#data-table').datagrid( {
				url : '${basePath}/outstock/query',
				rownumbers : true,
				autoRowHeight : true, 
				singleSelect : true,
				pagination : true,
				nowrap: false,
				toolbar: [{
					text:'添加',
					iconCls: 'icon-add',
					handler: function(){addoutstock();}
				},'-',{
					text:'修改',
					iconCls: 'icon-edit',
					handler: function(){updateoutstock();}
				},'-',{
					text:'删除',
					iconCls: 'icon-remove',
					handler: function(){deleteoutstock();}
				}],
				columns:[[
				    {field : 'id',align : 'center',halign:'center',checkbox : true}, 
				    {field : 'outstockNo',title : '出库单号',halign:'center',width : 150},
				    {field : 'productNo',title : '饲料编号',halign:'center',width : 120},
				    {field : 'productName',title : '饲料名称',halign:'center',width : 120},
				    {field : 'outstockNum',title : '数量',halign:'center',width : 120},
				    {field : 'stockName',title : '仓库名称',halign:'center',width : 100},
				    {field : 'supplierName',title : '供货供应商名称',halign:'center',width : 120},
				    {field : 'outstockState',title : '出库单状态',halign:'center',width : 80,formatter:function(value,rowData,rowIndex){
				    		if (value == "00") {
								return "创建";
							}else if (value == "02") {
								return "记账"
							} else {
								return value;
							}
				    }}, 
				    {field : 'outstockDate',title : '出库日期',halign:'center',width : 100},
				    {field : 'remark',title : '备注',halign:'center',width : 200}
				]],
			});
			
			$('#dlg-frame').dialog( {
				title : '出库管理',
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
				title : '出库管理',
				width :  900,
				height : 520,
				top:20,
				left:100,
				closed : true,
				cache : false,
				modal : true
			});
			
			//初始化下拉框
			
			//出库状态
			initDictionarySelect("stockState","#search-outstockState");
			//仓库
			initStockSelect("#search-stockId");
			//供应商
			initSupplierSelect("#search-supplierId");
			
		});
		
		function doSearch(){
			var outstockNo = $("#search-outstockNo").val();
			var stockId = $("#search-stockId").val();
			var supplierId = $("#search-supplierId").val();
			var outstockState = $("#search-outstockState").val();
			var outstockDateStart = $("#search-outstockDateStart").datebox("getValue");
			var outstockDateEnd = $("#search-outstockDateEnd").datebox("getValue");
			$('#data-table').datagrid('reload',{
				outstockNo:outstockNo,stockId:stockId,supplierId:supplierId,outstockState:outstockState,
				outstockDateStart:outstockDateStart,outstockDateEnd:outstockDateEnd
			} );
		}
		function doClear(){
			$("#search-outstockNo").val("");
			$("#search-stockId").val("");
			$("#search-supplierId").val("");
			$("#search-outstockState").val("");
			$("#search-outstockDateStart").combo("setText","");
			$("#search-outstockDateStart").combo("setValue","");
			$("#search-outstockDateEnd").combo("setText","");
			$("#search-outstockDateEnd").combo("setValue","");
		}
		function addoutstock(){
			var path = "${basePath}/outstock/save_input";
			document.getElementById('frameContent').src = path;
			$('#dlg-frame').dialog('open');
		}
		
		function updateoutstock(){
			var obj = $('#data-table').datagrid('getSelected');
			if (obj == null || obj.id == null) {
				$.messager.alert('提示', "请先选中一行(只允许单行操作)", 'error');
				return;
			}		
			var path = "${basePath}/outstock/save_input/" + obj.id;
			document.getElementById('frameContent').src = path;
			$('#dlg-frame').dialog('open');
		}
		
		function deleteoutstock(){
			var obj = $('#data-table').datagrid('getSelected');
			if (obj == null || obj.id == null) {
				$.messager.alert('提示', "请先选中一行(只允许单行操作)", 'error');
				return;
			}	
			$.messager.confirm("操作提示", "确定要进行删除操作么?", function(data){
				if(data){
					var url = "${basePath}/outstock/delete/"+obj.id;
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
		<span>出库单号:</span>
		<input id="search-outstockNo" name="outstockNo"/>
		<span>所在仓库:</span>
		<select id="search-stockId" name="stockId">
			<option value="">---请选择---</option>
		</select> 
		<span>供货供应商:</span>
		<select id="search-supplierId" name=supplierId>
			<option value="">---请选择---</option>
		</select> <br/>
		<span>出库单状态:</span>
		<select id="search-outstockState" name="outstockState">
			<option value="">---请选择---</option>
		</select>
		<span>出库日期:</span>
		<input id="search-outstockDateStart" class="easyui-datebox"  name="outstockDateStart" data-options="editable:false"/>-
		<input id="search-outstockDateEnd" class="easyui-datebox"  name="outstockDateEnd" data-options="editable:false"/>
		<a href="####" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="doSearch()">查询</a>
		<a href="####" class="easyui-linkbutton" plain="true" iconCls="icon-clear" onclick="doClear()">清除</a>
	</div>
	<form:form id="dataForm" action="${basePath}/outstock/delete" method="post">
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