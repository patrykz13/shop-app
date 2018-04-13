package computershop.database.view;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Kamil Cieślik on 06.01.2018.
 */

@Entity
@Immutable
@Table(name = "view_order_positions_of_order")
public class InfoAboutOrderPositions {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "name")
    private String productName;

    @Column(name = "barcode")
    private String barcode;

    @Column(name = "purchase_price_netto")
    private Float priceNetto;

    @Column(name = "purchase_price_brutto")
    private Float priceBrutto;

    @Column(name = "vat_rate")
    private Float vatRate;

    public InfoAboutOrderPositions() {
    }

    public InfoAboutOrderPositions(String id, int orderId, String productName, String barcode, Float priceNetto, Float priceBrutto, Float vatRate) {
        this.id = id;
        this.orderId = orderId;
        this.productName = productName;
        this.barcode = barcode;
        this.priceNetto = priceNetto;
        this.priceBrutto = priceBrutto;
        this.vatRate = vatRate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Float getPriceNetto() {
        return priceNetto;
    }

    public void setPriceNetto(Float priceNetto) {
        this.priceNetto = priceNetto;
    }

    public Float getPriceBrutto() {
        return priceBrutto;
    }

    public void setPriceBrutto(Float priceBrutto) {
        this.priceBrutto = priceBrutto;
    }

    public Float getVatRate() {
        return vatRate;
    }

    public void setVatRate(Float vatRate) {
        this.vatRate = vatRate;
    }

    @Override
    public String toString() {
        return "InfoAboutOrderPositions{" +
                "id='" + id + '\'' +
                ", orderId=" + orderId +
                ", productName='" + productName + '\'' +
                ", barcode='" + barcode + '\'' +
                ", priceNetto=" + priceNetto +
                ", priceBrutto=" + priceBrutto +
                ", vatRate=" + vatRate +
                '}';
    }
}
