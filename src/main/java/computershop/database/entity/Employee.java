package computershop.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamil Cieślik on 30.11.2017.
 */

@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<ProductSet> productSets;

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinColumn(name = "place_of_work_id")
    private StationaryShop stationaryShop;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<ProductSet> getProductSets() {
        return productSets;
    }

    public void setProductSets(List<ProductSet> productSets) {
        this.productSets = productSets;
    }

    public StationaryShop getStationaryShop() {
        return stationaryShop;
    }

    public void setStationaryShop(StationaryShop stationaryShop) {
        this.stationaryShop = stationaryShop;
    }

    // add/delete functions

    public void addProductSet(ProductSet productSet) {
        if (productSets == null)
            productSets = new ArrayList<>();

        productSets.add(productSet);
        productSet.setEmployee(this);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
