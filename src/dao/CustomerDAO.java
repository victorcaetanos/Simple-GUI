package dao;

import java.sql.ResultSet;

public interface CustomerDAO {

    boolean insertCustomer(Customer customer);

    boolean updateCustomer(Customer customer);

    boolean deleteCustomer(int customerId);

    ResultSet listCustomer(int customerId);

    ResultSet listAllCustomers();
}
