package models;

/**
 * =======================================================
 * Nomes da Dupla:
 * - Eduardo Barbosa e Vinicius Pontes
 * =======================================================
 */
public class CurrencyPreference {

    private String userId;
    private String currencyPair;

    public CurrencyPreference(String userId, String currencyPair) {
        this.userId = userId;
        this.currencyPair = currencyPair;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }
}