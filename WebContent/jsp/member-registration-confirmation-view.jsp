<!DOCTYPE html>
<!-- All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited -->
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>オンラインショップ</title>
<link rel="stylesheet" type="text/css" href="/freemarkn/css/style.css">
<!--  Nilesh No need of the javascript logininput check -->
<!--
<script type="text/javascript" src="/freemarkn/js/loginInputCheck.js"></script>
 -->
<!--  Nilesh No need of the javascript logininput check -->
<style type="text/css">
    select {
        width:100px;
    }
    input.ex3{
  margin-left: 45px;
}
</style>
</head>
<body>

	<jsp:include page="/jsp/header-non-menu.jsp" />

	<div id="mainArea">
	<h1>MEMBER REGISTRATION CONFIRMATION SCREEN</h1>
<br>
		<%-- contents start --%>

		<div id="target" style="color: red;">
			<c:forEach var="error" items="${errorMessageList}" varStatus="status">
				<p>
					<c:out value="${error}" />
				</p>
			</c:forEach>
			<c:out value="${errorMessage}" />
		</div>

		<form method="Post" action="/freemarkn/mserv" id="chkForm">
			<table border="1">
				<tr>
					<th><b>Name</b></th>
					<td><c:out value="${memberregistration.memberName}" /></td>
				</tr>
				<tr>
					<th><b>Gender</b></th>
					<td><c:out value="${memberregistration.gender}" /></td>
				</tr>
				<tr>
					<th><b>Address</b></th>
					<td><c:out value="${memberregistration.address}" /></td>
				</tr>
				<tr>
					<th><b>TelePhone Number</b></th>
					<td><c:out value="${memberregistration.phone}" /></td>
				</tr>
				<tr>
					<th><b>Email</b></th>
					<td><c:out value="${memberregistration.memberId}" /></td>
				</tr>
			</table>
			<br> <input type="hidden" name="flag" value="B0201MemberRegistrationConfirmation">
			<input type="submit" value="Confirm">
		</form>

		<p>
			<a href="/freemarkn/mserv?flag=backB0201G02 ">[Back]</a>
			<a href="/freemarkn/mserv">[Back to Top]</a>
		</p>
		<%-- contents end --%>
	</div>
<jsp:include page="/jsp/footer.jsp" />
</body>
</html>