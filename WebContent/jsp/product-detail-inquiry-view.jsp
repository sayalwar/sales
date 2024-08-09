<!DOCTYPE html>
<!-- All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited -->
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
<meta charset="UTF-8">
<title>Product Detail</title>
<link rel="stylesheet" type="text/css" href="/freemarkn/css/style.css">
</head>
<body>
<jsp:include page="/jsp/header-cart.jsp" />

<div id="mainArea">
<h1>PRODUCT DETAIL INQUIRY</h1><br>
<%-- contents start --%>
<%--nilesh update--%>

<div align = "center">
<c:if test="${! empty errorMessageList}">
    <c:out value="${errorMessageList}" />
    </c:if>
<c:if test="${! empty message}">
    <c:out value="${message}" />
    </c:if>
</div><br>
		<form method="post">
		<table border="1" style="width:100%">
		  <tr>
		    <th>Product Image</th>
		    <th>Product ID</th>
		    <th>Product Category</th>
		    <th>Product Name</th>
		    <th>Price</th>
		    <th>Inventory</th>
		    <th>Add to Cart</th>
		  </tr>
		  <tr>
			<td><img src="img_DB/${product.picture}" width = "120"></td>
		    <td><c:out value="${product.productId}" /></td>
		   <td><c:out value="${categoryName}" /></td>
		    <td><c:out value="${product.productName}" /></td>
		    <td><c:out value="${product.price}" /></td>
		    <td ><c:out value="${product.stock.quantity}"/></td>
		     <td><input type="submit" id="button"  formaction="/freemarkn/mserv?flag=B0101ProductAddForDetail&productId=${product.productId}&categoryName=${categoryName}&numberofproducts=${numberofproducts}&categoryId=${categoryId}" value="Add to Cart"></td>
		  </tr>
		</table>
		</form>

<br>
<br>
		<a href="/freemarkn/mserv?flag=B0101ProductSearch&categoryId=${categoryId}&categoryName=${categoryName}">[Back]</a>&nbsp;&nbsp;
<%-- contents end --%>
</div>
<jsp:include page="/jsp/footer.jsp" />
</body>
</html>