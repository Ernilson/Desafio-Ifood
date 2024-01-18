package br.com.projetoIfood.controller;

import br.com.projetoIfood.domain.Category.Category;
import br.com.projetoIfood.domain.Category.CategoryDTO;
import br.com.projetoIfood.domain.Product.Product;
import br.com.projetoIfood.domain.Product.ProductDTO;
import br.com.projetoIfood.repository.CategoryRepository;
import br.com.projetoIfood.service.CategoryService;
import br.com.projetoIfood.service.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService service;

    public CategoryController(CategoryService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll(){
        List<Category> categories = this.service.getAll();
        return ResponseEntity.ok().body(categories);
    }
    @PostMapping
    public ResponseEntity<Category> gravaCategory(@RequestBody CategoryDTO categoryDTO){
        Category newCategory = service.gravar(categoryDTO);
        return ResponseEntity.ok().body(newCategory);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable("id") String id, @RequestBody CategoryDTO categoryDTO){
       Category updateCategory = this.service.update(id, categoryDTO);
        return ResponseEntity.ok().body(updateCategory);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable("id") String id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
