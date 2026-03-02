package database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static util.DatabaseUtil.getConnection;

class TodoDatabaseTest {
    TodoDatabase dao;

    @BeforeEach
    void setUp() throws Exception {
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

        dao = new TodoDatabase();
        dao.add("Test Item");
    }

    @AfterEach
    void tearDown() throws Exception {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.execute("""
            DROP TABLE todo
        """);
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddAndRetrieve() throws Exception {
        assertTrue(
                dao.findAll()
                        .stream()
                        .anyMatch(i -> i.getText().equals("Test Item"))
        );
    }

    @Test
    void testAddDeleteItem() throws Exception {

        dao.delete(1);
        assertFalse(
                dao.findAll()
                        .stream()
                        .anyMatch(i -> i.getText().equals("Test Item"))
        );
    }
}