import java.util.ArrayList;
import java.util.Scanner;
import java.text.ParseException;


public class MatsvinnApp {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        Kjoleskap kjoleskap = new Kjoleskap();

        while (true) {
            System.out.println("\n1. Opprett ny vare\n2. Legg til vare i kjøleskapet\n3. Søk etter vare\n4. Fjern vare\n5. Skriv ut alle varer\n6. Skriv ut utdaterte varer\n7. Beregn verdi av varer\n8. Vis pris per enhet for en vare\n9. Avslutt");
            System.out.print("Velg et alternativ: ");
            int valg = scanner.nextInt();
            scanner.nextLine(); // Rydder opp input

            switch (valg) {
                case 1:
                    System.out.print("Navn: ");
                    String navn = scanner.nextLine();
                    System.out.print("Mengde: ");
                    double mengde = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enhet: ");
                    String enhet = scanner.nextLine();
                    System.out.print("Utløpsdato (dd.MM.yyyy): ");
                    String utlop = scanner.nextLine();
                    System.out.print("Pris: ");
                    double pris = scanner.nextDouble();
                    scanner.nextLine();

                    Matvare matvare = new Matvare(navn, mengde, enhet, utlop, pris);
                    kjoleskap.leggTilMatvare(matvare);
                    System.out.println("Matvare lagt til!");
                    break;

                case 2:
                    System.out.print("Navn: ");
                    navn = scanner.nextLine();
                    System.out.print("Mengde: ");
                    mengde = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enhet: ");
                    enhet = scanner.nextLine();
                    System.out.print("Utløpsdato (dd.MM.yyyy): ");
                    utlop = scanner.nextLine();
                    System.out.print("Pris: ");
                    pris = scanner.nextDouble();
                    scanner.nextLine();

                    matvare = new Matvare(navn, mengde, enhet, utlop, pris);
                    kjoleskap.leggTilMatvare(matvare);
                    System.out.println("Matvare lagt til!");
                    break;

                case 3:
                    System.out.print("Søk etter vare: ");
                    navn = scanner.nextLine();
                    String resultat = kjoleskap.finnMatvare(navn);
                    if (resultat != null) {
                        System.out.println(resultat);
                    } else {
                        System.out.println("Varen finnes ikke i kjøleskapet.");
                    }
                    break;
                
                case 4:
                    System.out.print("Navn: ");
                    navn = scanner.nextLine();
                    System.out.print("Mengde å fjerne: ");
                    mengde = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Utløpsdato (dd.MM.yyyy): ");
                    utlop = scanner.nextLine();
                    kjoleskap.fjernMatvare(navn, mengde, utlop);
                    break;

                case 5:
                    System.out.println("Oversikt over alle varer:");
                    kjoleskap.skrivUtOversikt();
                    break;

                case 6:
                    System.out.println("Utdaterte varer:");
                    kjoleskap.skrivUtUtdaterte();
                    break;

                case 7:
                    System.out.print("Oppgi varer (kommaseparert): ");
                    String[] varer = scanner.nextLine().split(",");
                    double verdi = kjoleskap.beregnVerdi(varer);
                    System.out.println("Samlet verdi: " + verdi + " kr");
                    break;

                case 8:
                System.out.print("Oppgi navnet på varen: ");
                navn = scanner.nextLine();
                ArrayList<Matvare> vareListe = kjoleskap.getMatvarer().get(navn.toLowerCase());
                if (vareListe != null && !vareListe.isEmpty()) {
                    double totalMengde = 0;
                    double totalPris = 0;
                    enhet = vareListe.get(0).getEnhet();
                    for (Matvare vare : vareListe) {
                        totalMengde += vare.getMengde();
                        totalPris += vare.getPris();
                    }
                    System.out.println("Pris per enhet for " + navn + ": " + (totalPris / totalMengde) + " kr per " + enhet);
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
}
