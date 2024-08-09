<!DOCTYPE html>
<!-- All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited -->
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>

<meta charset="UTF-8">
<title>オンラインショップ</title>
<link rel="stylesheet" type="text/css" href="/freemarkn/css/style.css">
<script type="text/javascript" src="/freemarkn/js/memberUpdateCheck.js"></script>

<style>
/* Chrome, Safari, Edge, Opera */
input::-webkit-outer-spin-button,
input::-webkit-inner-spin-button {
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
		<h1>MEMBER UPDATE SCREEN</h1>
		<%-- contents start --%>
		<div id="target" style="color: red;">
			<c:forEach var="error" items="${errorMessageList}" varStatus="status">
				<p>
					<c:out value="${error}" />
				</p>
			</c:forEach>
			<c:out value="${errorMessage}" />
		</div>
		<h2>Member Infromation</h2>
			<form method="post" action="/freemarkn/mserv" id="B0203G03F" name="memberupdateform">
		<table >
	<tr><td>Name</td><td><input type="text" onkeydown="return allLetter(this);" name="memberName" id="memberName" size="20" value="${CommonLoginMember.memberName}" maxlength="50"></td></tr>
	<tr><td>Address</td><td><input type="text" name="memberAddress" id="memberAddress" size="20" value="${CommonLoginMember.address}" maxlength="80"></td></tr>
 	<tr><td>Telephone Number</td><td><input type="text" onkeydown="return onlyNumbers(this);" pattern="[0-9]{2}-[0-9]{4}-[0-9]{4}" title="Use Format like 12-3456-7890" name="phone" maxlength="12"  id="phone" value="${CommonLoginMember.phone}" ></td></tr>
	<tr><td>Password</td><td><input type="password" name="memberPassword" id="memberPassword" size="20"></td></tr>
	<tr><td>Password Check
	</td><td><input type="password"  name="memberPasswordCheck" id="memberPasswordCheck" size="20"></td></tr>
<tr>
<td colspan="2">
<input type="hidden" name="flag" value="B0203G02update">
<input type="Submit" value="Update" > </td>

</tr>
</table>
</form>
		<br>
		<a href="/freemarkn/mserv?flag=B0203G01back">[Back]</a>&nbsp;&nbsp;
		<a href="/freemarkn/mserv">[Back to Top]</a>

		<%-- contents end --%>
	</div>
<jsp:include page="/jsp/footer.jsp" />
</body>
</html>