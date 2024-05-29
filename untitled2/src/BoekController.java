import BoekOpBouw.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoekController implements BoekErAf, BoekErBij, BoekUpdate, ZoekBoek {
    private ZoekBoek zoekBoek;
    private BoekErAf boekErAf;
    private BoekUpdate boekUpdate;
    private BoekErBij boekErBij;
    private Scanner scanner;
    private List<BoekKastObserver> observers;

    public BoekController(ZoekBoek zoekBoek, BoekErAf boekErAf, BoekUpdate boekUpdate, BoekErBij boekErBij) {
        this.zoekBoek = zoekBoek;
        this.boekErAf = boekErAf;
        this.boekUpdate = boekUpdate;
        this.boekErBij = boekErBij;
        this.scanner = new Scanner(System.in);
        this.observers = new ArrayList<>();
    }

    public void voegBoekToe() {
        System.out.println("Heb je een nieuw boek gelezen of wil je er nog 1 lezen?");
        System.out.println("1. Heb er 1 gelezen");
        System.out.println("2. Wil er 1 lezen");
        int gelezenInput = scanner.nextInt();
        scanner.nextLine();
        GelezenBoek gelezen = new GelezenBoek(gelezenInput == 1);

        System.out.print("Welk boek heb je gelezen of wil je lezen?: ");
        String naam = scanner.nextLine();
        TitelBoek titel = new TitelBoek(naam);

        System.out.println("Maar welke genre/genres heeft het?");
        String[] genresArray = scanner.nextLine().split(", ");
        GenreBoek genres = new GenreBoek(genresArray);

        System.out.print("Uit welk jaar komt het boek eigenlijk?: ");
        int jaarInput = scanner.nextInt();
        scanner.nextLine();
        JaarBoek jaar = new JaarBoek(jaarInput);

        System.out.print("En wie is de schrijver?: ");
        String schrijverNaam = scanner.nextLine();
        AuteurBoek auteur = new AuteurBoek(schrijverNaam);

        System.out.print("Wat dacht je eigenlijk over het boek?: ");
        String opmerkingText = scanner.nextLine();
        OpmerkingBoek opmerking = new OpmerkingBoek(opmerkingText);

        System.out.println("Is het een speciaal boek? (CD/Kookboek)");
        System.out.println("1. CD");
        System.out.println("2. Kookboek");
        System.out.println("3. Geen speciaal boek");
        int keuzeSpeciaal = scanner.nextInt();
        scanner.nextLine();

        Boek nieuwBoek;
        if (keuzeSpeciaal == 1) {
            nieuwBoek = new CD(gelezen, titel, genres, jaar, auteur, "CD", opmerking);
        } else if (keuzeSpeciaal == 2) {
            nieuwBoek = new KookBoeken(gelezen, titel, genres, jaar, auteur, "Kookboek", opmerking);
        } else {
            nieuwBoek = new Boek(gelezen, titel, genres, jaar, auteur, "niet speciaal", opmerking);
        }

        boekErBij.voegBoekToe(nieuwBoek);
        meldObservers();
        System.out.println("Boek succesvol toegevoegd aan de boekenkast.");
    }

    @Override
    public void verwijderBoek(String naam) {
        boekErAf.verwijderBoek(naam);
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
            boekErAf.verwijderBoek(naam);
            System.out.println("Oké, het is verwijderd");
        } else if (antwoord.equalsIgnoreCase("N")) {
            System.out.println("Dat dacht ik al");
        } else {
            System.out.println("Ongeldige invoer, stop.");
        }
    }

    @Override
    public void updateBoek(String criterium, String oudeWaarde, String nieuweWaarde) {
        boekUpdate.updateBoek(criterium, oudeWaarde, nieuweWaarde);
        meldObservers();
    }

    @Override
    public List<Boek> zoekOpAlles() {
        return zoekBoek.zoekOpAlles();
    }

    @Override
    public List<Boek> zoekBoekenOpGelezen(boolean gelezen) {
        return zoekBoek.zoekBoekenOpGelezen(gelezen);
    }

    @Override
    public List<Boek> zoekBoekenOpNaam(String naam) {
        return zoekBoek.zoekBoekenOpNaam(naam);
    }

    @Override
    public List<Boek> zoekBoekenOpGenre(String genre) {
        return zoekBoek.zoekBoekenOpGenre(genre);
    }

    @Override
    public List<Boek> zoekBoekenOpJaar(int jaar) {
        return zoekBoek.zoekBoekenOpJaar(jaar);
    }

    @Override
    public List<Boek> zoekBoekenOpSchrijver(String schrijver) {
        return zoekBoek.zoekBoekenOpSchrijver(schrijver);
    }

    @Override
    public List<Boek> zoekBoekenOpSpeciaal(String speciaal) {
        return zoekBoek.zoekBoekenOpSpeciaal(speciaal);
    }

    @Override
    public void voegBoekToe(Boek boek) {

    }

    @Override
    public void registreerObserver(BoekKastObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
            System.out.println("Observer succesvol geregistreerd.");
        } else {
            System.out.println("Observer is null of al geregistreerd. Registratie mislukt.");
        }
    }


    @Override
    public void verwijderObserver(BoekKastObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void meldObservers() {
        List<Boek> boeken = zoekOpAlles();
        for (BoekKastObserver observer : observers) {
            observer.update(boeken);
        }
    }
}
