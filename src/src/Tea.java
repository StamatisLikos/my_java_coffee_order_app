import java.util.ArrayList;
import java.util.Scanner;

public class Tea extends Product {
    private String type;

    public Tea(String name, double price) {
        super(name, price);
    }

    @Override
    public double calculateTotal() {
        return getPrice();
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f €", getName(), calculateTotal());
    }

    public static void showMenu(Scanner input, ArrayList<Product> currentOrder, OrderMenu mainApp) {
        ArrayList<Tea> teas = new ArrayList<>();
        teas.add(new Tea("Τσάι Classic", 2.0));
        teas.add(new Tea("Τσάι Green", 2.2));
        teas.add(new Tea("Τσάι Vanilla Flavor", 2.4));
        teas.add(new Tea("Τσάι Matcha", 3.0));

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
