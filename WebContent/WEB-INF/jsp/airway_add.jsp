<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>航线添加页面</title>
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
	    	url : "${basePath}/airway/insert",
       		type:'post',  
       		data: $("#airwayForm").serialize(),
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
	<form id="airwayForm" action="" method="post">
	  <table width="100%">
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
	  		<td>航空公司：</td>
	  		<td>
	  			<select id="company" name="company" >
	  				<option value="">---请选择---</option>
	  				<c:forEach  items="${companyList }" var = "company">
						  	 	<option value="${company.company}">${company.company}</option>
					</c:forEach>
	  			</select>
	  		</td>
	  		<td>飞行类型：</td>
	  		<td>
	  			<select id="flighttype" name="flighttype">
	  				<option value="">---请选择---</option>
	  				<option value="直飞">直飞</option>
	  				<option value="转机">转机</option>
	  			</select>
	  		</td>
	  	</tr>
	  	
	  	<tr>
	  		<td>起飞时间：</td>
	  		<td>
	  			<input type="text" id="startdate" name="startdate" size="14"  class="easyui-datebox" />
	  		</td>
	  		<td>飞行时间：</td>
	  		<td>
	  			<input type="text" id="flighttime" name="flighttime" size="14" />
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>起飞机场：</td>
	  		<td>
	  			<input type="text" id="fromairport" name="fromairport" size="14"  />
	  		</td>
	  		<td>到达机场：</td>
	  		<td>
	  			<input type="text" id="toairport" name="toairport" size="14"  />
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>准点率：</td>
	  		<td>
	  			<input  type="text" id = "ontime" name="ontime"  size="14"/>
	  		</td>
	  	</tr>
	  </table>
  </form>
</body>
</html>