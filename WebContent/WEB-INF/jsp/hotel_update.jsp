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
	    	url : "${basePath}/hotel/update",
       		type:'post',  
       		data: $("#hotelForm").serialize(),
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
	<form id="hotelForm" action="${basePath}/admin/SupplierServlet?flag=update" method="post">
	<input type="hidden" value = "${hotel.id }"  name = "id">
	  <table width="100%">
	  	<tr>
	  		<td>酒店名称：</td>
	  		<td>
	  			<input type="text" name="hotel" size="14" value = "${hotel.hotel }" />
	  		</td>
	  		
	  		<td>房间数：</td>
	  		<td>
	  			<input type="text" name="rooms" size="14" value = "${hotel.rooms }" />
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>星级：</td>
	  		<td>
	  			<input type="text" name="stars" value="${hotel.stars }"/>
	  		</td>
	  		<td>剩余房间：</td>
	  		<td>
	  			<input type="text" id="rest"  name="rest" value = "${hotel.rest }" />
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>地址：</td>
	  		<td>
	  			<input type="text" id="address"  name="address" value = "${hotel.address }" />
	  		</td>
	  		<td >电话：</td>
			<td >
				<input type="text" id="phone"  name="phone" value = "${hotel.phone }" />
			</td>
	  	</tr>
	  </table>
  </form>
</body>
</html>