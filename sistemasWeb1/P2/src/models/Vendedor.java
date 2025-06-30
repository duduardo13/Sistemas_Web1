package models;

public class Vendedor {
	private int salesmanId;
	private String name;
	private String city;
	private double commission;
	
	/**
	 * @author Eduardo Barbosa E Vinicius Pontes
	 */

    public Vendedor() {
    }

    public Vendedor(int salesmanId) {
        this.salesmanId = salesmanId;
    }

    public Vendedor(int salesmanId, String name, String city, double commission) {
        this(name, city, commission);
        this.salesmanId = salesmanId;
    }

    public Vendedor(String name, String city, double commission) {
        this.name = name;
        this.city = city;
        this.commission = commission;
    }

    public int getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(int salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getCommission() {
        return commission;
    }

    public void setCommission(double commission) {
        this.commission = commission;
    }
}
