package com.nico.store.store.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.nico.store.store.dto.ArticleCusDTO;
import com.nico.store.store.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nico.store.store.domain.Article;
import com.nico.store.store.domain.ArticleBuilder;
import com.nico.store.store.domain.Brand;
import com.nico.store.store.domain.Category;
import com.nico.store.store.domain.Size;
import com.nico.store.store.service.ArticleService;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/article")
public class ArticleController {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private StorageService storageService;
	
	@RequestMapping("/add")
	public String addArticle(Model model) {
		model.addAttribute("article", new Article());
		model.addAttribute("allSizes", articleService.getAllSizes());
		model.addAttribute("allBrands", articleService.getAllBrands());
		model.addAttribute("allCategories", articleService.getAllCategories());
		return "addArticle";
	}

	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addArticlePost(@ModelAttribute("article") Article article,
								 HttpServletRequest request,
								 @RequestParam("file") MultipartFile file
	) {

		List<String> a = Arrays.asList(request.getParameter("category").split("\\s*,\\s*"));

		List<String> b = Arrays.asList(request.getParameter("brand").split("\\s*,\\s*"));

		List<Category> categoryList =  articleService.getAllCategories();
		List<Category> c = articleService.getAllCategories().stream().filter(e -> a.contains(e.getName())).collect(Collectors.toList());
		List<Brand> d = articleService.getAllBrands().stream().filter(e -> b.contains(e.getName())).collect(Collectors.toList());

		this.storageService.store(file);

		Article newArticle = new ArticleBuilder()
				.withTitle(article.getTitle())
				.stockAvailable(article.getStock())
				.withPrice(article.getPrice())
				.imageLink(file.getOriginalFilename())
				.sizesAvailable(Arrays.asList(request.getParameter("size").split("\\s*,\\s*")))
				.ofCategories(c)
				.ofBrand(d)
				.build();
		articleService.saveArticle(newArticle);
		return "redirect:article-list";
	}
	
	@RequestMapping("/article-list")
	public String articleList(Model model) {
		List<ArticleCusDTO> articles = articleService.findAllArticleCus();
		model.addAttribute("articles", articles);
		return "articleList";
	}
	
	@RequestMapping("/edit")
	public String editArticle(@RequestParam("id") Long id, Model model) {
		Article article = articleService.findArticleById(id);
		String preselectedSizes = "";
		for (Size size : article.getSizes()) {
			preselectedSizes += (size.getValue() + ",");
		}
		String preselectedBrands = "";
//		for (Brand brand : article.getBrands()) {
//			preselectedBrands += (brand.getName() + ",");
//		}
//		String preselectedCategories = "";
//		for (Category category : article.getCategories()) {
//			preselectedCategories += (category.getName() + ",");
//		}
		model.addAttribute("article", article);
		model.addAttribute("preselectedSizes", preselectedSizes);
		model.addAttribute("preselectedBrands", preselectedBrands);
//		model.addAttribute("preselectedCategories", preselectedCategories);
		model.addAttribute("allSizes", articleService.getAllSizes());
		model.addAttribute("allBrands", articleService.getAllBrands());
		model.addAttribute("allCategories", articleService.getAllCategories());
		return "editArticle";
	}
	
//	@RequestMapping(value="/edit", method=RequestMethod.POST)
//	public String editArticlePost(@ModelAttribute("article") Article article, HttpServletRequest request) {
//		Article newArticle = new ArticleBuilder()
//				.withTitle(article.getTitle())
//				.stockAvailable(article.getStock())
//				.withPrice(article.getPrice())
//				.imageLink(article.getPicture())
//				.sizesAvailable(Arrays.asList(request.getParameter("size").split("\\s*,\\s*")))
//				.ofCategories(Arrays.asList(request.getParameter("category").split("\\s*,\\s*")))
//				.ofBrand(Arrays.asList(request.getParameter("brand").split("\\s*,\\s*")))
//				.build();
//		newArticle.setId(article.getId());
//		articleService.saveArticle(newArticle);
//		return "redirect:article-list";
//	}
//
	@RequestMapping("/delete")
	public String deleteArticle(@RequestParam("id") Long id) {
		articleService.deleteArticleById(id);
		return "redirect:article-list";
	}
	
}
