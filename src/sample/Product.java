package sample;

import java.math.BigDecimal;

public class Product {

    private static int count = 0;
    private int id;
    private String name;
    private BigDecimal price;
    private int qty;

    public Product() {
        this.name = "";
        this.price = BigDecimal.ZERO;
        this.qty = 0;
    }

    public Product(String name, BigDecimal price, int qty) {
        this.id = count + 1;
        this.name = name;
        this.price = price;
        this.qty = qty;
        count++;
    }

    public Product(int id ,String name, BigDecimal price, int qty) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                '}';
    }
}
