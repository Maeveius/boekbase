import BoekOpBouw.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HomePage implements BoekKastObserver, ObserverOnderwerp {
    private final ZoekBoek zoekBoek;
    private final BoekErAf boekErAf;
    private final BoekUpdate boekUpdate;
    private final BoekErBij boekErBij;
    private final Scanner scanner;
    private final List<BoekKastObserver> observers;

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
                    zoekBoekMenu();
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
        notifyObservers();
    }

    public void wijzigen() {
        System.out.println("Weet je zeker dat je een boek wilt aanpassen?");
        System.out.println("Y/N");
        String vraag31 = scanner.nextLine();
        if (vraag31.equalsIgnoreCase("N")) {
            System.out.println("Grappig");
        } else if (vraag31.equalsIgnoreCase("Y")) {
            System.out.println("Voer de naam van het boek in dat je wilt aanpassen:");
            String boekNaam = scanner.nextLine();

            List<Boek> gevondenBoeken = zoekBoek.zoekBoekenOpNaam(boekNaam);

            if (gevondenBoeken.isEmpty()) {
                System.out.println("Geen boeken gevonden met de opgegeven naam.");
                return;
            }

            Boek teWijzigenBoek = gevondenBoeken.getFirst();

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
                    controller.updateBoek("Naam", teWijzigenBoek.getTitel(), nieuweNaam);
                    System.out.println("Naam succesvol gewijzigd.");
                    break;
                case 2:
                    System.out.println("Voer het nieuwe genre in:");
                    String nieuwGenre = scanner.nextLine();
                    controller.updateBoek("Genre", String.join(",", teWijzigenBoek.getGenres()), nieuwGenre);
                    System.out.println("Genre succesvol gewijzigd.");
                    break;
                case 3:
                    System.out.println("Voer het nieuwe jaar in:");
                    int nieuwJaar = scanner.nextInt();
                    scanner.nextLine();
                    controller.updateBoek("Jaar", Integer.toString(teWijzigenBoek.getJaar()), Integer.toString(nieuwJaar));
                    System.out.println("Jaar succesvol gewijzigd.");
                    break;
                case 4:
                    System.out.println("Voer de nieuwe schrijver in:");
                    String naam = scanner.nextLine();
                    System.out.println("Weet je nog meer over de schrijver (Y/N)");
                    String antwoord = scanner.nextLine();

                    String alles;
                    if (antwoord.equalsIgnoreCase("Y")) {
                        System.out.print("Geboortejaar van de schrijver?: ");
                        int geboortejaar = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Wat is het beste boek van de schrijver?: ");
                        String besteBoek = scanner.nextLine();
                        System.out.print("Algemene informatie over de schrijver?: ");
                        String algemeneInformatie = scanner.nextLine();
                        alles = String.format("%s,%d,%s,%s", naam, geboortejaar, besteBoek, algemeneInformatie);
                    } else {
                        alles = String.format("%s,%d,%s,%s", naam, 0, "Onbekend", "Geen informatie");
                    }
                    controller.updateBoek("Schrijver", teWijzigenBoek.getAuteur(), alles);
                    System.out.println("Schrijver succesvol gewijzigd.");
                    break;
                case 5:
                    System.out.println("Voer de nieuwe opmerking in:");
                    String nieuweOpmerking = scanner.nextLine();
                    controller.updateBoek("Opmerking", teWijzigenBoek.getOpmerking(), nieuweOpmerking);
                    System.out.println("Opmerking succesvol gewijzigd.");
                    break;
                default:
                    System.out.println("Ongeldige keuze.");
            }
            notifyObservers();
        }
    }

    public void verwijderBoek() {
        System.out.print("Welk boek wil je verwijderen? Geef de naam: ");
        String naam = scanner.nextLine();

        System.out.println("Weet je zeker dat je dit boek wilt verwijderen: " + naam + "?\n(Y/N)");
        if (scanner.nextLine().equalsIgnoreCase("Y")) {
            BoekController controller = new BoekController(zoekBoek, boekErAf, boekUpdate, boekErBij);
            controller.verwijderBoek(naam);
            System.out.println("Boek succesvol verwijderd.");
            notifyObservers();
        } else {
            System.out.println("Dat dacht ik al");
        }
    }

    public void zoekBoekMenu() {
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

        BoekController controller = new BoekController(zoekBoek, boekErAf, boekUpdate, boekErBij);
        List<Boek> resultaten;

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
                System.out.println("Titel: " + boek.getTitel());
                System.out.println("Genres: " + String.join(", ", boek.getGenres()));
                System.out.println("Jaar: " + boek.getJaar());
                System.out.println("Schrijver: " + boek.getAuteur());
                System.out.println("Speciaal: " + boek.getSpeciaal());
                System.out.println("Opmerking: " + boek.getOpmerking());
                System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            }
        }
    }

    @Override
    public void update(List<Boek> boeken) {
        System.out.println("Observer is op de hoogte gesteld van de nieuwe boekenlijst:");
        for (Boek boek : boeken) {
            System.out.println("Titel: " + boek.getTitel());
        }
    }

    @Override
    public void addObserver(BoekKastObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
            System.out.println("Observer succesvol geregistreerd.");
        } else {
            System.out.println("Observer is null of al geregistreerd. Registratie mislukt.");
        }
    }

    @Override
    public void removeObserver(BoekKastObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        List<Boek> boeken = zoekBoek.zoekOpAlles();
        for (BoekKastObserver observer : observers) {
            observer.update(boeken);
        }
    }
}
