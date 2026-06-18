import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class Juices extends Product {
    public Juices(String name, double price) { super(name, price); }
    @Override
    public double calculateTotal() { return getPrice(); }
    @Override
    public String toString() { return String.format("%s (Φυσικός): %.2f €", getName(), calculateTotal()); }

    public static void showMenu(Scanner input, ArrayList<Product> currentOrder, OrderMenu mainApp) {
        ArrayList<Juices> juices = new ArrayList<>();

        // Χυμοί = category_id 3
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT name, price FROM products WHERE category_id = 3");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                juices.add(new Juices(rs.getString("name"), rs.getDouble("price")));
            }
        } catch (SQLException e) {
            System.out.println("Σφάλμα βάσης: " + e.getMessage());
            return;
        }

        System.out.println("\n--- ΚΑΤΑΛΟΓΟΣ ΦΥΣΙΚΩΝ ΧΥΜΩΝ ---");
        for (int i = 0; i < juices.size(); i++) {
            System.out.println((i + 1) + ". " + juices.get(i).getName() + " (" + juices.get(i).getPrice() + "€)");
        }

        int choice = mainApp.getValidInput(input, "Επιλέξτε χυμό (ή 0 για επιστροφή): ", 0, juices.size());
        if (choice > 0) {
            currentOrder.add(juices.get(choice - 1));
            System.out.println("Ο χυμός προστέθηκε στην παραγγελία!");
        }
    }
}


