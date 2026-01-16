package org.example.examproject.DAL;

import org.example.examproject.BE.Category;
import org.example.examproject.BE.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO implements IMovie {
    @Override
    public List<Movie> getAllMovies() throws Exception {
        ArrayList<Movie> movies = new ArrayList<>();
        try(Connection conn = DBConnector.getStaticConnection(); Statement stmt = conn.createStatement()) {
            String sql = "select * from movie";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                if(rs.getDate("lastview") != null && rs.getFloat("personalrating") != 0.0){
                    movies.add(new Movie(rs.getInt("id"), rs.getString("name"),rs.getFloat("IMDBRating"), rs.getString("filelink"),rs.getFloat("personalrating"),rs.getDate("lastview")));
                }
                else if (rs.getFloat("personalrating") != 0.0) {
                    movies.add(new Movie( rs.getInt("id"), rs.getString("name"), rs.getFloat("IMDBRating"), rs.getString("filelink"), rs.getFloat("personalrating") ));
                }
                else if (rs.getDate("lastview") != null) {
                    movies.add(new Movie( rs.getInt("id"), rs.getString("name"), rs.getFloat("IMDBRating"), rs.getString("filelink"), rs.getDate("lastview") ));
                }
                else {
                    movies.add(new Movie( rs.getInt("id"), rs.getString("name"), rs.getFloat("IMDBRating"), rs.getString("filelink") ));
                }
            }
        }
        catch (Exception e) {
            throw new Exception("something went wrong getting all movies",e);
        }
        return movies;
    }

    @Override
    public Movie createMovie(Movie movie) throws Exception {
        int contentType = 0;
        String sql;
        if(movie.getPersonalRating() != 0 && movie.getLastView() != null) {
            sql = "INSERT INTO dbo.movie (name,IMDBRating,filelink,personalrating,lastview) VALUES (?,?,?,?,?)";
            contentType = 1;
        }
        else if(movie.getPersonalRating() != 0) {
            sql = "INSERT INTO dbo.movie (name,IMDBRating,filelink,personalrating) VALUES (?,?,?,?)";
            contentType = 2;
        }
        else if(movie.getLastView() != null) {
            sql = "INSERT INTO dbo.movie (name,IMDBRating,filelink,lastview) VALUES (?,?,?,?)";
            contentType = 3;
        }
        else {
            sql = "INSERT INTO dbo.movie (name,IMDBRating,filelink) VALUES (?,?,?)";
        }
        try(Connection conn = DBConnector.getStaticConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,movie.getName());
            stmt.setFloat(2,movie.getIMDBRating());
            stmt.setString(3, movie.getFileLink());
            switch (contentType) {
                case 1:
                    stmt.setFloat(4,movie.getPersonalRating());
                    stmt.setDate(5, new java.sql.Date(movie.getLastView().getTime()));
                    break;
                case 2:
                    stmt.setFloat(4,movie.getPersonalRating());
                    break;
                case 3:
                    stmt.setDate(4, new java.sql.Date(movie.getLastView().getTime()));
                    break;
                default:
                    break;
            }
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            switch (contentType) {
                case 1:
                    return new Movie(id, movie.getName(), movie.getIMDBRating(),movie.getFileLink(),movie.getPersonalRating(),movie.getLastView());
                case 2:
                    return new Movie(id, movie.getName(), movie.getIMDBRating(),movie.getFileLink(),movie.getPersonalRating());
                case 3:
                    return new Movie(id, movie.getName(), movie.getIMDBRating(),movie.getFileLink(),movie.getLastView());
                default:
                    return new Movie(id, movie.getName(), movie.getIMDBRating(),movie.getFileLink());
            }
        }
        catch (Exception e) {
            throw new Exception("something went wrong creating a new movie",e );
        }
    }

    @Override
    public void updateMovie(Movie movie) throws Exception {
        String sql;
        if(movie.getLastView() != null && movie.getPersonalRating() != 0) {
            sql = "update dbo.movie set name=?, IMDBRating=?, filelink=?, lastview=?, personalrating=? where id = ?";
        }
        else if(movie.getLastView() != null) {
            sql = "update dbo.movie set name=?, IMDBRating=?, filelink=?, lastview=? where id = ?";
        }
        else if (movie.getPersonalRating() != 0) {
            sql = "update dbo.movie set name=?, IMDBRating=?, filelink=?, personalrating=? where id = ?";
        }
        else {
            sql = "update dbo.movie set name=?, IMDBRating=?, filelink=? where id = ?";
        }
        try (Connection conn = DBConnector.getStaticConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if(movie.getLastView() != null && movie.getPersonalRating() != 0) {
                stmt.setString(1,movie.getName());
                stmt.setFloat(2,movie.getIMDBRating());
                stmt.setString(3, movie.getFileLink());
                stmt.setFloat(4,movie.getPersonalRating());
                stmt.setDate(5, new java.sql.Date(movie.getLastView().getTime()));
                stmt.setInt(6,movie.getId());
            }
            else if(movie.getLastView() != null) {
                stmt.setString(1,movie.getName());
                stmt.setFloat(2,movie.getIMDBRating());
                stmt.setString(3, movie.getFileLink());
                stmt.setDate(4, new java.sql.Date(movie.getLastView().getTime()));
                stmt.setInt(5,movie.getId());
            }
            else if (movie.getPersonalRating() != 0) {
                stmt.setString(1,movie.getName());
                stmt.setFloat(2,movie.getIMDBRating());
                stmt.setString(3, movie.getFileLink());
                stmt.setFloat(4,movie.getPersonalRating());
                stmt.setInt(5,movie.getId());
            }
            else {
                stmt.setString(1,movie.getName());
                stmt.setFloat(2,movie.getIMDBRating());
                stmt.setString(3, movie.getFileLink());
                stmt.setInt(4,movie.getId());
            }
            stmt.executeUpdate();
        }
        catch (Exception e)
        {
            throw new Exception("something went wrong update the movie",e);
        }
    }

    @Override
    public void deleteMovie(Movie movie) throws Exception {
        String sql = "delete from dbo.movie where id = ?";
        try (Connection conn = DBConnector.getStaticConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, movie.getId());
            stmt.executeUpdate();
        }
        catch (Exception e)
        {
            throw new Exception("something went wrong deleting the movie", e);
        }
    }
}
