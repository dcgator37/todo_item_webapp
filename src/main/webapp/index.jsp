<%--@elvariable id="items" type=""--%>
<%--
  Created by IntelliJ IDEA.
  User: dcgat
  Date: 3/1/2026
  Time: 12:56 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.TodoItem" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%--<%@ taglib uri="jakarta.tags.core" prefix="c" %>--%>



<html>
<head>
    <title>Todo App</title>
</head>
<body>

<h2>Todo Items</h2>


<p>Items attribute is: ${items}</p>
<p>Items size: ${items.size()}</p>
<c:if test="${empty items}">
    <p><i>No items found</i></p>
</c:if>

<ul>
    <c:forEach var="item" items="${items}">
        <li>
                ${item.text}
            <form method="post" action="${pageContext.request.contextPath}/todos" style="display:inline">
                <input type="hidden" name="action" value="delete"/>
                <input type="hidden" name="id" value="${item.id}"/>
                <button type="submit">Delete</button>
            </form>
        </li>
    </c:forEach>
</ul>

<%--<h2>Todo List</h2>

<ul>
    <%
        Object obj = request.getAttribute("items");

        if (obj != null) {
            List<?> items = (List<?>) obj;
            for (Object o : items) {
                TodoItem item = (TodoItem) o;
    %>
    <li>
        <%= item.getText() %>
        <form method="post" style="display:inline">
            <input type="hidden" name="action" value="delete"/>
            <input type="hidden" name="id" value="<%= item.getId() %>"/>
            <button type="submit">Delete</button>
        </form>
    </li>
    <%
        }
    } else {
    %>
    <li>No items found</li>
    <%
        }
    %>
</ul>--%>

<h3>Add Item</h3>
<form action="${pageContext.request.contextPath}/todos" method="post">
<input type="hidden" name="action" value="add"/>
    <label>
        <input type="text" name="text"/>
    </label>
    <button type="submit">Add</button>
</form>

</body>
</html>
