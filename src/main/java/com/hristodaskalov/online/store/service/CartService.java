package com.hristodaskalov.online.store.service;

import com.hristodaskalov.online.store.model.Cart;
import com.hristodaskalov.online.store.model.CartItem;

import java.util.List;

public interface CartService {

    Cart getCart(Long id);

    Cart getLoggedUserCart();

    void checkout(List<CartItem> cartItems, Cart cart);
}
