<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>字典添加页面</title>
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
	    	url : "${basePath}/dic/insert",
       		type:'post',  
       		data: $("#dictionaryForm").serialize(),
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
	
</script>
</head>
<body>
	<form id="dictionaryForm" action="${basePath}/admin/DictionaryServlet?flag=insert" method="post">
	  <table width="100%">
	  	<tr>
	  		<td>字典类型ID：</td>
	  		<td>
	  			<input type="text" name="dicno" size="14" />
	  		</td>
	  		<td>字典类型名称：</td>
	  		<td>
	  			<input type="text" name="dictype" size="20" />
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>字典项编码：</td>
	  		<td>
	  			<input type="text" id="diccode" name="diccode" size="14"  />
	  		</td>
	  		<td>字典项值：</td>
	  		<td>
	  			<input type="text" id="dicvalue" name="dicvalue" size="20"  />
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>字典排序：</td>
	  		<td>
	  			<input type="text" name="dicsort" size="14"></input>
	  		</td>
	  	</tr>
	  	<tr>
	  		<td valign="top" >备注：</td>
			<td colspan="3">
				<textarea rows="2" cols="60" name="remark"></textarea>
			</td>
	  	</tr>
	  </table>
  </form>
</body>
</html>