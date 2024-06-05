public interface BoekUpdate {
    void updateBoek(String criterium, String oudeWaarde, String nieuweWaarde);
    void registreerObserver(BoekKastObserver observer);

    void meldObservers();
}
