package com.nico.store.store.service.impl;

import com.nico.store.store.domain.Article;
import com.nico.store.store.domain.Brand;
import com.nico.store.store.domain.Category;
import com.nico.store.store.dto.ArticleCusDTO;
import com.nico.store.store.dto.CategoryDTO;
import com.nico.store.store.repository.ArticleRepository;
import com.nico.store.store.repository.ArticleSpecification;
import com.nico.store.store.repository.CategoryRepository;
import com.nico.store.store.service.ArticleService;
import com.nico.store.store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<CategoryDTO> findAllCategory() {
		List<Category> categories = categoryRepository.findAll();
		List<CategoryDTO> categoryDTOS = new ArrayList<>();

		categories.forEach(c -> {
			CategoryDTO categoryDTO = new CategoryDTO();
			categoryDTO.setId(String.valueOf(c.getId()));
			categoryDTO.setName(c.getName());
			categoryDTO.setStatus(c.getStatus());

			categoryDTOS.add(categoryDTO);
		});
		return categoryDTOS;
	}

	@Override
	public Category findCategoryById(Long id) {
		return categoryRepository.findById(id).get();
	}

	@Override
	public Category saveCategory(Category category) {
		return categoryRepository.save(category);
	}

	@Override
	public void deleteCategoryById(Long id) {
		categoryRepository.deleteById(id);
	}
}
