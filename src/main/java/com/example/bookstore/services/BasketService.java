package com.example.bookstore.services;

import com.example.bookstore.core.BadRequestException;
import com.example.bookstore.core.NotFoundException;
import com.example.bookstore.model.Basket;
import com.example.bookstore.model.Order;

/**
 * Сервис по работе с корзиной текущего пользователя
 */
public interface BasketService {

    /**
     * Возвращает корзину текущего пользователя
     * 
     * @return корзину текущего пользователя
     */
    public Basket basket();

    /**
     * Добавляет книгу в корзину
     * 
     * @param bookId идентификатор книги
     * @param amount количество книг
     * @return обновленную корзину
     * @throws NotFoundException если книга по указанному идентификатору не найдена
     */
    public Basket addBook(long bookId, int amount) throws NotFoundException;

    /**
     * Очистить корзину
     */
    public void clearBasket();

    /**
     * Изменяет количество книг в корзине
     * 
     * @param bookId идентификатор книги
     * @param amount количество книг
     * @return обновленную корзину
     * @throws NotFoundException если книга по указанному идентификатору не найдена
     */
    public Basket changeBooksAmount(long bookId, int amount) throws NotFoundException;

    /**
     * Удаляет книгу из корзины
     * 
     * @param bookId идентификатор книги
     * @return обновленную корзину
     * @throws NotFoundException если книга по указанному идентификатору не найдена
     */
    public Basket removeBook(long bookId) throws NotFoundException;

    /**
     * Создает заказ из данных в корзине. Корзина после создания заказа очищается.
     * 
     * @return сформированный заказ
     * @throws BadRequestException если данные в корзине не корректны или она пуста
     */
    public Order submitOrder() throws BadRequestException;

}
