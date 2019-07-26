package com.bin448.backend.repository;

import com.bin448.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User getUserByUsername(String username);

}
