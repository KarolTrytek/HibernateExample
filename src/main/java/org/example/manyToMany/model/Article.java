package org.example.manyToMany.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "articles")
public class Article {
    public Article() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    String title, content;

    @ManyToMany(cascade =  CascadeType.ALL)
    @JoinTable( //tworzymy tabele z relacja article i tag(definiujemy relacje)
            name = "articles_tags",//nazwa tabeli w ktorej sa trzymane id artykulu i taga
            joinColumns = @JoinColumn(name = "article_id"),//z perspektywy article
            inverseJoinColumns = @JoinColumn(name = "tag_id") //nazwa kolumny z perspektywy taga
    )
    private Set<Tag> tags = new HashSet<>();

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public void addTag(Tag tag) {
        tags.add(tag); //dodajemy taga do article
        tag.getArticles().add(this); //rowniez musimy dodac do przekazanego taga artykul, relacja z dwoch stron
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getArticles().remove(this);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                //", tags=" + tags + //w relacji MTM moze dojśc do zapetlenia
                '}';
    }
}
