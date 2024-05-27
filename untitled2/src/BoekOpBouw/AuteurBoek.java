package BoekOpBouw;

public class AuteurBoek {
    private String auteur;

    public AuteurBoek(String auteur) {
        this.auteur = auteur;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    @Override
    public String toString() {
        return auteur;
    }
}