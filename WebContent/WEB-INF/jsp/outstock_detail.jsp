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
			var outstockId = $('#id').val();
			var outstockState = $('#outstockState').val();
			if (outstockId == "") {
				$.messager.alert('提示',"当前入库申请单无效，请检查!","error");
				return;
			}
			if (outstockState != "00") {
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
			    	url : "${basePath}/outstockDetail/saveorupdate",
		       		type:'post',  
		       		data: {
		       			outstockId:outstockId,
		       			outstockState:outstockState,
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
	  			${requestScope.outstock.outstockNo }
	  			<input id="id" type="hidden" name="id" value="${requestScope.outstock.id }"/> 
	  			<input id="outstockState" type="hidden" name="outstockState" value="${requestScope.outstock.outstockState }"/> 
	  		</td>
	  		<td>入库仓库：</td>
	  		<td>
	  			 ${requestScope.outstock.stockName }
	  		</td>
	  		<td>入库日期：</td>
	  		<td>
	  			${requestScope.outstock.outstockDate }
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>供货商：</td>
	  		<td>
	  			${requestScope.outstock.supplierName }
	  		</td>
	  		<td>入库单状态：</td>
	  		<td>
	  			<c:if test="${requestScope.outstock.outstockState eq '00' }">创建</c:if >
	  			<c:if test="${requestScope.outstock.outstockState eq '02' }">记账</c:if >
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>备注：</td>
	  		<td colspan="5">
	  			${requestScope.outstock.remark }
	  		</td>
	  	</tr>
	  </table>
	  </div>
	  <table id="goods" title="产品列表"></table>
	<script type="text/javascript">
		$(function(){
			var outstockId = $('#id').val();
		    $('#goods').datagrid({
		    	url: "${basePath}/outstockDetail/search",
		    	queryParams:{
		    		outstockId:outstockId
		    	},		
		    	rownumbers : true,
				autoRowHeight : true,
				singleSelect:true,
				nowrap: false,
				columns:[[
							{field : 'id',title : '产品ID',halign:'center',width : 180,hidden:true},
						    {field : 'productno',title : '产品编号',halign:'center',width : 180},
						    {field : 'productname',title : '产品名称',halign:'center',width : 160},
						    {field : 'productstandard',title : '产品规格',halign:'center',width : 180}, 
						    {field : 'productnum',title : '产品数量',halign:'center',width : 100},
						    {field : 'unit',title : '计量单位',halign:'center',width : 100},
						    {field : 'price',title : '产品单价',halign:'center',width : 100}
						]]
		    });
		});
	   
	</script>
</body>
</html>