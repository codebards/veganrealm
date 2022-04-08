package io.codebards.veganrealm.db;

import io.codebards.veganrealm.api.Recipe;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface Dao {

    @SqlQuery("SELECT 'bidu'")
    String healthCheck();

    @SqlQuery("SELECT COUNT(*) FROM search_recipes")
    int countAllRecipes();

    @SqlQuery("SELECT * FROM search_recipes LIMIT 12 OFFSET :offset")
    @RegisterBeanMapper(Recipe.class)
    List<Recipe> findAll(@Bind("offset") int offset);

    @SqlQuery("SELECT count(recipe_id) FROM search_recipes WHERE search_recipes MATCH :terms")
    int countRecipes(@Bind("terms") String terms);

    @SqlQuery("SELECT * FROM search_recipes WHERE search_recipes MATCH :terms ORDER BY rank LIMIT 12 OFFSET :offset")
    @RegisterBeanMapper(Recipe.class)
    List<Recipe> findRecipeByIds(@Bind("terms") String terms, @Bind("offset") int offset);
}
