package com.example.bookstore.services.impl;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.LinkedList;
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
import com.example.bookstore.dto.BookDto;
import com.example.bookstore.dto.BookFilterDto;
import com.example.bookstore.entities.AuthorEntity;
import com.example.bookstore.entities.BookEntity;
import com.example.bookstore.model.Book;
import com.example.bookstore.repositories.AuthorsRepository;
import com.example.bookstore.repositories.BooksRepository;
import com.example.bookstore.services.BookService;

/**
 * Сервис по работе с книгами
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BooksRepository booksRepository;
    
    @Autowired
    private AuthorsRepository authorsRepository;

    @Override
    public Page<Book> getBooks(Pageable pageable, BookFilterDto filter) {
        Page<BookEntity> books = booksRepository.findAll(buildSpecification(filter), pageable);
        List<Book> elements = books.getContent().stream().map(EntityModelMapper::toBook).collect(Collectors.toList());
        return new PageImpl<>(elements, pageable, books.getTotalElements());
    }

    @Override
    public Book getBook(long id) throws NotFoundException {
        return EntityModelMapper.toBook(load(id));
    }

    @Override
    @Transactional
    public Book createBook(BookDto book) throws BadRequestException {
        List<AuthorEntity> authors = loadAuthors(book.getAuthors());
        BookEntity newBook = new BookEntity();
        return saveBook(newBook, book, authors);
    }

    @Override
    @Transactional
    public Book updateBook(long id, BookDto book) throws NotFoundException, BadRequestException {
        List<AuthorEntity> authors = loadAuthors(book.getAuthors());
        BookEntity bookEntity = load(id);
        return saveBook(bookEntity, book, authors);
    }

    @Override
    @Transactional
    public void deleteBook(long id) throws NotFoundException {
        if (booksRepository.deleteById(id) == 0) {
            throw NotFoundException.build(MessageFormat.format("Книга с идентификатором {0} не найдена", id));
        }
    }

    private BookEntity load(long id) {
        return Optional.ofNullable(booksRepository.findOne(id))
                .orElseThrow(() -> NotFoundException.build(MessageFormat.format("Книга с идентификатором {0} не найден", id)));
    }
    
    private List<AuthorEntity> loadAuthors(List<Long> ids) {
        List<AuthorEntity> authors = authorsRepository.findByIdIn(ids);
        if (Optional.ofNullable(ids).map(Collection::size).orElse(0) == 0) {
            throw BadRequestException.build("У книги должен быть по крайней мере один автор");
        }
        if (authors.size() != ids.size()) {
            throw BadRequestException.build("Некоторые авторы книги не найдены");
        }
        return authors;
    }
    
    private Book saveBook(BookEntity entity, BookDto book, List<AuthorEntity> authors) {
        entity.setTitle(book.getTitle());
        entity.setDescription(book.getDescription());
        entity.setISBN(book.getIsbn());
        entity.setPrice(book.getPrice());
        entity.setYear(book.getYear());
        entity.setAuthors(authors);
        entity = booksRepository.save(entity);
        return EntityModelMapper.toBook(entity);
    }

    private Specification<BookEntity> buildSpecification(BookFilterDto filter) {
        return new Specification<BookEntity>() {
            @Override
            public Predicate toPredicate(Root<BookEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new LinkedList<>();
                if (filter.getYear() != null) {
                    predicates.add(cb.equal(root.get("year"), filter.getYear()));
                }
                if (filter.getAuthor() != null) {
                    predicates.add(cb.equal(root.join("authors").get("id"), filter.getAuthor()));
                }
                if (filter.getSearch() != null && filter.getSearch().trim().length() > 0) {
                    String pattern = "%" + filter.getSearch().trim().toLowerCase() + "%";
                    Predicate like = cb.or(
                            cb.like(cb.lower(root.get("title")), pattern), 
                            cb.like(cb.lower(root.get("description")), pattern),
                            cb.like(cb.lower(root.get("ISBN")), pattern));
                    predicates.add(like);
                }
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        };
    }

}
