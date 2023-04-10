package com.sda.OnlineShop.mapper;
import com.sda.OnlineShop.dto.CustomerOrderDto;
import com.sda.OnlineShop.dto.SelectedProductDto;
import com.sda.OnlineShop.entities.CustomerOrder;
import com.sda.OnlineShop.entities.SelectedProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerOrderMapper {
    @Autowired
    private SelectedProductMapper selectedProductMapper;
    @Autowired
    private OrderDetailsMapper orderDetailsMapper;

    public CustomerOrderDto map(CustomerOrder customerOrder){
        CustomerOrderDto customerOrderDto = new CustomerOrderDto();
        customerOrderDto.setId(String.valueOf(customerOrder.getId()));

        List<SelectedProductDto> selectedProductDtos = getSelectedProductDtos(customerOrder.getSelectedProducts());
        customerOrderDto.setSelectedProductDtos(selectedProductDtos);
        customerOrderDto.setOrderDetailsDto(orderDetailsMapper.map(customerOrder.getOrderDetails()));

        Integer subtotal = computeSubtotal(selectedProductDtos);
        customerOrderDto.setSubtotal(String.valueOf(subtotal));
        customerOrderDto.setShipping("50");
        customerOrderDto.setTotal(String.valueOf(subtotal + 50));

        return customerOrderDto;
    }

    private Integer computeSubtotal(List<SelectedProductDto> selectedProductDtoList) {
        int subtotal = 0;
        for(SelectedProductDto selectedProductDto : selectedProductDtoList){
            int priceTimesQuantity = Integer.parseInt(selectedProductDto.getPriceTimesQuantity());
            subtotal += priceTimesQuantity;
        }
        return subtotal;
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
