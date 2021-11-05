package org.example.lombok;

import org.example.Employee;
import org.example.lombok.model.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class LombokExample {
    public static void main(String[] args) {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Car car = new Car("Ford","Mustang",220);
        Integer carId = (Integer) session.save(car);
        System.out.println("Saved car in db id:" + carId);

        System.out.println("Car from db:" + session.get(Car.class,
                carId).objStrData());

        transaction.commit();
        sessionFactory.close();
        session.close();
    }
}
