import BoekOpBouw.Boek;

public interface BoekErBij {
    void voegBoekToe(Boek boek);
    void registreerObserver(BoekKastObserver observer);

    void meldObservers();
}
