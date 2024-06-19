package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static utils.DbConnection.getConnection;

public class CustomerDAOImpl implements CustomerDAO {

    private PreparedStatement ps;
    private final Connection con = getConnection();

    @Override
    public boolean insertCustomer(Customer customer) {

        String sql = "INSERT INTO customers (name, phoneNumber) values (?, ?);";

        try (Connection con = getConnection();
             PreparedStatement ps = Objects.requireNonNull(con).prepareStatement(sql)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getPhoneNumber());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
            throw new RuntimeException("Failed to insert customer", ex);
        }
    }

    @Override
    public boolean updateCustomer(Customer customer) {

        String sql = "UPDATE customers SET name = ?, phoneNumber = ? WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = Objects.requireNonNull(con).prepareStatement(sql)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getPhoneNumber());
            ps.setInt(3, customer.getId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
            throw new RuntimeException("Failed to update customer", ex);
        }
    }

    @Override
    public boolean deleteCustomer(int customerId) {

        String sql = "DELETE FROM customers WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = Objects.requireNonNull(con).prepareStatement(sql)) {
            ps.setInt(1, customerId);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
            throw new RuntimeException("Failed to delete customer", ex);
        }
    }

    @Override
    public ResultSet listCustomer(int customerId) {

        String sql = "SELECT id, name, phoneNumber FROM customers WHERE id = ?";

        try (Connection con = getConnection();
             PreparedStatement ps = Objects.requireNonNull(con).prepareStatement(sql)) {
            ps.setInt(1, customerId);
            return ps.executeQuery();
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
            throw new RuntimeException("Failed to list customer", ex);
        }
    }

    @Override
    public ResultSet listAllCustomers() {

        String sql = "SELECT id, name, phoneNumber FROM customers";

        try  {
            ps = Objects.requireNonNull(con).prepareStatement(sql);
            return ps.executeQuery();
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
            throw new RuntimeException("Failed to list all customers", ex);
        }
    }
}
