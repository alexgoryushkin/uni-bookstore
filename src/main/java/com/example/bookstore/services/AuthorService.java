package com.example.bookstore.services;

import com.example.bookstore.core.NotFoundException;
import com.example.bookstore.dto.AuthorInfoDto;
import com.example.bookstore.model.Author;

/**
 * Сервис по работе с авторами
 */
public interface AuthorService {

    /**
     * Получить автора по идентификатору
     * 
     * @param id идентификатор автора
     * @return информацию об авторе
     * @throws NotFoundException если автор по идентификатору не найден
     */
    public Author getAuthor(long id) throws NotFoundException;

    /**
     * Создание автора
     * 
     * @param newAuthor данные для создания автора
     * @return созданный автор
     */
    public Author createAuthor(AuthorInfoDto newAuthor);

    /**
     * Редактирование автора
     * 
     * @param id идентификатор автора
     * @param authorData обновленные данные автора
     * @return обновленный автор
     * @throws NotFoundException если автор по идентификатору не найден
     */
    public Author editAuthor(long id, AuthorInfoDto authorData) throws NotFoundException;

    /**
     * Удаление автора
     * 
     * @param id идентификатор автора
     * @throws NotFoundException если автор по идентификатору не найден
     */
    public void deleteAuthor(long id) throws NotFoundException;

}
