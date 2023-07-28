package org.kcsmini2.ojeommo.category.repository;

import org.kcsmini2.ojeommo.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    public Category findCategoryByCategoryName(String categoryName);
}
