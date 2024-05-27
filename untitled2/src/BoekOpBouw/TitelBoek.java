package BoekOpBouw;

public class TitelBoek {
    private String titel;

    public TitelBoek(String titel) {
        this.titel = titel;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    @Override
    public String toString() {
        return titel;
    }
}