package BoekOpBouw;

public class CD extends SpeciaalBoek {
    public CD(GelezenBoek gelezen, TitelBoek titel, GenreBoek genres, JaarBoek jaar, AuteurBoek auteur, String speciaal, OpmerkingBoek opmerking) {
        super(gelezen, titel, genres, jaar, auteur, speciaal, opmerking);
    }

    @Override
    public boolean isSpeciaalBoek() {
        return true;
    }

}
