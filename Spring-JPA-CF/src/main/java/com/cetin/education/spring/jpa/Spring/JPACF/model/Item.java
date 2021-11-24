package com.cetin.education.spring.jpa.Spring.JPACF.model;

import com.cetin.education.spring.jpa.Spring.JPACF.dto.ItemDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "ITEM")
public class Item implements IEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String serialNumber;

    @ManyToOne
    private Cart cart;

    public static Item from(ItemDTO itemDTO){
        Item item = new Item();
        item.setSerialNumber(itemDTO.getSerialNumber());
        return item;
    }
}
