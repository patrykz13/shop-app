package computershop.database.view;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;

/**
 * Created by Kamil Cieślik on 06.01.2018.
 */

@Entity
@Immutable
@Table(name = "view_info_about_discounts")
public class InfoAboutDiscounts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "discount_code")
    private String discountCode;

    @Column(name = "account_id")
    private int accountId;

    @Column(name = "product_category")
    private String productCategory;

    @Column(name = "discount_percentage")
    private Integer discountPercentage;

    @Column(name = "is_used")
    private String isUsed;

    public InfoAboutDiscounts() {
    }

    public InfoAboutDiscounts(String discountCode, int accountId, String productCategory, Integer discountPercentage, String isUsed) {
        this.discountCode = discountCode;
        this.accountId = accountId;
        this.productCategory = productCategory;
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

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public Integer getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Integer discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }

    @Override
    public String toString() {
        return "InfoAboutDiscounts{" +
                "id=" + id +
                ", discountCode='" + discountCode + '\'' +
                ", accountId=" + accountId +
                ", productCategory='" + productCategory + '\'' +
                ", discountPercentage=" + discountPercentage +
                ", isUsed='" + isUsed + '\'' +
                '}';
    }
}
