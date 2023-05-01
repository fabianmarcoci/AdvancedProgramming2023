package lab8.compulsory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDAO {
    private static List<Genre> genres = new ArrayList<>();
    public void create(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "SELECT * FROM genres WHERE name = ?")) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // artist already exists, do nothing
                return;
            }
        }
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into genres (name) values (?)", Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    Genre genre = new Genre();
                    genre.setId(id);
                    genre.setName(name);
                    genres.add(genre);
                }
            }
        }
    }

    public List<Integer> findByName(String name) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from genres where name='" + name + "'")) {
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
                     "select name from genres where id='" + id + "'")) {
            return rs.next() ? rs.getString("name") : null;
        }
    }

    public <T> List<Genre> findAll(T element) throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement()) {
            String sqlQuery;
            if (element instanceof String) {
                sqlQuery = "select * from genres where name='" + element + "'";
            } else if (element instanceof Integer) {
                sqlQuery = "select * from genres where id=" + element;
            } else {
                throw new IllegalArgumentException("Invalid type for element: " + element.getClass().getSimpleName());
            }
            ResultSet rs = stmt.executeQuery(sqlQuery);
            {
                List<Genre> tempGenres = new ArrayList<>();

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    Genre genre = new Genre();
                    genre.setName(name);
                    genre.setId(id);
                    tempGenres.add(genre);
                }
                return !tempGenres.isEmpty() ? tempGenres : null;
            }
        }
    }

    public List<Genre> findAllElements() throws SQLException {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select * from genres")) {

            return !genres.isEmpty() ? genres : null;
        }
    }
}
