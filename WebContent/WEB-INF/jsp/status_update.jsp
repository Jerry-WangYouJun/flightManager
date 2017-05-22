<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品修改页面</title>
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
	    	url : "${basePath}/status/update",
       		type:'post',  
       		data: $("#statusForm").serialize(),
       		dataType: 'text',
       		success: function(data){
	       		if(data > 0) {
	       			parent.doSearch();
	       			parent.$('#dlg-frame').dialog("close");
		       	}
       		},
       		error: function(transport) { 
       			$.messager.alert('提示',"系统产生错误,请联系管理员!","error");
        	} 
       	});
	}
	
	function chooseOption(obj){
		var optionValue = $(obj).find("option:selected").val();
		var optionText = $(obj).find("option:selected").text();
		if(optionValue == ""){
			$("#producttypename").val("");
		}else{
			$("#producttypename").val(optionText);
		}
		
	}
	
	$(function(){
		var option = $("option");
		 for(var i = 0 ; i < option.length ; i ++){
			  if("${status.flight }" == option[i].value){
				  option[i].selected = "selected";
			  }
			  if("${status.status }" == option[i].value){
				  option[i].selected = "selected";
			  }  
		 }
	});
</script>
</head>
<body>
	<form id="statusForm" action="" method="post">
	    <input type="hidden" value="${status.id }" name = "id">
	  <table width="100%">
	  	<tr>
	  		<td>航班编码：</td>
	  		<td>
	  			<select id="flight" name="flight" >
	  				<option value="">---请选择---</option>
	  				<c:forEach  items="${flightList }" var = "flight">
						  	 	<option value="${status.flight}">${status.flight}</option>
					</c:forEach>
	  			</select>
	  		</td>
	  		<td>航班动态：</td>
	  		<td>
	  			<select id="status" name="status" >
	  				<option value="">---请选择---</option>
	  				<option value="正常">正常</option>
	  				<option value="延误起飞">延误起飞</option>
	  				<option value="延误降落">延误降落</option>
	  				<option value="取消">取消</option>
	  			</select>
	  		</td>
	  	</tr>
	  	
	  	<tr>
	  		<td>预期到达时间：</td>
	  		<td>
	  			<input type="text" id = "pretime" name="pretime" size="14" value="${status.pretime }"/>
	  		</td>
	  		<td>原因：</td>
	  		<td>
	  			<input type="text" id = "reason" name="reason" size="14"value="${status.reason }" />
	  		</td>
	  	</tr>
	  </table>
  </form>
</body>
</html>