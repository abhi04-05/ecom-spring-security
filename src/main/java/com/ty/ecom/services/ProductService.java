package com.ty.ecom.services;

import com.ty.ecom.pojos.EcomResponse;
import com.ty.ecom.pojos.ProductRequest;
import com.ty.ecom.models.Product;
import com.ty.ecom.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public EcomResponse saveProduct(ProductRequest productRequest) {
        Product product = Product.builder().productName(productRequest.getProductName())
                .productPrice(productRequest.getProductPrice()).build();
        productRepository.save(product);
        return EcomResponse.builder().isError(Boolean.FALSE).message("Product added to cart").data(productRequest).build();
    }

    public EcomResponse findProductByName(String productName) {
        Optional<Product> product = productRepository.findByProductName(productName);
        return product.isPresent() ? EcomResponse.builder().isError(Boolean.FALSE).message("Success")
                .data(product.get()).build() : EcomResponse.builder().isError(Boolean.TRUE).message("Product not found").data(Optional.empty().get()).build();
    }

    public EcomResponse findProductById(Integer productId) {
        Optional<Product> product = productRepository.findByProductId(productId);
        return product.isPresent() ? EcomResponse.builder().isError(Boolean.FALSE).message("Success")
                .data(product.get()).build() : EcomResponse.builder().isError(Boolean.TRUE)
                .message("Product not found").data(Optional.empty().get()).build();
    }

}
