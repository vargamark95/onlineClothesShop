package com.sda.OnlineShop.repository;

import com.sda.OnlineShop.entities.CustomerOrder;
import com.sda.OnlineShop.entities.OrderStatus;
import jakarta.persistence.criteria.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {
        Optional<CustomerOrder> findCustomerOrderByOrderStatusAndUserEmail(OrderStatus orderStatus, String email);
}
