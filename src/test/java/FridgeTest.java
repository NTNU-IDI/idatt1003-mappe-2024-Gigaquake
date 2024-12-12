import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class FridgeTest {

    @Test
    public void testAddAndFindIngredient() throws ParseException {
        Fridge fridge = new Fridge();
        Ingredient melk = new Ingredient("Melk", 1.5, "L", "10.12.2024", 30.0);

        fridge.addIngredient(melk);

        String resultat = fridge.findIngredient("Melk");
        assertNotNull(resultat);
        assertTrue(resultat.contains("Melk"));
        assertTrue(resultat.contains("1.5 L"));
        assertTrue(resultat.contains("30.0 kr"));
    }

    @Test
    public void testRemoveIngredient() throws ParseException {
        Fridge fridge = new Fridge();
        Ingredient melk = new Ingredient("Melk", 1.5, "L", "10.12.2024", 30.0);

        fridge.addIngredient(melk);

        fridge.removeIngredient("Melk", 1.0, "10.12.2024");

        String resultat = fridge.findIngredient("Melk");
        assertNotNull(resultat);
        assertTrue(resultat.contains("0.5 L"));
        assertFalse(resultat.contains("1.5 L"));
    }

    @Test
    public void testPrintOverview() throws ParseException {
        Fridge fridge = new Fridge();
        fridge.addIngredient(new Ingredient("Melk", 1.5, "L", "12.12.2024", 20.0));
        fridge.addIngredient(new Ingredient("Juice", 2.0, "L", "10.11.2024", 30.0));
    
        List<Ingredient> resultat = new ArrayList<>();
        for (ArrayList<Ingredient> ingredientList : fridge.getIngredients().values()) {
            resultat.addAll(ingredientList);
        }
        resultat.sort((m1, m2) -> m1.getName().compareToIgnoreCase(m2.getName()));
    
        assertEquals(2, resultat.size());
        assertEquals("Juice", resultat.get(0).getName());
        assertEquals("Melk", resultat.get(1).getName());
    }

    @Test
    public void testPrintIngredientsBeforeDate() throws ParseException {
        Fridge fridge = new Fridge();
        fridge.addIngredient(new Ingredient("Melk", 1.5, "L", "12.12.2024", 20.0));
        fridge.addIngredient(new Ingredient("Juice", 2.0, "L", "10.11.2024", 30.0));

        System.out.println("Varer med best-før-dato før 15.11.2024:");
        fridge.printIngredientsBeforeDate("15.11.2024");
    }

    @Test
    public void testFyllFridge() {
        Fridge fridge = new Fridge();
        fridge.fillFrdige();

        assertNotNull(fridge.findIngredient("Melk"));
        assertNotNull(fridge.findIngredient("Juice"));
        assertNotNull(fridge.findIngredient("Egg"));
        assertNotNull(fridge.findIngredient("Smør"));
        assertNotNull(fridge.findIngredient("Paprika"));
        assertNotNull(fridge.findIngredient("Yoghurt"));
        assertNotNull(fridge.findIngredient("Ost"));
    }

    @Test
    public void testPrintExpired() throws ParseException {
        Fridge fridge = new Fridge();
        Ingredient gammelMelk = new Ingredient("Melk", 1.5, "L", "10.12.2023", 30.0);
        Ingredient ferskMelk = new Ingredient("Melk", 1.5, "L", "10.12.2024", 30.0);

        fridge.addIngredient(gammelMelk);
        fridge.addIngredient(ferskMelk);

        System.out.println("Utdaterte varer:");
        fridge.printExpired();
        // Visuell bekreftelse under test, kan utvides med mer spesifikke metoder for verifikasjon.
    }

    @Test
    public void testCalculateValue() throws ParseException {
        Fridge fridge = new Fridge();
        Ingredient melk = new Ingredient("Melk", 1.5, "L", "10.12.2024", 30.0);
        Ingredient juice = new Ingredient("Juice", 2.0, "L", "15.12.2024", 50.0);

        fridge.addIngredient(melk);
        fridge.addIngredient(juice);

        String[] varer = { "Melk", "Juice" };
        double verdi = fridge.calculateValue(varer);

        assertEquals(80.0, verdi, 0.01);
    }

    @Test
    public void testReduceAmount() throws ParseException {
        Ingredient melk = new Ingredient("Melk", 1.5, "L", "10.12.2024", 30.0);
        melk.reduceAmount(0.5);

        assertEquals(1.0, melk.getAmount(), 0.01);
        assertEquals(20.0, melk.getCost(), 0.01);
    }
}
