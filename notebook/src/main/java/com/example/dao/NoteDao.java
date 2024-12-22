package com.example.dao;

import com.example.model.Note;
import com.example.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.utils.DBUtil.getConnection;

public class NoteDao {
    public boolean addNote(Note note) {
        String sql = "INSERT INTO note (date, title, notebook, userID) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new Date(note.getDate().getTime()));
            statement.setString(2, note.getTitle());
            statement.setString(3, note.getNotebook());
            statement.setInt(4, note.getUserID());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Note> getNotesByUserId(int userId) {
        List<Note> notes = new ArrayList<>();
        String sql = "SELECT * FROM note WHERE userID = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                notes.add(new Note(
                        resultSet.getInt("ID"),
                        resultSet.getDate("date"),
                        resultSet.getString("title"),
                        resultSet.getString("notebook"),
                        resultSet.getInt("userID")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }

    public boolean deleteNoteById(int noteId) {
        String sql = "DELETE FROM note WHERE ID = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, noteId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateNoteById(Note note) {
        String sql = "UPDATE note SET date = ?, title = ?, notebook = ? WHERE ID = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, new Date(note.getDate().getTime()));
            statement.setString(2, note.getTitle());
            statement.setString(3, note.getNotebook());
            statement.setInt(4, note.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Note> searchNotesByTitle(String keyword, int userId) {
        List<Note> notes = new ArrayList<>();
        String sql = "SELECT * FROM note WHERE title LIKE ? AND userID = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            stmt.setInt(2, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Note note = new Note(
                        rs.getInt("id"),
                        rs.getDate("date"),
                        rs.getString("title"),
                        rs.getString("notebook"),
                        rs.getInt("userID")
                );
                notes.add(note);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }
}
