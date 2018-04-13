package computershop.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamil Cieślik on 30.11.2017.
 */

@Entity
@Table(name = "product_category")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL)
    private List<Product> products;

    @OneToMany(mappedBy = "productCategory", cascade = CascadeType.ALL)
    private List<Discount> discounts;

    public ProductCategory() {
    }

    public ProductCategory(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    // add/delete functions

    public void addProduct(Product product) {
        if (products == null)
            products = new ArrayList<>();

        products.add(product);
        product.setProductCategory(this);
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    @Override
    public String toString() {
        return "ProductCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
