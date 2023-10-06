package com.app.repository;

import com.app.model.entity.CategoryMst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCategoryMstRepository extends JpaRepository<CategoryMst, Integer> {

    CategoryMst findByName(String name);

}
