package com.ecommerce.order.Service;

import com.ecommerce.order.Dto.OrderItemDto;
import com.ecommerce.order.Dto.OrderResponse;
import com.ecommerce.order.Models.*;
import com.ecommerce.order.Repository.OrderRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartItemService cartItemService;

        public Optional<OrderResponse> addOrder(Long userID) {
            List<Cartitem> cartitems = cartItemService.fetchItemfromCartById(userID);
            if (cartitems.isEmpty()){

                return Optional.empty();
            }
          //  Optional<Users>usersOptional = userRepo.findById(Long.valueOf(userID));
            //if (usersOptional.isEmpty()){
              //  return Optional.empty();
            //}
            //Users users = usersOptional.get();
            BigDecimal totalPrice = cartitems.stream().map(Cartitem::getPrice).reduce(BigDecimal.ZERO,BigDecimal::add);
            cartItemService.clearCart(userID);

            Order order = new Order();
            order.setUserId(Long.valueOf(userID));
            order.setStatus(OrderStatus.CONFIRMED);
            order.setTotalAmount(totalPrice);
            List<OrderItem>orderItems = cartitems.stream().map(item->new OrderItem(
                    null,
                    item.getProductId(),
                    item.getQuantity(),
                    item.getPrice(),
                    order

            )).toList();
            order.setOrderItems(orderItems);
            Order saved = orderRepository.save(order);
            cartItemService.clearCart(userID);

return Optional.of(MapfromOrderResponse(saved));
        }

    private OrderResponse MapfromOrderResponse(Order order) {
           return new OrderResponse(
                    order.getId(),
                    order.getTotalAmount(),
                    order.getStatus(),
                    order.getOrderItems().stream().map(orderItem -> new OrderItemDto(
                            orderItem.getId(),
                            orderItem.getProductId(),
                            orderItem.getQuantity(),
                            orderItem.getPrice(),
                            orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity()))

                    ))
                            .toList(),order.getCreatedAt()
            );
    }
}

