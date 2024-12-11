import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.text.ParseException;
import java.util.ArrayList;

public class KjoleskapTest {

    @Test
    public void testLeggTilOgFinnMatvare() throws ParseException {
        Kjoleskap kjoleskap = new Kjoleskap();
        Matvare melk = new Matvare("Melk", 1.5, "L", "10.12.2024", 30.0);

        kjoleskap.leggTilMatvare(melk);

        String resultat = kjoleskap.finnMatvare("Melk");
        assertNotNull(resultat);
        assertTrue(resultat.contains("Melk"));
        assertTrue(resultat.contains("1.5 L"));
        assertTrue(resultat.contains("30.0 kr"));
    }

    @Test
    public void testFjernMatvare() throws ParseException {
        Kjoleskap kjoleskap = new Kjoleskap();
        Matvare melk = new Matvare("Melk", 1.5, "L", "10.12.2024", 30.0);

        kjoleskap.leggTilMatvare(melk);

        kjoleskap.fjernMatvare("Melk", 1.0, "10.12.2024");

        String resultat = kjoleskap.finnMatvare("Melk");
        assertNotNull(resultat);
        assertTrue(resultat.contains("0.5 L"));
        assertFalse(resultat.contains("1.5 L"));
    }

    @Test
    public void testSkrivUtUtdaterte() throws ParseException {
        Kjoleskap kjoleskap = new Kjoleskap();
        Matvare gammelMelk = new Matvare("Melk", 1.5, "L", "10.12.2023", 30.0);
        Matvare ferskMelk = new Matvare("Melk", 1.5, "L", "10.12.2024", 30.0);

        kjoleskap.leggTilMatvare(gammelMelk);
        kjoleskap.leggTilMatvare(ferskMelk);

        System.out.println("Utdaterte varer:");
        kjoleskap.skrivUtUtdaterte();
        // Visuell bekreftelse under test, kan utvides med mer spesifikke metoder for verifikasjon.
    }

    @Test
    public void testBeregnVerdi() throws ParseException {
        Kjoleskap kjoleskap = new Kjoleskap();
        Matvare melk = new Matvare("Melk", 1.5, "L", "10.12.2024", 30.0);
        Matvare juice = new Matvare("Juice", 2.0, "L", "15.12.2024", 50.0);

        kjoleskap.leggTilMatvare(melk);
        kjoleskap.leggTilMatvare(juice);

        String[] varer = { "Melk", "Juice" };
        double verdi = kjoleskap.beregnVerdi(varer);

        assertEquals(80.0, verdi, 0.01);
    }

    @Test
    public void testReduserMengde() throws ParseException {
        Matvare melk = new Matvare("Melk", 1.5, "L", "10.12.2024", 30.0);
        melk.reduserMengde(0.5);

        assertEquals(1.0, melk.getMengde(), 0.01);
        assertEquals(20.0, melk.getPris(), 0.01);
    }
}
