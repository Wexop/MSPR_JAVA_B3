package fr.mspr_java_b3.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer topic_id;
    private Integer user_id;
    private String message;
    private LocalDateTime date;

    public Messages() {
    }

    public Messages(Integer topic_id, Integer user_id, String message, LocalDateTime date) {
        this.topic_id = topic_id;
        this.user_id = user_id;
        this.message = message;
        this.date = date;
    }

    public Messages(int id, Integer topic_id, Integer user_id, String message, LocalDateTime date) {
        this.id = id;
        this.topic_id = topic_id;
        this.user_id = user_id;
        this.message = message;
        this.date = date;
    }

    public Integer getTopic_id() {
        return topic_id;
    }

    public void setTopic_id(Integer topic_id) {
        this.topic_id = topic_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
