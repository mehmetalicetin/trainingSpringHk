package com.cetin.education.spring.jpa.Spring.JPACF.model;

import com.cetin.education.spring.jpa.Spring.JPACF.dto.CartDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CART")
@NoArgsConstructor
@Data
public class Cart implements IEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;


    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "cart_id")
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public static Cart from(CartDTO cartDTO){
        Cart cart= new Cart();
        cart.setName(cartDTO.getName());
        return cart;
    }
}
