package com.sda.OnlineShop.service;

import com.sda.OnlineShop.dto.CustomerOrderDto;
import com.sda.OnlineShop.dto.OrderDetailsDto;
import com.sda.OnlineShop.entities.*;
import com.sda.OnlineShop.mapper.CustomerOrderMapper;
import com.sda.OnlineShop.mapper.OrderDetailsMapper;
import com.sda.OnlineShop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderDetailsMapper orderDetailsMapper;
    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    public void launchOrder(String authenticatedUserEmail, CustomerOrderDto customerOrderDto, OrderDetailsDto orderDetailsDto) {
        ShoppingCart shoppingCart = shoppingCartRepository.findShoppingCartByUserEmail(authenticatedUserEmail);
        User user = shoppingCart.getUser();

        CustomerOrder customerOrder = customerOrderBuilder(customerOrderDto, orderDetailsDto, user);

        //customerOrder.setSelectedProducts(shoppingCart.getSelectedProducts());
        customerOrderRepository.save(customerOrder);
        System.out.println(customerOrder);

        List<SelectedProduct> selectedProducts = new ArrayList<>();
        // scoatem selectedProducts din shoopingCart
        for (SelectedProduct selectedProduct : shoppingCart.getSelectedProducts()) {
            selectedProduct.setShoppingCart(null);
            selectedProduct.setCustomerOrder(customerOrder);
            selectedProductRepository.save(selectedProduct);
            selectedProduct.getProduct().setQuantity(selectedProduct.getProduct().getQuantity() - selectedProduct.getQuantity());
            productRepository.save(selectedProduct.getProduct());
        }

        //updateProductQuantities(customerOrder);
    }

    public CustomerOrder customerOrderBuilder(CustomerOrderDto customerOrderDto, OrderDetailsDto orderDetailsDto, User user){
        CustomerOrder customerOrder = new CustomerOrder();
        OrderDetails orderDetails = orderDetailsMapper.map(orderDetailsDto);
        orderDetailsRepository.save(orderDetails);

        customerOrder.setOrderDetails(orderDetails);
        customerOrder.setUser(user);
        customerOrder.setOrderStatus(OrderStatus.ORDER_STATUS_LAUNCHED);
        return customerOrder;
    }

//    public void updateProductQuantities(CustomerOrder customerOrder){
//        List<SelectedProduct> selectedProducts = customerOrder.getSelectedProducts();
//        for(SelectedProduct selectedProduct : selectedProducts){
//            int availableQuantity = selectedProduct.getProduct().getQuantity();
//            int amountBought = selectedProduct.getQuantity();
//            selectedProduct.getProduct().setQuantity(availableQuantity-amountBought);
//            productRepository.save(selectedProduct.getProduct());
//        }
//    }

    public Optional<CustomerOrderDto> getLaunchedCustomerOrderDto(String authenticatedUserEmail) {
        Optional<CustomerOrder> optionalCustomerOrder =
                customerOrderRepository
                        .findCustomerOrderByOrderStatusAndUserEmail(
                                OrderStatus.ORDER_STATUS_LAUNCHED,
                                authenticatedUserEmail);
        if (optionalCustomerOrder.isEmpty()){
            return Optional.empty();
        }

        CustomerOrder customerOrder = optionalCustomerOrder.get();
        System.out.println(customerOrder);
        CustomerOrderDto customerOrderDto = customerOrderMapper.map(customerOrder);
        Optional<CustomerOrderDto> optionalCustomerOrderDto = Optional.of(customerOrderDto);
        customerOrder.setOrderStatus(OrderStatus.ORDER_STATUS_CONFIRMED);
        customerOrderRepository.save(customerOrder);
        return optionalCustomerOrderDto;
    }
}
