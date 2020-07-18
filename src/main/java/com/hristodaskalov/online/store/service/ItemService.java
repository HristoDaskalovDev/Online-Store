package com.hristodaskalov.online.store.service;

import com.hristodaskalov.online.store.model.Item;

public interface ItemService {

    Item createItem(Item item);

    Item updateItem(Long existingItemId, Item newItem);

    Item getItem(Long id);

    void deleteItem(Long id);

    void decreaseItemStock(Long id, Long quantity);

    void increaseItemStock(Long id, Long quantity);
}
