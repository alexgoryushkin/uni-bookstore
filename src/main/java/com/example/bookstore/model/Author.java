package com.example.bookstore.model;

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
 * Автор
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString(of = { "id", "name" })
@ApiModel(description = "Подробная информация об авторе")
public class Author {

    /**
     * Идентификатор автора
     */
    @ApiModelProperty(value = "идентификатор автора", example = "5", required = true, position = 1)
    private long id;

    /**
     * Имя автора
     */
    @ApiModelProperty(value = "имя автора", example = "Сергей Александрович Есенин", required = true, position = 2)
    private String name;

    /**
     * Информация об авторе
     */
    @ApiModelProperty(value = "краткая информация об авторе", example = "Русский поэт, представитель новокрестьянской поэзии и лирики, а в более позднем периоде творчества - имажинизма", required = true, position = 3)
    private String description;
}
