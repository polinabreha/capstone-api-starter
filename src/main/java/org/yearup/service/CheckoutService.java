package org.yearup.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.yearup.models.*;
import org.yearup.repository.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Service
public class CheckoutService {
    private final OrderRepository orderRepository;
    private final OrderLineItemRepository orderLineItemRepository;
    private final ShoppingCartService shoppingCartService;
    private final ProfileRepository profileRepository;
    private final ProductRepository productRepository;
    public CheckoutService(OrderRepository orderRepository,
                           OrderLineItemRepository orderLineItemRepository,
                           ShoppingCartService shoppingCartService,
                           ProfileRepository profileRepository,
                           ProductRepository productRepository
                           ) {
        this.orderRepository = orderRepository;
        this.orderLineItemRepository = orderLineItemRepository;
        this.shoppingCartService = shoppingCartService;
        this.profileRepository = profileRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Order checkout(Integer userId) {
        ShoppingCart shoppingCart = shoppingCartService.getByUserId(userId);

        Profile profile = profileRepository.findByUserId(userId);

        Order order = new Order();
        order.setUserId(userId);
        order.setDate(LocalDateTime.now());
        order.setAddress(profile.getAddress());
        order.setCity(profile.getCity());
        order.setState(profile.getState());
        order.setZip(profile.getZip());
        order.setShippingAmount(BigDecimal.ZERO);
        Order savedOrder = orderRepository.save(order);

        for (ShoppingCartItem cartItem : shoppingCart.getItems().values()) {
            Product product = productRepository.findById(cartItem.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + cartItem.getProductId()));

            OrderLineItem orderLineItem = new OrderLineItem();
            orderLineItem.setOrder(savedOrder);
            orderLineItem.setProductId(product.getProductId());
            orderLineItem.setSalesPrice(product.getPrice());
            orderLineItem.setQuantity(cartItem.getQuantity());
            orderLineItem.setDiscount(BigDecimal.ZERO);
            orderLineItemRepository.save(orderLineItem);

        }

        shoppingCartService.clearCart(userId);
        return savedOrder;
    }
}
