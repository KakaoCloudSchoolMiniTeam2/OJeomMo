package org.kcsmini2.ojeommo.member.repository;

import org.kcsmini2.ojeommo.category.entity.FavoriteCategory;
import org.kcsmini2.ojeommo.category.entity.FavoriteCategoryPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteCategoryRepository extends JpaRepository<FavoriteCategory, FavoriteCategoryPk> {
}
