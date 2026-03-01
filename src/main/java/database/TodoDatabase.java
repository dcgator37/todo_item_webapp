package database;

import model.TodoItem;
import util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TodoDatabase {
    public List<TodoItem> findAll() throws Exception {
        List<TodoItem> items = new ArrayList<>();

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM todo")) {

            while (rs.next()) {
                items.add(new TodoItem(
                        rs.getInt("id"),
                        rs.getString("text")
                ));
            }
        }
        return items;
    }

    public void add(String text) throws Exception {
        System.out.println("DAO.add() called with text: " + text);
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement("INSERT INTO todo(text) VALUES (?)")) {
            ps.setString(1, text);
            int rows = ps.executeUpdate();  // must be executeUpdate, not executeQuery
            System.out.println("Rows inserted: " + rows);
        } catch (Exception e) {
            System.out.println("!!! DAO.add() FAILED !!!");
            e.printStackTrace();
            throw e;
        }
    }

    public void delete(int id) throws Exception {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps =
                     conn.prepareStatement("DELETE FROM todo WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
