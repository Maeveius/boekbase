package login;

public interface AccountFactory {
    GebruikersNaam maakGebruikersNaam(String gebruikersNaam);
    WachtWoord maakWachtWoord(String wachtWoord);
}
