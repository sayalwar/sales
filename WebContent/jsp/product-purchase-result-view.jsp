<!DOCTYPE html>
<!-- All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited -->
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>PRODUCT PURCHASE  SCREEN </title>
<link rel="stylesheet" type="text/css" href="/freemarkn/css/style.css">
<script type="text/javascript" src="/freemarkn/js/getValues.js"></script>
</head>
<body>
	<jsp:include page="/jsp/header-non-menu.jsp" />

	<div id="mainArea">
		<h1>PRODUCT PURCHASE RESULT SCREEN</h1>
		<%-- contents start --%>

		<div id="target" style="color: red;">
			<c:forEach var="error" items="${errorMessageList}" varStatus="status">
				<p>
					<c:out value="${error}" />
				</p>
			</c:forEach>
			<c:out value="${errorMessage}" />
		</div>
			<img src="img/Thankyou.png" alt="Thankyou msg" width="400">
					<div id="printTable">
<center>
		<h3>	Sales Invoice</h3>
</center>
			<table border="1" style="width: 100%">
				<tr>
				<th>S.No</th>
				<th>Product Id</th>
				<th>Product Name</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Points</th>
				<th>Amount</th>
				</tr>

				<c:set var = "cnt" value = "${0}"/>

				<c:forEach var="orders" items="${orderList}">
					<tr>
						<td><input type="hidden" name="sNo" id="sNo" size="10">
							<c:set var="cnt" value="${cnt+1}" /> <c:out value="${cnt}" /></td>
						<td><c:out value="${orders.product.productId}"></c:out></td>
						<td><c:out value="${orders.product.productName}"></c:out></td>
						<td><c:out value="${orders.product.price}"></c:out></td>
						<td><c:out value="${orders.quantity}"></c:out></td>
						<td><input type="hidden" value="${orders.subTotalPoint}"
							name="subPoint"> <c:out value="${orders.subTotalPoint}"></c:out>
						</td>
						<td><input type="hidden" value="${orders.subTotal}"
							name="subAmt"> <c:out value="${orders.subTotal}"></c:out>
						</td>
					</tr>
				</c:forEach>
				<tr>
					<th colspan="5"><b><c:out value="Total "></c:out></b></th>
					<td colspan="1"><b><p id="totPoint" ></p></b></td>
					<td colspan="1"><b><p id="totAmount" ></p></b></td>
				</tr>
			</table>
			<br>
		</div>

		<input type="button" id="btnPrint" onclick="javascript:printData('printTable')" value="Print Invoice" />
		<br> <br>
		<input type="hidden" name="flag" value="B0103ProductPurchaseResult">
		<a href="/freemarkn/mserv">[Back to Top] </a>
		<%-- contents end --%>
	</div>
	<div id="footerArea">
		<small>Copyright YYYY FUJITSU LEARNING MEDIA LIMITED</small>
	</div>
</body>
<script>
document.getElementById("totAmount").innerHTML = getAmt();
document.getElementById("totPoint").innerHTML = getPoints();
</script>
</html>