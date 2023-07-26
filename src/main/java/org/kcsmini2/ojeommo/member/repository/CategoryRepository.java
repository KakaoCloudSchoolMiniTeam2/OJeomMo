package org.kcsmini2.ojeommo.member.repository;

import org.kcsmini2.ojeommo.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
}
