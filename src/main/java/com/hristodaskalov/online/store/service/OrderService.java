package com.hristodaskalov.online.store.service;

import com.hristodaskalov.online.store.model.Order;

public interface OrderService {

    Order addOrder(Order order);

    Order getOrder(Long orderId);

    Order updateOrder(Order order, Long orderId);
}
