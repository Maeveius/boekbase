public interface BoekErAf {
    void verwijderBoek(String naam);
    void registreerObserver(BoekKastObserver observer);
    void meldObservers();
}
