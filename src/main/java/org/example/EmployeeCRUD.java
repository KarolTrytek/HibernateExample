package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.internal.compile.CriteriaQueryTypeQueryAdapter;

import java.util.List;

public class EmployeeCRUD {
    private SessionFactory sessionFactory;

    public EmployeeCRUD() {
        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();

        Metadata metadata = new MetadataSources(ssr).getMetadataBuilder().build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
    }

    public Integer saveEmployee(Employee employee) {
        Session session = null;
        Integer id = null;
        try {
            session = sessionFactory.openSession();//uchwyt do sesji
            Transaction transaction = session.beginTransaction();
            id = (Integer) session.save(employee);//zapisuje nowy rekord do bazy danych
            transaction.commit();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return id;
    }

    public Employee getEmployee(Integer id) {
        Session session = null;
        Employee employee = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            String hql = "FROM Employee E WHERE E.id = " + id;//tworzymy alias E, kod zapytania hibernate
            Query query = session.createQuery(hql);//wyslanie zapytania

            List results = query.list(); //rezultat w postaci listy

            if(results.size()>0) employee = (Employee) results.get(0);

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employee;
    }

    public List getEmployees () {
        Session session = null;
        List employees = null;
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            String hql = "FROM Employee";
            Query query = session.createQuery(hql);//wyslanie zapytania
            employees = query.list(); //rezultat w postaci listy



        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employees;
    }

    public void updateEmployee(Integer id, String name) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Employee employee = (Employee) session.get(Employee.class, id);//pobranie elementu
            employee.setName(name); //ustawiamy nowe imie z konstruktora

            session.update(employee);//aktualizujemy baze danych o zmodyfikowany obiekt

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction != null) transaction.rollback(); //powrot do domyslnej wartosci jesli jakis blad
        } finally {
            session.close();
        }
    }

        public void deleteEmployee (Integer id) {
            Session session = null;
            Transaction transaction = null;
            try {
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();

                Employee employee = (Employee) session.get(Employee.class, id);//pobranie elementu po id

                session.delete(employee);//usuniecie elementu z bazy danych
                transaction.commit();
            }catch (Exception e) {
                e.printStackTrace();

            } finally {
                session.close();
            }

    }

    public void run(){
        Employee employee = new Employee(0,"Adam","Kowalska","programmer",
                "Warsow",30,10000);
        Integer newId = saveEmployee(employee);
        System.out.println("zapisany rekord, id: " + newId);

        Employee savedEmployee = getEmployee(newId);
        System.out.println("\nostatni dodany: " + savedEmployee);

        updateEmployee(newId,"Pawel");

        List<Employee> results = getEmployees();
        System.out.println("\n results: " + results.size());
        for (int i = 0; i <results.size(); i++) {
            System.out.println("Pracownik: " + i + ": " + results.get(i));
        }
            System.out.println("\nSkasowanie pracownika o id: " + results.get(0).getId());
            deleteEmployee(results.get(0).getId());//kasujemy pierwszy rezultat z listy

        System.out.println("\nstan po skadowaniu: ");
        results = getEmployees();
        System.out.println("\n results: " + results.size());
        for (int i = 0; i <results.size(); i++) {
            System.out.println("Pracownik: " + i + ": " + results.get(i));
        }
    }



    public static void main(String[] args) {



        EmployeeCRUD employeeCRUD = new EmployeeCRUD();
        employeeCRUD.run();
    }
}
