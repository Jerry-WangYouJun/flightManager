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
	    	url : "${basePath}/airway/update",
       		type:'post',  
       		data: $("#airwayForm").serialize(),
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
	<form id="airwayForm" action="" method="post">
	    <input type="hidden" value="${airway.id }" name = "id">
	  <table width="100%">
	  	<tr>
	  		<td>饲料编码：</td>
	  		<td>
	  			<input type="text" id = "flight" name="flight" value="${airway.flight }" size="14" />
	  		</td>
	  		<td>饲料分类：</td>
	  		<td>
	  			<select id="flighttype" name="flighttype" >
	  				<option value="">---请选择---</option>
	  				<option value="直飞">直飞</option>
	  				<option value="转机">转机</option>
	  			</select>
	  		</td>
	  		<td>饲料供货商：</td>
	  		<td>
	  			<select id="company" name="company" >
	  				<option value="">---请选择---</option>
	  			</select>
	  		</td>
	  	</tr>
	  	
	  	<tr>
	  		<td>饲料名称：</td>
	  		<td>
	  			<input type="text" id="startdate" name="startdate" size="14"  value="${airway.startdate }" />
	  		</td>
	  		<td>饲料规格：</td>
	  		<td>
	  			<input type="text" id="flighttime" name="flighttime" size="14"   value="${airway.flighttime }"/>
	  		</td>
	  		<td>生产日期：</td>
	  		<td>
	  			<input  type="text" id = "ontime" name="ontime"  size="14"  value="${airway.ontime }"/>
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>饲料名称：</td>
	  		<td>
	  			<input type="text" id="fromairport" name="fromairport" size="14"  value="${airway.fromairport }" />
	  		</td>
	  		<td>饲料规格：</td>
	  		<td>
	  			<input type="text" id="toairport" name="toairport" size="14"  value="${airway.toairport }" />
	  		</td>
	  	</tr>
	  </table>
  </form>
</body>
</html>