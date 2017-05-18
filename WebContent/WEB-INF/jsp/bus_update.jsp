<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品修改页面</title>
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
	    	url : "${basePath}/bus/update",
       		type:'post',  
       		data: $("#busForm").serialize(),
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
	});
</script>
</head>
<body>
	<form id="busForm" action="" method="post">
	    <input type="hidden" value="${bus.id }" name = "id">
	 <table width="100%">
	  	<tr>
	  		<td>巴士号：</td>
	  		<td>
	  			<input type="text" id = "bus" name="bus" size="14"value="${bus.bus }"  />
	  		</td>
	  		<td>机场：</td>
	  		<td>
	  			<input type="text" id = "airport" name="airport" size="14" value="${bus.airport }"/>
	  		</td>
	  		<td>出发点：</td>
	  		<td>
	  			<input type="text" id = "startpoint" name="startpoint" size="14" value="${bus.startpoint }"/>
	  		</td>
	  	</tr>
	  	
	  	<tr>
	  		<td>目的地：</td>
	  		<td>
	  			<input type="text" id="endpoint" name="endpoint" size="14" value="${bus.endpoint }" />
	  		</td>
	  		<td>用时（分）：</td>
	  		<td>
	  			<input type="text" id="times" name="times" size="14" value="${bus.times }" />
	  		</td>
	  	</tr>
	  </table>
  </form>
</body>
</html>