import java.util.ArrayList;
import java.util.Scanner;

public class Snack extends Product {
    public Snack(String name, double price) {
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
        ArrayList<Snack> snacks = new ArrayList<>();
        snacks.add(new Snack("Μίνι Sandwich Γαλοπούλα Ολικής", 2.2));
        snacks.add(new Snack("Μίνι Sandwich Ζαμπόν", 2.2));
        snacks.add(new Snack("Sandwich Γαλοπούλα Ολικής", 3.2));
        snacks.add(new Snack("Sandwich Ζαμπόν Λευκη", 3.2));
        snacks.add(new Snack("Σαλάτα του Καίσαρα", 4.5));

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
