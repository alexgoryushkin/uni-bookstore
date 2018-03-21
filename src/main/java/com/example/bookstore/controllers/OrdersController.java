package com.example.bookstore.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.config.ApiTags;
import com.example.bookstore.core.SecurityUtils;
import com.example.bookstore.model.Order;
import com.example.bookstore.services.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Контроллер по работе с заказами
 */
@Api(tags = ApiTags.ORDERS)
@RestController
@RequestMapping(path = "/orders", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrdersController {

    @Autowired
    private OrderService orderService;

    /**
     * Получение списка заказов текущего пользователя
     * 
     * @param page номер станицы
     * @param size размер страницы
     * @return список заказов текущего пользователя
     */
    @ApiOperation(
            value = "Получить список заказов", 
            notes = "Получает список заказов текущего пользователя")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Список заказов"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Присланы некорректные данные"),
    })
    @RequestMapping(method = RequestMethod.GET)
    public Page<Order> orders(Pageable pageable) {
        return orderService.getOrders(SecurityUtils.getCurrentUserId(), pageable);
    }

    /**
     * Получение информации о заказе
     * 
     * @param orderId идентификатор заказа
     * @return информацию о заказе
     */
    @ApiOperation(
            value = "Получение информации о заказе", 
            notes = "Получает информацию о заказе, сделанном текущим пользователем")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Информация о заказе"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Заказ с указанным идентификатором не найден"),
    })
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Order getOrder(@PathVariable("id") @ApiParam(name = "id", value = "идентификатор заказа", example = "1", required = true) long orderId) {
        return orderService.getOrder(SecurityUtils.getCurrentUserId(), orderId);
    }
}
