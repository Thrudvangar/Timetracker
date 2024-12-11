import java.sql.*;

public class DatabaseHandler {
    private Connection conn;

    public DatabaseHandler() {
        connect();
        createTables();
    }

    private void connect() {
        try {
            String url = "jdbc:sqlite:timetracker.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Database connection established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createTables() {
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
                "year INTEGER," +
                "month INTEGER," +
                "week INTEGER," +
                "theme_id INTEGER," +
                "FOREIGN KEY (theme_id) REFERENCES theme (id)" +
                ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createThemeTable);
            stmt.execute(createTimeTrackingTable);
            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

