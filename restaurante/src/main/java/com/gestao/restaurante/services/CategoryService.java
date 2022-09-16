package com.gestao.restaurante.services;

import com.gestao.restaurante.dtos.CategoryDto;
import com.gestao.restaurante.models.CategoryModel;
import com.gestao.restaurante.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public CategoryModel save (CategoryModel categoryModel){
        return categoryRepository.save(categoryModel);
    }
    public boolean existsByDescricao(String descricao) {
        return categoryRepository.existsByDescricao(descricao);
    }
    public List<CategoryModel> findAll() {
        return categoryRepository.findAll();
    }
    public Optional<CategoryModel> findByDescricao(String descricao){
        return categoryRepository.findByDescricao(descricao);
    }

    public void delete (CategoryModel categoryModel){
        categoryRepository.delete(categoryModel);
    }

    public List<CategoryDto> getAll(){
        return categoryRepository.getAll();
    }

    public Optional<CategoryDto> getOne(String descricao){
        return categoryRepository.getOne(descricao);
    }
}