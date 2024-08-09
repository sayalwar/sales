<!DOCTYPE html>
<!-- All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited -->
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>オンラインショップ</title>
<link rel="stylesheet" type="text/css" href="/freemarkn/css/style.css">
<script type="text/javascript" src="/freemarkn/js/loginInputCheck.js"></script>
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
	<h1>MEMBER REGISTRATION RESULT SCREEN</h1>
		<%-- contents start --%>
	<h3><c:out value="${SuccessMessage}" /></h3>
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
					<td><c:out value="${memberobj.memberName}" /></td>
				</tr>
				<tr>
					<th><b>Gender</b></th>
					<td><c:out value="${memberobj.gender}" /></td>
				</tr>
				<tr>
					<th><b>Address</b></th>
					<td><c:out value="${memberobj.address}" /></td>
				</tr>
				<tr>
					<th><b>TelePhone Number</b></th>
					<td><c:out value="${memberobj.phone}" /></td>
				</tr>
				<tr>
					<th><b>Email</b></th>
					<td><c:out value="${memberobj.memberId}" /></td>
				</tr>
			</table>
		</form>

		<p>
			<a href="/freemarkn/mserv">[Back to Top]</a>
		</p>
		<%-- contents end --%>
	</div>
<jsp:include page="/jsp/footer.jsp" />
</body>
</html>