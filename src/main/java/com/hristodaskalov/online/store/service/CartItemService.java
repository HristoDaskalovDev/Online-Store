package com.hristodaskalov.online.store.service;

import com.hristodaskalov.online.store.model.CartItem;

public interface CartItemService {

    CartItem addCartItem(CartItem cartItem);

    CartItem getCartItem(Long cartItemId);

    CartItem updateCartItem(Long cartItemId, Long quantity);

    void deleteCartItem(Long cartItemId);
}
