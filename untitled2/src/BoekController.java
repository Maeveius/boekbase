import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoekController extends CSVBoekKast{
    private BoekKast boekKast;
    private Scanner scanner;
    private List<BoekKastObserver> observers;

    public BoekController(BoekKast boekKast) {
        super("untitled2\\src\\data.csv");
        this.boekKast = boekKast;
        this.scanner = new Scanner(System.in);
        this.observers = new ArrayList<>();
    }

    public void voegBoekToe() {
        System.out.println("Heb je een nieuw boek gelezen of wil je er nog 1 lezen?");
        System.out.println("1. Heb er 1 gelezen");
        System.out.println("2. Wil er 1 lezen");
        int gelezen = scanner.nextInt();
        scanner.nextLine();
        boolean gelezen1 = (gelezen == 1);

        System.out.print("Welk boek heb je gelezen of wil je lezen?: ");
        String naam = scanner.nextLine();

        System.out.println("Maar welke genre/genres heeft het?");
        String[] genres = scanner.nextLine().split(", ");

        System.out.print("Uit welk jaar komt het boek eigenlijk?: ");
        int jaar = scanner.nextInt();
        scanner.nextLine();

        System.out.print("En wie is de schrijver?: ");
        String schrijver = scanner.nextLine();

        System.out.print("Wat dacht je eigenlijk over het boek?: ");
        String opmerking = scanner.nextLine();

        System.out.println("Is het een speciaal boek? (CD/Kookboek)");
        System.out.println("1. CD");
        System.out.println("2. Kookboek");
        System.out.println("3. Geen speciaal boek");
        int keuzeSpeciaal = scanner.nextInt();
        scanner.nextLine();

        Boek nieuwBoek;
        if (keuzeSpeciaal == 1) {
            String speciaal1 = "CD";
            nieuwBoek = new CD(gelezen1, naam, genres, jaar, schrijver, speciaal1, opmerking);
        } else if (keuzeSpeciaal == 2) {
            String speciaal2 = "Kookboek";
            nieuwBoek = new KookBoeken(gelezen1, naam, genres, jaar, schrijver, speciaal2, opmerking);
        } else {
            String nietspeciaal = "niet speciaal";
            nieuwBoek = new Boek(gelezen1, naam, genres, jaar, schrijver, nietspeciaal, opmerking);
        }

        boekKast.voegBoekToe(nieuwBoek);
        meldObservers();
        System.out.println("Boek succesvol toegevoegd aan de boekenkast.");
    }


    public void verwijderBoek() {
        System.out.print("Welk boek wil je verwijderen? Geef de naam: ");
        String naam = scanner.nextLine();

        System.out.println("Weet je zeker dat je dit boek wilt verwijderen: " + naam);
        System.out.println("Y/N");
        String antwoord = scanner.nextLine();

        if (antwoord.equalsIgnoreCase("Y")) {
            boekKast.verwijderBoek(naam);
            System.out.println("Oké, het is verwijderd");
        } else if (antwoord.equalsIgnoreCase("N")) {
            System.out.println("Dat dacht ik al");
        } else {
            System.out.println("Ongeldige invoer, stop.");
        }
        meldObservers();
    }
    public void registreerObserver(BoekKastObserver observer) {
        if (observer != null) {
            observers.add(observer);
            System.out.println("Observer succesvol geregistreerd.");
        } else {
            System.out.println("Observer is null. Registratie mislukt.");
        }
    }

    public void verwijderObserver(BoekKastObserver observer) {
        observers.remove(observer);
    }

    public void meldObservers() {
        List<Boek> boeken = boekKast.zoekOpAlles();
        for (BoekKastObserver observer : observers) {
            observer.update(boeken);
        }

}}