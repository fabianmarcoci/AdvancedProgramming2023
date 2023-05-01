package lab8.compulsory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) {
        try {
            var artists = new ArtistDAO();
            artists.create("Pink Floyd");
            artists.create("Michael Jackson");
            var genres = new GenreDAO();
            genres.create("Rock");
            genres.create("Funk");
            genres.create("Soul");
            genres.create("Pop");
            genres.create("Dance");
            Database.getConnection().commit();
            var albums = new AlbumDAO();
            albums.create(1979, "The Wall", "Pink Floyd", "Rock");
            albums.create(1982, "Thriller", "Michael Jackson","Funk, Soul, Pop");
            albums.create(1987, "Bad", "Michael Jackson","Pop, Dance");
            Database.getConnection().commit();
            //check
            List<Album> albumsOfMJ = new ArrayList<>();
            albumsOfMJ = albums.findAll("artist", "Michael Jackson");
            for (int i = 0; i < albumsOfMJ.size(); i++) {
                System.out.println(albumsOfMJ.get(i));
            }
            Database.getConnection().close();
        } catch (SQLException e) {
            System.err.println(e);
            Database.rollback();
        }
    }
}
