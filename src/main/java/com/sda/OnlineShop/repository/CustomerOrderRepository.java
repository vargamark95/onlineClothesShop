package com.sda.OnlineShop.repository;

import com.sda.OnlineShop.entities.CustomerOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Integer> {

}
