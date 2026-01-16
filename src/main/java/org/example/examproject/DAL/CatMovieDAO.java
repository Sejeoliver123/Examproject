package org.example.examproject.DAL;

import org.example.examproject.BE.Category;
import org.example.examproject.BE.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CatMovieDAO implements ICatMovie {
    @Override
    public List<Integer> getAllCatMovie() throws Exception {
        ArrayList<Integer> ints = new ArrayList<>();
        try(Connection conn = DBConnector.getStaticConnection(); Statement stmt = conn.createStatement()) {
            String sql = "Select * from CatMovie";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ints.add(rs.getInt("MovieId"));
                ints.add(rs.getInt("categoryId"));
            }
        }
        catch (Exception e) {
            throw new Exception("something went wrong getting all CatMovie",e);
        }
        return ints;
    }

    @Override
    public void createCatMovie(Movie movie, Category category) throws Exception {
        String sql = "INSERT INTO dbo.catMovie (MovieId,CategoryId) VALUES (?,?)";
        try (Connection conn = DBConnector.getStaticConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setInt(1,movie.getId());
            stmt.setInt(2,category.getId());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

        }
        catch (Exception e) {
            throw new Exception("something went wrong creating a new CatMovie",e );
        }
    }

    @Override
    public void deleteCatMovie(Movie movie, Category category) throws Exception {
        String sql = "delete from dbo.CatMovie where MovieId = ? and CategoryId = ?";
        try (Connection conn = DBConnector.getStaticConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, movie.getId());
            stmt.setInt(2,category.getId());
            stmt.executeUpdate();
        }
        catch (Exception e)
        {
            throw new Exception("something went wrong deleting the CatMovie", e);
        }
    }
}
