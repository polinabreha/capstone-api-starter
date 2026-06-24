package org.yearup.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yearup.models.Order;
import org.yearup.models.User;
import org.yearup.repository.UserRepository;
import org.yearup.service.CheckoutService;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class CheckoutController {
    private CheckoutService checkoutService;
    private UserRepository userRepository;
    @Autowired
    public CheckoutController(CheckoutService checkoutService, UserRepository userRepository) {
        this.checkoutService = checkoutService;
        this.userRepository = userRepository;
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Order> checkout(@AuthenticationPrincipal UserDetails userDetails) {
        try {
            User user = userRepository.findByUsername(userDetails.getUsername());
            Order savedOrder = checkoutService.checkout(user.getId());
            return ResponseEntity.ok(savedOrder);
        } catch (RuntimeException e) {
            if (e.getMessage().equals("Cart is empty")) {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.internalServerError().build();
        }
    }
}
