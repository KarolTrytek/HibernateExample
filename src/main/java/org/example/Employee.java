package org.example;

//POJO class - Persistent, mozemy zapisac w bazie danych

import javax.persistence.*;

//zapis za pomoca adnotacji, bez pliku hbm.xml
@Entity //wskazuje ze to bedzie klasa bedaca encja
@Table(name="employees")
public class Employee {

    @Id //adnotacja do id, klucz glowny encji
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto increment id
    private int id; //obligatory id
    private String name, surname, jobTitle,address; //wszystkie pola musza byc private
    private int age;
    @Column(name="salaryMonthly") //dodajemy adnotacje do encji, domyslnie nazywala by sie jak zmienna (salary)
    private int salary;

    //obligatory empty constructor
    public Employee() {
    }

    public Employee(int id, String name, String surname, String jobTitle,
                    String address, int age, int salary) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.jobTitle = jobTitle;
        this.address = address;
        this.age = age;
        this.salary = salary;
    }

    //zmienne sa private, wiec dostep do nich przez gettery and settery
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }
}
