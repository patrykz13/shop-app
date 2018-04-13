package computershop.database.entity;

import javax.persistence.*;

/**
 * Created by Kamil Cieślik on 30.11.2017.
 */

@Entity
@Table(name = "discount")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "discount_code")
    private String discountCode;

    @Column(name = "discount_percentage")
    private Integer discountPercentage;

    @Column(name = "is_used", columnDefinition = "INT(1)")
    private Boolean isUsed;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH})
    @JoinColumn(name = "product_category_id")
    private ProductCategory productCategory;

    @OneToOne(mappedBy = "discount", cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    private Order order;

    public Discount() {
    }

    public Discount(String discountCode, Integer discountPercentage, Boolean isUsed) {
        this.discountCode = discountCode;
        this.discountPercentage = discountPercentage;
        this.isUsed = isUsed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", discountCode='" + discountCode + '\'' +
                ", discountPercentage=" + discountPercentage +
                ", isUsed=" + isUsed +
                '}';
    }
}
