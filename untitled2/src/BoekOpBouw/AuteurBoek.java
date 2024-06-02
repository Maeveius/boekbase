package BoekOpBouw;

public class AuteurBoek {
    private String auteur;
    private int geboortejaar;
    private String besteBoek;
    private String algemeneInformatie;

    public AuteurBoek(String alles) {
        String[] gegevens = alles.split(",");
        if (gegevens.length < 4) {
            throw new IllegalArgumentException("Onvoldoende gegevens om AuteurBoek aan te maken. Verwacht: 4, Ontvangen: " + gegevens.length);
        }
        this.auteur = gegevens[0];
        this.geboortejaar = Integer.parseInt(gegevens[1]);
        this.besteBoek = gegevens[2];
        this.algemeneInformatie = gegevens[3];
    }

    public String getAuteur() {
        return auteur;
    }

    public int getGeboortejaar() {
        return geboortejaar;
    }

    public String getBesteBoek() {
        return besteBoek;
    }

    public String getAlgemeneInformatie() {
        return algemeneInformatie;
    }

    @Override
    public String toString() {
        return String.format("%s,%d,%s,%s", auteur, geboortejaar, besteBoek, algemeneInformatie);
    }
}
