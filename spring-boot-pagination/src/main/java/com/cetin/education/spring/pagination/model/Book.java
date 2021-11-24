package com.cetin.education.spring.pagination.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "book")
@Data
@EqualsAndHashCode(of = "id")
public class Book {

    @Id
    @GeneratedValue
    private Long id;
    private String isbn;
    private String title;
    private LocalDate publishDate;
}
