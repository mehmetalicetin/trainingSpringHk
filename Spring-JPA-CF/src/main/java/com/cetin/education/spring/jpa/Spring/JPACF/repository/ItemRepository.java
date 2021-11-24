package com.cetin.education.spring.jpa.Spring.JPACF.repository;

import com.cetin.education.spring.jpa.Spring.JPACF.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {
}
