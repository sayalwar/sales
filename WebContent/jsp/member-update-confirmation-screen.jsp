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
		<h1>MEMBER UPDATE CONFIRMATION SCREEN</h1>

<br>
		<table border="1">
		    <tr>
		        <th>Name </th>
		        <td><c:out value="${updateMember.memberName}" /></td>
		    </tr>
		    <tr>
		        <th>Address</th>
		        <td><c:out value="${updateMember.address}" /> </td>
		    </tr>
		    <tr>
		        <th>Telephone Number</th>
		        <td><c:out value="${updateMember.phone}" /></td>
		    </tr>
		  	</table>
<br>
<form method="post"action="/freemarkn/mserv" id="updateConfirm" name="chkForm" >
<input type="hidden" name="tempData" value="${memeberData}" readonly="readonly"></input>
<input type="hidden" name="flag" value="B0203G03">
<input type="Submit" value="confirm" >
</form>
<br>
		<a href="/freemarkn/mserv?flag=B0203G02back">[Back]</a>&nbsp;&nbsp;
		<a href="/freemarkn/mserv">[Back to Top]</a>
		<%-- contents end --%>
	</div>
<jsp:include page="/jsp/footer.jsp" />
</body>
</html>