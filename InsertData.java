import java.sql.*;

public class InsertData {

    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "your_password";

        try (Connection con = DriverManager.getConnection(
                    url, username, password)) {

            String createTable = "CREATE TABLE IF NOT EXISTS users (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100), " +
                    "email VARCHAR(100))";

            try (PreparedStatement ps = con.prepareStatement(createTable)) {
                ps.executeUpdate();
            }

            String query = "INSERT INTO users (name, email) VALUES (?, ?)";

            try (PreparedStatement ps = con.prepareStatement(query)) {

                ps.setString(1, "John Doe");
                ps.setString(2, "john@example.com");
                ps.executeUpdate();

                ps.setString(1, "Jane Doe");
                ps.setString(2, "jane@example.com");
                ps.executeUpdate();
            }

            System.out.println("Data inserted successfully.");

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }
}
