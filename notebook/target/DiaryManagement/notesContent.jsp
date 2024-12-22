<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Note" %>
<%@ page import="com.example.dao.NoteDao" %>
<%@ page import="com.example.model.User" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="java.util.Date" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    NoteDao noteDao = new NoteDao();
    List<Note> notes = noteDao.getNotesByUserId(user.getId());
%>

<!DOCTYPE html>
<html>
<head>
    <title>My Notes</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body class="notes-page">
<h1>我的日记</h1>
<form action="note" method="post">
    <input type="hidden" name="action" value="add">
    <label for="title">标题:</label>
    <input type="text" id="title" name="title" required><br>
    <label for="notebook">内容:</label>
    <textarea id="notebook" name="notebook" required></textarea><br>
    <button type="submit">Add Note</button>
</form>
<h2>日记记录</h2>
<table>
    <tr>
        <th>日期</th>
        <th>标题</th>
        <th>内容</th>
        <th>操作</th>
    </tr>
    <%
        for (Note note : notes) {
    %>
    <tr>
        <td><%= note.getDate() %></td>
        <td><%= note.getTitle() %></td>
        <td><%= note.getNotebook() %></td>
        <td>
            <form action="note" method="post" style="display:inline;">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="<%= note.getId() %>">
                <button type="submit">Delete</button>
            </form>
            <form action="note" method="post" style="display:inline;">
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="<%= note.getId() %>">
                <input type="text" name="title" value="<%= note.getTitle() %>">
                <textarea name="notebook"><%= note.getNotebook() %></textarea>
                <button type="submit">Update</button>
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>
<a href="logout.jsp">返回登录</a>
<a href="layout.jsp">返回上级</a>
</body>
</html>
