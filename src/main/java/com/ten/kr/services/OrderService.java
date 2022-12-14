package com.ten.kr.services;

import com.ten.kr.entity.Cart;
import com.ten.kr.entity.Order;
import com.ten.kr.entity.OrderProducts;
import com.ten.kr.entity.User;
import com.ten.kr.repositories.CartRepository;
import com.ten.kr.repositories.OrderProductsRepository;
import com.ten.kr.repositories.OrderRepository;
import com.ten.kr.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    public void createOrder(Order newOrder, User user, List<Cart> cartList){
        newOrder.setCreationDate(new Date());
        newOrder.setUser(user);
        double total = 0;
        List<OrderProducts> orderProductsList = new ArrayList<>();
        for(Cart cart : cartList){
            total += cart.getProduct().getPrice() * cart.getQuantity();
            OrderProducts orderProducts = new OrderProducts();
            orderProducts.setQuantity(cart.getQuantity());
            orderProducts.setUser(user);
            orderProducts.setProduct(cart.getProduct());
            orderProductsList.add(orderProducts);
        }
        newOrder.setSummary(total);
        newOrder.setProductsList(orderProductsList);
        orderRepository.save(newOrder);
        cartRepository.deleteByUser(user.getId());
    }
    public void removeOrder(Long id){
        orderRepository.deleteById(id);
    }
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}
