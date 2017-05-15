<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
	<jsp:include page="/common.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			$('#data-table').datagrid( {
				url : '${basePath}/role/query',
				rownumbers : true,
				autoRowHeight : true, 
				singleSelect : true,
				pagination : true,
				nowrap: false,
				toolbar: [{
					text:'添加',
					iconCls: 'icon-add',
					handler: function(){addRole();}
				},'-',{
					text:'修改',
					iconCls: 'icon-edit',
					handler: function(){updateRole();}
				},'-',{
					text:'删除',
					iconCls: 'icon-remove',
					handler: function(){deleteRole();}
				}],
				columns:[[
				    {field : 'id',align : 'center',halign:'center',checkbox : true}, 
				    {field : 'roleno',title : '角色编码名',halign:'center',width : 80},
				    {field : 'rolename',title : '角色名',halign:'center',width : 80},
				    {field : 'rolelevel',title : '角色等级',halign:'center',width : 80}
				]]
			});
			
			$('#dlg-frame').dialog( {
				title : '角色管理',
				width :  700,
				height : 320,
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
		});
		
		function doSearch(){
			var roleno = $("#search-roleno").val();
			var rolename = $("#search-rolename").val();
			var rolelevel = $("#search-rolelevel").val();
		
			$('#data-table').datagrid('reload',{
				roleno:roleno,rolename:rolename,
				rolelevel:rolelevel
			} );
		}
		function doClear(){
			$("#search-roleno").val("");
			$("#search-rolename").val("");
			$("#search-rolelevel").val("");
		
		}
		function addRole(){
			var path = "${basePath}/init/role_add";
			document.getElementById('frameContent').src = path;
			$('#dlg-frame').dialog('open');
		}
		
		function updateRole(){
			var obj = $('#data-table').datagrid('getSelected');
			if (obj == null || obj.id == null) {
				$.messager.alert('提示', "请先选中一行(只允许单行操作)", 'error');
				return;
			}		
			var path = "${basePath}/role/updateinit?id=" + obj.id;
			document.getElementById('frameContent').src = path;
			$('#dlg-frame').dialog('open');
		}
		
		function deleteRole(){
			var del= confirm("确认删除？");
			if(!del){
				return false;
			}
			var obj = $('#data-table').datagrid('getSelected');
			if (obj == null || obj.id == null) {
				$.messager.alert('提示', "请先选中一行(只允许单行操作)", 'error');
				return;
			}	
			var url = "${basePath}/role/delete/" ;
			$.ajax( {
				url : url,
				type : 'post',
				data : {
					id : obj.id
				},
				dataType : 'text',
				success : function(data) {
					if(data>0){
						doSearch();
					}else{
						$.messager.alert('提示',data.msg,"error");
					}
					
				},
				error : function(transport) {
					$.messager.alert('提示', "系统产生错误,请联系管理员!", "error");
				}
			});
		}
	</script>

</head>
<body class="easyui-layout">
	<div id="tb" region="north" title="查询条件区" class="easyui-panel"  iconCls="icon-search"  style="padding:3px;height: 60px; width: 86%"  >
		<span>角色编码:</span>
		<input id="search-roleno" name="roleno"/>
		<span>角色名:</span>
		<input id="search-rolename" name="rolename"/>
		<span>角色等级:</span>
			<input id="search-rolelevel" name="rolelevel"/>
		<a href="###" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="doSearch()">查询</a>
		<a href="###" class="easyui-linkbutton" plain="true" iconCls="icon-clear" onclick="doClear()">清除</a>
	</div>
	<div region="center"  border="0">
		<form:form id="dataForm" action="${basePath}/role/role_delete" modelAttribute="role" method="post">
			<input type="hidden" name="_method" value="DELETE"/>
			<table id="data-table" style="height:510px" title="数据列表" width="86%" ></table>
		</form:form>
		
		<div id="dlg-frame">
			<iframe width="80%" height="90%" name="frameContent" id="frameContent"
				frameborder="0"></iframe>
		</div>
	</div>
	
	
</body>
</html>