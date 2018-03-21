package com.example.bookstore.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.config.ApiTags;
import com.example.bookstore.model.Basket;
import com.example.bookstore.model.Order;
import com.example.bookstore.services.BasketService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Контроллер по работе с корзиной
 */
@Api(tags = ApiTags.BASKET)
@RestController
@Scope("session")
@RequestMapping(path = "/basket", produces = MediaType.APPLICATION_JSON_VALUE)
public class BasketController {

    @Autowired
    private BasketService basketService;

    /**
     * Возвращает корзину текущего пользователя
     * 
     * @return корзину текущего пользователя
     */
    @ApiOperation(
            value = "Корзина текущего пользователя",
            notes = "Получает информацию о книгах в корзине текущего пользователя")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Корзина текущего пользователя"),
    })
    @RequestMapping(method = RequestMethod.GET)
    public Basket basket() {
        return basketService.basket();
    }

    /**
     * Добавляет книгу в корзину
     * 
     * @param bookId идентификатор книги
     * @param amount количество книг
     * @return обновленную карзину пользователя
     */
    @ApiOperation(
            value = "Добавление книги в корзину",
            notes = "Добавляет книгу в указанном количестве в корзину текущего пользователя")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Обновленная корзина текущего пользователя"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Книга с указанным идентификатором не найдена"),
    })
    @RequestMapping(method = RequestMethod.POST)
    public Basket addBook(
            @RequestParam("book") @ApiParam(name = "book", value = "идентификатор книги", required = true) long bookId,
            @RequestParam(name = "amount", required = false, defaultValue = "1") @ApiParam(name = "amount", value = "количество добавляемых экземпляров книги", required = true) int amount) {
        return basketService.addBook(bookId, amount);
    }

    /**
     * Очищает корзину
     */
    @ApiOperation(
            value = "Очистить корзину",
            notes = "Очищает корзину текущего пользователя, удаляя из нее все книги")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Корзина очищена"),
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE)
    public void clearBacket() {
        basketService.clearBasket();
    }

    /**
     * Изменяет количество определенной книги в корзине
     * 
     * @param bookId идентификатор книги
     * @param amount новое количество
     * @return обновленную карзину пользователя
     */
    @ApiOperation(
            value = "Изменение количества книги в корзине",
            notes = "Измененяет количество книги в корзине текущего пользователя")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Обновленная корзина текущего пользователя"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Книга с указанным идентификатором не найдена в корзине"),
    })
    @RequestMapping(path = "/book/{id}", method = RequestMethod.POST)
    public Basket changeBooksAmount(
            @PathVariable("id") @ApiParam(name = "id", value = "идентификатор книги", required = true) long bookId, 
            @RequestParam(name = "amount") @ApiParam(name = "amount", value = "количество добавляемых экземпляров книги", required = true) int amount) {
        return basketService.changeBooksAmount(bookId, amount);
    }

    /**
     * Удаляет книгу из корзины
     * 
     * @param bookId идентификатор книги
     * @return обновленную карзину пользователя
     */
    @ApiOperation(
            value = "Удаление книги из корзины",
            notes = "Удаляет книгу из корзины текущего пользователя")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Обновленная корзина текущего пользователя"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Книга с указанным идентификатором не найдена в корзине"),
    })
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public Basket removeBook(@PathVariable("id") @ApiParam(name = "id", value = "идентификатор книги", required = true) long bookId) {
        return basketService.removeBook(bookId);
    }

    /**
     * Разместить заказ
     * 
     * @return созданный заказ
     */
    @ApiOperation(
            value = "Формирование заказа",
            notes = "Формирует заказ на основе корзины пользователя")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Заказ сформирован"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "submit", method = RequestMethod.POST)
    public Order submit() {
        return basketService.submitOrder();
    }

}
