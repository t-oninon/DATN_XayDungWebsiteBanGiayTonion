package com.nico.store.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Boolean status;

	@OneToMany(mappedBy = "category", cascade=CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private Set<CategoryArticle> categoryArticles;

	public Category() {
	}

	public Category(String name, Boolean status, Set<CategoryArticle> categoryArticles) {
		this.name = name;
		this.status = status;
		this.categoryArticles = categoryArticles;
	}

	public Category(Long id, String name, Boolean status, Set<CategoryArticle> categoryArticles) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.categoryArticles = categoryArticles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Set<CategoryArticle> getCategoryArticles() {
		return categoryArticles;
	}

	public void setCategoryArticles(Set<CategoryArticle> categoryArticles) {
		this.categoryArticles = categoryArticles;
	}

	@Override
	public String toString() {
		return "Category{" +
				"id=" + id +
				", name='" + name + '\'' +
				", status=" + status +
				", categoryArticles=" + categoryArticles +
				'}';
	}
}
