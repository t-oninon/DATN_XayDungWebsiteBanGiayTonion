package com.nico.store.store.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ArticleBuilder {
		
	private String title;
	private int stock;	
	private double price;
	private String picture;
	private List<String> sizes;
	private List<Category> categoryArticles;
	private List<Brand> brandArticles;
	
	public ArticleBuilder() {
	}
	
	public ArticleBuilder withTitle(String title) {
		this.title = title;
		return this;
	}
	
	public ArticleBuilder stockAvailable(int stock) {
		this.stock = stock;
		return this;
	}
	
	public ArticleBuilder withPrice(double price) {
		this.price = price;
		return this;
	}
	
	public ArticleBuilder imageLink(String picture) {
		this.picture = picture;
		return this;
	}
	
	public ArticleBuilder sizesAvailable(List<String> sizes) {
		this.sizes = sizes;
		return this;
	}
	
	public ArticleBuilder ofCategories(List<Category> categories) {
		this.categoryArticles = categories;
		return this;
	}
	
	public ArticleBuilder ofBrand(List<Brand> brands) {
		this.brandArticles = brands;
		return this;
	}
	
	public Article build() {
		Article article = new Article();
		article.setTitle(this.title);
		article.setPrice(this.price);
		article.setStock(this.stock);
		article.setPicture(this.picture);		
		
		if (this.sizes != null && !this.sizes.isEmpty()) {
			Set<Size> sizeElements = new HashSet<>();
			for (String val : this.sizes) {
				sizeElements.add(new Size(val,article));
			}	
			article.setSizes(sizeElements);
		}
		
		if (this.categoryArticles != null && !this.categoryArticles.isEmpty() ) {
			Set<CategoryArticle> catElements = new HashSet<>();
			for (Category cal : this.categoryArticles) {
				catElements.add(new CategoryArticle(article, cal));
			}
			article.setCategoryArticles(catElements);
		}

		if (this.brandArticles != null && !this.brandArticles.isEmpty() ) {
			Set<BrandArticle> brandlements = new HashSet<>();
			for (Brand bra : this.brandArticles) {
				brandlements.add(new BrandArticle(article, bra));
			}
			article.setBrandArticles(brandlements);
		}
		
		
		return article;
	}
	
}