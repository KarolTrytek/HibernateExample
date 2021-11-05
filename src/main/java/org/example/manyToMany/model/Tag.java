package org.example.manyToMany.model;

import org.example.manyToMany.ArticlesExample;

import javax.persistence.*;
import java.util.HashSet;
import java.util.*;

@Entity
@Table(name = "tags")
public class Tag {
    public Tag() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "tags") //wskazujemy ze encja tag jest zalezna od encji article
    private java.util.Set<Article> articles = new HashSet<>();

    public Tag(String name) {
        this.name = name;
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

    public Set<Article> getArticles() {
        return articles;
    }

    public void setArticles(Set<Article> articles) {
        this.articles = articles;
    }


    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
