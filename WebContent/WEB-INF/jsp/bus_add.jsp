<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>巴士管理</title>
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
	    	url : "${basePath}/bus/insert",
       		type:'post',  
       		data: $("#busForm").serialize(),
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
	<form id="busForm" action="" method="post">
	  <table width="100%">
	  	<tr>
	  		<td>巴士号：</td>
	  		<td>
	  			<input type="text" id = "bus" name="bus" size="14" />
	  		</td>
	  		<td>机场：</td>
	  		<td>
	  			<select id="airport" name="airport" >
	  				<option value="">---请选择---</option>
	  				<c:forEach  items="${airPortList }" var = "airport">
						  	 	<option value="${airport.airport}">${airport.airport}</option>
					</c:forEach>
	  			</select>
	  		</td>
	  		<td>起点：</td>
	  		<td>
	  			<input type="text" id = "startpoint" name="startpoint" size="14" />
	  		</td>
	  	</tr>
	  	
	  	<tr>
	  		<td>终点：</td>
	  		<td>
	  			<input type="text" id="endpoint" name="endpoint" size="14"  />
	  		</td>
	  		<td>用时：</td>
	  		<td>
	  			<input type="text" id="times" name="times" size="14"  />
	  		</td>
	  	</tr>
	  </table>
  </form>
</body>
</html>