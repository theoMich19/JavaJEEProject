package com.nextu.mesdevis.service;

import com.nextu.mesdevis.dto.CategoryDto;
import com.nextu.mesdevis.entity.Category;
import com.nextu.mesdevis.entity.Client;
import com.nextu.mesdevis.entity.Product;
import com.nextu.mesdevis.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 *  Services continent les méthodes qui effectuent des opérations complexes, manipulent les données,
 *  et interagissent avec la couche de persistance pour accéder ou mettre à jour des données.
 *  Ici nous sommes dans le service de Category
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Service methods

    public List<CategoryDto> getAllCategories() {
        try {
            List<Category> category = categoryRepository.findAll();
            return category.stream().map(this::convertToDto).collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des category");
        }
    }

    public CategoryDto getCategoryById(Long categoryId) {
         try {
             Category category = categoryRepository.findById(categoryId)
                     .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
             return convertToDto(category);
         } catch (Exception e) {
             throw new RuntimeException("Erreur lors de la récupération de la category");
         }
    }

    public CategoryDto getCategoryByName(String categoryName) {
        try {
            Category category = categoryRepository.findByName(categoryName);
            return convertToDto(category);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération de la category");
        }
    }

    public CategoryDto createCategory(CategoryDto categoryDto) {
        try {
            Category category = convertToEntity(categoryDto);
            Category savedCategory = categoryRepository.save(category);
            return convertToDto(savedCategory);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la sauvegarde de la category");
        }
    }

    public CategoryDto updateCategory(Long categoryId, CategoryDto updatedCategoryDto) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + categoryId));
        if (category.getIdCategory() != -1) {
            category.setName(updatedCategoryDto.getName());
            categoryRepository.save(category);
            return convertToDto(category);
        }
        return null;
    }

    public void deleteCategory(Long categoryId) {
        try {
            categoryRepository.deleteById(categoryId);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la suppression de la category");
        }
    }


    private CategoryDto convertToDto(Category category) {
        // Get all list of id products
        List<Long> productIds = category.getProduct() != null
                ? category.getProduct().stream().map(Product::getIdProduct).toList()
                : List.of();


        return new CategoryDto(
            category.getIdCategory(),
            category.getName(),
            productIds
        );
    }


    private Category convertToEntity(CategoryDto categoryDto) {
        return new Category(categoryDto.getName());
    }
}
