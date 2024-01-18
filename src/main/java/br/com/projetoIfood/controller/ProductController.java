package br.com.projetoIfood.controller;

import br.com.projetoIfood.domain.Category.Category;
import br.com.projetoIfood.domain.Product.Product;
import br.com.projetoIfood.domain.Product.ProductDTO;
import br.com.projetoIfood.service.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }
    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        List<Product> categories = this.service.getAll();
        return ResponseEntity.ok().body(categories);
    }
    @PostMapping
    public ResponseEntity<Product> gravaProduct(@RequestBody ProductDTO productDTO){
        Product newProduct = service.gravarProduto(productDTO);
        return ResponseEntity.ok().body(newProduct);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") String id, @RequestBody ProductDTO productDTO){
        Product updateProduct = this.service.update(id, productDTO);
        return ResponseEntity.ok().body(updateProduct);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable("id") String id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
