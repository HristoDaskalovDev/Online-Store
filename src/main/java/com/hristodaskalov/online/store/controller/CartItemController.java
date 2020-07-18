package com.hristodaskalov.online.store.controller;

import com.hristodaskalov.online.store.dto.CartItemDto;
import com.hristodaskalov.online.store.model.CartItem;
import com.hristodaskalov.online.store.service.CartItemService;
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

import javax.validation.Valid;

@RestController
@RequestMapping("/profile/cart/cart-items")
public class CartItemController {

    private final CartItemService cartItemService;

    @Autowired
    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @PostMapping
    public ResponseEntity<CartItemDto> addCartItem(@RequestBody @Valid CartItemDto cartItemDto) {

        CartItem cartItem = ObjectConverter.convertObject(cartItemDto, CartItem.class);
        cartItem = cartItemService.addCartItem(cartItem);
        return new ResponseEntity<>(ObjectConverter.convertObject(cartItem, CartItemDto.class), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItemDto> updateCartItemQuantity(@PathVariable("id") Long cartItemId, Long quantity) {
        CartItem cartItem = cartItemService.updateCartItem(cartItemId, quantity);
        return new ResponseEntity<>(ObjectConverter.convertObject(cartItem, CartItemDto.class), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItemDto> getCartItem(@PathVariable("id") Long cartItemId) {
        CartItem cartItem = cartItemService.getCartItem(cartItemId);
        return new ResponseEntity<>(ObjectConverter.convertObject(cartItem, CartItemDto.class), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCartItem(@PathVariable("id") Long cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
