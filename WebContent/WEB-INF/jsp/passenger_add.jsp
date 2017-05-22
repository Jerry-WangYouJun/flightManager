<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>航线添加页面</title>
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
	    	url : "${basePath}/passenger/insert",
       		type:'post',  
       		data: $("#passengerForm").serialize(),
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
	<form id="passengerForm" action="" method="post">
	  <table width="100%">
	  	<tr>
	  		<td>乘客姓名：</td>
	  		<td>
	  			<input type="text" id = "passenger" name="passenger" size="14" />
	  		</td>
	  		<td>身份证号：</td>
	  		<td>
	  			<input type="text" id = "idcard" name="idcard" size="14" />
	  		</td>
	  		<td>起点：</td>
	  		<td>
	  			<input type="text" id = "telephone" name="telephone" size="14" />
	  		</td>
	  	</tr>
	  	
	  	<tr>
	  		<td>紧急联系人：</td>
	  		<td>
	  			<input type="text" id="linkman" name="linkman" size="14"  />
	  		</td>
	  		<td>紧急联系人电话：</td>
	  		<td>
	  			<input type="text" id="linkphone" name="linkphone" size="14"  />
	  		</td>
	  	</tr>
	  </table>
  </form>
</body>
</html>