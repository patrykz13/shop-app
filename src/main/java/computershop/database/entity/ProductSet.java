package computershop.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamil Cieślik on 30.11.2017.
 */

@Entity
@Table(name = "product_set")
public class ProductSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToMany(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinTable(
            name = "connector_set_details",
            joinColumns = @JoinColumn(name = "set_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    public ProductSet() {
    }

    public ProductSet(String name) {
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
    }


    @Override
    public String toString() {
        return "ProductSet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
