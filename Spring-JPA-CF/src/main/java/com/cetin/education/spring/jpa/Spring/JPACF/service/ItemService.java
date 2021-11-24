package com.cetin.education.spring.jpa.Spring.JPACF.service;

import com.cetin.education.spring.jpa.Spring.JPACF.exception.ResourceNotFoundException;
import com.cetin.education.spring.jpa.Spring.JPACF.model.IEntity;
import com.cetin.education.spring.jpa.Spring.JPACF.model.Item;
import com.cetin.education.spring.jpa.Spring.JPACF.repository.ItemRepository;
import com.cetin.education.spring.jpa.Spring.JPACF.util.Helper;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.module.ResolutionException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Data
@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public Item addItem(Item item){
        Optional.ofNullable(item).orElseThrow(()->new ResourceNotFoundException("Item Not Found."));
        return itemRepository.save(item);
    }

    //Convert iterable to List
    public List<Item> getItems(){
        List<Item> iEntities = (List<Item>) Helper.convertToListByIterator(itemRepository.findAll());
        return iEntities;
    }

    public Item getItem(Long id){
        return itemRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Item Not Found. ID:"+id));
    }

    public Item deleteItem(Long id) {
        Item item = getItem(id);
        itemRepository.delete(item);
        return item;
    }

    @Transactional
    public Item editItem(Long id, Item item){
        Item itemToEdit = getItem(id);
        itemToEdit.setSerialNumber(item.getSerialNumber());
        return itemToEdit;
    }
}
