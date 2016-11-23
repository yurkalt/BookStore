<%@ include file="include.jsp"%>


<%--
  Created by IntelliJ IDEA.
  User: Iurii
  Date: 09.11.2016
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>BookShop</title>
</head>
<body>
<table>
<c:forEach items="${books}" var="book">

        <tr>
            <td>Title: <c:out value="${book.title}"/></td>
            <td>Author: <c:out value="${book.author}"/></td>
            <td>Count: <c:out value="${book.count}"/></td>
            <td>
            <form id="buy" action="shop" method="post">
                <input type="hidden" name="bookID" value="${book.id}" />
                <button type="submit" name="button" value="button1">Add to card</button>
            </form>
            </td>
        </tr>
</c:forEach>
</table>

<p>
    <a href="order">
        <input type="button" value="PROCEED TO CHECKOUT" name="order"/></a>
</p>


</body>
</html>
