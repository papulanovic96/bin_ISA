package com.bin448.backend.repository;

import com.bin448.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query(value = "UPDATE user SET city = ?2, email = ?3, last_name = ?4, name = ?5, telephone = ?6, username =?7 WHERE id = ?1", nativeQuery = true)
    void modifyUser(String city, String email, String lastName, String firstName, String telephone, String username);
  
    User getUserByUsername(String username);
    User getByUsernameAndPassword(String username, String password);
    User findByEmailIgnoreCase(String emailId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET active =?1 where username = ?2", nativeQuery = true)
    void modifyByUsername(boolean active, String username);
}
