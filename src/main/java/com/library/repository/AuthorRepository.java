package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.library.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>,JpaSpecificationExecutor<Author> {

}
