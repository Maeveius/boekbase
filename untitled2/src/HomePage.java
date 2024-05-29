import BoekOpBouw.Boek;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HomePage implements BoekKastObserver {
    private ZoekBoek zoekBoek;
    private BoekErAf boekErAf;
    private BoekUpdate boekUpdate;
    private BoekErBij boekErBij;
    private Scanner scanner;
    private List<BoekKastObserver> observers;


    public HomePage(ZoekBoek zoekBoek, BoekErAf boekErAf, BoekUpdate boekUpdate, BoekErBij boekErBij) {
        this.zoekBoek = zoekBoek;
        this.boekErAf = boekErAf;
        this.boekUpdate = boekUpdate;
        this.boekErBij = boekErBij;
        this.scanner = new Scanner(System.in);
        this.observers = new ArrayList<>();
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
        BoekController controller = new BoekController(zoekBoek, boekErAf, boekUpdate, boekErBij);
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

            List<Boek> gevondenBoeken = zoekBoek.zoekBoekenOpNaam(boekNaam);

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

            BoekController controller = new BoekController(zoekBoek, boekErAf, boekUpdate, boekErBij);

            switch (keuze) {
                case 1:
                    System.out.println("Voer de nieuwe naam in:");
                    String nieuweNaam = scanner.nextLine();
                    controller.updateBoek("Naam", teWijzigenBoek.getTitel().getTitel(), nieuweNaam);
                    System.out.println("Naam succesvol gewijzigd.");
                    break;
                case 2:
                    System.out.println("Voer het nieuwe genre in:");
                    String nieuwGenre = scanner.nextLine();
                    controller.updateBoek("Genre", String.join(",", teWijzigenBoek.getGenres().getGenres()), nieuwGenre);
                    System.out.println("Genre succesvol gewijzigd.");
                    break;
                case 3:
                    System.out.println("Voer het nieuwe jaar in:");
                    int nieuwJaar = scanner.nextInt();
                    scanner.nextLine();
                    controller.updateBoek("Jaar", Integer.toString(teWijzigenBoek.getJaar().getJaar()), Integer.toString(nieuwJaar));
                    System.out.println("Jaar succesvol gewijzigd.");
                    break;
                case 4:
                    System.out.println("Voer de nieuwe schrijver in:");
                    String nieuweSchrijver = scanner.nextLine();
                    controller.updateBoek("Schrijver", teWijzigenBoek.getAuteur().getAuteur(), nieuweSchrijver);
                    System.out.println("Schrijver succesvol gewijzigd.");
                    break;
                case 5:
                    System.out.println("Voer de nieuwe opmerking in:");
                    String nieuweOpmerking = scanner.nextLine();
                    controller.updateBoek("Opmerking", teWijzigenBoek.getOpmerking().getOpmerking(), nieuweOpmerking);
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
            BoekController controller = new BoekController(zoekBoek, boekErAf, boekUpdate, boekErBij);
            controller.verwijderBoek(naam);
            System.out.println("Oké, het is verwijderd");
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

        BoekController controller = new BoekController(zoekBoek, boekErAf, boekUpdate, boekErBij);

        switch (vraag21) {
            case 1:
                System.out.println("Type de naam:");
                String vraag211 = scanner.nextLine();
                resultaten = controller.zoekBoekenOpNaam(vraag211);
                break;
            case 2:
                System.out.println("Welke genre valt het onder (je kan meerdere nemen):");
                String vraag212 = scanner.nextLine();
                resultaten = controller.zoekBoekenOpGenre(vraag212);
                break;
            case 3:
                System.out.println("Uit welk jaar komt het boek:");
                int vraag213 = scanner.nextInt();
                scanner.nextLine();
                resultaten = controller.zoekBoekenOpJaar(vraag213);
                break;
            case 4:
                System.out.println("Weet je de schrijver:");
                String vraag214 = scanner.nextLine();
                resultaten = controller.zoekBoekenOpSchrijver(vraag214);
                break;
            case 5:
                System.out.println("Hier zijn alle gelezen boeken:");
                resultaten = controller.zoekBoekenOpGelezen(true);
                break;
            case 6:
                System.out.println("Hier zijn alle boeken die je nog niet gelezen hebt:");
                resultaten = controller.zoekBoekenOpGelezen(false);
                break;

            default:
                System.out.println("Ongeldige keuze.");
                return;
        }

        if (resultaten == null || resultaten.isEmpty()) {
            System.out.println("Geen resultaten gevonden.");
        } else {
            for (Boek boek : resultaten) {
                System.out.println("Titel: " + boek.getTitel().getTitel());
                System.out.println("Genres: " + String.join(", ", boek.getGenres().getGenres()));
                System.out.println("Jaar: " + boek.getJaar().getJaar());
                System.out.println("Schrijver: " + boek.getAuteur().getAuteur());
                System.out.println("Opmerking: " + boek.getOpmerking().getOpmerking());
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
