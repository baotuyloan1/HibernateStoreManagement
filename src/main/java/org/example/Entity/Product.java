package org.example.Entity;

import org.example.Entity.Category;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCTS_NEW")
public class Product {

    private long id;
    private String name;

    private String description;

    private Category category;

    private ProductDetail productDetail;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    public Category getCategory() {
        return category;
    }


    private float price;   public void setCategory(Category category) {
        this.category = category;
    }

    @Id
    @Column(name = "PRODUCT_ID")
    @GeneratedValue
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", productDetail=" + productDetail +
                ", price=" + price +
                '}';
    }
}
