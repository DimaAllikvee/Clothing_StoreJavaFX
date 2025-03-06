package ee.ivkhkdev.Clothing_StoreJavaFX.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Clothing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private String size;
    private String color;
    private double price;
    private int quantity;
    private int inStock;

    public Clothing() {}


    public Clothing(String name, String brand, String size, String color, double price, int quantity, int inStock) {
        this.name = name;
        this.brand = brand;
        this.size = size;
        this.color = color;
        this.price = price;
        this.quantity = quantity;
        this.inStock = inStock;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String type) {
        this.brand = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getInStock() {
        return inStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }



    @Override
    public String toString() {
        return "Clothing{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + brand + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", inStock=" + inStock +
                '}';
    }


}

