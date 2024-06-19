package dao;

import java.sql.ResultSet;

public interface OrderDAO  {

    public boolean insertOrder(Order order);

    public boolean updateOrder(Order order);

    public boolean deleteOrder(Order order);

    public ResultSet listOrder(Order order);

    public ResultSet listAllOrder();
}
