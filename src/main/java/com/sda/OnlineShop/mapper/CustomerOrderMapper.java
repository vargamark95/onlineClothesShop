package com.sda.OnlineShop.mapper;

import com.sda.OnlineShop.dto.CustomerOrderDto;
import com.sda.OnlineShop.dto.SelectedProductDto;
import com.sda.OnlineShop.entities.CustomerOrder;
import com.sda.OnlineShop.entities.PaymentMethod;
import com.sda.OnlineShop.entities.SelectedProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerOrderMapper {
    @Autowired
    private SelectedProductMapper selectedProductMapper;

    public CustomerOrder map(CustomerOrderDto customerOrderDto){
        CustomerOrder customerOrder = new CustomerOrder();
        customerOrder.setContactPerson(customerOrderDto.getContactPerson());
        customerOrder.setAddress(customerOrderDto.getAddress());
        customerOrder.setPostcode(customerOrderDto.getPostcode());
        customerOrder.setCity(customerOrderDto.getCity());
        customerOrder.setCounty(customerOrderDto.getCounty());
        customerOrder.setCountry(customerOrderDto.getCountry());
        customerOrder.setPhoneNumber(customerOrderDto.getPhoneNumber());
        customerOrder.setPaymentMethod(PaymentMethod.valueOf(customerOrderDto.getPaymentMethod()));

        return customerOrder;
    }

    public CustomerOrderDto map(CustomerOrder customerOrder){
        CustomerOrderDto customerOrderDto = new CustomerOrderDto();
        customerOrderDto.setAddress(customerOrder.getAddress());
        customerOrderDto.setCity(customerOrder.getCity());
        customerOrderDto.setCounty(customerOrderDto.getCounty());
        customerOrderDto.setCountry(customerOrder.getCountry());
        customerOrderDto.setPostcode(customerOrder.getPostcode());
        customerOrderDto.setContactPerson(customerOrder.getContactPerson());
        customerOrderDto.setPaymentMethod(String.valueOf(customerOrder.getPaymentMethod()));
        customerOrderDto.setPhoneNumber(customerOrder.getPhoneNumber());
        customerOrderDto.setId(String.valueOf(customerOrder.getId()));
        customerOrderDto.setLocalDate(String.valueOf(customerOrder.getLocalDate()));
        customerOrderDto.setSelectedProductDtos(getSelectedProductDtos(customerOrder.getSelectedProducts()));

        return customerOrderDto;
    }

    private List<SelectedProductDto> getSelectedProductDtos(List<SelectedProduct> selectedProducts){
        List<SelectedProductDto> selectedProductDtos = new ArrayList<>();

        for(SelectedProduct selectedProduct : selectedProducts){
            SelectedProductDto selectedProductDto = selectedProductMapper.map(selectedProduct);
            selectedProductDtos.add(selectedProductDto);
        }
        return selectedProductDtos;
    }

}
