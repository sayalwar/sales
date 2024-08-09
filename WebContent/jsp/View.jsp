<!DOCTYPE html>
<!-- All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited -->
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<link rel="stylesheet" type="text/css" href="/freemarkn/css/style.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="mainArea">
<table border="1" >
				<tr>
				<th>S.No</th>
				<th>Product Id</th>
				<th>Product Name</th>
				<th>Product Price</th>
				<th>Product Point</th>
				<th>Inventory</th>
				<th width="10">Quantity</th>
				<th>Total Points</th>
				<th>Amount</th>
				</tr>
				<c:set var = "cnt" value = "${0}"/>
				<c:forEach var="orders" items="${shoppingCart}">
				<tr>
					<td><input type="hidden" name="sNo" id="sNo" size="10">
					<c:set var="cnt" value = "${cnt+1}"/>
					<c:out value="${cnt}"/>
					</td>
					<td>
					<c:out value="${orders.product.productId}"></c:out>
					</td>
					<td>
					<c:out value="${orders.product.productName}"></c:out>
					</td>
					<td>
					<c:out value="${orders.product.price}"></c:out>
					</td>
						<td>
					<c:out value="${orders.product.point}" />
					</td>
					<td><c:out value="${orders.product.stock.quantity}" />
					</td>
					<td ><c:out  value="${orders.quantity}"/>
					</td>

					<td>
					<c:out value="${orders.subTotalPoint}" />

					</td>
					<td>
					<input type="text" value="${orders.subTotal}" name="productAMt" id="productAMt" size="4" style="border:none" readonly>
					</td>
				</tr>
				<!--
				<tr>
					<td></td>
				</tr> -->
				<c:set var="totPoint" value="${totPoint +orders.subTotalPoint}" scope="page"/>
				<c:set var="totamount" value="${totamount +orders.subTotal}" scope="page"/>
			</c:forEach>
				<tr>
					<th colspan="7"><b><c:out value="Total "></b></c:out></th>
					<td colspan="1" align="left" style="padding-left:15px"><b><c:out value="${totPoint} "/></b></td>
					<td colspan="1" align="left" style="padding-left:7px"><b><c:out value="${totamount} "/></b></td>
				</tr>
			</table>
			</div>
</body>
</html>