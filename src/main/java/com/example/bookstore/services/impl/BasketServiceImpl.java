package com.example.bookstore.services.impl;

import java.text.MessageFormat;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.example.bookstore.core.BadRequestException;
import com.example.bookstore.core.NotFoundException;
import com.example.bookstore.core.SecurityUtils;
import com.example.bookstore.model.Basket;
import com.example.bookstore.model.Basket.BasketItem;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Order;
import com.example.bookstore.services.BasketService;
import com.example.bookstore.services.BookService;
import com.example.bookstore.services.OrderService;

@Service
@Scope("session")
public class BasketServiceImpl implements BasketService {

    @Autowired
    private Basket basket;

    @Autowired
    private BookService bookService;

    @Autowired
    private OrderService orderService;

    @Override
    public Basket basket() {
        return basket;
    }

    @Override
    public Basket addBook(long bookId, int amount) throws NotFoundException {
        Book book = bookService.getBook(bookId);
        basket.getBooks().add(new Basket.BasketItem(book, amount));
        return basket;
    }

    @Override
    public void clearBasket() {
        basket.getBooks().clear();
    }

    @Override
    public Basket changeBooksAmount(long bookId, int amount) throws NotFoundException {
        ListIterator<BasketItem> it = basket.getBooks().listIterator();
        boolean found = false;
        while (it.hasNext()) {
            BasketItem bitem = it.next();
            if (bitem.getBook().getId() == bookId) {
                if (amount > 0) {
                    it.set(new BasketItem(bitem.getBook(), amount));
                } else {
                    it.remove();
                }
                found = true;
                break;
            }
        }
        if (!found) {
            throw NotFoundException.build(MessageFormat.format("В корзине не найдено книги с идентификатором", bookId));
        }
        return basket;
    }

    @Override
    public Basket removeBook(long bookId) throws NotFoundException {
        ListIterator<BasketItem> it = basket.getBooks().listIterator();
        boolean found = false;
        while (it.hasNext()) {
            BasketItem bitem = it.next();
            if (bitem.getBook().getId() == bookId) {
                it.remove();
                found = true;
                break;
            }
        }
        if (!found) {
            throw NotFoundException.build(MessageFormat.format("В корзине не найдено книги с идентификатором", bookId));
        }
        return basket;
    }

    @Override
    public Order submitOrder() throws BadRequestException {
        Order order = orderService.createOrder(SecurityUtils.getCurrentUserId(), basket);
        clearBasket();
        return order;
    }
    
}
