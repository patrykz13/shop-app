package computershop.database.view;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Kamil Cieślik on 06.01.2018.
 */

@Entity
@Immutable
@Table(name = "view_order_details")
public class InfoAboutOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "date")
    private Date date;

    @Column(name = "stationary_shop_name")
    private String stationaryShop;

    @Column(name = "standard_order_price")
    private Float price;

    @Column(name = "discount_code")
    private String discountCode;

    @Column(name = "promotion_order_price")
    private Float promotionPrice;

    @Column(name = "customer_id")
    private int customer_id;

    public InfoAboutOrder() {
    }

    public InfoAboutOrder(Date date, String stationaryShop, Float price, String discountCode, Float promotionPrice, int customer_id) {
        this.date = date;
        this.stationaryShop = stationaryShop;
        this.price = price;
        this.discountCode = discountCode;
        this.promotionPrice = promotionPrice;
        this.customer_id = customer_id;
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

    public String getStationaryShop() {
        return stationaryShop;
    }

    public void setStationaryShop(String stationaryShop) {
        this.stationaryShop = stationaryShop;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public Float getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(Float promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    @Override
    public String toString() {
        return "InfoAboutOrder{" +
                "id=" + id +
                ", date=" + date +
                ", stationaryShop='" + stationaryShop + '\'' +
                ", price=" + price +
                ", discountCode='" + discountCode + '\'' +
                ", promotionPrice=" + promotionPrice +
                ", customer_id=" + customer_id +
                '}';
    }
}
