package com.app.repository;

import com.app.model.entity.BusinessUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BusinessUserRepository extends JpaRepository<BusinessUser, Integer> {

    BusinessUser findByEmail(String email);

    Optional<BusinessUser> findById(int id);

}
