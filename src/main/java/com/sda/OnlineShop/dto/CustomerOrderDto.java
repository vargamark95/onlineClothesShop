package com.sda.OnlineShop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CustomerOrderDto {
    private String id;
    private List<SelectedProductDto> selectedProductDtos;
    private OrderDetailsDto orderDetailsDto;
    private String orderStatus;
    private String subtotal;
    private String shipping;
    private String total;
}
