<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>航班管理</title>
	<jsp:include page="/common.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			$('#data-table').datagrid( {
				url : '${basePath}/flight/query',
				rownumbers : true,
				autoRowHeight : true, 
				singleSelect : true,
				pagination : true,
				nowrap: false,
				toolbar: [{
					text:'添加',
					iconCls: 'icon-add',
					handler: function(){addFlight();}
				},'-',{
					text:'修改',
					iconCls: 'icon-edit',
					handler: function(){updateFlight();}
				},'-',{
					text:'删除',
					iconCls: 'icon-remove',
					handler: function(){deleteFlight();}
				}],
				columns:[[
				    {field : 'id',align : 'center',halign:'center',checkbox : true}, 
				    {field : 'flight',title : '航班名称',halign:'center',width : 80},
				    {field : 'flightmodel',title : '航班型号',halign:'center',width : 80},
				    {field : 'flighttype',title : '航班类型',halign:'center',width : 180}, 
				    {field : 'useage',title : '服役时间',halign:'center',width : 120},
				    {field : 'business',title : '商务舱规格',halign:'center',width : 80},
				    {field : 'tourist',title : '经济舱规格',halign:'center',width : 200}
				]]
			});
			
			$('#dlg-frame').dialog( {
				title : '航班管理',
				width :  900,
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
			
			//initDictionarySelect("province", "#search-provinceCode");
		});
		
		function doSearch(){
			var flight = $("#search-flight").val();
			var flightmodel = $("#search-flightmodel").val();
			$('#data-table').datagrid('reload',{
				flight:flight,flightmodel:flightmodel
			} );
		}
		function doClear(){
			$("#search-flightmodel").val("");
			$("#search-flight").val("");
		}
		function addFlight(){
			var path = "${basePath}/init/flight_add";
			document.getElementById('frameContent').src = path;
			$('#dlg-frame').dialog('open');
		}
		
		function updateFlight(){
			var obj = $('#data-table').datagrid('getSelected');
			if (obj == null || obj.id == null) {
				$.messager.alert('提示', "请先选中一行(只允许单行操作)", 'error');
				return;
			}		
			var path = "${basePath}/flight/updateinit?id=" + obj.id;
			document.getElementById('frameContent').src = path;
			$('#dlg-frame').dialog('open');
		}
		
		function deleteFlight(){
			var obj = $('#data-table').datagrid('getSelected');
			if (obj == null || obj.id == null) {
				$.messager.alert('提示', "请先选中一行(只允许单行操作)", 'error');
				return;
			}	
			var url = "${basePath}/flight/delete" ;
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
	<div id="tb" title="查询条件区" class="easyui-panel" style="padding:3px;width:85%" iconCls="icon-search">
		<span>航班名称:</span>
		<input id="search-flight" name="flight"/>
		<span>航班型号:</span>
		<select id="search-flightmodel" name="flightmodel">
	  				<option value="">----请选择----</option>
	  				<option value="波音737">波音747</option>
	  				<option value="波音777">波音777</option>
	  				<option value="空客A380">空客A380</option>
		</select> 
		<a href="####" class="easyui-linkbutton" plain="true" iconCls="icon-search" onclick="doSearch()">查询</a>
		<a href="####" class="easyui-linkbutton" plain="true" iconCls="icon-clear" onclick="doClear()">清除</a>
	</div>
	<table id="data-table" style="height:510px" title="数据列表" width="85%"></table>
	<div id="dlg-frame">
		<iframe width="99%" height="98%" name="frameContent" id="frameContent"
			frameborder="0"></iframe>
	</div>
	
</body>
</html>