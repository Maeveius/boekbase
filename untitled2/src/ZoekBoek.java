import BoekOpBouw.Boek;

import java.util.List;

public interface ZoekBoek {
    List<Boek> zoekBoekenOpGelezen(boolean gelezen);
    List<Boek> zoekBoekenOpNaam(String naam);
    List<Boek> zoekBoekenOpGenre(String genre);
    List<Boek> zoekBoekenOpJaar(int jaar);
    List<Boek> zoekBoekenOpSchrijver(String schrijver);
    List<Boek> zoekBoekenOpSpeciaal(String speciaal);
    List<Boek> zoekOpAlles();



    void registreerObserver(BoekKastObserver observer);
    void verwijderObserver(BoekKastObserver observer);
    void meldObservers();
}
