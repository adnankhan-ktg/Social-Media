package com.app.repository;

import com.app.model.entity.PostCategoryMst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCategoryMstRepository extends JpaRepository<PostCategoryMst, Integer> {

    PostCategoryMst findByName(String name);

}
