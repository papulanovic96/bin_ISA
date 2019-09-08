package com.bin448.backend.repository;

import com.bin448.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Modifying
    @Query(value = "UPDATE isa.user SET city = ?1, email = ?2, last_name = ?3, name = ?4, telephone = ?5 WHERE username = ?6", nativeQuery = true)
    void modifyUser(String city, String email, String lastName, String firstName, String telephone, String username);

    User getUserById(Long id);
    User getUserByUsername(String username);
    User getByUsernameAndPassword(String username, String password);
    User findByEmailIgnoreCase(String emailId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user SET active =?1 where username = ?2", nativeQuery = true)
    void modifyByUsername(boolean active, String username);

    @Query(value = "SELECT * FROM isa.user WHERE username = ?1", nativeQuery = true)
    User findByUsername(String username);
}
