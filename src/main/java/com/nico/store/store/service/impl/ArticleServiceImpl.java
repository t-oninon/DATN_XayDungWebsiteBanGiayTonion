package com.nico.store.store.service.impl;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

import com.nico.store.store.domain.Brand;
import com.nico.store.store.domain.Category;
import com.nico.store.store.dto.ArticleCusDTO;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nico.store.store.domain.Article;
import com.nico.store.store.repository.ArticleRepository;
import com.nico.store.store.repository.ArticleSpecification;
import com.nico.store.store.service.ArticleService;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Value("${articleservice.featured-items-number}")
	private int featuredArticlesNumber;

	@Override
	public List<ArticleCusDTO> findAllArticles() {
		List<Article> list  = articleRepository.findAllEagerBy();
		List<ArticleCusDTO> result = new ArrayList<>();

		list.forEach(e -> {
			ArticleCusDTO articleCusDTO = new ArticleCusDTO();
//			articleCusDTO.setId(e.getId());
			articleCusDTO.setTitle(e.getTitle());
			articleCusDTO.setStock(e.getStock());
			articleCusDTO.setPrice(e.getPrice());
			articleCusDTO.setPicture(e.getPicture());
			result.add(articleCusDTO);
		});
		return result;
	}
	
	@Override
	public Page<Article> findArticlesByCriteria(Pageable pageable, Integer priceLow, Integer priceHigh, 
										List<String> sizes, List<Long> categories, List<Long> brands, String search) {
		Page<Article> page = articleRepository.findAll(ArticleSpecification.filterBy(priceLow, priceHigh, sizes, categories, brands, search), pageable);
        return page;		
	}

	@Override
	public List<ArticleCusDTO> findAllArticleCus() {
		List<Map<String, Object>> list  = articleRepository.findAllArticle();
		List<ArticleCusDTO> result = new ArrayList<>();

		list.forEach(e -> {
			ArticleCusDTO articleCusDTO = new ArticleCusDTO();
			articleCusDTO.setId((BigInteger) e.get("id"));
			articleCusDTO.setTitle((String) e.get("title"));
			articleCusDTO.setStock((int) e.get("stock"));
			articleCusDTO.setPrice((Double)  e.get("price"));
			articleCusDTO.setPicture((String)  e.get("picture"));
			articleCusDTO.setSizes(Arrays.stream(Objects.toString(e.get("lstSize")).split(", ")).collect(Collectors.toSet()));
			articleCusDTO.setLstBrandName(Arrays.stream(Objects.toString(e.get("lstBrand")).split(", ")).collect(Collectors.toSet()));
			articleCusDTO.setLstCategoryName(Arrays.stream(Objects.toString(e.get("lstCategory")).split(", ")).collect(Collectors.toSet()));

			result.add(articleCusDTO);
		});
		return result;
	}

	@Override
	public List<Article> findFirstArticles() {
		return articleRepository.findAll(PageRequest.of(0,featuredArticlesNumber)).getContent(); 
	}

	@Override
	public Article findArticleById(Long id) {
		Optional<Article> opt = articleRepository.findById(id);
		return opt.get();
	}

	@Override
	@CacheEvict(value = { "sizes", "categoryArticles", "brandArticles" }, allEntries = true)
	public Article saveArticle(Article article) {
		return articleRepository.save(article);
	}
	
	@Override
	@CacheEvict(value = { "sizes", "categoryArticles", "brandArticles" }, allEntries = true)
	public void deleteArticleById(Long id) {
		articleRepository.deleteById(id);		
	}

	
	@Override
	@Cacheable("sizes")
	public List<String> getAllSizes() {
		return articleRepository.findAllSizes();
	}

	@Override
	@Cacheable("categories")
	public List<Category> getAllCategories() {
		return articleRepository.findAllCategories();
	}

	@Override
	@Cacheable("brands")
	public List<Brand> getAllBrands() {
		return articleRepository.findAllBrands();
	}

	@Override
	public List<Map<String, Object>> findListCategoryByArticleId(Long articleId) {
		return articleRepository.findListCategoryByArticleId(articleId);
	}

	@Override
	public List<Map<String, Object>> findListBrandByArticleId(Long articleId) {
		return articleRepository.findListBrandByArticleId(articleId);
	}
}
