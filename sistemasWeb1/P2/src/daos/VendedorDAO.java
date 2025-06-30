package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Vendedor;

/**
 * @author Eduardo Barbosa E Vinicius Pontes
 */

public class VendedorDAO extends BaseDAO<Vendedor> {     
    public VendedorDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        super(jdbcURL, jdbcUsername, jdbcPassword);
    }
     
    @Override
    public boolean insert(Vendedor entity) throws SQLException {
        String sql = "INSERT INTO salesman (name, city, commission) VALUES (?, ?, ?)";
        connect();
        
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getCity());
        statement.setDouble(3, entity.getCommission());
        
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
 
    @Override
    public List<Vendedor> listAll() throws SQLException {
        List<Vendedor> listVendedor = new ArrayList<>();
         
        String sql = "SELECT * FROM salesman";
         
        connect();
         
        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
            int salesmanId = resultSet.getInt("salesman_id");
            String name = resultSet.getString("name");
            String city = resultSet.getString("city");
            double commission = resultSet.getDouble("commission");
             
            Vendedor vendedor = new Vendedor(salesmanId, name, city, commission);
            listVendedor.add(vendedor);
        }
         
        resultSet.close();
        statement.close();
         
        disconnect();
         
        return listVendedor;
    }
 
    @Override
    public boolean delete(Vendedor entity) throws SQLException {
        String sql = "DELETE FROM salesman WHERE salesman_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, entity.getSalesmanId());
         
        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }
 
    @Override
    public boolean update(Vendedor entity) throws SQLException {
        String sql = "UPDATE salesman SET name = ?, city = ?, commission = ? WHERE salesman_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getCity());
        statement.setDouble(3, entity.getCommission());
        statement.setInt(4, entity.getSalesmanId());
         
        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }
 
    @Override
    public Vendedor getById(int id) throws SQLException {
        Vendedor vendedor = null;
        String sql = "SELECT * FROM salesman WHERE salesman_id = ?";
         
        connect();
         
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);
         
        ResultSet resultSet = statement.executeQuery();
         
        if (resultSet.next()) {
            String name = resultSet.getString("name");
            String city = resultSet.getString("city");
            double commission = resultSet.getDouble("commission");
             
            vendedor = new Vendedor(id, name, city, commission);
        }
         
        resultSet.close();
        statement.close();
        disconnect();
        
        return vendedor;
    }
}
