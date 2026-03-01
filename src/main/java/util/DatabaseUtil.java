package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseUtil {
    private static final String URL =
            "jdbc:mysql://localhost:3306/todo_item_app?useSSL=false&serverTimezone=UTC";
    private static final String USER = "todo_user";
    private static final String PASSWORD = "todo_password";

    static {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS todo (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    text VARCHAR(255) NOT NULL
                )
            """);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
