package com.nextu.mesdevis.service;

import com.nextu.mesdevis.dto.ProductDto;
import com.nextu.mesdevis.entity.Category;
import com.nextu.mesdevis.entity.DetailsDevis;
import com.nextu.mesdevis.entity.Product;
import com.nextu.mesdevis.repository.CategoryRepository;
import com.nextu.mesdevis.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Services continent les méthodes qui effectuent des opérations complexes, manipulent les données,
 *  et interagissent avec la couche de persistance pour accéder ou mettre à jour des données.
 *  Ici nous sommes dans le service de Devis
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Service methods

    public List<ProductDto> getAllProducts() {

        try {
            List<Product> product =  productRepository.findAll();
            return product.stream().map(this::convertToDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des produits");
        }
    }

    public ProductDto getProductById(Long productId) {
        try {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
            return convertToDto(product);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération du produit");
        }
    }

    public ProductDto createProduct(ProductDto productDto) {
        try {
            Product product = convertToEntity(productDto);
            Product savedProduct = productRepository.save(product);
            return convertToDto(savedProduct);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la sauvegarde du produit");
        }
    }

    public ProductDto updateProduct(Long productId, ProductDto updatedProductDto) {
        try {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

            if (product.getIdProduct() != -1) {
                product.setName(updatedProductDto.getName());
                product.setProductCode(updatedProductDto.getProductCode());
                product.setStock(updatedProductDto.getStock());

                productRepository.save(product);
                return convertToDto(product);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la sauvegarde du produit");
        }
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    private ProductDto convertToDto(Product product) {


        List<Long> idDetailsDevis = (product.getDetailsDevisList() != null)
                ? product.getDetailsDevisList().stream()
                .map(detailsDevis -> Long.valueOf(detailsDevis.getIdDetailsDevis()))
                .collect(Collectors.toList())
                : Collections.emptyList();

        return new ProductDto(
                product.getIdProduct(),
                product.getName(),
                product.getProductCode(),
                product.getStock(),
                product.getCategory().getIdCategory(),
                idDetailsDevis
        );
    }

    private Product convertToEntity(ProductDto productDto) {
        Product product = new Product(
                productDto.getName(),
                productDto.getProductCode(),
                productDto.getStock()
        );

        if (productDto.getIdCategory() != 0) {
            Category category = categoryRepository.findById(productDto.getIdCategory())
                    .orElseThrow(() -> new RuntimeException("Category not found with id: " + productDto.getIdCategory()));
            product.setCategory(category);
        }

        return product;
    }
}