package com.example.bookstore.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id", "title"})
public class BookEntity {
    
    /**
     * Идентификатор книги
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Название книги
     */
    @Column(nullable = false, length = 255)
    private String title;

    /**
     * Список авторов
     */
    @ManyToMany(fetch = FetchType.EAGER)
    private List<AuthorEntity> authors;

    /**
     * Год издания
     */
    private int year;

    /**
     * Международный стандартный книжный номер
     */
    @Column(nullable = false, length = 17)
    private String ISBN;

    /**
     * Цена
     */
    private int price;

    /**
     * Описание книги
     */
    @Column(nullable = false, length = 2048)
    private String description;
}
