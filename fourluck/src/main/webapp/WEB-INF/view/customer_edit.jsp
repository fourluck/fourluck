<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="BASE" value="${pageContext.request.contextPath }"/>
<html>
<head>
<title>客户管理 </title>
</head>
<body>
<h1>编辑</h1>
<table>
		<tr>
			<td>客户名称：</td>
			<td><input name="customer.name" id="name" value="${customer.name }" type="text"/></td>
		</tr>
		<tr>
			<td>联系人：</td>
			<td><input name="customer.contact" id="contact" value="${customer.contact }" type="text"/></td>
		</tr>
		<tr>
			<td>电话号码：</td>
			<td><input name="customer.telePhone" id="telePhone" value="${customer.telePhone }" type="text"/></td>
		</tr>
		<tr>
			<td>邮箱地址：</td>
			<td><input name="customer.email" id="email" value="${customer.email }" type="text"/></td>
		</tr>
</table>
</body>
</html>