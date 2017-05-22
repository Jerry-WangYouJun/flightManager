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
	    	url : "${basePath}/price/update",
       		type:'post',  
       		data: $("#priceForm").serialize(),
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
	
	$(function(){
		var option = $("option");
		 for(var i = 0 ; i < option.length ; i ++){
			  if("${price.flight }" == option[i].value){
				  option[i].selected = "selected";
			  }
			 	  
		 }
	});
</script>
</head>
<body>
	<form id="priceForm" action="" method="post">
	    <input type="hidden" value="${price.id }" name = "id">
	 <table width="100%">
	  	<tr>
	  		<td>原价：</td>
	  		<td>
	  			<input type="text" id = "added" name="added" size="14"value="${price.added }"  />
	  		</td>
	  		<td>折扣类型：</td>
	  		<td>
	  			<input type="text" id = "rebatetype" name="rebatetype" size="14" value="${price.rebatetype }"/>
	  		</td>
	  		<td>折扣：</td>
	  		<td>
	  			<input type="text" id = "rebate" name="rebate" size="14" value="${price.rebate }"/>
	  		</td>
	  	</tr>
	  	
	  	<tr>
	  		<td>航班：</td>
	  		<td>
	  			<select id="flight" name="flight" >
	  				<option value="">---请选择---</option>
	  				<c:forEach  items="${flightList }" var = "flight">
						  	 	<option value="${flight.flight}">${flight.flight}</option>
					</c:forEach>
	  			</select>
	  		</td>
	  		<td>舱位：</td>
	  		<td>
	  			<input type="text" id="classtype" name="classtype" size="14" value="${price.classtype }" />
	  		</td>
	  	</tr>
	  </table>
  </form>
</body>
</html>