package com.cetin.education.spring.jpa.Spring.JPACF.util;

import com.cetin.education.spring.jpa.Spring.JPACF.model.IEntity;
import com.cetin.education.spring.jpa.Spring.JPACF.model.Item;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Helper {
    public static List<? extends IEntity> convertToListByIterator(Iterable<? extends IEntity> iterable){
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }
}
