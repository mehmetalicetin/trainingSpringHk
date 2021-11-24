package com.cetin.education.spring.jpa.Spring.JPACF.controller;

import com.cetin.education.spring.jpa.Spring.JPACF.dto.CartDTO;
import com.cetin.education.spring.jpa.Spring.JPACF.model.Cart;
import com.cetin.education.spring.jpa.Spring.JPACF.service.CartService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@RestController
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartDTO> addCart(@RequestBody final CartDTO cartDTO){
        Cart cart = cartService.addCart(Cart.from(cartDTO));
        return ResponseEntity.ok(CartDTO.from(cart));
    }

    @GetMapping
    public ResponseEntity<List<CartDTO>> getCarts(){
        List<Cart> carts = cartService.getCarts();
        List<CartDTO> cartDTOS = carts.stream().map(CartDTO::from).collect(Collectors.toList());
        return ResponseEntity.ok(cartDTOS);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<CartDTO> getCart(@PathVariable final Long id){
        Cart cart = cartService.getCart(id);
        return ResponseEntity.ok(CartDTO.from(cart));
    }


    @DeleteMapping(value = "{id}")
    public ResponseEntity<CartDTO> deleteCart(@PathVariable final Long id){
        Cart cart = cartService.deleteCart(id);
        CartDTO cartDTO = CartDTO.from(cart);
        return ResponseEntity.ok().body(cartDTO);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<CartDTO> editCart(@PathVariable final Long id,
                                            @RequestBody final CartDTO cartDTO){
        Cart editCart = cartService.editCart(id, Cart.from(cartDTO));
        return ResponseEntity.ok(CartDTO.from(editCart));
    }

    @PostMapping(value = "{cartId}/items/{itemId}/add")
    public ResponseEntity<CartDTO> addItemToCart(@PathVariable final Long cartId,
                                                 @PathVariable final Long itemId){
        Cart cart = cartService.addItemToCart(cartId, itemId);
        return ResponseEntity.ok(CartDTO.from(cart));
    }

    @DeleteMapping(value = "{cartId}/items/{itemId}/remove")
    public ResponseEntity<CartDTO> removeItemToCart(@PathVariable final Long cartId,
                                                 @PathVariable final Long itemId){
        Cart cart = cartService.removeItemFromCart(cartId, itemId);
        return ResponseEntity.ok(CartDTO.from(cart));
    }

}
