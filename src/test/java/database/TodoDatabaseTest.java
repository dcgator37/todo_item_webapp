package database;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static util.DatabaseUtil.getConnection;

class TodoDatabaseTest {

    @Test
    void sanityTest() {
        assertTrue(true, "JUnit is working");
    }

    @Test
    void testAddAndRetrieve() throws Exception {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("""
            CREATE TABLE  todo (
                id INT AUTO_INCREMENT PRIMARY KEY,
                text VARCHAR(255) NOT NULL
            )
        """);
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TodoDatabase dao = new TodoDatabase();
        dao.add("Test Item");

        assertTrue(
                dao.findAll()
                        .stream()
                        .anyMatch(i -> i.getText().equals("Test Item"))
        );

        //dao.delete(1);
    }

    @Test
    void testAddDeleteItem() throws Exception {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("""
            CREATE TABLE  todo (
                id INT AUTO_INCREMENT PRIMARY KEY,
                text VARCHAR(255) NOT NULL
            )
        """);
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        TodoDatabase dao = new TodoDatabase();
        dao.add("2nd Test Item");
        dao.delete(1);
        assertFalse(
                dao.findAll()
                        .stream()
                        .anyMatch(i -> i.getText().equals("2nd Test Item"))
        );
    }
}