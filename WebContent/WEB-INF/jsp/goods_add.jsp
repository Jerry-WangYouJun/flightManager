<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品添加页面</title>
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
	    	url : "${basePath}/goods/insert",
       		type:'post',  
       		data: $("#goodsForm").serialize(),
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
		initDictionarySelect("productType", "#productTypeCode");
		initSupplierSelect("#supplierNo");
	});
</script>
</head>
<body>
	<form id="goodsForm" action="${basePath}/admin/GoodsServlet?flag=insert" method="post">
	  <table width="100%">
	  	<tr>
	  		<td>饲料编码：</td>
	  		<td>
	  			<input type="text" name="productno" size="14" />
	  		</td>
	  		<td>饲料分类：</td>
	  		<td>
	  			<select id="productTypeCode" name="producttype">
	  				<option value="">---请选择---</option>
	  			</select>
	  		</td>
	  		<td>饲料供货商：</td>
	  		<td>
	  			<select id="supplierNo" name="supplierNo" >
	  				<option value="">---请选择---</option>
	  			</select>
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>饲料名称：</td>
	  		<td>
	  			<input type="text" name="productname" size="14"  />
	  		</td>
	  		<td>饲料规格：</td>
	  		<td>
	  			<input type="text" id="productStandard" name="productstandard" size="14"  />
	  		</td>
	  		<td>生产日期：</td>
	  		<td>
	  			<input class="easyui-datebox" type="text" name="productdate"  size="14"
	  				data-options="currentText:'今日',closeText:'关闭',editable:false"></input>
	  		</td>
	  	</tr>
	  	<tr>
	  		<td>饲料数量：</td>
	  		<td>
	  			<input type="text" name="productnum" size="14"></input>
	  		</td>
	  		<td>计量单位：</td>
	  		<td>
	  			<input type="text" name="unit" size="14"></input>
	  		</td>
	  		<td>饲料单价：</td>
	  		<td>
	  			<input type="text" name="price" size="14"></input>
	  		</td>
	  	</tr>
	  	<tr>
	  		<td valign="top" colspan="">备注：</td>
			<td colspan="5">
				<textarea rows="2" cols="60" name="remark"></textarea>
			</td>
	  	</tr>
	  </table>
  </form>
</body>
</html>