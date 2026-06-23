package org.yearup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yearup.models.OrderLineItem;

public interface OrderLineItemRepository extends JpaRepository<OrderLineItem, Long> {
    OrderLineItem findByOrderId(Long orderId);
    void deleteByOrderId(Long orderId);
}
