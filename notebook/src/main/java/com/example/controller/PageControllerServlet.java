package com.example.controller;

import com.example.dao.NoteDao;
import com.example.model.Note;
import com.example.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

//@WebServlet("/page")
public class PageControllerServlet extends HttpServlet {
    private NoteDao noteDao = new NoteDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String page = request.getParameter("page");
        if ("notes".equals(page)) {
            request.setAttribute("title", "My Notes");
            request.setAttribute("contentPage", "notesContent.jsp");
            List<Note> notes = noteDao.getNotesByUserId(user.getId());
            request.setAttribute("notes", notes);
        } else if ("search".equals(page)) {
            String keyword = request.getParameter("keyword");
            request.setAttribute("title", "Search Notes");
            request.setAttribute("contentPage", "searchContent.jsp");
            if (keyword != null && !keyword.trim().isEmpty()) {
                List<Note> notes = noteDao.searchNotesByTitle(keyword, user.getId());
                request.setAttribute("notes", notes);
            }
        }

        request.getRequestDispatcher("layout.jsp").forward(request, response);
    }
}
