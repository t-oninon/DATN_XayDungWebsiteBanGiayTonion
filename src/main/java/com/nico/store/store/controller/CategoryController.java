package com.nico.store.store.controller;

import com.nico.store.store.domain.*;
import com.nico.store.store.dto.ArticleCusDTO;
import com.nico.store.store.dto.CategoryDTO;
import com.nico.store.store.service.ArticleService;
import com.nico.store.store.service.CategoryService;
import com.nico.store.store.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private StorageService storageService;
	
//	@RequestMapping("/add")
//	public String addArticle(Model model) {
//		model.addAttribute("category", new Category());
//		return "addArticle";
//	}
//
//	@RequestMapping(value="/add", method=RequestMethod.POST)
//	public String addArticlePost(@ModelAttribute("category") Category category,
//								 HttpServletRequest request
//	) {
//		categoryService.saveCategory(category);
//		return "redirect:category-list";
//	}
	
	@RequestMapping("/category-list")
	public String articleList(Model model) {
		List<CategoryDTO> categories = categoryService.findAllCategory();
		model.addAttribute("categories", categories);
		return "categoryList";
	}
	
//	@RequestMapping("/edit")
//	public String editArticle(@RequestParam("id") Long id, Model model) {
//		Article article = articleService.findArticleById(id);
//		String preselectedSizes = "";
//		for (Size size : article.getSizes()) {
//			preselectedSizes += (size.getValue() + ",");
//		}
//		String preselectedBrands = "";
//
//		List<Map<String, Object>> brands = articleService.findListBrandByArticleId(id);
//		List<Map<String, Object>> categories = articleService.findListCategoryByArticleId(id);
//
//		for (Map brand : brands) {
//			preselectedBrands += ((String)brand.get("name") + ",");
//		}
//		String preselectedCategories = "";
//		for (Map category : categories) {
//			preselectedCategories += ((String)category.get("name") + ",");
//		}
//
//		model.addAttribute("article", article);
//		model.addAttribute("preselectedSizes", preselectedSizes);
//		model.addAttribute("preselectedBrands", preselectedBrands);
//		model.addAttribute("preselectedCategories", preselectedCategories);
//		model.addAttribute("allSizes", articleService.getAllSizes());
//		model.addAttribute("allBrands", articleService.getAllBrands());
//		model.addAttribute("allCategories", articleService.getAllCategories());
//		return "editArticle";
//	}
//
//	@RequestMapping(value="/edit", method=RequestMethod.POST)
//	public String editArticlePost(
//			@ModelAttribute("article") Article article,
//			HttpServletRequest request,
//			@RequestParam("file") MultipartFile file
//	) {
//		List<String> a = Arrays.asList(request.getParameter("category").split("\\s*,\\s*"));
//
//		List<String> b = Arrays.asList(request.getParameter("brand").split("\\s*,\\s*"));
//
//		List<Category> categoryList =  articleService.getAllCategories();
//		List<Category> c = articleService.getAllCategories().stream().filter(e -> a.contains(e.getName())).collect(Collectors.toList());
//		List<Brand> d = articleService.getAllBrands().stream().filter(e -> b.contains(e.getName())).collect(Collectors.toList());
//
//		this.storageService.store(file);
//
//		Article newArticle = new ArticleBuilder()
//				.withTitle(article.getTitle())
//				.stockAvailable(article.getStock())
//				.withPrice(article.getPrice())
//				.imageLink(article.getPicture())
//				.sizesAvailable(Arrays.asList(request.getParameter("size").split("\\s*,\\s*")))
//				.ofCategories(c)
//				.ofBrand(d)
//				.build();
//		newArticle.setId(article.getId());
//		articleService.saveArticle(newArticle);
//		return "redirect:article-list";
//	}
//
//	@RequestMapping("/delete")
//	public String deleteArticle(@RequestParam("id") Long id) {
//		articleService.deleteArticleById(id);
//		return "redirect:article-list";
//	}
	
}
