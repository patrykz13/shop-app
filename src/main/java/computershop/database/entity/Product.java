package computershop.database.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamil Cieślik on 30.11.2017.
 */

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "producer")
    private String producer;

    @Column(name = "model")
    private String model;

    @Column(name = "vat_rate")
    private Float vatRate;

    @Column(name = "selling_price_netto")
    private Float sellingPriceNetto;

    @Column(name = "selling_price_brutto")
    private Float sellingPriceBrutto;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "description")
    private String description;

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    @OneToMany(mappedBy ="product", cascade = CascadeType.ALL)
    private List<ProductPhoto> productPhotos;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    private List<OrderPosition> orderPositions;

    @ManyToMany(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinTable(
            name = "connector_set_details",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "set_id")
    )
    private List<ProductSet> productSets;

    public Product() {
    }

    public Product(String name, Float vatRate, Float sellingPriceNetto, Float sellingPriceBrutto, Integer amount) {
        this.name = name;
        this.vatRate = vatRate;
        this.sellingPriceNetto = sellingPriceNetto;
        this.sellingPriceBrutto = sellingPriceBrutto;
        this.amount = amount;
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

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Float getVatRate() {
        return vatRate;
    }

    public void setVatRate(Float vatRate) {
        this.vatRate = vatRate;
    }

    public Float getSellingPriceNetto() {
        return sellingPriceNetto;
    }

    public void setSellingPriceNetto(Float sellingPriceNetto) {
        this.sellingPriceNetto = sellingPriceNetto;
    }

    public Float getSellingPriceBrutto() {
        return sellingPriceBrutto;
    }

    public void setSellingPriceBrutto(Float sellingPriceBrutto) {
        this.sellingPriceBrutto = sellingPriceBrutto;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public List<ProductPhoto> getProductPhotos() {
        return productPhotos;
    }

    public void setProductPhotos(List<ProductPhoto> productPhotos) {
        this.productPhotos = productPhotos;
    }

    public List<OrderPosition> getOrderPositions() {
        return orderPositions;
    }

    public void setOrderPositions(List<OrderPosition> orderPositions) {
        this.orderPositions = orderPositions;
    }

    public List<ProductSet> getProductSets() {
        return productSets;
    }

    public void setProductSets(List<ProductSet> productSets) {
        this.productSets = productSets;
    }

    // add/delete functions

    public void addPhoto(ProductPhoto productPhoto){
        if (productPhotos==null)
            productPhotos = new ArrayList<>();

        productPhotos.add(productPhoto);
        productPhoto.setProduct(this);
    }

    public void addOrderPosition(OrderPosition orderPosition){
        if (orderPositions ==null)
            orderPositions = new ArrayList<>();

        orderPositions.add(orderPosition);
        orderPosition.setProduct(this);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", producer='" + producer + '\'' +
                ", model='" + model + '\'' +
                ", vatRate=" + vatRate +
                ", sellingPriceNetto=" + sellingPriceNetto +
                ", sellingPriceBrutto=" + sellingPriceBrutto +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", productCategory=" + productCategory +
                ", productPhotos=" + productPhotos +
                ", orderPositions=" + orderPositions +
                ", productSets=" + productSets +
                '}';
    }
}
