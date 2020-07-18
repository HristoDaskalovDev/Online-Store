package com.hristodaskalov.online.store.service.implementation;

import com.hristodaskalov.online.store.entity.CartItemEntity;
import com.hristodaskalov.online.store.exception.InvalidInputException;
import com.hristodaskalov.online.store.model.CartItem;
import com.hristodaskalov.online.store.model.Item;
import com.hristodaskalov.online.store.repository.CartItemRepository;
import com.hristodaskalov.online.store.service.CartItemService;
import com.hristodaskalov.online.store.service.CartService;
import com.hristodaskalov.online.store.service.ItemService;
import com.hristodaskalov.online.store.utils.ObjectConverter;
import com.hristodaskalov.online.store.utils.Validation;

import javax.transaction.Transactional;

public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartService cartService;
    private final ItemService itemService;

    public CartItemServiceImpl(CartItemRepository cartItemRepository, CartService cartService, ItemService itemService) {
        this.cartItemRepository = cartItemRepository;
        this.cartService = cartService;
        this.itemService = itemService;
    }

    @Override
    @Transactional
    public CartItem addCartItem(CartItem cartItem) {

       // TODO: try catch fer all
        if (cartItem.getId() == null) {
            throw new InvalidInputException("Item id cannot be null.");
        }
        Item item = itemService.getItem(cartItem.getItem().getId());// - check if it fails on post mapping
        cartItem.setCart(cartService.getLoggedUserCart());

        Validation.isValidPositiveNumber(cartItem.getQuantity().toString());
        cartItem.setQuantity(cartItem.getQuantity());
        itemService.decreaseItemStock(cartItem.getItem().getId(), cartItem.getQuantity());

        CartItemEntity cartItemEntity = ObjectConverter.convertObject(cartItem, CartItemEntity.class);
        cartItemEntity = cartItemRepository.save(cartItemEntity);

        return ObjectConverter.convertObject(cartItemEntity, CartItem.class);
    }

    @Override
    public CartItem getCartItem(Long cartItemId) {

        CartItemEntity cartItemEntity = cartItemRepository.findById(cartItemId).orElseThrow(
                ()-> new InvalidInputException(String.format("Cart item with id: %d doesn't exist", cartItemId))
        );

        return ObjectConverter.convertObject(cartItemEntity, CartItem.class);
    }

    @Override
    public CartItem updateCartItem(Long cartItemId, Long quantity) {

        CartItem cartItem = getCartItem(cartItemId);
        return updateCartItemQuantity(cartItem, quantity);
    }

    @Override
    @Transactional
    public void deleteCartItem(Long cartItemId) {

        CartItem cartItem = getCartItem(cartItemId);
        itemService.increaseItemStock(cartItem.getItem().getId(), cartItem.getQuantity());
        cartItemRepository.delete(ObjectConverter.convertObject(cartItem, CartItemEntity.class));
    }

    private CartItem updateCartItemQuantity(CartItem cartItem, Long quantity) {

        Validation.isValidPositiveNumber(quantity.toString());

        if (quantity - cartItem.getQuantity() > 0) {
            itemService.decreaseItemStock(cartItem.getItem().getId(), quantity - cartItem.getQuantity());
        } else if (quantity - cartItem.getQuantity() < 0){
            itemService.increaseItemStock(cartItem.getItem().getId(), cartItem.getQuantity() - quantity);
        } else {
            deleteCartItem(cartItem.getId());
        }

        return cartItem;
    }
}
