import java.util.ArrayList;
import java.util.Scanner;

public class Beverages extends Product {
    private String size;

    public Beverages(String name, double price, String size) {
        super(name, price);
        this.size = size;
    }

    @Override
    public double calculateTotal() {
        return getPrice();
    }

    @Override
    public String toString() {
        return String.format("%s (%s): %.2f €", getName(), size, calculateTotal());
    }

    public static void showMenu(Scanner input, ArrayList<Product> currentOrder, OrderMenu mainApp) {
        ArrayList<Beverages> beverages = new ArrayList<>();
        beverages.add(new Beverages("Coca Cola 330ml", 1.8, "330ml"));
        beverages.add(new Beverages("Fanta 330ml", 1.8, "330ml"));
        beverages.add(new Beverages("Sprite 330ml", 1.8, "330ml"));
        beverages.add(new Beverages("Εμφιαλωμένο Νερό 500ml", 0.5, "500ml"));

        System.out.println("\n--- ΚΑΤΑΛΟΓΟΣ ΑΝΑΨΥΚΤΙΚΩΝ & ΝΕΡΟΥ ---");
        for (int i = 0; i < beverages.size(); i++) {
            System.out.println((i + 1) + ". " + beverages.get(i).getName() + " (" + beverages.get(i).getPrice() + "€)");
        }

        int choice = mainApp.getValidInput(input, "Επιλέξτε αναψυκτικό/νερό (ή 0 για επιστροφή): ", 0, beverages.size());
        if (choice > 0) {
            currentOrder.add(beverages.get(choice - 1));
            System.out.println("Το αναψυκτικό/νερό προστέθηκε στην παραγγελία!");
        }
    }
}
