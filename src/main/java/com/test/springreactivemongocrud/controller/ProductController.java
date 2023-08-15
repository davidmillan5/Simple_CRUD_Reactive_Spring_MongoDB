package com.test.springreactivemongocrud.controller;

import com.test.springreactivemongocrud.dto.ProductDto;
import com.test.springreactivemongocrud.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private ProductService productService;

    @GetMapping
    public Flux<ProductDto> getProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductDto> getProduct(@PathVariable String id){
        return productService.getProductById(id);
    }

    @GetMapping("/product-range")
    public Flux<ProductDto> getProductInRange(@RequestParam("min") double min, @RequestParam("max") double max){
        return productService.getProductInRange(min,max);
    }

    @PostMapping
    public Mono <ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDtoMono){
        return productService.saveProduct(productDtoMono);
    }

    @PutMapping("/update/{id}")
    public Mono <ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDtoMono, @PathVariable String id){
        return productService.updateProduct(productDtoMono,id);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteProduct(@PathVariable String id){
        return productService.deleteProduct(id);
    }

}
