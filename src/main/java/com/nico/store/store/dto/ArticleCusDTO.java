package com.nico.store.store.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nico.store.store.domain.BrandArticle;
import com.nico.store.store.domain.CategoryArticle;
import com.nico.store.store.domain.Size;
import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class ArticleCusDTO {
	private BigInteger id;

	private String title;

	private int stock;

	private double price;

	private String picture;

	Set<String> sizes;

	Set<String> lstBrandName;

	Set<String> lstCategoryName;
}
