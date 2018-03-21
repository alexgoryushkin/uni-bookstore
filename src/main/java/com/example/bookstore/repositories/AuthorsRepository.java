package com.example.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookstore.entities.AuthorEntity;

/**
 * Репозиторий по работе с авторами
 */
@Repository
public interface AuthorsRepository extends JpaRepository<AuthorEntity, Long> {

    /**
     * Удаляет автора по идентификатору
     * 
     * @param id идентификатор автора
     * @return 1, если автор был удален, 0 в случае, если автор не найден
     */
    public int deleteById(long id);

    /**
     * Загружает список авторов по идентификаторами
     * 
     * @param ids идентификаторы
     * @return список авторов
     */
    public List<AuthorEntity> findByIdIn(List<Long> ids);
}
