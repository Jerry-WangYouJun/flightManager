<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>供应商修改页面</title>
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
	    	url : "${basePath}/airport/update",
       		type:'post',  
       		data: $("#airportForm").serialize(),
       		dataType: 'text',
       		success: function(data){
	       		if(data>0) {
	       			parent.doSearch();
	       			parent.$('#dlg-frame').dialog("close");
		       	}
       		},
       		error: function(transport) { 
       			$.messager.alert('提示',"系统产生错误,请联系管理员!","error");
        	} 
       	});
	}
	$(function(){
	});
</script>
</head>
<body> 
	<form id="airportForm" action="${basePath}/admin/SupplierServlet?flag=update" method="post">
	<input type="hidden" value = "${airport.id }"  name = "id">
	  <table width="100%">
	  	<tr>
	  		<td>机场名称：</td>
	  		<td>
	  			<input type="text" name="airport" size="14" value = "${airport.airport }" />
	  		</td>
	  		
	  		<td>军民用：</td>
	  		<td>
	  			<input type="text" name="level" size="14" value = "${airport.level }" />
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>航站楼：</td>
	  		<td>
	  			<input type="text" name="towers" value="${airport.towers }"/>
	  		</td>
	  		<td>飞行范围：</td>
	  		<td>
	  			<input type="text" id="scope"  name="scope" value = "${airport.scope }" />
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>地址：</td>
	  		<td>
	  			<input type="text" id="address"  name="address" value = "${airport.address }" />
	  		</td>
	  		<td >跑道数：</td>
			<td >
				<input type="text" id="runway"  name="runway" value = "${airport.runway }" />
			</td>
	  	</tr>
	  </table>
  </form>
</body>
</html>