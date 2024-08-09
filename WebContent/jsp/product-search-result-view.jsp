<!DOCTYPE html>
<!-- All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited -->
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
<meta charset="UTF-8">
<title>Product Search Result</title>
<link rel="stylesheet" type="text/css" href="/freemarkn/css/style.css">
</head>
<body>
<jsp:include page="/jsp/header-cart.jsp" />

<div id="mainArea">
<h1>PRODUCT SEARCH RESULT</h1>
<%-- contents start
<%--nilesh update--%>

	<h4>The number of products for <c:out value="${categoryName}" /> Category is <c:out value="${numberofproducts}" />.<br></h4>
<%--nilesh update end c out--%>
<div align = "center">
<c:if test="${! empty errorMessageList}">
    <c:out value="${errorMessageList}" />
    </c:if>
<c:if test="${! empty message}">
    <c:out value="${message}" />
    </c:if>
</div><br>
		<c:if test="${empty productList}">
		  <c:out value="${message}" />
		</c:if>
		<c:if test="${!empty productList}">
		<form method="post">
		<table border="1" style="width:100%">
		  <tr>
		    <th>Product ID</th>
		    <th>Product Name</th>
		    <th>Price</th>
		    <th>Inventory</th>
		    <th>Details</th>
		    <th>Add to Cart</th>
		  </tr>
		   <c:forEach var="product" items="${productList}" varStatus="status">
				    <tr>
				        <td><c:out value="${product.productId}" /></td>
				        <td><c:out value="${product.productName}" /></td>
				        <td><c:out value="${product.price}" /></td>
						<td><c:out value="${product.stock.quantity}" /></td>
						 <td><input type="submit" id="button" formaction="/freemarkn/mserv?flag=detail&productId=${product.productId}&categoryName=${categoryName}&categoryId=${categoryId}" value="Details" ></td>
		   				 <td><input type="submit" id="button"  formaction="/freemarkn/mserv?flag=B0101ProductAddForSearch&productId=${product.productId}&categoryName=${categoryName}&numberofproducts=${numberofproducts}&categoryId=${categoryId}" value="Add to Cart"></td>
				    </tr>
			    </c:forEach>
		</table>
		</form>
		</c:if>


<br>
<br>
<!-- nilesh -->
<a href="/freemarkn/mserv">[Back to Top]</a>
<!-- nilesh -->
<%-- contents end --%>
</div>
<jsp:include page="/jsp/footer.jsp" />
</body>
</html>