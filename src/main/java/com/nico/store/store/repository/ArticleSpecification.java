package com.nico.store.store.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.nico.store.store.domain.*;
import org.springframework.data.jpa.domain.Specification;

public class ArticleSpecification {
	
	private ArticleSpecification() {}
	
	@SuppressWarnings("serial")
	public static Specification<Article> filterBy(Integer priceLow, Integer priceHigh, List<String> sizes, 
												  List<Long> categories, List<Long> brands, String search) {
		return new Specification<Article>() {
            @Override
            public Predicate toPredicate(Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();                
                query.distinct(true);                
                if (sizes!=null && !sizes.isEmpty()) {
                	Join<Article, Size> joinSize = root.join("sizes");
                	predicates.add(criteriaBuilder.and(joinSize.get("value").in(sizes)));
                }
                if (categories!=null && !categories.isEmpty()) {
                	Join<Article, CategoryArticle> joinSize = root.join("categoryArticles");
                	predicates.add(criteriaBuilder.and(joinSize.get("category").get("id").in(categories)));
                }
                if (brands!=null && !brands.isEmpty()) {
                	Join<Article, BrandArticle> joinSize = root.join("brandArticles");
                	predicates.add(criteriaBuilder.and(joinSize.get("brand").get("id").in(brands)));
                }  
                
                if(search!=null && !search.isEmpty()) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("title"), "%"+search+"%")));
                }
                if (priceLow!=null && priceLow >= 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), priceLow)));
                }
                if (priceHigh!=null && priceHigh >= 0) {
                    predicates.add(criteriaBuilder.and(criteriaBuilder.lessThanOrEqualTo(root.get("price"), priceHigh)));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };		
	}
}
