package com.example.bookstore.model;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;

/**
 * Корзина
 */
@Component
@Scope("session")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Информация о книгах, помещенных в корзину")
public class Basket {

    /**
     * Список книг в корзине
     */
    @ApiModelProperty("список книг")
    private List<BasketItem> books = new LinkedList<>();

    @ApiModelProperty("суммарная стоимость добавленных в корзину книг")
    public int getTotal() {
        return books
                .stream()
                .mapToInt(b -> b.amount * b.book.getPrice())
                .sum();
    }

    /**
     * Книга в корзине
     */
    @Value
    @ApiModel(description = "Книга в корзине")
    public static class BasketItem {
        @ApiModelProperty("книга")
        private Book book;
        @ApiModelProperty("количество книг в корзине")
        private int amount;
    }
}
