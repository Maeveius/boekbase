//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class HomePage {
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
            keuze = this.scanner.nextInt();
            this.scanner.nextLine();
            switch (keuze) {
                case 1:
                    this.voegNieuwBoekToe();
                    break;
                case 2:
                    this.genreUpdate();
                    break;
                case 3:
                    this.wijzigen();
                    break;
                case 4:
                    this.verwijderBoek();
                    break;
                case 5:
                    System.out.println("Tot ziens!");
                    break;
                default:
                    System.out.println("Ongeldige keuze, probeer opnieuw.");
            }
        } while(keuze != 5);

        return keuze;
    }

    public void voegNieuwBoekToe() {
        BoekController controller = new BoekController(this.boekKast);
        controller.voegBoekToe();
    }

    public void wijzigen() {
        System.out.println("Weet je zeker dat je een boek wilt aanpassen?");
        System.out.println("Y/N");
        String vraag31 = this.scanner.nextLine();
        if (vraag31.equalsIgnoreCase("N")) {
            System.out.println("Grappig");
        } else {
            if (vraag31.equalsIgnoreCase("Y")) {
                System.out.println("Voer de naam van het boek in dat je wilt aanpassen:");
                String boekNaam = this.scanner.nextLine();
                List<Boek> gevondenBoeken = this.boekKast.zoekBoekenOpNaam(boekNaam);
                if (gevondenBoeken.isEmpty()) {
                    System.out.println("Geen boeken gevonden met de opgegeven naam.");
                    return;
                }

                Boek teWijzigenBoek = (Boek)gevondenBoeken.get(0);
                System.out.println("Wat zou je willen aanpassen?");
                System.out.println("----------------------------");
                System.out.println("1. Naam");
                System.out.println("2. Genre");
                System.out.println("3. Jaar");
                System.out.println("4. Schrijver");
                System.out.println("5. Opmerking");
                System.out.println("----------------------------");
                int keuze = this.scanner.nextInt();
                this.scanner.nextLine();
                switch (keuze) {
                    case 1:
                        System.out.println("Voer de nieuwe naam in:");
                        String nieuweNaam = this.scanner.nextLine();
                        this.boekKast.updateBoek("Naam", teWijzigenBoek.getNaam(), nieuweNaam);
                        System.out.println("Naam succesvol gewijzigd.");
                        break;
                    case 2:
                        System.out.println("Voer het nieuwe genre in:");
                        String nieuwGenre = this.scanner.nextLine();
                        this.boekKast.updateBoek("Genre", String.join(",", teWijzigenBoek.getGenres()), nieuwGenre);
                        System.out.println("Genre succesvol gewijzigd.");
                        break;
                    case 3:
                        System.out.println("Voer het nieuwe jaar in:");
                        int nieuwJaar = this.scanner.nextInt();
                        this.scanner.nextLine();
                        this.boekKast.updateBoek("Jaar", Integer.toString(teWijzigenBoek.getJaar()), Integer.toString(nieuwJaar));
                        System.out.println("Jaar succesvol gewijzigd.");
                        break;
                    case 4:
                        System.out.println("Voer de nieuwe schrijver in:");
                        String nieuweSchrijver = this.scanner.nextLine();
                        this.boekKast.updateBoek("Schrijver", teWijzigenBoek.getSchrijver(), nieuweSchrijver);
                        System.out.println("Schrijver succesvol gewijzigd.");
                        break;
                    case 5:
                        System.out.println("Voer de nieuwe opmerking in:");
                        String nieuweOpmerking = this.scanner.nextLine();
                        this.boekKast.updateBoek("Opmerking", teWijzigenBoek.getOpmerking(), nieuweOpmerking);
                        System.out.println("Opmerking succesvol gewijzigd.");
                        break;
                    default:
                        System.out.println("Ongeldige keuze.");
                }
            }

        }
    }

    public void verwijderBoek() {
        System.out.print("Welk boek wil je verwijderen? Geef de naam: ");
        String naam = this.scanner.nextLine();
        System.out.println("Weet je zeker dat je dit boek wilt verwijderen: " + naam);
        System.out.println("Y/N");
        String vraag92 = this.scanner.nextLine();
        if (vraag92.equalsIgnoreCase("Y")) {
            System.out.println("Oké, het is verwijderd");
            this.boekKast.verwijderBoek(naam);
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
        int vraag21 = this.scanner.nextInt();
        this.scanner.nextLine();
        new ArrayList();
        List resultaten;
        switch (vraag21) {
            case 1:
                System.out.println("Type de naam:");
                String vraag211 = this.scanner.nextLine();
                resultaten = this.boekKast.zoekBoekenOpNaam(vraag211);
                break;
            case 2:
                System.out.println("Welke genre valt het onder (je kan meerdere nemen):");
                String vraag212 = this.scanner.nextLine();
                resultaten = this.boekKast.zoekBoekenOpGenre(vraag212);
                break;
            case 3:
                System.out.println("Uit welk jaar komt het boek:");
                int vraag213 = this.scanner.nextInt();
                this.scanner.nextLine();
                resultaten = this.boekKast.zoekBoekenOpJaar(vraag213);
                break;
            case 4:
                System.out.println("Weet je de schrijver:");
                String vraag214 = this.scanner.nextLine();
                resultaten = this.boekKast.zoekBoekenOpSchrijver(vraag214);
                break;
            case 5:
                System.out.println("Hier zijn alle gelezen boeken:");
                resultaten = this.boekKast.zoekBoekenOpGelezen(true);
                break;
            case 6:
                System.out.println("Hier zijn alle boeken die je nog niet gelezen hebt:");
                resultaten = this.boekKast.zoekBoekenOpGelezen(false);
                break;
            default:
                System.out.println("Ongeldige keuze.");
                return;
        }

        if (resultaten != null && !resultaten.isEmpty()) {
            Iterator var7 = resultaten.iterator();

            while(var7.hasNext()) {
                Boek boek = (Boek)var7.next();
                System.out.println("Naam: " + boek.getNaam());
                System.out.println("Genres: " + String.join(", ", boek.getGenres()));
                System.out.println("Jaar: " + boek.getJaar());
                System.out.println("Schrijver: " + boek.getSchrijver());
                System.out.println("Opmerking: " + boek.getOpmerking());
                System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
            }
        } else {
            System.out.println("Geen resultaten gevonden.");
        }

    }
}
