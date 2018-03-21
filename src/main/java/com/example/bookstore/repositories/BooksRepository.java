package com.example.bookstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.bookstore.entities.BookEntity;

/**
 * Репозиторий для работы с книгами
 */
@Repository
public interface BooksRepository extends JpaRepository<BookEntity, Long>, JpaSpecificationExecutor<BookEntity> {

    /**
     * Удаляет книгу по идентификатору
     * 
     * @param id идентификатор автора
     * @return 1, если книга была удалена, 0 в случае, если книга не найдена
     */
    public int deleteById(long id);
}
