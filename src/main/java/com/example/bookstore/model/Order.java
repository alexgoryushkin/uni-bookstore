package com.example.bookstore.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.Value;

/**
 * Заказ
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
@ToString
@ApiModel(description = "Заказ")
public class Order {

    /**
     * Идентификатор заказа
     */
    @ApiModelProperty(notes = "идентификатор заказа", example = "1", required = true, position = 1)
    private long id;

    /**
     * Дата размещения заказа
     */
    @ApiModelProperty(notes = "дата размещения заказа", example = "2017-04-27 15:52:37", required = true, position = 2)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime date;

    /**
     * Список книг в заказе
     */
    @ApiModelProperty(notes = "список книг в заказе", required = true, position = 3)
    private List<OrderItem> books;

    /**
     * Суммарная стоимость всех книг в заказе в копейках
     * 
     * @return стоимость всех книг в заказе в копейках
     */
    @ApiModelProperty(notes = "суммарная стоимость всех книг в заказе в копейках", example = "156400", required = true, position = 4)
    public int getTotal() {
        return books
                .stream()
                .mapToInt(b -> b.amount * b.price)
                .sum();
    }

    /**
     * Книга в заказе
     */
    @Value
    @ApiModel(description = "книга в заказе")
    public static class OrderItem {

        /**
         * Книга
         */
        @ApiModelProperty(notes = "книга", required = true, position = 1)
        private Book book;

        /**
         * Цена на момент подтверждения заказа
         */
        @ApiModelProperty(notes = "цена в копейкох на момент заказа", example = "64800", required = true, position = 2)
        private int price;

        /**
         * Количество книг
         */
        @ApiModelProperty(notes = "количество книг в заказе", example = "1", required = true, position = 3)
        private int amount;
    }

}
