<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门管理</title>
	<jsp:include page="/common.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			$('#data-table').datagrid( {
				url : '${basePath}/dept/query',
				rownumbers : true,
				autoRowHeight : true, 
				singleSelect : true,
				pagination : true,
				nowrap: false,
				toolbar: [{
					text:'添加',
					iconCls: 'icon-add',
					handler: function(){addDept();}
				},'-',{
					text:'修改',
					iconCls: 'icon-edit',
					handler: function(){updateDept();}
				},'-',{
					text:'删除',
					iconCls: 'icon-remove',
					handler: function(){deleteDept();}
				}],
				columns:[[
				    {field : 'id',align : 'center',halign:'center',checkbox : true}, 
				    {field : 'deptno',title : '部门编号',halign:'center',width : 120},
				    {field : 'deptname',title : '部门名称',halign:'center',width : 150},
				    {field : 'deptleader',title : '部门领导',halign:'center',width : 80},
				    {field : 'depttel',title : '部门联系方式',halign:'center',width : 80}, 
				    {field : 'parentno',title : '上级部门',halign:'center',width : 150},
				    {field : 'deptdesc',title : '部门描述',halign:'center',width : 200}
				]]
			});
			
			$('#dlg-frame').dialog( {
				title : '部门管理',
				width :  700,
				height : 400,
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
			var deptNo = $("#deptno").val();
			var deptName = $("#deptname").val();
			var parentDeptNo = $("#parentno").val();
			$("#data-table").datagrid('reload',{
				deptno:deptNo,deptname:deptName,parentDeptNo:parentDeptNo
			} );
		}
		function doClear(){
			$("#deptno").val("");
			$("#deptname").val("");
			$("#parentno").val("");
		}
		function addDept(){
			var path = "${basePath}/init/dept_add" ;
			document.getElementById('frameContent').src = path;
			$('#dlg-frame').dialog('open');
		}
		
		function updateDept(){
			var obj = $('#data-table').datagrid('getSelected');
			if (obj == null || obj.id == null) {
				$.messager.alert('提示', "请先选中一行(只允许单行操作)", 'error');
				return;
			}	
			var path = "${basePath}/dept/updateinit?id=" + obj.id;
			document.getElementById('frameContent').src = path;
			$('#dlg-frame').dialog('open');
		}
		
		function deleteDept(){
			var obj = $('#data-table').datagrid('getSelected');
			if (obj == null || obj.id == null) {
				$.messager.alert('提示', "请先选中一行(只允许单行操作)", 'error');
				return;
			}	
			var url = "${basePath}/dept/delete";
			$.ajax( {
				url : url,
				type : 'post',
				data : {
					id : obj.id
				},
				dataType : 'text',
				success : function(data) {
					if(data>0){
						$.messager.alert('提示','已删除'+data+'行' );
						doSearch();
					}else{
						$.messager.alert('提示', "系统产生错误,请联系管理员!", "error");
					}
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
				<td><span>部门编号:</span></td>
				<td><input id="deptno" name="deptNo"/></td>
				<td><span>部门名称:</span></td>
				<td><input id="deptname" name="deptName"/></td>
				<td><span>上级部门:</span></td>
				<td>
					<input id="parentno" name="parentDeptNo"/>
				</td>
				<td>
					<a href="####" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="doSearch()">查询</a>
					<a href="###" class="easyui-linkbutton" plain="true" iconCls="icon-clear" onclick="doClear()">清除</a>
				</td>
			</tr>
		</table>
	</div>
	<table id="data-table" style="height:510px" title="数据列表" width="85%"></table>
	<div id="dlg-frame">
		<iframe width="99%" height="98%" name="frameContent" id="frameContent"
			frameborder="0"></iframe>
	</div>
	</body>
</html>