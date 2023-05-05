/*
 * Category.java
 * Copyright by Nguyen Duc Bao
 * Created on 5 - 5 - 2023 (mm-dd-yyyy)
 */

package org.example.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CATEGORIES")
public class Category {
    private long id;
    private String name;
    private Set<Product> products;

    private Category parent;

    private Set<Category> children = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "idParent")
    public Category getParent() {
        return parent;
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    public Set<Category> getChildren() {
        return children;
    }

    public void setChildren(Set<Category> children) {
        this.children = children;
    }

    @Id
    @Column(name = "CATEGORY_ID")
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

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    public void addChild(Category child){
        this.children.add(child);
    }

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, Category parent) {
        this.name = name;
        this.parent = parent;
    }
}
