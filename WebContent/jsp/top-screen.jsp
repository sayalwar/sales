<!DOCTYPE html>
<!-- All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited -->
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Top Screen</title>
<link rel="stylesheet" type="text/css" href="/freemarkn/css/style.css">
</head>
<body>

<c:if test="${empty CommonLoginMember}">
<jsp:include page="/jsp/header-login-cart.jsp"/>
</c:if>
<c:if test="${! empty CommonLoginMember}">
<jsp:include page="/jsp/header-logout-cart.jsp"/>
</c:if>


	<div id="mainArea">
		<h1>TOP SCREEN</h1>
		<%-- contents start --%>
		<form method="post">
			<table>
				<c:set var="secCount" value="1" scope="page" />
				<tr>
					<c:forEach var="list" items="${categoryList}">
						<td style="height: 150px; width: 400px">
							<div class="row">
								<!-- nilesh category name update -->
								<a
									href="/freemarkn/mserv?flag=B0101ProductSearch&categoryId=${list.categoryId}&categoryName=${list.categoryName}"><img
									src="img/${list.picture}" width="150"></a><br>
								<!-- nilesh category name update end -->
								<c:out value="${list.categoryName}" />
								<c:if test="${(secCount /3)==1}">
									<tr></tr>
								</c:if>
								<c:set var="secCount" value="${secCount + 1}" scope="page" />
							</div>
						</td>
					</c:forEach>
				</tr>
			</table>
		</form>
		<a href="/freemarkn/mserv?flag=B0201G01Emailentry">[New Member
			Registration]</a>
		<%-- contents end --%>
	</div>
	<jsp:include page="/jsp/footer.jsp" />
</body>
</html>