package com.example.bookstore.services.impl;

import java.text.MessageFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.bookstore.core.NotFoundException;
import com.example.bookstore.dto.AuthorInfoDto;
import com.example.bookstore.entities.AuthorEntity;
import com.example.bookstore.model.Author;
import com.example.bookstore.repositories.AuthorsRepository;
import com.example.bookstore.services.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorsRepository repository;

    @Override
    public Author getAuthor(long id) throws NotFoundException {
        return EntityModelMapper.toAuthor(load(id));
    }

    @Override
    @Transactional
    public Author createAuthor(AuthorInfoDto newAuthor) {
        AuthorEntity entity = new AuthorEntity();
        entity.setName(newAuthor.getName());
        entity.setDescription(newAuthor.getDescription());
        return EntityModelMapper.toAuthor(repository.save(entity));
    }

    @Override
    @Transactional
    public Author editAuthor(long id, AuthorInfoDto authorData) throws NotFoundException {
        AuthorEntity entity = load(id);
        entity.setName(authorData.getName());
        entity.setDescription(authorData.getDescription());
        return EntityModelMapper.toAuthor(repository.save(entity));
    }

    @Override
    @Transactional
    public void deleteAuthor(long id) throws NotFoundException {
        if (repository.deleteById(id) == 0) {
            throw NotFoundException.build(MessageFormat.format("Автор с идентификатором {0} не найден", id));
        }
    }
    
    private AuthorEntity load(long id) {
        return Optional.ofNullable(repository.findOne(id))
                .orElseThrow(() -> NotFoundException.build(MessageFormat.format("Автор с идентификатором {0} не найден", id)));
    }

}
