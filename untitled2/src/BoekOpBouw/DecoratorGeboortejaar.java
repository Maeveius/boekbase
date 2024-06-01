package BoekOpBouw;

public class DecoratorGeboortejaar extends DecoratorBoek {
    public DecoratorGeboortejaar(ComponenteVanHetBoek boek) {
        super(boek);
    }

    @Override
    public String getAuteur() {
        if (boek instanceof Boek) {
            return super.getAuteur() + ", Geboortejaar: " + ((Boek) boek).getAuteurBoek().getGeboortejaar();
        }
        return super.getAuteur();
    }
}
