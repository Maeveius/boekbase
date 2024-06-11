package BoekOpBouw;

public class Boek implements ComponenteVanHetBoek {
    private final boolean gelezen;
    private final String titel;
    private final String[] genres;
    private final int jaar;
    private final AuteurBoek auteur;
    private final String speciaal;
    private final OpmerkingBoek opmerking;

    public Boek(boolean gelezen, String titel, String[] genres, int jaar, AuteurBoek auteur, String speciaal, OpmerkingBoek opmerking) {

        this.gelezen = gelezen;
        this.titel = titel;
        this.genres = genres;
        this.jaar = jaar;
        this.auteur = auteur;
        this.speciaal = speciaal;
        this.opmerking = opmerking;
    }

    public AuteurBoek getAuteurBoek() {
        return auteur;
    }

    public OpmerkingBoek getOpmerkingBoek() {
        return opmerking;
    }

    @Override
    public boolean getGelezen() {
        return gelezen;
    }

    @Override
    public String getTitel() {
        return titel;
    }

    @Override
    public String[] getGenres() {
        return genres;
    }

    @Override
    public int getJaar() {
        return jaar;
    }

    @Override
    public String getAuteur() {
        return auteur.getAuteur();
    }

    @Override
    public String getSpeciaal() {
        return speciaal;
    }

    @Override
    public String getOpmerking() {
        return opmerking.getOpmerking();
    }
}
