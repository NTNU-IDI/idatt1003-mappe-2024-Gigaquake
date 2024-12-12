import java.util.ArrayList;
import java.util.Scanner;
import java.text.ParseException;
           
public class FoodWasteApp {
    private void init() {
        Fridge fridge = new Fridge();
        fridge.fillFrdige(); // Legger til varer i kjøleskapet for tester
    }


    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Legg til vare i kjøleskapet\n2. Søk etter vare\n3. Fjern vare\n4. Skriv ut alle varer\n5. Skriv ut utdaterte varer\n6. Beregn verdi av varer\n7. Skriv ut varer med best-før-dato før angitt dato\n8. Vis pris per enhet for en vare\n9. Avslutt");
            System.out.print("Velg et alternativ: ");
            int valg = scanner.nextInt();
            scanner.nextLine(); // Rydder opp input

            switch (valg) {
                case 1:
                System.out.print("Navn: ");
                String name = scanner.nextLine();
                System.out.print("Mengde: ");
                double mengde = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Enhet: ");
                String unit = scanner.nextLine();
                System.out.print("Utløpsdato (dd.MM.yyyy): ");
                String expiration = scanner.nextLine();
                System.out.print("Pris: ");
                double cost = scanner.nextDouble();
                scanner.nextLine();

                Ingredient ingredient = new Ingredient(name, mengde, unit, expiration, cost);
                fridge.addIngredient(ingredient);
                System.out.println("Matvare lagt til!");
                break;

                case 2:
                    System.out.print("Navn på varen du vil søke etter: ");
                    name = scanner.nextLine();
                    String resultat = fridge.findIngredient(name);
                    if (resultat != null) {
                        System.out.println(resultat);
                    } else {
                        System.out.println("Varen finnes ikke i kjøleskapet.");
                    }
                    break;

                case 3:
                    System.out.print("Navn: ");
                    name = scanner.nextLine();
                    System.out.print("Mengde å fjerne: ");
                    mengde = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Utløpsdato (dd.MM.yyyy): ");
                    expiration = scanner.nextLine();

                    fridge.removeIngredient(name, mengde, expiration);
                    System.out.println("Varen ble oppdatert eller fjernet.");
                    break;

                case 4:
                    System.out.println("Alle matvarer i kjøleskapet:");
                    fridge.printOverview();
                    break;

                case 5:
                    System.out.println("Utdaterte varer:");
                    fridge.printExpired();
                    break;

                case 6:
                    System.out.println("Angi varer (separert med komma): ");
                    String varerInput = scanner.nextLine();
                    String[] varer = varerInput.split(",");
                    double totalValue = fridge.calculateValue(varer);
                    System.out.println("Samlet verdi for valgte varer: " + totalValue + " kr");
                    break;

                case 7:
                    System.out.print("Angi dato (dd.MM.yyyy): ");
                    String date = scanner.nextLine();
                    fridge.printIngredientsBeforeDate(date);
                    break;
                
                case 8:
                System.out.print("Oppgi navnet på varen: ");
                name = scanner.nextLine();
                ArrayList<Ingredient> ingredientList = fridge.getIngredients().get(name.toLowerCase());
                if (ingredientList != null && !ingredientList.isEmpty()) {
                    double totalAmount = 0;
                    double totalCost = 0;
                    unit = ingredientList.get(0).getUnit();
                    for (Ingredient vare : ingredientList) {
                        totalAmount += vare.getAmount();
                        totalCost += vare.getCost();
                    }
                    System.out.println("Pris per enhet for " + name + ": " + (totalCost / totalAmount) + " kr per " + unit);
                } else {
                    System.out.println("Varen finnes ikke i kjøleskapet.");
                }
                break; 

                case 9:
                    System.out.println("Avslutter programmet...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Ugyldig valg, prøv igjen.");
            }
        }
    }
    public static void main(String[] args) {
        FoodWasteApp app = new FoodWasteApp();
        app.init(); // Initialiserer applikasjonen
        app.start(); // Starter hovedmenyen
    }
}
