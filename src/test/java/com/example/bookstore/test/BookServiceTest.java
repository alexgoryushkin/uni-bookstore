package com.example.bookstore.test;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.dto.BookFilterDto;
import com.example.bookstore.model.Book;
import com.example.bookstore.services.BookService;

public class BookServiceTest extends AbstractTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testGetBook() {
        Book book = bookService.getBook(2);
        Assert.assertNotNull("Не найдена ожидаемая книга", book);
        Assert.assertEquals("Неверный код книги", book.getId(), 2L);
    }

    @Test
    public void testListBooks() {
        BookFilterDto filter = new BookFilterDto();
        Page<Book> books = bookService.getBooks(new PageRequest(1, 10), filter);
        Assert.assertEquals("Неверное количество книг", 6, books.getTotalElements());
        filter.setSearch("соляриС");
        books = bookService.getBooks(new PageRequest(1, 10), filter);
        System.out.println(books.getSize());
        Assert.assertEquals("Неверное количество книг с фильтром \"соляриС\"", 1, books.getTotalElements());
        System.out.println(books);
    }
    
    @Test(expected = DataIntegrityViolationException.class)
    public void testCreateIncorrectBook() {
    	BookDto newBook = new BookDto();
        newBook.setPrice(1234);
        newBook.setIsbn("123-123-123-12");
        newBook.setDescription("test1 description");
        newBook.setYear(2017);
        newBook.setAuthors(Collections.singletonList(1L));
        bookService.createBook(newBook);
    }

    @Test
    public void testCreateBook() {
        BookDto newBook = new BookDto();
        newBook.setTitle("test1");
        newBook.setPrice(1234);
        newBook.setIsbn("123-123-123-12");
        newBook.setDescription("test1 description");
        newBook.setYear(2017);
        newBook.setAuthors(Collections.singletonList(1L));
        Book book = bookService.createBook(newBook);
        Assert.assertNotNull("Новая книга не может быть null", book);
        Assert.assertEquals("Некорректный заголовок", newBook.getTitle(), book.getTitle());
        Assert.assertEquals("Некорректное описание", newBook.getDescription(), book.getDescription());
        Assert.assertEquals("Некорректная цена", newBook.getPrice(), book.getPrice());
        Assert.assertEquals("Некорректный ISBN", newBook.getIsbn(), book.getIsbn());
        Assert.assertEquals("Некорректный год", newBook.getYear(), book.getYear());
        Assert.assertEquals("Количество авторов ожидалось 1", book.getAuthors().size(), 1);
        Assert.assertEquals("Ожидался автор с идентификатором - 1", book.getAuthors().get(0).getId(), 1L);
    }
}
