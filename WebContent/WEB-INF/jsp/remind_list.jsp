<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>供应商管理</title>
	<jsp:include page="/common.jsp"></jsp:include>
	<script type="text/javascript">
		$(function(){
			$('#data-table').datagrid( {
				url : '${basePath}/remind/query',
				rownumbers : true,
				autoRowHeight : true, 
				singleSelect : true,
				pagination : true,
				nowrap: false,
				toolbar: [{
					text:'添加',
					iconCls: 'icon-add',
					handler: function(){addImmune();}
				},'-',{
					text:'修改',
					iconCls: 'icon-edit',
					handler: function(){updateImmune();}
				},'-',{
					text:'删除',
					iconCls: 'icon-remove',
					handler: function(){deleteImmune();}
				}],
				columns:[[
				    {field : 'id',align : 'center',halign:'center',checkbox : true}, 
				    {field : 'immuneid',title : '疫苗名称',halign:'center',width : 80},
				    {field : 'sheep',title : '羊群',halign:'center',width : 80},
				    {field : 'lastdate',title : '上次接种时间',halign:'center',width : 180}, 
				    {field : 'remind',title : '提醒时间',halign:'center',width : 120},
				    {field : 'remindtype',title : '提醒方式',halign:'center',width : 80},
				    {field : 'remark',title : '备注',halign:'center',width : 200}
				]]
			});
			
			$('#dlg-frame').dialog( {
				title : '供应商管理',
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
			var remindname = $("#search-sheep").val();
			var remindtype = $("#search-remindtype").val();
			$('#data-table').datagrid('reload',{
				remindname:remindname,remindtype:remindtype
			} );
		}
		function doClear(){
			$("#search-remindtype").val("");
			$("#search-sheep").val("");
		}
		function addImmune(){
			var path = "${basePath}/remind/addinit";
			document.getElementById('frameContent').src = path;
			$('#dlg-frame').dialog('open');
		}
		
		function updateImmune(){
			var obj = $('#data-table').datagrid('getSelected');
			if (obj == null || obj.id == null) {
				$.messager.alert('提示', "请先选中一行(只允许单行操作)", 'error');
				return;
			}		
			var path = "${basePath}/remind/updateinit?id=" + obj.id;
			document.getElementById('frameContent').src = path;
			$('#dlg-frame').dialog('open');
		}
		
		function deleteImmune(){
			var obj = $('#data-table').datagrid('getSelected');
			if (obj == null || obj.id == null) {
				$.messager.alert('提示', "请先选中一行(只允许单行操作)", 'error');
				return;
			}	
			var url = "${basePath}/remind/delete" ;
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
		<span>羊群名称:</span>
		<input id="search-sheep" name="sheep"/>
		<span>所在地:</span>
		<select id="search-remindtype" name="remindtype">
	  				<option value="">----请选择----</option>
	  				<option value="羊痘疫苗">羊痘疫苗</option>
	  				<option value="口蹄疫疫苗">口蹄疫疫苗</option>
	  				<option value="羊三联四防疫苗">羊三联四防疫苗</option>
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