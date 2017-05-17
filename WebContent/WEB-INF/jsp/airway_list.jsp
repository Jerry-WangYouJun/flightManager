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
				url : '${basePath}/airway/query',
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
				    {field : 'flight',title : '航班编号',halign:'center',width : 120},
				    {field : 'startdate',title : '起飞时间',halign:'center',width : 150},
				    {field : 'flighttype',title : '飞行类型',halign:'center',width : 80},
				    {field : 'company',title : '航空公司',halign:'center',width : 80}, 
				    {field : 'fromairport',title : '起飞机场',halign:'center',width : 80},
				    {field : 'toairport',title : '降落机场',halign:'center',width : 80}, 
				    {field : 'ontime',title : '准点率',halign:'center',width : 80}, 
				]]
			});
			
			$('#dlg-frame').dialog( {
				title : '航线管理',
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
			
			initDictionarySelect("productType", "#fromairport");
			initSupplierSelect("#supplierNo");
		});
		
		function doSearch(){
			var productNo = $("#flight").val();
			var productName = $("#startdate").val();
			var fromairport = $("#flighttypecode").val();
			var productStandard = $("#company").val();
			var supplierNo = $("#supplierno").val();
			var productDateStart = $("#fromairport").val();
			var productDateEnd = $("#fromairportend").val();
			$('#data-table').datagrid('reload',{
				flight:productNo,startdate:productName,flighttypecode:fromairport,
				company:productStandard,supplierno:supplierNo,fromairport:productDateStart,
				fromairportend:productDateEnd
			} );
		}
		function doClear(){
			$("#flight").val("");
			$("#startdate").val("");
			$("#flighttypecode").val("");
			$("#company").val("");
			$("#supplierno").val("");
			$("#fromairport").combo("setText","");
			$("#fromairport").combo("setValue","");
			$("#fromairportend").combo("setText","");
			$("#fromairportend").combo("setValue","");
		}
		function addGoods(){
			var path = "${basePath}/init/airway_add";
			document.getElementById('frameContent').src = path;
			$('#dlg-frame').dialog('open');
		}
		
		function updateGoods(){
			var obj = $('#data-table').datagrid('getSelected');
			if (obj == null || obj.id == null) {
				$.messager.alert('提示', "请先选中一行(只允许单行操作)", 'error');
				return;
			}		
			var path = "${basePath}/airway/updateinit?id=" + obj.id;
			document.getElementById('frameContent').src = path;
			$('#dlg-frame').dialog('open');
		}
		
		function deleteGoods(){
			var obj = $('#data-table').datagrid('getSelected');
			if (obj == null || obj.id == null) {
				$.messager.alert('提示', "请先选中一行(只允许单行操作)", 'error');
				return;
			}	
			var url = "${basePath}/airway/delete";
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
				<td><span>航班编码:</span></td>
				<td><input id="flight" name="flight"/></td>
				<td><span>起飞时间:</span></td>
				<td><input id="startdate" name="startdate"/></td>
				<td><span>飞行类型:</span></td>
				<td>
					<select id="flighttype" name="flighttype">
						<option value="">---请选择---</option>
						<option value="直飞">直飞</option>
						<option value="转机">转机</option>
					</select> 
				</td>
				<td>
					<a href="####" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="doSearch()">查询</a>
				</td>
			</tr>
			<tr>
				<td><span>航空公司:</span></td>
				<td><input id="company" name="company"/></td>
				<td><span>起飞机场:</span></td>
				<td>
					<input id="fromairport"   name="fromairport" size="14"/>
				</td>
				<td><span>降落机场:</span></td>
				<td>
					<input id="toairport"   name="toairport" size="14"/>
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