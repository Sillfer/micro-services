package com.simplon.service;

import com.simplon.dto.InventoryResponse;
import com.simplon.dto.OrderLineItemsDto;
import com.simplon.dto.OrderRequest;
import com.simplon.model.Order;
import com.simplon.model.OrderLineItems;
import com.simplon.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final WebClient webClient;

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
//        System.out.println("Order placed successfully");
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList()
                .stream()
                .map(OrderLineItems::getSkuCode)
                .toList();
//        call inventory service and place order if in stock
        InventoryResponse[] inventoryResponseArray = webClient.get()
                .uri("http://localhost:8082/api/inventory", uriBuilder -> uriBuilder
                        .queryParam("skuCode", skuCodes)
                        .build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class) // convert to boolean
                .block();   // blocking call to wait for response

//        assert inventoryResponseArray != null;
        assert inventoryResponseArray != null;
        boolean allProductsInStock = Arrays
                .stream(inventoryResponseArray)
                .allMatch(InventoryResponse::isInStock);

        if (allProductsInStock) {
            orderRepository.save(order);
            System.out.println("Order placed successfully");
        } else {
            throw new IllegalArgumentException("Product is out of stock, Try again later");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        return orderLineItems;
    }
}
