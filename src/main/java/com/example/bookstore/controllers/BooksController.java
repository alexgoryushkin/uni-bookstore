package com.example.bookstore.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.config.ApiTags;
import com.example.bookstore.dto.BookDto;
import com.example.bookstore.dto.BookFilterDto;
import com.example.bookstore.model.Book;
import com.example.bookstore.services.BookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Контроллер по работе с книгами
 */
@Api(tags = ApiTags.BOOKS)
@RestController
@RequestMapping(path = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BooksController {
    
    @Autowired
    private BookService books;

    /**
     * Получить список книг
     * 
     * @param page страница загрузки
     * @param size количество элементов на странице
     * @param filter фильтр по книгам
     * @return список книг
     */
    @ApiOperation(
            value = "Получить список книг", 
            notes = "Получает список книг, подпадающих под фильтр")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Список заказов"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Присланы некорректные данные"),
    })
    @RequestMapping(method = RequestMethod.GET)
    public Page<Book> books(
            Pageable pageable, 
            BookFilterDto filter) {
        return books.getBooks(pageable, filter);
    }

    /**
     * Получение книги по идентификатору
     * 
     * @param id идентификатор книги
     * @return книга
     */
    @ApiOperation(
            value = "Получить подробную информацию о книге", 
            notes = "Получает подробную информацию о книге")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Информация об авторе"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Книга по указанному идентификатору не найдена"),
    })
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Book getBook(@PathVariable("id") @ApiParam(name = "id", value = "идентификатор книги", required = true) long id) {
        return books.getBook(id);
    }

    /**
     * Создает книгу
     * 
     * @param book данные книги
     * @return созданная книга
     */
    @ApiOperation(
            value = "Создание книги", 
            notes = "Создает новую книгу")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Книга успешно создана"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Некорректные данные в книге"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "Недостаточно привилегий")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public Book createBook(@RequestBody @ApiParam(name = "book", value = "данные для создания книги", required = true) BookDto book) {
        return books.createBook(book);
    }

    /**
     * Обновляет книгу. Игнорирует null поля
     * 
     * @param book данные книги
     * @return обновленная книга
     */
    @ApiOperation(
            value = "Обновление книги", 
            notes = "Обновляет существующую книгу")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Книга успешно обновлена"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Некорректные данные в книге"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "Недостаточно привилегий"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Книга по указанному идентификатору не найдена")
    })
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public Book updateBook(
            @PathVariable("id") @ApiParam(name = "id", value = "идентификатор книги", required = true) long id, 
            @RequestBody @ApiParam(name = "book", value = "данные для обновления книги", required = true) BookDto book) {
        return books.updateBook(id, book);
    }

    /**
     * Удаляет книгу по идентификатору
     * 
     * @param id идентификатор книгиА
     */
    @ApiOperation(
            value = "Удаление книги", 
            notes = "Удаляет книгу по идентификатору")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Книга успешно удалена"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "Недостаточно привилегий"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Книга по указанному идентификатору не найдена")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public void deleteBook(@PathVariable("id") @ApiParam(name = "id", value = "идентификатор книги", required = true) long id) {
        books.deleteBook(id);
    }
}
