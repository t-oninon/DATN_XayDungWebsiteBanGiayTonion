package com.nico.store.store.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.nico.store.store.domain.Brand;
import com.nico.store.store.domain.Category;
import com.nico.store.store.dto.ArticleCusDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nico.store.store.domain.Article;

public interface ArticleService {

	List<ArticleCusDTO> findAllArticles();
	
	Page<Article> findArticlesByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh, List<String> sizes,
			List<Long> categories, List<Long> brands, String search);

	List<ArticleCusDTO> findAllArticleCus();
		
	List<Article> findFirstArticles();

	Article findArticleById(Long id);
	
	Article saveArticle(Article article);

	void deleteArticleById(Long id);
	
	List<String> getAllSizes();

	List<Category> getAllCategories();

	List<Brand> getAllBrands();

	List<Map<String, Object>> findListBrandByArticleId(Long articleId);

	List<Map<String, Object>> findListCategoryByArticleId(Long articleId);

}
