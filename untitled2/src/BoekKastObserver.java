import BoekOpBouw.Boek;
import java.util.List;

public interface BoekKastObserver {
    void update(List<Boek> boeken);
}
