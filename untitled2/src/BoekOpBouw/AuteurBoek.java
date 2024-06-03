package BoekOpBouw;

public class AuteurBoek {
    private String auteur;
    private int geboortejaar;
    private String besteBoek;
    private String algemeneInformatie;

    public AuteurBoek(String alles) {
        String[] gegevens = alles.split(",");
        if (gegevens.length < 4) {
            this.auteur = gegevens.length > 0 ? gegevens[0] : "Onbekend";
            this.geboortejaar = gegevens.length > 1 ? Integer.parseInt(gegevens[1]) : 0;
            this.besteBoek = gegevens.length > 2 ? gegevens[2] : "Onbekend";
            this.algemeneInformatie = gegevens.length > 3 ? gegevens[3] : "Geen informatie";
        } else {
            this.auteur = gegevens[0];
            this.geboortejaar = Integer.parseInt(gegevens[1]);
            this.besteBoek = gegevens[2];
            this.algemeneInformatie = gegevens[3];
        }
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
