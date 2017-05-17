<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>航线添加页面</title>
	<jsp:include page="/common.jsp"></jsp:include>
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
	    	url : "${basePath}/ticket/insert",
       		type:'post',  
       		data: $("#statusForm").serialize(),
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
	<form id="statusForm" action="" method="post">
	  <table width="100%">
	  	<tr>
	  		<td>航班编码：</td>
	  		<td>
	  			<input type="text" id = "flight" name="flight" size="14" />
	  		</td>
	  		<td>订单编号：</td>
	  		<td>
	  			<input type="text" id = "ordercode" name="ordercode" size="14" />
	  		</td>
	  		<td>购票时间：</td>
	  		<td>
	  			<input type="text" id = "ticketdate" name="ticketdate" size="14" />
	  		</td>
	  	</tr>
	  	
	  	<tr>
	  		<td>机票价格：</td>
	  		<td>
	  			<input type="text" id="price" name="price" size="14"  />
	  		</td>
	  		<td>状态：</td>
	  		<td>
	  			<input type="text" id="status" name="status" size="14"  />
	  		</td>
	  	</tr>
	  </table>
  </form>
</body>
</html>