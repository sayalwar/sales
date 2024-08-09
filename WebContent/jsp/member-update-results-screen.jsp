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
		<h1>MEMBER UPDATED RESULT SCREEN</h1>

<h3>Member Data succesfully Updated</h3>
		<table border="1">
		    <tr>
		        <th>Name</th>
		        <td><c:out value="${CommonLoginMember.memberName}" /></td>
		    </tr>
		    <tr>
		        <th>Gender</th>
		        <td><c:out value="${CommonLoginMember.gender}" /></td>
		    </tr>
		    <tr>
		        <th>Address</th>
		        <td><c:out value="${CommonLoginMember.address}" /></td>
		    </tr>

		    <tr>
		        <th>Telephone Number</th>
		        <td><c:out value="${CommonLoginMember.phone}" />
		    		    <tr>
		        <th>Email Address</th>
		        <td><c:out value="${CommonLoginMember.memberId}" /></td>
		    </tr>
		</table>
		<br>

		<a href="/freemarkn/mserv">[Back to Top]</a>

		<%-- contents end --%>
	</div>
<jsp:include page="/jsp/footer.jsp" />
</body>
</html>