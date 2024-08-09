<!DOCTYPE html>
<!-- All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited -->
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>No cart DATA</title>
<link rel="stylesheet" type="text/css" href="/freemarkn/css/style.css">
</head>
<body>
	<jsp:include page="/jsp/header-non-menu.jsp" />
	<div id="mainArea">
		<h1>SHOPPING CART SCREEN</h1>
		<%-- contents start --%>

		<div id="target" style="color: red;">
			<c:forEach var="error" items="${errorMessageList}" varStatus="status">
				<p>
					<c:out value="${error}" />
				</p>
			</c:forEach>
			<c:out value="${errorMessage}" />
		</div>

<br>
		<p>
			<a href="/freemarkn/mserv">[Back to Top]</a>
		</p>
		<%-- contents end --%>
	</div>
<jsp:include page="/jsp/footer.jsp" />

</body>
</html>