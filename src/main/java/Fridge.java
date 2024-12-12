import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;


public class Fridge {
    private Map<String, ArrayList<Ingredient>> ingredients = new HashMap<>();

    public void addIngredient(Ingredient ingredient) {
        ingredients.putIfAbsent(ingredient.getName().toLowerCase(), new ArrayList<>());
        ArrayList<Ingredient> ingredientList = ingredients.get(ingredient.getName().toLowerCase());
        for (Ingredient existing : ingredientList) {
            if (existing.getExpirationDate().equals(ingredient.getExpirationDate())) {
                existing.addAmount(ingredient.getAmount(), ingredient.getCost());
                return;
            }
        }
        ingredientList.add(ingredient);
    }

    public String findIngredient(String name) {
        ArrayList<Ingredient> ingredientList = ingredients.get(name.toLowerCase());
        if (ingredientList != null && !ingredientList.isEmpty()) {
            double totalAmount = 0;
            double totalCost = 0;
            String unit = ingredientList.get(0).getUnit();
            for (Ingredient ingredient : ingredientList) {
                totalAmount += ingredient.getAmount();
                totalCost += ingredient.getCost();
            }
            return name + ", total mengde: " + totalAmount + " " + unit + ", total pris: " + totalCost + " kr";
        }
        return null;
    }

    public void removeIngredient(String name, double amount, String expiration) {
    ArrayList<Ingredient> ingredientList = ingredients.get(name.toLowerCase());
    if (ingredientList != null) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date expirationDate = sdf.parse(expiration);
            for (Ingredient ingredient : ingredientList) {
                if (ingredient.getExpirationDate().equals(expirationDate)) {
                    if (ingredient.getAmount() >= amount) {
                        ingredient.reduceAmount(amount);
                        if (ingredient.getAmount() == 0) {
                            ingredientList.remove(ingredient);
                        }
                        return;
                    } else {
                        System.out.println("Mengden som skal fjernes er større enn tilgjengelig mengde.");
                        return;
                    }
                }
            }
        } catch (ParseException e) {
            System.out.println("Feil format på utløpsdato. Bruk dd.MM.yyyy.");
        }
        System.out.println("Ingen vare med spesifisert utløpsdato ble funnet.");
    } else {
        System.out.println("Varen finnes ikke i kjøleskapet.");
    }
}


    public void printOverview() {
        List<Ingredient> allIngredients = new ArrayList<>();
        for (ArrayList<Ingredient> ingredientList : ingredients.values()) {
            allIngredients.addAll(ingredientList);
        }
        allIngredients.sort((m1, m2) -> m1.getName().compareToIgnoreCase(m2.getName()));

        for (Ingredient ingredient : allIngredients) {
            System.out.println(ingredient);
        }
    }

    public void printIngredientsBeforeDate(String date) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date limitDate = sdf.parse(date);

        System.out.println("Varer med best-før-dato før " + date + ":");
        for (ArrayList<Ingredient> ingredientList : ingredients.values()) {
            for (Ingredient ingredient : ingredientList) {
                if (ingredient.getExpirationDate().before(limitDate)) {
                    System.out.println(ingredient);
                }
            }
        }
    }

    public void printExpired() {
        Date today = new Date();
        double totalValue = 0;
        for (ArrayList<Ingredient> ingredientList : ingredients.values()) {
            for (Ingredient ingredient : ingredientList) {
                if (ingredient.getExpirationDate().before(today)) {
                    System.out.println(ingredient);
                    totalValue += ingredient.getCost();
                }
            }
        }
        System.out.println("Samlet verdi av utdaterte varer: " + totalValue + " kr");
    }

    public double calculateValue(String[] nameList) {
        double totalValue = 0;
        for (String name : nameList) {
            ArrayList<Ingredient> ingredientList = ingredients.get(name.toLowerCase());
            if (ingredientList != null) {
                for (Ingredient ingredient : ingredientList) {
                    totalValue += ingredient.getCost();
                }
            }
        }
        return totalValue;
    }

    public Map<String, ArrayList<Ingredient>> getIngredients() {
        return ingredients;
    }

    public void fillFrdige() {
        try {
            addIngredient(new Ingredient("Melk", 1.5, "L", "12.12.2024", 20.0));
            addIngredient(new Ingredient("Juice", 4.0, "L", "15.12.2024", 40.0));
            addIngredient(new Ingredient("Egg", 6, "stk", "01.01.2025", 25.0));
            addIngredient(new Ingredient("Smør", 0.1, "kg", "20.11.2024", 15.0));
            addIngredient(new Ingredient("Paprika", 1, "stk", "10.11.2024", 10.0));
            addIngredient(new Ingredient("Yoghurt", 0.5, "L", "05.12.2024", 12.0));
            addIngredient(new Ingredient("Ost", 0.25, "kg", "25.12.2024", 50.0));
        } catch (ParseException e) {
            System.out.println("Feil ved oppretting av varer: " + e.getMessage());
        }
    }
}
