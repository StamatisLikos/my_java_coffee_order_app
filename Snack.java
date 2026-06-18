import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class Snack extends Product {
    public Snack(String name, double price) { super(name, price); }
    @Override
    public double calculateTotal() { return getPrice(); }
    @Override
    public String toString() { return String.format("%s: %.2f €", getName(), calculateTotal()); }

    public static void showMenu(Scanner input, ArrayList<Product> currentOrder, OrderMenu mainApp) {
        ArrayList<Snack> snacks = new ArrayList<>();

        // Σνακ = category_id 2
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT name, price FROM products WHERE category_id = 2");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                snacks.add(new Snack(rs.getString("name"), rs.getDouble("price")));
            }
        } catch (SQLException e) {
            System.out.println("Σφάλμα βάσης: " + e.getMessage());
            return;
        }

        System.out.println("\n--- ΚΑΤΑΛΟΓΟΣ ΣΝΑΚ (ΚΡΥΑ) ---");
        for (int i = 0; i < snacks.size(); i++) {
            System.out.println((i + 1) + ". " + snacks.get(i).getName() + " (" + snacks.get(i).getPrice() + "€)");
        }

        int choice = mainApp.getValidInput(input, "Επιλέξτε σνακ (ή 0 για επιστροφή): ", 0, snacks.size());
        if (choice > 0) {
            currentOrder.add(snacks.get(choice - 1));
            System.out.println("Το σνακ προστέθηκε στην παραγγελία!");
        }
    }
}
