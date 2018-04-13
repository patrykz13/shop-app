package computershop.database.view;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;

/**
 * Created by Kamil Cieślik on 06.01.2018.
 */

@Entity
@Immutable
@Table(name = "view_set_details")
public class InfoAboutSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "set_name")
    private String setName;

    @Column(name = "total_price")
    private Float totalPrice;

    @Column(name = "set_patron")
    private String setPatron;

    public InfoAboutSet() {
    }

    public InfoAboutSet(String setName, Float totalPrice, String setPatron) {
        this.setName = setName;
        this.totalPrice = totalPrice;
        this.setPatron = setPatron;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSetName() {
        return setName;
    }

    public void setSetName(String setName) {
        this.setName = setName;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSetPatron() {
        return setPatron;
    }

    public void setSetPatron(String setPatron) {
        this.setPatron = setPatron;
    }

    @Override
    public String toString() {
        return "InfoAboutSet{" +
                "id=" + id +
                ", setName='" + setName + '\'' +
                ", totalPrice=" + totalPrice +
                ", setPatron='" + setPatron + '\'' +
                '}';
    }
}
