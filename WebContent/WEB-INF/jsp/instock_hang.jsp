<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta  http-equiv="pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品添加</title>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/common.css">
	<script type="text/javascript" src="${basePath}/js/common.js"></script>
	<script type="text/javascript" src="${basePath}/js/json2.js"></script>
	<script type="text/javascript" src="${basePath}/js/jquery.edatagrid.js"></script>
	<style type="text/css">
		table{
			font-size:12px;
		}
		textarea{
			font-size:12px;
		}
	</style> 
	<script type="text/javascript">
	
		function doServlet(){
			var instockId = $('#id').val();
			var instockState = $('#instockState').val();
			if (instockId == "") {
				$.messager.alert('提示',"当前入库申请单无效，请检查!","error");
				return;
			}
			if (instockState != "00") {
				$.messager.alert('提示',"当前入库申请单状态不是【创建状态】，无法进行确认操作，请检查!","error");
				return;
			}
			
			//自动保存编辑状态的数据
			var row = $('#goods').edatagrid('getSelected');
			if(row != null){
				var index = $('#goods').edatagrid('getRowIndex',row);
				$('#goods').edatagrid('endEdit',index);
			}
			//获取表格中的所有记录数
			var rows = $('#goods').datagrid('getRows');
			//校验产品数量不能小于0
			for(var i=0;i<rows.length;i++){
				if(rows[i].productnum == "" || isNaN(rows[i].productnum) || rows[i].productnum <= 0){
					$.messager.alert('提示',"产品编号是[" + rows[i].productno + "]的产品数量不能为非数字且值不能小于0!","error");
					return;
				}
			}
			$.ajax({
			    	url : "${basePath}/instockDetail/saveorupdate",
		       		type:'post',  
		       		data: {
		       			instockId:instockId,
		       			instockState:instockState,
		       			goodsList:JSON.stringify(rows)
		       			},
		       		dataType: 'json',
		       		success: function(data){
			           	parent.$.messager.alert('提示',data.msg);
			       		if(data.success == true) {
			       			parent.doSearch();
			       			parent.$('#dlg-frame').dialog("close");
				       	}
		       		},
		       		error: function(transport) { 
		       			$.messager.alert('提示',"系统产生错误,请联系管理员!","error");
		        	} 
		       	}); 
		}
		
	</script>

</head>
<body>
	<center>
	 	<h1>入库申请单</h1>
	 </center>
	 <div class="">
	  <table width="100%">
	  	<tr>
	  		<td>入库申请单编号：</td>
	  		<td>
	  			${requestScope.instock.instockNo }
	  			<input id="id" type="hidden" name="id" value="${requestScope.instock.id }"/> 
	  			<input id="instockState" type="hidden" name="instockState" value="${requestScope.instock.instockState }"/> 
	  		</td>
	  		<td>入库仓库：</td>
	  		<td>
	  			 ${requestScope.instock.stockName }
	  		</td>
	  		<td>入库日期：</td>
	  		<td>
	  			${requestScope.instock.instockDate }
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>供货商：</td>
	  		<td>
	  			${requestScope.instock.supplierName }
	  		</td>
	  		<td>入库单状态：</td>
	  		<td>
	  			<c:if test="${requestScope.instock.instockState eq '00' }">创建</c:if >
	  			<c:if test="${requestScope.instock.instockState eq '02' }">记账</c:if >
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>备注：</td>
	  		<td colspan="5">
	  			${requestScope.instock.remark }
	  		</td>
	  	</tr>
	  </table>
	  </div>
	  <table id="goods"   title="产品列表"></table>
	<script type="text/javascript">
	$(function(){
		var instockId = $('#id').val();
	    $('#goods').edatagrid({
	    	url: "${basePath}/instockDetail/search",
	    	destroyUrl:	"${basePath}/instockDetail/delete",
	    	queryParams:{
	    		instockId:instockId
	    	},		
	    	rownumbers : true,
			autoRowHeight : true,
			singleSelect:true,
			nowrap: false,
			toolbar: [{
				text:'添加',
				iconCls: 'icon-add',
				handler: function(){
					$('#goods').edatagrid('addRow');
				}
			},'-',{
				text:'取消编辑',
				iconCls: 'icon-remove',
				handler: function(){
					var row = $('#goods').edatagrid('getSelected');
					if(row == null)
						return;
					var index = $('#goods').edatagrid('getRowIndex',row);
					$('#goods').edatagrid('cancelRow',index);
				}
			},'-',{
				text:'删除',
				iconCls: 'icon-removeGoods',
				handler: function(){
					 $('#goods').edatagrid('destroyRow');
				}
			}],
			columns:[[
						{field : 'id',title : '产品ID',halign:'center',width : 180,hidden:true},
					    {field : 'productno',title : '产品编号',halign:'center',width : 180,editor:{type:'combogrid',options:{
					    	panelWidth:700,
					    	url: '${basePath}/goods/search',
					    	idField:'id',
					    	textField:'productno',
					    	mode:'remote',
					    	fitColumns:true,
					    	columns:[[
					    		{field : 'productno',title:'产品编码',halign:'center',width:100},
					    		{field : 'productname',title : '产品名称',halign:'center',width : 160},
							    {field : 'productstandard',title : '产品规格',halign:'center',width : 120}, 
							    {field : 'unit',title : '计量单位',halign:'center',width : 100},
							    {field : 'price',title : '产品单价',halign:'center',width : 100}
					    	]],
					    	onClickRow:function(index,rowData){
					    		rowData.id="";
					    		rowData.productNum="";
					    		var rowIndex=$('#goods').datagrid('getRowIndex',$('#goods').datagrid('getSelected'));
			                    $('#goods').edatagrid("updateRow",{index:rowIndex,row:rowData});
			                    $('#goods').datagrid("unselectRow",rowIndex);
							}
					    }}},
					    {field : 'productname',title : '产品名称',halign:'center',width : 160,editor:{type:'validatebox',options:{required:true}}},
					    {field : 'productstandard',title : '产品规格',halign:'center',width : 180,editor:{type:'validatebox',options:{required:true}}}, 
					    {field : 'productnum',title : '产品数量',halign:'center',width : 100,editor:{type:'numberbox',options:{precision:2}}},
					    {field : 'unit',title : '计量单位',halign:'center',width : 100,editor:{type:'validatebox',options:{required:true}}},
					    {field : 'price',title : '产品单价',halign:'center',width : 100,editor:{type:'numberbox',options:{precision:2}}}
					]],
					onDblClickCell:function(rowIndex, field, value){
						$(this).datagrid('beginEdit', rowIndex);
						var ed = $(this).datagrid('getEditor', {index:rowIndex,field:field});
						$(ed.target).focus();
					}
	    });
	});
	</script>
</body>
</html>