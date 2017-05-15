<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>仓库管理</title>
	<jsp:include page="/common.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			$('#data-table').datagrid( {
				url : '${basePath}/stock/query',
				rownumbers : true,
				autoRowHeight : true, 
				singleSelect : true,
				pagination : true,
				nowrap: false,
				toolbar: [{
					text:'添加',
					iconCls: 'icon-add',
					handler: function(){addStock();}
				},'-',{
					text:'修改',
					iconCls: 'icon-edit',
					handler: function(){updateStock();}
				},'-',{
					text:'删除',
					iconCls: 'icon-remove',
					handler: function(){deleteStock();}
				}],
				columns:[[
				    {field : 'id',align : 'center',halign:'center',checkbox : true}, 
				    {field : 'stockno',title : '仓库编号',halign:'center',width : 120},
				    {field : 'stockname',title : '仓库名称',halign:'center',width : 150},
				    {field : 'provincecode',title : '所在地区',halign:'center',width : 80},
				    {field : 'stockaddress',title : '仓库详细地址',halign:'center',width : 180}, 
				    {field : 'stocktel',title : '仓库联系方式',halign:'center',width : 80}, 
				    {field : 'userid',title : '仓管员',halign:'center',width : 80}, 
				    {field : 'remark',title : '备注',halign:'center',width : 200}
				]]
			});
			
			$('#dlg-frame').dialog( {
				title : '仓库管理',
				width :  700,
				height : 300,
				top:50,
				left:200,
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
			
		});
		
		function doSearch(){
			var stockNo = $("#stockno").val();
			var stockName = $("#stockname").val();
			var userId = $("#userid").val();
			$('#data-table').datagrid('reload',{
				stockno:stockNo,stockname:stockName,userid:userId
			} );
		}
		function doClear(){
			$("#stockno").val("");
			$("#stockname").val("");
			$("#userid").val("");
		}
		function addStock(){
			var path = "${basePath}/init/stock_add";
			document.getElementById('frameContent').src = path;
			$('#dlg-frame').dialog('open');
		}
		
		function updateStock(){
			var obj = $('#data-table').datagrid('getSelected');
			if (obj == null || obj.id == null) {
				$.messager.alert('提示', "请先选中一行(只允许单行操作)", 'error');
				return;
			}		
			var path = "${basePath}/stock/updateinit?id=" + obj.id;
			document.getElementById('frameContent').src = path;
			$('#dlg-frame').dialog('open');
		}
		
		function deleteStock(){
			var obj = $('#data-table').datagrid('getSelected');
			if (obj == null || obj.id == null) {
				$.messager.alert('提示', "请先选中一行(只允许单行操作)", 'error');
				return;
			}	
			var url = "${basePath}/stock/delete" ;
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
				<td><span>仓库编号:</span></td>
				<td><input id="stockno" name="stockno"/></td>
				<td><span>仓库名称:</span></td>
				<td><input id="stockname" name="stockname"/></td>
				<td><span>仓管员:</span></td>
				<td>
					<input id="userid" name="userid"/>
				</td>
				<td>
					<a href="####" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="doSearch()">查询</a>
					<a href="####" class="easyui-linkbutton" plain="true" iconCls="icon-clear" onclick="doClear()">清除</a>
				</td>
			</tr>
		</table>
	</div>
	<table id="data-table"  style="height:500px" title="数据列表" width="85%"></table>
	<div id="dlg-frame">
		<iframe width="99%" height="100%" name="frameContent" id="frameContent"
			frameborder="0"></iframe>
	</div>
	</body>
</html>