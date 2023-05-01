package lab8.compulsory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistDAO {
    private static List<Artist> artists = new ArrayList<>();

    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "SELECT * FROM artists WHERE name = ?")) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // artist already exists, do nothing
                return;
            }
        }
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into artists (name) values (?)", Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString( 1, name);
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    Artist artist = new Artist();
                    artist.setId(id);
                    artist.setName(name);
                    artists.add(artist);
                }
            }
        }
    }

    public List<Integer> findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from artists where name='" + name + "'")) {
            List<Integer> ids = new ArrayList<>();
            while (rs.next()) {
                ids.add(rs.getInt("id"));
            }
            return ids;
        }
    }

    public String findById(int id) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select name from artists where id='" + id + "'")) {
            return rs.next() ? rs.getString("name") : null;
        }
    }

    public <T> List<Artist> findAll(T element) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement()) {
            String sqlQuery;
            if (element instanceof String) {
                sqlQuery = "select * from artists where name='" + element + "'";
            } else if (element instanceof Integer) {
                sqlQuery = "select * from artists where id=" + element;
            } else {
                throw new IllegalArgumentException("Invalid type for element: " + element.getClass().getSimpleName());
            }
            ResultSet rs = stmt.executeQuery(sqlQuery);
            {
                List<Artist> tempArtists = new ArrayList<>();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    Artist artist = new Artist();
                    artist.setName(name);
                    artist.setId(id);
                    tempArtists.add(artist);
                }
                return !tempArtists.isEmpty() ? tempArtists : null;
            }
        }
    }

    public List<Artist> findAllElements() throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from artists")) {

            return !artists.isEmpty() ? artists : null;
        }
    }
}
