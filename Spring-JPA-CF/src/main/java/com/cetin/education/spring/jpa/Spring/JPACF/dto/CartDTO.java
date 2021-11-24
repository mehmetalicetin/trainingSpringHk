package com.cetin.education.spring.jpa.Spring.JPACF.dto;

import com.cetin.education.spring.jpa.Spring.JPACF.model.Cart;
import com.cetin.education.spring.jpa.Spring.JPACF.model.Item;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class CartDTO {
    private Long id;
    private String name;
    private List<ItemDTO> items = new ArrayList<>();

    public static CartDTO from(Cart cart){
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setItems(cart.getItems().stream().map(ItemDTO::from).collect(Collectors.toList()));
        cartDTO.setName(cart.getName());
        return cartDTO;
    }
}
