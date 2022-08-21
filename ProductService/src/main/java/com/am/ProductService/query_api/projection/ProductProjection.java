package com.am.ProductService.query_api.projection;

import com.am.ProductService.core_api.data.entity.Product;
import com.am.ProductService.core_api.data.repository.ProductRepository;
import com.am.ProductService.core_api.model.ProductRestModel;
import com.am.ProductService.query_api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    private ProductRepository productRepository;

    public ProductProjection(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery queryProductsQuery){
        List<Product> products = productRepository.findAll();

        return products.stream().map(p -> ProductRestModel.builder()
                .name(p.getName())
                .quantity(p.getQuantity())
                .price(p.getPrice())
                .build()
        ).collect(Collectors.toList());
    }
}
