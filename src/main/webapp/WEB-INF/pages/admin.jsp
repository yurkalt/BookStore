<%--
  Created by IntelliJ IDEA.
  User: Iurii
  Date: 18.11.2016
  Time: 11:05
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="include.jsp"%>
<html>
<head>
    <title>AdminPage</title>
</head>
<body>

<table>
    <c:forEach items="${orders}" var="order">
        <tr>
        <td>ID:<c:out value="${order.id}"/></td>
        <td>Status: <c:out value="${order.status}"/></td>
        <td>Total price: <c:out value="${order.totalPrice}"/></td>
        <td>
            <form id="buy" action="admin" method="post">
                <input type="hidden" name="orderID" value="${order.id}" />
                <button type="submit" name="button" value="button1">CANCEL ORDER</button>
            </form>
        </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
