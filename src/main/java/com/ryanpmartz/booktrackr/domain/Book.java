package com.ryanpmartz.booktrackr.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "book")
public class Book extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @NotEmpty
    @Column(nullable = false)
    private String title;

    @NotEmpty
    @Column(nullable = false)
    private String author;

    @Column(columnDefinition = "TEXT")
    private String notes;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
