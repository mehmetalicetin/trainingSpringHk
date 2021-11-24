package com.cetin.education.spring.pagination.repo;

import com.cetin.education.spring.pagination.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
