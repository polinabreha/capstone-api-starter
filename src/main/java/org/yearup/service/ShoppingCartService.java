package org.yearup.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.yearup.models.CartItem;
import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;
import org.yearup.models.ShoppingCartItem;
import org.yearup.repository.ShoppingCartRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShoppingCartService
{
    // a shopping cart is built from cart rows plus a product lookup for each row
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductService productService;

    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ProductService productService)
    {
        this.shoppingCartRepository = shoppingCartRepository;
        this.productService = productService;
    }

    public ShoppingCart getByUserId(int userId)
    {

        List<CartItem> cartItems = shoppingCartRepository.findByUserId(userId);

        ShoppingCart cart = new ShoppingCart();

        Map<Integer, ShoppingCartItem>  map = new HashMap<>();

        for (CartItem cartItem : cartItems) {
            Product product = productService.getById(cartItem.getProductId());

            ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
            shoppingCartItem.setProduct(product);
            shoppingCartItem.setQuantity(cartItem.getQuantity());
            map.put(cartItem.getProductId(), shoppingCartItem);
    }

        cart.setItems(map);
        return cart;
    }

    public void addProduct(int userId, Product product)
    {
       CartItem existing = shoppingCartRepository.findByUserIdAndProductId(userId, product.getProductId());

       if (existing != null){
           existing.setQuantity(existing.getQuantity() + 1);
           shoppingCartRepository.save(existing);
       }else{
           CartItem item = new CartItem();
           item.setProductId(product.getProductId());
           item.setQuantity(1);
           item.setUserId(userId);
           shoppingCartRepository.save(item);
       }
    }

    public void updateProduct(int userId, int productId, int quantity)
    {
        CartItem existing = shoppingCartRepository.findByUserIdAndProductId(userId, productId);

        if (existing != null) {
            existing.setQuantity(quantity);
            shoppingCartRepository.save(existing);
        }
    }

    @Transactional
    public void clearCart(int userId)
    {
        shoppingCartRepository.deleteByUserId(userId);
    }
}
