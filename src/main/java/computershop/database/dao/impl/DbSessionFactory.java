package computershop.database.dao.impl;

import computershop.database.entity.*;
import computershop.database.view.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Kamil Cieślik on 03.12.2017.
 */
public abstract class DbSessionFactory {
    SessionFactory factory;

    DbSessionFactory(){
        System.out.println("Stworzenie fabryki.");
        factory = new Configuration()
                .configure()
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Address.class)
                .addAnnotatedClass(Account.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(StationaryShop.class)
                .addAnnotatedClass(Discount.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(ProductCategory.class)
                .addAnnotatedClass(ProductPhoto.class)
                .addAnnotatedClass(Employee.class)
                .addAnnotatedClass(OrderPosition.class)
                .addAnnotatedClass(ProductSet.class)
                .addAnnotatedClass(InfoAboutStationaryShop.class)
                .addAnnotatedClass(InfoAboutSet.class)
                .addAnnotatedClass(InfoAboutDiscounts.class)
                .addAnnotatedClass(InfoAboutOrder.class)
                .addAnnotatedClass(InfoAboutOrderPositions.class)
                .buildSessionFactory();
    }

    @Override
    public void finalize(){
        System.out.println("Zamknięcie fabryki.");
        factory.close();
    }
}
