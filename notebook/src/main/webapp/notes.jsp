<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Note" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.example.model.User" %>

<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("notesContent.jsp");
        return;
    }
%>

<jsp:forward page="page?page=notes" />
