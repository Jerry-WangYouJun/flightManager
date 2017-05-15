<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色添加页面</title>
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
	    	url : "${basePath}/role/insert",
       		type:'post',  
       		data: $("#roleForm").serialize(),
       		dataType: 'text',
       		success: function(data){
	       		if(data > 0) {
	       			parent.doSearch();
	       			parent.$('#dlg-frame').dialog("close");
		       	}else{
		       		$.messager.alert('提示',"系统产生错误,请联系管理员!","error");
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
	<form id="roleForm" action="${basePath}/admin/StockServlet?flag=insert" method="post">
	  <table width="100%">
	  	<tr>
	  		<td>角色编码：</td>
	  		<td>
	  			<input type="text" name="roleno" size="14" />
	  		</td>
	  		<td>角色名：</td>
	  		<td>
	  			<input type="text" name="rolename" size="20" />
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>角色等级：</td>
	  		<td>
	  			<input type="text" name="rolelevel" size="14"></input>
	  		</td>
	  	</tr>
	  </table>
  </form>
</body>
</html>