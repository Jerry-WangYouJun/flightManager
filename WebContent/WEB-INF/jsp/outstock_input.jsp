<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>出库单保存操作</title>
	<link rel="stylesheet" type="text/css" href="${basePath}/css/common.css">
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
			 $.ajax({
		    	url : "${basePath}/outstock/save",
	       		type:'post',  
	       		data: $("#outstockForm").serialize(),
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
		
		//$(function(){
			//initStockSelect("#stockId");
			//initSupplierSelect("#supplierId");
			
		//});
		
	</script>

</head>
<body>
	<center>
		<h1>出库申请单</h1>
	</center>
	
	<form:form id="outstockForm"  action="${basePath}/outstock/save" modelAttribute="outstock" method="post">
		<c:if test="${outstock.id != null }">
			<form:hidden path="id"/>
			<%-- <form:hidden path="outstockstate"/> --%>
			<input type="hidden" name="_method" value="PUT"/>
		</c:if>
		
		<table width="100%" class="table" cellpadding="0" cellspacing="0">
		  	<tr>
		  		<td class="td">出库申请单编号：</td>
		  		<td class="td">
		  			<form:input id="outstockNo" path="outstockno"/>
		  		</td>
		  		<td class="td">出库仓库：</td>
		  		<td class="td">
		  			<form:select id="stockId"  path="stockid" items="${requestScope.stocks }" itemLabel="stockname" itemValue="id">
		  				<form:option value="">---请选择----</form:option>
		  			</form:select>
		  		</td>
		  		<td class="td">出库日期：</td>
		  		<td class="td">
		  			<form:input path="outstockdate"  class="easyui-datebox" data-options="currentText:'今日',closeText:'关闭',editable:false"/>
		  		</td>
		  	</tr>
		  	<tr>
		  		<td class="td">供货商：</td>
		  		<td class="td">
		  			<form:select id="supplierId"  path="supplierid" items="${requestScope.suppliers }" itemLabel="suppliername" itemValue="id">
		  				<form:option value="">---请选择----</form:option>
		  			</form:select>
		  		</td>
		  		<td class="td">出库饲料：</td>
		  		<td class="td">
		  			<form:select id="goodid"  path="goodid" >
		  				<c:forEach items="${goods}" var = "good">
		  				 	  <form:option value="${good.id }">${good.productname }</form:option>
		  				</c:forEach>
		  			</form:select>
		  		</td>
		  		<td class="td">数量：</td>
		  		<td class="td"  >
		  			<form:input id="outstockNum" path="outstockNum"/>
		  		</td>
		  		
		  	</tr>
		  	<tr>
		  		 <td class="td">备注：</td>
		  		<td class="td"  >
		  			<form:textarea path="remark" />
		  		</td>
		  	</tr>
		  </table>
	</form:form>
</body>
</html>