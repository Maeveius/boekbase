package BoekOpBouw;

public class CD extends SpeciaalBoek {
    public CD(boolean gelezen, String naam, String[] genres, int jaar, String schrijver,String speciaal , String opmerking) {
        super(gelezen, naam, genres, jaar, schrijver, speciaal, opmerking);
    }
    @Override
    public boolean isSpeciaalBoek() {
        return true;
    }
}
