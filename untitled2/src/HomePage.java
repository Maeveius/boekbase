import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HomePage implements BoekKastObserver{
    private Scanner scanner;
    private BoekKast boekKast;

    public HomePage(BoekKast boekKast) {
        this.scanner = new Scanner(System.in);
        this.boekKast = boekKast;
    }

    public int toonMenu() {
        int keuze;

        do {
            System.out.println("         Wat wil je doen?");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.println("- 1. Voeg een nieuw boek toe      -");
            System.out.println("= 2. Zoek een boek                =");
            System.out.println("- 3. Pas een boek aan             -");
            System.out.println("= 4. Verwijder een boek           =");
            System.out.println("- 5. Exit                         -");
            System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            System.out.print("Keuze: ");
            keuze = scanner.nextInt();
            scanner.nextLine();

            switch (keuze) {
                case 1:
                    voegNieuwBoekToe();
                    break;
                case 2:
                    genreUpdate();
                    break;
                case 3:
                    wijzigen();
                    break;
                case 4:
                    verwijderBoek();
                    break;
                case 5:
                    System.out.println("Tot ziens!");
                    break;
                default:
                    System.out.println("Ongeldige keuze, probeer opnieuw.");
            }
        } while (keuze != 5);

        return keuze;
    }

    public void voegNieuwBoekToe() {
        BoekController controller = new BoekController(boekKast);
        controller.voegBoekToe();
    }

    public void wijzigen() {
        System.out.println("Weet je zeker dat je een boek wilt aanpassen?");
        System.out.println("Y/N");
        String vraag31 = scanner.nextLine();
        if (vraag31.equalsIgnoreCase("N")) {
            System.out.println("Grappig");
            return;
        } else if (vraag31.equalsIgnoreCase("Y")) {
            System.out.println("Voer de naam van het boek in dat je wilt aanpassen:");
            String boekNaam = scanner.nextLine();

            List<Boek> gevondenBoeken = boekKast.zoekBoekenOpNaam(boekNaam);

            if (gevondenBoeken.isEmpty()) {
                System.out.println("Geen boeken gevonden met de opgegeven naam.");
                return;
            }

            Boek teWijzigenBoek = gevondenBoeken.get(0);

            System.out.println("Wat zou je willen aanpassen?");
            System.out.println("----------------------------");
            System.out.println("1. Naam");
            System.out.println("2. Genre");
            System.out.println("3. Jaar");
            System.out.println("4. Schrijver");
            System.out.println("5. Opmerking");
            System.out.println("----------------------------");
            int keuze = scanner.nextInt();
            scanner.nextLine();

            switch (keuze) {
                case 1:
                    System.out.println("Voer de nieuwe naam in:");
                    String nieuweNaam = scanner.nextLine();
                    boekKast.updateBoek("Naam", teWijzigenBoek.getNaam(), nieuweNaam);
                    System.out.println("Naam succesvol gewijzigd.");
                    break;
                case 2:
                    System.out.println("Voer het nieuwe genre in:");
                    String nieuwGenre = scanner.nextLine();
                    boekKast.updateBoek("Genre", String.join(",", teWijzigenBoek.getGenres()), nieuwGenre);
                    System.out.println("Genre succesvol gewijzigd.");
                    break;
                case 3:
                    System.out.println("Voer het nieuwe jaar in:");
                    int nieuwJaar = scanner.nextInt();
                    scanner.nextLine();
                    boekKast.updateBoek("Jaar", Integer.toString(teWijzigenBoek.getJaar()), Integer.toString(nieuwJaar));
                    System.out.println("Jaar succesvol gewijzigd.");
                    break;
                case 4:
                    System.out.println("Voer de nieuwe schrijver in:");
                    String nieuweSchrijver = scanner.nextLine();
                    boekKast.updateBoek("Schrijver", teWijzigenBoek.getSchrijver(), nieuweSchrijver);
                    System.out.println("Schrijver succesvol gewijzigd.");
                    break;
                case 5:
                    System.out.println("Voer de nieuwe opmerking in:");
                    String nieuweOpmerking = scanner.nextLine();
                    boekKast.updateBoek("Opmerking", teWijzigenBoek.getOpmerking(), nieuweOpmerking);
                    System.out.println("Opmerking succesvol gewijzigd.");
                    break;
                default:
                    System.out.println("Ongeldige keuze.");
            }
        }
    }

    public void verwijderBoek() {
        System.out.print("Welk boek wil je verwijderen? Geef de naam: ");
        String naam = scanner.nextLine();

        System.out.println("Weet je zeker dat je dit boek wilt verwijderen: " + naam);
        System.out.println("Y/N");
        String vraag92 = scanner.nextLine();
        if (vraag92.equalsIgnoreCase("Y")) {
            System.out.println("Oké, het is verwijderd");
            boekKast.verwijderBoek(naam);
        } else if (vraag92.equalsIgnoreCase("N")) {
            System.out.println("Dat dacht ik al");
        } else {
            System.out.println("Ongeldige invoer, stop.");
        }
    }

    public void genreUpdate() {
        System.out.println("Waar wil je op gaan zoeken?");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("1. Op naam");
        System.out.println("2. Op genre");
        System.out.println("3. Op jaar");
        System.out.println("4. Op schrijver");
        System.out.println("5. Alle gelezen");
        System.out.println("6. Alle niet gelezen");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        int vraag21 = scanner.nextInt();
        scanner.nextLine();

        List<Boek> resultaten = new ArrayList<>();

        switch (vraag21) {
            case 1:
                System.out.println("Type de naam:");
                String vraag211 = scanner.nextLine();
                resultaten = boekKast.zoekBoekenOpNaam(vraag211);
                break;
            case 2:
                System.out.println("Welke genre valt het onder (je kan meerdere nemen):");
                String vraag212 = scanner.nextLine();
                resultaten = boekKast.zoekBoekenOpGenre(vraag212);
                break;
            case 3:
                System.out.println("Uit welk jaar komt het boek:");
                int vraag213 = scanner.nextInt();
                scanner.nextLine();
                resultaten = boekKast.zoekBoekenOpJaar(vraag213);
                break;
            case 4:
                System.out.println("Weet je de schrijver:");
                String vraag214 = scanner.nextLine();
                resultaten = boekKast.zoekBoekenOpSchrijver(vraag214);
                break;
            case 5:
                System.out.println("Hier zijn alle gelezen boeken:");
                resultaten = boekKast.zoekBoekenOpGelezen(true);
                break;
            case 6:
                System.out.println("Hier zijn alle boeken die je nog niet gelezen hebt:");
                resultaten = boekKast.zoekBoekenOpGelezen(false);
                break;

            default:
                System.out.println("Ongeldige keuze.");
                return;
        }

        if (resultaten == null || resultaten.isEmpty()) {
            System.out.println("Geen resultaten gevonden.");
        } else {
            for (Boek boek : resultaten) {
                System.out.println("Naam: " + boek.getNaam());
                System.out.println("Genres: " + String.join(", ", boek.getGenres()));
                System.out.println("Jaar: " + boek.getJaar());
                System.out.println("Schrijver: " + boek.getSchrijver());
                System.out.println("Opmerking: " + boek.getOpmerking());
                System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            }
        }
    }

    @Override
    public void update(List<Boek> boeken) {

    }

    @Override
    public boolean contains(BoekKastObserver observer) {
        return false;
    }

    @Override
    public void add(BoekKastObserver observer) {

    }

    @Override
    public void remove(BoekKastObserver observer) {

    }
}
