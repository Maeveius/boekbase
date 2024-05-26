package BoekOpBouw;

public abstract class SpeciaalBoek extends Boek {
    public SpeciaalBoek(GelezenBoek gelezen, TitelBoek titel, GenreBoek genres, JaarBoek jaar, AuteurBoek auteur,String speciaal, OpmerkingBoek opmerking) {
        super(gelezen, titel, genres, jaar, auteur, speciaal, opmerking);
    }

    public abstract boolean isSpeciaalBoek();
}
