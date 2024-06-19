package dao;

public class Order {
    private String id;
    private String customerId;

    public Order() {
    }

    public Order(String id) {
        this.id = id;
    }

    public Order(String id, String customerId) {
        this.id = id;
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
