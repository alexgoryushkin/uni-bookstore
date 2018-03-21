package com.example.bookstore.controllers;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.bookstore.dto.AuthorInfoDto;
import com.example.bookstore.model.Author;
import com.example.bookstore.services.AuthorService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Контроллер по работе с авторами
 */
@Api(tags = ApiTags.AUTHORS)
@RestController
@RequestMapping(path = "/authors", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthorsController {
    
    @Autowired
    private AuthorService authorService;

    /**
     * Возвращает информацию об авторе по идентификатору
     * 
     * @param id идентификатор автора
     * @return информация об авторе
     */
    @ApiOperation(
            value = "Получить информацию об авторе", 
            notes = "Получает подробную информацию об авторе")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Информация об авторе"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Автор с таким идентификатором не найден"),
    })
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Author getAuthor(@PathVariable("id") @ApiParam(name = "id", value = "идентификатор автора", example = "1", required = true) long id) {
        return authorService.getAuthor(id);
    }

    /**
     * Создает нового автора
     * 
     * @param newAuthor данные нового автора
     * @return информация о созданном авторе
     */
    @ApiOperation(
            value = "Создать автора", 
            notes = "Создает нового автора")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Информация о созданном авторе"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Присланы некорректные данные автора"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "Недостаточно привилегий"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public Author createAuthor(@RequestBody @ApiParam(name = "author", value = "информация об авторе", required = true) AuthorInfoDto newAuthor) {
        return authorService.createAuthor(newAuthor);
    }

    /**
     * Редактирует существующего автора
     * 
     * @param id идентификатор автора
     * @param author новые данные автора
     * @return изменившийся объект автора
     */
    @ApiOperation(
            value = "Редактирование автора",
            notes = "Редактирует существующего автора")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_OK, message = "Обновленная информация об авторе"),
            @ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Присланы некорректные данные автора"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "Недостаточно привилегий"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Автор по указанному идентифатору не найден")
    })
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public Author editAuthor(
            @PathVariable("id") @ApiParam(name = "id", value = "идентификатор автора", required = true) long id, 
            @RequestBody @ApiParam(name = "author", value = "обновленная информация об авторе", required = true) AuthorInfoDto author) {
        return authorService.editAuthor(id, author);
    }

    /**
     * Удаляет автора из системы. Удалить можно только автора, у кого нет
     * привязанных книг
     * 
     * @param id идентификатор автора
     */
    @ApiOperation(
            value = "Удаление автора",
            notes = "Удаляет автора по идентификатору")
    @ApiResponses({
            @ApiResponse(code = HttpServletResponse.SC_NO_CONTENT, message = "Автор был успешно удален"),
            @ApiResponse(code = HttpServletResponse.SC_FORBIDDEN, message = "Недостаточно привилегий"),
            @ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Автор по указанному идентифатору не найден")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @Secured("ROLE_ADMIN")
    public void deleteAuthor(@PathVariable("id") @ApiParam(name = "id", value = "идентификатор автора", required = true) long id) {
        authorService.deleteAuthor(id);
    }
}
