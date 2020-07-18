package com.hristodaskalov.online.store.service.implementation;

import com.hristodaskalov.online.store.entity.CartEntity;
import com.hristodaskalov.online.store.exception.InvalidInputException;
import com.hristodaskalov.online.store.model.Cart;
import com.hristodaskalov.online.store.model.CartItem;
import com.hristodaskalov.online.store.model.User;
import com.hristodaskalov.online.store.repository.CartRepository;
import com.hristodaskalov.online.store.service.CartService;
import com.hristodaskalov.online.store.service.UserService;
import com.hristodaskalov.online.store.utils.ObjectConverter;

import javax.transaction.Transactional;
import java.util.List;

public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final UserService userService;

    public CartServiceImpl(CartRepository cartRepository, UserService userService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
    }

    @Override
    public Cart getCart(Long id) {

        CartEntity cartEntity = cartRepository.findById(id).orElseThrow(
                ()-> new InvalidInputException(String.format("Cart with id: %d doesn't exist", id))
        );

        return ObjectConverter.convertObject(cartEntity, Cart.class);
    }

    @Override
    @Transactional
    public void checkout(List<CartItem> cartItems, Cart cart) {
        //TODO make orders and decrease item quantity, empty cart, add discount to every item
    }


    //TODO should get functionality have @Transactional
    @Override
    public Cart getLoggedUserCart() {

        User user = userService.retrieveLoggedUser();
        CartEntity cartEntity = cartRepository.findOneByUserId(user.getId());

        return ObjectConverter.convertObject(cartEntity, Cart.class);
    }
}
