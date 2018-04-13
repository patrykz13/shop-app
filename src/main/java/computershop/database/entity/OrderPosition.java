package computershop.database.entity;

import javax.persistence.*;

/**
 * Created by Kamil Cieślik on 30.11.2017.
 */

@Entity
@Table(name = "order_position")
public class OrderPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "purchase_price_brutto")
    private Float purchasePriceBrutto;

    @Column(name = "purchase_price_netto")
    private Float purchasePriceNetto;

    @Column(name = "vat_rate")
    private Float vatRate;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderPosition() {
    }

    public OrderPosition(String name, String barcode, Float purchasePriceBrutto, Float purchasePriceNetto, Float vatRate) {
        this.name = name;
        this.barcode = barcode;
        this.purchasePriceBrutto = purchasePriceBrutto;
        this.purchasePriceNetto = purchasePriceNetto;
        this.vatRate = vatRate;
    }

    public OrderPosition(Product product) {
        this.product = product;
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

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Float getPurchasePriceBrutto() {
        return purchasePriceBrutto;
    }

    public void setPurchasePriceBrutto(Float purchasePriceBrutto) {
        this.purchasePriceBrutto = purchasePriceBrutto;
    }

    public Float getPurchasePriceNetto() {
        return purchasePriceNetto;
    }

    public void setPurchasePriceNetto(Float purchasePriceNetto) {
        this.purchasePriceNetto = purchasePriceNetto;
    }

    public Float getVatRate() {
        return vatRate;
    }

    public void setVatRate(Float vatRate) {
        this.vatRate = vatRate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "OrderPosition{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", barcode='" + barcode + '\'' +
                ", purchasePriceBrutto=" + purchasePriceBrutto +
                ", purchasePriceNetto=" + purchasePriceNetto +
                ", vatRate=" + vatRate +
                '}';
    }
}
