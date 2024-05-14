package com.nico.store.store.controller;

import javax.websocket.server.PathParam;

import com.nico.store.store.domain.Brand;
import com.nico.store.store.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nico.store.store.domain.Article;
import com.nico.store.store.form.ArticleFilterForm;
import com.nico.store.store.service.ArticleService;
import com.nico.store.store.type.SortFilter;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class StoreController {
	
	@Autowired
	private ArticleService articleService;
	
	@RequestMapping("/store")
	public String store(@ModelAttribute("filters") ArticleFilterForm filters, Model model) {
		Integer page = filters.getPage();			
		int pagenumber = (page == null ||  page <= 0) ? 0 : page-1;
		SortFilter sortFilter = new SortFilter(filters.getSort());
		if(filters.getCategory() == null || filters.getCategory().isEmpty()) {
			filters.setCategory(articleService.getAllCategories().stream().map(Category::getId).collect(Collectors.toList()));
		}
		if(filters.getBrand() == null || filters.getBrand().isEmpty()) {
			filters.setBrand(articleService.getAllBrands().stream().map(Brand::getId).collect(Collectors.toList()));
		}

		Page<Article> pageresult = articleService.findArticlesByCriteria(PageRequest.of(pagenumber,9, sortFilter.getSortType()), 
																filters.getPricelow(), filters.getPricehigh(), 
																filters.getSize(), filters.getCategory(), filters.getBrand(), filters.getSearch());	
		model.addAttribute("allCategories", articleService.getAllCategories());
		model.addAttribute("allBrands", articleService.getAllBrands());
		model.addAttribute("allSizes", articleService.getAllSizes());
		model.addAttribute("articles", pageresult.getContent());
		model.addAttribute("totalitems", pageresult.getTotalElements());
		model.addAttribute("itemsperpage", 9);
		return "store";
	}
	
	
	@RequestMapping("/article-detail")
	public String articleDetail(@PathParam("id") Long id, Model model) {
		Article article = articleService.findArticleById(id);
		model.addAttribute("article", article);

		model.addAttribute("lstBrandName", articleService.findListBrandByArticleId(id).stream().map(e -> e.get("name").toString()).collect(Collectors.joining(", ")));
		model.addAttribute("lstCategoryName", articleService.findListCategoryByArticleId(id).stream().map(e -> e.get("name").toString()).collect(Collectors.joining(", ")));
		model.addAttribute("notEnoughStock", model.asMap().get("notEnoughStock"));
		model.addAttribute("addArticleSuccess", model.asMap().get("addArticleSuccess"));
		return "articleDetail";
	}
	

}
