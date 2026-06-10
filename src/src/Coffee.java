public class Coffee extends Product{

    private String sugar;
    private boolean extraShot;
    private boolean extraVeganMilk;

    public Coffee(String name, double price, String sugar, boolean extraShot,boolean extraVeganMilk) {
        super(name, price);
        this.sugar = sugar;
        this.extraShot = extraShot;
        this.extraVeganMilk = extraVeganMilk;
    }

    @Override
    public double calculateTotal() {
        double finalPrice = price;
        if(extraShot) {
            finalPrice = finalPrice + 0.60;
        }
        if (extraVeganMilk) {
            finalPrice = finalPrice + 0.60;
        }
        return finalPrice;
    }

    @Override
    public String toString() {
        String shot = extraShot ? "(Με Extra Δοση)" : "";
        String milk = extraVeganMilk ? "(Με Extra Γαλα Vegan)" : "";
        return String.format("%s [%s]%s %s: %.2f €", getName(), sugar, shot, milk, calculateTotal());
    }

    public static void showMenu(java.util.Scanner input, java.util.ArrayList<Product> currentOrder, OrderMenu mainApp) {
        java.util.ArrayList<Coffee> coffees = new java.util.ArrayList<>();
        coffees.add(new Coffee("Espresso", 1.5, "Σκέτος", false, false));
        coffees.add(new Coffee("Espresso Double", 2.1, "Σκέτος", false, false));
        coffees.add(new Coffee("Espresso Americano", 2.1, "Σκέτος", false, false));
        coffees.add(new Coffee("Espresso Freddo", 2.4, "Σκέτος", false, false));
        coffees.add(new Coffee("Cappuccino", 1.9, "Σκέτος", false, false));
        coffees.add(new Coffee("Cappuccino Double", 2.4, "Σκέτος", false, false));
        coffees.add(new Coffee("Cappuccino Freddo", 2.6, "Σκέτος", false, false));
        coffees.add(new Coffee("Φίλτρου", 2.2, "Σκέτος", false, false));
        coffees.add(new Coffee("Nescafe", 2.2, "Σκέτος", false, false));

        System.out.println("\n--- ΚΑΤΑΛΟΓΟΣ ΚΑΦΕΔΩΝ ---");
        for (int i = 0; i < coffees.size(); i++) {
            System.out.println((i + 1) + ". " + coffees.get(i).getName() + " (" + coffees.get(i).getPrice() + "€)");
        }

        int choice = mainApp.getValidInput(input, "Επιλέξτε καφέ (ή 0 για επιστροφή): ", 0, coffees.size());
        if (choice == 0) return;

        Coffee selected = coffees.get(choice - 1);

        int sugarChoice = mainApp.getValidInput(input, "Επιλέξτε ζάχαρη (1. Σκέτος, 2. Μέτριος, 3. Γλυκός): ", 1, 3);
        String sugarLabel = switch (sugarChoice) {
            case 1 -> "Σκέτος";
            case 2 -> "Μέτριος";
            default -> "Γλυκός";
        };

        boolean extraShot = mainApp.getValidInput(input, "Έξτρα Δόση; (1. Ναι, 2. Όχι): ", 1, 2) == 1;
        boolean extraVeganMilk = mainApp.getValidInput(input, "Έξτρα Γάλα Vegan; (1. Ναι, 2. Όχι): ", 1, 2) == 1;

        currentOrder.add(new Coffee(selected.getName(), selected.getPrice(), sugarLabel, extraShot, extraVeganMilk));
        System.out.println("Ο καφές προστέθηκε στην παραγγελία!");
    }
}