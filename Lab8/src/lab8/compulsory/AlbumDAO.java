package lab8.compulsory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {
    private static List<Album> albums = new ArrayList<>();

    public void create(int year, String name, String artist, String genre) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "SELECT * FROM albums WHERE name = ?")) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // artist already exists, do nothing
                return;
            }
        }
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into albums (year, name, artist, genre) values (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, year);
            pstmt.setString(2, name);
            pstmt.setString(3, artist);
            pstmt.setString(4, genre);
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    Album album = new Album();
                    album.setId(id);
                    album.setYear(year);
                    album.setName(name);
                    album.setArtist(artist);
                    album.setGenre(genre);
                    albums.add(album);
                }
            }
        }
    }

    public <T> List<Album> findAll(String elementType, T element) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement()) {
            String sqlQuery;
            if (elementType.equals("name")) {
                sqlQuery = "select * from albums where name='" + element + "'";
            } else if (elementType.equals("id")) {
                sqlQuery = "select * from albums where id='" + element + "'";
            } else if (elementType.equals("year")) {
                sqlQuery = "select * from albums where year='" + element + "'";
            } else if (elementType.equals("artist")) {
                sqlQuery = "select * from albums where artist='" + element + "'";
            } else if (elementType.equals("genre")) {
                sqlQuery = "select * from albums where genre='" + element + "'";
            } else {
                throw new IllegalArgumentException("Invalid type for element: " + element.getClass().getSimpleName());
            }
            ResultSet rs = stmt.executeQuery(sqlQuery);
            {
                List<Album> tempAlbums = new ArrayList<>();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    int year = rs.getInt("year");
                    String name = rs.getString("name");
                    String artist = rs.getString("artist");
                    String genre = rs.getString("genre");
                    Album album = new Album();
                    album.setId(id);
                    album.setYear(year);
                    album.setName(name);
                    album.setArtist(artist);
                    album.setGenre(genre);
                    tempAlbums.add(album);
                }
                return !tempAlbums.isEmpty() ? tempAlbums : null;
            }
        }
    }

    public List<Album> findAllElements() throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from albums")) {

            return !albums.isEmpty() ? albums : null;
        }
    }
}
