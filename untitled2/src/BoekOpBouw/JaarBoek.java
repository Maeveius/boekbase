package BoekOpBouw;

public class JaarBoek {
    private int jaar;

    public JaarBoek(int jaar) {
        this.jaar = jaar;
    }

    public int getJaar() {
        return jaar;
    }

    public void setJaar(int jaar) {
        this.jaar = jaar;
    }

    @Override
    public String toString() {
        return "JaarBoek{" +
                "jaar=" + jaar +
                '}';
    }
}