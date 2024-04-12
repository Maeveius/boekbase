public class CD extends SpeciaalBoek {
    public CD(boolean gelezen, String naam, String[] genres, int jaar, String schrijver, String opmerking) {
        super(gelezen, naam, genres, jaar, schrijver, opmerking);
    }
    @Override
    public boolean isSpeciaalBoek() {
        return true;
    }
}
