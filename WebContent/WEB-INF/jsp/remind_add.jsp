<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>供应商添加页面</title>
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
	    	url : "${basePath}/remind/insert",
       		type:'post',  
       		data: $("#remindForm").serialize(),
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
		initDictionarySelect("province", "#provincecode");
	});
</script>
</head>
<body>
	<form id="remindForm" action="${basePath}/admin/SupplierServlet?flag=insert" method="post">
	  <table width="100%">
	  	<tr>
	  		<td>羊群：</td>
	  		<td>
	  			<input type="text" name="sheep" size="14" />
	  		</td>
	  		
	  		<td>提醒方式：</td>
	  		<td>
	  			<select id="remindtype" name="remindtype" >
	  				<option value="">----请选择----</option>
	  				<option value="邮件">邮件</option>
	  				<option value="短信">短信</option>
	  				<option value="微信">微信</option>
	  			</select>
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>提醒时间：</td>
	  		<td>
	  			<input type="text" name="remind" class="easyui-datebox" />
	  		</td>
	  		<td>上次接种时间：</td>
	  		<td>
	  			<input type="text" id="lastdate" class="easyui-datebox" name="lastdate"  />
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>疫苗名称：</td>
	  		<td>
	  			<select  name = "immuneid"  id ="immuneid" >
	  				  	 <option>---请选择---</option>
	  				  	 <c:forEach items="${immunelist }" var="immune">
	  				  	 	 <option value = '${immune.immunename }'>${ immune.immunename}</option>
	  				  	 </c:forEach>
	  			</select>
	  		</td>
	  		<td valign="top" colspan="">备注：</td>
			<td >
				<textarea rows="2" cols="18" name="remark"></textarea>
			</td>
	  	</tr>
	  </table>
  </form>
</body>
</html>