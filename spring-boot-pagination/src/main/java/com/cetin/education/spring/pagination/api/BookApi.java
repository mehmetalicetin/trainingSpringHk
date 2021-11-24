package com.cetin.education.spring.pagination.api;

import com.cetin.education.spring.pagination.model.Book;
import com.cetin.education.spring.pagination.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookApi {

    private final BookRepository bookRepository;

    @GetMapping
    public Page<Book> pagination(@RequestParam Integer size,@RequestParam Integer page){
        PageRequest pageRequest = PageRequest.of(page, size);
       return bookRepository.findAll(pageRequest);
    }

    @GetMapping("/p")
    public Page<Book> pagination(Pageable pageable){
        //ek bir query daha calistirarak total count bilgisini de ceker
        return bookRepository.findAll(pageable);
    }

    @GetMapping("/s")
    public Slice<Book> slice(Pageable pageable){
        //ek bir query calistirarak total count bilgisini cekmez
        // sadece parcanın devam olup olmadıgı ogrebilecegimiz bir bilgi vardır
        return bookRepository.findAll(pageable);
    }
}
