package com.example.bookstore.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Данные для создания/редактирования книги
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Данные для создания/редактирования книги")
public class BookDto {

    /**
     * Название книги
     */
    @ApiModelProperty(value = "название книги", example = "Сумма технологии", required = true, position = 1)
    private String title;

    /**
     * Список авторов
     */
    @ApiModelProperty(value = "список авторов", required = true, position = 2)
    private List<Long> authors;

    /**
     * Год издания
     */
    @ApiModelProperty(value = "год издания", example = "2012", required = true, position = 3)
    private int year;

    /**
     * Международный стандартный книжный номер
     */
    @ApiModelProperty(value = "международный стандартный книжный номер", example = "978-5-17-074401-5", required = true, position = 4)
    private String isbn;

    /**
     * Цена
     */
    @ApiModelProperty(value = "цена в копейках", example = "46800", required = true, position = 5)
    private int price;

    /**
     * Описание книги
     */
    @ApiModelProperty(value = "описание книги", example = "\"Сумма технологии\" подвела итог классической эпохе исследования Будущего. В своей книге Станислав Лем провел уникальный и смелый технологический анализ цивилизаций. Он проанализировал возможности возникновения принципиально новых групп научных дисциплин и полностью отказался от простых экстраполяционных построений Будущего. Написанная почти сорок лет назад книга нисколько не устарела и является классикой футурологии.", required = true, position = 6)
    private String description;
}
