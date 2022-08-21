package com.am.ProductService.command_api.controller;

import com.am.ProductService.command_api.commands.CreateProductCommand;
import com.am.ProductService.core_api.model.ProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import static java.util.UUID.*;


@RestController
@RequestMapping("/products")
public class ProductCommandController {

    private CommandGateway commandGateway;

    public ProductCommandController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String addProduct(@RequestBody ProductRestModel productRestModel){

        //create a product command payload
        CreateProductCommand createProductCommand =
                CreateProductCommand.builder()
                        .productId(randomUUID().toString())
                        .name(productRestModel.getName())
                        .price(productRestModel.getPrice())
                        .quantity(productRestModel.getQuantity())
                        .build();

        //send the product command to command gateway
        String result = commandGateway.sendAndWait(createProductCommand);

        return result;
    }

}
