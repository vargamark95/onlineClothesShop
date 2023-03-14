package com.sda.OnlineShop.service;

import com.sda.OnlineShop.dto.CustomerOrderDto;
import com.sda.OnlineShop.entities.CustomerOrder;
import com.sda.OnlineShop.entities.SelectedProduct;
import com.sda.OnlineShop.entities.ShoppingCart;
import com.sda.OnlineShop.entities.User;
import com.sda.OnlineShop.mapper.CustomerOrderMapper;
import com.sda.OnlineShop.repository.CustomerOrderRepository;
import com.sda.OnlineShop.repository.SelectedProductRepository;
import com.sda.OnlineShop.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;
    @Autowired
    private SelectedProductRepository selectedProductRepository;
    @Autowired
    private CustomerOrderRepository customerOrderRepository;
    @Autowired
    private CustomerOrderMapper customerOrderMapper;

    public void launchOrder(String authenticatedUserEmail, CustomerOrderDto customerOrderDto) {
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUserEmail(authenticatedUserEmail);
        User user = shoppingCart.getUser();

        CustomerOrder customerOrder = customerOrderMapper.map(customerOrderDto);
        customerOrder.setUser(user);


        LocalDate localDate = LocalDate.now();
        customerOrder.setLocalDate(localDate);
        //customerOrder.setSelectedProducts(shoppingCart.getSelectedProducts());
        customerOrderRepository.save(customerOrder);




        // scoatem selectedProducts din shoopingCart
        for(SelectedProduct selectedProduct : shoppingCart.getSelectedProducts()){
            selectedProduct.setShoppingCart(null);
            selectedProduct.setCustomerOrder(customerOrder);
            selectedProductRepository.save(selectedProduct);
        }


    }

    public CustomerOrderDto getCustomerOrderDtoById(String orderId) {
        Optional<CustomerOrder> optionalCustomerOrder = customerOrderRepository.findById(Integer.valueOf(orderId));
        CustomerOrder customerOrder = optionalCustomerOrder.get();
        System.out.println(customerOrder);
        CustomerOrderDto customerOrderDto = customerOrderMapper.map(customerOrder);

        return customerOrderDto;
    }
}
