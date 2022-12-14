package com.ten.kr.repositories;

import com.ten.kr.entity.Cart;
import com.ten.kr.entity.Product;
import com.ten.kr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    public List<Cart> findByUser(User user);

    public Cart findByUserAndProduct(User user, Product product);
    @Query("UPDATE Cart c SET c.quantity = ?1 WHERE c.product.id = ?2 AND c.user.id = ?3")
    @Modifying
    public void updateQuantity(int quantity, Long productId, Long userId);
    @Query("DELETE FROM Cart c WHERE c.user.id = ?1 AND c.product.id =?2")
    @Modifying
    void deleteByUserAndProduct(Long userId, Long productId);
    @Query("DELETE FROM Cart c WHERE c.user.id = ?1")
    @Modifying
    void deleteByUser(Long userId);

}
