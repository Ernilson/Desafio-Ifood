package br.com.projetoIfood.service;

import br.com.projetoIfood.domain.Category.Category;
import br.com.projetoIfood.domain.Product.Product;
import br.com.projetoIfood.domain.Product.ProductDTO;
import br.com.projetoIfood.repository.ProductRepository;
import br.com.projetoIfood.service.aws.AwsSnsService;
import br.com.projetoIfood.service.aws.MessageDTO;
import br.com.projetoIfood.utils.exceptions.CategoryNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final CategoryService categoryService;

    private final AwsSnsService snsService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService,
                          AwsSnsService snsService){
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.snsService = snsService;
    }

    public List<Product> getAll(){
        return this.productRepository.findAll();
    }

    public Product gravarProduto(ProductDTO productDTO){
        Category category = this.categoryService.findById((productDTO.categoryId()))
                .orElseThrow(() -> new NoSuchElementException("Categoria não encontrada"));

        Product newProduct = new Product(productDTO);
        newProduct.setCategory(category);
        this.productRepository.save(newProduct);
        this.snsService.publish(new MessageDTO(newProduct.getOwnerId()));
        return newProduct;
    }

    public Product update(String id, ProductDTO produtctDTO){
        Product newProduct = this.productRepository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);

        if (produtctDTO.categoryId() != null){
            this.categoryService.findById(produtctDTO.categoryId())
                    .ifPresent(newProduct::setCategory);
        }
        if (!produtctDTO.title().isEmpty()) newProduct.setTitle(produtctDTO.title());
        if (!produtctDTO.description().isEmpty()) newProduct.setDescription(produtctDTO.description());
        if (!(produtctDTO.price() == null)) newProduct.setPrice(produtctDTO.price());

        this.productRepository.save(newProduct);
        this.snsService.publish(new MessageDTO(newProduct.getOwnerId()));
        return newProduct;
    }

    public void delete(String id){
        Product product = this.productRepository.findById(id).
                orElseThrow(CategoryNotFoundException::new);

        this.productRepository.delete(product);
    }

}
