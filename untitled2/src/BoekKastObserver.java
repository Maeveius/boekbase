import BoekOpBouw.Boek;
import java.util.List;

public interface BoekKastObserver {
    void update(List<Boek> boeken);

    boolean contains(BoekKastObserver observer);

    void add(BoekKastObserver observer);

    void remove(BoekKastObserver observer);
}
