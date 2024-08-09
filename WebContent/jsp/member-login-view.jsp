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
</head>
<body>
	<jsp:include page="/jsp/header-non-menu.jsp" />
	<div id="mainArea">
		<h1>LOGIN</h1>
		<%-- contents start --%>
		<div id="target" style="color: red;">
			<c:forEach var="error" items="${errorMessageList}" varStatus="status">
				<p>
					<c:out value="${error}" />
				</p>
			</c:forEach>
			<c:out value="${errorMessage}" />
		</div>
		<form method="post" action="/freemarkn/mserv" id="chkForm">
			<table>
				<tr>
					<td>Member Id</td>
					<td><input type="text" name="memberId" id="memberId" size="10"></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" id="password"
						size="10"></td>
				</tr>
			</table>
			<br> <input type="hidden" name="flag" value="B0202LoginMember">
			<input type="submit" value="Login">
		</form>
		<p>
			<a href="/freemarkn/mserv">[Back to Top]</a>
		</p>
		<%-- contents end --%>
	</div>
<jsp:include page="/jsp/footer.jsp" />
</body>
</html>