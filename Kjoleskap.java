import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Date;


public class Kjoleskap {
    private Map<String, ArrayList<Matvare>> matvarer = new HashMap<>();

    public void leggTilMatvare(Matvare matvare) {
        matvarer.putIfAbsent(matvare.getNavn().toLowerCase(), new ArrayList<>());
        ArrayList<Matvare> vareListe = matvarer.get(matvare.getNavn().toLowerCase());
        for (Matvare eksisterende : vareListe) {
            if (eksisterende.getUtlop().equals(matvare.getUtlop())) {
                eksisterende.leggTilMengde(matvare.getMengde(), matvare.getPris());
                return;
            }
        }
        vareListe.add(matvare);
    }

    public String finnMatvare(String navn) {
        ArrayList<Matvare> vareListe = matvarer.get(navn.toLowerCase());
        if (vareListe != null && !vareListe.isEmpty()) {
            double totalMengde = 0;
            double totalPris = 0;
            String enhet = vareListe.get(0).getEnhet();
            for (Matvare matvare : vareListe) {
                totalMengde += matvare.getMengde();
                totalPris += matvare.getPris();
            }
            return navn + ", total mengde: " + totalMengde + " " + enhet + ", total pris: " + totalPris + " kr";
        }
        return null;
    }

    public void fjernMatvare(String navn, double mengde, String utlop) {
    ArrayList<Matvare> vareListe = matvarer.get(navn.toLowerCase());
    if (vareListe != null) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date utlopDato = sdf.parse(utlop);
            for (Matvare matvare : vareListe) {
                if (matvare.getUtlop().equals(utlopDato)) {
                    if (matvare.getMengde() >= mengde) {
                        matvare.reduserMengde(mengde);
                        if (matvare.getMengde() == 0) {
                            vareListe.remove(matvare);
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


    public void skrivUtOversikt() {
        for (ArrayList<Matvare> vareListe : matvarer.values()) {
            double totalMengde = 0;
            double totalPris = 0;
            String navn = "";
            String enhet = "";
            for (Matvare matvare : vareListe) {
                totalMengde += matvare.getMengde();
                totalPris += matvare.getPris();
                navn = matvare.getNavn();
                enhet = matvare.getEnhet();
            }
            System.out.println(navn + ", " + totalMengde + " " + enhet + ", total pris: " + totalPris + " kr");
        }
    }

    public void skrivUtUtdaterte() {
        Date idag = new Date();
        double totalVerdi = 0;
        for (ArrayList<Matvare> vareListe : matvarer.values()) {
            for (Matvare matvare : vareListe) {
                if (matvare.getUtlop().before(idag)) {
                    System.out.println(matvare);
                    totalVerdi += matvare.getPris();
                }
            }
        }
        System.out.println("Samlet verdi av utdaterte varer: " + totalVerdi + " kr");
    }

    public double beregnVerdi(String[] navnListe) {
        double totalVerdi = 0;
        for (String navn : navnListe) {
            ArrayList<Matvare> vareListe = matvarer.get(navn.toLowerCase());
            if (vareListe != null) {
                for (Matvare matvare : vareListe) {
                    totalVerdi += matvare.getPris();
                }
            }
        }
        return totalVerdi;
    }

    public Map<String, ArrayList<Matvare>> getMatvarer() {
        return matvarer;
    }
}
