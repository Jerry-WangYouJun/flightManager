<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>部门修改页面</title>
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
	    	url : "${basePath}/dept/update?"+$("#deptForm").serialize(),
       		type:'get',  
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
	
	/* function chooseOption(obj){
		var optionValue = $(obj).find("option:selected").val();
		var optionText = $(obj).find("option:selected").text();
		if(optionValue == ""){
			$("#productTypeName").val("");
		}else{
			$("#productTypeName").val(optionText);
		}
		
	} */
	
    $(function(){
			console.info("${dept.id }");
	}); 
</script>
</head>
<body>
	<form id="deptForm" action="${basePath}/dept/update?flag=update" method="post">
	 <input type="hidden"  name="id" value="${dept.id }" />
	  <table width="100%">
	  	<tr>
	  		<td>部门编号：</td>
	  		<td>
	  			<input type="text" name="deptno" size="14" value="${dept.deptno }" />
	  		</td>
	  		<td>部门名称：</td>
	  		<td>
	  			<input type="text" name="deptname" size="14" value="${dept.deptname }"/>
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>部门领导人：</td>
	  		<td>
	  			<input type="text"  name="deptleader" size="14" value="${dept.deptleader }" />
	  		</td>
	  		<td>部门联系方式：</td>
	  		<td>
	  			<input type="text"  name="depttel" size="20" value="${dept.depttel }" />
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>上级部门：</td>
	  		<td>
	  			<input type="text" name="parentno" size="14" value="${dept.parentno }" ></input>
	  		</td>
	  		<td>部门简介：</td>
	  		<td>
	  			<input type="text" name="deptdesc" size="20" value="${dept.deptdesc }" ></input>
	  		</td>
	  	</tr>
	  	<tr>
	  		<td valign="top" colspan="">备注：</td>
			<td colspan="3">
				<textarea rows="2" cols="60" name="remark">${dept.remark }</textarea>
			</td>
	  	</tr>
	  </table>
  </form>
</body>
</html>