package com.example.bookstore.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Фильтр для выборки книг
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookFilterDto {

    /**
     * Строка поиска. Фильтрует книги по заголовку, описанию, ISBN
     */
    @ApiModelProperty(value = "строка поиска", example = "солярис", required = false, position = 1)
    private String search;

    /**
     * Идентификатор автора
     */
    @ApiModelProperty(value = "идентификатор автора", example = "4", required = false, position = 2)
    private Integer author;

    /**
     * Год издания
     */
    @ApiModelProperty(value = "год издания", example = "2014", required = false, position = 3)
    private Integer year;
}
