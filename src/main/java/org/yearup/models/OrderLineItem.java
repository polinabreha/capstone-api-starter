package org.yearup.models;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_line_items")
public class OrderLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_line_item_id")
    private Integer orderLineItemId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @Column(name = "product_id", nullable = false)
    private Integer productId;
    @Column(name = "sales_price", nullable = false)
    private BigDecimal salesPrice;
    @Column(name = "quantity", nullable = false)
    private Integer quantity;
    @Column(name = "discount", nullable = false)
    private BigDecimal discount;

    public OrderLineItem() {}

    public OrderLineItem(Integer orderLineItemId, Order order, Integer productId, BigDecimal salesPrice, Integer quantity, BigDecimal discount) {
        this.orderLineItemId = orderLineItemId;
        this.order = order;
        this.productId = productId;
        this.salesPrice = salesPrice;
        this.quantity = quantity;
        this.discount = discount;
    }

    public Integer getOrderLineItemId() {
        return orderLineItemId;
    }

    public void setOrderLineItemId(Integer orderLineItemId) {
        this.orderLineItemId = orderLineItemId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public BigDecimal getSalesPrice() {
        return salesPrice;
    }

    public void setSalesPrice(BigDecimal salesPrice) {
        this.salesPrice = salesPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}
