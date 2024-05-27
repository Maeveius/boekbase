package BoekOpBouw;


public class Boek {
    private GelezenBoek gelezen;
    private TitelBoek titel;
    private GenreBoek genres;
    private JaarBoek jaar;
    private AuteurBoek auteur;
    private String speciaal;
    private OpmerkingBoek opmerking;

    public Boek(GelezenBoek gelezen, TitelBoek titel, GenreBoek genres, JaarBoek jaar, AuteurBoek auteur, String speciaal, OpmerkingBoek opmerking) {
        this.gelezen = gelezen;
        this.titel = titel;
        this.genres = genres;
        this.jaar = jaar;
        this.auteur = auteur;
        this.speciaal = speciaal;
        this.opmerking = opmerking;
    }

    public GelezenBoek getGelezen() {
        return gelezen;
    }

    public TitelBoek getTitel() {
        return titel;
    }

    public GenreBoek getGenres() {
        return genres;
    }

    public JaarBoek getJaar() {
        return jaar;
    }

    public AuteurBoek getAuteur() {
        return auteur;
    }

    public String getSpeciaal() {
        return speciaal;
    }

    public OpmerkingBoek getOpmerking() {
        return opmerking;
    }

    @Override
    public String toString() {
        return "Boek{" +
                "gelezen=" + gelezen +
                ", titel=" + titel +
                ", genres=" + genres +
                ", jaar=" + jaar +
                ", auteur=" + auteur +
                ", speciaal='" + speciaal + '\'' +
                ", opmerking=" + opmerking +
                '}';
    }
}