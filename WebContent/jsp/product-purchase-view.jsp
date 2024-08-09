<!-- All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited -->
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Product Purchase</title>
<link rel="stylesheet" type="text/css" href="/freemarkn/css/style.css">
<script type="text/javascript" src="/freemarkn/js/creditInputCheck.js"></script>
<style>
/* Chrome, Safari, Edge, Opera */
input::-webkit-outer-spin-button, input::-webkit-inner-spin-button {
	-webkit-appearance: none;
	margin: 0;
}
/* Firefox */
input[type=number] {
	-moz-appearance: textfield;
}
</style>
</head>
<body>
	<jsp:include page="/jsp/header-non-menu.jsp" />
	<div id="mainArea">

		<h1>PRODUCT PURCHASE SCREEN</h1>
		<h2>MEMBER INFORMATION</h2>

		<div id="target" style="color: red;">
			<c:forEach var="error" items="${errorMessageList}" varStatus="status">
				<p>
					<c:out value="${error}" />
				</p>
			</c:forEach>
			<c:out value="${errorMessage}" />
		</div>

		<form method="post" action="/freemarkn/mserv" id="chkForm">
			<table border="1">
				<tr>
					<th>MEMBER ID</th>
					<td><c:out value="${purchaseLoginMember.memberId}" /></td>
				</tr>
				<tr>
					<th> NAME</th>
					<td><c:out value="${purchaseLoginMember.memberName}" /></td>
				</tr>
				<tr>
					<th>ADDRESS</th>
					<td><c:out value="${purchaseLoginMember.address}" /></td>
				</tr>
				<tr>
					<th>PHONE NUMBER</th>
					<td><c:out value="${purchaseLoginMember.phone}" /></td>
				</tr>
				<tr>
					<th>CREDIT CARD NO.</th>
					<!--  <td><input type="text" name="creditCardNo" id="creditCardNo"
						maxlength="16"></td>-->
						<td><input type="text" onkeydown="return onlyNumbers(this);" name="creditCardNo" maxlength="16"  id="creditCardNo" ></td>

				</tr>
			</table>

			<br> <input type="hidden" name="flag"
				value="B0103PurchaseProduct"> <input type="submit"
				value="Purchase">
		</form>
		<h2>Products In Cart</h2>
			<jsp:include page="/jsp/View.jsp" />

		<br> <a href="/freemarkn/mserv">[back to top]</a>

	</div>
<jsp:include page="/jsp/footer.jsp" />

</body>
</html>