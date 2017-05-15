<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>仓库添加页面</title>
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
	    	url : "${basePath}/stock/insert",
       		type:'post',  
       		data: $("#stockForm").serialize(),
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
	
	$(function(){
		initDictionarySelect("province", "#provinceCode");
	});
</script>
</head>
<body>
	<form id="stockForm" action="${basePath}/admin/StockServlet?flag=insert" method="post">
	  <table width="100%">
	  	<tr>
	  		<td>仓库编号：</td>
	  		<td>
	  			<input type="text" name="stockno" size="14" />
	  		</td>
	  		<td>仓库名称：</td>
	  		<td>
	  			<input type="text" name="stockname" size="20" />
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>所在地区：</td>
	  		<td>
	  			<select id="provincecode" name="provincecode" >
	  				<option value="">---请选择---</option>
	  			</select>
	  		</td>
	  		<td>仓库详细地址：</td>
	  		<td>
	  			<input type="text"  name="stockaddress" size="20"  />
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>仓库联系方式：</td>
	  		<td>
	  			<input type="text" name="stocktel" size="14"></input>
	  		</td>
	  		<td>仓管员：</td>
	  		<td>
	  			<input type="text" name="userid" size="20"></input>
	  		</td>
	  	</tr>
	  	<tr>
	  		<td valign="top" colspan="">备注：</td>
			<td colspan="3">
				<textarea rows="2" cols="60" name="remark"></textarea>
			</td>
	  	</tr>
	  </table>
  </form>
</body>
</html>