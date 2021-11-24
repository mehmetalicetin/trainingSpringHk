package com.cetin.education.spring.jpa.Spring.JPACF.exception;

import java.text.MessageFormat;

public class ItemIsAlreadyAssignedException extends RuntimeException{
    public ItemIsAlreadyAssignedException(final Long cartId, final Long itemId) {
        super(MessageFormat.format("Item {0} is already assigned to cart: {1}", itemId, cartId));
    }
}
