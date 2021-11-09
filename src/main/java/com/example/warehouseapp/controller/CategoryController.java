package com.example.warehouseapp.controller;

import com.example.warehouseapp.entity.Category;
import com.example.warehouseapp.payload.ApiResponse;
import com.example.warehouseapp.payload.CategoryDTO;
import com.example.warehouseapp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping
    public HttpEntity<?>addCategory(@RequestBody CategoryDTO categoryDTO){
        ApiResponse response = categoryService.save(categoryDTO);
        return ResponseEntity.status(response.isSuccess() ? 201:409).body(response);
    }

    @GetMapping
    public HttpEntity<?> allCategory(){
         List<Category> all = categoryService.getAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOneCategory(@PathVariable Integer id){
        ApiResponse oneById = categoryService.getOneById(id);
        return ResponseEntity.ok(oneById);
    }


    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteCategory(@PathVariable Integer id ){
            ApiResponse deleted = categoryService.delete(id);
            return  ResponseEntity.ok(deleted);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editCategory(@PathVariable Integer id, @RequestBody CategoryDTO categoryDTO){

        ApiResponse edited = categoryService.edit(id,categoryDTO);

        return ResponseEntity.status(edited!=null?202:409).body(edited);
    }


}
