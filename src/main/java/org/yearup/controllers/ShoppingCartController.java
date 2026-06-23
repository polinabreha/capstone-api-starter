package org.yearup.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.models.User;
import org.yearup.service.ProductService;
import org.yearup.service.ShoppingCartService;
import org.yearup.service.UserService;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:63342")
@PreAuthorize("isAuthenticated()")
public class ShoppingCartController
{
    private final ShoppingCartService shoppingCartService;
    private final ProductService productService;
    private final UserService userService;

    public ShoppingCartController(ShoppingCartService shoppingCartService, UserService userService, ProductService productService) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/cart")
    public ShoppingCart getCart(Principal principal)
    {
        String userName = principal.getName();
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        return shoppingCartService.getByUserId(userId);
    }

    @PostMapping("/cart/products/{productId}")
    public ResponseEntity<ShoppingCart> addProduct(Principal principal, @PathVariable int productId)
    {
        String userName = principal.getName();
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        Product product = productService.getById(productId);
        shoppingCartService.addProduct(userId,product);


        ShoppingCart cart = shoppingCartService.getByUserId(userId);
        return ResponseEntity.status(201).body(cart);
    }

    @PutMapping("/cart/products/{productId}")
    public ResponseEntity<ShoppingCart> updateCart(Principal principal, @PathVariable int productId, @RequestBody ShoppingCartItem item)
    {
        String userName = principal.getName();
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        shoppingCartService.updateProduct(userId, productId, item.getQuantity());


        ShoppingCart cart = shoppingCartService.getByUserId(userId);
        return ResponseEntity.ok().body(cart);
    }

    @DeleteMapping("/cart")
    public ResponseEntity<ShoppingCart> clearCart(Principal principal)
    {
        String userName = principal.getName();
        User user = userService.getByUserName(userName);
        int userId = user.getId();

        shoppingCartService.clearCart(userId);
        ShoppingCart cart = shoppingCartService.getByUserId(userId);
        return ResponseEntity.ok().body(cart);
    }


}
