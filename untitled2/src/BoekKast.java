import java.util.List;

interface BoekKast {
    void voegBoekToe(Boek boek);
    List<Boek> zoekBoekenOpGelezen(boolean gelezen);
    List<Boek> zoekBoekenOpNaam(String naam);
    List<Boek> zoekBoekenOpGenre(String genre);
    List<Boek> zoekBoekenOpJaar(int jaar);
    List<Boek> zoekBoekenOpSchrijver(String schrijver);
    List<Boek> zoekOpAlles();
    void updateBoek(String criterium, String oudeWaarde, String nieuweWaarde);
    void verwijderBoek(String naam);
}
