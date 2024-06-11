import BoekOpBouw.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoekController implements BoekErAf, BoekErBij, BoekUpdate, ZoekBoek {
    private final ZoekBoek zoekBoek;
    private final BoekErAf boekErAf;
    private final BoekUpdate boekUpdate;
    private final BoekErBij boekErBij;
    private final Scanner scanner;
    private final List<BoekKastObserver> observers;

    public BoekController(ZoekBoek zoekBoek, BoekErAf boekErAf, BoekUpdate boekUpdate, BoekErBij boekErBij) {
        this.zoekBoek = zoekBoek;
        this.boekErAf = boekErAf;
        this.boekUpdate = boekUpdate;
        this.boekErBij = boekErBij;
        this.scanner = new Scanner(System.in);
        this.observers = new ArrayList<>();
    }

    public static boolean isValidBook(Boek boek) {
        if (boek.getTitel() == null || boek.getTitel().isEmpty() || boek.getTitel().length() > 255) {
            return false;
        }
        if (boek.getJaar() <= 0 || boek.getJaar() > 2025) {
            return false;
        }
        if (boek.getAuteurBoek() == null || boek.getAuteurBoek().getAuteur() == null || boek.getAuteurBoek().getAuteur().isEmpty()) {
            return false;
        }
        return true;
    }

    public void voegBoekToe() {
        Boek nieuwBoek = verzamelBoekGegevens();
        boekErBij.voegBoekToe(nieuwBoek);
        meldObservers();
        System.out.println("Boek succesvol toegevoegd aan de boekenkast.");
    }

    private Boek verzamelBoekGegevens() {
        boolean gelezen = vraagOfBoekGelezenIs();
        String titel = vraagOmBoekTitel();
        String[] genres = vraagOmGenres();
        int jaar = vraagOmJaar();
        AuteurBoek auteur = vraagOmAuteur();
        OpmerkingBoek opmerking = vraagOmOpmerking();
        Boek nieuwBoek = maakBoek(titel, genres, jaar, auteur, opmerking, gelezen);

        boekErBij.voegBoekToe(nieuwBoek);
        meldObservers();
        System.out.println("Boek succesvol toegevoegd aan de boekenkast.");
        return nieuwBoek;
    }

    private boolean vraagOfBoekGelezenIs() {
        System.out.println("Heb je een nieuw boek gelezen of wil je er nog 1 lezen?");
        System.out.println("1. Heb er 1 gelezen");
        System.out.println("2. Wil er 1 lezen");
        int gelezenInput = scanner.nextInt();
        scanner.nextLine();
        return gelezenInput == 1;
    }

    private String vraagOmBoekTitel() {
        System.out.print("Welk boek heb je gelezen of wil je lezen?: ");
        return scanner.nextLine();
    }

    private String[] vraagOmGenres() {
        System.out.println("Maar welke genre/genres heeft het?");
        return scanner.nextLine().split(", ");
    }

    private int vraagOmJaar() {
        System.out.print("Uit welk jaar komt het boek eigenlijk?: ");
        int jaarInput = scanner.nextInt();
        scanner.nextLine();
        return jaarInput;
    }

    private AuteurBoek vraagOmAuteur() {
        System.out.print("En wie is de schrijver?: ");
        String naam = scanner.nextLine();
        if (naam == null || naam.trim().isEmpty()) {
            throw new IllegalArgumentException("Auteur mag niet null of leeg zijn.");
        }
        System.out.println("Weet je nog meer over de schrijver (Y/N)");
        String antwoord = scanner.nextLine();

        if (antwoord.equalsIgnoreCase("Y")) {
            System.out.print("Geboortejaar van de schrijver?: ");
            int geboortejaar = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Wat is het beste boek van de schrijver?: ");
            String besteBoek = scanner.nextLine();
            System.out.print("Algemene informatie over de schrijver?: ");
            String algemeneInformatie = scanner.nextLine();
            return new AuteurBoek(naam, geboortejaar, besteBoek, algemeneInformatie);
        } else {
            return new AuteurBoek(naam, 0, "Onbekend", "Geen informatie");
        }
    }
    private OpmerkingBoek vraagOmOpmerking() {
        System.out.print("Wat dacht je eigenlijk over het boek?: ");
        String opmerkingText = scanner.nextLine();
        System.out.print("Meer details over de opmerking?: ");
        String details = scanner.nextLine();
        return new OpmerkingBoek(opmerkingText, details);
    }

    private Boek maakBoek(String titel, String[] genres, int jaar, AuteurBoek auteur, OpmerkingBoek opmerking, boolean gelezen) {
        System.out.println("Is het een speciaal boek? (CD/Kookboek)");
        System.out.println("1. CD");
        System.out.println("2. Kookboek");
        System.out.println("3. Geen speciaal boek");
        int keuzeSpeciaal = scanner.nextInt();
        scanner.nextLine();

        return switch (keuzeSpeciaal) {
            case 1 -> new CD(gelezen, titel, genres, jaar, auteur, "CD", opmerking);
            case 2 -> new KookBoeken(gelezen, titel, genres, jaar, auteur, "Kookboek", opmerking);
            default -> new Boek(gelezen, titel, genres, jaar, auteur, "niet speciaal", opmerking);
        };
    }

    @Override
    public void verwijderBoek(String naam) {
        boekErAf.verwijderBoek(naam);
        meldObservers();
        System.out.println("Boek succesvol verwijderd.");
    }

    @Override
    public void updateBoek(String criterium, String oudeWaarde, String nieuweWaarde) {
        if (criterium.equals("Schrijver")) {
            boekUpdate.updateBoek(criterium, oudeWaarde, nieuweWaarde);
        }
            if (oudeWaarde == null || nieuweWaarde == null) {
                throw new IllegalArgumentException("Auteur mag niet null zijn.");
            }
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
        boekErBij.voegBoekToe(boek);
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
