import java.util.ArrayList;
import java.util.Scanner;
import java.sql.*;

public class Tea extends Product {
    private String type;
    public Tea(String name, double price, String type) { super(name, price); this.type = type; }
    @Override
    public double calculateTotal() { return getPrice(); }
    @Override
    public String toString() { return String.format("%s [%s]: %.2f €", getName(), type, calculateTotal()); }

    public static void showMenu(Scanner input, ArrayList<Product> currentOrder, OrderMenu mainApp) {
        ArrayList<Tea> teas = new ArrayList<>();

        // Τσάι = category_id 4
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT name, price FROM products WHERE category_id = 4");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                String flavor = name.replace("Τσάι ", "").toLowerCase(); // classic, green κλπ.
                teas.add(new Tea(name, rs.getDouble("price"), flavor));
            }
        } catch (SQLException e) {
            System.out.println("Σφάλμα βάσης: " + e.getMessage());
            return;
        }

        System.out.println("\n--- ΚΑΤΑΛΟΓΟΣ ТΣΑΓΙΟΥ ---");
        for (int i = 0; i < teas.size(); i++) {
            System.out.println((i + 1) + ". " + teas.get(i).getName() + " (" + teas.get(i).getPrice() + "€)");
        }

        int choice = mainApp.getValidInput(input, "Επιλέξτε τσάι (ή 0 για επιστροφή): ", 0, teas.size());
        if (choice > 0) {
            currentOrder.add(teas.get(choice - 1));
            System.out.println("Το τσάι προστέθηκε στην παραγγελία!");
        }
    }
}
