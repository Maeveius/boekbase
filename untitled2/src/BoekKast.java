import java.util.List;

public interface BoekKast {
    void voegBoekToe(Boek boek);
    List<Boek> zoekBoekenOpGelezen(boolean gelezen);
    List<Boek> zoekBoekenOpNaam(String naam);
    List<Boek> zoekBoekenOpGenre(String genre);
    List<Boek> zoekBoekenOpJaar(int jaar);
    List<Boek> zoekBoekenOpSchrijver(String schrijver);
    List<Boek> zoekBoekenOpSpeciaal(String speciaal);
    List<Boek> zoekOpAlles();
    void updateBoek(String criterium, String oudeWaarde, String nieuweWaarde);
    void verwijderBoek(String naam);

    void registreerObserver(BoekKastObserver observer);
    void verwijderObserver(BoekKastObserver observer);
    void meldObservers();
}
