<!DOCTYPE html>
<!-- All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited -->
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>オンラインショップ</title>
<link rel="stylesheet" type="text/css" href="/freemarkn/css/style.css">
<script type="text/javascript" src="/freemarkn/js/memberInputCheck.js"></script>

</head>
<body>
	<jsp:include page="/jsp/header-non-menu.jsp" />
	<div id="mainArea">
		<h1>MEMBER REGISTRATION SCREEN</h1>
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
					<td ><b>Name</b></td>
					<td ><input type="text"  name="membername" id="membername" size="30" value="${memberregistration.memberName}"></td>
				</tr>
				<tr>
					<td ><b>Gender</b></td>
					<td >
					<input type="radio" name="gender" id="female"  value="Female"  class ="genderF"  ${memberregistration.gender ==  "Female" ? "checked" : ""}>
						<label for="gender">Female</label>
						<input type="radio" name="gender" id="male" value="Male"  class ="genderM"  ${memberregistration.gender ==  "Male" ? "checked" : ""}>
						<label for="gender" >Male</label>
						<input type="radio" name="gender" id="notspecified" value="Notspecified"  class ="notspecified"  ${memberregistration.gender ==  "Notspecified" ? "checked" : ""}>
						<label for="gender" >Notspecified</label></td>
				</tr>
				<tr>
					<td ><b>Address</b></td>
					<td><input type="text" name="address" id="address"  size="30"  value="${memberregistration.address}"></td>
				</tr>
				<tr>
					<td ><b>Telephone Number</b></td>
				<!--  Nilesh<td><input type="text" name="phone" id="phone" ></td> -->
					<td><input type="text" onkeydown="return onlyNumbers(this);" pattern="[0-9]{2}-[0-9]{4}-[0-9]{4}"
					title="Use Format like 12-3456-7890" name="phone" maxlength="12"  id="phone"  size="30"  value="${memberregistration.phone}"></td>
				</tr>
				<tr>
					<td ><b>Password</b></td>
					<td><input type="password" name="password" id="password" size="30"></td>
				</tr>
								<tr>
					<td ><b>Password check</b></td>
					<td><input type="password" name="passwordcheck" id="password_confirm" size="30"></td>
				</tr>
			</table>
			<br> <input type="hidden" name="flag" value="B0201MemberRegistration">
			<input type="submit" value="Submit">
		</form>
		<p>
			<a href="/freemarkn/mserv?flag=B0201G01Emailentry">[Back]</a>
			<a href="/freemarkn/mserv">[Back to Top]</a>
		</p>
		<%-- contents end --%>
	</div>
<jsp:include page="/jsp/footer.jsp" />
</body>
</html>