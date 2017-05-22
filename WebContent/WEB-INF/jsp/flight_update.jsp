<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>航班修改页面</title>
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
	    	url : "${basePath}/flight/update",
       		type:'post',  
       		data: $("#flightForm").serialize(),
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
		//initDictionarySelect("province", "#provinceCode",$("#provinceCodeDB").val());
		 var option = $("option");
		 for(var i = 0 ; i < option.length ; i ++){
			  if("${flight.flightmodel }" == option[i].value){
				  option[i].selected = "selected";
			  }
			 	  
		 }
	});
</script>
</head>
<body> 
	<form id="flightForm" action="${basePath}/admin/SupplierServlet?flag=update" method="post">
	<input type="hidden" value = "${flight.id }"  name = "id">
	  <table width="100%">
	  	<tr>
	  		<td>航班名称：</td>
	  		<td>
	  			<input type="text" name="flight" size="14"  value="${flight.flight }"/>
	  		</td>
	  		
	  		<td>航班型号：</td>
	  		<td>
	  			<select id="flightmodel" name="flightmodel"  >
	  				<option value="">----请选择----</option>
	  				<option value="波音737">波音747</option>
	  				<option value="波音777">波音777</option>
	  				<option value="空客A380">空客A380</option>
	  			</select>
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>航班类型：</td>
	  		<td>
	  			<input type="text" name="flighttype" size="14" value="${flight.flighttype }" />
	  		</td>
	  		<td>使用年限：</td>
	  		<td>
	  			<input type="text" id="useage" name="useage" size="14" value="${flight.useage }" />
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>商务舱规格：</td>
	  		<td>
	  			<input type="text" name="business" size="14" value="${flight.business }" />
	  		</td>
	  		<td>经济舱规格：</td>
			<td >
				<input type="text" name="tourist" size="14" value="${flight.tourist }" />
			</td>
	  	</tr>
	  </table>
  </form>
</body>
</html>