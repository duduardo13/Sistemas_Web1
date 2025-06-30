package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.*;

/**
 * @author Eduardo Barbosa E Vinicius Pontes
 */

public class ClientesDAO extends BaseDAO<Cliente> {     
    public ClientesDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
    	super(jdbcURL, jdbcUsername, jdbcPassword);
    }
     
	@Override
	public boolean insert(Cliente entity) throws SQLException {
		String sql = "INSERT INTO customer (customer_id, cust_name, city, grade, salesman_id) VALUES (?, ?, ?, ?, ?)";
	    connect();
	     
	    PreparedStatement statement = jdbcConnection.prepareStatement(sql);
	    statement.setInt(1, entity.getCustomerId());
	    statement.setString(2, entity.getCustName());
	    statement.setString(3, entity.getCity());
	    statement.setInt(4, entity.getGrade());
	    statement.setInt(5, entity.getSalesmanId());
	     
	    boolean rowInserted = statement.executeUpdate() > 0;
	    statement.close();
	    disconnect();
	    return rowInserted;
	}

	@Override
	public List<Cliente> listAll() throws SQLException {
	    List<Cliente> listCliente = new ArrayList<>();

	    String sql = "SELECT * FROM customer";

	    connect();

	    Statement statement = jdbcConnection.createStatement();
	    ResultSet resultSet = statement.executeQuery(sql);

	    while (resultSet.next()) {
	        int id = resultSet.getInt("customer_id");
	        String custName = resultSet.getString("cust_name");
	        String city = resultSet.getString("city");
	        int grade = resultSet.getInt("grade");
	        int salesmanId = resultSet.getInt("salesman_id");

	        Cliente cliente = new Cliente(id, custName, city, grade, salesmanId);
	        listCliente.add(cliente);
	    }

	    resultSet.close();
	    statement.close();

	    disconnect();

	    return listCliente;
	}

	@Override
	public boolean delete(Cliente entity) throws SQLException {
	    String sql = "DELETE FROM customer WHERE customer_id = ?";

	    connect();

	    PreparedStatement statement = jdbcConnection.prepareStatement(sql);
	    statement.setInt(1, entity.getCustomerId());

	    boolean rowDeleted = statement.executeUpdate() > 0;
	    statement.close();
	    disconnect();
	    return rowDeleted;
	}

	@Override
	public boolean update(Cliente entity) throws SQLException {
	    String sql = "UPDATE customer SET cust_name = ?, city = ?, grade = ?, salesman_id = ?";
	    sql += " WHERE customer_id = ?";
	    connect();

	    PreparedStatement statement = jdbcConnection.prepareStatement(sql);
	    statement.setString(1, entity.getCustName());
	    statement.setString(2, entity.getCity());
	    statement.setInt(3, entity.getGrade());
	    statement.setInt(4, entity.getSalesmanId());
	    statement.setInt(5, entity.getCustomerId());

	    boolean rowUpdated = statement.executeUpdate() > 0;
	    statement.close();
	    disconnect();
	    return rowUpdated;
	}

	@Override
	public Cliente getById(int id) throws SQLException {
	    Cliente cliente = null;
	    String sql = "SELECT * FROM customer WHERE customer_id = ?";

	    connect();

	    PreparedStatement statement = jdbcConnection.prepareStatement(sql);
	    statement.setInt(1, id);

	    ResultSet resultSet = statement.executeQuery();

	    if (resultSet.next()) {
	        String custName = resultSet.getString("cust_name");
	        String city = resultSet.getString("city");
	        int grade = resultSet.getInt("grade");
	        int salesmanId = resultSet.getInt("salesman_id");

	        cliente = new Cliente(id, custName, city, grade, salesmanId);
	    }

	    resultSet.close();
	    statement.close();
	    disconnect();
	    return cliente;
	}

}