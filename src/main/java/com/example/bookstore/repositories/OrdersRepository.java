package com.example.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.bookstore.entities.OrderEntity;

/**
 * Репозиторий заказов
 */
@Repository
public interface OrdersRepository extends JpaRepository<OrderEntity, Long>, JpaSpecificationExecutor<OrderEntity> {

    /**
     * Найти заказ пользователя по номеру
     * 
     * @param userId идентификатор пользователя
     * @param orderId идентификатор заказа
     * @return заказ
     */
    public OrderEntity findByUserIdAndId(long userId, long orderId);
}
