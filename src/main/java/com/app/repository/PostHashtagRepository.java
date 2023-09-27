package com.app.repository;

import com.app.model.entity.PostHashtag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostHashtagRepository extends JpaRepository<PostHashtag, Long> {
    // Custom queries if needed
}
