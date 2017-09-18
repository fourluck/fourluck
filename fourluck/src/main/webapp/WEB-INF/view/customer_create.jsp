<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="BASE" value="${pageContext.request.contextPath }"/>
<html>
<head>
<title>客户管理 - 创建客户</title>
</head>
<body>
<h1>创建客户界面</h1>
<form id="customer_form" method="post" enctype="multipart/form-data">
	<div>
	<table>
		<tr>
			<td>客户名称：</td>
			<td><input name="name" id="name" value="${customer.name }" type="text"/></td>
		</tr>
		<tr>
			<td>联系人：</td>
			<td><input name="contact" id="contact" value="${customer.contact }" type="text"/></td>
		</tr>
		<tr>
			<td>电话号码：</td>
			<td><input name="telephone" id="telephone" value="${customer.telephone }" type="text"/></td>
		</tr>
		<tr>
			<td>邮箱地址：</td>
			<td><input name="email" id="email" value="${customer.email }" type="text"/></td>
		</tr>
		<tr>
			<td>照片：</td>
			<td><input name="photo" id="photo" value="${customer.photo }" type="file"/></td>
		</tr>
	</table>
	</div>
	<div>
		<button type="submit">提交</button>
	</div>
</form>
<script src="${BASE }/asset/lib/jquery/jquery.min.js"></script>
<script src="${BASE }/asset/lib/jquery-form/jquery.form.js"></script>
<script type="text/javascript">
	$(function(){
		$("#customer_form").ajaxForm({
			type : 'post',
			url : '${BASE}/customer_create',
			success : function(data){
				if(data){
					location.href = '${BASE}/customer';
				}
			}
		});
	});
</script>
</body>
</html>