package org.kcsmini2.ojeommo.board.repository;

import org.kcsmini2.ojeommo.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
