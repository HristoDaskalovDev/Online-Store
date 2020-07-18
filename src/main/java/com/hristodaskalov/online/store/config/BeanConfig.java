package com.hristodaskalov.online.store.config;

import com.hristodaskalov.online.store.repository.*;
import com.hristodaskalov.online.store.service.*;
import com.hristodaskalov.online.store.service.implementation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanConfig {

    // Basic Security Config
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserRepository userRepository, RoleService roleService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(UserService(userRepository, roleService));
        auth.setPasswordEncoder(passwordEncoder());

        return auth;
    }


    @Bean
    public RoleService RoleService(RoleRepository roleRepository) {
        return new RoleServiceImpl(roleRepository);
    }

    @Bean
    public CategoryService CategoryService(CategoryRepository categoryRepository) {
        return new CategoryServiceImpl(categoryRepository);
    }

    @Bean
    public SubCategoryService SubCategoryService(SubCategoryRepository subCategoryRepository,
                                                 CategoryService categoryService) {

        return new SubCategoryServiceImpl(subCategoryRepository, categoryService);
    }

    @Bean
    public UserPictureService UserPictureService(UserPictureRepository userPictureRepository) {
        return new UserPictureServiceImpl(userPictureRepository);
    }

    @Bean
    public UserService UserService(UserRepository userRepository, RoleService roleService) {
        return new UserServiceImpl(userRepository, roleService, passwordEncoder());
    }

    @Bean
    public ItemService ItemService(ItemRepository itemRepository,
                                   UserService userService,
                                   SubCategoryService subCategoryService) {

        return new ItemServiceImpl(userService, subCategoryService, itemRepository);
    }

    @Bean
    public ReviewService ReviewService(ReviewRepository reviewRepository, ItemService itemService) {
        return new ReviewServiceImpl(reviewRepository, itemService);
    }

    @Bean
    public CartService CartService(CartRepository cartRepository, UserService userService) {
        return new CartServiceImpl(cartRepository, userService);
    }

    @Bean
    public CartItemService CartItemService(CartItemRepository cartItemRepository,
                                           CartService cartService,
                                           ItemService itemService) {

        return new CartItemServiceImpl(cartItemRepository, cartService, itemService);
    }

    @Bean
    public OrderService OrderService(OrderRepository orderRepository,
                                     CartItemService cartItemService,
                                     UserService userService) {

        return new OrderServiceImpl(orderRepository, cartItemService, userService);
    }
}
