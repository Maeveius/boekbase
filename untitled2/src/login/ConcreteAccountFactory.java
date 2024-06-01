package login;

public class ConcreteAccountFactory implements AccountFactory {
    @Override
    public GebruikersNaam maakGebruikersNaam(String gebruikersNaam) {
        return new GebruikersNaam(gebruikersNaam);
    }

    @Override
    public WachtWoord maakWachtWoord(String wachtWoord) {
        return new WachtWoord(wachtWoord);
    }
}
