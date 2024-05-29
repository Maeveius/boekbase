public interface BoekUpdate {
    void updateBoek(String criterium, String oudeWaarde, String nieuweWaarde);
    void registreerObserver(BoekKastObserver observer);
    void verwijderObserver(BoekKastObserver observer);
    void meldObservers();
}
