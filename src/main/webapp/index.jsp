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
        List<TodoItem> items = (List<TodoItem>) request.getAttribute("items");
        for (TodoItem item : items) {
    %>
    <li>
        <%= item.getText() %>
        <form method="post" style="display:inline">
            <input type="hidden" name="action" value="delete"/>
            <input type="hidden" name="id" value="<%= item.getId() %>"/>
            <button type="submit">Delete</button>
        </form>
    </li>
    <% } %>
</ul>

<h3>Add Item</h3>
<form method="post">
    <input type="hidden" name="action" value="add"/>
    <input name="text"/>
    <button type="submit">Add</button>
</form>

</body>
</html>
