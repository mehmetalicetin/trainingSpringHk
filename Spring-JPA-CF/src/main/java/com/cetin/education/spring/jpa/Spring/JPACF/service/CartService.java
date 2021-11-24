package com.cetin.education.spring.jpa.Spring.JPACF.service;

import com.cetin.education.spring.jpa.Spring.JPACF.exception.ItemIsAlreadyAssignedException;
import com.cetin.education.spring.jpa.Spring.JPACF.exception.ResourceNotFoundException;
import com.cetin.education.spring.jpa.Spring.JPACF.model.Cart;
import com.cetin.education.spring.jpa.Spring.JPACF.model.IEntity;
import com.cetin.education.spring.jpa.Spring.JPACF.model.Item;
import com.cetin.education.spring.jpa.Spring.JPACF.repository.CartRepository;
import com.cetin.education.spring.jpa.Spring.JPACF.util.Helper;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Data
@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ItemService itemService;

    public Cart addCart(Cart cart){
        Optional.ofNullable(cart).orElseThrow(()->new ResourceNotFoundException("Cart Not Found."));
        return cartRepository.save(cart);
    }

    //Convert iterable to List
    public List<Cart> getCarts(){
       return (List<Cart>) Helper.convertToListByIterator(cartRepository.findAll());
    }

    public Cart getCart(Long id){
        return cartRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Cart Not Found. ID:"+id));
    }

    public Cart deleteCart(Long id) {
        Cart cart = getCart(id);
        cartRepository.delete(cart);
        return cart;
    }

    @Transactional
    public Cart editCart(Long id, Cart cart){
        Cart cartToEdit = getCart(id);
        cartToEdit.setName(cart.getName());
        return cartToEdit;
    }

    @Transactional
    public Cart addItemToCart(Long cartId, Long itemId){
        Cart cart = getCart(cartId);
        Item item = itemService.getItem(itemId);
        if(Objects.nonNull(item.getCart())){
            throw new ItemIsAlreadyAssignedException(item.getCart().getId(), itemId);
        }
        cart.addItem(item);
        item.setCart(cart);
        return cart;
    }

    @Transactional
    public Cart removeItemFromCart(Long cartId, Long itemId){
        Cart cart = getCart(cartId);
        Item item = itemService.getItem(itemId);
        cart.removeItem(item);
        return cart;
    }
}
