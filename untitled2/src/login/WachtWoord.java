package login;

public class WachtWoord{
    private final String wachtwoord;

    public WachtWoord(String wachtwoord) {
        if (wachtwoord == null || wachtwoord.isEmpty()) {
            throw new IllegalArgumentException("Wachtwoord mag niet leeg zijn.");
        }
        this.wachtwoord = wachtwoord;
    }
    public String getWachtwoord() {return wachtwoord;}
}
