public class KookBoeken extends SpeciaalBoek {
    public KookBoeken(boolean gelezen, String naam, String[] genres, int jaar, String schrijver, String opmerking) {
        super(gelezen, naam, genres, jaar, schrijver, opmerking);
    }

    @Override
    public boolean isSpeciaalBoek() {
        return true; 
    }
}
