package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseUtil {
    private static final String DB_HOST =
            System.getenv().getOrDefault("DB_HOST", "localhost");
    private static final String DB_NAME =
            System.getenv().getOrDefault("DB_NAME", "todo_item_app");
    private static final String DB_USER =
            System.getenv().getOrDefault("DB_USER", "todo_user");
    private static final String DB_PASSWORD =
            System.getenv().getOrDefault("DB_PASSWORD", "todo_password");

    private static final String URL =
            "jdbc:mysql://" + DB_HOST + ":3306/" + DB_NAME +
                    "?useSSL=false&serverTimezone=UTC";

    //static {
//        try (Connection conn = getConnection();
//             Statement stmt = conn.createStatement()) {
//
//            stmt.execute("""
//                CREATE TABLE IF NOT EXISTS todo (
//                    id INT AUTO_INCREMENT PRIMARY KEY,
//                    text VARCHAR(255) NOT NULL
//                )
//            """);
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
    //}

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
    }

    public static void initializeDatabase() {
        System.out.println("=== Initializing database ===");
        System.out.println("DB_HOST=" + DB_HOST);
        System.out.println("DB_NAME=" + DB_NAME);
        System.out.println("DB_USER=" + DB_USER);
        System.out.println("JDBC URL=" + URL);


        try {
            System.out.println("Attempting DB connection...");
            Connection conn = getConnection();
            System.out.println("Connection successful!");

            Statement stmt = conn.createStatement();
            System.out.println("Statement created");

            stmt.execute("""
            CREATE TABLE IF NOT EXISTS todo (
                id INT AUTO_INCREMENT PRIMARY KEY,
                text VARCHAR(255) NOT NULL
            )
        """);

            System.out.println("=== Table creation SQL executed ===");

            stmt.close();
            conn.close();

            System.out.println("=== Database initialized successfully ===");

        } catch (Exception e) {
            System.out.println("!!! DATABASE INITIALIZATION FAILED !!!");
            e.printStackTrace();
        }
    }
}
