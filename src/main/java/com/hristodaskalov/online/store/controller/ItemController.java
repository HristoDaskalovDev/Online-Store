package com.hristodaskalov.online.store.controller;

import com.hristodaskalov.online.store.dto.ItemDto;
import com.hristodaskalov.online.store.model.Item;
import com.hristodaskalov.online.store.service.ItemService;
import com.hristodaskalov.online.store.utils.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchant/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemDto> createItem(@RequestBody ItemDto itemDto) {
        Item item = ObjectConverter.convertObject(itemDto, Item.class);
        item = itemService.createItem(item);
        return new ResponseEntity<>(ObjectConverter.convertObject(item, ItemDto.class), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDto> getItem(@PathVariable("id") Long id) {
        Item item = itemService.getItem(id);
        return new ResponseEntity<>(ObjectConverter.convertObject(item, ItemDto.class), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDto> updateItem(@PathVariable("id") Long id, @RequestBody ItemDto itemDto) {
        Item item = ObjectConverter.convertObject(itemDto, Item.class);
        item = itemService.updateItem(id, item);
        return new ResponseEntity<>(ObjectConverter.convertObject(item, ItemDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") Long id) {
        itemService.deleteItem(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
