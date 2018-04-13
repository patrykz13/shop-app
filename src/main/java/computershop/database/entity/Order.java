package computershop.database.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Kamil Cieślik on 30.11.2017.
 */

@Entity
@Table(name = "torder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "promotion_price")
    private Float promotion_price;

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinColumn(name = "stationary_shop_id")
    private StationaryShop stationaryShop;

    @OneToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderPosition> orderPositions;

    public Order() {
    }

    public Order(Float promotion_price, Customer customer) {
        this.promotion_price = promotion_price;
        this.customer = customer;
    }

    public Order(Customer customer) {
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Float getPromotion_price() {
        return promotion_price;
    }

    public void setPromotion_price(Float promotion_price) {
        this.promotion_price = promotion_price;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public StationaryShop getStationaryShop() {
        return stationaryShop;
    }

    public void setStationaryShop(StationaryShop stationaryShop) {
        this.stationaryShop = stationaryShop;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public List<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(List<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", promotion_price=" + promotion_price +
                '}';
    }
}
