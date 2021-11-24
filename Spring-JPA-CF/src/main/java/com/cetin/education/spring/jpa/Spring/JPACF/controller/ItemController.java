package com.cetin.education.spring.jpa.Spring.JPACF.controller;

import com.cetin.education.spring.jpa.Spring.JPACF.dto.ItemDTO;
import com.cetin.education.spring.jpa.Spring.JPACF.model.Item;
import com.cetin.education.spring.jpa.Spring.JPACF.service.ItemService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@RestController
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;

    @PostMapping
    public ResponseEntity<ItemDTO> addItem(@RequestBody final ItemDTO ItemDto){
       Item item = itemService.addItem(Item.from(ItemDto));
       return ResponseEntity.ok(ItemDTO.from(item));
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getItems(){
        List<Item> items = itemService.getItems();
        List<ItemDTO> itemDTOS = items.stream().map(ItemDTO::from).collect(Collectors.toList());
        return ResponseEntity.ok().body(itemDTOS);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ItemDTO> getItem(@PathVariable final Long id){
        Item item = itemService.getItem(id);
        ItemDTO itemDTO = ItemDTO.from(item);
        return ResponseEntity.ok().body(itemDTO);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<ItemDTO> deleteItem(@PathVariable final Long id){
        Item item = itemService.deleteItem(id);
        ItemDTO itemDTO = ItemDTO.from(item);
        return ResponseEntity.ok().body(itemDTO);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<ItemDTO> editItem(@PathVariable final Long id,
                                            @RequestBody final ItemDTO itemDTO){
        Item editItem = itemService.editItem(id, Item.from(itemDTO));
        return ResponseEntity.ok(ItemDTO.from(editItem));
    }
}
