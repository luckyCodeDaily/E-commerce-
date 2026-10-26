package com.ecommerce.product.Controllers;


import com.ecommerce.product.Dto.ProductRequest;
import com.ecommerce.product.Dto.ProductResponse;
import com.ecommerce.product.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductControllers {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> Create(@RequestBody ProductRequest productRequest) {
        return new ResponseEntity<ProductResponse>(productService.create(productRequest), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<ProductResponse>> GetAll() {
        return ResponseEntity.ok(productService.GetAllProduct());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> Updated(@PathVariable Long id,
                                                   @RequestBody ProductRequest productRequest) {
      return productService.updatedProduct(id, productRequest).map(ResponseEntity::ok)
                .orElseGet(()->ResponseEntity.notFound().build());

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>DeleteProduct(@PathVariable Long id){
      Boolean deleted =productService.deleteProduct(id);
        return deleted ? ResponseEntity.noContent().build(): ResponseEntity.notFound().build();
    }

@GetMapping("/search")
public ResponseEntity<List<ProductResponse>> SearchByKeyword(@RequestParam String keyword){
        return ResponseEntity.ok(productService.searchProduct(keyword));

}
}
