package com.am.ProductService.command_api.aggregate;

import com.am.ProductService.command_api.commands.CreateProductCommand;
import com.am.ProductService.command_api.events.ProductCreatedEvent;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
@NoArgsConstructor
public class ProductAggregate {
    
    @AggregateIdentifier
    private String productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProduct) {
        //Can perform all the validation

        //perform all the business logic


        //after business logic, create the event
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        BeanUtils.copyProperties(createProduct,productCreatedEvent);

        //Publish the Event
        AggregateLifecycle.apply(productCreatedEvent);
    }

    //update the values on sourcing
    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent){
        this.quantity = productCreatedEvent.getQuantity();
        this.productId = productCreatedEvent.getProductId();
        this.price = productCreatedEvent.getPrice();
        this.name = productCreatedEvent.getName();
    }
}
