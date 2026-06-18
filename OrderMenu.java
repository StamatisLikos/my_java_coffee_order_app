import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class OrderMenu {

    public static void main(String[] args) {
        java.sql.Connection testConn = DBConnection.getConnection();
        if (testConn != null) {
            System.out.println("=== ΕΠΙΤΥΧΙΑ! Η Java συνδέθηκε με τη MySQL! ===");
            try { testConn.close(); } catch (Exception e) {}
        } else {
            System.out.println("=== ΑΠΟΤΥΧΙΑ ΣΥΝΔΕΣΗΣ! Ελέγξτε τον κώδικα. ===");
        }


        Scanner input = new Scanner(System.in);
        ArrayList<Product> currentOrder = new ArrayList<>();
        OrderMenu app = new OrderMenu();

        while (true) {
            try {
                System.out.println("\n===== ΚΥΡΙΟ ΜΕΝΟΥ =====");
                System.out.println("1. Καφέδες");
                System.out.println("2. Σνακ");
                System.out.println("3. Φυσικοί Χυμοί");
                System.out.println("4. Τσάι");
                System.out.println("5. Αναψυκτικά & Νερό");
                System.out.println("0. Ταμείο & Έξοδος");
                System.out.print("Επιλέξτε κατηγορία: ");

                int mainChoice = input.nextInt();
                if (mainChoice == 0) break;

                switch (mainChoice) {
                    case 1 -> Coffee.showMenu(input, currentOrder, app);
                    case 2 -> Snack.showMenu(input, currentOrder, app);
                    case 3 -> Juices.showMenu(input, currentOrder, app);
                    case 4 -> Tea.showMenu(input, currentOrder, app);
                    case 5 -> Beverages.showMenu(input, currentOrder, app);
                    default -> System.out.println("Λάθος επιλογή! Παρακαλώ επιλέξτε από 0 έως 5.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Λάθος! Πρέπει να πληκτρολογήσετε αριθμό!");
                input.nextLine();
            }
        }

        System.out.println("\n***Απόδειξη Πληρωμής***");
        double totalSum = 0;
        for (Product p : currentOrder) {
            System.out.println(p.toString());
            totalSum += p.calculateTotal();
        }
        System.out.printf("Σύνολο: %.2f€\n", totalSum);
    }

    public int getValidInput(Scanner input, String message, int min, int max) {
        while (true) {
            try {
                System.out.print(message);
                int choice = input.nextInt();
                if (choice >= min && choice <= max) return choice;
                System.out.println("Λάθος! Επιλέξτε αριθμό από " + min + " έως " + max + ".");
            } catch (Exception e) {
                System.out.println("Λάθος! Πρέπει να πληκτρολογήσετε αριθμό.");
                input.nextLine();
            }
        }
    }
}
