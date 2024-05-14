package com.nico.store.store.repository;


import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.nico.store.store.domain.Brand;
import com.nico.store.store.domain.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.nico.store.store.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {
	
	@EntityGraph(attributePaths = { "sizes", "categoryArticles", "brandArticles" })
	List<Article> findAllEagerBy();	
		
	@EntityGraph(attributePaths = { "sizes", "categoryArticles", "brandArticles" })
	Optional<Article> findById(Long id);
	
	@Query("SELECT DISTINCT s.value FROM Size s")
	List<String> findAllSizes();

	@Query(
			value = "" +
					"select distinct \n" +
					"\ta.id,\n" +
					"\ta.picture,\n" +
					"\ta.price,\n" +
					"\ta.stock,\n" +
					"\ta.title,\n" +
					"\tgroup_concat(b.name separator ', ') as lstBrand ,\n" +
					"\tgroup_concat(s.value separator ', ') as lstSize,\n" +
					"\tgroup_concat(c.name separator ', ') as lstCategory\n" +
					"from\n" +
					"\tarticle a\n" +
					"join brand_article ba on\n" +
					"\tba.article_id = a.id\n" +
					"join brand b on\n" +
					"\tb.id = ba.brand_id\n" +
					"join category_article ca on\n" +
					"\tca.article_id = a.id\n" +
					"join category c on\n" +
					"\tc.id = ca.category_id\n" +
					"join `size` s on\n" +
					"\ts.article_id  = a.id\n" +
					"group by\n" +
					"\ta.id",
			nativeQuery = true
	)
	List<Map<String, Object>> findAllArticle();
	
	@Query(
			value = "" +
					"select\n" +
					"\tb.id,\n" +
					"\tb.name,\n" +
					"\tb.status \n" +
					"from\n" +
					"\tbrand b \n" +
					"join brand_article ba on\n" +
					"\tb.id = ba.brand_id \n" +
					"where\n" +
					"\tba.article_id = :articleId and \n" +
					"\tb.status = true",
			nativeQuery = true
	)
	List<Map<String, Object>> findListBrandByArticleId(Long articleId);

	@Query(
			value = "" +
					"select distinct \n" +
					"\tc.id,\n" +
					"\tc.name,\n" +
					"\tc.status\n" +
					"from\n" +
					"\tcategory c\n" +
					"join category_article ca on\n" +
					"\tc.id = ca.category_id\n" +
					"where\n" +
					"\tca.article_id = :articleId AND\n" +
					"\tc.status = true",
			nativeQuery = true
	)
	List<Map<String, Object>> findListCategoryByArticleId(Long articleId);

	@Query("SELECT DISTINCT c FROM Category c WHERE c.status = true")
	List<Category> findAllCategories();
	
	@Query("SELECT DISTINCT b FROM Brand b WHERE b.status = true")
	List<Brand> findAllBrands();


	
}
