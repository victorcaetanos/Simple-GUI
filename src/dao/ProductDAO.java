package dao;

import java.sql.ResultSet;


public interface ProductDAO {

    public boolean insertProduct(Product product);

    public boolean updateProduct(Product product);

    public boolean deleteProduct(Product product);

    public ResultSet listProduct(Product product);

    public ResultSet listAllProduct();
}
