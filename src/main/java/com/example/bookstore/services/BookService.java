package com.example.bookstore.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.bookstore.core.BadRequestException;
import com.example.bookstore.core.NotFoundException;
import com.example.bookstore.dto.BookDto;
import com.example.bookstore.dto.BookFilterDto;
import com.example.bookstore.model.Book;

/**
 * Сервис по работе с книгами
 */
public interface BookService {

    /**
     * Получить страницу с книгами
     * 
     * @param pageable построничная разбивка и сортировки
     * @param filter фильтр
     * @return страницу со списком книг
     */
    public Page<Book> getBooks(Pageable pageable, BookFilterDto filter);

    /**
     * Получить книгу по идентификатору
     * 
     * @param id идентификатор книги
     * @return книга
     * @throws NotFoundException если книга по идентификатору не найдена
     */
    public Book getBook(long id) throws NotFoundException;

    /**
     * Создание книги
     * 
     * @param book данные новой книги
     * @return созданную книгу
     * @throws BadRequestException если данные новой книги некорректны. В
     *             частности, если не найден указаный автор
     */
    public Book createBook(BookDto book) throws BadRequestException;

    /**
     * Обновление книги
     * 
     * @param id идентификатор книги
     * @param book данные обновленной книги
     * @return обновленную книгу
     * @throws NotFoundException если книга по идентификатору не найдена
     * @throws BadRequestException если данные новой книги некорректны. В
     *             частности, если не найден указаный автор
     */
    public Book updateBook(long id, BookDto book) throws NotFoundException, BadRequestException;

    /**
     * Удаление книги
     * 
     * @param id идентификатор книги
     * @throws NotFoundException если книга по идентификатору не найдена
     */
    public void deleteBook(long id) throws NotFoundException;
}
