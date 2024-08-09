<!DOCTYPE html>
<!-- All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited -->
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>オンラインショップ</title>
<link rel="stylesheet" type="text/css" href="/freemarkn/css/style.css">
<script type="text/javascript" src="/freemarkn/js/cartItemValidate.js"></script>
</head>
<body>
	<jsp:include page="/jsp/header-non-menu.jsp" />
	<div id="mainArea">
		<h1>SHOPPING CART SCREEN</h1>
<%-- contents start --%>
<div id="success" style="color: red;">
</div>
		<div id="target" style="color: red;">
			<c:forEach var="error" items="${errorMessageList}" varStatus="status">
				<p>
					<c:out value="${error}" />
				</p>
			</c:forEach>
			<c:out value="${errorMessage}" />
			<c:out value="${successMessage}" />
		</div>

<br>
		<form method="post" action="/freemarkn/mserv" id="shoppingcartid" name="chkForm">
			<table border="1" >
				<tr>
				<th>S.No</th>
				<th>Product Id</th>
				<th>Product Name</th>
				<th>Product Price</th>
				<th>Product Point</th>
				<th>Inventory</th>
				<th>Quantity</th>
				<th>Total Points</th>
				<th>Amount</th>
				<th>Action</th>
				</tr>
				<c:set var = "cnt" value = "${0}"/>
				<c:forEach var="orders" items="${shoppingCart}">
				<tr>
					<td><input type="hidden" name="sNo" id="sNo" size="10">
					<c:set var="cnt" value = "${cnt+1}"/>
					<c:out value="${cnt}"/>
					</td>
					<td><input type="hidden" value="${orders.product.productId}" name="productId" id="productId" size="10">
					<c:out value="${orders.product.productId}"></c:out>
					</td>
					<td><input type="hidden" name="productName" id="productName" size="10">
					<c:out value="${orders.product.productName}"></c:out>
					</td>
					<td><input type="hidden" value="${orders.product.price}" name="productPrice" id="productPrice" size="10" >
					<c:out value="${orders.product.price}"></c:out>
					</td>
						<td>
					<input type="text" value="${orders.product.point}" size="2" style="border:none" readonly>
					</td>
					<td><input type="text" value="${orders.product.stock.quantity}" name="productInvent" id="productInvent" size="2" style="border:none" readonly>
					</td>
							<td><input type="number"  value="${orders.quantity}" name="productQuantity" id="productQuantity"
							min="1"  max="${orders.product.stock.quantity}"  size="2"  style="width: 60px;" onchange=quantityVal()>
					</td>

					<td>
					<input type="hidden" value="${orders.product.point}" name="prodPoint" id="prodPoint" size="10" >
					<input type="text" value="${orders.subTotalPoint}" name="productPoints" id="productPoints" size="2" style="border:none" readonly>
					</td>
					<td>
					<input type="text" value="${orders.subTotal}" name="productAMt" id="productAMt" size="4" style="border:none" readonly>
					</td>
					<td>
							<a class="btn" href="?flag=B0101CartDelete&productId=${orders.product.productId}">Remove</a>
					</td>
				</tr>
				<!--
				<tr>
					<td></td>
				</tr> -->
			</c:forEach>
				<tr>
					<th colspan="7"><b><c:out value="Total "></b></c:out></th>
					<td colspan="1" align="left" style="padding-left:15px"><b><p id="totPoint" ></p></b></td>
					<td colspan="1" align="left" style="padding-left:7px"><b><p id="totAmount" ></p></b></td>
				</tr>
			</table>
			<br>
			<!--  <input type="hidden" name="flag" value="B0101CartBuy">
			<input type="submit" value="Buy" >
			<input type="submit" value="save" onclick="check()">-->
			<button type="submit" name="flag" value="B0101CartSave">SAVE</button>
			<button type="submit" name="flag" value="B0101CartBuy">BUY</button>
		</form>
		<p>
			<a href="/freemarkn/mserv">[Back to Top]</a>
		</p>
		<%-- contents end --%>
	</div>
<jsp:include page="/jsp/footer.jsp" />

</body>
<script>
document.getElementById("totAmount").innerHTML = getAmt();
document.getElementById("totPoint").innerHTML = getPoints();
</script>
</html>