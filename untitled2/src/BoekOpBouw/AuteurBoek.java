package BoekOpBouw;

public class AuteurBoek {
    private String auteur;
    private int geboortejaar;
    private String besteBoek;
    private String algemeneInformatie;

    public AuteurBoek(String auteur, int geboortejaar, String besteBoek, String algemeneInformatie) {
        this.auteur = auteur;
        this.geboortejaar = geboortejaar;
        this.besteBoek = besteBoek;
        this.algemeneInformatie = algemeneInformatie;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public int getGeboortejaar() {
        return geboortejaar;
    }

    public void setGeboortejaar(int geboortejaar) {
        this.geboortejaar = geboortejaar;
    }

    public String getBesteBoek() {
        return besteBoek;
    }

    public void setBesteBoek(String besteBoek) {
        this.besteBoek = besteBoek;
    }

    public String getAlgemeneInformatie() {
        return algemeneInformatie;
    }

    public void setAlgemeneInformatie(String algemeneInformatie) {
        this.algemeneInformatie = algemeneInformatie;
    }

    @Override
    public String toString() {
        return auteur;
    }
}
