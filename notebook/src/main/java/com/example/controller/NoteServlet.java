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
import java.io.IOException;
import java.util.Date;
import java.util.List;

//@WebServlet("/note")
public class NoteServlet extends HttpServlet {
    private NoteDao noteDao = new NoteDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求和响应的字符编码为 UTF-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        if ("add".equals(action)) {
            String title = request.getParameter("title");
            String notebook = request.getParameter("notebook");

            if (title == null || notebook == null) {
                response.sendRedirect("error.jsp");
                return;
            }

            Date date = new Date();
            Note note = new Note(0, date, title, notebook, user.getId());
            noteDao.addNote(note);
        } else if ("delete".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                noteDao.deleteNoteById(id);
            } catch (NumberFormatException e) {
                response.sendRedirect("error.jsp");
                return;
            }
        } else if ("update".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                String title = request.getParameter("title");
                String notebook = request.getParameter("notebook");

                if (title == null || notebook == null) {
                    response.sendRedirect("error.jsp");
                    return;
                }

                Date date = new Date();
                Note note = new Note(id, date, title, notebook, user.getId());
                noteDao.updateNoteById(note);
            } catch (NumberFormatException e) {
                response.sendRedirect("error.jsp");
                return;
            }
        }else if ("search".equals(action)) {
            String keyword = request.getParameter("keyword");
            List<Note> notes = noteDao.searchNotesByTitle(keyword, user.getId());
            request.setAttribute("notes", notes);
            request.getRequestDispatcher("search.jsp").forward(request, response);
            return;
        }

        response.sendRedirect("notes.jsp");
    }
}
