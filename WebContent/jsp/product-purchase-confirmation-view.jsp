<!DOCTYPE html>
<!-- All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited -->
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>PRODUCT PURCHASE  SCREEN </title>
<link rel="stylesheet" type="text/css" href="/freemarkn/css/style.css">
</head>
<body>
	<jsp:include page="/jsp/header-non-menu.jsp" />
<div id="mainArea">
		<h1>PRODUCT PURCHASE  CONFIRMATION SCREEN</h1>
		<%-- contents start --%>

		<div>
			<c:forEach var="error" items="${errorMessageList}" varStatus="status">
				<p>
					<c:out value="${error}" />
				</p>
			</c:forEach>
			<c:out value="${errorMessage}" />
		</div>

		<form method="post" action="/freemarkn/mserv" id="chkForm" >
			<table border="1" >
				<tr>
		        <th>MEMBER ID</th>
		        <td><c:out value="${purchaseLoginMember.memberId}" /></td>
		    </tr>
		    <tr>
		        <th>NAME</th>
		        <td><c:out value="${purchaseLoginMember.memberName}" /></td>
		    </tr>
				<tr>
					<th>CREDIT CARD NO.</th>
					 <td><c:out value="${requestScope.creditCardNo}" /></td>
				</tr>

			</table>
			<br> <input type="hidden" name="flag" value="B0103ProductConfirmation" >
			<input  type="submit" value="Confirm" >
		</form>
		<br>
		<a  href="/freemarkn/mserv?flag=backB0103G01">[Back] </a>
		<a  href="/freemarkn/mserv">[Back to Top] </a>

		<%-- contents end --%>
</div>
	<div id="footerArea">
	   <small>Copyright YYYY FUJITSU LEARNING MEDIA LIMITED</small>
	</div>
</body>
</html>