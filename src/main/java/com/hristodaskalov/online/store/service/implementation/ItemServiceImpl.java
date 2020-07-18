package com.hristodaskalov.online.store.service.implementation;

import com.hristodaskalov.online.store.entity.ItemEntity;
import com.hristodaskalov.online.store.exception.InvalidInputException;
import com.hristodaskalov.online.store.model.Item;
import com.hristodaskalov.online.store.model.SubCategory;
import com.hristodaskalov.online.store.repository.ItemRepository;
import com.hristodaskalov.online.store.service.ItemService;
import com.hristodaskalov.online.store.service.SubCategoryService;
import com.hristodaskalov.online.store.service.UserService;
import com.hristodaskalov.online.store.utils.Constants;
import com.hristodaskalov.online.store.utils.ObjectConverter;
import com.hristodaskalov.online.store.utils.Validation;

import javax.transaction.Transactional;


public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final UserService userService;
    private final SubCategoryService subCategoryService;

    public ItemServiceImpl(UserService userService,
                           SubCategoryService subCategoryService,
                           ItemRepository itemRepository) {

        this.userService = userService;
        this.subCategoryService = subCategoryService;
        this.itemRepository = itemRepository;
    }

    @Override
    @Transactional
    public Item createItem(Item item) {

        validateFields(item);
        item.setMerchant(userService.retrieveLoggedUser());
        item.setInactive(false);

        ItemEntity itemEntity = ObjectConverter.convertObject(item, ItemEntity.class);
        itemEntity = itemRepository.save(itemEntity);

        return ObjectConverter.convertObject(itemEntity, Item.class);
    }

    @Override
    @Transactional
    public Item updateItem(Long existingItemId, Item newItem) {

        Item existingItem = getItem(existingItemId);

        validateFields(newItem);
        updateFieldsIfValid(existingItem, newItem);

        ItemEntity itemEntity = itemRepository.save(
                ObjectConverter.convertObject(existingItem, ItemEntity.class)
        );

        return ObjectConverter.convertObject(itemEntity, Item.class);
    }

    @Override
    public Item getItem(Long id) {
        ItemEntity itemEntity = itemRepository.findById(id).orElseThrow(
                ()-> new InvalidInputException(String.format("Item with id: %d does not exist.", id))
        );

        return ObjectConverter.convertObject(itemEntity, Item.class);
    }

    //TODO delete item reviews when item is deleted
    @Override
    @Transactional
    public void deleteItem(Long id) {
        Item item = getItem(id);
        item.setInactive(true);
        itemRepository.save(
                ObjectConverter.convertObject(item, ItemEntity.class)
        );
    }

    @Override
    @Transactional
    public void decreaseItemStock(Long id, Long quantity) {

        Item item = getItem(id);

        if (item.getStock() < quantity) {
            throw new InvalidInputException(
                    String.format("Item has only %d left in stock.", item.getStock())
            );
        }

        item.setStock(item.getStock() - quantity);
        itemRepository.save(
                ObjectConverter.convertObject(item, ItemEntity.class)
        );
    }

    @Override
    @Transactional
    public void increaseItemStock(Long id, Long quantity) {

        Item item = getItem(id);

        item.setStock(item.getStock() + quantity);
        itemRepository.save(
                ObjectConverter.convertObject(item, ItemEntity.class)
        );
    }

    private void validateFields(Item item) {

        Validation.fieldNotEmptyOrNull(item.getName(), "item name");
        Validation.fieldHasValidLength(item.getName(), Constants.NAME_FIELD_MAX_LENGTH, "item name");
        Validation.fieldNotEmptyOrNull(item.getStock().toString(), "stock");
        Validation.isValidPositiveNumber(item.getStock().toString());
        Validation.fieldNotEmptyOrNull(item.getPrice().toString(), "price");
        Validation.isValidPositiveNumber(item.getPrice().toString());
        validateDiscount(item.getDiscount());
        validateSubCategory(item.getSubCategory());
    }

    private void updateFieldsIfValid(Item existingItem, Item newItem) {

        if (Validation.isSameAsExisting(existingItem.getName(), newItem.getName())) {
            existingItem.setName(newItem.getName());
        }
        if (!existingItem.getStock().equals(newItem.getStock())) {
            existingItem.setStock(newItem.getStock());
        }
        if (!existingItem.getPrice().equals(newItem.getPrice())) {
            existingItem.setPrice(newItem.getPrice());
        }
        if (!existingItem.getDiscount().equals(newItem.getDiscount())) {
            existingItem.setDiscount(newItem.getDiscount());
        }
        if (!existingItem.getSubCategory().getId().equals(newItem.getSubCategory().getId())) {
            existingItem.setSubCategory(newItem.getSubCategory());
        }
    }

    private void validateDiscount(Short discount) {

        Validation.fieldNotEmptyOrNull(discount.toString(), "discount");
        Validation.isValidPositiveNumber(discount.toString());

        if (discount < Constants.DISCOUNT_MIN_VALUE || discount > Constants.DISCOUNT_MAX_VALUE) {
            throw new InvalidInputException(
                    String.format("Discount must be between %d and %d.",
                            Constants.DISCOUNT_MIN_VALUE,
                            Constants.DISCOUNT_MAX_VALUE
                    )
            );
        }
    }

    private void validateSubCategory(SubCategory subCategory) {

        if (subCategory == null) {
            throw new InvalidInputException("SubCategory cannot be null.");
        }
        if (subCategory.getId() == null) {
            throw new InvalidInputException("SubCategory id cannot be null.");
        }
        // check if the subcategory we want to update to exists
        SubCategory newSubCategory = subCategoryService.getSubCategoryByIdAndCategoryId(
                subCategory.getId(),
                subCategory.getCategory().getId()
        );
    }
}
