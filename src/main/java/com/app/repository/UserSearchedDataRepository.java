package com.app.repository;

import com.app.model.entity.UserSearchedData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSearchedDataRepository extends JpaRepository<UserSearchedData,Integer> {


}
