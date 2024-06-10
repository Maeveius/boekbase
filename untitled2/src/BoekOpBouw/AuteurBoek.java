package BoekOpBouw;

public class AuteurBoek {
    private String auteur;
    private int geboortejaar;
    private String besteBoek;
    private String algemeneInformatie;

    public AuteurBoek(String auteur) {
        this.auteur = auteur;
        this.geboortejaar = geboortejaar;
        this.besteBoek = besteBoek;
        this.algemeneInformatie = algemeneInformatie;
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
}