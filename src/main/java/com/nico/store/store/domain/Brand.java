package com.nico.store.store.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Brand {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String name;

	private Boolean status;

	@OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<BrandArticle> brandArticles = new HashSet<>();


	public Brand() {
	}

	public Brand(String name, Boolean status, Set<BrandArticle> brandArticles) {
		this.name = name;
		this.status = status;
		this.brandArticles = brandArticles;
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

	public Set<BrandArticle> getBrandArticles() {
		return brandArticles;
	}

	public void setBrandArticles(Set<BrandArticle> brandArticles) {
		this.brandArticles = brandArticles;
	}

	@Override
	public String toString() {
		return "Brand{" +
				"id=" + id +
				", name='" + name + '\'' +
				", status=" + status +
				", brandArticles=" + brandArticles +
				'}';
	}
}
