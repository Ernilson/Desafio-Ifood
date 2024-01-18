package br.com.projetoIfood.service;

import br.com.projetoIfood.domain.Category.Category;
import br.com.projetoIfood.domain.Category.CategoryDTO;
import br.com.projetoIfood.utils.exceptions.CategoryNotFoundException;
import br.com.projetoIfood.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository){
        this.repository = repository;
    }

    public Category gravar(CategoryDTO dto){
        Category newCategory = new Category(dto);
        this.repository.save(newCategory);
        return newCategory;
    }

    public Category update(String id, CategoryDTO categoryDTO){
        Category category = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);
        if (!categoryDTO.title().isEmpty()) category.setTitle(categoryDTO.title());
        if (!categoryDTO.description().isEmpty()) category.setDescription(categoryDTO.description());
        this.repository.save(category);
        return category;
    }

    public void delete(String id){
        Category category = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);
        this.repository.delete(category);
    }

    public List<Category> getAll(){
        return this.repository.findAll();
    }

    public Optional<Category> findById(String id) {
        return this.repository.findById(id);
    }
}
