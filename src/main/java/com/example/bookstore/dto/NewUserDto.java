package com.example.bookstore.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Данные нового пользователя при регистрации
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Регистрационные данные пользователя")
public class NewUserDto {

    /**
     * Логин пользователя
     */
    @ApiModelProperty(value = "логин", example = "batarelkin", required = true, position = 1)
    private String login;

    /**
     * Пароль пользователя
     */
    @ApiModelProperty(value = "пароль", example = "Password1", required = true, position = 2)
    private String password;

    /**
     * Имя пользователя
     */
    @ApiModelProperty(value = "имя пользователя", example = "Тарелкин Борис", required = true, position = 3)
    private String name;

    /**
     * Почтовый ящик пользователя
     */
    @ApiModelProperty(value = "почтовый ящик", example = "tarelkinba@example.com", required = true, position = 4)
    private String email;
}
