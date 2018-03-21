package com.example.bookstore.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Книга
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = { "id", "title" })
@ApiModel(description = "Книга")
public class Book {

    /**
     * Идентификатор книги
     */
    @ApiModelProperty(notes = "идентификатор книги", example = "1", required = true, position = 1)
    private long id;

    /**
     * Название книги
     */
    @ApiModelProperty(value = "название книги", example = "Сумма технологии", required = true, position = 2)
    private String title;

    /**
     * Список авторов
     */
    @ApiModelProperty(value = "список авторов", required = true, position = 3)
    private List<Author> authors;

    /**
     * Год издания
     */
    @ApiModelProperty(value = "год издания", example = "2012", required = true, position = 4)
    private int year;

    /**
     * Международный стандартный книжный номер
     */
    @ApiModelProperty(value = "международный стандартный книжный номер", example = "978-5-17-074401-5", required = true, position = 5)
    private String isbn;

    /**
     * Цена
     */
    @ApiModelProperty(value = "цена в копейках", example = "46800", required = true, position = 6)
    private int price;

    /**
     * Описание книги
     */
    @ApiModelProperty(value = "описание книги", example = "\"Сумма технологии\" подвела итог классической эпохе исследования Будущего. В своей книге Станислав Лем провел уникальный и смелый технологический анализ цивилизаций. Он проанализировал возможности возникновения принципиально новых групп научных дисциплин и полностью отказался от простых экстраполяционных построений Будущего. Написанная почти сорок лет назад книга нисколько не устарела и является классикой футурологии.", required = true, position = 7)
    private String description;
}
