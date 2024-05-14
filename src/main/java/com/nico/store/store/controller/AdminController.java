package com.nico.store.store.controller;

import com.nico.store.store.domain.Article;
import com.nico.store.store.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class AdminController {
		
	@Autowired
	private ArticleService articleService;
	
	
	@RequestMapping("/admin")
	public String index(Model model) {		
		List<Article> articles = articleService.findFirstArticles();
		model.addAttribute("articles", articles);
		return "adminIndex";
	}

	
}
