package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;

public class BasicDatabaseSave {
    public static void main(String[] args) {

        //konfiguracja hibernate
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Employee employee = new Employee();
        //employee.setId(); //increment
        employee.setName("Adam");
        employee.setSurname("Kowalski");
        employee.setJobTitle("Programmer");
        employee.setSalary(10000);

        session.save(employee);//zapisujemy do bazy danych
        transaction.commit();

        System.out.println("Employee saved!");
        sessionFactory.close();
        session.close();


            }
        }
