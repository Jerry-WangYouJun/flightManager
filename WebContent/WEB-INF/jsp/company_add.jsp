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
	    	url : "${basePath}/company/insert",
       		type:'post',  
       		data: $("#companyForm").serialize(),
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
	<form id="companyForm" action="" method="post">
	  <table width="100%">
	  	<tr>
	  		<td>原价：</td>
	  		<td>
	  			<input type="text" id = "company" name="company" size="14" />
	  		</td>
	  		<td>折扣类型：</td>
	  		<td>
	  			<input type="text" id = "country" name="country" size="14" />
	  		</td>
	  		<td>折扣：</td>
	  		<td>
	  			<input type="text" id = "flights" name="flights" size="14" />
	  		</td>
	  	</tr>
	  	
	  	<tr>
	  		<td>航线：</td>
	  		<td>
	  			<input type="text" id="airways" name="airways" size="14"  />
	  		</td>
	  		<td>舱位：</td>
	  		<td>
	  			<input type="text" id="scope" name="scope" size="14"  />
	  		</td>
	  	</tr>
	  </table>
  </form>
</body>
</html>