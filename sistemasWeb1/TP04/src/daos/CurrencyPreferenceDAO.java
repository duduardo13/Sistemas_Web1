package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.CurrencyPreference; 
/**
 * =======================================================
 * Nomes da Dupla:
 * - Eduardo Barbosa e Vinicius Pontes
 * =======================================================
 */
public class CurrencyPreferenceDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public CurrencyPreferenceDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

   
    protected void connect() throws SQLException {
        if (jdbcConnection == null || jdbcConnection.isClosed()) {
            try {
               
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException("Driver JDBC do MySQL n�o encontrado!", e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }

   
    protected void disconnect() throws SQLException {
        if (jdbcConnection != null && !jdbcConnection.isClosed()) {
            jdbcConnection.close();
        }
    }

    
    public boolean savePreference(CurrencyPreference preference) throws SQLException {
        String sql = "INSERT INTO preferences (user_id, currency_pair) VALUES (?, ?) ON DUPLICATE KEY UPDATE currency_pair = ?";
        connect();
        
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, preference.getUserId());
        statement.setString(2, preference.getCurrencyPair());
        statement.setString(3, preference.getCurrencyPair());

        boolean rowAffected = statement.executeUpdate() > 0;
        
        statement.close();
        disconnect();
        
        return rowAffected;
    }

  
    public CurrencyPreference getPreference(String userId) throws SQLException {
        CurrencyPreference preference = null;
        String sql = "SELECT * FROM preferences WHERE user_id = ?";
        connect();
        
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, userId);
        
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            String currencyPair = resultSet.getString("currency_pair");
            preference = new CurrencyPreference(userId, currencyPair);
        }
        
        resultSet.close();
        statement.close();
        disconnect();
        
        return preference;
    }
    
    public boolean deletePreference(String userId) throws SQLException {
        String sql = "DELETE FROM preferences WHERE user_id = ?";
        connect();
        
        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, userId);
        
        boolean rowDeleted = statement.executeUpdate() > 0;
        
        statement.close();
        disconnect();
        
        return rowDeleted;
    }
}