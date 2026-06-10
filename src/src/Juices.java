import java.util.ArrayList;
import java.util.Scanner;

public class Juices extends Product {
    public Juices(String name, double price) {
        super(name, price);
    }

    @Override
    public double calculateTotal() {
        return getPrice();
    }

    @Override
    public String toString() {
        return String.format("%s : %.2f €", getName(), calculateTotal());
    }

    public static void showMenu(Scanner input, ArrayList<Product> currentOrder, OrderMenu mainApp) {
        ArrayList<Juices> juices = new ArrayList<>();
        juices.add(new Juices("Φυσικός Χυμός Πορτοκάλι", 2.8));
        juices.add(new Juices("Φυσικός Χυμός Ανάμεικτος", 3.2));

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

