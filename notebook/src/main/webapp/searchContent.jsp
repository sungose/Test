<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Note" %>

<%
    List<Note> notes = (List<Note>) request.getAttribute("notes");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Search Notes</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body class="search-page">
<h1>搜索日记</h1><a href="layout.jsp">返回上级</a>
<form action="page" method="get">
    <input type="hidden" name="page" value="search">
    <label for="keyword">关键字:</label>
    <input type="text" id="keyword" name="keyword" required><br>
    <button type="submit">搜索</button>
</form>

<%
    if (notes != null) {
%>
<h2>搜索结果</h2>
<table>
    <tr>
        <th>日期</th>
        <th>标题</th>
        <th>内容</th>
    </tr>
    <%
        for (Note note : notes) {
    %>
    <tr>
        <td><%= note.getDate() %></td>
        <td><%= note.getTitle() %></td>
        <td><%= note.getNotebook() %></td>
    </tr>
    <%
        }
    %>
</table>
<%
    }
%>
</body>
</html>
