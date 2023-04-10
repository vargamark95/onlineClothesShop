package com.sda.OnlineShop.mapper;

import com.sda.OnlineShop.dto.OrderDetailsDto;
import com.sda.OnlineShop.entities.OrderDetails;
import com.sda.OnlineShop.entities.PaymentMethod;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class OrderDetailsMapper {

    public OrderDetails map(OrderDetailsDto orderDetailsDto){
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setContactPerson(orderDetailsDto.getContactPerson());
        orderDetails.setPhoneNumber(orderDetailsDto.getPhoneNumber());
        orderDetails.setStreet(orderDetailsDto.getStreet());
        orderDetails.setCity(orderDetailsDto.getCity());
        orderDetails.setCountry(orderDetailsDto.getCountry());
        orderDetails.setPostcode(orderDetailsDto.getPostcode());
        orderDetails.setLocalDate(LocalDate.now());
        orderDetails.setPaymentMethod(PaymentMethod.valueOf(orderDetailsDto.getPaymentMethod()));
        return orderDetails;
    }

    public OrderDetailsDto map(OrderDetails orderDetails){
        OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
        orderDetailsDto.setContactPerson(orderDetails.getContactPerson());
        orderDetailsDto.setPhoneNumber(orderDetails.getPhoneNumber());
        orderDetailsDto.setStreet(orderDetails.getStreet());
        orderDetailsDto.setCity(orderDetails.getCity());
        orderDetailsDto.setCountry(orderDetails.getCountry());
        orderDetailsDto.setPostcode(orderDetails.getPostcode());
        orderDetailsDto.setLocalDate(String.valueOf(orderDetails.getLocalDate()));
        orderDetailsDto.setPaymentMethod(String.valueOf(orderDetails.getPaymentMethod()));
        return orderDetailsDto;
    }
}
