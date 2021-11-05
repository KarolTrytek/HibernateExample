package org.example.oneToMany.model;

import javax.persistence.*;
import java.util.List;

//encja Question w bazie danych
@Entity
@Table(name = "questions")
public class Question {
    public Question() {
    }
    public Question(String question) {
        this.question = question;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String question;

    //definiujemy relacje OneToMany - jedno pytanie moze miec wiele odpowiediz
    @OneToMany(cascade = CascadeType.ALL) //cascadeType.All - jesli skasujemy pytanie zostana skasowane wszystkie odpowiedzi
    @JoinColumn(name = "question_id") //klucz obcy do pytania, bedzie automatycznie dodany jako kolumna do tabeli answers
    private List<Answer> answers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answers=" + answers +
                '}';
    }
}
