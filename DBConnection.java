import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/coffee_shop_db";

    private static final String USER = "root";

    private static final String PASSWORD = "root";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Δεν βρέθηκε ο MySQL Driver: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Σφάλμα σύνδεσης με τη βάση δεδομένων: " + e.getMessage());
        }
        return connection;
    }
}

