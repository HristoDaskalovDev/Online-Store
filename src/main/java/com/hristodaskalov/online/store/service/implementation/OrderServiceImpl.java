package com.hristodaskalov.online.store.service.implementation;

import com.hristodaskalov.online.store.entity.OrderEntity;
import com.hristodaskalov.online.store.exception.InvalidInputException;
import com.hristodaskalov.online.store.model.CartItem;
import com.hristodaskalov.online.store.model.Order;
import com.hristodaskalov.online.store.model.User;
import com.hristodaskalov.online.store.repository.OrderRepository;
import com.hristodaskalov.online.store.service.CartItemService;
import com.hristodaskalov.online.store.service.OrderService;
import com.hristodaskalov.online.store.service.UserService;
import com.hristodaskalov.online.store.utils.Constants;
import com.hristodaskalov.online.store.utils.ObjectConverter;
import com.hristodaskalov.online.store.utils.Validation;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartItemService cartItemService;
    private final UserService userService;

    public OrderServiceImpl(OrderRepository orderRepository, CartItemService cartItemService, UserService userService) {
        this.orderRepository = orderRepository;
        this.cartItemService = cartItemService;
        this.userService = userService;
    }

    @Override
    public Order addOrder(Order order) {
        validateOrder(order);
        order.setStatus(Constants.STATUS_ACCEPTED);

        OrderEntity orderEntity = ObjectConverter.convertObject(order, OrderEntity.class);
        orderEntity = orderRepository.save(orderEntity);

        return ObjectConverter.convertObject(orderEntity, Order.class);
    }

    @Override
    public Order getOrder(Long orderId) {

        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(
                ()-> new InvalidInputException(String.format("Order with id: %d does not exist", orderId))
        );

        return ObjectConverter.convertObject(orderEntity, Order.class);
    }

    @Override
    public Order updateOrder(Order order, Long orderId) {

        Order existingOrder = getOrder(orderId);
        updateOrderStatus(existingOrder, order);

        OrderEntity orderEntity = ObjectConverter.convertObject(existingOrder, OrderEntity.class);
        orderEntity = orderRepository.save(orderEntity);

        return ObjectConverter.convertObject(orderEntity, Order.class);
    }

    private void validateOrder(Order order) {

        checkIfCartItemExists(order.getCartItem());
        checkIfUserExistsAndIsCorrect(order.getUser());

        Validation.fieldNotEmptyOrNull(order.getDeliveryAddress(), "delivery address");
        Validation.fieldHasValidLength(
                order.getDeliveryAddress(),
                Constants.DELIVERY_ADDRESS_FIELD_MAX_LENGTH,
                "delivery address"
        );
    }

    private void checkIfCartItemExists(CartItem cartItem) {

        if (cartItem == null) {
            throw new InvalidInputException("Cart item cannot be null.");
        }
        if (cartItem.getId() == null) {
            throw new InvalidInputException("Cart item id cannot be null.");
        }

        // check if cart item with that id exist
        cartItemService.getCartItem(cartItem.getId());
    }

    private void checkIfUserExistsAndIsCorrect(User user) {

        if (user == null) {
            throw new InvalidInputException("User cannot be null.");
        }

        // check if order user is the same as the logged user
        User loggedUser = userService.retrieveLoggedUser();
        if (!user.getId().equals(loggedUser.getId())) {
            throw new InvalidInputException("Order user and logged user ids do not match.");
        }
    }

    private void updateOrderStatus(Order existingOrder, Order order) {

        if (order.getStatus() == null) {
            throw new InvalidInputException("Order status cannot be null.");
        }
        if (!Validation.isSameAsExisting(existingOrder.getStatus(), order.getStatus())) {
            existingOrder.setStatus(order.getStatus());
        }
    }
}
