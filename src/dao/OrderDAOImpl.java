package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static utils.DbConnection.getConnection;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean insertOrder(Order order) {

        String sql = "INSERT INTO orders (customer_id) values (?);";

        try (Connection con = getConnection();
             PreparedStatement ps = Objects.requireNonNull(con).prepareStatement(sql)) {
            ps.setString(1, order.getCustomerId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
            throw new RuntimeException("Failed to insert order", ex);
        }
    }

    @Override
    public boolean updateOrder(Order order) {

        String sql = "UPDATE orders SET customer_id = ? WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = Objects.requireNonNull(con).prepareStatement(sql)) {
            ps.setString(1, order.getCustomerId());
            ps.setString(2, order.getId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
            throw new RuntimeException("Failed to update order", ex);
        }
    }

    @Override
    public boolean deleteOrder(Order order) {

        String sql = "DELETE FROM orders WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = Objects.requireNonNull(con).prepareStatement(sql)) {
            ps.setString(1, order.getId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
            throw new RuntimeException("Failed to delete order", ex);
        }
    }

    @Override
    public ResultSet listOrder(Order order) {

        String sql = "SELECT customer_id FROM orders WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = Objects.requireNonNull(con).prepareStatement(sql)) {
            ps.setString(1, order.getId());
            return ps.executeQuery();
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
            throw new RuntimeException("Failed to list order", ex);
        }
    }

    @Override
    public ResultSet listAllOrder() {

        String sql = "SELECT id, customer_id FROM orders";

        try (Connection con = getConnection();
             PreparedStatement ps = Objects.requireNonNull(con).prepareStatement(sql)) {
            return ps.executeQuery();
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
            throw new RuntimeException("Failed to list all orders", ex);
        }
    }
}
