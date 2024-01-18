package br.com.projetoIfood.domain.Product;

import br.com.projetoIfood.domain.Category.Category;

public record ProductDTO (String title, String description, String ownerId, Integer price, String categoryId) {

}