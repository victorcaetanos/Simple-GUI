package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static utils.DbConnection.getConnection;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public boolean insertProduct(Product product) {

        String sql = "INSERT INTO products (name, price) values (?, ?);";

        try (Connection con = getConnection();
             PreparedStatement ps = Objects.requireNonNull(con).prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setFloat(2, product.getPrice());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
            throw new RuntimeException("Failed to insert product", ex);
        }
    }

    @Override
    public boolean updateProduct(Product product) {

        String sql = "UPDATE products SET name = ?, price = ? WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = Objects.requireNonNull(con).prepareStatement(sql)) {
            ps.setString(1, product.getName());
            ps.setFloat(2, product.getPrice());
            ps.setString(3, product.getId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
            throw new RuntimeException("Failed to update product", ex);
        }
    }

    @Override
    public boolean deleteProduct(Product product) {

        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = Objects.requireNonNull(con).prepareStatement(sql)) {
            ps.setString(1, product.getId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
            throw new RuntimeException("Failed to delete product", ex);
        }
    }

    @Override
    public ResultSet listProduct(Product product) {

        String sql = "SELECT id, name, price FROM products WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = Objects.requireNonNull(con).prepareStatement(sql)) {
            ps.setString(1, product.getId());
            return ps.executeQuery();
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
            throw new RuntimeException("Failed to list product", ex);
        }
    }

    @Override
    public ResultSet listAllProduct() {

        String sql = "SELECT id, name, price FROM products";

        try (Connection con = getConnection();
             PreparedStatement ps = Objects.requireNonNull(con).prepareStatement(sql)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
            throw new RuntimeException("Failed to list all products", ex);
        }
    }
}
