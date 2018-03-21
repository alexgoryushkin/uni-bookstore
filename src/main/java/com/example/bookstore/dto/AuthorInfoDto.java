package com.example.bookstore.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Данные для создания/редактирования автора
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Данные для создания/редактирования автора")
public class AuthorInfoDto {

    /**
     * Имя автора
     */
    @ApiModelProperty(value = "Имя автора", required = true, example = "Сергей Александрович Есенин", position = 1)
    private String name;

    /**
     * Информация об авторе
     */
    @ApiModelProperty(value = "Краткая информация об авторе", required = true, example = "Русский поэт, представитель новокрестьянской поэзии и лирики, а в более позднем периоде творчества - имажинизма", position = 2)
    private String description;
}
