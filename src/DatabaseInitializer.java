import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    //Create database if inexistent
    public static void initializeDatabase(String dbName) {
        String url = "jdbc:sqlite:" + dbName;

        String createThemeTable = "CREATE TABLE IF NOT EXISTS theme (" +
                "id INTEGER PRIMARY KEY," +
                "description TEXT UNIQUE" +
                ");";

        String createTimeTrackingTable = "CREATE TABLE IF NOT EXISTS time_tracking (" +
                "id INTEGER PRIMARY KEY," +
                "date TEXT," +
                "start_time TEXT," +
                "end_time TEXT," +
                "project TEXT," +
                "work_duration REAL," +
                "theme TEXT" +
                ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            stmt.execute(createThemeTable);
            stmt.execute(createTimeTrackingTable);
            System.out.println("Database and tables initialized successfully.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

