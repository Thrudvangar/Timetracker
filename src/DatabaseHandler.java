import java.sql.*;

public class DatabaseHandler {
    private Connection conn;

    public DatabaseHandler() {
        connect();
        createTables();
    }


    // Connect to SQLite-database
    private void connect() {
        try {
            String url = "jdbc:sqlite:timetracker.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Database connection established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    // Create database tables if inexistent
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
                "theme TEXT" +
                ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createThemeTable);
            stmt.execute(createTimeTrackingTable);
            System.out.println("Programm successfully initialized.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    // Add theme to theme database
    public void addTheme(String description) {
        String sql = "INSERT OR IGNORE INTO theme (description) VALUES (?)";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, description);
            preparedStatement.executeUpdate();
            System.out.println("Theme: " + description + " added successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    // Add entry
    public void addTimeEntry(String date, String startTime, String endTime, String project, double workDuration, String theme) {
        String sql = "INSERT INTO time_tracking (date, start_time, end_time, project, work_duration, theme) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, date);
            pstmt.setString(2, startTime);
            pstmt.setString(3, endTime);
            pstmt.setString(4, project);
            pstmt.setDouble(5, workDuration);
            pstmt.setString(6, theme);
            pstmt.executeUpdate();
            System.out.println("Time entry added successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    //Update entry
    public void updateTimeEntry(int id, String date, String startTime, String endTime, String project, double workDuration, String theme) {
        String sql = "UPDATE time_tracking SET date = ?, start_time = ?, end_time = ?, project = ?, work_duration = ?, theme = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, date);
            pstmt.setString(2, startTime);
            pstmt.setString(3, endTime);
            pstmt.setString(4, project);
            pstmt.setDouble(5, workDuration);
            pstmt.setString(6, theme);
            pstmt.setInt(7, id);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Time entry with ID " + id + " updated successfully.");
            } else {
                System.out.println("No entry found with ID " + id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    // Delete entry
    public void removeTimeEntry(int id) {
        String sql = "DELETE FROM time_tracking WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Time entry with ID " + id + " removed successfully.");
            } else {
                System.out.println("No entry found with ID " + id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    // Show all database entries
    public void showAllEntries() {
        String sql = "SELECT * FROM time_tracking";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                ", Date: " + rs.getString("date") +
                                ", Start Time: " + rs.getString("start_time") +
                                ", End Time: " + rs.getString("end_time") +
                                ", Project: " + rs.getString("project") +
                                ", Work Duration: " + rs.getDouble("work_duration") +
                                ", Theme: " + rs.getString("theme")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Close database connection
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