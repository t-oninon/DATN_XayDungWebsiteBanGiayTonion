package com.nico.store.store.service;

import com.nico.store.store.domain.Article;
import com.nico.store.store.domain.Brand;
import com.nico.store.store.domain.Category;
import com.nico.store.store.dto.ArticleCusDTO;
import com.nico.store.store.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface CategoryService {
	List<CategoryDTO> findAllCategory();

	Category findCategoryById(Long id);
	
	Category saveCategory(Category category);

	void deleteCategoryById(Long id);
}
