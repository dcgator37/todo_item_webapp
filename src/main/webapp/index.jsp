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

<html>
<head>
    <title>Todo App</title>
</head>
<body>

<h2>Todo List</h2>

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
</ul>

<h3>Add Item</h3>
<form action="/" method="post">
<input type="hidden" name="action" value="add"/>
    <label>
        <input type="text" name="text"/>
    </label>
    <button type="submit">Add</button>
</form>

</body>
</html>
