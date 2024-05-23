package BoekOpBouw;

import java.util.Arrays;

public class Boek {
    private boolean gelezen;
    private String naam;
    private String[] genres;
    private int jaar;
    private String schrijver;
    private String opmerking;
    private String speciaal;


    public Boek(boolean gelezen, String naam, String[] genres, int jaar, String schrijver,String speciaal ,String opmerking) {
        this.gelezen = gelezen;
        this.naam = naam;
        this.genres = genres;
        this.jaar = jaar;
        this.schrijver = schrijver;
        this.opmerking = opmerking;
        this.speciaal = speciaal;
    }
    public boolean isGelezen() {
        return gelezen;
    }
    public String getNaam() {
        return naam;
    }
    public String[] getGenres() {
        return genres;
    }
    public int getJaar() {
        return jaar;
    }
    public String getSchrijver() {
        return schrijver;
    }
    public String getOpmerking() {
        return opmerking;
    }
    public String getSpeciaal() { return speciaal;}
    public void setGelezen(boolean gelezen) {
        this.gelezen = gelezen;
    }
    public void setNaam(String naam) {
        this.naam = naam;
    }
    public void setGenres(String[] genres) {
        this.genres = genres;
    }
    public void setJaar(int jaar) {
        this.jaar = jaar;
    }
    public void setSchrijver(String schrijver) {
        this.schrijver = schrijver;
    }public void setOpmerking(String opmerking) {
        this.opmerking = opmerking;
    }
    public void setSpeciaal(String speciaal){this.speciaal = speciaal;}

    @Override
    public String toString() {
        return "Boek{" +
                "gelezen=" + gelezen +
                ", naam='" + naam + '\'' +
                ", genres=" + Arrays.toString(genres) +
                ", jaar=" + jaar +
                ", schrijver='" + schrijver + '\'' +
                ", opmerking='" + opmerking + '\'' +
                ", speciaal='" + speciaal + '\'' +
                '}';
    }
}