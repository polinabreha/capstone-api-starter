#          Clothing Store 👚👗🧢

Clothing store application - is a Spring Boot REST API backend for a fully 
functional e-commerce web application. This project was built as part of a capstone assignment,
focusing on backend development, bug fixing, and feature implementation using Java, Spring Boot, and MySQL.


---------
### Technologies Used 💻

- ## Java Maven 17
- ## Spring Boot
- ## MySQL
- ## Insomnia

----------

### FEATURES ✨✨✨

- ## Categories Controller
    Implemented all CRUD operations for product categories. 
    Admin-only access is enforced for create, update, and delete actions using Spring Security role checks.

-  ## Shopping Cart
   Built a full shopping cart feature. Users can add products (quantity increments if already in cart),
   update quantities,and clear the cart. The cart persists between sessions since it is stored in the database.

-  ## User Profile
   Users can view and update their profile information. The profile is automatically created when a user registers.

-  ## Checkout / Orders
   Implemented an order checkout flow. On POST /orders, the API reads the user's cart, creates an order record, 
    inserts one order line item per product, and then clears the cart.

------
## FLOW of the Application ⏳
   Controller -> Service -> Repository -> Model
-----

## PROJECT STRUCTURE 🤍

```

```
src/
└── main/
└── java/
└── org.yearup/
├── controllers/
│   ├── AuthenticationController.java
│   ├── CategoriesController.java
│   ├── CheckoutController.java
│   ├── ProductsController.java
│   ├── ProfileController.java
│   └── ShoppingCartController.java
│
├── models/
│   ├── authentication/
│   ├── CartItem.java
│   ├── Category.java
│   ├── Order.java
│   ├── OrderLineItem.java
│   ├── Product.java
│   ├── Profile.java
│   ├── ShoppingCart.java
│   ├── ShoppingCartItem.java
│   └── User.java
│
├── repository/
│   ├── CategoryRepository.java
│   ├── OrderLineItemRepository.java
│   ├── OrderRepository.java
│   ├── ProductRepository.java
│   ├── ProfileRepository.java
│   ├── ShoppingCartRepository.java
│   └── UserRepository.java
│
├── security/
│   ├── jwt/
│   ├── JwtAccessDeniedHandler.java
│   ├── JwtAuthenticationEntryPoint.java
│   ├── SecurityUtils.java
│   ├── UserModelDetailsService.java
│   ├── UserNotActivatedException.java
│   └── WebSecurityConfig.java
│
├── service/
│   ├── CategoryService.java
│   ├── CheckoutService.java
│   ├── ProductService.java
│   ├── ProfileService.java
│   ├── ShoppingCartService.java
│   └── UserService.java
│
└── ECommerceApplication.java
│
└── resources/
├── static/
├── application.properties


------

## REPOSITORY GitHub LINK

https://github.com/polinabreha/capstone-api-starter   --- backend
https://github.com/polinabreha/capstone-client-clothingstore  --- frontend

-------
## Future Features 
####   Ranked by priority:

1. Product reviews and star ratings
2. Wish lists / saved items
3. Discount codes and promotional pricing
4. Order history and order status tracking
5. Email confirmation on registration and checkout

-------


## License 📄

This project was built as a learning exercise, free using.
