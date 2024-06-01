package BoekOpBouw;

public abstract class DecoratorBoek implements ComponenteVanHetBoek {
    protected ComponenteVanHetBoek boek;

    public DecoratorBoek(ComponenteVanHetBoek boek) {
        this.boek = boek;
    }

    @Override
    public boolean getGelezen() {
        return boek.getGelezen();
    }

    @Override
    public String getTitel() {
        return boek.getTitel();
    }

    @Override
    public String[] getGenres() {
        return boek.getGenres();
    }

    @Override
    public int getJaar() {
        return boek.getJaar();
    }

    @Override
    public String getAuteur() {
        return boek.getAuteur();
    }

    @Override
    public String getSpeciaal() {
        return boek.getSpeciaal();
    }

    @Override
    public String getOpmerking() {
        return boek.getOpmerking();
    }
}
