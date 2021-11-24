package com.cetin.education.spring.grahql.dto;

public interface IConverterDto<T, E> {
    public E dtoToEntity(T t);
}
