package com.cetin.education.spring.jpa.Spring.JPACF.dto;

import com.cetin.education.spring.jpa.Spring.JPACF.model.Item;
import lombok.Data;

import java.util.Objects;

@Data
public class ItemDTO {
    private Long id;
    private String serialNumber;
    private PlainCartDto plainCartDto;

    public static ItemDTO from(Item item){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setSerialNumber(item.getSerialNumber());

        if(Objects.nonNull(item.getCart())){
            itemDTO.setPlainCartDto(PlainCartDto.from(item.getCart()));
        }
        return itemDTO;
    }
}
