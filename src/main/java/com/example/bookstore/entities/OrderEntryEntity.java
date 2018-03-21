package com.example.bookstore.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class OrderEntryEntity {

    @EmbeddedId
    private OrderEntryId id = new OrderEntryId();

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "orderId"/*, insertable = false, updatable = false, nullable = false*/)
    private OrderEntity order;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "bookId"/*, insertable = false, updatable = false, nullable = false*/)
    private BookEntity book;

    private int price;

    private int amount;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    @Embeddable
    public static class OrderEntryId implements Serializable {
        private static final long serialVersionUID = -7220952260230685065L;
        private long orderId;
        private long bookId;
    }

}
