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
				url : '${basePath}/user/list',
				rownumbers : true,
				autoRowHeight : true, 
				singleSelect : true,
				pagination : true,
				nowrap: false,
				toolbar: [{
					text:'添加',
					iconCls: 'icon-add',
					handler: function(){addUser();}
				},'-',{
					text:'修改',
					iconCls: 'icon-edit',
					handler: function(){updateUser();}
				},'-',{
					text:'删除',
					iconCls: 'icon-remove',
					handler: function(){deleteUser();}
				}],
				columns:[[
				    {field : 'id',align : 'center',halign:'center',checkbox : true}, 
				    {field : 'userno',title : '用户名',halign:'center',width : 80},
				    {field : 'username',title : '用户姓名',halign:'center',width : 80},
				    {field : 'sex',title : '性别',halign:'center',width : 80,formatter:function(value,rowData,rowIndex){
				    		if(value == "1"){
				    			return "女";
				    		}else if(value == "0"){
				    			return "男";
				    		}else{
				    			return "";
				    		}
				    }},
				    {field : 'position',title : '职位',halign:'center',width : 80},
				    {field : 'telphone',title : '联系方式',halign:'center',width : 100},
				    {field : 'email',title : '邮箱',halign:'center',width : 120}, 
				    {field : 'remark',title : '备注',halign:'center',width : 200}
				]]
			});
			
			$('#dlg-frame').dialog( {
				title : '管理员信息',
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
			var userNo = $("#search-userNo").val();
			var userName = $("#search-userName").val();
			var position = $("#search-position").val();
			var deptNo = $("#search-deptNo").val();
		
			$('#data-table').datagrid('reload',{
				userNo:userNo,userName:userName,
				deptId:deptNo
			} );
		}
		function doClear(){
			$("#search-userNo").val("");
			$("#search-userName").val("");
			$("#search-position").val("");
			$("#search-deptNo").val("");
		
			$("#search-endDate").combo("setText","");
			$("#search-endDate").combo("setValue","");
		}
		function addUser(){
			var path = "${basePath}/user/user_input";
			document.getElementById('frameContent').src = path;
			$('#dlg-frame').dialog('open');
		}
		
		function updateUser(){
			var obj = $('#data-table').datagrid('getSelected');
			if (obj == null || obj.id == null) {
				$.messager.alert('提示', "请先选中一行(只允许单行操作)", 'error');
				return;
			}		
			var path = "${basePath}/user/user_update_input/" + obj.id;
			document.getElementById('frameContent').src = path;
			$('#dlg-frame').dialog('open');
		}
		
		function deleteUser(){
			var del= confirm("确认删除？");
			if(!del){
				return false;
			}
			var obj = $('#data-table').datagrid('getSelected');
			if (obj == null || obj.id == null) {
				$.messager.alert('提示', "请先选中一行(只允许单行操作)", 'error');
				return;
			}	
			var url = "${basePath}/user/user_delete/" + obj.id;
			$.ajax( {
				url : url,
				type : 'post',
				data : $("#dataForm").serialize(),
				dataType : 'json',
				success : function(data) {
					if(data.success){
						$.messager.alert('提示',data.msg);
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
		<span>用户名:</span>
		<input id="search-userNo" name="userNo"/>
		<span>姓名:</span>
		<input id="search-userName" name="userName"/>
		<span>所属部门:</span>
		<select id="search-deptNo" name="deptId">
			<option value="">---请选择---</option>
		</select> 
		<span>岗位:</span>
			<input id="search-position" name="position"/>
		<a href="####" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="doSearch()">查询</a>
		<a href="####" class="easyui-linkbutton" plain="true" iconCls="icon-clear" onclick="doClear()">清除</a>
	</div>
	<div region="center"  border="0">
		<form:form id="dataForm" action="${basePath}/user/user_delete" modelAttribute="user" method="post">
			<input type="hidden" name="_method" value="DELETE"/>
			<table id="data-table"  title="数据列表" width="86%" ></table>
		</form:form>
		
		<div id="dlg-frame">
			<iframe width="99%" height="98%" name="frameContent" id="frameContent"
				frameborder="0"></iframe>
		</div>
	</div>
	
	
</body>
</html>