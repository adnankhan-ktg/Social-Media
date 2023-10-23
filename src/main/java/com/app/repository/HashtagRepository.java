package com.app.repository;

import com.app.model.entity.HashtagMst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<HashtagMst, Integer> {

   List<HashtagMst> findByIdIn(List<Integer> list);
}