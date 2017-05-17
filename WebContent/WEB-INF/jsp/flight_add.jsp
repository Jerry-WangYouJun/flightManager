<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>供应商添加页面</title>
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
	    	url : "${basePath}/flight/insert",
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
		initDictionarySelect("province", "#provincecode");
	});
</script>
</head>
<body>
	<form id="flightForm" action="" method="post" role="form">
	  <table width="100%">
	  	<tr>
	  		<td>航班名称：</td>
	  		<td>
	  			<input type="text" name="flight" size="14" />
	  		</td>
	  		
	  		<td>航班型号：</td>
	  		<td>
	  			<select id="flightmodel" name="flightmodel" >
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
	  			<input type="text" name="flighttype" size="14"  />
	  		</td>
	  		<td>服役时间：</td>
	  		<td>
	  			<input type="text" id="useage" name="useage" size="14" />
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>商务舱规格：</td>
	  		<td>
	  			<input type="text" name="business" size="14"  />
	  		</td>
	  		<td>经济舱规格：</td>
			<td >
				<input type="text"  name="tourist" size = "14"/>
			</td>
	  	</tr>
	  </table>
  </form>
</body>
</html>