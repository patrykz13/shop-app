package computershop.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamil Cieślik on 30.11.2017.
 */

@Entity
@Table(name = "stationary_shop")
public class StationaryShop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "stationaryShop", cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private List<Order> orders;

    @OneToMany(mappedBy = "stationaryShop", cascade = CascadeType.ALL)
    private List<Employee> employees;

    public StationaryShop() {
    }

    public StationaryShop(String name, Address address) {
        this.name = name;
        this.address = address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    // add/delete functions

    public void addEmployee(Employee employee) {
        if (employees == null)
            employees = new ArrayList<>();

        employees.add(employee);
        employee.setStationaryShop(this);
    }

    @Override
    public String toString() {
        return "StationaryShop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
