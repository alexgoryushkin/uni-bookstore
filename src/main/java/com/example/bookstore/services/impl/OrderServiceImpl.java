package com.example.bookstore.services.impl;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookstore.core.BadRequestException;
import com.example.bookstore.core.NotFoundException;
import com.example.bookstore.entities.BookEntity;
import com.example.bookstore.entities.OrderEntity;
import com.example.bookstore.entities.OrderEntryEntity;
import com.example.bookstore.entities.UserEntity;
import com.example.bookstore.model.Basket;
import com.example.bookstore.model.Order;
import com.example.bookstore.repositories.BooksRepository;
import com.example.bookstore.repositories.OrdersRepository;
import com.example.bookstore.repositories.UsersRepository;
import com.example.bookstore.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BooksRepository booksRepository;

    @Override
    public Page<Order> getOrders(long userId, Pageable pageable) {
        Page<OrderEntity> orders = ordersRepository.findAll(buildSpecification(userId), pageable);
        List<Order> elements = orders.getContent().stream().map(EntityModelMapper::toOrder).collect(Collectors.toList());
        return new PageImpl<>(elements, pageable, orders.getTotalElements());
    }

    @Override
    public Order getOrder(long userId, long id) throws NotFoundException {
        return EntityModelMapper.toOrder(ordersRepository.findByUserIdAndId(userId, id));
    }

    @Override
    @Transactional
    public Order createOrder(long userId, Basket basket) throws BadRequestException {
        OrderEntity order = new OrderEntity();
        order.setCreated(LocalDateTime.now());
        UserEntity user = usersRepository.findOne(userId);
        if (user == null) {
            throw BadRequestException.build(MessageFormat.format("Пользователь с идентификатором {0} не найден", userId));
        }
        order.setUser(user);
        List<OrderEntryEntity> entries = basket.getBooks().stream().map(b -> {
            OrderEntryEntity item = new OrderEntryEntity();
            BookEntity book = loadBook(b.getBook().getId());
            item.setOrder(order);
            item.setBook(book);
            item.setPrice(book.getPrice());
            item.setAmount(b.getAmount());
            return item;
        }).collect(Collectors.toList());
        order.setBooks(entries);
        ordersRepository.save(order);
        return EntityModelMapper.toOrder(order);
    }

    private Specification<OrderEntity> buildSpecification(long userId) {
        return new Specification<OrderEntity>() {

            @Override
            public Predicate toPredicate(Root<OrderEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("user").get("id"), userId);
            }
        };
    }

    private BookEntity loadBook(long id) {
        return Optional.ofNullable(booksRepository.findOne(id))
                .orElseThrow(() -> NotFoundException.build(MessageFormat.format("Книга с идентификатором {0} не найден", id)));
    }

}
