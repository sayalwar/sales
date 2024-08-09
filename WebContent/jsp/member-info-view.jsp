<!DOCTYPE html>
<!-- All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited -->
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>オンラインショップ</title>
<link rel="stylesheet" type="text/css" href="/freemarkn/css/style.css">
</head>
<body>
	<jsp:include page="/jsp/header-non-menu.jsp" />
	<div id="mainArea">
		<h1>MEMBER INFORMATION SCREEN</h1>
		<%-- contents start --%>
		Thanks for using our website.<br>
		Your Current Point is <c:out value="${CommonLoginMember.memberPoint}" />.

		<h2>Member Information</h2>
		<table border="1">
		    <tr>
		        <th>Member Id</th>
		        <td><c:out value="${CommonLoginMember.memberId}" /></td>
		    </tr>
		    <tr>
		        <th>Member Name</th>
		        <td><c:out value="${CommonLoginMember.memberName}" /></td>
		    </tr>
		    <tr>
		        <th>Address</th>
		        <td><c:out value="${CommonLoginMember.address}" /></td>
		    </tr>
		    <tr>
		        <th>Telephone Number	</th>
		        <td><c:out value="${CommonLoginMember.phone}" /></td>
		    </tr>
		</table>

		<h2>Order Information</h2>

		<c:if test="${empty orderList}">
		  <c:out value="${message}" />
		</c:if>

		<c:if test="${!empty orderList}">
			<table border="1" style="width:100%">
			    <tr bgcolor="#9999ff">
			   	 <th>S.No</th>
			        <th>Order Id</th>
			        <th>Date</th>
			        <th>Product Id</th>
					<th>Product Name</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Point</th>
					<th>Amount</th>
			    </tr>
			    <c:set var = "cnt" value = "${0}"/>
			    <c:forEach var="order" items="${orderList}" varStatus="status">
				    <tr><td><input type="hidden" name="sNo" id="sNo" size="10">
					<c:set var="cnt" value = "${cnt+1}"/>
					<c:out value="${cnt}"/>
					</td>
				        <td><c:out value="${order.orderId}" /></td>
				        <td><c:out value="${order.orderDate}" /></td>
				        <td><c:out value="${order.product.productId}" /></td>
						<td><c:out value="${order.product.productName}" /></td>
						<td style="text-align: right;"><c:out value="${order.product.price}" /></td>
						<td style="text-align: right;"><c:out value="${order.quantity}" /></td>
						<td style="text-align: right;"><c:out value="${order.subTotalPoint}" /></td>
						<td style="text-align: right;"><c:out value="${order.subTotal}" /></td>
				    </tr>
				    <c:set var="total" value="${total + order.subTotal}" />
			    </c:forEach>
			    <tr>
					<th colspan="7">Total</th>
					<td style="text-align: right;"><c:out value="${CommonLoginMember.memberPoint}" /></td>
					<td style="text-align: right;"><c:out value="${total}" /></td>
				</tr>
			</table>
		</c:if>
		<br>

		<a href="/freemarkn/mserv?flag=B0203G01">[Member Update Screen]</a>&nbsp;&nbsp;
		<a href="/freemarkn/mserv">[Back to Top]</a>

		<%-- contents end --%>
	</div>
<jsp:include page="/jsp/footer.jsp" />
</body>
</html>