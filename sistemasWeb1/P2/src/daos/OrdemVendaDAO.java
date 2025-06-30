package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.OrdemVenda;

/**
 * @author Eduardo Barbosa E Vinicius Pontes
 */

public class OrdemVendaDAO extends BaseDAO<OrdemVenda> {
    public OrdemVendaDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        super(jdbcURL, jdbcUsername, jdbcPassword);
    }

    @Override
    public boolean insert(OrdemVenda entity) throws SQLException {
        String sql = "INSERT INTO orders (purch_amt, ord_date, customer_id, salesman_id) VALUES (?, ?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        
        statement.setDouble(1, entity.getPurchAmt());
        statement.setDate(2, new java.sql.Date(entity.getOrdDate().getTime()));
        statement.setInt(3, entity.getCustomerId());
        statement.setInt(4, entity.getSalesmanId());

        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowInserted;
    }
    @Override
    public List<OrdemVenda> listAll() throws SQLException {
        List<OrdemVenda> listOrdemVenda = new ArrayList<>();

        String sql = "SELECT * FROM orders";

        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int ordNo = resultSet.getInt("ord_no");
            double purchAmt = resultSet.getDouble("purch_amt");
            java.sql.Date ordDate = resultSet.getDate("ord_date");
            int customerId = resultSet.getInt("customer_id");
            int salesmanId = resultSet.getInt("salesman_id");

            OrdemVenda ordemVenda = new OrdemVenda(ordNo, purchAmt, ordDate, customerId, salesmanId);
            listOrdemVenda.add(ordemVenda);
        }

        resultSet.close();
        statement.close();

        disconnect();

        return listOrdemVenda;
    }

    @Override
    public boolean delete(OrdemVenda entity) throws SQLException {
        String sql = "DELETE FROM orders WHERE ord_no = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, entity.getOrdNo());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    @Override
    public boolean update(OrdemVenda entity) throws SQLException {
        String sql = "UPDATE orders SET purch_amt = ?, ord_date = ?, customer_id = ?, salesman_id = ?";
        sql += " WHERE ord_no = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setDouble(1, entity.getPurchAmt());
        statement.setDate(2, new java.sql.Date(entity.getOrdDate().getTime()));
        statement.setInt(3, entity.getCustomerId());
        statement.setInt(4, entity.getSalesmanId());
        statement.setInt(5, entity.getOrdNo());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    @Override
    public OrdemVenda getById(int id) throws SQLException {
        OrdemVenda ordemVenda = null;
        String sql = "SELECT * FROM orders WHERE ord_no = ?";

        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            double purchAmt = resultSet.getDouble("purch_amt");
            java.sql.Date ordDate = resultSet.getDate("ord_date");
            int customerId = resultSet.getInt("customer_id");
            int salesmanId = resultSet.getInt("salesman_id");

            ordemVenda = new OrdemVenda(id, purchAmt, ordDate, customerId, salesmanId);
        }

        resultSet.close();
        statement.close();
        disconnect();
        return ordemVenda;
    }
}
