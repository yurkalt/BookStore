<%--
  Created by IntelliJ IDEA.
  User: Iurii
  Date: 17.11.2016
  Time: 15:14
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="include.jsp"%>

<html>
<head>
    <title>Order</title>
</head>
<body>
    <table>
            <c:forEach items="${orderBooks}" var="book">
                <tr>
                <td>Title: <c:out value="${book.title}"/></td>
                <td>Author: <c:out value="${book.author}"/></td>
                <td>Price: <c:out value="${book.price}"/></td>
                    </tr>
            </c:forEach>
    </table>

    <form id="buy" action="order" method="post">
        <button type="submit" name="button" value="button1">PAY</button>
    </form>
    </body>
</html>
