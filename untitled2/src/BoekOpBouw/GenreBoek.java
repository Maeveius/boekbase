package BoekOpBouw;

import java.util.Arrays;

public class GenreBoek {
    private String[] genres;

    public GenreBoek(String[] genres) {
        this.genres = genres;
    }

    public String[] getGenres() {
        return genres;
    }

    public void setGenres(String[] genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return Arrays.toString(genres);
    }
}