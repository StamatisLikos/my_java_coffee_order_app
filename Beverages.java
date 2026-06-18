import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class Beverages extends Product {
    private String size;
    public Beverages(String name, double price, String size) { super(name, price); this.size = size; }
    @Override
    public double calculateTotal() { return getPrice(); }
    @Override
    public String toString() { return String.format("%s (%s): %.2f €", getName(), size, calculateTotal()); }

    public static void showMenu(Scanner input, ArrayList<Product> currentOrder, OrderMenu mainApp) {
        ArrayList<Beverages> beverages = new ArrayList<>();

        // Αναψυκτικά = category_id 5
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT name, price FROM products WHERE category_id = 5");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                String ml = name.contains("330ml") ? "330ml" : "500ml";
                beverages.add(new Beverages(name, rs.getDouble("price"), ml));
            }
        } catch (SQLException e) {
            System.out.println("Σφάλμα βάσης: " + e.getMessage());
            return;
        }

        System.out.println("\n--- ΚΑΤΑΛΟΓΟΣ ΑΝΑΨΥΚΤΙΚΩΝ & ΝΕΡΟΥ ---");
        for (int i = 0; i < beverages.size(); i++) {
            System.out.println((i + 1) + ". " + beverages.get(i).getName() + " (" + beverages.get(i).getPrice() + "€)");
        }

        int choice = mainApp.getValidInput(input, "Επιλέξτε αναψυκτικό/νερό (ή 0 για επιστροφή): ", 0, beverages.size());
        if (choice > 0) {
            currentOrder.add(beverages.get(choice - 1));
            System.out.println("Το αναψυκτικό προστέθηκε στην παραγγελία!");
        }
    }
}
