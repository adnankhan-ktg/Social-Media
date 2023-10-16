package com.app.repository;

import com.app.model.entity.HashtagMst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashtagRepository extends JpaRepository<HashtagMst, Long> {

}