package com.app.Service;

import com.app.Repository.ProductRepository;
import com.app.dto.ProductRequest;
import com.app.dto.ProductResponse;
import com.app.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;


    public ProductResponse create(ProductRequest productRequest) {
        Product product = new Product();
        updateProductfromRequest(product,productRequest);
        Product savedProduct = productRepository.save(product);
        return MapProductfromResponse(savedProduct);
    }

    private ProductResponse MapProductfromResponse(Product savedProduct) {
        ProductResponse response = new ProductResponse();
        response.setId(savedProduct.getId());
        response.setName(savedProduct.getName());
        response.setActive(savedProduct.getActive());
        response.setCategory(savedProduct.getCategory());
        response.setDescription(savedProduct.getDescription());
        response.setPrice(savedProduct.getPrice());
        response.setImgUrl(savedProduct.getImgUrl());
        response.setStockQuality(savedProduct.getStockQuality());

return response;
    }

    private void updateProductfromRequest(Product product, ProductRequest productRequest) {
        product.setName(productRequest.getName());
        product.setCategory(productRequest.getCategory());
        product.setDescription(productRequest.getDescription());
        product.setPrice(productRequest.getPrice());
        product.setImgUrl(productRequest.getImgUrl());
        product.setStockQuality(productRequest.getStockQuality());
    }


    public  Optional< ProductResponse> updatedProduct(Long id, ProductRequest productRequest) {
       return productRepository.findById(id).map(exitingProduct->{
            updateProductfromRequest(exitingProduct,productRequest);
            Product saved = productRepository.save(exitingProduct);
            return MapProductfromResponse(saved);
        });

    }

    public List<ProductResponse> GetAllProduct() {
        return productRepository.findByActiveTrue().stream().map(this::MapProductfromResponse).collect(Collectors.toList());
    }


    public Boolean deleteProduct(Long id) {
      return productRepository.findById(id).map(product -> {
           product.setActive(false);
           productRepository.save(product);
         return true;
       }).orElse(false);
    }
    public List<ProductResponse> searchProduct(String Keyword){
        return productRepository.SearchByKeyword(Keyword).stream().map(this::MapProductfromResponse).collect(Collectors.toList());

    }
}
