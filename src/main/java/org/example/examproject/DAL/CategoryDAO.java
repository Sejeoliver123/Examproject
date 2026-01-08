package org.example.examproject.DAL;

import org.example.examproject.BE.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements ICategory {

    @Override
    public List<Category> getAllCategory() throws Exception {
        ArrayList<Category> categories = new ArrayList<>();
        try(Connection conn = DBConnector.getStaticConnection(); Statement stmt = conn.createStatement()) {
            String sql = "Select * from Catefory";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                categories.add(new Category(id,name));
            }
        }
        catch (Exception e) {
            throw new Exception("something went wrong getting all category",e);
        }
        return  categories;
    }
    @Override
    public Category createCategory(Category category) throws Exception {
        String sql = "INSERT INTO dbo.category (Name) VALUES (?)";
        try (Connection conn = DBConnector.getStaticConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1,category.getName());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            return new Category(id, category.getName());
        }
        catch (Exception e) {
            throw new Exception("something went wrong creating a new category",e );
        }
    }

    @Override
    public void updateCategory(Category category) throws Exception {
        String sql = "update dbo.Category set Name=? where id = ?";
        try (Connection conn = DBConnector.getStaticConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,category.getName());
            stmt.setInt(2, category.getId());
            stmt.executeUpdate();
        }
        catch (Exception e)
        {
            throw new Exception("something went wrong update the category",e);
        }
    }

    @Override
    public void deleteCategory(Category category) throws Exception {
        String sql = "delete from dbo.Category where id = ?";
        try (Connection conn = DBConnector.getStaticConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, category.getId());
            stmt.executeUpdate();
        }
        catch (Exception e)
        {
            throw new Exception("something went wrong deleting the category", e);
        }
    }
}
