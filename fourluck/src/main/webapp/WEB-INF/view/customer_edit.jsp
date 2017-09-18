<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="BASE" value="${pageContext.request.contextPath }"/>
<html>
<head>
<title>客户管理 </title>
</head>
<body>
<h1>编辑</h1>
<form action="${BASE }/customer_edit" method="post">
	<input type="hidden" value="${customer.id }" name="id"/>
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
	</table>
	</div>
	<div>
		<button type="submit">提交</button>
	</div>
</form>
</body>
</html>