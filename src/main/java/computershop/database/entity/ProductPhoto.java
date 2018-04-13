package computershop.database.entity;

import javax.persistence.*;

/**
 * Created by Kamil Cieślik on 30.11.2017.
 */

@Entity
@Table(name = "product_photo")
public class ProductPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "photo_path")
    private String photoPath;

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.DETACH,
            CascadeType.REFRESH})
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductPhoto() {
    }

    public ProductPhoto(String photoPath) {
        this.photoPath = photoPath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductPhoto{" +
                "id=" + id +
                ", photoPath='" + photoPath + '\'' +
                '}';
    }
}
