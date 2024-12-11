import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Matvare {
    private String navn;
    private double mengde;
    private String enhet;
    private Date utlop;
    private double pris;

    public Matvare(String navn, double mengde, String enhet, String utlop, double pris) throws ParseException {
        this.navn = navn;
        this.mengde = mengde;
        this.enhet = enhet;
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        this.utlop = sdf.parse(utlop);
        this.pris = pris;
    }

    public String getNavn() {
        return navn;
    }

    public double getMengde() {
        return mengde;
    }

    public Date getUtlop() {
        return utlop;
    }

    public double getPris() {
        return pris;
    }

    public String getEnhet() {
        return enhet;
    }

    public double getPrisPerEnhet() {
        return pris / mengde;
    }

    public void leggTilMengde(double mengde, double pris) {
        this.mengde += mengde;
        this.pris += pris;
    }

    public void reduserMengde(double mengde) {
        this.pris -= getPrisPerEnhet() * mengde;
        this.mengde -= mengde;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        return navn + ", " + mengde + " " + enhet + ", utløpsdato: " + sdf.format(utlop) + ", pris: " + pris + " kr";
    }
}