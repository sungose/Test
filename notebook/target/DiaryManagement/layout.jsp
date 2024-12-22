<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.example.controller.PageControllerServlet" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<div class="navbar">
    <a href="notesContent.jsp">Notes</a>
    <a href="searchContent.jsp">Search</a>
    <a href="logout.jsp">Logout</a>
</div>
<%--<div class="content">--%>
<%--    <jsp:include page="${contentPage}" />--%>
<%--</div>--%>
</body>
</html>
