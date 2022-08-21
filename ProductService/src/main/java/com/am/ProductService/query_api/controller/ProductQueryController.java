package com.am.ProductService.query_api.controller;

import com.am.ProductService.core_api.model.ProductRestModel;
import com.am.ProductService.query_api.queries.GetProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    private QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }
    
    @GetMapping
    public List<ProductRestModel> getAllProducts(){
        GetProductsQuery getProductsQuery = new GetProductsQuery();

        List<ProductRestModel> products = queryGateway.query(getProductsQuery, ResponseTypes.multipleInstancesOf(ProductRestModel.class)).join();

        return products;
    }
}
