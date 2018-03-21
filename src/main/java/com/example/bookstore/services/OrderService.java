package com.example.bookstore.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.bookstore.core.BadRequestException;
import com.example.bookstore.core.NotFoundException;
import com.example.bookstore.model.Basket;
import com.example.bookstore.model.Order;

/**
 * Сервис по работе с заказами
 */
public interface OrderService {

    /**
     * Получить список заказов
     * 
     * @param userId идентификатор пользователя
     * @param pageable постраничная навигация
     * @return список заказов
     */
    public Page<Order> getOrders(long userId, Pageable pageable);

    /**
     * Получить заказ по идентификатору
     * 
     * @param userId идентификатор пользователя, сделавший заказ
     * @param id идентификатор заказа
     * @return заказ
     * @throws NotFoundException если заказ не найден
     */
    public Order getOrder(long userId, long id) throws NotFoundException;

    /**
     * Создает заказ из данных в корзине пользователя
     * 
     * @param userId идентификатор пользователя
     * @param basket корзина, сформированная пользователем
     * @return сформированный заказ
     * @throws BadRequestException если данные в корзине не корректны или она пуста
     */
    public Order createOrder(long userId, Basket basket) throws BadRequestException;

}
