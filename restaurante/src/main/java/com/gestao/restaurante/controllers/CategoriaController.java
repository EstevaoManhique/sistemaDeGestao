package com.gestao.restaurante.controllers;

import com.gestao.restaurante.dtos.CategoryDto;
import com.gestao.restaurante.models.CategoryModel;
import com.gestao.restaurante.services.CategoryService;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/categoria")
public class CategoriaController {

    final CategoryService categoryService;

    public CategoriaController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Object> saveCategory(@RequestBody @Valid CategoryDto categoriaDto){

        if(categoryService.existsByDescricao(categoriaDto.getDescricao())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Categoy already exists");
        }
        CategoryModel categoryModel = new CategoryModel();
        BeanUtils.copyProperties(categoriaDto, categoryModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(categoryModel));
    }

    @GetMapping
    public ResponseEntity<Object> getAllCategory(){
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.getAll());
    }

    @GetMapping("/{descricao}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "descricao") String descricao){

        Optional<CategoryDto> optionalCategoryModel = categoryService.getOne(descricao);
        if(optionalCategoryModel.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("Catgegoria ja existe");
        }
        CategoryModel categoryModel = new CategoryModel();
        BeanUtils.copyProperties(optionalCategoryModel.get(), categoryModel);
        return ResponseEntity.ok().body(optionalCategoryModel.get());
    }

    @PatchMapping("/{descricao}")
    public ResponseEntity<Object> updateCategory (@PathVariable(value = "descricao") String descricao,
                                                  @RequestBody @Valid CategoryDto categoryDto){
        Optional<CategoryDto> categoryOptional = categoryService.getOne(descricao);
        if (categoryOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict:Category not exists");
        }
        CategoryModel categoryModel = new CategoryModel();
        BeanUtils.copyProperties(categoryDto, categoryModel);
        categoryModel.setId(categoryOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.save(categoryModel));
    }

    @DeleteMapping("/{descricao}")
    public ResponseEntity<Object> deleteCategory(@PathVariable(value = "descricao") String descricao){
        Optional<CategoryModel> categoryOptional = categoryService.findByDescricao(descricao);
        if (!categoryOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conflict:Category not found");
        }
        categoryService.delete(categoryOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Category deleted successfully!");
    }
}